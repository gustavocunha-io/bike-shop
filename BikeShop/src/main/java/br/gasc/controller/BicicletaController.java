package br.gasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Bicicleta;
import br.gasc.service.BicicletaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/bicicleta")
public class BicicletaController {
	private BicicletaService bicicletaService;

	public BicicletaController() {
		this.bicicletaService = new BicicletaService();
	}
	
	@RequestMapping(value = "cadastrarBicicleta")
	public String formBicicleta() {
		return "bicicleta/cadastrar";
	}
	
	@RequestMapping(value = "salvarBicicleta")
	public String cadastrar(@Valid Bicicleta bicicleta, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "bicicleta/cadastrar";
		
		try {
			bicicletaService.cadastrarBicicleta(bicicleta);
			redirectAttributes.addAttribute("sucesso", "Bicicleta cadastrada com sucesso");
			return "redirect:/bicicleta/listar";
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
			return "bicicleta/listar";			
		}
	}
	
	@RequestMapping(value = "listarBicicletas")
	public String listar(Model model) {
		 model.addAttribute("bicicletasList", bicicletaService.listaBicicleta());
		 return "bicicleta/listar";
	}
	
}
