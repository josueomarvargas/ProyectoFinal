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
import java.awt.Font;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Component;
//import com.toedter.calendar.JDateChooser;

public class AddDatosPersonal extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnVolver;
	private JButton btnBorrarDatos;
	private JButton btnCerrarSystem;

	/**
	 * Create the dialog.
	 */
	public AddDatosPersonal() {
		
		
		this.setUndecorated(true);
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
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
			JTextField textField = new JTextField();
			textField.setBounds(251, 239, 148, 19);
			contentPanel.add(textField);
		}
		{
			JTextField textField = new JTextField();
			textField.setBounds(251, 119, 148, 19);
			contentPanel.add(textField);
		}


		{
			JLabel lblNewLabel = new JLabel("Datos Personales");
			lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblNewLabel.setBounds(137, 18, 262, 29);
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
			JButton btnMostrar = new JButton("Mostrar");
			btnMostrar.setBounds(340, 88, 79, 23);
			contentPanel.add(btnMostrar);
		}

		JLabel lblNewLabel_1 = new JLabel("Fecha nacimiento :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1.setBounds(93, 274, 111, 19);
		contentPanel.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel("Num Tel :");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1_1.setBounds(145, 304, 64, 19);
		contentPanel.add(lblNewLabel_1_1);

		textField_2 = new JTextField();
		textField_2.setBounds(251, 301, 148, 19);
		contentPanel.add(textField_2);

		textField_3 = new JTextField();
		textField_3.setBounds(251, 209, 148, 19);
		contentPanel.add(textField_3);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("DIRECTOR");
		rdbtnNewRadioButton.setBounds(150, 346, 109, 23);
		contentPanel.add(rdbtnNewRadioButton);

		JRadioButton rdbtnActor = new JRadioButton("ACTOR");
		rdbtnActor.setBounds(150, 376, 109, 23);
		contentPanel.add(rdbtnActor);

		JRadioButton rdbtnGuionista = new JRadioButton("GUIONISTA");
		rdbtnGuionista.setBounds(290, 346, 109, 23);
		contentPanel.add(rdbtnGuionista);

		JRadioButton rdbtnTecnicoAudiovisual = new JRadioButton("TECNICO AUDIOVISUAL");
		rdbtnTecnicoAudiovisual.setBounds(290, 376, 148, 23);
		contentPanel.add(rdbtnTecnicoAudiovisual);

		JLabel lblNewLabel_1_2 = new JLabel("Categoria :");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1_2.setBounds(29, 413, 79, 19);
		contentPanel.add(lblNewLabel_1_2);

		textField_4 = new JTextField();
		textField_4.setBounds(29, 432, 128, 20);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tipo Guion :");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1_1_1.setBounds(236, 406, 88, 19);
		contentPanel.add(lblNewLabel_1_1_1);

		JRadioButton rdbtnAccion = new JRadioButton("Acci\u00F3n ");
		rdbtnAccion.setBounds(221, 431, 79, 23);
		contentPanel.add(rdbtnAccion);

		JRadioButton rdbtnMisterio = new JRadioButton("Misterio");
		rdbtnMisterio.setBounds(221, 457, 79, 23);
		contentPanel.add(rdbtnMisterio);

		JRadioButton rdbtnCienciaFiccion = new JRadioButton("Ciencia Ficci\u00F3n");
		rdbtnCienciaFiccion.setBounds(307, 431, 109, 23);
		contentPanel.add(rdbtnCienciaFiccion);

		JRadioButton rdbtnMiedo = new JRadioButton("Miedo");
		rdbtnMiedo.setBounds(307, 457, 101, 23);
		contentPanel.add(rdbtnMiedo);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Especialidad :");
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1_1_1_1.setBounds(29, 477, 88, 19);
		contentPanel.add(lblNewLabel_1_1_1_1);

		JRadioButton rdbtnAccion_1 = new JRadioButton("Acci\u00F3n ");
		rdbtnAccion_1.setBounds(16, 500, 64, 23);
		contentPanel.add(rdbtnAccion_1);

		JRadioButton rdbtnDoble = new JRadioButton("Doble");
		rdbtnDoble.setBounds(16, 526, 69, 23);
		contentPanel.add(rdbtnDoble);

		JRadioButton rdbtnDoblaje = new JRadioButton("Doblaje");
		rdbtnDoblaje.setBounds(86, 500, 109, 23);
		contentPanel.add(rdbtnDoblaje);

		JRadioButton rdbtnComedia = new JRadioButton("Comedia");
		rdbtnComedia.setBounds(86, 526, 109, 23);
		contentPanel.add(rdbtnComedia);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Area Trabajo :");
		lblNewLabel_1_1_1_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		lblNewLabel_1_1_1_2.setBounds(236, 487, 88, 19);
		contentPanel.add(lblNewLabel_1_1_1_2);

		JRadioButton rdbtnAccion_1_1 = new JRadioButton("Coordinador");
		rdbtnAccion_1_1.setBounds(236, 500, 94, 23);
		contentPanel.add(rdbtnAccion_1_1);

		JRadioButton rdbtnAccion_1_2 = new JRadioButton("Audio");
		rdbtnAccion_1_2.setBounds(236, 530, 64, 23);
		contentPanel.add(rdbtnAccion_1_2);

		JRadioButton rdbtnAccion_1_3 = new JRadioButton("Camara");
		rdbtnAccion_1_3.setBounds(335, 500, 64, 23);
		contentPanel.add(rdbtnAccion_1_3);

		JRadioButton rdbtnAccion_1_4 = new JRadioButton("Efectos especiales");
		rdbtnAccion_1_4.setBounds(335, 526, 118, 23);
		contentPanel.add(rdbtnAccion_1_4);
		
				JScrollBar scrollBar = new JScrollBar(JScrollBar.VERTICAL);
				scrollBar.setMaximum(1000);
				scrollBar.setAutoscrolls(true);
				scrollBar.setAlignmentX(Component.RIGHT_ALIGNMENT);
				scrollBar.setAlignmentY(Component.TOP_ALIGNMENT);
				scrollBar.setBounds(530, 25, 20, 362);
				contentPanel.add(scrollBar);

		//	JDateChooser dateChooser = new JDateChooser();
		//dateChooser.setBounds(251, 273, 148, 20);
		//	contentPanel.add(dateChooser);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnModificar = new JButton("Modificar datos");
				{
					btnBorrarDatos = new JButton("Borrar datos");
					btnBorrarDatos.addActionListener(this);
					btnBorrarDatos.setActionCommand("OK");
					buttonPane.add(btnBorrarDatos);
				}
				btnModificar.setActionCommand("OK");
				btnModificar.addActionListener(this);
				buttonPane.add(btnModificar);
				getRootPane().setDefaultButton(btnModificar);
				{
					btnAceptar = new JButton("Aceptar");
					btnAceptar.addActionListener(this);
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
		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(512, 0, 39, 25);
		contentPanel.add(btnCerrarSystem);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		if(e.getSource().equals(btnModificar)) {
			int resp =JOptionPane.showConfirmDialog(null,"¿Quieres Modificar Los Datos?","Alerta!",JOptionPane.YES_NO_OPTION);
			if (resp==0) {
		        JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "Titulo", JOptionPane.DEFAULT_OPTION);
				
				
			}
			else if(e.getSource().equals(btnAceptar)){
				GestionDatos vGDatos = new GestionDatos();
				
			}
		}
	}
}
