<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:choose>
	<c:when test="${ empty ordemServico }">
		<p>Ordem de serviço não encontrada.</p>
	</c:when>
	<c:otherwise>
		<div style="margin-bottom: 12px; line-height: 1.6;">
			<p>
				<strong>Proprietário:</strong>
				${ ordemServico.cliente != null ? ordemServico.cliente.nome : 'Sem proprietário' }
			</p>
			<p>
				<strong>Bicicleta:</strong>
				<c:choose>
					<c:when test="${ ordemServico.bicicleta != null }">
						${ ordemServico.bicicleta.marca } ${ ordemServico.bicicleta.modelo }
					</c:when>
					<c:otherwise>
						Não informada
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<strong>Status:</strong>
				${ ordemServico.status != null ? ordemServico.status.descricao : '' }
			</p>
			<p>
				<strong>Data de Agendamento:</strong>
				<c:choose>
					<c:when test="${ ordemServico.dataAgendamento != null }">
						<fmt:formatDate value="${ ordemServico.dataAgendamento }" pattern="dd/MM/yyyy"/>
					</c:when>
					<c:otherwise>
						Não informada
					</c:otherwise>
				</c:choose>
			</p>
			<p>
				<strong>Data de Execução:</strong>
				<c:choose>
					<c:when test="${ ordemServico.dataExecucao != null }">
						<fmt:formatDate value="${ ordemServico.dataExecucao }" pattern="dd/MM/yyyy"/>
					</c:when>
					<c:otherwise>
						Não informada
					</c:otherwise>
				</c:choose>
			</p>
		</div>

		<c:choose>
			<c:when test="${ not empty ordemServico.servicosList }">
				<table class="tabela-compacta">
					<thead>
						<tr>
							<th>Serviço Realizado</th>
							<th>Valor na Época</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="item" items="${ ordemServico.servicosList }">
							<tr>
								<td>${ item.servico != null ? item.servico.tipo : 'Serviço' }</td>
								<td><fmt:formatNumber value="${ item.valorUnitario }" type="currency"/></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</c:when>
			<c:otherwise>
				<p>Nenhum serviço registrado nesta ordem.</p>
			</c:otherwise>
		</c:choose>

		<c:set var="desconto" value="${ ordemServico.valorBruto - ordemServico.valorFinal }" />
		<c:if test="${ desconto > 0 }">
			<p style="color:#166534; margin-top:12px;">
				<strong>Desconto aplicado:</strong> - <fmt:formatNumber value="${ desconto }" type="currency"/>
			</p>
		</c:if>

		<p style="text-align:right; font-size:16px; margin-top:12px;">
			<strong>Valor Total: <fmt:formatNumber value="${ ordemServico.valorFinal }" type="currency"/></strong>
		</p>
	</c:otherwise>
</c:choose>