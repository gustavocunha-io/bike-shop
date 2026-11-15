package br.gasc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Bicicleta;
import br.gasc.model.Cliente;
import br.gasc.service.BicicletaService;
import br.gasc.service.ClienteService;
import br.gasc.service.TipoBicicletaService;
import jakarta.validation.Valid;

@Controller
@RequestMapping(value = "/bicicleta")
public class BicicletaController {
	private BicicletaService bicicletaService;

	public BicicletaController() {
		this.bicicletaService = new BicicletaService();
	}
	
	@RequestMapping(value = "cadastrarBicicleta")
	public String formBicicleta(Model model) {
		TipoBicicletaService bicicletaService = new TipoBicicletaService();
		model.addAttribute("tipoBicicletasList", bicicletaService.listarTipoBicicleta());
		
		return "bicicleta/cadastrar";
	}
	
	@RequestMapping(value = "salvarBicicleta")
	public String cadastrar(@Valid Bicicleta bicicleta, 
	  @RequestParam(name = "cpfProprietario", required = false)
	  String cpfProprietario, BindingResult bindingResult,
	  Model model,
	  RedirectAttributes redirectAttributes) {
		if(bindingResult.hasErrors())
			return "bicicleta/cadastrar";
		
		try {
			if(cpfProprietario != null) {
				ClienteService clienteService = new ClienteService();
				Cliente cliente = clienteService.pesquisarPorCpf(cpfProprietario);
				
				if(cliente == null)
					return "bicicleta/cadastrar";
				
				bicicleta.setProprietario(cliente);
			}
			
			bicicletaService.cadastrarBicicleta(bicicleta);
			redirectAttributes.addAttribute("sucesso", "Bicicleta cadastrada com sucesso");
			
			return "redirect:/bicicleta/listarBicicletas";
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
