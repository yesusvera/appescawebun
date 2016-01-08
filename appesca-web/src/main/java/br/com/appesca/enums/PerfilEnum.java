package br.com.appesca.enums;

public enum PerfilEnum {

	ADMINISTRADOR(0, "Administrador"), 
	COORDENADOR(1, "Coordenador"), 
	DEGRAVADOR(2, "Degravador"), 
	PESQUISADOR(3, "Pesquisador");
	
	private final int valor;
	private final String desc;
	
	private PerfilEnum(int valor, String desc) {
		this.valor = valor;
		this.desc = desc;
	}
	
	public int getValor(){
		return valor;
	}
	
	public String getDescricao(){
		return desc;
	}
}
