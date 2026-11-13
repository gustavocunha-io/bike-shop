<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inicio</title>
</head>
<body>
	<div>
		<nav>
			<span><a href="${pageContext.request.contextPath}/logoutAdmin">sair</a></span>
			<span><a href="${pageContext.request.contextPath}/cliente/cadastrarCliente">Cadastrar Cliente</a></span>
		</nav>
	</div>
	<c:if test="${ not empty mensagem }">
		<div style="color: red; border: 1px solid red; padding: 10px;">
			${ mensagem }
		</div>
	</c:if>
	<div>
		<h1>Teste Painel Administrativo</h1>	
	</div>
</body>
</html>