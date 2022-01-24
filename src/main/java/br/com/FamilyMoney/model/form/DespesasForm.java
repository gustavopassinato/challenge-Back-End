package br.com.FamilyMoney.model.form;

import java.time.LocalDateTime;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


public class DespesasForm {
	
	@NotNull @NotEmpty 
	private String descricao;
	
	@NotNull  @Min(value = 0)
	private double valor;
	
	@NotNull 
	private LocalDateTime data;
	
	
	public void setDescicao(String descricao) {
		this.descricao = descricao;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	public String getDescricao() {
		return descricao;
	}
	public double getValor() {
		return valor;
	}
	public LocalDateTime getData() {
		return data;
	}
}
