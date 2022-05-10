package vistas;

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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.SystemColor;

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
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBackground(SystemColor.activeCaption);
			scrollPane.setBounds(10, 47, 499, 265);
			contentPanel.add(scrollPane);
			{
				table = new JTable();
				table.setModel(new DefaultTableModel(
						new Object[][] {
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
							{null, null, null, null},
						},
						new String[] {
								"ID", "Nombre", "Tipo", "Caracter\u00EDstica"
						}
						));
				scrollPane.setViewportView(table);
			}
			btnCerrarSystem = new JButton("X");
			btnCerrarSystem.setForeground(Color.RED);
			btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
			btnCerrarSystem.setBounds(496, 0, 55, 29);
			btnCerrarSystem.addActionListener(this);
			contentPanel.add(btnCerrarSystem);
		}
		
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
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAnadir)) {
			AddEquipamiento vEquipamiento= new AddEquipamiento();
			vEquipamiento.setVisible(true);

		}
		else if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
	}

}