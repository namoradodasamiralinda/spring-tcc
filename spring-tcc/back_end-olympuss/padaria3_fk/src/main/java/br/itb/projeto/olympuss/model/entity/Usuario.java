package br.itb.projeto.olympuss.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// DEFINE A CLASSE COMO UMA ENTIDADE NA CONEXÃO COM O BANCO DE DADOS
@Entity

// INDICA QUAL TABELA DO BANCO DE DADOS REPRESENTA A CLASSE
@Table(name = "USUARIO")
public class Usuario {
	
	@Id
	// DEFINE A GERAÇÃO AUTOMÁTICA VALORES PARA O ATRIBUTO
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	private String NOME_COMPLETO;
	private String DATA_NASC;
	private String TELEFONE;
	private String EMAIL; // ADMIN, TECNICO ou USER
	private String SENHA;
	private String NIVEL_ACESSO;
	private byte [] FOTO;
	private String STATUS_USUARIO;

	@ManyToOne
	@JoinColumn(name="ID_PLANO")
	private Plano plano;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNOME_COMPLETO() {
		return NOME_COMPLETO;
	}
	public void setNOME_COMPLETO(String nOME_COMPLETO) {
		NOME_COMPLETO = nOME_COMPLETO;
	}
	public String getDATA_NASC() {
		return DATA_NASC;
	}
	public void setDATA_NASC(String dATA_NASC) {
		DATA_NASC = dATA_NASC;
	}
	public String getTELEFONE() {
		return TELEFONE;
	}
	public void setTELEFONE(String tELEFONE) {
		TELEFONE = tELEFONE;
	}
	public String getEMAIL() {
		return EMAIL;
	}
	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}
	public String getSENHA() {
		return SENHA;
	}
	public void setSENHA(String senha2) {
		SENHA = senha2;
	}
	public String getNIVEL_ACESSO() {
		return NIVEL_ACESSO;
	}
	public void setNIVEL_ACESSO(String nIVEL_ACESSO) {
		NIVEL_ACESSO = nIVEL_ACESSO;
	}
		public byte[] getFOTO() {
		return FOTO;
	}
		public String getSTATUS_USUARIO() {
		return STATUS_USUARIO;
	}
	public void setSTATUS_USUARIO(String sTATUS_USUARIO) {
		STATUS_USUARIO = sTATUS_USUARIO;
	}
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	public void setFOTO(byte[] fOTO) {
		FOTO = fOTO;
	}



	
}