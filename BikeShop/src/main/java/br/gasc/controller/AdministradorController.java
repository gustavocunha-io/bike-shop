package br.gasc.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.gasc.dao.AdministradorDao;
import br.gasc.dao.DAO;
import br.gasc.model.Administrador;
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
	
	@RequestMapping(value = "efetuaLogin")
	public String efetuaLogin(Administrador administrador, HttpSession session) {
		if(!new AdministradorDao().validarLogin(administrador)) 
			return "redirect:loginAdmin";
		
		session.setAttribute("adminLogado", administrador);
		return "pagina-inicial";
	}
	
	@RequestMapping(value = "paginaInicial", method = RequestMethod.GET)
	public String paginaInicial() {
		return "pagina-inicial";
	}
}
