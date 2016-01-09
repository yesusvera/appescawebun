package br.com.appesca.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.ByteArrayInputStream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import br.com.appesca.enums.PerfilEnum;

@Entity
@Table(name = "TB_USUARIO", schema="appesca")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -7140175801442164346L;

	private Integer id;
	private String nome;
	private String endereco;
	private String login;
	private String senha;
	private PerfilEnum perfil;
	private byte[] imagem;

	public Usuario() {
	}

	public Usuario(String nome, String endereco, String login, String senha, PerfilEnum perfil, byte[] imagem) {
		this.nome = nome;
		this.endereco = endereco;
		this.login = login;
		this.senha = senha;
		this.perfil = perfil;
		this.imagem = imagem;
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

	@Column(name = "nome", length = 45)
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Column(name = "endereco", length = 45)
	public String getEndereco() {
		return this.endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@NotNull
    @Size(min = 1, max = 20, message="Digite o login.")
	@Column(name = "login", length = 20)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
	
	@NotNull
    @Size(min = 1, max = 20, message="Digite a senha.")
	@Column(name = "senha", length = 20)
	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Column(name = "perfil")
	@Enumerated(EnumType.ORDINAL)
	public PerfilEnum getPerfil() {
		return this.perfil;
	}

	public void setPerfil(PerfilEnum perfil) {
		this.perfil = perfil;
	}

	@Column(name = "imagem")
	public byte[] getImagem() {
		return imagem;
	}

	public void setImagem(byte[] imagem) {
		this.imagem = imagem;
	}
	
	
	@Transient
	public StreamedContent getImageGraphics()  {
	   if(imagem!=null){
		   return new DefaultStreamedContent(new ByteArrayInputStream(imagem));
	   }else{
		   return null;
	   }
	 }

}
