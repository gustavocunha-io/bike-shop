<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Histórico por Bicicleta</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/historico-bicicleta.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordem-servico.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	<div class="conteudo-principal">
		<h1>Histórico por Bicicleta</h1>
		<form action="${pageContext.request.contextPath}/historico/historicoBicicletas" method="get" class="barra-filtros">
			<div class="campo-filtro">
				<label for="idBicicleta">Bicicleta:</label>
				<select name="idBicicleta" id="idBicicleta" required>
					<option value="">Selecione...</option>
					<c:forEach var="bicicleta" items="${ bicicletasList }">
						<option value="${ bicicleta.id }" ${ idBicicleta == bicicleta.id ? 'selected' : '' }>${ bicicleta.marca } ${ bicicleta.modelo }</option>
					</c:forEach>
				</select>
			</div>
			<button type="submit" class="btn-primario">Buscar</button>
		</form>
		
		<c:if test="${ not empty idBicicleta }">
			<c:choose>
				<c:when test="${ empty ordensList }">
					<div class="mensagem-vazia"><p>Nenhuma ordem de serviço executada para esta bicicleta.</p></div>
				</c:when>
				<c:otherwise>
					<div class="tabela-container">
						<table class="tabela-os">
							<thead>
								<tr><th>Data de Execução</th><th>Proprietário</th><th>Valor Total</th><th>Ações</th></tr>
							</thead>
							<tbody>
								<c:forEach var="ordemServico" items="${ ordensList }">
									<tr>
										<td><fmt:formatDate value="${ ordemServico.dataExecucao }" pattern="dd/MM/yyyy"/></td>
										<td>${ ordemServico.cliente != null ? ordemServico.cliente.nome : 'Sem proprietário' }</td>
										<td><fmt:formatNumber value="${ ordemServico.valorFinal }" type="currency"/></td>
										<td><button type="button" class="btn-info" onclick="abrirDetalhesOrdem(${ ordemServico.id });">Detalhes da ordem</button></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<p style="text-align:right; margin-top:12px;"><strong>Total: <fmt:formatNumber value="${ totalGeral }" type="currency"/></strong></p>
				</c:otherwise>
			</c:choose>
		</c:if>
	</div>
	
	<div id="modalDetalhes" class="modal-fundo">
		<div class="modal-conteudo">
			<h2>Detalhes da Ordem de Serviço #<span id="detalheOsId"></span></h2>
			<div id="detalheOsCorpo">Carregando...</div>
			<div style="text-align: right; margin-top: 16px;">
				<button type="button" class="btn-secundario" onclick="$('#modalDetalhes').fadeOut(150);">Fechar</button>
			</div>
		</div>
	</div>

	<script type="text/javascript">
		function abrirDetalhesOrdem(id) {
			$("#detalheOsId").text(id);
			$("#detalheOsCorpo").html("<p>Carregando serviços...</p>");
			$("#modalDetalhes").css("display", "flex");

			$.post("${pageContext.request.contextPath}/ordemServico/detalhesAjax", { 'id': id }, function (html) {
				$("#detalheOsCorpo").html(html);
			}, "html").fail(function (xhr) {
				$("#detalheOsCorpo").html("<p>Falha na requisição AJAX (HTTP " + xhr.status + ").</p>");
			});
		}
	</script>
</body>
</html>