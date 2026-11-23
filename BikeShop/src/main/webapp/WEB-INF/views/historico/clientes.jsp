<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="br.gasc.utils.DataUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Histórico por Cliente</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordem-servico.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/historico-cliente.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Histórico por Cliente</h1>
		</div>

		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/historico/historicoClientes" method="get" class="barra-filtros">
			<div class="campo-filtro">
				<label for="cpf">CPF do cliente:</label>
				<input type="text" name="cpf" id="cpf" value="${ cpf }" required>
			</div>
			<button type="submit" class="btn-primario">Buscar</button>
		</form>

		<c:if test="${ not empty cliente }">
			<h2>Cliente: ${ cliente.nome }</h2>

			<c:choose>
				<c:when test="${ empty ordensList }">
					<div class="mensagem-vazia">
						<p>Nenhuma ordem de serviço executada para este cliente.</p>
					</div>
				</c:when>
				<c:otherwise>
					<div class="tabela-container">
						<table class="tabela-os">
							<thead>
								<tr>
									<th>Data de Execução</th>
									<th>Bicicleta</th>
									<th>Valor Total</th>
									<th>Ações</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ordemServico" items="${ ordensList }">
									<tr>
										<td>${ DataUtil.dataFormatada(ordemServico.dataExecucao) }</td>
										<td>${ ordemServico.bicicleta.marca } ${ ordemServico.bicicleta.modelo }</td>
										<td><fmt:formatNumber value="${ ordemServico.valorFinal }" type="currency"/></td>
										<td>
											<button type="button" class="btn-info" onclick="abrirDetalhesOrdem(${ ordemServico.id });">Detalhes da ordem</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					<p style="text-align:right; margin-top:12px;"><strong>Total gasto: <fmt:formatNumber value="${ totalGeral }" type="currency"/></strong></p>
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