package vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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

public class LogIn extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField textField;
	private JButton btnAceptar;
	private JButton btnCancelar;


	/**
	 * Create the frame.
	 */
	public LogIn() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 168, 173, 20);
		contentPane.add(passwordField);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(this);
			
		btnAceptar.setBounds(192, 227, 89, 23);
		contentPane.add(btnAceptar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(this);
		btnCancelar.setBounds(317, 227, 89, 23);
		contentPane.add(btnCancelar);
		
		JLabel lblIniciarSesin = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesin.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblIniciarSesin.setBounds(135, 30, 173, 29);
		contentPane.add(lblIniciarSesin);
		
		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(37, 171, 79, 26);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Usuario :");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		lblNewLabel_1_1.setBounds(60, 106, 56, 23);
		contentPane.add(lblNewLabel_1_1);
		
		textField = new JTextField();
		textField.setBounds(147, 105, 173, 20);
		contentPane.add(textField);
		textField.setColumns(10);
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
