<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro Servico - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cadastrar-servico.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	
	<form action="salvarServico" method="post">
		<h2 class="form-titulo">Cadastrar Serviço</h2>
		
		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>
		
		<div class="campo">
			<label for="tipo">Tipo*</label>
			<input id="tipo" name="tipo" type="text">
		</div>
		<div class="campo">
			<label for="valor">Valor (R$)*</label>
			<input id="valor" name="valor" type="number" step="0.01">
		</div>
		<div class="campo">
			<label for="descricao">Descricao (opcional)</label>
			<textarea rows="4" cols="58" name="descricao"></textarea>
		</div>
		<div class="acoes">
			<input type="submit" value="Salvar">
			<a href="${pageContext.request.contextPath}/paginaInicial" class="btn-voltar">Voltar</a>
		</div>
	</form>
</body>
</html>