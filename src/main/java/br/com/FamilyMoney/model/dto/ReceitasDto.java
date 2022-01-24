package br.com.FamilyMoney.model.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import br.com.FamilyMoney.model.ReceitasDao;

public class ReceitasDto {
	
	private Long id;
	private String descricao;
	private double valor;
	private LocalDateTime data;
	
	public ReceitasDto(ReceitasDao receitaDao) {
		this.id = receitaDao.getId();
		this.descricao = receitaDao.getDescricao();
		this.valor = receitaDao.getValor();
		this.data = receitaDao.getData();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public static List<ReceitasDto> converte(List<ReceitasDao> receitasDaoList) {

		return receitasDaoList.stream().map(ReceitasDto::new).collect(Collectors.toList());
	}
	
	

}
