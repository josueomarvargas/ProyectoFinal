package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
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
	private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public TablaPeliculasSeries() {
		this.setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(7, 31, 400, 230);

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
		lblNewLabel.setBounds(417, 79, 46, 14);
		contentPanel.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(417, 104, 99, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Director:");
		lblNewLabel_1.setBounds(417, 128, 46, 14);
		contentPanel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(417, 147, 99, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Guionista:");
		lblNewLabel_2.setBounds(417, 171, 71, 14);
		contentPanel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(419, 186, 99, 20);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Num Trabajadores:");
		lblNewLabel_3.setBounds(419, 212, 99, 14);
		contentPanel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setBounds(419, 229, 99, 20);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Presupuesto:");
		lblNewLabel_4.setBounds(419, 260, 83, 14);
		contentPanel.add(lblNewLabel_4);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Mayor a Menor", "Menor a Mayor"}));
		comboBox.setBounds(419, 275, 99, 22);
		contentPanel.add(comboBox);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Taquillera");
		chckbxNewCheckBox.setBounds(421, 304, 97, 23);
		contentPanel.add(chckbxNewCheckBox);

		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(512, 0, 39, 25);
		contentPanel.add(btnCerrarSystem);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(this);
		btnBuscar.setBounds(421, 340, 89, 23);
		contentPanel.add(btnBuscar);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 391, 550, 29);
		contentPanel.add(panel);
		
				btnAnadir = new JButton("A\u00F1adir");
				panel.add(btnAnadir);
				
						btnVolver = new JButton("Volver");
						panel.add(btnVolver);
						btnVolver.addActionListener(this);
				btnAnadir.addActionListener(this);
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
