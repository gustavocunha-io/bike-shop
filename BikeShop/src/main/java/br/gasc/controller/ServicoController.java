package br.gasc.controller;

import java.security.Provider.Service;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Servico;
import br.gasc.service.ServicoService;
import jakarta.validation.Valid;

import static br.gasc.constantes.Constantes.*;

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
			return "redirect:/servico/listarServico";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "servico/cadastrar";
		}
	}
	
	@RequestMapping(value = "listarServicos")
	public String listar(Model model) {
		model.addAttribute("servicosList", servicoService.listarServicos());
		return "servico/listar";
	}
	
}
