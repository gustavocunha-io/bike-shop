<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Pesquisar Cliente (OS) - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/pesquisar-cliente-os.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<form action="${pageContext.request.contextPath}/ordemServico/buscarCliente" method="post">
		<h2 class="form-titulo">Agendar Ordem de Serviço</h2>

		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<div class="campo">
			<label for="cpf">CPF do Cliente:</label>
			<input id="cpf" name="cpf" type="text" value="${ param.cpfCliente }" autocomplete="off" required="required">
			<small id="msg-cliente" style="display: block; margin-top: 6px; font-size: 13px; font-weight: 600;"></small>
			<div class="acoes">
			<input type="submit" value="Buscar">
				<a href="${pageContext.request.contextPath}/paginaInicial" class="btn-voltar">Voltar</a>
			</div>
		</div>
	</form>
</body>