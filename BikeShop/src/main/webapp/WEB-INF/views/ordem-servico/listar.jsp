<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="br.gasc.utils.DataUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Ordens de Serviços - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordem-servico.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Ordens de Serviços</h1>
			<a href="${pageContext.request.contextPath}/ordemServico/cadastrarOrdemServico" class="btn-novo">+ Nova Ordem de Serviço</a>
		</div>

		<c:if test="${ not empty sucesso }">
			<div class="alerta-sucesso">${ sucesso }</div>
		</c:if>
		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/ordemServico/listarOrdensServicos" method="post" class="barra-filtros">
			<div class="campo-filtro">
				<label for="status">Status:</label>
				<select name="status" id="status">
					<option value="TODOS" ${ statusFiltro == 'TODOS' ? 'selected' : '' }>Todos os Status</option>
					<option value="AGENDADA" ${ statusFiltro == 'AGENDADA' ? 'selected' : '' }>Agendada</option>
					<option value="EXECUTADA" ${ statusFiltro == 'EXECUTADA' ? 'selected' : '' }>Executada</option>
					<option value="PAGA" ${ statusFiltro == 'PAGA' ? 'selected' : '' }>Paga</option>
					<option value="CANCELADA" ${ statusFiltro == 'CANCELADA' ? 'selected' : '' }>Cancelada</option>
				</select>
			</div>

			<div class="campo-filtro">
				<label for="dataInicio">A partir da data:</label>
				<input type="date" name="dataInicio" id="dataInicio" value="${ dataInicio }">
			</div>

			<div class="campo-filtro">
				<label for="dataExata">Ou data específica:</label>
				<input type="date" name="dataExata" id="dataExata" value="${ dataExata }">
			</div>

			<button type="submit" class="btn-primario">Filtrar</button>
			<a href="${pageContext.request.contextPath}/ordemServico/listarOrdemServico" class="btn-secundario">Limpar</a>
		</form>

		<c:choose>
			<c:when test="${ empty ordensServicosList }">
				<div class="mensagem-vazia">
					<p>Nenhuma ordem de serviço encontrada para os filtros selecionados!</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tabela-container">
					<table class="tabela-os">
						<thead>
							<tr>
								<th>Data Agendamento</th>
								<th>Cliente</th>
								<th>Bicicleta</th>
								<th>Serviços Previstos</th>
								<th>Valor</th>
								<th>Status</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="ordemServico" items="${ ordensServicosList }">
								<tr id="ordem_${ ordemServico.id }">
									<td>${ DataUtil.dataFormatada(ordemServico.dataAgendamento) }</td>
									<td>${ ordemServico.cliente != null ? ordemServico.cliente.nome : 'Sem proprietário' }</td>
									<td>${ ordemServico.bicicleta.marca } ${ ordemServico.bicicleta.modelo }</td>
									<td>
										<c:forEach var="item" items="${ ordemServico.servicosList }" varStatus="loop">
											${ item.servico.tipo }<c:if test="${ !loop.last }">, </c:if>
										</c:forEach>
									</td>
									<td><fmt:formatNumber value="${ ordemServico.valorFinal }" type="currency"/></td>
									<td id="status_${ ordemServico.id }">
										<span class="badge-status status-${ ordemServico.status }">${ ordemServico.status.descricao }</span>
									</td>
									<td id="acao_${ ordemServico.id }">
										<c:if test="${ ordemServico.status == 'AGENDADA' }">
											<form action="${pageContext.request.contextPath}/ordemServico/executarOrdemServico" method="post" style="display:inline;">
												<input type="hidden" name="id" value="${ ordemServico.id }">
												<button type="submit" class="btn-sucesso">Executar</button>
											</form>
											<button type="button" class="btn-perigo" onclick="cancelarOrdemServico(${ ordemServico.id });">Cancelar</button>
										</c:if>
										<c:if test="${ ordemServico.status == 'EXECUTADA' }">
											<form action="${pageContext.request.contextPath}/ordemServico/pagar" method="post" style="display:inline;">
												<input type="hidden" name="id" value="${ ordemServico.id }">
												<button type="submit" class="btn-primario">Pagar</button>
											</form>
										</c:if>
										<button type="button" class="btn-info" onclick="abrirDetalhesOrdem(${ ordemServico.id });">Detalhes</button>
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
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
		
		function cancelarOrdemServico(id) {
			$.post("${pageContext.request.contextPath}/ordemServico/cancelarOrdemServicoAjax", { 'id': id }, function () {
				$("#status_" + id).html('<span class="badge-status status-CANCELADA">Cancelada</span>');
				$("#acao_" + id).html('<button type="button" class="btn-info" onclick="abrirDetalhesOrdem(' + id + ');">Detalhes</button>');
			});
		}
	</script>
</body>
</html>