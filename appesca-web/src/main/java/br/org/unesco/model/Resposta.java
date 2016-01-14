package br.org.unesco.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TB_RESPOSTA", schema="appesca")
public class Resposta implements java.io.Serializable {

	private static final long serialVersionUID = 6100540469341649847L;
	
	private Integer id;
	private Integer opcao;
	private String texto;
	private byte[] audio;
	private Pergunta pergunta;

	public Resposta() {
	}


	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "opcao")
	public Integer getOpcao() {
		return this.opcao;
	}

	public void setOpcao(Integer opcao) {
		this.opcao = opcao;
	}

	@Column(name = "texto")
	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Column(name = "audio")
	public byte[] getAudio() {
		return this.audio;
	}

	public void setAudio(byte[] audio) {
		this.audio = audio;
	}

	@ManyToOne
	@JoinColumn(name="id_pergunta")
	public Pergunta getPergunta() {
		return pergunta;
	}


	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
	
	@Transient
	public String[] getColuna(){
		return this.texto.split(";");
	}



}
