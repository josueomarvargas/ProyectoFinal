package modelo.clases;

/**
 * Clase para guardar la información de las películas
 * 
 * @author Henrique Yeguo
 * @author Iker Aspiazu
 */
public class Pelicula extends ObraAudiovisual {

	/** Si es taquillera o no **/
	private boolean esTaquillera;

	/**
	 * 
	 * @return true si es taquillera
	 */
	public boolean isEsTaquillera() {
		return esTaquillera;
	}

	/**
	 * 
	 * @param esTaquillera
	 */
	public void setEsTaquillera(boolean esTaquillera) {
		this.esTaquillera = esTaquillera;
	}

	@Override
	public String toString() {
		return super.toString() + ", esTaquillera=" + esTaquillera;
	}

}
