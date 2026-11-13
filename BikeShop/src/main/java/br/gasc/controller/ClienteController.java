package br.gasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Cliente;
import br.gasc.service.ClienteService;
import jakarta.validation.Valid;

import static br.gasc.constantes.Constantes.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	private ClienteService clienteService;
	
	public ClienteController() {
		this.clienteService = new ClienteService();
	}
	
	@RequestMapping(value = "cadastrarCliente")
	public String formCliente() {
		return "cliente/cadastrar";
	}
	
	@RequestMapping(value = "salvarCliente")
	public String cadastrar(@Valid Cliente cliente, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "cliente/cadastrar";
		
		try {
			clienteService.cadastrarCliente(cliente);
			redirectAttributes.addFlashAttribute("sucesso", MSG_SUCESSO_CADASTRO_CLIENTE);
			return "redirect:/cliente/listarClientes";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "cliente/cadastrar";
		}	
	}
	
	@RequestMapping(value = "listarClientes")
	public String listar(Model model) {
		model.addAttribute("clientesList", clienteService.listarClientes());
		return "cliente/listar";
	}

}