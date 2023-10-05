package br.itb.projeto.padaria3_fk.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.data.annotation.Id;
 
@Entity

public class CHECKIN {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String DATA_INICIO;

	
	@ManyToOne
	@JoinColumn(name="ID_PLANO")
	private Plano plano;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
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