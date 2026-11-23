<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<head>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cabecalho.css">
</head>

<div class="cabecalho-container">
<nav>
	<div class="marca-oficina">GASC <span>Bike Shop</span></div>
	<span><a href="${pageContext.request.contextPath}/paginaInicial">Início</a></span>
	<span><a href="${pageContext.request.contextPath}/cliente/listarClientes">Clientes</a></span>
	<span><a href="${pageContext.request.contextPath}/bicicleta/listarBicicletas">Bicicletas</a></span>
	<span><a href="${pageContext.request.contextPath}/servico/listarServicos">Serviços</a></span>
	<span><a href="${pageContext.request.contextPath}/ordemServico/listarOrdemServico">Ordens de Serviço</a></span>

	<span class="menu-dropdown">
		<a href="#" onclick="return false;">Histórico ▾</a>
		<div class="menu-dropdown-itens">
			<a href="${pageContext.request.contextPath}/historico/historicoClientes">Por Cliente</a>
			<a href="${pageContext.request.contextPath}/historico/historicoBicicletas">Por Bicicleta</a>
		</div>
	</span>
	<span><a href="${pageContext.request.contextPath}/historico/relatorio">Relatório</a></span>

	<span><a href="${pageContext.request.contextPath}/logoutAdmin">Sair</a></span>
</nav>
</div>