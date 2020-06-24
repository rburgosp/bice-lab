package cl.bice.lab.dto;

public class DtoRespuestaCrud {
	
	private boolean respuestaTransaccion;
	private String mensajeTransaccion;
	public boolean isRespuestaTransaccion() {
		return respuestaTransaccion;
	}
	public void setRespuestaTransaccion(boolean respuestaTransaccion) {
		this.respuestaTransaccion = respuestaTransaccion;
	}
	public String getMensajeTransaccion() {
		return mensajeTransaccion;
	}
	public void setMensajeTransaccion(String mensajeTransaccion) {
		this.mensajeTransaccion = mensajeTransaccion;
	}
	
	

}
