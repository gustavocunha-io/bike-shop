package br.gasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import br.gasc.dao.DAO;
import br.gasc.model.Cliente;
import br.gasc.service.ClienteService;
import jakarta.validation.Valid;

import static br.gasc.constantes.Constantes.*;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@RequestMapping(value = "cadastrarCliente")
	public String formCliente() {
		return "cliente/cadastrar";
	}
	
	@RequestMapping(value = "salvarCliente")
	public String cadastrar(@Valid Cliente cliente, BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors())
			return "cliente/cadastrar";
		
		ClienteService clienteService = new ClienteService();
		
		if(clienteService.validarCpf(cliente.getCpf())) {
			model.addAttribute("mensagem", MSG_ERRO_CPF_DUPLICADO);
			return "cliente/cadastrar";
		}
		
		DAO<Cliente> dao = new DAO<Cliente>(Cliente.class);		
		
		dao.inserir(cliente);
		model.addAttribute("mensagem", MSG_SUCESSO_CADASTRO_CLIENTE);
		
		return "redirect:paginaInicial";
	}

}
