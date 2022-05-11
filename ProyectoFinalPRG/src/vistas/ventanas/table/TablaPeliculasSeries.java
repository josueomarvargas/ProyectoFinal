package vistas.ventanas.table;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controlador.utils.dao.GenericFactory;
import controlador.utils.views.Utilidades;
import vistas.dao.GetData;
import vistas.ventanas.custom.CustomTab;

import java.awt.Color;

public class TablaPeliculasSeries extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private JTable table;
	private JTextField nombreField;
	private JTextField directorField;
	private JTextField guionistaField;
	private JTextField numTfield;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnAceptar;

	/**
	 * Create the dialog.
	 */
	public TablaPeliculasSeries() {
		getContentPane().setBackground(Color.WHITE);
		addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent e) {
				System.out.println("aa");
			}
		});
		this.setUndecorated(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setMinimumSize(new Dimension(600, 475));
		setBounds(100, 100, 600, 475);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		CustomTab tabs = new CustomTab();
		tabs.setBackground(Color.WHITE);
		peliPanel.setBackground(Color.WHITE);
		tabs.add("Pelicula", peliPanel);
		seriePanel.setBackground(Color.WHITE);
		tabs.add("Serie", seriePanel);
		peliPanel.setLayout(null);
		seriePanel.setLayout(null);
		getContentPane().add(tabs);

		buttons(peliPanel);
		buttons(seriePanel);
		menuFiltro(peliPanel);
		menuFiltro(seriePanel);
		tablas(peliPanel, GetData.PELICULA);
		tablas(seriePanel, GetData.SERIE);

	}

	private void buttons(JPanel panel) {

		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("a");
			}
		});
		btnAceptar.setBounds(18, 360, 100, 23);
		panel.add(btnAceptar);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAnadir.setBounds(299, 360, 100, 23);
		panel.add(btnAnadir);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnVolver.setBounds(432, 360, 100, 23);
		panel.add(btnVolver);

	}

	private void menuFiltro(JPanel panel) {
		// Etiqueta: Nombre
		JLabel nombreLabel = new JLabel("Nombre Obra:");
		nombreLabel.setBounds(418, 30, 140, 15);
		panel.add(nombreLabel);

		// TextField: Nombre
		nombreField = new JTextField();
		nombreField.setBounds(418, 45, 140, 20);
		panel.add(nombreField);

		// Etiqueta: Director
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(418, 85, 140, 14);
		panel.add(directorLabel);

		// TextField: Director
		directorField = new JTextField();
		directorField.setBounds(418, 100, 140, 20);
		panel.add(directorField);

		// Etiqueta: Guionista
		JLabel guionistaLabel = new JLabel("Guionista:");
		guionistaLabel.setBounds(418, 140, 140, 14);
		panel.add(guionistaLabel);

		// TextField: Guionista
		guionistaField = new JTextField();
		guionistaField.setBounds(418, 155, 140, 20);
		panel.add(guionistaField);

		// Etiqueta: Num Trabajadores
		JLabel trabajdorLabel = new JLabel("Numero Trabajadores");
		trabajdorLabel.setBounds(418, 195, 140, 14);
		panel.add(trabajdorLabel);

		// TextField: Num Trabajadores
		numTfield = new JTextField();
		numTfield.setBounds(418, 210, 140, 20);
		panel.add(numTfield);

		// Etiqueta: Presupuesto
		JLabel presupuestoLabel = new JLabel("Presupuesto:");
		presupuestoLabel.setBounds(418, 250, 140, 14);
		panel.add(presupuestoLabel);

		// ComboBox: presupuesto
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Mayor a Menor", "Menor a Mayor" }));
		comboBox.setBounds(419, 265, 140, 22);
		panel.add(comboBox);

		// Boton para buscar
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBuscar.setBounds(419, 320, 100, 24);
		panel.add(btnBuscar);
	}

	private void tablas(JPanel panel, List<String> obra) {

		// Recoger los datos de las pelis/series
		Object[][] data = (Object[][]) GenericFactory.GETDATA.getUIcontroller().check(obra);
		String[] column = null;

		// Inicializar el nombre de las columnas dependiendo de que tabla es
		if (obra.equals(GetData.PELICULA)) {
			column = new String[] { "ID", "Nombre", "Director", "Guionista", "Num.T", "Presupuesto", "Fecha Estreno",
					"Es Taquillero" };
		} else {
			column = new String[] { "ID", "Nombre", "Director", "Guionista", "Num.T", "Presupuesto", "Fecha Estreno",
					"Temporadas", "Capitulos" };
		}

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(8, 30, 400, 315);

		// Crear una tabla
		table = new JTable();
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(data, column)); // Añadir los datos a la tabla
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		panel.add(scrollPane); // Añadir el scroll panel al panel de la pestaña

	}

}
