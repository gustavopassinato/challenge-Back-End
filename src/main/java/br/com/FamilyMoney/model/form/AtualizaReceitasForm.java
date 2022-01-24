package br.com.FamilyMoney.model.form;

import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.FamilyMoney.model.ReceitasDao;
import br.com.FamilyMoney.repository.ReceitasRepository;

public class AtualizaReceitasForm {
	
	@NotNull @NotEmpty
	private String descricao;
	
	@NotNull @NotEmpty
	private double valor;
	
	@NotNull @NotEmpty
	private LocalDateTime data;
	
	
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
	public ReceitasDao atualiza(Long id, ReceitasRepository receitasRepository) {
		ReceitasDao receitaParaAtualizar = receitasRepository.getById(id);
		
		receitaParaAtualizar.setData(this.data);
		receitaParaAtualizar.setDescricao(this.descricao);
		receitaParaAtualizar.setValor(this.valor);
		
		return receitaParaAtualizar;
	}
	
	

}
