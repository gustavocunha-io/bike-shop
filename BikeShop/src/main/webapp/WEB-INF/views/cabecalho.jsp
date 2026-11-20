<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<style>
	html, body {
		margin: 0;
		padding: 0;
		width: 100%;
		font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
	}

	.cabecalho-container {
		box-sizing: border-box;
		width: 100%;
		background-color: #18181b; /* Preto grafite (estilo quadro/pneu) */
		border-bottom: 3px solid #f97316; /* Detalhe laranja ciclismo */
		padding: 0 24px;
		height: 60px;
		display: flex;
		align-items: center;
		box-shadow: 0 2px 6px rgba(0, 0, 0, 0.15);
		position: sticky;
		top: 0;
		left: 0;
		z-index: 100;
	}

	.cabecalho-container nav {
		width: 100%;
		display: flex;
		align-items: center;
		gap: 12px;
	}

	.marca-oficina {
		color: #ffffff;
		font-weight: 700;
		font-size: 16px;
		margin-right: 16px;
		letter-spacing: 0.5px;
	}

	.marca-oficina span {
		color: #f97316;
	}

	.cabecalho-container nav span a {
		color: #e4e4e7;
		text-decoration: none;
		font-size: 14px;
		font-weight: 500;
		padding: 8px 14px;
		border-radius: 5px;
		white-space: nowrap;
		transition: background-color 0.2s, color 0.2s;
	}

	.cabecalho-container nav span a:hover {
		background-color: #27272a;
		color: #f97316;
	}

	.cabecalho-container nav span:last-child {
		margin-left: auto;
	}

	.cabecalho-container nav span:last-child a {
		color: #fca5a5;
	}

	.cabecalho-container nav span:last-child a:hover {
		background-color: #dc2626;
		color: #ffffff;
	}
</style>

<div class="cabecalho-container">
	<nav>
		<div class="marca-oficina">GASC <span>Bike Shop</span></div>
		<span><a href="${pageContext.request.contextPath}/paginaInicial">Início</a></span>
		<span><a href="${pageContext.request.contextPath}/cliente/listarClientes">Clientes</a></span>
		<span><a href="${pageContext.request.contextPath}/bicicleta/listarBicicletas">Bicicletas</a></span>
		<span><a href="${pageContext.request.contextPath}/servico/listarServicos">Serviços</a></span>
		<span><a href="${pageContext.request.contextPath}/ordemServico/listarOrdemServico">Ordens de Serviço</a></span>
		<span><a href="${pageContext.request.contextPath}/logoutAdmin">Sair</a></span>
	</nav>
</div>