package vistas.ventanas.table;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Trabajador;
import vistas.dao.CheckLogin;
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
	private final Window parent;
	private final JDialog thisDialog;
	private final Trabajador trabajador = CheckLogin.getLogin();
	private CustomTab tabs = null;
	private JTable tablePeli = null;
	private JTable tableSerie = null;
	private TextField nombreField, duracion, fechaField, presupuestoField;
	private MenuButton btnAnadir, btnVolver, btnRefrescar;
	private DatosObra dataObra;

	public TablaPeliculasSeries(Window parent, boolean modal) {
		super(parent);
		setModal(modal);
		this.parent = parent;
		thisDialog = this;
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		init();
	}

	private void init() {
		this.setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);

		loadTables();
		buttons();
		menuFiltro();

		tablePeli.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablePeli.getSelectedRow() != -1) {
					int index = tablePeli.getSelectedRow();
					TableModel model = tablePeli.getModel();
					openData(index, model);
				}
			}
		});
		tableSerie.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tableSerie.getSelectedRow() != -1) {
					int index = tableSerie.getSelectedRow();
					TableModel model = tableSerie.getModel();
					openData(index, model);
				}

			}
		});

		switch (trabajador.getTipo()) {
		case "administrador":
		case "director":
			btnAnadir.setEnabled(true);
			btnVolver.setEnabled(true);
			btnRefrescar.setEnabled(true);
			break;
		case "tecnicoaudiovisual":
			btnAnadir.setEnabled(false);
			btnVolver.setEnabled(true);
			btnRefrescar.setEnabled(true);
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
		contentPanel.add(tabs);
		tablePeli = tablas(peliPanel, ClasesEnum.PELICULA.getName(), tablePeli);
		tableSerie = tablas(seriePanel, ClasesEnum.SERIE.getName(), tableSerie);
	}

	private JTable tablas(JPanel panel, String obra, JTable table) {
		panel.setLayout(null);

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 5, 725, 428);
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

	private void tableSort(String obra) {
		JTable table = obra.equalsIgnoreCase(ClasesEnum.PELICULA.getName()) ? tablePeli : tableSerie;
		try {
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel(obra));
			List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
			RowFilter<TableModel, Object> compoundRowFilter = null;

			filters.add(RowFilter.regexFilter("(?i)" + nombreField.getText(), 1));
			filters.add(RowFilter.regexFilter("(?i)" + duracion.getText(), 2));
			filters.add(RowFilter.regexFilter("(?i)" + fechaField.getText(), 3));
			filters.add(RowFilter.regexFilter("(?i)" + presupuestoField.getText(), 4));
			compoundRowFilter = RowFilter.andFilter(filters);
			sorter.setRowFilter(compoundRowFilter);
			table.setRowSorter(sorter);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String selectedTab() {
		return tabs.getSelectedIndex() == 0 ? ClasesEnum.PELICULA.getName() : ClasesEnum.SERIE.getName();
	}

	/**
	 * Abrir ventana con los datos
	 * 
	 * @param model2
	 * @param index2
	 * 
	 **/
	private void openData(int i, TableModel tableModel) {
		int id = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
		ObraAudiovisual oa = (ObraAudiovisual) GetData.getDatos(ClasesEnum.OBRA.getName()).get(id);
		dataObra = new DatosObra(thisDialog, true, oa, null);
		thisDialog.setVisible(false);
		dataObra.setVisible(true);
	}

	private void buttons() {

		btnAnadir = new MenuButton();
		btnAnadir.setBounds(890, 355, 50, 30);
		btnAnadir.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
		Utilidades.configButtons(btnAnadir, "");
		btnAnadir.addActionListener(this);
		contentPanel.add(btnAnadir);

		btnRefrescar = new MenuButton();
		btnRefrescar.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/refresh.png")));
		btnRefrescar.setBounds(835, 355, 50, 30);

		Utilidades.configButtons(btnRefrescar, "");
		btnRefrescar.addActionListener(this);
		contentPanel.add(btnRefrescar);

		btnVolver = new MenuButton();
		btnVolver.setBounds(780, 355, 50, 30);
		btnVolver.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(btnVolver, "");
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);

	}

	private void menuFiltro() {
		// TextField: Nombre
		nombreField = new TextField();
		nombreField.setBounds(780, 100, 160, 45);
		nombreField.setLabelText("Nombre");
		nombreField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort(selectedTab());
			}
		});
		contentPanel.add(nombreField);

		// TextField: Durecion
		duracion = new TextField();
		duracion.setBounds(780, 160, 160, 45);
		duracion.setLabelText("Duracion");
		duracion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort(selectedTab());
			}
		});
		contentPanel.add(duracion);

		// TextField: Fecha
		fechaField = new TextField();
		fechaField.setBounds(780, 210, 160, 45);
		fechaField.setLabelText("Fecha de estreno");
		fechaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort(selectedTab());
			}
		});
		contentPanel.add(fechaField);

		// TextField: Presupuesto
		presupuestoField = new TextField();
		presupuestoField.setBounds(780, 270, 160, 45);
		presupuestoField.setLabelText("Presupuesto");
		presupuestoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort(selectedTab());
			}
		});
		contentPanel.add(presupuestoField);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnadir)) {

			int i = OptionPanel.showOptionMessage(thisDialog, "¿Qué tipo de obra audiovisual desea añadir?",
					"Añadir obra audiovisual", "Pelicula", "Serie", OptionPanel.CONFIRM);
			if (i == 0) {
				dataObra = new DatosObra(thisDialog, true, null, ClasesEnum.SERIE.getName());
			} else {
				dataObra = new DatosObra(thisDialog, true, null, ClasesEnum.PELICULA.getName());
			}

			if (dataObra != null) {
				thisDialog.setVisible(false);
				dataObra.setVisible(true);
			} else {

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
