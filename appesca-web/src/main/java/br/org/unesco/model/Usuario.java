package br.org.unesco.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.ByteArrayInputStream;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.joda.time.DateTime;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import br.org.unesco.appesca.enums.PerfilEnum;

@Entity
@Table(name = "TB_USUARIO", schema="appesca")
public class Usuario implements java.io.Serializable {

	private static final long serialVersionUID = -7140175801442164346L;

	private Integer id;
	private String nome;
	private String email;
	private String endereco;
	private String login;
	private String senha;
	private PerfilEnum perfil;
	private byte[] imagem;
	private String uf;
	//private String centerMap;
	
	private List<Equipe> listaEquipes;
	
//	private List<Rastro> listaRastros;
//	
	//private MapModel map;

	
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
	

	@Column(name = "email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listaMembrosEquipe")
	public List<Equipe> getListaEquipes() {
		return listaEquipes;
	}

	public void setListaEquipes(List<Equipe> listaEquipes) {
		this.listaEquipes = listaEquipes;
	}

	@NotNull
    @Size(min = 2, max = 2)
	@Column(length = 2)
	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
//
//	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "usuario")
//	@Fetch(FetchMode.SUBSELECT)
//	public List<Rastro> getListaRastros() {
//		return listaRastros;
//	}
//
//	public void setListaRastros(List<Rastro> listaRastros) {
//		this.listaRastros = listaRastros;
//	}
//	
//	
//	@Transient
//	public MapModel getMap() {
//		map = new DefaultMapModel();
//		
//		//int ultimoRastro = listaRastros.size() -1;
//		
//		for (Rastro r : listaRastros) {
//			DateTime dt = new DateTime(r.getDataRegistro());
//			
//			int dia = dt.dayOfWeek().get();
//			
//			if(r.getLatitude()!=null && r.getLongitude()!=null){
//				//LatLng coord = new LatLng(r.getLatitude().doubleValue(), r.getLongitude().doubleValue());
//				LatLng coord = new LatLng(-1.40740000, -48.45145000);
//				map.addOverlay(new Marker(coord, "Local do formulario", "konyaalti.png", "http://maps.google.com/mapfiles/ms/micons/blue-dot.png"));
////			    if(listaRastros.lastIndexOf(r) == ultimoRastro){
////			    	setCenterMap(r.getLatitude().doubleValue() + "," + r.getLongitude().doubleValue());
////			    }
//			}
//			
//		}
//		
//		return map;
//	}
//	@Transient
//	public String getCenterMap() {
//		this.centerMap = "-1.40740000,-48.45145000";
//		return centerMap;
//	}
//
//	public void setCenterMap(String center) {
//		this.centerMap = center;
//	}
}
