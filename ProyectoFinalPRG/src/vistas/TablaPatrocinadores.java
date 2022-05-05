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
import java.awt.SystemColor;

public class TablaPatrocinadores extends JDialog implements ActionListener {

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

	/**
	 * Create the dialog.
	 */
	public TablaPatrocinadores() {
		this.setUndecorated(true);
		setBounds(100, 100, 407, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 397, 137);
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
							{null, null, null, null},
						},
						new String[] {
								"ID", "Nombre", "Cantidad(mill)", "Condicion"
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
		
		panel = new JPanel();
		panel.setBackground(SystemColor.textHighlight);
		panel.setBounds(0, 225, 391, 36);
		contentPanel.add(panel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAnadir)) {	
			AddDatosPatrocinador vPatrocinador= new AddDatosPatrocinador();
			vPatrocinador.setVisible(true);
		}
		else if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		
		}

	}
