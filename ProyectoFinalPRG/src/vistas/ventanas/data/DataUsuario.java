package vistas.ventanas.data;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.Usuario;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.PasswordField;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;

public class DataUsuario extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TextField usernameField;
	private PasswordField passwdField;
	private MenuButton confirmar;
	private MenuButton volver;
	private JLabel lblNewLabel;
	private boolean valido = true;
	private Usuario usuario;
	private Window parent;
	private JDialog thisDialog;

	/**
	 * Create the dialog.
	 */
	public DataUsuario(Window parent, boolean modal, Usuario user) {
		super(parent);
		setModal(modal);
		setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.usuario = user;
		this.parent = parent;
		thisDialog = this;

		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);

		usernameField = new TextField();
		usernameField.setLabelText("Nombre de usuario");
		usernameField.setBounds(137, 82, 179, 50);
		usernameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_ENTER)) {
						e.consume();
					}
				}
				valido = true;
			}
		});
		contentPanel.add(usernameField);

		passwdField = new PasswordField();
		passwdField.setLabelText("Contrase\u00F1a");
		passwdField.setBounds(137, 157, 179, 50);
		contentPanel.add(passwdField);
		passwdField.setColumns(10);

		confirmar = new MenuButton();
		Utilidades.configButtons(confirmar, "");
		confirmar.setIcon(
				new ImageIcon(DataUsuario.class.getResource("/vistas/ventanas/custom/components/img/check.png")));
		confirmar.setBounds(139, 244, 80, 23);
		confirmar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boolean ok = false;
				if (valido) {
					usuario.setIdUsuario(usernameField.getText());
					usuario.setPasswd(String.valueOf(passwdField.getPassword()));
					ok = FactoryDAO.getInsertData().dataManage(usuario);
				}
				if (ok) {
					OptionPanel.showMessage(thisDialog, "Se ha creado el usuario", "Usuario creado",
							OptionPanel.MESSAGE);
					thisDialog.dispose();
					parent.setVisible(true);
				} else
					OptionPanel.showMessage(thisDialog,
							"Ha occurido un error inesperado al intentar crear este usuario", "Creación de usuario",
							OptionPanel.ERROR);
			}

		});
		contentPanel.add(confirmar);

		volver = new MenuButton();
		volver.setIcon(
				new ImageIcon(DataUsuario.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(volver, "");
		volver.setBounds(236, 244, 80, 23);
		volver.setEnabled(true);
		volver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Estas segur@ de que quieres volver a la ventana anterior?", "Volver", OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					thisDialog.dispose();
					parent.setVisible(true);
				}
			}

		});
		contentPanel.add(volver);

		lblNewLabel = new JLabel("Modificar datos del usuario");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(39, 36, 374, 35);
		contentPanel.add(lblNewLabel);

		if (usuario.getIdUsuario() == null) {
			valido = false;
		}

	}
}
