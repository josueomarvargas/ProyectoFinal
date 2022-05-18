package vistas.ventanas.table;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.Trabajador;
import vistas.dao.GetData;
import vistas.ventanas.data.DatosPersonal;

public class TablaTrabajadores extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JDialog thisDialog;
	private JTable table;
	private JTextField textField;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public TablaTrabajadores(Window parent) {
		super(parent);
		this.setUndecorated(true);
		thisDialog = this;
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		contentPanel.setLayout(null);

		tabla();

		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);

		textField = new JTextField();
		textField.setBounds(77, 387, 128, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(210, 386, 65, 23);
		contentPanel.add(btnBuscar);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBounds(280, 386, 63, 23);
		contentPanel.add(btnAnadir);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(348, 386, 63, 23);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);
		btnAnadir.addActionListener(this);
		btnBuscar.addActionListener(this);
	}

	private void tabla() {

		// Recoger los datos de los trabajdores
		Object[][] data = FactoryDAO.getGetData().checkInfo(GetData.TRABAJADOR);
		String[] column = new String[] { "ID", "DNI", "Nombre", "Apellido", "Num. Tel", "Num. Premios", "Dirección",
				"Tipo", "Fecha Nacimiento" };

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 475, 380);

		// Crear una tabla
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = table.getSelectedRow();
				TableModel model = table.getModel();
				int id = Integer.parseInt(model.getValueAt(i, 1).toString());
				Trabajador trabajador = (Trabajador) GetData.getDatos(GetData.TRABAJADOR).get(id);
				DatosPersonal dataPersona = new DatosPersonal(thisDialog, true, trabajador);
			}
		});
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(data, column)); // Añadir los datos a la tabla
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		contentPanel.add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnadir)) {
			DatosPersonal vPersonal = new DatosPersonal();
			vPersonal.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();

		} else if (e.getSource().equals(btnCerrarSystem)) {
			this.dispose();
		}
	}

}
