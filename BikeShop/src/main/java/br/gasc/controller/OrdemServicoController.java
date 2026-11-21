package br.gasc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.enums.StatusOrdemServico;
import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Bicicleta;
import br.gasc.model.Cliente;
import br.gasc.model.ItemOrdemServico;
import br.gasc.model.OrdemServico;
import br.gasc.model.Servico;
import br.gasc.service.BicicletaService;
import br.gasc.service.ClienteService;
import br.gasc.service.OrdemServicoService;
import br.gasc.service.ServicoService;
import jakarta.validation.Valid;

import static br.gasc.constantes.Constantes.*;

@Controller
@RequestMapping(value = "/ordemServico")
public class OrdemServicoController {
	private OrdemServicoService ordemServicoService;
	
	public OrdemServicoController() {
		this.ordemServicoService = new OrdemServicoService();
	}
	
	@RequestMapping(value = "cadastrarOrdemServico")
	public String formOrdemServico(@RequestParam(value = "idCliente", required = false) Long idCliente, Model model) {
		if (idCliente == null)
			return "redirect:/ordemServico/pesquisarCliente";

		ClienteService clienteService = new ClienteService();
		BicicletaService bicicletaService = new BicicletaService();
		ServicoService servicoService = new ServicoService();

		Cliente cliente = clienteService.pesquisarPorId(idCliente);
		
		if (cliente == null)
		    return "redirect:/ordemServico/pesquisarCliente";
		
		List<Bicicleta> bicicletasClienteList = bicicletaService.obterBicicletasCliente(idCliente);

		model.addAttribute("cliente", cliente);
		model.addAttribute("bicicletasClienteList", bicicletasClienteList);
		model.addAttribute("servicosList", servicoService.listarServicos());

		return "ordem-servico/cadastrar";

	}
	
	@RequestMapping(value = "pesquisarCliente")
	public String formPesquisarCliente() {
		return "ordem-servico/pesquisar-cliente";
	}
	
	@RequestMapping(value = "salvarOrdemServico")
	public String salvarOrdemServico(@Valid OrdemServico ordemServico, BindingResult bindingResult,
	@RequestParam(value = "idBicicleta") Long idBicicleta,
	@RequestParam(value = "idCliente") Long idCliente,
	@RequestParam(value = "servicosIDs", required = true) List<Long> servicosIDs,
	@RequestParam(value = "aplicarDesconto", defaultValue = "false") boolean aplicarDesconto,
	Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors()) {
			redirectAttributes.addAttribute("idCliente", idCliente);
			return "redirect:/ordemServico/cadastrarOrdemServico";
		}
		
		try {
			List<ItemOrdemServico> itemOrdemServicosList = new ArrayList<ItemOrdemServico>();
			ServicoService servicoService = new ServicoService();
			BigDecimal valorBruto = BigDecimal.ZERO;
			
			for(Long id : servicosIDs) {
				Servico servico = servicoService.pesquisarServicoId(id);
				ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
				BigDecimal valorServico = servico.getValor();
				
				itemOrdemServico.setOrdemServico(ordemServico);
				itemOrdemServico.setServico(servico);
				itemOrdemServico.setValorUnitario(valorServico);
				itemOrdemServicosList.add(itemOrdemServico);
				
				valorBruto = valorBruto.add(valorServico);
			}
			
			BigDecimal desconto = BigDecimal.ZERO;
			if(servicosIDs.size() >= 3 && aplicarDesconto)
				desconto = valorBruto.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).multiply(DESCONTO_SERVICOS) ;
				
			BigDecimal valorFinal = valorBruto.subtract(desconto);
			
			ordemServico.setValorBruto(valorBruto);
			ordemServico.setValorFinal(valorFinal);
			ordemServico.setServicosList(itemOrdemServicosList);

			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			ordemServico.setCliente(cliente);
			
			Bicicleta bicicleta = new Bicicleta();
			bicicleta.setId(idBicicleta);
			ordemServico.setBicicleta(bicicleta);
			
			ordemServicoService.cadastrarOrdemServico(ordemServico);
			redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESSO_CADASTRAR_ORDEM_SERVICO);
			
			return "redirect:/ordemServico/listarOrdemServico";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			redirectAttributes.addAttribute("idCliente", idCliente);
			
			return "redirect:/ordemServico/cadastrarOrdemServico";
		}
	}

	@ResponseBody
	@RequestMapping(value = "cancelarOrdemServicoAjax")
	public void cancelarOrdemServico(@RequestParam(value = "id") Long id, Model model) {
		try {
			OrdemServico ordemServico = ordemServicoService.pesquisarPorId(id);
			
			ordemServico.setStatus(StatusOrdemServico.CANCELADA);
			ordemServicoService.alterarOrdemServico(ordemServico);
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
		}
	}
	
	@RequestMapping(value = "listarOrdemServico")
	public String listar(Model model) {
		model.addAttribute("ordensServicosList", ordemServicoService.listarOrdemServicoPorStatus(StatusOrdemServico.AGENDADA));
		return "ordem-servico/listar";
	}
	
	@RequestMapping(value = "listarOrdensServicos", method = {RequestMethod.POST, RequestMethod.GET})
	public String listarOrdensServicos(
	@RequestParam(value = "status", required = false, defaultValue = "AGENDADA") String statusFiltro,
	@RequestParam(value = "dataInicio", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataInicio,
	@RequestParam(value = "dataExata", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dataExata,
	Model model) {

	    List<OrdemServico> lista = ordemServicoService.filtrarOrdemServico(statusFiltro, dataInicio, dataExata);
	    
	    model.addAttribute("ordensServicosList", lista);
	    model.addAttribute("statusFiltro", statusFiltro);
	    model.addAttribute("dataInicio", dataInicio);
	    model.addAttribute("dataExata", dataExata);
	    
	    return "ordem-servico/listar";
	}

	@RequestMapping(value = "detalhesAjax", method = RequestMethod.POST)
	public String detalhesAjax(@RequestParam("id") Long id, Model model) {
	    OrdemServico ordemServico = ordemServicoService.pesquisarPorIdComServicos(id);
	    model.addAttribute("ordemServico", ordemServico);
	    return "ordem-servico/detalhes";
	}
	
	@RequestMapping(value = "executarOrdemServico", method = {RequestMethod.POST, RequestMethod.GET})
	public String executarOrdemServico(@RequestParam("id") Long id, Model model) {
	    OrdemServico ordemServico = ordemServicoService.pesquisarPorIdComServicos(id);

	    if (ordemServico == null)
	        return "redirect:/ordemServico/listarOrdemServico";
	    
	    List<Long> idsNaOrdem = new ArrayList<>();
	    
	    for (ItemOrdemServico item : ordemServico.getServicosList())
	        idsNaOrdem.add(item.getServico().getId());

	    List<Servico> servicosCatalogo = new ArrayList<>();
	    
	    for (Servico servico : new ServicoService().listarServicos())
	        if (!idsNaOrdem.contains(servico.getId()))
	            servicosCatalogo.add(servico);

	    model.addAttribute("ordemServico", ordemServico);
	    model.addAttribute("servicosCatalogo", servicosCatalogo);
	    model.addAttribute("dataExecucao", LocalDate.now());
	    model.addAttribute("aplicarDesconto", false);

	    return "ordem-servico/executar";
	}
	
	@RequestMapping(value = "confirmarExecucao", method = RequestMethod.POST)
	public String confirmarExecucaoOrdemServico(
	        @RequestParam("idOrdemServico") Long idOrdemServico,
	        @RequestParam("dataExecucao") String dataExecucaoStr,
	        @RequestParam(value = "servicosSelecionadosIds", required = false) List<Long> servicosSelecionadosIds,
	        @RequestParam(value = "aplicarDesconto", defaultValue = "false") boolean aplicarDesconto,
	        RedirectAttributes redirectAttributes) {

	    if (servicosSelecionadosIds == null || servicosSelecionadosIds.isEmpty()) {
	        redirectAttributes.addFlashAttribute("erro", "Selecione pelo menos um serviço executado.");
	        redirectAttributes.addAttribute("id", idOrdemServico);
	        return "redirect:/ordemServico/executarOrdemServico";
	    }

	    try {
	        OrdemServico ordemServico = ordemServicoService.pesquisarPorIdComServicos(idOrdemServico);

	        if (ordemServico == null)
	            return "redirect:/ordemServico/listarOrdemServico";

	        ServicoService servicoService = new ServicoService();

	        ordemServico.getServicosList().removeIf(item -> !servicosSelecionadosIds.contains(item.getServico().getId()));

	        List<Long> idsNaOrdem = new ArrayList<>();
	        
	        for (ItemOrdemServico item : ordemServico.getServicosList())
	            idsNaOrdem.add(item.getServico().getId());

	        for (Long idServico : servicosSelecionadosIds) {
	            if (idsNaOrdem.contains(idServico))
	                continue;

	            Servico servico = servicoService.pesquisarServicoId(idServico);
	            ItemOrdemServico novo = new ItemOrdemServico();
	            
	            novo.setOrdemServico(ordemServico);
	            novo.setServico(servico);
	            novo.setValorUnitario(servico.getValor());
	            ordemServico.getServicosList().add(novo);
	        }

	        BigDecimal valorBruto = BigDecimal.ZERO;
	        
	        for (ItemOrdemServico item : ordemServico.getServicosList())
	            valorBruto = valorBruto.add(item.getValorUnitario());

	        BigDecimal desconto = BigDecimal.ZERO;
	        
	        if (aplicarDesconto && ordemServico.getServicosList().size() >= 3)
	            desconto = valorBruto.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP).multiply(DESCONTO_SERVICOS);

	        ordemServico.setValorBruto(valorBruto);
	        ordemServico.setValorFinal(valorBruto.subtract(desconto));
	        ordemServico.setDataExecucao(Date.valueOf(dataExecucaoStr));
	        ordemServico.setStatus(StatusOrdemServico.EXECUTADA);
	        ordemServicoService.alterarOrdemServico(ordemServico);
	        redirectAttributes.addFlashAttribute("sucesso", "Ordem de serviço executada com sucesso!");
	        
	        return "redirect:/ordemServico/listarOrdemServico";
	    } catch (RegraNegocioException regraNegocioException) {
	        redirectAttributes.addFlashAttribute("erro", regraNegocioException.getMessage());
	        redirectAttributes.addAttribute("id", idOrdemServico);
	        return "redirect:/ordemServico/executarOrdemServico";
	    }
	}
	
	@RequestMapping(value = "pagar", method = RequestMethod.POST)
	public String pagarOrdemServico(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
	    try {
	        OrdemServico ordemServico = ordemServicoService.pesquisarPorId(id);

	        if (ordemServico == null) {
	            redirectAttributes.addFlashAttribute("erro", "Ordem de serviço não encontrada.");
	            return "redirect:/ordemServico/listarOrdemServico";
	        }

	        if (ordemServico.getStatus() != StatusOrdemServico.EXECUTADA) {
	            redirectAttributes.addFlashAttribute("erro", "Só é possível pagar ordens de serviço executadas.");
	            return "redirect:/ordemServico/listarOrdemServico";
	        }

	        ordemServico.setStatus(StatusOrdemServico.PAGA);
	        ordemServicoService.alterarOrdemServico(ordemServico);
	        redirectAttributes.addFlashAttribute("sucesso", "Pagamento registrado com sucesso!");
	        
	        return "redirect:/ordemServico/listarOrdemServico";
	    } catch (RegraNegocioException regraNegocioException) {
	        redirectAttributes.addFlashAttribute("erro", regraNegocioException.getMessage());
	        return "redirect:/ordemServico/listarOrdemServico";
	    }
	}
	
	@RequestMapping(value = "buscarCliente")
	public String buscarCliente(Model model,
	@RequestParam(value = "cpf") String cpf,
	RedirectAttributes redirectAttributes) {	
		if(cpf == null || cpf.isEmpty())
			return "ordem-servico/cadastrar";
		
		ClienteService clienteService = new ClienteService();
		
		try {
			Cliente cliente = clienteService.pesquisarPorCpf(cpf);
			
			if(cliente != null) {				
				redirectAttributes.addAttribute("idCliente", cliente.getId());
				return "redirect:/ordemServico/cadastrarOrdemServico";
			}
			
			model.addAttribute("erro", MSG_ERRO_CLIENTE_NAO_ENCONTRADO);
			return "ordem-servico/pesquisar-cliente";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "ordem-servico/pesquisar-cliente";
		}
		
	}
	
}
