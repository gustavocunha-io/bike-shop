<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Agendar Ordem de Serviço - GASC Bike Shop</title>
	<style>
		* {
			box-sizing: border-box;
			font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
		}

		body {
			background-color: #f4f4f5;
			display: flex;
			flex-direction: column;
			align-items: center;
			min-height: 100vh;
			margin: 0;
			padding: 0;
		}

		form {
			background-color: #ffffff;
			padding: 32px;
			border-radius: 8px;
			border-top: 4px solid #f97316;
			box-shadow: 0 4px 12px rgba(0, 0, 0, 0.08);
			width: 100%;
			max-width: 440px;
			margin: auto 20px;
		}

		.form-titulo {
			margin: 0 0 22px 0;
			font-size: 20px;
			color: #18181b;
		}

		.alerta-erro {
			color: #991b1b;
			background-color: #fee2e2;
			border: 1px solid #fca5a5;
			padding: 10px 14px;
			border-radius: 5px;
			font-size: 13px;
			margin-bottom: 18px;
		}

		div.campo {
			margin-bottom: 16px;
		}

		.acoes {
			margin-top: 12px;
			display: flex;
			gap: 10px;
		}

		label {
			display: block;
			margin-bottom: 6px;
			font-weight: 600;
			font-size: 14px;
			color: #27272a;
		}

		input[type="text"],
		input[type="email"],
		input[type="date"],
		select {
			width: 100%;
			padding: 10px 12px;
			border: 1px solid #d4d4d8;
			border-radius: 5px;
			font-size: 14px;
			color: #18181b;
			background-color: #ffffff;
			transition: border-color 0.2s, box-shadow 0.2s;
		}

		input[type="text"]:focus,
		input[type="date"]:focus,
		select:focus {
			outline: none;
			border-color: #f97316;
			box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.18);
		}

		input[type="submit"],
		.btn-buscar,
		.btn-voltar {
			flex: 1;
			padding: 10px 16px;
			font-size: 14px;
			font-weight: 600;
			border: none;
			border-radius: 5px;
			cursor: pointer;
			text-align: center;
			text-decoration: none;
			transition: background-color 0.2s;
		}

		input[type="submit"],
		.btn-buscar {
			background-color: #f97316;
			color: #ffffff;
		}

		input[type="submit"]:hover,
		.btn-buscar:hover {
			background-color: #ea580c;
		}

		.btn-voltar {
			background-color: #e4e4e7;
			color: #27272a;
		}

		.btn-voltar:hover {
			background-color: #d4d4d8;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
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