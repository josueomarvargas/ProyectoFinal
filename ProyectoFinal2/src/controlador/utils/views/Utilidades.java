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
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Base64;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.plaf.basic.BasicEditorPaneUI;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import vistas.ventanas.custom.components.MenuButton;

public class Utilidades {

	// Screen size
	private static Dimension size;

	// Img path
	private static String basePath = new File("").getAbsolutePath();
	public static Path imgSavePath = Paths.get(basePath, "/src/vistas/img/");

	/**
	 * Redimensionar las columnas seg�n el valor m�s largo de la columna.
	 * 
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

	private static Dimension getWindowDimension(Container window) {
		Toolkit toolKit = window.getToolkit();
		return toolKit.getScreenSize();

	}

	public static void configButtons(MenuButton b, String text) {
		b.setBackground(new Color(25, 182, 247));
		b.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
		b.setForeground(new Color(255, 255, 255));
		b.setText(text);
		b.setFont(new Font("sansserif", Font.BOLD, 14));

	}

	public static ImageIcon resizeIcon(JLabel comp, File file) {
		ImageIcon icon = new ImageIcon(file.getPath());
		Image img = icon.getImage().getScaledInstance(comp.getWidth(), comp.getHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public static JLabel backgroundImg(Container window) {

		return new JLabel(new ImageIcon(
				window.getClass().getResource("/vistas/ventanas/custom/components/img/background_img.png")));

	}

	public static LocalDate validateDate(String date) {

		final DateTimeFormatter dateFormatter = DateTimeFormatter.BASIC_ISO_DATE;

		try {
			return LocalDate.parse(date, dateFormatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

	public static File addFoto() {
		JFileChooser fileChooser = new JFileChooser("C:\\");

		int response = fileChooser.showOpenDialog(null);

		if (response == JFileChooser.APPROVE_OPTION) {
			File foto = fileChooser.getSelectedFile();

			Path copiedFile = null;
			File aux = null;
			try {
				if (copiedFile == null)
					copiedFile = Paths.get(imgSavePath.toString() + "\\" + foto.getName());
				aux = Files.copy(foto.toPath(), copiedFile, StandardCopyOption.REPLACE_EXISTING).toFile();
			} catch (Exception e) {
				System.err.println(e);
			}

			return aux;
		}
		return null;
	}

}
