<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Ordem de Serviço - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cadastro-os.css">
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
			<label for="id-cliente">ID</label>
			<input id="id-cliente" name="idCliente" type="number" value="${ cliente.id }" autocomplete="off" readonly="readonly">
		</div>

		<div class="campo">
			<label for="nome-cliente">Nome</label>
			<input id="nome-cliente" name="nomeCliente" type="text" value="${ cliente.nome }" autocomplete="off" readonly="readonly">
		</div>
		
		<div class="campo">
			<label for="cpf-cliente">CPF</label>
			<input id="cpf-cliente" name="cpfCliente" type="text" value="${ cliente.cpf }" autocomplete="off" readonly="readonly">
		</div>
		
		<div class="campo">
			<label for="bicicleta">Bicicleta*</label>
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
			<label>Serviços oferecidos*</label>
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
	</script>
</body>
</html>