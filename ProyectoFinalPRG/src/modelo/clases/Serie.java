package modelo.clases;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta clase guarda un {@code List<List<String>>}, esto es un arrayList de
 * Strings de 2 dimensiones<br>
 * <ol>
 * <li>El array de afuera guarda la temporada
 * <li>El array que est� dentro guarda el n�mero de capitulo junto con el nombre
 * </ol>
 * Un ejemplo m�s visual:<blockquote>
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
 * En esta tabla se muestra un ejemplo, el primer n�mero ser� la temporada, como
 * en java los arrays empiezan por 0, la temporada 1 en realidad se guardar� en
 * el �ndice 0, esto tambi�n pasa con los capitulos, y en este ejemplo vemos que
 * la temporada 1 y el nombre del cap�tulo 1 se llama "Patata" </blockquote>
 *
 * 
 * @author Henrique Yeguo
 **/
public class Serie extends ObraAudiovisual {

	private List<List<String>> nombreCap;

	public Serie() {
		super();
		this.nombreCap = new ArrayList<>();
	}

	public List<List<String>> getNombreCap() {
		return nombreCap;
	}

	public void setNombreCap(List<List<String>> nombreCap) {
		this.nombreCap = nombreCap;
	}

}