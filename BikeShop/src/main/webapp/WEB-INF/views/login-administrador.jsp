<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${ not empty mensagemErro }">
		<div style="color: red; border: 1px solid red; padding: 10px;">
			${ mensagemErro }
		</div>
	</c:if>
	<form action="efetuaLogin" method="post">
		<div>
			<h1>Painel Administrativo</h1>		
		</div>
		<div>
			<label for="login">Login: </label>
			<input id="login" name="login" type="text" required="required" autocomplete="off">
		</div>
		<div>		
			<label for="login">Senha: </label>
			<input id="login" name="senha" type="password" required="required">
		</div>
		
		<div>
			<input type="submit" name="Entrar no Painel Administrativo" value="Entrar">
		</div>
	</form>
</body>
</html>