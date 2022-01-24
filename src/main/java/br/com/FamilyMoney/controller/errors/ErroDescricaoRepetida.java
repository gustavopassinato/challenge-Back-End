package br.com.FamilyMoney.controller.errors;

public class ErroDescricaoRepetida {
	
	private String message;
	private Tipo tipoRegistro;
	

	public ErroDescricaoRepetida(Tipo tipo) {
		this.tipoRegistro = tipo;
		this.message = "[ERRO] - Não é permitido adicionar mais de uma "+ tipo.toString().toLowerCase() +" com mesma descrição no mesmo mês";
	}
	
	
	
	public Tipo getTipoRegistro() {
		return tipoRegistro;
	}



	public void setTipoRegistro(Tipo tipoRegistro) {
		this.tipoRegistro = tipoRegistro;
	}



	public void setMessage(String message) {
		this.message = message;
	}

	
	public String getMessage() {
		return message;
	}
}
