<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login - GASC Bike Lab</title>
	<style>
		* {
			box-sizing: border-box;
			font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, Helvetica, Arial, sans-serif;
		}

		body {
			background-color: #18181b;
			display: flex;
			justify-content: center;
			align-items: center;
			min-height: 100vh;
			margin: 0;
			padding: 20px;
		}

		form {
			background-color: #ffffff;
			padding: 36px 32px;
			border-radius: 8px;
			border-top: 4px solid #f97316;
			box-shadow: 0 10px 25px rgba(0, 0, 0, 0.35);
			width: 100%;
			max-width: 380px;
		}

		.marca-topo {
			text-align: center;
			margin-bottom: 24px;
		}

		.marca-topo span {
			display: inline-block;
			font-size: 13px;
			font-weight: 700;
			text-transform: uppercase;
			letter-spacing: 1px;
			color: #f97316;
			margin-bottom: 4px;
		}

		.marca-topo h1 {
			margin: 0;
			font-size: 22px;
			color: #18181b;
		}

		.alerta-erro {
			color: #991b1b;
			background-color: #fee2e2;
			border: 1px solid #fca5a5;
			padding: 10px 14px;
			border-radius: 5px;
			font-size: 13px;
			margin-bottom: 18px;
			text-align: center;
		}

		form > div.campo {
			margin-bottom: 16px;
		}

		label {
			display: block;
			margin-bottom: 6px;
			font-weight: 600;
			font-size: 14px;
			color: #27272a;
		}

		input[type="text"],
		input[type="password"] {
			width: 100%;
			padding: 10px 12px;
			border: 1px solid #d4d4d8;
			border-radius: 5px;
			font-size: 14px;
			color: #18181b;
			transition: border-color 0.2s, box-shadow 0.2s;
		}

		input[type="text"]:focus,
		input[type="password"]:focus {
			outline: none;
			border-color: #f97316;
			box-shadow: 0 0 0 3px rgba(249, 115, 22, 0.18);
		}

		input[type="submit"] {
			width: 100%;
			margin-top: 8px;
			padding: 11px 16px;
			background-color: #f97316;
			color: #ffffff;
			font-size: 15px;
			font-weight: 600;
			border: none;
			border-radius: 5px;
			cursor: pointer;
			transition: background-color 0.2s;
		}

		input[type="submit"]:hover {
			background-color: #ea580c;
		}
	</style>
</head>
<body>
	<form action="efetuaLogin" method="post">
		<div class="marca-topo">
			<span>GASC Bike Shop</span>
			<h1>Painel Administrativo</h1>
		</div>

		<c:if test="${ not empty mensagemErro }">
			<div class="alerta-erro">
				${ mensagemErro }
			</div>
		</c:if>

		<div class="campo">
			<label for="login">Login:</label>
			<input id="login" name="login" type="text" required="required" autocomplete="off">
		</div>

		<div class="campo">
			<label for="senha">Senha:</label>
			<input id="senha" name="senha" type="password" required="required">
		</div>

		<div>
			<input type="submit" value="Entrar">
		</div>
	</form>
</body>
</html>