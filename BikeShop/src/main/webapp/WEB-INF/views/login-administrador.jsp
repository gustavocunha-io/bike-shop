<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Login - GASC Bike Lab</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/login-administrador.css">
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