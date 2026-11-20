<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Clientes - GASC Bike Lab</title>
	<style>
		body {
			background-color: #f4f4f5;
		}

		.conteudo-principal {
			padding: 32px;
			max-width: 1100px;
			margin: 0 auto;
		}

		.topo-pagina {
			display: flex;
			justify-content: space-between;
			align-items: center;
			margin-bottom: 24px;
		}

		.topo-pagina h1 {
			color: #18181b;
			font-size: 24px;
			margin: 0;
		}

		.btn-novo {
			background-color: #f97316;
			color: #ffffff;
			text-decoration: none;
			font-size: 14px;
			font-weight: 600;
			padding: 10px 18px;
			border-radius: 6px;
			transition: background-color 0.2s;
		}

		.btn-novo:hover {
			background-color: #ea580c;
		}

		.alerta-sucesso {
			color: #166534;
			background-color: #dcfce7;
			border: 1px solid #86efac;
			padding: 12px 16px;
			border-radius: 6px;
			margin-bottom: 20px;
			font-size: 14px;
			font-weight: 500;
		}

		.mensagem-vazia {
			background-color: #ffffff;
			padding: 32px;
			text-align: center;
			border-radius: 8px;
			color: #52525b;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
		}

		.tabela-container {
			background-color: #ffffff;
			border-radius: 8px;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
			overflow-x: auto; /* Evita quebrar o layout em telas menores */
		}

		table {
			width: 100%;
			border-collapse: collapse;
			text-align: left;
		}

		thead {
			background-color: #18181b;
			color: #ffffff;
		}

		th {
			padding: 14px 16px;
			font-size: 13px;
			font-weight: 600;
			text-transform: uppercase;
			letter-spacing: 0.5px;
			text-align: center;
		}

		td {
			padding: 14px 16px;
			font-size: 14px;
			color: #27272a;
			border-bottom: 1px solid #e4e4e7;
			text-align: center;
		}

		tbody tr:last-child td {
			border-bottom: none;
		}

		tbody tr:hover {
			background-color: #fafafa;
		}
	</style>
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
									<td>${ bicicleta.proprietario.cpf }</td>
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