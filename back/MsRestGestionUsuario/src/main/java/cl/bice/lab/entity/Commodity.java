package cl.bice.lab.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Commodity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private int rutMantisa;
	@Column
	private double totalUsd;
	@Column
	private double totalClp;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getRutMantisa() {
		return rutMantisa;
	}
	public void setRutMantisa(int rutMantisa) {
		this.rutMantisa = rutMantisa;
	}
	public double getTotalUsd() {
		return totalUsd;
	}
	public void setTotalUsd(double totalUsd) {
		this.totalUsd = totalUsd;
	}
	public double getTotalClp() {
		return totalClp;
	}
	public void setTotalClp(double totalClp) {
		this.totalClp = totalClp;
	}
	
	
	

}
