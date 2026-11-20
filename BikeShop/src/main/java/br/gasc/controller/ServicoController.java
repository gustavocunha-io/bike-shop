package br.gasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Servico;
import br.gasc.service.ServicoService;
import jakarta.validation.Valid;

import static br.gasc.constantes.Constantes.*;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

@Controller
@RequestMapping("/servico")
public class ServicoController {
	private ServicoService servicoService;
	
	public ServicoController() {
		this.servicoService = new ServicoService();
	}
	
	@RequestMapping(value = "cadastrarServico", method = RequestMethod.GET)
	public String formServico() {
		return "servico/cadastrar";
	}
	
	@RequestMapping(value = "salvarServico")
	public String cadastrar(@Valid Servico servico, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "servico/cadastrar";
		
		try {
			servicoService.cadastrarServico(servico);
			redirectAttributes.addAttribute("sucesso", MSG_SUCESSO_CADASTRO_SERVICO);
			return "redirect:/servico/listarServicos";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "servico/cadastrar";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "alterarPrecoAjax", method = RequestMethod.POST)
	public String alterarPrecoAjax(@RequestParam(value = "id") Long id,
	@RequestParam(value = "valor") BigDecimal novoValor) {
		Servico servico = servicoService.pesquisarServicoId(id);
		
		servico.setValor(novoValor);
		servicoService.alterarServico(servico);
		
		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"));
		
		return numberFormat.format(novoValor);
	}
	
	@RequestMapping(value = "listarServicos")
	public String listar(Model model) {
		model.addAttribute("servicosList", servicoService.listarServicos());
		return "servico/listar";
	}
	
}
