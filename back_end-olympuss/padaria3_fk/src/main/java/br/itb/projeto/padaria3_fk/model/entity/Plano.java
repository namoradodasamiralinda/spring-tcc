package br.itb.projeto.padaria3_fk.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PLANO")
public class Plano {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String NOME;
	private String DESCRICAO;
	private double PRECO_MENSAL;
	
	@ManyToOne
	@JoinColumn(name="categoria_id")
	private CHECKIN categoria;
	
	private byte[] foto;
	
	private String statusProduto;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNOME() {
		return NOME;
	}

	public void setNOME(String nOME) {
		NOME = nOME;
	}

	public String getDESCRICAO() {
		return DESCRICAO;
	}

	public void setDESCRICAO(String dESCRICAO) {
		DESCRICAO = dESCRICAO;
	}

	public double getPRECO_MENSAL() {
		return PRECO_MENSAL;
	}

	public void setPRECO_MENSAL(double pRECO_MENSAL) {
		PRECO_MENSAL = pRECO_MENSAL;
	}

	public CHECKIN getCategoria() {
		return categoria;
	}

	public void setCategoria(CHECKIN categoria) {
		this.categoria = categoria;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getStatusProduto() {
		return statusProduto;
	}

	public void setStatusProduto(String statusProduto) {
		this.statusProduto = statusProduto;
	}

	


	
}