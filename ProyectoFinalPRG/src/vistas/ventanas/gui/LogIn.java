package vistas.ventanas.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controlador.utils.dao.GenericFactory;
import controlador.utils.messages.UIMessages;
import modelo.clases.Trabajador;

public class LogIn extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwd;
	private JTextField username;
	private JButton btnAcceder;
	private JButton btnCerrar;
	private Dimension size = null;

	/**
	 * Create the frame.
	 */
	public LogIn() {
		this.setUndecorated(true);

		// this.setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 420);
		contentPane = new JPanel();
		contentPane.setMaximumSize(new Dimension(40000, 32767));
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIniciarSesin = new JLabel("Iniciar sesi\u00F3n");
		lblIniciarSesin.setBounds(160, 57, 173, 29);
		lblIniciarSesin.setFont(new Font("Calibri", Font.PLAIN, 28));
		contentPane.add(lblIniciarSesin);

		JLabel lblNewLabel_1_1 = new JLabel("Usuario :");
		lblNewLabel_1_1.setBounds(60, 133, 56, 23);
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1_1);
		username = new JTextField();
		username.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkField();
			}
		});
		username.setBounds(171, 132, 173, 20);
		contentPane.add(username);
		username.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Contrase\u00F1a :");
		lblNewLabel_1.setBounds(60, 201, 79, 26);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPane.add(lblNewLabel_1);
		passwd = new JPasswordField();
		passwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				checkField();
			}
		});
		passwd.setBounds(171, 202, 173, 20);
		contentPane.add(passwd);

		btnCerrar = new JButton("X");
		btnCerrar.addActionListener(this);
		btnCerrar.setForeground(Color.RED);
		btnCerrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrar.setBounds(501, 0, 50, 39);
		contentPane.add(btnCerrar);
		btnAcceder = new JButton("Acceder");
		btnAcceder.setBounds(171, 297, 173, 23);
		contentPane.add(btnAcceder);
		btnAcceder.addActionListener(this);
		btnAcceder.setEnabled(false);

		Toolkit toolKit = getToolkit();
		size = toolKit.getScreenSize();
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAcceder)) {
			// Comprobar que el usuario y contraseña
			Trabajador tData = (Trabajador) GenericFactory.LOGIN.getUIcontroller()
					.check(Arrays.asList(username.getText(), String.valueOf(passwd.getPassword())));

			if (tData == null) {
				new UIMessages(this, "El usuario y/o contraseña són incorrectos, vuelva a intentarlo.",
						"Permiso denegado");
			} else {
				Menu vMenu = new Menu(this, size, tData);
				vMenu.setVisible(true);
				username.setText("");
				passwd.setText("");
			}

		}
		if (e.getSource().equals(btnCerrar)) {
			int resp = JOptionPane.showConfirmDialog(null, "¿Quieres Salir del programa?", "Alerta!",
					JOptionPane.YES_NO_OPTION);
			if (resp == 0) {
				this.dispose();

			}
		}
	}

	public void checkField() {
		if (username.getText().length() > 4 && passwd.getPassword().length > 8) {
			btnAcceder.setEnabled(true);
		} else {
			btnAcceder.setEnabled(false);
		}
	}
}
