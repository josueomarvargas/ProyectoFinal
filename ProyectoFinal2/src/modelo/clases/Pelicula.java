package modelo.clases;

public class Pelicula extends ObraAudiovisual {

	private boolean esTaquillera;

	public boolean isEsTaquillera() {
		return esTaquillera;
	}

	public void setEsTaquillera(boolean esTaquillera) {
		this.esTaquillera = esTaquillera;
	}

	@Override
	public String toString() {
		return super.toString() + ", esTaquillera=" + esTaquillera;
	}

}
