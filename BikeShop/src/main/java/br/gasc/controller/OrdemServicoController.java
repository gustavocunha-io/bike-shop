package br.gasc.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String formOrdemServico(Model model) {
		ServicoService servicoService = new ServicoService();
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
	Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "ordem-servico/cadastrar";
		
		try {
			List<ItemOrdemServico> itemOrdemServicosList = new ArrayList<ItemOrdemServico>();
			ServicoService servicoService = new ServicoService();
			BigDecimal valorTotal = BigDecimal.ZERO;
			
			for(Long id : servicosIDs) {
				Servico servico = servicoService.pesquisarServicoId(id);
				ItemOrdemServico itemOrdemServico = new ItemOrdemServico();
				BigDecimal valorServico = servico.getValor();
				
				itemOrdemServico.setOrdemServico(ordemServico);
				itemOrdemServico.setServico(servico);
				itemOrdemServico.setValor(valorServico);
				itemOrdemServicosList.add(itemOrdemServico);
				
				valorTotal.add(valorServico);
			}
			
			ordemServico.setServicosList(itemOrdemServicosList);

			Cliente cliente = new Cliente();
			cliente.setId(idCliente);
			ordemServico.setCliente(cliente);
			
			Bicicleta bicicleta = new Bicicleta();
			bicicleta.setId(idBicicleta);
			ordemServico.setBicicleta(bicicleta);
			
			ordemServico.setValorTotal(valorTotal);
			
			ordemServicoService.cadastrarOrdemServico(ordemServico);
			redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESSO_CADASTRAR_ORDEM_SERVICO);
			
			return "redirect:/ordemServico/listarOrdemServico";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "ordem-servico/cadastrar";
		}
	}
	
	@RequestMapping(value = "listarOrdemServico")
	public String listar(Model model) {
		model.addAttribute("ordensServicosList", ordemServicoService.listarOrdemServico());
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
				BicicletaService bicicletaService = new BicicletaService();
				List<Bicicleta> bicicletasClienteList = bicicletaService.obterBicicletasCliente(cliente.getId());
				
				cliente.setCpf(cpf);				
				redirectAttributes.addFlashAttribute("cliente", cliente);
				redirectAttributes.addFlashAttribute("bicicletasClienteList", bicicletasClienteList);
	
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
