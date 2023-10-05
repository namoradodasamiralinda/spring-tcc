package br.itb.projeto.padaria3_fk.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.hibernate.annotations.Table;
import org.springframework.data.annotation.Id;
 
@Entity

public class CHECKIN {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String DATA_INICIO;
	private String ID_PLANO;
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
	public String getID_PLANO() {
		return ID_PLANO;
	}
	public void setID_PLANO(String iD_PLANO) {
		ID_PLANO = iD_PLANO;
	}
		
	
}