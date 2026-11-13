package br.gasc.constantes;

public final class Constantes {
	// Mensagens de erro administrador. 
	public static final String MSG_ERRO_LOGIN_ADMIN = "Erro: usuário ou senha incorretos";

	// Mensagens de erro cliente.
	public static final String MSG_ERRO_CLIENTE_NULO = "Erro: o cliente não pode ser null";
	public static final String MSG_ERRO_CLIENTE_NAO_ENCONTRADO = "Erro: cliente não encontrado"; 
	public static final String MSG_ERRO_CPF_DUPLICADO = "Erro: cpf já cadastrado";
	public static final String MSG_ERRO_DATA_NASCIMENTO_INVALIDA = "Erro: a data de nascimento não pode ser futura";
	
	// Mensagens de sucesso cliente.
	public static final String MSG_SUCESSO_CADASTRO_CLIENTE = "Status: cliente cadastrado com sucesso";
	
	//Mensagens de sucesso bicicleta.
	public static final String MSG_SUCESSO_CADASTRO_BICICLETA = "Status: bicicleta cadastrada com sucesso";
	
	// Mensagens de erro cpf.
	public static final String MSG_ERRO_CPF_NULO = "Erro: o cpf inserido não pode ser nulo";
	public static final String MSG_ERRO_CPF_INEXISTENTE = "Erro: o cpf inserido não existe no sistema";
	
	// Mensagens de erro servico.
	public static final String MSG_ERRO_SERVICO_NULO = "Erro: insira os dados do serviço";
	public static final String MSG_ERRO_TIPO_SERVICO_NULO = "Erro: o tipo do serviço não pode ser nulo";
	public static final String MSG_ERRO_SERVICO_DUPLICADO = "Erro: esse serviço ja existe";
	
	// Mensagens de sucesso servico.
	public static final String MSG_SUCESSO_CADASTRO_SERVICO = "Sucesso: o serviço foi cadastrado no sistema!";
	
	private Constantes() {}

}
