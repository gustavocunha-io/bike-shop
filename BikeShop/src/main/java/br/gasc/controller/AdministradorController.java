package br.gasc.controller;

import org.mindrot.jbcrypt.BCrypt;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gasc.dao.DAO;
import br.gasc.model.Administrador;
import br.gasc.service.AdministradorService;

import static br.gasc.constantes.Constantes.*;

import jakarta.servlet.http.HttpSession;

@Controller
public class AdministradorController {
	@GetMapping("adminDefault")
	@ResponseBody
	public String criarAdminInicial() {
	    Administrador admin = new Administrador();
	    
	    admin.setLogin("gustavo");
	    admin.setSenha(BCrypt.hashpw("123456", BCrypt.gensalt()));
	    new DAO<Administrador>(Administrador.class).inserir(admin);
	    
	    return "Admin criado com sucesso!";
	}
	
	@RequestMapping(value = "loginAdmin", method = RequestMethod.GET)
	public String loginAdmin() {
		return "login-administrador";
	}
	
	@RequestMapping(value = "paginaInicial", method = RequestMethod.GET)
	public String paginaInicial() {
		return "pagina-inicial";
	}
	
	@RequestMapping(value = "efetuaLogin", method = {RequestMethod.GET, RequestMethod.POST})
	public String efetuaLogin(Administrador administrador, HttpSession session, Model model) {
		AdministradorService administradorService = new AdministradorService();
		
		if(administradorService.autenticar(administrador)) {
			session.setAttribute("adminLogado", administrador.getLogin());
			return "redirect:paginaInicial";
		}
		
		model.addAttribute("mensagemErro", MSG_ERRO_LOGIN_ADMIN);
		return "login-administrador";	
	}
	
	@RequestMapping(value = "logoutAdmin")
	public String logoutAdmin(HttpSession session) {
		session.invalidate();
		return "redirect:loginAdmin";
	}
	
}
