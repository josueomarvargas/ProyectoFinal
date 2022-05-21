package controlador.utils.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import vistas.ventanas.custom.components.MenuButton;

/**
 * Esta clase abstracta, que no se va ha poder instanciar, solo tendr� m�todos
 * est�ticos que nos ser�n �tiles a lo largo del programa.
 * 
 * @author yeguo
 *
 */
public abstract class Utilidades {

	/** Tama�o de la pantalla **/
	private static Dimension size;

	/** Ruta absoluta del proyecto **/
	public static String basePath = new File("").getAbsolutePath();
	/** Ruta absoulta m�s la ruta relativa donde se guardar� las imagenes **/
	public static Path imgSavePath = Paths.get(basePath, "/src/modelo/img/");

	/**
	 * Redimensionar las columnas seg�n el valor m�s largo de la columna.
	 * 
	 * @param table la tabla que queremos redimensionar su columna
	 */
	public static void resizeColumnWidth(JTable table) {
		// Recoger el modelo da las columnas
		final TableColumnModel columnModel = table.getColumnModel();
		// Iterar por las columnas
		for (int column = 0; column < table.getColumnCount(); column++) {
			// La anchura m�nima de la columna
			int width = 100;
			// Iterar por las filas
			for (int row = 0; row < table.getRowCount(); row++) {
				// Recogemos la informaci�n de la celda en esa fila-columna
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				// Comparamos si el tama�o del contenido es m�s grande que el m�nimo que hemos
				// establecido anteriormente y pondr� la anchura el n�mero m�s grande
				width = Math.max(comp.getPreferredSize().width + 10, width);
			}
			columnModel.getColumn(column).setPreferredWidth(width);
		}
	}

	/**
	 * Este m�todo lo que har� es redimensionar la ventana para que sea la mitad de
	 * grande que la pantalla <br>
	 * Ej; si tienes una pantalla 1920x1080 se redimensionar� a 960x540.
	 * 
	 * @param window la ventana que queremos que queremos redimensionar
	 * @return devolveremos la
	 */
	public static Dimension resizeWindow(Container window) {
		if (size == null) {
			size = getWindowDimension(window);
		}
		return new Dimension(size.width / 2, size.height / 2);
	}

	public static void centerWindow(Container parent, Container window) {
		if (parent instanceof Frame && window == null) {
			resizeWindow(parent);
			parent.setLocation(size.width / 2 - parent.getWidth() / 2, size.height / 2 - parent.getHeight() / 2);
		} else {
			Rectangle posParent = parent.getBounds();
			int x = (int) posParent.getX() + (posParent.width / 2) - (window.getWidth() / 2);
			int y = (int) posParent.getY() + (posParent.height / 2) - (window.getHeight() / 2);

			window.setLocation(x, y);
		}
	}

	/**
	 * Este m�todo devolver� el tama�o del monitor, por ejemplo 1920x1080
	 * 
	 * @param window ventana que se usar� para recoger el tama�o de la pantalla
	 * @return devuelve el tama�o de la pantall
	 */
	private static Dimension getWindowDimension(Container window) {
		Toolkit toolKit = window.getToolkit();
		return toolKit.getScreenSize();

	}

	/**
	 * M�todo para configurar los botones
	 * 
	 * @param b    bot�n que se configurar�
	 * @param text el texto que tendr� el boton
	 */
	public static void configButtons(MenuButton b, String text) {
		b.setBackground(new Color(25, 182, 247));
		b.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
		b.setForeground(new Color(255, 255, 255));
		b.setText(text);
		b.setFont(new Font("sansserif", Font.BOLD, 14));

	}

	/**
	 * M�todo para redimensionar una imagen que le a�adiremos a una etiqueta.
	 * 
	 * @param label etiqueta donde se pondr� la imagen
	 * @param file  archivo de la imagen que se quiere a�adir
	 * @return imagen ya redimensionada proporcinalmente al tama�o de la etiqueta
	 */
	public static ImageIcon resizeIcon(JLabel label, File file) {
		ImageIcon icon = new ImageIcon(file.getPath());
		Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	/**
	 * M�todo para crear una etiqueta con una imagen que se usar� para el fonto de
	 * las ventanas.
	 * 
	 * @param window ventana para recoger al ruta de la clase
	 * @return devolver� una etiqueta con una imagen ya definida
	 */
	public static JLabel backgroundImg(Container window) {

		return new JLabel(new ImageIcon(
				window.getClass().getResource("/vistas/ventanas/custom/components/img/background_img.png")));

	}

	/**
	 * M�todo para comprobar las fechas.
	 * 
	 * @param date texto que se querr� validar como fecha
	 * @return la fecha ya parseada, si no ha parseado bien devolver� null
	 */
	public static LocalDate validateDate(String date) {

		final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

		try {
			return LocalDate.parse(date, dateFormatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	/**
	 * Este m�todo permite que el usuario seleccione una foto, al seleccionarla se
	 * har� una copia en una carpeta preestablecida y se le devolver� un fichero con
	 * la ruta a esa copia.
	 * 
	 * @return archivo con la ruta de la foto
	 */
	public static File addFoto() {
		JFileChooser fileChooser = new JFileChooser("C:\\");
		FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg", "png");
		fileChooser.setFileFilter(filter);

		int response = fileChooser.showOpenDialog(null);

		if (response == JFileChooser.APPROVE_OPTION) {
			File foto = fileChooser.getSelectedFile();

			Path copiedFile = null;
			File aux = null;
			try {
				copiedFile = Paths.get(imgSavePath.toString() + "\\" + foto.getName());
				aux = Files.copy(foto.toPath(), copiedFile, StandardCopyOption.REPLACE_EXISTING).toFile();
			} catch (Exception e) {
				System.err.println(e);
			}

			return new File(aux.toString().substring(basePath.length()));
		}
		return null;
	}

}
