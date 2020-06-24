package cl.bice.lab.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Persona {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private int rutMantisa;
	@Column
	private String rutDv;
	@Column
	private String nombre;
	@Column
	private String apellido;
	@Column
	private String email;
	
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
		
	

}
