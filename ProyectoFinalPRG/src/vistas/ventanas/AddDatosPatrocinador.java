package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;

public class AddDatosPatrocinador extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton btnBorrarDatos;
	private JButton btnModificarDatos;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public AddDatosPatrocinador() {
		setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Datos Personales");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblNewLabel.setBounds(149, 27, 262, 29);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre  :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(51, 87, 75, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad (MIL) :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(51, 145, 118, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Condicion :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(51, 207, 75, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Logo de Patrocinador :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(51, 269, 152, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			textField.setBounds(236, 80, 205, 29);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(236, 138, 205, 29);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(236, 200, 205, 29);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnBorrarDatos = new JButton("Borrar datos");
				btnBorrarDatos.setBackground(new Color(255, 255, 255));
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
				btnAceptar.addActionListener(this);
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
		
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		else if(e.getSource().equals(btnAceptar)) {
			this.dispose();
		}
		else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
	}

}
