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

public class AñadirPelicula extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			AñadirPelicula dialog = new AñadirPelicula();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public AñadirPelicula() {
		setBounds(100, 100, 502, 384);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JTextField textField = new JTextField();
		textField.setBounds(165, 73, 148, 19);
		contentPanel.add(textField);
		
		JTextField textField_1 = new JTextField();
		textField_1.setBounds(165, 103, 43, 19);
		contentPanel.add(textField_1);
		
		JTextField textField_2 = new JTextField();
		textField_2.setBounds(165, 141, 148, 19);
		contentPanel.add(textField_2);
		
		JTextField textField_3 = new JTextField();
		textField_3.setBounds(165, 171, 148, 19);
		contentPanel.add(textField_3);
		
		JTextField textField_1_1 = new JTextField();
		textField_1_1.setBounds(342, 96, 43, 19);
		contentPanel.add(textField_1_1);
		
		JButton btnVerPatrocinadores = new JButton("Ver patrocinadores");
		btnVerPatrocinadores.setActionCommand("OK");
		btnVerPatrocinadores.setBounds(305, 276, 123, 23);
		contentPanel.add(btnVerPatrocinadores);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(469, 0, 17, 312);
		contentPanel.add(scrollBar);
		
		JLabel lblNewLabel_1_1 = new JLabel("Nombre :");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(79, 72, 56, 23);
		contentPanel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Duraci\u00F3n (min)  :");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_1.setBounds(43, 106, 93, 23);
		contentPanel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Fecha de estreno :");
		lblNewLabel_1_1_2.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_2.setBounds(31, 140, 104, 23);
		contentPanel.add(lblNewLabel_1_1_2);
		
		JLabel lblNewLabel_1_1_3 = new JLabel("Tipo :");
		lblNewLabel_1_1_3.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_3.setBounds(99, 174, 36, 23);
		contentPanel.add(lblNewLabel_1_1_3);
		
		JLabel lblNewLabel_1_1_4 = new JLabel("Portada :");
		lblNewLabel_1_1_4.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_4.setBounds(80, 221, 56, 23);
		contentPanel.add(lblNewLabel_1_1_4);
		
		JLabel lblNewLabel_1_1_5 = new JLabel("Presupuesto (Mill) :");
		lblNewLabel_1_1_5.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1_5.setBounds(227, 99, 116, 23);
		contentPanel.add(lblNewLabel_1_1_5);
		
		JLabel lblDatosPelcula = new JLabel("Datos Pel\u00EDcula ");
		lblDatosPelcula.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblDatosPelcula.setBounds(131, 11, 171, 36);
		contentPanel.add(lblDatosPelcula);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			
			JButton btnBorrarDatos = new JButton("Borrar datos");
			btnBorrarDatos.setActionCommand("OK");
			buttonPane.add(btnBorrarDatos);
			
			JButton btnModificar = new JButton("Modificar datos");
			btnModificar.setActionCommand("OK");
			buttonPane.add(btnModificar);
			
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.setActionCommand("OK");
			buttonPane.add(btnAceptar);
			{
				JButton okButton = new JButton("Volver");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
