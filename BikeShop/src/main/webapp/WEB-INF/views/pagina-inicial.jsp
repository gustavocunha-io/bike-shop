<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Painel GASC - Oficina de Bikes</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pagina-inicial.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<div class="conteudo-principal">
		<c:if test="${ not empty mensagem }">
			<div class="alerta-erro">
				${ mensagem }
			</div>
		</c:if>

		<div class="topo-painel">
			<h1>Painel Administrativo</h1>
			<p>Gestão de clientes, bicicletas e ordens de manutenção.</p>
		</div>

		<div class="grid-atalhos">
			<a href="${pageContext.request.contextPath}/cliente/cadastrarCliente" class="card-atalho">
				<h3>+ Novo Cliente</h3>
				<p>Cadastrar ciclista para abertura de revisão ou orçamento.</p>
			</a>
			
			<a href="${pageContext.request.contextPath}/bicicleta/cadastrarBicicleta" class="card-atalho">
				<h3>+ Nova Bicicleta</h3>
				<p>Cadastrar quadro, modelo, aro e marca ao cliente.</p>
			</a>
			
			<a href="${pageContext.request.contextPath}/servico/cadastrarServico" class="card-atalho">
				<h3>+ Novo Serviço</h3>
				<p>Cadastrar tipo, preço dos serviços solicitados.</p>
			</a>
			
			<a href="${pageContext.request.contextPath}/ordemServico/pesquisarCliente" class="card-atalho">
				<h3>+ Nova Ordem de Serviço</h3>
				<p>Cadastrar revisões gerais, sangria de freio e trocas de peças.</p>
			</a>
			
		</div>
	</div>
</body>
</html>