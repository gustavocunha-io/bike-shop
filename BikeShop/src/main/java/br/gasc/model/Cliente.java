package br.gasc.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Cliente {
	@Id
	@SequenceGenerator(name = "id_cliente", sequenceName = "seq_cliente", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_cliente")
	private Long id;
	
	@Column(unique = true, nullable = false, length = 11)
	private String cpf;
	
	@Column(nullable = false, length = 100)
	private String nome;
	
	@Column(unique = true, nullable = false, length = 254)
	private String email;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	@Column(nullable =  true, length = 11)
	private String contato;
	
	/** Defini mappedBy para que o hibernate saiba que a tabela {@link Cliente} não guarda informações físicas sobre as bicicletas*/
	@OneToMany(mappedBy = "proprietario", cascade = CascadeType.ALL)
	private List<Bicicleta> bicicletasList;
	
	@OneToMany(mappedBy = "cliente")
	private List<OrdemServico> ordensServicosList;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public List<Bicicleta> getBicicletasList() {
		return bicicletasList;
	}

	public void setBicicletasList(List<Bicicleta> bicicletasList) {
		this.bicicletasList = bicicletasList;
	}

	public List<OrdemServico> getOrdensServicosList() {
		return ordensServicosList;
	}

	public void setOrdensServicosList(List<OrdemServico> ordensServicosList) {
		this.ordensServicosList = ordensServicosList;
	}

	public String getContato() {
		return contato;
	}

	public void setContato(String contato) {
		this.contato = contato;
	}
	
}