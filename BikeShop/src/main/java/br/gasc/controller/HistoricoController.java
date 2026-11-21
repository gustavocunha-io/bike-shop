package br.gasc.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.gasc.exception.RegraNegocioException;
import br.gasc.model.Cliente;
import br.gasc.model.OrdemServico;
import br.gasc.service.BicicletaService;
import br.gasc.service.ClienteService;
import br.gasc.service.OrdemServicoService;

@Controller
@RequestMapping(value = "/historico")
public class HistoricoController {
	private OrdemServicoService ordemServicoService;

	public HistoricoController() {
		this.ordemServicoService = new OrdemServicoService();
	}

	@RequestMapping(value = "historicoClientes")
	public String historicoCliente(@RequestParam(value = "cpf", required = false) String cpf, Model model) {
		if (cpf == null || cpf.trim().isEmpty())
			return "historico/clientes";

		try {
			Cliente cliente = new ClienteService().pesquisarPorCpf(cpf.trim());

			model.addAttribute("cpf", cpf);

			if (cliente == null) {
				model.addAttribute("erro", "Cliente não encontrado.");
				return "historico/clientes";
			}

			List<OrdemServico> ordensList = ordemServicoService.listarHistoricoPorCliente(cliente.getId());

			model.addAttribute("cliente", cliente);
			model.addAttribute("ordensList", ordensList);
			model.addAttribute("totalGeral", somarValorFinal(ordensList));
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
		}

		return "historico/clientes";
	}

	@RequestMapping(value = "historicoBicicletas")
	public String historicoBicicleta(@RequestParam(value = "idBicicleta", required = false) Long idBicicleta, Model model) {
		// ATENÇÃO: ajuste o nome do método se o seu BicicletaService listar de outro jeito
		model.addAttribute("bicicletasList", new BicicletaService().listaBicicleta());

		if (idBicicleta == null)
			return "historico/bicicletas";

		List<OrdemServico> ordensList = ordemServicoService.listarHistoricoPorBicicleta(idBicicleta);

		model.addAttribute("idBicicleta", idBicicleta);
		model.addAttribute("ordensList", ordensList);
		model.addAttribute("totalGeral", somarValorFinal(ordensList));

		return "historico/bicicletas";
	}

	@RequestMapping(value = "relatorio")
	public String relatorio(
	@RequestParam(value = "dataInicio", required = false) String dataInicioStr,
	@RequestParam(value = "dataFim", required = false) String dataFimStr,
	Model model) {
		if (dataInicioStr == null || dataInicioStr.isEmpty() || dataFimStr == null || dataFimStr.isEmpty())
			return "historico/relatorio";

		model.addAttribute("dataInicio", dataInicioStr);
		model.addAttribute("dataFim", dataFimStr);

		try {
			Date dataInicio = Date.valueOf(dataInicioStr);
			Date dataFim = Date.valueOf(dataFimStr);

			if (dataInicio.after(dataFim)) {
				model.addAttribute("erro", "A data inicial não pode ser maior que a data final.");
				return "historico/relatorio";
			}

			List<OrdemServico> ordensList = ordemServicoService.listarHistoricoPorPeriodo(dataInicio, dataFim);

			model.addAttribute("ordensList", ordensList);
			model.addAttribute("totalFaturado", somarValorFinal(ordensList));
			model.addAttribute("consultou", true);
			
		} catch (RegraNegocioException regraNegocioException) {
			model.addAttribute("erro", regraNegocioException.getMessage());
		}

		return "historico/relatorio";
	}

	private BigDecimal somarValorFinal(List<OrdemServico> ordensList) {
		BigDecimal total = BigDecimal.ZERO;

		for (OrdemServico ordemServico : ordensList)
			if (ordemServico.getValorFinal() != null)
				total = total.add(ordemServico.getValorFinal());

		return total;
	}
}