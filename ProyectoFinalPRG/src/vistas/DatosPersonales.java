package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;

public class DatosPersonales extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			DatosPersonales dialog = new DatosPersonales();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public DatosPersonales() {
		setBounds(100, 100, 492, 445);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea txtrNombreDeUsuario = new JTextArea();
			txtrNombreDeUsuario.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrNombreDeUsuario.setBackground(SystemColor.menu);
			txtrNombreDeUsuario.setText("Nombre de usuario:");
			txtrNombreDeUsuario.setBounds(36, 56, 111, 22);
			contentPanel.add(txtrNombreDeUsuario);
		}
		{
			JTextArea txtrContrasea = new JTextArea();
			txtrContrasea.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrContrasea.setBackground(SystemColor.menu);
			txtrContrasea.setText("Contrase\u00F1a:");
			txtrContrasea.setBounds(36, 82, 111, 22);
			contentPanel.add(txtrContrasea);
		}
		{
			JTextArea txtrDni = new JTextArea();
			txtrDni.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrDni.setBackground(SystemColor.menu);
			txtrDni.setText("DNI:");
			txtrDni.setBounds(46, 107, 47, 22);
			contentPanel.add(txtrDni);
		}
		{
			JTextArea txtrNombre = new JTextArea();
			txtrNombre.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrNombre.setBackground(SystemColor.menu);
			txtrNombre.setText("Nombre:");
			txtrNombre.setBounds(36, 136, 111, 22);
			contentPanel.add(txtrNombre);
		}
		{
			JTextArea txtrApellido = new JTextArea();
			txtrApellido.setBackground(SystemColor.menu);
			txtrApellido.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrApellido.setText("Apellido:");
			txtrApellido.setBounds(36, 169, 86, 19);
			contentPanel.add(txtrApellido);
		}
		{
			JTextArea txtrNmeroDePremios = new JTextArea();
			txtrNmeroDePremios.setBackground(SystemColor.menu);
			txtrNmeroDePremios.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrNmeroDePremios.setText("N\u00FAmero de premios:");
			txtrNmeroDePremios.setBounds(36, 199, 121, 22);
			contentPanel.add(txtrNmeroDePremios);
		}
		{
			JTextArea txtrDireccion = new JTextArea();
			txtrDireccion.setBackground(SystemColor.menu);
			txtrDireccion.setFont(new Font("Calibri", Font.PLAIN, 12));
			txtrDireccion.setText("Direccion:");
			txtrDireccion.setBounds(36, 228, 111, 22);
			contentPanel.add(txtrDireccion);
		}
		{
			JTextArea txtrDatosPersonales = new JTextArea();
			txtrDatosPersonales.setBackground(SystemColor.menu);
			txtrDatosPersonales.setFont(new Font("Calibri", Font.PLAIN, 28));
			txtrDatosPersonales.setText("Datos Personales");
			txtrDatosPersonales.setBounds(125, 11, 217, 34);
			contentPanel.add(txtrDatosPersonales);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 51, 148, 19);
			contentPanel.add(textArea);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(251, 79, 148, 20);
			contentPanel.add(passwordField);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 139, 148, 19);
			contentPanel.add(textArea);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 164, 148, 19);
			contentPanel.add(textArea);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 194, 148, 19);
			contentPanel.add(textArea);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 223, 148, 19);
			contentPanel.add(textArea);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 107, 148, 19);
			contentPanel.add(textArea);
		}
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(457, 0, 17, 362);
		contentPanel.add(scrollBar);
		
		JTextArea txtrFechaDeNacimiento = new JTextArea();
		txtrFechaDeNacimiento.setText("Fecha de Nacimiento:");
		txtrFechaDeNacimiento.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrFechaDeNacimiento.setBackground(SystemColor.menu);
		txtrFechaDeNacimiento.setBounds(36, 261, 148, 19);
		contentPanel.add(txtrFechaDeNacimiento);
		
		JTextArea txtrFechaDeNacimiento_1 = new JTextArea();
		txtrFechaDeNacimiento_1.setText("N\u00FAmero de Tel\u00E9fono:");
		txtrFechaDeNacimiento_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		txtrFechaDeNacimiento_1.setBackground(SystemColor.menu);
		txtrFechaDeNacimiento_1.setBounds(36, 291, 131, 22);
		contentPanel.add(txtrFechaDeNacimiento_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(251, 253, 148, 19);
		contentPanel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBounds(251, 286, 148, 19);
		contentPanel.add(textArea_1);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Modificar datos");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				{
					JButton btnNewButton = new JButton("Aceptar");
					buttonPane.add(btnNewButton);
				}
				{
					JButton cancelButton = new JButton("Volver");
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
		}
	}
}
