package vistas.ventanas.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.utils.dao.GenericFactory;
import controlador.utils.views.Utilidades;
import vistas.dao.GetData;
import vistas.ventanas.data.DatosEquipamiento;

public class TablaEquipamiento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JPanel panel;
	private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public TablaEquipamiento() {
		this.setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		tabla();

		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);

		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 382, 550, 38);
		contentPanel.add(panel);
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);

		btnBuscar = new JButton("Buscar");
		panel.add(btnBuscar);

		btnAnadir = new JButton("A\u00F1adir");
		panel.add(btnAnadir);

		btnVolver = new JButton("Volver");
		panel.add(btnVolver);
		btnVolver.addActionListener(this);
		btnAnadir.addActionListener(this);
		btnBuscar.addActionListener(this);
	}

	private void tabla() {

		// Recoger los datos de los trabajdores
		Object[][] data = (Object[][]) GenericFactory.GETDATA.getUIcontroller().check(GetData.EQUIPAMIENTO);
		String[] column = new String[] { "ID", "Nombre", "Tipo" };

		// Scroll panel
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 0, 475, 380);

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
			DatosEquipamiento vEquipamiento = new DatosEquipamiento();
			vEquipamiento.setVisible(true);

		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();
		} else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
	}

}
