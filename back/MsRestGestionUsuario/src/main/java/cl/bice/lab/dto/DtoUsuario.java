package cl.bice.lab.dto;

import java.util.Date;

public class DtoUsuario {
	private int rutMantisa;
	private String rutDv;
	private String nombre;
	private String apellido;
	private String email;
	private Date fechaNacimiento;
	private double totalCommodityUsd;
	private double totalCommodityClp;
	
	public int getRutMantisa() {
		return rutMantisa;
	}
	public void setRutMantisa(int rutMantisa) {
		this.rutMantisa = rutMantisa;
	}
	public String getRutDv() {
		return rutDv;
	}
	public void setRutDv(String rutDv) {
		this.rutDv = rutDv;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public double getTotalCommodityUsd() {
		return totalCommodityUsd;
	}
	public void setTotalCommodityUsd(double totalCommodityUsd) {
		this.totalCommodityUsd = totalCommodityUsd;
	}
	public double getTotalCommodityClp() {
		return totalCommodityClp;
	}
	public void setTotalCommodityClp(double totalCommodityClp) {
		this.totalCommodityClp = totalCommodityClp;
	}
	@Override
	public String toString() {
		return "DtoUsuario [rutMantisa=" + rutMantisa + ", rutDv=" + rutDv + ", nombre=" + nombre + ", apellido="
				+ apellido + ", email=" + email + ", fechaNacimiento=" + fechaNacimiento + ", totalCommodityUsd="
				+ totalCommodityUsd + ", totalCommodityClp=" + totalCommodityClp + "]";
	}
	
	

		
}
