package vistas.ventanas.table;

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
import modelo.clases.Trabajador;
import modelo.clases.Usuario;
import vistas.dao.GetData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosPersonal;

public class TablaTrabajadores extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JDialog thisDialog;
	private Window parent;
	private JTable table;
	private TextField nombreField;
	private TextField apellidoField;
	private TextField dniField;
	private TextField fechaField;
	private MenuButton btnAnadir;
	private MenuButton btnVolver;
	private MenuButton btnRefrescar;

	/**
	 * Create the dialog.
	 */
	public TablaTrabajadores(Window parent, boolean modal) {
		super(parent);
		setModal(true);
		this.setUndecorated(true);
		this.parent = parent;
		thisDialog = this;
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		contentPanel.setLayout(null);

		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);

		tabla();

		// TextField: Nombre
		nombreField = new TextField();
		nombreField.setBounds(780, 90, 160, 45);
		nombreField.setLabelText("Nombre");
		nombreField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});
		contentPanel.add(nombreField);

		// TextField: Director
		apellidoField = new TextField();
		apellidoField.setBounds(780, 146, 160, 45);
		apellidoField.setLabelText("Apellido\r\n");
		apellidoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});
		contentPanel.add(apellidoField);

		// TextField: Guionista
		dniField = new TextField();
		dniField.setBounds(780, 202, 160, 45);
		dniField.setLabelText("DNI");
		dniField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});
		contentPanel.add(dniField);

		// TextField: Guionista
		fechaField = new TextField();
		fechaField.setBounds(780, 258, 160, 45);
		fechaField.setLabelText("Fecha de nacimiento");
		fechaField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});
		contentPanel.add(fechaField);

		btnAnadir = new MenuButton();
		btnAnadir.setEnabled(true);
		btnAnadir.setBounds(890, 355, 50, 30);
		btnAnadir.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
		Utilidades.configButtons(btnAnadir, "");
		btnAnadir.addActionListener(this);
		contentPanel.add(btnAnadir);

		btnRefrescar = new MenuButton();
		btnRefrescar.setEnabled(true);
		btnRefrescar.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/refresh.png")));
		btnRefrescar.setBounds(835, 355, 50, 30);
		Utilidades.configButtons(btnRefrescar, "");
		btnRefrescar.addActionListener(this);
		contentPanel.add(btnRefrescar);

		btnVolver = new MenuButton();
		btnVolver.setEnabled(true);
		btnVolver.setBounds(780, 355, 50, 30);
		btnVolver.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(btnVolver, "");
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);

		getContentPane().add(contentPanel);
	}

	private void tableSort() {

		try {
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel());
			List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
			RowFilter<TableModel, Object> compoundRowFilter = null;

			filters.add(RowFilter.regexFilter("(?i)" + dniField.getText(), 1));
			filters.add(RowFilter.regexFilter("(?i)" + nombreField.getText(), 2));
			filters.add(RowFilter.regexFilter("(?i)" + apellidoField.getText(), 3));
			filters.add(RowFilter.regexFilter("(?i)" + fechaField.getText(), 8));
			compoundRowFilter = RowFilter.andFilter(filters);
			sorter.setRowFilter(compoundRowFilter);
			table.setRowSorter(sorter);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private DefaultTableModel tableModel() {
		// Recoger los datos de los trabajdores
		Object[][] data = FactoryDAO.getGetData().dataManage(ClasesEnum.TRABAJADOR.getName());
		String[] column = new String[] { "ID", "DNI", "Nombre", "Apellido", "Num. Tel", "Num. Premios", "Dirección",
				"Tipo", "Fecha Nacimiento" };
		return new DefaultTableModel(data, column);

	}

	private void tabla() {

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 25, 743, 504);

		// Crear una tabla
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				int id = Integer.parseInt(model.getValueAt(i, 0).toString());
				Trabajador trabajador = (Trabajador) GetData.getDatos(ClasesEnum.TRABAJADOR.getName()).get(id);
				Usuario user = (Usuario) FactoryDAO.getUsuario().search(new String[] { Integer.toString(id) });
				DatosPersonal dataPersona = new DatosPersonal(thisDialog, true, trabajador, user);
				dataPersona.setVisible(true);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tableModel()); // Añadir los datos a la tabla
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		contentPanel.add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnadir)) {
			DatosPersonal vPersonal = new DatosPersonal(thisDialog, true, null, null);
			thisDialog.dispose();
			vPersonal.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();
			parent.setVisible(true);

		} else if (e.getSource().equals(btnRefrescar)) {
			table.setModel(tableModel());
			Utilidades.resizeColumnWidth(table);
		}
	}

}
