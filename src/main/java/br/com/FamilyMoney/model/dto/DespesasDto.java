package br.com.FamilyMoney.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.FamilyMoney.model.DespesasDao;

public class DespesasDto {
	
	private Long id;
	private String descicao;
	private double valor;
	private LocalDateTime data;

	
	public DespesasDto(DespesasDao despesaDao) {
		this.id = despesaDao.getId();
		this.descicao = despesaDao.getDescricao();
		this.valor = despesaDao.getValor();
		this.data = despesaDao.getData();
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setDescicao(String descicao) {
		this.descicao = descicao;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescicao() {
		return descicao;
	}
	public double getValor() {
		return valor;
	}
	public LocalDateTime getData() {
		return data;
	}

	public static List<DespesasDto> converte(List<DespesasDao> listaDeDespesasDao) {
		
		return listaDeDespesasDao.stream().map(DespesasDto::new).collect(Collectors.toList()); 
	}
}
