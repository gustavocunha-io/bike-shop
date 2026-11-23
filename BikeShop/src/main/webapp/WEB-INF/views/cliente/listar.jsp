<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="br.gasc.utils.DataUtil" %>
<%@ page import="br.gasc.utils.CpfUtil" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Clientes - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/listar-cliente.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Clientes Cadastrados</h1>
			<a href="${pageContext.request.contextPath}/cliente/cadastrarCliente" class="btn-novo">+ Novo Cliente</a>
		</div>

		<c:if test="${ not empty sucesso }">
			<div class="alerta-sucesso">
				${ sucesso }
			</div>
		</c:if>

		<c:choose>
			<c:when test="${ empty clientesList }">
				<div class="mensagem-vazia">
					<p>Nenhum cliente cadastrado até o momento!</p>
				</div>
			</c:when>
			<c:otherwise>
				<div class="tabela-container">
					<table>
						<thead>
							<tr>
								<th>Nome</th>
								<th>CPF</th>
								<th>Email</th>
								<th>Contato</th>
								<th>Data de Nascimento</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="cliente" items="${ clientesList }">
								<tr>
									<td>${ cliente.nome }</td>
									<td>${ CpfUtil.cpfFormatado(cliente.cpf) }</td>
									<td>${ cliente.email }</td>
									<td>${ cliente.contato }</td>
									<td>${ DataUtil.dataFormatada(cliente.dataNascimento) }</td>
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