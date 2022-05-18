package vistas.ventanas.custom.containers;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.utils.dao.FactoryDAO;
import modelo.clases.Usuario;
import vistas.ventanas.Menu;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.PasswordField;
import vistas.ventanas.custom.components.TextField;

public class PanelLogin extends CustomPanel {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TextField usuario;
	private PasswordField passwd;
	private MenuButton btnAcceder;
	private JLabel userRequired;
	private JLabel passRequired;
	private boolean userOk;
	private boolean passOk;
	private final int userLength = 4;
	private final int passLength = 8;

	public PanelLogin(JFrame parent) {
		setOpaque(false);
		setLayout(null);
		setPreferredSize(new Dimension(350, 400));
		contentPanel.setBackground(Color.WHITE);

		JLabel titulo = new JLabel("Iniciar Sesi\u00F3n");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.BOLD, 30));
		titulo.setBounds(50, 28, 250, 53);
		add(titulo);

		// Usuario
		usuario = new TextField();
		usuario.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!userOk) {
					userRequired.setText("Debe ser mínimo 4 caracteres de longitud");
					btnAcceder.setEnabled(false);
				}
			}
		});
		usuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				userOk = check(e);
			}
		});
		usuario.setBounds(50, 135, 250, 44);
		usuario.setLabelText("Nombre Usuario");
		add(usuario);

		userRequired = new JLabel();
		userRequired.setHorizontalAlignment(SwingConstants.LEFT);
		userRequired.setForeground(Color.GRAY);
		userRequired.setFont(new Font("Tahoma", Font.PLAIN, 11));
		userRequired.setBounds(50, 182, 250, 14);
		add(userRequired);

		// Passwd
		passwd = new PasswordField();
		passwd.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if (!passOk) {
					passRequired.setText("Debe ser mínimo 8 caracteres de longitud");
					btnAcceder.setEnabled(false);
				}
			}
		});
		passwd.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				passOk = check(e);

			}
		});
		passwd.setBounds(50, 210, 250, 44);
		passwd.setLabelText("Contrase\u00F1a");
		add(passwd);

		passRequired = new JLabel();
		passRequired.setHorizontalAlignment(SwingConstants.LEFT);
		passRequired.setForeground(Color.GRAY);
		passRequired.setFont(new Font("Tahoma", Font.PLAIN, 11));
		passRequired.setBounds(50, 257, 250, 14);
		add(passRequired);

		// Boton acceders
		btnAcceder = new MenuButton();
		btnAcceder.setBackground(new Color(25, 182, 247));
		btnAcceder.setForeground(new Color(255, 255, 255));
		btnAcceder.setText("Acceder");
		btnAcceder.setFont(new Font("sansserif", 1, 14));
		btnAcceder.setBounds(50, 310, 250, 44);
		btnAcceder.setEnabled(false);
		btnAcceder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (null != FactoryDAO.getCheckLogin().checkInfo(new Usuario(getUserName(), getPassword()))) {
					parent.dispose();
					Menu vMenu = new Menu(parent, true);
					vMenu.setVisible(true);
				} else {
					OptionPanel.showMessage(parent, "Usuario y/o contraseña incorrecta, intentelo de nuevo",
							"Error inicio de sesión", OptionPanel.ERROR);
					resetFields();
				}
				;
			}
		});
		add(btnAcceder);

	}

	public String getUserName() {
		return usuario.getText().trim();
	}

	public String getPassword() {
		return String.valueOf(passwd.getPassword());
	}

	public void resetFields() {
		btnAcceder.setEnabled(false);
		usuario.setText("");
		passwd.setText("");
	}

	private Boolean check(KeyEvent e) {
		boolean ok = false;

		if (e.getSource().equals(usuario)) {
			if (usuario.getText().length() >= userLength) {
				userRequired.setText("");
				ok = true;
			}
		}

		if (e.getSource().equals(passwd)) {
			if (passwd.getPassword().length >= passLength) {
				passRequired.setText("");
				ok = true;
			}
		}
		if (userOk && passOk) {
			btnAcceder.setEnabled(true);
		} else {
			btnAcceder.setEnabled(false);
		}
		return ok;
	}

}
