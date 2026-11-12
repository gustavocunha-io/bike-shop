<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="efetuaLogin" method="post">
		<div>
			<h1>Painel Administrativo</h1>		
		</div>
		<div>
			<label for="login">Login: </label>
			<input id="login" name="login" type="text" required="required">		
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