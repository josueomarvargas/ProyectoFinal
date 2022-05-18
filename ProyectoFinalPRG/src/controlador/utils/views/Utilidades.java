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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.text.Document;
import javax.swing.text.JTextComponent;

import vistas.ventanas.custom.components.MenuButton;

public abstract class Utilidades {

	// Screen size
	private static Dimension size;

	// Img path
	public static String basePath = new File("").getAbsolutePath();
	public static Path imgSavePath = Paths.get(basePath, "/src/modelo/img/");

	/**
	 * Redimensionar las columnas según el valor más largo de la columna.
	 * 
	 * 
	 * @param table la tabla que queremos redimensionar su columna
	 */
	public static void resizeColumnWidth(JTable table) {
		// Recoger el modelo da las columnas
		final TableColumnModel columnModel = table.getColumnModel();
		// Iterar por las columnas
		for (int column = 0; column < table.getColumnCount(); column++) {
			// La anchura mínima de la columna
			int width = 100;
			// Iterar por las filas
			for (int row = 0; row < table.getRowCount(); row++) {
				// Recogemos la información de la celda en esa fila-columna
				TableCellRenderer renderer = table.getCellRenderer(row, column);
				Component comp = table.prepareRenderer(renderer, row, column);
				// Comparamos si el tamaño del contenido es más grande que el mínimo que hemos
				// establecido anteriormente y pondrá la anchura el número más grande
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

	public static ImageIcon resizeIcon(JLabel label, File file) {
		ImageIcon icon = new ImageIcon(file.getPath());
		Image img = icon.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
		return new ImageIcon(img);
	}

	public static JLabel backgroundImg(Container window) {

		return new JLabel(new ImageIcon(
				window.getClass().getResource("/vistas/ventanas/custom/components/img/background_img.png")));

	}

	public static LocalDate validateDate(String date) {

		final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;

		try {
			return LocalDate.parse(date, dateFormatter);
		} catch (DateTimeParseException e) {
			return null;
		}
	}

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
