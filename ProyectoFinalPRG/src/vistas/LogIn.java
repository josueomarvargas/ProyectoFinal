package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JEditorPane;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;
import java.awt.Color;

public class LogIn extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnCancelar;
	private JButton btnAceptar;
	private JPanel buttonPane;
	private JButton btnCerrarSystem;


	/**
	 * Create the frame.
	 */
	public LogIn() {
		this.setUndecorated(true);
		//this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 420);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(40000, 32767));
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(171, 202, 173, 20);
		contentPane.add(passwordField);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesin.setBounds(160, 57, 173, 29);
		lblIniciarSesin.setFont(new Font("Calibri", Font.PLAIN, 28));
		contentPane.add(lblIniciarSesin);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
		lblNewLabel_1.setBounds(60, 201, 79, 26);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario :");
		lblNewLabel_1_1.setBounds(60, 133, 56, 23);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(171, 132, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(512, 0, 39, 39);
		contentPane.add(btnCerrarSystem);
		{
			buttonPane = new JPanel();
			buttonPane.setBounds(0, 388, 550, 32);
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				
				btnCancelar = new JButton("Cancelar");
				btnCancelar.addActionListener(this);
				btnAceptar = new JButton("Aceptar");
				btnAceptar.addActionListener(this);
				buttonPane.add(btnAceptar);
				buttonPane.add(btnCancelar);
			}
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAceptar)) {
			Menu vMenu=new Menu();
			vMenu.setVisible(true);
		
		}
		else if(e.getSource().equals(btnCancelar)) {
			//SalirPrograma vSalir=new SalirPrograma();
			//vSalir.setVisible(true);
			int resp =JOptionPane.showConfirmDialog(null,"¿Quieres Salir del programa?","Alerta!",JOptionPane.YES_NO_OPTION);
			if (resp==0) {
				System.exit(0);
				
			}
		}
	}
}
