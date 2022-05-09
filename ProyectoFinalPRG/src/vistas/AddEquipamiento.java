package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class AddEquipamiento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAadirAUna;
private JButton btnBorrarDatos;
private JButton btnModificarDatos;
private JButton btnAceptar;
private JButton btnVolver;
private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public AddEquipamiento() {
		this.setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextField textField = new JTextField();
			textField.setBounds(187, 67, 197, 23);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(187, 108, 197, 23);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(187, 151, 197, 23);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(187, 213, 197, 46);
			contentPanel.add(textField);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Caractersticas :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(46, 110, 107, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Tipo :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(113, 153, 40, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Logo :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(108, 227, 40, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Nombre :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1_1.setBounds(89, 69, 59, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblDatosEquipamiento = new JLabel("Datos Equipamiento");
			lblDatosEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblDatosEquipamiento.setBounds(118, 27, 266, 29);
			contentPanel.add(lblDatosEquipamiento);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnAadirAUna = new JButton("A\u00F1adir a una obra");
				btnAadirAUna.addActionListener(this);
				btnAadirAUna.setActionCommand("OK");
				buttonPane.add(btnAadirAUna);
			}
			{
				btnBorrarDatos = new JButton("Borrar datos");
				btnBorrarDatos.addActionListener(this);
				btnBorrarDatos.setActionCommand("OK");
				buttonPane.add(btnBorrarDatos);
			}
			{
				btnModificarDatos = new JButton("Modificar datos");
				btnModificarDatos.addActionListener(this);
				btnModificarDatos.setActionCommand("OK");
				buttonPane.add(btnModificarDatos);
			}
			{
				btnAceptar = new JButton("Aceptar");
				btnModificarDatos.addActionListener(this);
				btnAceptar.setActionCommand("OK");
				buttonPane.add(btnAceptar);
				getRootPane().setDefaultButton(btnAceptar);
			}
			{
				btnVolver = new JButton("Volver");
				btnVolver.addActionListener(this);
				btnVolver.setActionCommand("Cancel");
				buttonPane.add(btnVolver);
			}
		}
		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(512, 0, 39, 25);
		contentPanel.add(btnCerrarSystem);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAceptar)) {
			this.dispose();
			
		}
		else if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		
		
	}
}

