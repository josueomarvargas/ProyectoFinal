package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase guarda un {@code List<List<String>>}, esto es un arrayList de
 * Strings de 2 dimensiones<br>
 * <ol>
 * <li>El array de afuera guarda la temporada
 * <li>El array que está dentro guarda el número de capitulo junto con el nombre
 * </ol>
 * Un ejemplo más visual:<blockquote>
 * <table border="1">
 * <tr>
 * <th>Capitulo</th>
 * <th>1</th>
 * <th>2</th>
 * <th>3</th>
 * </tr>
 * <tr>
 * <th>Temporada 1</th>
 * <td>Patata</td>
 * <td>Zanahoria</td>
 * <td>Manzana</td>
 * </tr>
 * </table>
 * En esta tabla se muestra un ejemplo, el primer número será la temporada, como
 * en java los arrays empiezan por 0, la temporada 1 en realidad se guardará en
 * el índice 0, esto también pasa con los capitulos, y en este ejemplo vemos que
 * la temporada 1 y el nombre del capítulo 1 se llama "Patata" </blockquote>
 *
 * 
 * @author Henrique Yeguo
 **/
public class Serie extends ObraAudiovisual {

	/** Arry de dos dimensiones para guardar los capitulos **/
	private List<List<String>> nombreCap;

	/**
	 * Constructor vacío
	 */
	public Serie() {
		super();
		this.nombreCap = new ArrayList<List<String>>();
		this.nombreCap.add(new ArrayList<>());
	}

	/**
	 * 
	 * @return lista con todos los datos de la serie
	 */
	public List<List<String>> getNombreCap() {
		return nombreCap;
	}

	/**
	 * 
	 * @param nombreCap lista de 2 dimensiones con toda la información de la serie
	 */
	public void setNombreCap(List<List<String>> nombreCap) {
		this.nombreCap = nombreCap;
	}

	@Override
	public String toString() {
		return super.toString() + ", nombreCap=" + getInfoData();
	}

	private String getInfoData() {
		String aux = "";

		for (int i = 0; i < nombreCap.size(); i++) {
			for (int j = 0; j < nombreCap.get(i).size(); j++) {
				aux += "\n\t\tTemporada= " + (i + 1) + "\tCapitulo: " + (j + 1) + " - " + nombreCap.get(i).get(j);

			}
		}
		return aux;
	}
}