<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro</title>
</head>
<body>
	<form action="salvarCliente" method="post">
		<div>
			<label for="nome">Nome: </label>
			<input id="nome" name="nome" type="text" autocomplete="off" required="required">	
		</div>
		<div>
			<label for="cpf">CPF:</label>
			<input id="cpf" name="cpf" type="text" autocapitalize="off" required="required">
		</div>
		<div>
			<label for="email">Email:</label>
			<input id="email" name="email" type="email" required="required">
		</div>
		<div>
			<label for="data-nascimento">Data de Nascimento: </label>
			<input id="data-nascimento" name="data-nascimento" type="date">
		</div>
	</form>
</body>
</html>