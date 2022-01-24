package br.com.FamilyMoney.model.form;

import java.time.LocalDateTime;


public class DespesasForm {
	
	private String descricao;
	private double valor;
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
