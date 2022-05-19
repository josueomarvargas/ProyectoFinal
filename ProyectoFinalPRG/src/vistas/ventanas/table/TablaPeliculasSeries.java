package vistas.ventanas.table;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import vistas.dao.GetData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosObra;

public class TablaPeliculasSeries extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private final JDialog thisDialog;
//	private final Trabajador trabajador = CheckLogin.getLogin();
	private CustomTab tabs = null;
	private JTable tablePeli = null;
	private JTable tableSerie = null;
	private TextField nombreField, directorField, guionistaField, numTfield;
	private MenuButton btnBuscar, btnAnadir, btnVolver, btnRefrescar;
	private DatosObra dataObra;
	private TitleBar bar;
	private Window parent;
	private int index;
	private TableModel model;

	public TablaPeliculasSeries(Window parent, boolean modal) {
//		super(parent);
		setModal(modal);
		this.setUndecorated(true);
		this.parent = parent;
		setSize(Utilidades.resizeWindow(this));
//		Utilidades.centerWindow(parent, this);
		thisDialog = this;
		contentPanel.setBackground(Color.WHITE);
		init();
	}

	private void init() {
		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);

		loadTables();
		buttons();
		menuFiltro();

		tablePeli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablePeli.getSelectedRow() != -1) {
					index = tablePeli.getSelectedRow();
					model = tablePeli.getModel();
				}
				openData(index, model);
				thisDialog.dispose();
			}
		});

		tableSerie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableSerie.getSelectedRow() != -1) {
					index = tableSerie.getSelectedRow();
					model = tableSerie.getModel();
				}
				openData(index, model);
				thisDialog.dispose();
			}
		});

		switch (new String("administrador")) {
		case "administrador":
			btnAnadir.setEnabled(true);
			btnVolver.setEnabled(true);
			btnRefrescar.setEnabled(true);
			break;

		default:
			break;
		}

		getContentPane().add(contentPanel);
	}

	private void loadTables() {
		tabs = new CustomTab();
		tabs.setBounds(15, 50, 750, 480);
		tabs.setTabPlacement(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.WHITE);
		peliPanel.setBackground(Color.WHITE);
		tabs.add("Pelicula", peliPanel);
		seriePanel.setBackground(Color.WHITE);
		tabs.add("Serie", seriePanel);
		seriePanel.setLayout(null);
		contentPanel.add(tabs);
		tablePeli = tablas(peliPanel, ClasesEnum.PELICULA.getName(), tablePeli);
		tableSerie = tablas(seriePanel, ClasesEnum.SERIE.getName(), tableSerie);
	}

	/**
	 * Abrir ventana con los datos
	 * 
	 * @param model2
	 * @param index2
	 * 
	 **/
	private void openData(int i, TableModel tableModel) {
		thisDialog.setVisible(false);
		int id = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
		ObraAudiovisual oa = (ObraAudiovisual) GetData.getDatos(ClasesEnum.OBRA.getName()).get(id);
		dataObra = new DatosObra(thisDialog, true, oa, null);
		dataObra.setVisible(true);
	}

	private void buttons() {

		btnAnadir = new MenuButton();
		btnAnadir.setBounds(890, 455, 50, 30);
		btnAnadir.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
		Utilidades.configButtons(btnAnadir, "");
		btnAnadir.addActionListener(this);
		contentPanel.add(btnAnadir);

		btnRefrescar = new MenuButton();
		btnRefrescar.setIcon(new ImageIcon(TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/refresh.png")));
		btnRefrescar.setBounds(835, 455, 50, 30);
		Utilidades.configButtons(btnRefrescar, "");
		btnRefrescar.addActionListener(this);
		contentPanel.add(btnRefrescar);

		btnVolver = new MenuButton();
		btnVolver.setBounds(780, 455, 50, 30);
		btnVolver.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(btnVolver, "");
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);

	}

	private void menuFiltro() {
		peliPanel.setLayout(null);

		// TextField: Nombre
		nombreField = new TextField();
		nombreField.setBounds(780, 50, 160, 45);
		nombreField.setLabelText("Nombre");
		contentPanel.add(nombreField);

		// TextField: Director
		directorField = new TextField();
		directorField.setBounds(780, 100, 160, 45);
		directorField.setLabelText("Director");
		contentPanel.add(directorField);

		// TextField: Guionista
		guionistaField = new TextField();
		guionistaField.setBounds(780, 150, 160, 45);
		guionistaField.setLabelText("Guionista");
		contentPanel.add(guionistaField);

		// TextField: Num Trabajadores
		numTfield = new TextField();
		numTfield.setBounds(780, 200, 160, 45);
		numTfield.setLabelText("Numero de trabajadores");
		contentPanel.add(numTfield);

		JLabel presupuesto = new JLabel("Presupuesto");
		presupuesto.setBounds(780, 250, 160, 25);
		contentPanel.add(presupuesto);

		// ComboBox: presupuesto
		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setBounds(780, 275, 160, 25);
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Mayor a Menor", "Menor a Mayor" }));
		contentPanel.add(comboBox);

		// Boton para buscar
		btnBuscar = new MenuButton();
		btnBuscar.setBounds(780, 320, 160, 35);
		btnBuscar.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/search.png")));
		Utilidades.configButtons(btnBuscar, "");
		btnBuscar.setEnabled(false);
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		contentPanel.add(btnBuscar);
	}

	private JTable tablas(JPanel panel, String obra, JTable table) {

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 740, 440);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		// Crear una tabla
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};

		table.setBackground(new Color(255, 255, 255));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// Añadir los datos a la tabla
		table.setModel(tableModel(obra));
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		panel.add(scrollPane); // Añadir el scroll panel al panel de la pestaña

		return table;
	}

	private DefaultTableModel tableModel(String obra) {
		// Recoger los datos de las pelis/series
		Object[][] data = FactoryDAO.getGetData().dataManage(obra);
		String[] column = null;

		// Inicializar el nombre de las columnas dependiendo de que tabla es
		if (obra.equals(ClasesEnum.PELICULA.getName())) {
			column = new String[] { "ID", "Nombre", "Duracion", "Fecha Estreno", "Presupuesto", "Es Taquillero" };
		} else {
			column = new String[] { "ID", "Nombre", "Duracion", "Fecha Estreno", "Presupuesto", "Temporadas" };
		}

		return new DefaultTableModel(data, column);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBuscar)) {

		} else if (e.getSource().equals(btnAnadir)) {
			int i = OptionPanel.showOptionMessage(thisDialog, "¿Qué tipo de obra audiovisual desea añadir?",
					"Añadir una nueva obra audiovisual", "Pelicula", "Serie", OptionPanel.CONFIRM);
			if (i == 0) {
				dataObra = new DatosObra(thisDialog, true, null, ClasesEnum.SERIE.getName());
			} else {
				dataObra = new DatosObra(thisDialog, true, null, ClasesEnum.PELICULA.getName());
			}

			if (dataObra != null) {
				thisDialog.setVisible(false);
				dataObra.setVisible(true);
			}
		} else if (e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "¿Quieres volver?",
					OptionPanel.CONFIRM);
			if (i == 1) {
				thisDialog.dispose();
				parent.setVisible(true);
			}
		} else if (e.getSource().equals(btnRefrescar)) {
			tablePeli.setModel(tableModel(ClasesEnum.PELICULA.getName()));
			tableSerie.setModel(tableModel(ClasesEnum.SERIE.getName()));
			Utilidades.resizeColumnWidth(tablePeli);
			Utilidades.resizeColumnWidth(tableSerie);

		}

	}
}
