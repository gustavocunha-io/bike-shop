<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Lista de Servicos</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/listar-servico.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>
	
	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Clientes Cadastrados</h1>
			<a href="${pageContext.request.contextPath}/servico/cadastrarServico" class="btn-novo">+ Novo Servico</a>
		</div>
		<c:if test="${ not empty sucesso }">
			<div class="alerta-sucesso">
				${ sucesso }
			</div>
		</c:if>
		
		<c:choose>
			<c:when test="${ empty servicosList }">
				<div class="mensagem-vazia">
					<p>Nenhum serviço cadastrado até o momento!</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tabela-container">
					<table>
						<thead>
							<tr>
								<th>Nome</th>
								<th>Valor</th>
								<th>Ações</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="servico" items="${ servicosList }">
								<tr>
									<td>${ servico.tipo }</td>
									<td id="valor_${ servico.id }"><fmt:formatNumber value="${ servico.valor }" type="currency"></fmt:formatNumber></td>
									<td id="alterar_${ servico.id }"><a href="#" onclick="alterarPreco(${ servico.id }, ${ servico.valor }); return false;">Alterar Preço</a></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>		
	</div>
	<script type="text/javascript">
		function alterarPreco(id) {
			var textoAtual = $("#valor_" + id).text();
			var valorLimpo = textoAtual.replace("R$", "").replace(/\./g, "").replace(",", ".").trim();
			var input_preco = '<input id="novo-valor-' + id + '" step="0.01" class="input-preco" type="number" value="' + valorLimpo + '" style="width: 100px;">';
			
			$("#valor_" + id).html(input_preco);
			$("#novo-valor-" + id).focus();
	
			var link_salvar = '<a href="#" onclick="salvarPreco(' + id + '); return false;">Salvar</a>';
			
			$("#alterar_" + id).html(link_salvar);
		}
			
		function salvarPreco(id) {
			var novo_valor = $("#novo-valor-" + id).val();
	
			$.post("${pageContext.request.contextPath}/servico/alterarPrecoAjax", {
				'id': id,
				'valor': novo_valor
			}, function(valor_formatado) {
				$("#valor_" + id).html(valor_formatado);
	
				var link_alterar = '<a href="#" onclick="alterarPreco(' + id + '); return false;">Alterar Preço</a>';
				
				$("#alterar_" + id).html(link_alterar);
			}).fail(function() {
				alert("Erro ao atualizar o preço.");
			});
		}
	</script>
</body>
</html>