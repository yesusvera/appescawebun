package br.com.appesca.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "TB_FORMULARIO", catalog = "appesca", uniqueConstraints = @UniqueConstraint(columnNames = "nome") )
public class Formulario implements java.io.Serializable {

	private static final long serialVersionUID = 2165508619825487958L;

	private Integer id;
	private String nome;
	private int idTipoFormulario;
	private int idUsuario;
	private Date dataAplicacao;

	public Formulario() {
	}

	public Formulario(String nome, int idTipoFormulario, int idUsuario, Date dataAplicacao) {
		this.nome = nome;
		this.idTipoFormulario = idTipoFormulario;
		this.idUsuario = idUsuario;
		this.dataAplicacao = dataAplicacao;
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

	@Column(name = "nome", unique = true, nullable = false, length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "id_tipo_formulario", nullable = false)
	public int getIdTipoFormulario() {
		return this.idTipoFormulario;
	}

	public void setIdTipoFormulario(int idTipoFormulario) {
		this.idTipoFormulario = idTipoFormulario;
	}

	@Column(name = "id_usuario", nullable = false)
	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_aplicacao", nullable = false, length = 19)
	public Date getDataAplicacao() {
		return this.dataAplicacao;
	}

	public void setDataAplicacao(Date dataAplicacao) {
		this.dataAplicacao = dataAplicacao;
	}

}
