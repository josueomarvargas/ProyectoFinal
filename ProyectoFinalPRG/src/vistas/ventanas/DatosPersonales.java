package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JLabel;

public class DatosPersonales extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JButton btnMostrar;
	private JButton btnModificar;
	private JButton btnVolver;
	private JButton btnBorrar;
	private JButton btnAceptar;
	private JTextField textField_1;

	/**
	 * Create the dialog.
	 */
	public DatosPersonales() {
		//this.setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 88, 187, 19);
			contentPanel.add(textField);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(251, 118, 148, 20);
			contentPanel.add(passwordField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 179, 187, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 209, 187, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 272, 187, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 149, 187, 19);
			contentPanel.add(textField);
		}
		{
			JLabel lblNewLabel = new JLabel("Datos Personales");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblNewLabel.setBounds(106, 36, 262, 29);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre de usuario :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(66, 91, 140, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(117, 121, 89, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("DNI :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(166, 152, 40, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(139, 182, 71, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apellido :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(135, 212, 71, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de premios :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(59, 242, 140, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Direcci\u00F3n :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			lblNewLabel_1.setBounds(125, 275, 71, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(86, 272, 95, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			btnMostrar = new JButton("Mostrar");
			btnMostrar.setBounds(414, 117, 79, 23);
			contentPanel.add(btnMostrar);
		}
		
		textField_1 = new JTextField();
		textField_1.setBounds(251, 241, 187, 20);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar datos");
				btnModificar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				{
					btnBorrar = new JButton("Borrar datos");
					btnBorrar.setActionCommand("OK");
					buttonPane.add(btnBorrar);
				}
				btnModificar.setActionCommand("OK");
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
				{
					btnAceptar = new JButton("Aceptar");
					buttonPane.add(btnAceptar);
				}
				{
					btnVolver = new JButton("Volver");
					btnVolver.addActionListener(this);
					btnVolver.setActionCommand("Cancel");
					buttonPane.add(btnVolver);
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
	}
}
