package br.gasc.constantes;

public final class Constantes {
	// Mensagens de erro administrador. 
	public static final String MSG_ERRO_LOGIN_ADMIN = "erro: usuário ou senha incorretos";

	// Mensagens de erro cliente.
	public static final String MSG_ERRO_CLIENTE_NULO = "erro: o cliente não pode ser null";
	public static final String MSG_ERRO_CPF_DUPLICADO = "erro: cpf já cadastrado";
	public static final String MSG_ERRO_DATA_NASCIMENTO_INVALIDA = "erro: a data de nascimento não pode ser futura";
	
	// Mensagens de sucesso cliente.
	public static final String MSG_SUCESSO_CADASTRO_CLIENTE = "status: o cliente foi cadastrado no sistema!";
	
	// Mensagens de erro servico.
	public static final String MSG_ERRO_SERVICO_NULO = "erro: insira os dados do serviço";
	public static final String MSG_ERRO_TIPO_SERVICO_NULO = "erro: o tipo do serviço não pode ser nulo";
	public static final String MSG_ERRO_SERVICO_DUPLICADO = "erro: esse serviço ja existe";
	
	// Mensagens de sucesso servico.
	public static final String MSG_SUCESSO_CADASTRO_SERVICO = "sucesso: o serviço foi cadastrado no sistema!";
	
	private Constantes() {}

}
