package br.gasc.utils;

public class CpfUtil {
	public static String cpfFormatado(String cpf) {
		if(cpf == null)
			return "";
		
		if(cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}"))
			return cpf;		
		return cpf.replaceAll("(\\d{3})(\\d{3})(\\d{3})(\\d{2})", "$1.$2.$3-$4"); 
	}
	
}
