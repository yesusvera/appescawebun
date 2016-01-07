package br.com.appesca.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TB_QUESTAO", schema="appesca")
public class Questao implements java.io.Serializable {

	private static final long serialVersionUID = -7214392109110721089L;

	private Integer id;
	private String titulo;
	private Integer ordem;
	private int idFormulario;

	public Questao() {
	}

	public Questao(int idFormulario) {
		this.idFormulario = idFormulario;
	}

	public Questao(String titulo, Integer ordem, int idFormulario) {
		this.titulo = titulo;
		this.ordem = ordem;
		this.idFormulario = idFormulario;
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

	@Column(name = "titulo", length = 100)
	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Column(name = "ordem")
	public Integer getOrdem() {
		return this.ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	@Column(name = "id_formulario", nullable = false)
	public int getIdFormulario() {
		return this.idFormulario;
	}

	public void setIdFormulario(int idFormulario) {
		this.idFormulario = idFormulario;
	}

}
