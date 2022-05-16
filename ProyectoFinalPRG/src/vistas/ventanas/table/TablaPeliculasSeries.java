package vistas.ventanas.table;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.awt.Window;

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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import vistas.dao.GetData;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosObra;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TablaPeliculasSeries extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private final JDialog thisDialog;
	private JTable tablePeli;
	private JTable tableSerie;
	private JTextField nombreField;
	private JTextField directorField;
	private JTextField guionistaField;
	private JTextField numTfield;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnAceptar;
	private int filterX = 780;
	private int filterY = 20;
	private int filterWidth = 160;
	private int filterHeight = 20;
	@SuppressWarnings("unused")
	private DatosObra dataObra;
	private TitleBar bar;

	/**
	 * Create the dialog.
	 */
	public TablaPeliculasSeries(Window parent, boolean modal) {
//		super(parent);
		setModal(true);
		this.setUndecorated(true);
		thisDialog = this;
		setSize(Utilidades.resizeWindow(this));
//		Utilidades.centerWindow(parent, this);
		getContentPane().setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);

		CustomTab tabs = new CustomTab();
		tabs.setTabPlacement(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.WHITE);
		peliPanel.setBackground(Color.WHITE);
		tabs.add("Pelicula", peliPanel);
		seriePanel.setBackground(Color.WHITE);
		tabs.add("Serie", seriePanel);
		seriePanel.setLayout(null);
		getContentPane().add(tabs);

		menuFiltro(peliPanel);
		menuFiltro(seriePanel);
		buttons(peliPanel);
		buttons(seriePanel);
		tablePeli = tablas(peliPanel, GetData.PELICULA, tablePeli);
		tableSerie = tablas(seriePanel, GetData.SERIE, tableSerie);

	}

	private void buttons(JPanel panel) {
		filterY += 350;
		filterHeight = 30;

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(780, 50, 160, 30);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("a");
			}
		});
		panel.add(btnAceptar);

		btnAnadir = new JButton("+");
		btnAnadir.setBounds(880, 40, 50, 30);
		btnAnadir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnAnadir);

		btnVolver = new JButton(new String("\u2190"));
		btnVolver.setBounds(780, 40, 50, 30);
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnVolver);

	}

	private void menuFiltro(JPanel panel) {
		filterY = 20;
		peliPanel.setLayout(null);

		// Etiqueta: Nombre
		JLabel nombreLabel = new JLabel("Nombre Obra:");
		nombreLabel.setBounds(780, 20, 160, 20);
		panel.add(nombreLabel);

		// TextField: Nombre
		nombreField = new JTextField();
		nombreField.setBounds(780, 20, 160, 25);
		panel.add(nombreField);

		// Etiqueta: Director
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(780, 45, 160, 20);
		panel.add(directorLabel);

		// TextField: Director
		directorField = new JTextField();
		directorField.setBounds(780, 20, 160, 25);
		panel.add(directorField);

		// Etiqueta: Guionista
		JLabel guionistaLabel = new JLabel("Guionista:");
		guionistaLabel.setBounds(780, 45, 160, 20);
		panel.add(guionistaLabel);

		// TextField: Guionista
		guionistaField = new JTextField();
		guionistaField.setBounds(780, 20, 160, 25);
		panel.add(guionistaField);

		// Etiqueta: Num Trabajadores
		JLabel trabajdorLabel = new JLabel("Numero Trabajadores");
		trabajdorLabel.setBounds(780, 45, 160, 20);
		panel.add(trabajdorLabel);

		// TextField: Num Trabajadores
		numTfield = new JTextField();
		numTfield.setBounds(780, 20, 160, 25);
		panel.add(numTfield);

		// Etiqueta: Presupuesto
		JLabel presupuestoLabel = new JLabel("Presupuesto:");
		presupuestoLabel.setBounds(780, 45, 160, 20);
		panel.add(presupuestoLabel);

		// ComboBox: presupuesto
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(780, 20, 160, 25);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Mayor a Menor", "Menor a Mayor" }));
		panel.add(comboBox);

		// Boton para buscar
		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(780, 50, 160, 25);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		panel.add(btnBuscar);
	}

	private JTable tablas(JPanel panel, String obra, JTable table) {

		// Recoger los datos de las pelis/series
		Object[][] data = FactoryDAO.getGetData().checkInfo(obra);
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
		scrollPane.setBounds(5, 40, 760, 445);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Crear una tabla
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TableModel model = null;
				int i;
				if (tablePeli.getSelectedRow() != -1) {
					i = tablePeli.getSelectedRow();
					model = tablePeli.getModel();
				} else {
					i = tableSerie.getSelectedRow();
					model = tableSerie.getModel();
				}
				thisDialog.setVisible(false);
				int id = Integer.parseInt(model.getValueAt(i, 0).toString());
				ObraAudiovisual oa = (ObraAudiovisual) GetData.getDatos(GetData.OBRA).get(id);
				dataObra = new DatosObra(thisDialog, true, oa);
				dataObra.setVisible(true);
			}
		});
		table.setBackground(new Color(255, 255, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Añadir los datos a la tabla
		table.setModel(new DefaultTableModel(data, column));
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		panel.add(scrollPane); // Añadir el scroll panel al panel de la pestaña

		return table;
	}

}
