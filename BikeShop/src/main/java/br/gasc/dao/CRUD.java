package br.gasc.dao;

import java.util.List;

public interface CRUD<T> {
	void inserir(T t);
	void alterar(T t);
	void remover(T t);
	T pesquisar(Long id);
	List<T> listar();
}
