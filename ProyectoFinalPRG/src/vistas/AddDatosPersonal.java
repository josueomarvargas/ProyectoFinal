package vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import java.awt.SystemColor;
import java.awt.Font;
import java.awt.ScrollPane;

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
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
	private JScrollPane scrollPane;
	private ButtonGroup grupo1;
	private ButtonGroup grupoGuion;
	private ButtonGroup grupoEspecialidad;
	private ButtonGroup grupoAreaTrabajo;
	private JPanel panelDirector;
	private JPanel panelActor;
	private JPanel panelGuionista;
	private JPanel panelAudiovisual;





	/**
	 * Create the dialog.
	 */
	public AddDatosPersonal() {
		scrollPane= new JScrollPane();
		scrollPane.setBounds(5,10,100,150);

		//this.setUndecorated(true);
		setBounds(100, 100, 550, 677);
		//getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		//getContentPane().add(contentPanel, BorderLayout.CENTER);
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


		grupo1=new ButtonGroup();


		JRadioButton rdbDirector = new JRadioButton("DIRECTOR");
		rdbDirector.addActionListener(this);
		rdbDirector.setBounds(150, 346, 109, 23);
		contentPanel.add(rdbDirector);
		grupo1.add(rdbDirector);

		JRadioButton rdbtnActor = new JRadioButton("ACTOR");
		rdbtnActor.setBounds(150, 376, 109, 23);
		contentPanel.add(rdbtnActor);
		grupo1.add(rdbtnActor);
		JRadioButton rdbtnGuionista = new JRadioButton("GUIONISTA");
		rdbtnGuionista.setBounds(290, 346, 109, 23);
		contentPanel.add(rdbtnGuionista);
		grupo1.add(rdbtnGuionista);
		JRadioButton rdbtnTecnicoAudiovisual = new JRadioButton("TECNICO AUDIOVISUAL");
		rdbtnTecnicoAudiovisual.setBounds(290, 376, 148, 23);
		contentPanel.add(rdbtnTecnicoAudiovisual);
		grupo1.add(rdbtnTecnicoAudiovisual);
		grupoGuion= new ButtonGroup();
		grupoEspecialidad= new ButtonGroup();
		grupoAreaTrabajo=new ButtonGroup();

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
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);
		contentPanel.setPreferredSize(new Dimension(510,610));
		scrollPane.setViewportView(contentPanel);

		panelAudiovisual=new JPanel();
		contentPanel.add(panelAudiovisual);
		panelAudiovisual.setBackground(Color.CYAN);
		panelAudiovisual.setBounds(160,420,278,98);
		panelAudiovisual.setLayout(null);														

		JLabel lblNewLabel_1_1_1_2 = new JLabel("Area Trabajo :");
		lblNewLabel_1_1_1_2.setBounds(10, 11, 88, 19);
		panelAudiovisual.add(lblNewLabel_1_1_1_2);
		lblNewLabel_1_1_1_2.setFont(new Font("Calibri", Font.PLAIN, 12));

		JRadioButton rdbtnAccion_1_1 = new JRadioButton("Coordinador");
		rdbtnAccion_1_1.setBounds(20, 37, 94, 23);
		panelAudiovisual.add(rdbtnAccion_1_1);
		grupoAreaTrabajo.add(rdbtnAccion_1_1);

		JRadioButton rdbtnAccion_1_3 = new JRadioButton("Camara");
		rdbtnAccion_1_3.setBounds(135, 37, 64, 23);
		panelAudiovisual.add(rdbtnAccion_1_3);
		grupoAreaTrabajo.add(rdbtnAccion_1_3);
		JRadioButton rdbtnAccion_1_2 = new JRadioButton("Audio");
		rdbtnAccion_1_2.setBounds(20, 68, 64, 23);
		panelAudiovisual.add(rdbtnAccion_1_2);
		grupoAreaTrabajo.add(rdbtnAccion_1_2);

		JRadioButton rdbtnAccion_1_4 = new JRadioButton("Efectos especiales");
		rdbtnAccion_1_4.setBounds(135, 63, 118, 23);
		panelAudiovisual.add(rdbtnAccion_1_4);
		grupoAreaTrabajo.add(rdbtnAccion_1_4);
		rdbDirector.setSelected(true);


		panelDirector=new JPanel();
		panelDirector.setBounds(164, 420, 148, 79);
		contentPanel.add(panelDirector);
		panelDirector.setBackground(Color.CYAN);
		panelDirector.setLayout(null);
		panelDirector.setVisible(false);
		JLabel lblNewLabel_1_2 = new JLabel("Categoria :");
		lblNewLabel_1_2.setBounds(22, 11, 79, 19);
		panelDirector.add(lblNewLabel_1_2);
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.PLAIN, 12));
		panelDirector.add(lblNewLabel_1_2);

		textField_4 = new JTextField();
		textField_4.setBounds(10, 41, 128, 20);
		panelDirector.add(textField_4);
		textField_4.setColumns(10);

		panelActor=new JPanel();
		panelActor.setBounds(142, 420, 244, 98);
		contentPanel.add(panelActor);
		panelActor.setBackground(Color.CYAN);
		panelActor.setLayout(null);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("Especialidad :");
		lblNewLabel_1_1_1_1.setBounds(22, 11, 88, 19);
		panelActor.add(lblNewLabel_1_1_1_1);
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));

		JRadioButton rdbtnAccion_1 = new JRadioButton("Acci\u00F3n ");
		rdbtnAccion_1.setBounds(22, 37, 64, 23);
		panelActor.add(rdbtnAccion_1);
		grupoEspecialidad.add(rdbtnAccion_1);

		JRadioButton rdbtnDoblaje = new JRadioButton("Doblaje");
		rdbtnDoblaje.setBounds(129, 37, 109, 23);
		panelActor.add(rdbtnDoblaje);
		grupoEspecialidad.add(rdbtnDoblaje);

		JRadioButton rdbtnDoble = new JRadioButton("Doble");
		rdbtnDoble.setBounds(22, 63, 69, 23);
		panelActor.add(rdbtnDoble);
		grupoEspecialidad.add(rdbtnDoble);

		JRadioButton rdbtnComedia = new JRadioButton("Comedia");
		rdbtnComedia.setBounds(129, 63, 109, 23);
		panelActor.add(rdbtnComedia);
		grupoEspecialidad.add(rdbtnComedia);

		panelGuionista=new JPanel();
		panelGuionista.setBounds(155, 420, 244, 98);
		contentPanel.add(panelGuionista);
		panelGuionista.setBackground(Color.CYAN);
		panelGuionista.setLayout(null);

		JLabel lblNewLabel_1_1_1 = new JLabel("Tipo Guion :");
		lblNewLabel_1_1_1.setBounds(20, 11, 88, 19);
		panelGuionista.add(lblNewLabel_1_1_1);
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.PLAIN, 12));
		JRadioButton rdbtnAccion = new JRadioButton("Acci\u00F3n ");
		rdbtnAccion.setBounds(6, 37, 79, 23);
		panelGuionista.add(rdbtnAccion);
		grupoGuion.add(rdbtnAccion);
		JRadioButton rdbtnCienciaFiccion = new JRadioButton("Ciencia Ficci\u00F3n");
		rdbtnCienciaFiccion.setBounds(104, 37, 109, 23);
		panelGuionista.add(rdbtnCienciaFiccion);
		grupoGuion.add(rdbtnCienciaFiccion);

		JRadioButton rdbtnMisterio = new JRadioButton("Misterio");
		rdbtnMisterio.setBounds(10, 68, 79, 23);
		panelGuionista.add(rdbtnMisterio);
		grupoGuion.add(rdbtnMisterio);
		JRadioButton rdbtnMiedo = new JRadioButton("Miedo");
		rdbtnMiedo.setBounds(104, 68, 101, 23);
		panelGuionista.add(rdbtnMiedo);
		grupoGuion.add(rdbtnMiedo);
		if(rdbDirector.isSelected()) {
			panelDirector.setVisible(true);
			panelActor.setVisible(false);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(false);
			rdbtnActor.setSelected(false);
			rdbtnGuionista.setSelected(false);
			rdbtnTecnicoAudiovisual.setSelected(false);
		}
		else if(rdbtnActor.isSelected()) {
			panelDirector.setVisible(false);
			panelActor.setVisible(true);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(false);
			rdbDirector.setSelected(false);
			rdbtnGuionista.setSelected(false);
			rdbtnTecnicoAudiovisual.setSelected(false);
		}
		else if(rdbtnGuionista.isSelected()) {
			panelDirector.setVisible(false);
			panelActor.setVisible(false);
			panelGuionista.setVisible(true);
			panelAudiovisual.setVisible(false);
			rdbtnActor.setSelected(false);
			rdbDirector.setSelected(false);
			rdbtnTecnicoAudiovisual.setSelected(false);
		}
		else if(rdbtnTecnicoAudiovisual.isSelected()) {
			panelDirector.setVisible(false);
			panelActor.setVisible(false);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(true);
			rdbtnActor.setSelected(false);
			rdbtnGuionista.setSelected(false);
			rdbDirector.setSelected(false);
		}
		getContentPane().add(scrollPane, BorderLayout.EAST); 


	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		else if(e.getSource().equals(btnModificar)) {
			int resp =JOptionPane.showConfirmDialog(null,"�Quieres Modificar Los Datos?","Alerta!",JOptionPane.YES_NO_OPTION);
			if (resp==0) {
				JOptionPane.showMessageDialog(null, "Se ha modificado correctamente", "Titulo", JOptionPane.DEFAULT_OPTION);


			}
			else if(e.getSource().equals(btnAceptar)){
				GestionDatos vGDatos = new GestionDatos();

			}
		}
		else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
		//else if(e.getSource().equals(btnDirector)) {
		//panelDirector.setVisible(true);

		//	}
	}

}