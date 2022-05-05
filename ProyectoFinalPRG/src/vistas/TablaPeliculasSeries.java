package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.SystemColor;

public class TablaPeliculasSeries extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JPanel panel;
	/**
	 * Create the dialog.
	 */
	public TablaPeliculasSeries() {
		this.setUndecorated(true);
		setBounds(100, 100, 629, 319);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(1, 0, 467, 129);

		table = new JTable();
		table.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
					{null, null, null, null, null, null, null},
				},
				new String[] {
						"ID", "Nombre", "Director", "Guionista", "Presupuesto", "Num.T", "Fecha Estreno"
				}
				));
		table.getColumnModel().getColumn(6).setPreferredWidth(105);
		contentPanel.setLayout(null);
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);

		JLabel lblNewLabel = new JLabel("Nombre:");
		lblNewLabel.setBounds(489, 16, 46, 14);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(489, 30, 99, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Director:");
		lblNewLabel_1.setBounds(489, 52, 46, 14);
		contentPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(489, 64, 99, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Guionista:");
		lblNewLabel_2.setBounds(489, 84, 71, 14);
		contentPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(489, 98, 99, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Num Trabajadores:");
		lblNewLabel_3.setBounds(489, 120, 99, 14);
		contentPanel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(489, 135, 99, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Presupuesto:");
		lblNewLabel_4.setBounds(489, 157, 83, 14);
		contentPanel.add(lblNewLabel_4);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mayor a Menor", "Menor a Mayor"}));
		comboBox.setBounds(489, 171, 99, 22);
		contentPanel.add(comboBox);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Taquillera");
		chckbxNewCheckBox.setBounds(489, 194, 97, 23);
		contentPanel.add(chckbxNewCheckBox);

		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(489, 220, 89, 23);
		contentPanel.add(btnBuscar);

		btnAnadir = new JButton("A\u00F1adir");
		btnAnadir.addActionListener(this);
		btnAnadir.setBounds(414, 254, 89, 23);
		contentPanel.add(btnAnadir);

		btnVolver = new JButton("Volver");
		btnVolver.addActionListener(this);
		btnVolver.setBounds(514, 254, 89, 23);
		contentPanel.add(btnVolver);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(1, 248, 612, 29);
		contentPanel.add(panel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAnadir)) {
			AddPelicula vPelicula=new AddPelicula();
			vPelicula.setVisible(true);
		}
		else if(e.getSource().equals(btnVolver)) {
			this.dispose();

		}
		

	}
}
