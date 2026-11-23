<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Cadastro de Bicicletas - GASC Bike</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/cadastrar-bicicleta.css">
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<form action="salvarBicicleta" method="post">
		<h2 class="form-titulo">Cadastrar Bicicleta</h2>

		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<div class="campo">
			<label for="marca">Marca*</label>
			<input id="marca" name="marca" type="text" value="${ bicicleta.marca }" autocomplete="off" required="required">	
		</div>
		<div class="campo">
			<label for="modelo">Modelo*</label>
			<input id="modelo" name="modelo" type="text" value="${ bicicleta.modelo }" autocomplete="off" required="required">
		</div>
		<div class="campo">
			<label>Tipo Bicicleta</label>
			<select name="tipoBicicleta.id">
				<option value="">selecionar</option>
				<c:forEach var="tipoBicicleta" items="${ tipoBicicletasList }">				
					<option value="${ tipoBicicleta.id }" ${ bicicleta.tipoBicicleta.id == tipoBicicleta.id ? 'selected' : '' }>
						${ tipoBicicleta.nome }
					</option>
				</c:forEach>
			</select>
		</div>
		<div class="campo">
			<label for="tamanho-quadro">Tamanho Quadro*</label>
			<input id="tamanho-quadro" name="tamanhoQuadro" type="number" value="${ bicicleta.tamanhoQuadro }" required="required">
		</div>
		<div class="campo">
			<label for="numero-serie">Numero de Série</label>
			<input id="numero-serie" name="numeroSerie" type="text" value="${ bicicleta.numeroSerie }" autocomplete="off">
		</div>
		<div class="campo">
			<label for="ano-fabricacao">Ano de Fabricação</label>
			<input id="ano-fabricacao" name="anoFabricacao" type="number" value="${ bicicleta.anoFabricacao }" autocomplete="off">
		</div>
		<div class="campo">
			<label for="cpf-proprietario">CPF do Proprietário</label>
			<input id="cpf-proprietario" name="cpfProprietario" type="text" value="${ bicicleta.proprietario.cpf }">
		</div>
		<div class="acoes">
			<input type="submit" value="Salvar">
			<a href="${pageContext.request.contextPath}/paginaInicial" class="btn-voltar">Voltar</a>
		</div>
	</form>
	
</body>
</html>