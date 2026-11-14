<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Painel - Oficina de Bikes</title>
	<style>
		body {
			background-color: #f4f4f5;
		}

		.conteudo-principal {
			padding: 32px;
			max-width: 1100px;
			margin: 0 auto;
		}

		.topo-painel {
			margin-bottom: 24px;
		}

		.topo-painel h1 {
			color: #18181b;
			font-size: 24px;
			margin: 0 0 6px 0;
		}

		.topo-painel p {
			color: #52525b;
			margin: 0;
			font-size: 14px;
		}

		.alerta-erro {
			color: #991b1b;
			background-color: #fee2e2;
			border: 1px solid #fca5a5;
			padding: 12px 16px;
			border-radius: 6px;
			margin-bottom: 20px;
			font-size: 14px;
		}

		.grid-atalhos {
			display: grid;
			grid-template-columns: repeat(auto-fit, minmax(240px, 1fr));
			gap: 20px;
		}

		.card-atalho {
			background-color: #ffffff;
			padding: 24px;
			border-radius: 8px;
			border-left: 4px solid #f97316;
			box-shadow: 0 2px 8px rgba(0, 0, 0, 0.06);
			text-decoration: none;
			color: inherit;
			transition: transform 0.15s, box-shadow 0.15s;
		}

		.card-atalho:hover {
			transform: translateY(-2px);
			box-shadow: 0 6px 14px rgba(0, 0, 0, 0.1);
		}

		.card-atalho h3 {
			margin: 0 0 8px 0;
			color: #18181b;
			font-size: 17px;
		}

		.card-atalho p {
			margin: 0;
			color: #71717a;
			font-size: 13px;
			line-height: 1.4;
		}
	</style>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<div class="conteudo-principal">
		<c:if test="${ not empty mensagem }">
			<div class="alerta-erro">
				${ mensagem }
			</div>
		</c:if>

		<div class="topo-painel">
			<h1>Painel Administrativo</h1>
			<p>Gestão de clientes, bicicletas e ordens de manutenção.</p>
		</div>

		<div class="grid-atalhos">
			<a href="${pageContext.request.contextPath}/cliente/cadastrarCliente" class="card-atalho">
				<h3>+ Novo Cliente</h3>
				<p>Cadastrar ciclista para abertura de revisão ou orçamento.</p>
			</a>

			<a href="${pageContext.request.contextPath}/cliente/listarClientes" class="card-atalho">
				<h3>Clientes Cadastrados</h3>
				<p>Consultar dados de contato e histórico de clientes.</p>
			</a>

			<%-- Exemplos de próximos módulos da sua oficina --%>
			<a href="#" class="card-atalho">
				<h3>Bicicletas</h3>
				<p>Vincular quadro, modelo, aro e marca ao cliente.</p>
			</a>

			<a href="#" class="card-atalho">
				<h3>Ordens de Serviço</h3>
				<p>Acompanhar revisões gerais, sangria de freio e trocas de peças.</p>
			</a>
		</div>
	</div>
</body>
</html>