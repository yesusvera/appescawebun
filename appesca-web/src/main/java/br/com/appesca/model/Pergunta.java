package br.com.appesca.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_PERGUNTA", catalog = "appesca")
public class Pergunta implements java.io.Serializable {

	private static final long serialVersionUID = -8806813412918825907L;
	
	private Integer id;
	private Boolean booleana;
	private Boolean respBooleana;
	private Integer ordem;
	private int idQuestao;

	public Pergunta() {
	}

	public Pergunta(int idQuestao) {
		this.idQuestao = idQuestao;
	}

	public Pergunta(Boolean booleana, Boolean respBooleana, Integer ordem, int idQuestao) {
		this.booleana = booleana;
		this.respBooleana = respBooleana;
		this.ordem = ordem;
		this.idQuestao = idQuestao;
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

	@Column(name = "booleana")
	public Boolean getBooleana() {
		return this.booleana;
	}

	public void setBooleana(Boolean booleana) {
		this.booleana = booleana;
	}

	@Column(name = "resp_booleana")
	public Boolean getRespBooleana() {
		return this.respBooleana;
	}

	public void setRespBooleana(Boolean respBooleana) {
		this.respBooleana = respBooleana;
	}

	@Column(name = "ordem")
	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Column(name = "id_questao", nullable = false)
	public int getIdQuestao() {
		return this.idQuestao;
	}

	public void setIdQuestao(int idQuestao) {
		this.idQuestao = idQuestao;
	}

}
