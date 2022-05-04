package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

	/**
	 * Create the dialog.
	 */
	public TablaEquipamiento() {
		setBounds(100, 100, 402, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 385, 142);
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
				textField = new JTextField();
				textField.setBounds(10, 230, 140, 20);
				contentPanel.add(textField);
				textField.setColumns(10);

				btnVolver = new JButton("Volver");
				btnVolver.addActionListener(this);
				btnVolver.setBounds(313, 229, 72, 23);
				contentPanel.add(btnVolver);

				btnAnadir = new JButton("A\u00F1adir");
				btnAnadir.addActionListener(this);
				btnAnadir.setBounds(237, 229, 72, 23);
				contentPanel.add(btnAnadir);

				btnBuscar = new JButton("Buscar");
				btnBuscar.addActionListener(this);
				btnBuscar.setBounds(160, 229, 72, 23);
				contentPanel.add(btnBuscar);
			}
		}
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
	}

}
