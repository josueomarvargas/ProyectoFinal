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

import javax.swing.DefaultComboBoxModel;
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
import modelo.clases.Equipamiento;
import vistas.dao.GetData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosEquipamiento;

public class TablaEquipamiento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final Window parent;
	private final JDialog thisDialog;
	private JTable table = null;
	private TextField nombreField;
	private TextField tipoField;
	private MenuButton btnRefrescar;
	private MenuButton btnAnadir;
	private MenuButton btnVolver;
	private static DefaultComboBoxModel<String> tipoEquip = new DefaultComboBoxModel<>();
	private DatosEquipamiento datosEquip;

	/**
	 * Create the dialog.
	 */
	public TablaEquipamiento(Window parent, boolean modal) {
		super(parent);
		setModal(modal);
		this.setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		this.parent = parent;
		thisDialog = this;
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		init();
	}

	private void init() {
		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);

		tabla();
		buttons();
		menuFiltro();

		getContentPane().add(contentPanel);

	}

	private void menuFiltro() {

		nombreField = new TextField();
		nombreField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});

		nombreField.setLabelText("Nombre");
		nombreField.setBounds(780, 128, 160, 45);
		contentPanel.add(nombreField);

		tipoField = new TextField();
		tipoField.setLabelText("Filtro");
		tipoField.setBounds(780, 201, 160, 45);
		tipoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				tableSort();
			}
		});
		contentPanel.add(tipoField);

		TableModel tableModel = table.getModel();
		for (int i = 0; i < tableModel.getRowCount(); i++) {
			if (tipoEquip.getIndexOf(tableModel.getValueAt(i, 2)) == -1) {
				tipoEquip.addElement((String) table.getModel().getValueAt(i, 2));
			}
		}
	}

	private void tableSort() {
		try {
			TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel());
			List<RowFilter<TableModel, Object>> filters = new ArrayList<RowFilter<TableModel, Object>>();
			RowFilter<TableModel, Object> compoundRowFilter = null;

			filters.add(RowFilter.regexFilter("(?i)" + nombreField.getText(), 1));
			filters.add(RowFilter.regexFilter("(?i)" + tipoField.getText(), 2));
			compoundRowFilter = RowFilter.andFilter(filters);
			sorter.setRowFilter(compoundRowFilter);
			table.setRowSorter(sorter);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private void buttons() {
		btnAnadir = new MenuButton();
		btnAnadir.setBounds(890, 302, 50, 30);
		btnAnadir.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
		Utilidades.configButtons(btnAnadir, "");
		btnAnadir.setEnabled(true);
		btnAnadir.addActionListener(this);
		contentPanel.add(btnAnadir);

		btnRefrescar = new MenuButton();
		btnRefrescar.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/refresh.png")));
		btnRefrescar.setBounds(835, 302, 50, 30);
		Utilidades.configButtons(btnRefrescar, "");
		btnRefrescar.setEnabled(true);
		btnRefrescar.addActionListener(this);
		contentPanel.add(btnRefrescar);

		btnVolver = new MenuButton();
		btnVolver.setBounds(780, 302, 50, 30);
		btnVolver.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(btnVolver, "");
		btnVolver.setEnabled(true);
		btnVolver.addActionListener(this);
		contentPanel.add(btnVolver);
	}

	private void tabla() {

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(40, 48, 715, 437);

		// Crear una tabla
		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(tableModel()); // Añadir los datos a la tabla
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() != -1) {
					int index = table.getSelectedRow();
					TableModel model = table.getModel();
					openData(index, model);
				}
			}
		});
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		contentPanel.add(scrollPane);
	}

	private DefaultTableModel tableModel() {
		// Recoger los datos de los trabajdores
		Object[][] data = FactoryDAO.getGetData().dataManage(ClasesEnum.EQUIPAMIENTO.getName());
		String[] column = new String[] { "ID", "Nombre", "Tipo" };

		return new DefaultTableModel(data, column);
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
		Equipamiento equip = (Equipamiento) GetData.getDatos(ClasesEnum.EQUIPAMIENTO.getName()).get(id);
		datosEquip = new DatosEquipamiento(thisDialog, true, equip);
		thisDialog.setVisible(false);
		datosEquip.setVisible(true);

	}

	public static DefaultComboBoxModel<String> tipoEquip() {
		return tipoEquip;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource().equals(btnAnadir)) {
			DatosEquipamiento vEquipamiento = new DatosEquipamiento(thisDialog, true, null);
			vEquipamiento.setVisible(true);

		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();
			parent.setVisible(true);
		} else if (e.getSource().equals(btnRefrescar)) {
			table.setModel(tableModel());
			Utilidades.resizeColumnWidth(table); // Redimensionar columnas
			repaint();
		}
	}
}
