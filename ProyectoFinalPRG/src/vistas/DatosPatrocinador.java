package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Font;

public class DatosPatrocinador extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JTable table_2;



	/**
	 * Create the dialog.
	 */
	public DatosPatrocinador() {
		this.setUndecorated(true);
		getContentPane().setBackground(SystemColor.activeCaption);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 517, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		table = new JTable();
		table.setBounds(31, 54, 15, -21);
		contentPanel.add(table);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setBounds(0, 387, 550, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		table_2 = new JTable();
		table_2.setFont(new Font("Sitka Small", Font.BOLD, 17));
		table_2.setForeground(SystemColor.textText);
		table_2.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"ID", "Nombre", "Cantidad (MILL)", "Condici\u00F3n"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_2.getColumnModel().getColumn(0).setPreferredWidth(51);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(88);
		table_2.getColumnModel().getColumn(3).setPreferredWidth(149);
		table_2.setBounds(10, 79, 517, 112);
		getContentPane().add(table_2);
	}
}
