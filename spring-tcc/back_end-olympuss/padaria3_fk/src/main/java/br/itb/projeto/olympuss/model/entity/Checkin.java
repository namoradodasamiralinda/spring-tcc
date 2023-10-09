package br.itb.projeto.olympuss.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
 
@Entity
@Table(name = "CHECKIN")
public class Checkin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long id;
	
	private String DATA_INICIO;

	@ManyToOne
	@JoinColumn(name="ID_PLANO")
	private Plano plano;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getDATA_INICIO() {
		return DATA_INICIO;
	}
	public void setDATA_INICIO(String dATA_INICIO) {
		DATA_INICIO = dATA_INICIO;
	}
	
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
		
	
	
}