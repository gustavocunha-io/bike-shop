package br.gasc.exception;

public class RegraNegocioException extends RuntimeException {
	private static final long serialVersionUID = -6857249298703948635L;

	public RegraNegocioException(String mensagem) {
		super(mensagem);
	}

}