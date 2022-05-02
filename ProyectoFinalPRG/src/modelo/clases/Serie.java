package modelo.clases;

import java.time.LocalDate;

public class Serie extends ObraAudiovisual {

	private int numTemporada;
	private int numCapitulo;
	private String nombreCap;

	public Serie() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Serie(int idObra, String nombre, int duracion, LocalDate fechaEstreno, int presupuesto, String tipo,
			int numTemporada, int numCapitulo, String nombreCap) {
		super(idObra, nombre, duracion, fechaEstreno, presupuesto, tipo);
		this.numTemporada = numTemporada;
		this.numCapitulo = numCapitulo;
		this.nombreCap = nombreCap;
	}

	public int getNumTemporada() {
		return numTemporada;
	}

	public void setNumTemporada(int numTemporada) {
		this.numTemporada = numTemporada;
	}

	public int getNumCapitulo() {
		return numCapitulo;
	}

	public void setNumCapitulo(int numCapitulo) {
		this.numCapitulo = numCapitulo;
	}

	public String getNombreCap() {
		return nombreCap;
	}

	public void setNombreCap(String nombreCap) {
		this.nombreCap = nombreCap;
	}

}