package vistas.ventanas.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import vistas.dao.GetData;
import vistas.ventanas.data.DatosPatrocinador;

public class TablaPatrocinadores extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnCerrarSystem;
	private int x = 780;
	private int y = 20;

	/**
	 * Create the dialog.
	 */
	public TablaPatrocinadores() {
		this.setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(this);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		tabla();

		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(x, 352, 100, 23);
		contentPanel.add(btnBuscar);

		btnAnadir = new JButton("+");
		btnAnadir.setBounds(x, 386, 42, 23);
		contentPanel.add(btnAnadir);

		btnVolver = new JButton("\u2190");
		btnVolver.setBounds(x, 386, 43, 23);
		contentPanel.add(btnVolver);

		textField = new JTextField();
		textField.setBounds(x, 317, 100, 20);
		contentPanel.add(textField);
		btnVolver.addActionListener(this);
		btnAnadir.addActionListener(this);
		btnBuscar.addActionListener(this);
	}

	private void tabla() {

		// Recoger los datos de los trabajdores
		Object[][] data = FactoryDAO.getGetData().checkInfo(GetData.PATROCINADOR);
		String[] column = new String[] { "ID", "Nombre", "Dinero" };

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 25, 600, 400);

		// Crear una tabla
		table = new JTable();
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
			DatosPatrocinador vPatrocinador = new DatosPatrocinador();
			vPatrocinador.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();
		} else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
	}

}
