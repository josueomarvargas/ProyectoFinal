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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JLabel;

public class AddPelicula extends JDialog implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnBorrarDatos;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnVolver; 
	private JButton btnVerPatrocinadores;
	/**
	 * Create the dialog.
	 */
	public AddPelicula() {
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

		btnVerPatrocinadores = new JButton("Ver patrocinadores");
		btnVerPatrocinadores.addActionListener(this);
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

			btnBorrarDatos = new JButton("Borrar datos");
			btnBorrarDatos.addActionListener(this);
			btnBorrarDatos.setActionCommand("OK");
			buttonPane.add(btnBorrarDatos);

			btnModificar = new JButton("Modificar datos");
			btnModificar.addActionListener(this);
			btnModificar.setActionCommand("OK");
			buttonPane.add(btnModificar);

			btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(this);
			btnAceptar.setActionCommand("OK");
			buttonPane.add(btnAceptar);
			{
				btnVolver = new JButton("Volver");
				btnVolver.addActionListener(this);
				btnVolver.setActionCommand("OK");
				buttonPane.add(btnVolver);
				getRootPane().setDefaultButton(btnVolver);
			}
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBorrarDatos)) {
			this.dispose();
		}
		else if(e.getSource().equals(btnModificar)) {
			this.dispose();
		}
		else if(e.getSource().equals(btnVolver)){
			this.dispose();
		}
		else if(e.getSource().equals(btnVerPatrocinadores)) {
			TablaPatrocinadores vPatrocinadores= new TablaPatrocinadores();
			vPatrocinadores.setVisible(true);
		}
		else if(e.getSource().equals(btnAceptar)) {
			GestionDatos vGDatos= new GestionDatos();
			vGDatos.setVisible(true);
		}
	}
}
