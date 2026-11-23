<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="br.gasc.utils.CpfUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Bicicletas - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/listar-bicicleta.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Bicicletas Cadastradas</h1>
			<a href="${pageContext.request.contextPath}/bicicleta/cadastrarBicicleta" class="btn-novo">+ Nova Bicicleta</a>
		</div>

		<c:if test="${ not empty sucesso }">
			<div class="alerta-sucesso">
				${ sucesso }
			</div>
		</c:if>
		<c:choose>
			<c:when test="${ empty bicicletasList }">
				<div class="mensagem-vazia">
					<p>Nenhum bicicleta cadastrada até o momento!</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tabela-container">
					<table>
						<thead>
							<tr>
								<th>Marca</th>
								<th>Modelo</th>
								<th>Tipo Bicicleta</th>
								<th>Tamanho Quadro</th>
								<th>Numero de Serie</th>
								<th>Ano de Fabricação</th>
								<th>Proprietario</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="bicicleta" items="${ bicicletasList }">
								<tr>
									<td>${ bicicleta.marca }</td>
									<td>${ bicicleta.modelo }</td>
									<td>${ bicicleta.tipoBicicleta.nome }</td>
									<td>${ bicicleta.tamanhoQuadro }</td>
									<td>${ bicicleta.numeroSerie }</td>
									<td>${ bicicleta.anoFabricacao }</td>
									<td>${ CpfUtil.cpfFormatado(bicicleta.proprietario.cpf) }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>