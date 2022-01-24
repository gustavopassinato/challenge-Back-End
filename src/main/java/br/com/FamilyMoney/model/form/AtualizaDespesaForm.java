package br.com.FamilyMoney.model.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.FamilyMoney.model.DespesasDao;
import br.com.FamilyMoney.repository.DespesasRepository;

public class AtualizaDespesaForm {
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull @NotEmpty
	private double valor;
	
	@NotNull @NotEmpty
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
	public DespesasDao atualiza(Long id, DespesasRepository despesasRepository) {

		DespesasDao despesaParaAtualizar = despesasRepository.getById(id);
		
		despesaParaAtualizar.setData(this.data);
		despesaParaAtualizar.setDescricao(this.descricao);
		despesaParaAtualizar.setValor(this.valor);
		return despesaParaAtualizar;
	}


}
