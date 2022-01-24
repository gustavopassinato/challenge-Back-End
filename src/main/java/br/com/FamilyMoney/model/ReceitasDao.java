package br.com.FamilyMoney.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.FamilyMoney.model.form.AtualizaReceitasForm;
import br.com.FamilyMoney.model.form.ReceitasForm;

@Entity
@Table(name = "receitas")
public class ReceitasDao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricao;
	private double valor;
	private LocalDateTime data;
	
	public ReceitasDao() {
		
	}
	
	public ReceitasDao(ReceitasForm receitaForm) {
		this.descricao = receitaForm.getDescricao();
		this.valor = receitaForm.getValor();
		this.data = receitaForm.getData();
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

	
	

}
