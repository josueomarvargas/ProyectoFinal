package controlador.utils.views;

import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class Utilidades {

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

}
