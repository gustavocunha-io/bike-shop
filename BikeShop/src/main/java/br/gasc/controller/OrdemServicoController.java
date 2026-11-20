package br.gasc.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
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
			OrdemServicoService ordemServicoService = new OrdemServicoService();
			OrdemServico ordemServico = ordemServicoService.pesquisarPorId(id);
			
			ordemServico.setStatus(StatusOrdemServico.CANCELADA);
			ordemServicoService.alterarOrdemServico(ordemServico);
		} catch (Exception e) {
			model.addAttribute("erro", MSG_ERRO_CANCELAR_ORDEM_SERVICO);
		}
	}
	
	@RequestMapping(value = "listarOrdemServico")
	public String listar(Model model) {
		model.addAttribute("ordensServicosList", ordemServicoService.listarOrdemServicoPorStatus(StatusOrdemServico.AGENDADA));
		return "ordem-servico/listar";
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
