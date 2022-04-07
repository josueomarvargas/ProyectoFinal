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

public class IniciarSesion extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IniciarSesion frame = new IniciarSesion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public IniciarSesion() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextArea txtrIniciarSesin = new JTextArea();
		txtrIniciarSesin.setBackground(SystemColor.menu);
		txtrIniciarSesin.setToolTipText("");
		txtrIniciarSesin.setFont(new Font("Imprint MT Shadow", Font.PLAIN, 27));
		txtrIniciarSesin.setTabSize(15);
		txtrIniciarSesin.setText("Iniciar Sesi\u00F3n");
		txtrIniciarSesin.setBounds(124, 33, 196, 37);
		contentPane.add(txtrIniciarSesin);
		
		JTextArea txtrUsuario = new JTextArea();
		txtrUsuario.setBackground(SystemColor.menu);
		txtrUsuario.setFont(new Font("Calibri", Font.PLAIN, 16));
		txtrUsuario.setText("Usuario");
		txtrUsuario.setTabSize(15);
		txtrUsuario.setBounds(10, 99, 97, 28);
		contentPane.add(txtrUsuario);
		
		JTextArea txtrContrasea = new JTextArea();
		txtrContrasea.setBackground(SystemColor.menu);
		txtrContrasea.setFont(new Font("Calibri", Font.PLAIN, 15));
		txtrContrasea.setText("Contrase\u00F1a");
		txtrContrasea.setTabSize(15);
		txtrContrasea.setBounds(10, 168, 97, 28);
		contentPane.add(txtrContrasea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(147, 168, 173, 28);
		contentPane.add(passwordField);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(147, 99, 173, 30);
		contentPane.add(editorPane);
		
		JButton btnNewButton = new JButton("Aceptar");
		btnNewButton.setBounds(192, 227, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(317, 227, 89, 23);
		contentPane.add(btnCancelar);
	}
}
