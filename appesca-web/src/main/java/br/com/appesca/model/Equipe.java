package br.com.appesca.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "TB_EQUIPE", schema = "appesca")
public class Equipe implements java.io.Serializable {

	private static final long serialVersionUID = -4258798850777666253L;
	
	private Integer id;
	private String nome;
	private String descricao;
	private int idCoordenador;
	private Date data;

	public Equipe() {
	}

	public Equipe(String nome, int idCoordenador, Date data) {
		this.nome = nome;
		this.idCoordenador = idCoordenador;
		this.data = data;
	}

	public Equipe(String nome, String descricao, int idCoordenador, Date data) {
		this.nome = nome;
		this.descricao = descricao;
		this.idCoordenador = idCoordenador;
		this.data = data;
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

	@Column(name = "nome", nullable = false, length = 100)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "descricao", length = 100)
	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Column(name = "id_coordenador", nullable = false)
	public int getIdCoordenador() {
		return this.idCoordenador;
	}

	public void setIdCoordenador(int idCoordenador) {
		this.idCoordenador = idCoordenador;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data", nullable = false, length = 19)
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
