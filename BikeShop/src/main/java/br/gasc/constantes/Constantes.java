package br.gasc.constantes;

import java.math.BigDecimal;

public final class Constantes {
	// Mensagens de erro administrador. 
	public static final String MSG_ERRO_LOGIN_ADMIN = "Erro: usuário ou senha incorretos";

	// Mensagens de erro cliente.
	public static final String MSG_ERRO_CLIENTE_NULO = "Erro: o cliente não pode ser null";
	public static final String MSG_ERRO_CLIENTE_NAO_ENCONTRADO = "Erro: cliente não encontrado"; 
	public static final String MSG_ERRO_CPF_DUPLICADO = "Erro: cpf já cadastrado";
	public static final String MSG_ERRO_DATA_NASCIMENTO_NULA = "Erro: a data de nascimento não pode ser nula";
	public static final String MSG_ERRO_DATA_NASCIMENTO_FUTURA = "Erro: a data de nascimento não pode ser futura";
	public static final String MSG_ERRO_CPF_DIFERENTE_DE_11 = "Erro: o cpf deve conter 11 dígitos";
	public static final String MSG_ERRO_CPF_NULO = "Erro: o cpf não pode ser nulo";
	
	// Mensagens de sucesso cliente.
	public static final String MSG_SUCESSO_CADASTRO_CLIENTE = "Status: cliente cadastrado com sucesso";
	
	//Mensagens de sucesso bicicleta.
	public static final String MSG_SUCESSO_CADASTRO_BICICLETA = "Status: bicicleta cadastrada com sucesso";
	
	// Mensagens de erro cpf.
	public static final String MSG_ERRO_CPF_INEXISTENTE = "Erro: o cpf inserido não existe no sistema";
	
	// Mensagens de erro servico.
	public static final String MSG_ERRO_SERVICO_NULO = "Erro: insira os dados do serviço";
	public static final String MSG_ERRO_TIPO_SERVICO_NULO = "Erro: o tipo do serviço não pode ser nulo";
	public static final String MSG_ERRO_SERVICO_DUPLICADO = "Erro: esse serviço ja existe";
	
	// Mensagens de sucesso servico.
	public static final String MSG_SUCESSO_CADASTRO_SERVICO = "Sucesso: o serviço foi cadastrado no sistema!";
	
	// Mensagem de erro ordem servico.
	public static final String MSG_ERRO_ORDEM_SERVICO_NULA = "Erro: a ordem de servico não pode ser nula";
	public static final String MSG_ERRO_SERVICO_MENOR_QUE_1 = "Erro: insira ao menos 1 serviço para continuar";
	public static final String MSG_ERRO_CANCELAR_ORDEM_SERVICO = "Erro: não foi possível cancelar a ordem de serviço";
	
	// Mensagem de sucesso ordem servico.
	public static final String MSG_SUCESSO_CADASTRAR_ORDEM_SERVICO = "Sucesso: a ordem de servico foi cadastrada no sistema!";
	
	// Desconto aplicado ao valor total para agendamentos com três ou mais serviços.
	public static final BigDecimal DESCONTO_SERVICOS = new BigDecimal(10.0);
	
	private Constantes() {}

}
