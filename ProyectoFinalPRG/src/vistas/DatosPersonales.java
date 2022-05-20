package vistas;

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
		setBounds(100, 100, 492, 363);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 58, 148, 19);
			contentPanel.add(textField);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(251, 88, 79, 20);
			contentPanel.add(passwordField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 149, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 179, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextArea textArea = new JTextArea();
			textArea.setBounds(251, 209, 148, 19);
			contentPanel.add(textArea);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 239, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 119, 148, 19);
			contentPanel.add(textField);
		}
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(457, 0, 17, 291);
		contentPanel.add(scrollBar);
		{
			JLabel lblNewLabel = new JLabel("Datos Personales");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblNewLabel.setBounds(104, 11, 262, 29);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre de usuario :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(86, 62, 118, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(130, 91, 79, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("DNI :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(171, 124, 39, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Nombre :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(150, 154, 54, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Apellido :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(145, 184, 64, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("N\u00FAmero de premios :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(86, 212, 118, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("Direcci\u00F3n :");
			lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
			lblNewLabel_1.setBounds(140, 244, 64, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setBounds(86, 272, 95, 19);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JButton btnMostrar = new JButton("Mostrar");
			btnMostrar.setBounds(340, 88, 79, 23);
			contentPanel.add(btnMostrar);
		}
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
				{
					JButton btnBorrarDatos = new JButton("Borrar datos");
					btnBorrarDatos.setActionCommand("OK");
					buttonPane.add(btnBorrarDatos);
				}
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
