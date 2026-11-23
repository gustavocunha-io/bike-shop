<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Cliente - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cadastrar-cliente.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<form action="salvarCliente" method="post">
		<h2 class="form-titulo">Cadastrar Cliente</h2>

		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<div class="campo">
			<label for="nome">Nome*</label>
			<input id="nome" name="nome" type="text" value="${ cliente.nome }" autocomplete="off" required="required">	
		</div>
		<div class="campo">
			<label for="cpf">CPF*</label>
			<input id="cpf" name="cpf" type="text" value="${ cliente.cpf }" autocomplete="off" required="required">
		</div>
		<div class="campo">
			<label for="email">Email*</label>
			<input id="email" name="email" type="email" value="${ cliente.email }" required="required">
		</div>
		<div class="campo">
			<label for="contato">Contato</label>
			<input id="contato" name="contato" type="number" value="${ cliente.contato }">
		</div>
		<div class="campo">
			<label for="data-nascimento">Data de Nascimento*</label>
			<input id="data-nascimento" name="dataNascimento" type="date" value="${ cliente.dataNascimento }">
		</div>
		<div class="acoes">
			<input type="submit" value="Salvar">
			<a href="${pageContext.request.contextPath}/paginaInicial" class="btn-voltar">Voltar</a>
		</div>
	</form>
	
</body>
</html>