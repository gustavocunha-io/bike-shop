<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registrar Execução - Ordem de Serviço #${ ordemServico.id }</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/ordem-servico.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-4.1.0.js"></script>
</head>
<body>
	<%@ include file="/WEB-INF/views/cabecalho.jsp" %>

	<div class="conteudo-principal">
		<div class="topo-pagina">
			<h1>Registrar Execução da OS #${ ordemServico.id }</h1>
			<a href="${pageContext.request.contextPath}/ordemServico/listarOrdemServico" class="btn-secundario">Voltar para Lista</a>
		</div>

		<c:if test="${ not empty sucesso }">
			<div class="alerta-sucesso">${ sucesso }</div>
		</c:if>
		<c:if test="${ not empty erro }">
			<div class="alerta-erro">${ erro }</div>
		</c:if>

		<div class="card-resumo">
			<h2>Dados da Ordem de Serviço</h2>
			<p>
				<strong>Proprietário (registrado na OS):</strong>
				<c:choose>
					<c:when test="${ ordemServico.cliente != null }">
						${ ordemServico.cliente.nome }
					</c:when>
					<c:otherwise>
						Sem proprietário
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<strong>Bicicleta:</strong>
				${ ordemServico.bicicleta.marca } ${ ordemServico.bicicleta.modelo }
			</p>
			<p>
				<strong>Data de Agendamento:</strong>
				<fmt:formatDate value="${ ordemServico.dataAgendamento }" pattern="dd/MM/yyyy"/>
			</p>
			<p>
				<strong>Status Atual:</strong>
				<span class="badge-status status-${ ordemServico.status }">${ ordemServico.status.descricao }</span>
			</p>
		</div>

		<form id="formExecucao" action="${pageContext.request.contextPath}/ordemServico/confirmarExecucao" method="post" class="formulario-os">
			<input type="hidden" name="idOrdemServico" value="${ ordemServico.id }">

			<div class="campo-formulario">
				<label for="dataExecucao">Data de Execução *:</label>
				<input type="date" id="dataExecucao" name="dataExecucao" value="${ dataExecucao }" required>
			</div>

			<h3>Serviços Efetivamente Realizados</h3>
			<p class="texto-ajuda">
				Marque os serviços concluídos. Os valores exibidos correspondem ao preço vigente para esta execução e não serão alterados por reajustes futuros do catálogo.
			</p>

			<div class="tabela-container">
				<table class="tabela-os">
					<thead>
						<tr>
							<th>Realizado</th>
							<th>Serviço</th>
							<th>Valor Unitário Registrado</th>
						</tr>
					</thead>
					<tbody>
					
						<c:forEach var="item" items="${ ordemServico.servicosList }">
							<tr>
								<td>
									<input type="checkbox"
										class="chk-servico"
										name="servicosSelecionadosIds"
										value="${ item.servico.id }"
										data-valor="${ item.valorUnitario != null ? item.valorUnitario : item.servico.valor }"
										checked>
								</td>
								<td>${ item.servico.tipo }</td>
								<td>
									<c:set var="valorItem" value="${ item.valorUnitario != null ? item.valorUnitario : item.servico.valor }" />
									<fmt:formatNumber value="${ valorItem }" type="currency"/>
									<input type="hidden" name="valoresUnitarios[${ item.servico.id }]" value="${ valorItem }">
								</td>
							</tr>
						</c:forEach>

						<c:if test="${ not empty servicosCatalogo }">
							<c:forEach var="servico" items="${ servicosCatalogo }">
								<tr>
									<td>
										<input type="checkbox"
											class="chk-servico"
											name="servicosSelecionadosIds"
											value="${ servico.id }"
											data-valor="${ servico.valor }">
									</td>
									<td>${ servico.tipo }</td>
									<td>
										<fmt:formatNumber value="${ servico.valor }" type="currency"/>
										<input type="hidden" name="valoresUnitarios[${ servico.id }]" value="${ servico.valor }">
									</td>
								</tr>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>

			<div class="campo-formulario" style="margin-top: 16px;">
				<label>
					<input type="checkbox" id="aplicarDesconto" name="aplicarDesconto" value="true" ${ aplicarDesconto ? 'checked' : '' }>
					Aplicar desconto promocional (quando aplicável)
				</label>
			</div>

			<div class="resumo-valores" style="margin-top: 16px;">
				<p><strong>Subtotal dos Serviços Selecionados:</strong> <span id="subtotalFormatado">R$ 0,00</span></p>
				<p id="linhaDesconto" style="display:none; color:#166534;">
					<strong>Desconto:</strong> - <span id="descontoFormatado">R$ 0,00</span>
				</p>
				<p style="font-size: 18px;">
					<strong>Valor Total Calculado da OS:</strong> <span id="totalFormatado">R$ 0,00</span>
				</p>
			</div>

			<div class="acoes-formulario" style="margin-top: 20px;">
				<button type="submit" class="btn-sucesso">Confirmar Execução</button>
				<a href="${pageContext.request.contextPath}/ordemServico/listarOrdemServico" class="btn-secundario">Cancelar</a>
			</div>
		</form>
	</div>

	<script type="text/javascript">
		function formatarMoedaBR(valor) {
			return "R$ " + Number(valor || 0).toFixed(2).replace(".", ",");
		}

		function recalcularValores() {
			let subtotal = 0;
			let qtdSelecionados = 0;

			$(".chk-servico:checked").each(function () {
				const valor = parseFloat($(this).data("valor")) || 0;
				subtotal += valor;
				qtdSelecionados++;
			});

			let desconto = 0;
			const podeDesconto = $("#aplicarDesconto").is(":checked") && qtdSelecionados >= 3;
			if (podeDesconto) {
				desconto = Number((subtotal * 0.10).toFixed(2));
			}

			const total = Math.max(0, Number((subtotal - desconto).toFixed(2)));

			$("#subtotalFormatado").text(formatarMoedaBR(subtotal));
			if (desconto > 0) {
				$("#descontoFormatado").text(formatarMoedaBR(desconto));
				$("#linhaDesconto").show();
			} else {
				$("#linhaDesconto").hide();
			}
			$("#totalFormatado").text(formatarMoedaBR(total));
		}

		$(document).ready(function () {
			recalcularValores();

			$(document).on("change", ".chk-servico, #aplicarDesconto", function () {
				recalcularValores();
			});

			$("#formExecucao").on("submit", function (e) {
				if ($(".chk-servico:checked").length === 0) {
					e.preventDefault();
					alert("Selecione pelo menos um serviço executado.");
					return false;
				}
				if (!$("#dataExecucao").val()) {
					e.preventDefault();
					alert("Informe a data de execução.");
					return false;
				}
			});
		});
	</script>
</body>
</html>