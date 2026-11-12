package br.gasc.utils;

import org.mindrot.jbcrypt.BCrypt;

public class HashSenhaUtil {

	public static boolean verificarSenha(String senha, String hashSenha) {
		return BCrypt.checkpw(senha, hashSenha);
	}
	
	public static String gerarHashSenha(String senha) {
		return BCrypt.hashpw(senha, BCrypt.gensalt());
	}
	
}