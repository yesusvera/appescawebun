package br.org.unesco.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_RESPOSTA", schema="appesca")
public class Resposta implements java.io.Serializable {

	private static final long serialVersionUID = 6100540469341649847L;
	
	private Integer id;
	private Integer opcao;
	private byte[] texto;
	private byte[] audio;
	private int idPergunta;

	public Resposta() {
	}

	public Resposta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

	public Resposta(Integer opcao, byte[] texto, byte[] audio, int idPergunta) {
		this.opcao = opcao;
		this.texto = texto;
		this.audio = audio;
		this.idPergunta = idPergunta;
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
	public byte[] getTexto() {
		return this.texto;
	}

	public void setTexto(byte[] texto) {
		this.texto = texto;
	}

	@Column(name = "audio")
	public byte[] getAudio() {
		return this.audio;
	}

	public void setAudio(byte[] audio) {
		this.audio = audio;
	}

	@Column(name = "id_pergunta", nullable = false)
	public int getIdPergunta() {
		return this.idPergunta;
	}

	public void setIdPergunta(int idPergunta) {
		this.idPergunta = idPergunta;
	}

}
