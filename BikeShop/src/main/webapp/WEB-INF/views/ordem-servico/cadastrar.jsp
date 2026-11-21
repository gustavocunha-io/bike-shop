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
		input[type="number"],
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
		input[type="number"]:focus,
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

		/* Estilo visual para quando o fieldset estiver bloqueado */
		fieldset {
			border: none;
			padding: 0;
			margin: 16px 0 0 0;
			transition: opacity 0.2s;
		}

		fieldset:disabled {
			opacity: 0.5;
		}

		fieldset:disabled input,
		fieldset:disabled select {
			background-color: #f4f4f5;
			cursor: not-allowed;
		}

		.item-checkbox {
			font-weight: normal;
			display: flex;
			align-items: center;
			gap: 8px;
			cursor: pointer;
			margin-bottom: 8px;
		}
	</style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<form action="${pageContext.request.contextPath}/ordemServico/salvarOrdemServico" method="post">
		<h2 class="form-titulo">Agendar Ordem de Serviço</h2>

		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<div class="campo">
			<label for="id-cliente">ID:</label>
			<input id="id-cliente" name="idCliente" type="number" value="${ cliente.id }" autocomplete="off" readonly="readonly">
		</div>

		<div class="campo">
			<label for="nome-cliente">Nome:</label>
			<input id="nome-cliente" name="nomeCliente" type="text" value="${ cliente.nome }" autocomplete="off" readonly="readonly">
		</div>
		
		<div class="campo">
			<label for="cpf-cliente">CPF:</label>
			<input id="cpf-cliente" name="cpfCliente" type="text" value="${ cliente.cpf }" autocomplete="off" readonly="readonly">
		</div>
		
		<div class="campo">
			<label for="bicicleta">Bicicleta</label>
			<select id="bicicleta" name="idBicicleta" required>
				<option value="">Selecionar bicicleta</option>
				<c:forEach var="bicicleta" items="${ bicicletasClienteList }">
					<option value="${ bicicleta.id }">${ bicicleta.marca } - ${ bicicleta.modelo }</option>
				</c:forEach>
			</select>
		</div>

		<div class="campo">
			<label for="data-agendamento">Data do Agendamento</label>
			<input id="data-agendamento" name="dataAgendamento" type="date" value="${ ordemServico.dataAgendamento }" required>
		</div>

		<div class="campo">
			<label>Serviços oferecidos</label>
			<c:forEach var="servico" items="${ servicosList }">
				<label class="item-checkbox">
					<input id="servico.${ servico.id }" class="servicosIDs" name="servicosIDs" value="${ servico.id }" type="checkbox" onchange="verificarDesconto()">
					<span>${ servico.tipo } (R$ ${ servico.valor })</span>
				</label>
			</c:forEach>
		</div>
		
	    <div id="areaDesconto" style="display: none; justify-content: center; align-items: center;">
	        <label style="display: flex; align-items: center; gap: 6px;">
	            Aplicar desconto de 10% <input type="checkbox" id="checarDesconto" name="aplicarDesconto" value="true" /> 
	        </label>
	    </div>

		<div class="acoes" style="margin-top: 24px;">
			<input type="submit" value="Salvar">
			<a href="${pageContext.request.contextPath}/ordemServico/pesquisarCliente" class="btn-voltar">Voltar</a>
		</div>
	</form>
	<script type="text/javascript">
		function verificarDesconto() {
			var quantidadeServicos = $(".servicosIDs:checked").length;
			
			if(quantidadeServicos >= 3)
				$("#areaDesconto").show();
			else {
				$("#areaDesconto").hide();
				$("#checarDesconto").prop("checked", false);
			}
		}
		
		$(document).ready(function() {
			alert("jQuery está funcionando!");
		});
	</script>
</body>
</html>