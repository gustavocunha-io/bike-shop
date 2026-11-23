<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="br.gasc.utils.DataUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Relatório de Serviços Prestados</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordem-servico.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/relatorio.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	
	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Relatório de Serviços Prestados</h1>
		</div>
	
		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>
	
		<form action="${pageContext.request.contextPath}/historico/relatorio" method="get" class="barra-filtros">
			<div class="campo-filtro">
				<label for="dataInicio">Data inicial:</label>
				<input type="date" name="dataInicio" id="dataInicio" value="${ dataInicio }" required>
			</div>
			<div class="campo-filtro">
				<label for="dataFim">Data final:</label>
				<input type="date" name="dataFim" id="dataFim" value="${ dataFim }" required>
			</div>
			<button type="submit" class="btn-primario">Gerar relatório</button>
		</form>
	
		<c:if test="${ consultou }">
			<c:choose>
				<c:when test="${ empty ordensList }">
					<div class="mensagem-vazia"><p>Nenhum serviço executado no período informado.</p></div>
				</c:when>
				<c:otherwise>
					<div class="tabela-container">
						<table class="tabela-os">
							<thead>
								<tr>
									<th>Data de Execução</th><th>Cliente</th><th>Bicicleta</th>
									<th>Serviço(s) Realizado(s)</th><th>Valor</th><th>Desconto</th><th>Valor Final</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="ordemServico" items="${ ordensList }">
									<c:set var="desconto" value="${ ordemServico.valorBruto - ordemServico.valorFinal }"/>
									<tr>
										<td>${ DataUtil.dataFormatada(ordemServico.dataExecucao) }</td>
										<td>${ ordemServico.cliente != null ? ordemServico.cliente.nome : 'Sem proprietário' }</td>
										<td>${ ordemServico.bicicleta.marca } ${ ordemServico.bicicleta.modelo }</td>
										<td>
											<c:forEach var="item" items="${ ordemServico.servicosList }" varStatus="loop">
												${ item.servico.tipo }<c:if test="${ !loop.last }">, </c:if>
											</c:forEach>
										</td>
										<td><fmt:formatNumber value="${ ordemServico.valorBruto }" type="currency"/></td>
										<td>
											<c:choose>
												<c:when test="${ desconto > 0 }">- <fmt:formatNumber value="${ desconto }" type="currency"/></c:when>
												<c:otherwise>-</c:otherwise>
											</c:choose>
										</td>
										<td><fmt:formatNumber value="${ ordemServico.valorFinal }" type="currency"/></td>
									</tr>
								</c:forEach>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="6" style="text-align:right;"><strong>Total faturado no período:</strong></td>
									<td><strong><fmt:formatNumber value="${ totalFaturado }" type="currency"/></strong></td>
								</tr>
							</tfoot>
						</table>
					</div>
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
</body>
</html>