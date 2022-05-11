package vistas.ventanas.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controlador.utils.dao.GenericFactory;
import vistas.ventanas.data.DatosPelicula;

import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class TablaPeliculasSeries extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPanel peliPanel = new JPanel();
	private JPanel seriePanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnAceptar;

	/**
	 * Create the dialog.
	 */
	public TablaPeliculasSeries() {
		this.setUndecorated(false);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 420);
		contentPanel.setLayout(null);
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JTabbedPane tabs = new JTabbedPane();
		tabs.add("Pelicula", peliPanel);
		tabs.add("Serie", seriePanel);
		peliPanel.setLayout(null);
		seriePanel.setLayout(null);
		getContentPane().add(tabs);

		buttons(peliPanel);
		buttons(seriePanel);
		menuFiltro(peliPanel);
		menuFiltro(seriePanel);
		tablas(peliPanel, "peli");
		tablas(seriePanel, "serie");
	}

	private void buttons(JPanel panel) {

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(18, 360, 100, 23);
		panel.add(btnAceptar);
		btnAceptar.addActionListener(this);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.setBounds(299, 360, 100, 23);
		panel.add(btnAnadir);
		btnAnadir.addActionListener(this);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(432, 360, 100, 23);
		panel.add(btnVolver);
		btnVolver.addActionListener(this);

	}

	private void menuFiltro(JPanel panel) {
		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(417, 79, 46, 14);
		panel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(417, 104, 99, 20);
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Director:");
		lblNewLabel_1.setBounds(417, 128, 46, 14);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(417, 147, 99, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Guionista:");
		lblNewLabel_2.setBounds(417, 171, 71, 14);
		contentPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(419, 186, 99, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Num Trabajadores:");
		lblNewLabel_3.setBounds(419, 212, 99, 14);
		contentPanel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(419, 229, 99, 20);
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Presupuesto:");
		lblNewLabel_4.setBounds(419, 260, 83, 14);
		panel.add(lblNewLabel_4);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Mayor a Menor", "Menor a Mayor" }));
		comboBox.setBounds(419, 275, 99, 22);
		panel.add(comboBox);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(421, 321, 95, 23);
		panel.add(btnBuscar);
	}

	private void tablas(JPanel panel, String obra) {

		Object[][] data = (Object[][]) GenericFactory.GETDATA.getUIcontroller().check(Arrays.asList(obra));
		String[] column = null;

		if (obra.equals("peli")) {
			column = new String[] { "ID", "Nombre", "Director", "Guionista", "Num.T", "Presupuesto", "Fecha Estreno",
					"Es Taquillero" };
		} else {
			column = new String[] { "ID", "Nombre", "Director", "Guionista", "Num.T", "Presupuesto", "Fecha Estreno",
					"Temporadas", "Capitulos" };
		}

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 31, 400, 313);
		table = new JTable();
		table.setModel(new DefaultTableModel(data, column));
		table.getColumnModel().getColumn(6).setPreferredWidth(105);
		scrollPane.setViewportView(table);
		panel.add(scrollPane);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnadir)) {
			DatosPelicula vPelicula = new DatosPelicula();
			vPelicula.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();

		}

	}
}
