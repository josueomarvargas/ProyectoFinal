package vistas;

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

	/**
	 * Create the dialog.
	 */
	public AddDatosPatrocinador() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("Datos Personales");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblNewLabel.setBounds(100, 11, 262, 29);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre  :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(67, 59, 75, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Cantidad (MIL) :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(36, 95, 118, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Condicion :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(67, 134, 75, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Logo de Patrocinador :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(12, 172, 130, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			textField = new JTextField();
			textField.setBounds(184, 56, 148, 19);
			contentPanel.add(textField);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(184, 92, 148, 19);
			contentPanel.add(textField_1);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(184, 131, 148, 19);
			contentPanel.add(textField_2);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
	}

}
