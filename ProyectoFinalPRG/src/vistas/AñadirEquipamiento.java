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
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class AñadirEquipamiento extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AñadirEquipamiento dialog = new AñadirEquipamiento();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AñadirEquipamiento() {
		setBounds(100, 100, 503, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextField textField = new JTextField();
			textField.setBounds(127, 56, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(127, 89, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(127, 122, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(127, 155, 148, 46);
			contentPanel.add(textField);
		}
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(470, 0, 17, 212);
		contentPanel.add(scrollBar);
		{
			JLabel lblNewLabel_1_1 = new JLabel("Caractersticas :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNewLabel_1_1.setBounds(21, 94, 95, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Tipo :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNewLabel_1_1.setBounds(77, 126, 40, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Logo :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNewLabel_1_1.setBounds(77, 158, 40, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblNewLabel_1_1 = new JLabel("Nombre :");
			lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblNewLabel_1_1.setBounds(53, 59, 56, 23);
			contentPanel.add(lblNewLabel_1_1);
		}
		{
			JLabel lblDatosEquipamiento = new JLabel("Datos Equipamiento");
			lblDatosEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblDatosEquipamiento.setBounds(118, 11, 266, 29);
			contentPanel.add(lblDatosEquipamiento);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnAadirAUna = new JButton("A\u00F1adir a una obra");
				btnAadirAUna.setActionCommand("OK");
				buttonPane.add(btnAadirAUna);
			}
			{
				JButton btnBorrarDatos = new JButton("Borrar datos");
				btnBorrarDatos.setActionCommand("OK");
				buttonPane.add(btnBorrarDatos);
			}
			{
				JButton btnModificarDatos = new JButton("Modificar datos");
				btnModificarDatos.setActionCommand("OK");
				buttonPane.add(btnModificarDatos);
			}
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
