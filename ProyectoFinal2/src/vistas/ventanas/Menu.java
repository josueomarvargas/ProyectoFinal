package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.utils.views.Utilidades;
import modelo.clases.Trabajador;
import vistas.dao.CheckLogin;
import vistas.ventanas.custom.containers.MenuPanel;
import vistas.ventanas.custom.containers.TitleBar;

public class Menu extends JDialog {

	static final long serialVersionUID = 1L;
	private TitleBar bar;
	private static final Trabajador user = CheckLogin.getLogin();
	private final JPanel contentPanel = new JPanel();
	private MenuPanel menu;

	/**
	 * Create the dialog.
	 */
	public Menu(JFrame parent, boolean modal) {
		super(parent);
		setModal(modal);
		setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);

		menu = new MenuPanel(this);
		menu.setBounds((int) getWidth() / 2 - 175, 45, (int) getWidth() / 3 + 30, 475);
		menu.setBackground(new Color(255, 255, 255, 240));
		contentPanel.add(menu);
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		if (user.getTipo().equals("Administrador")) {
			menu.setModificarEnable(false);
		}

		JLabel backgroundImg = Utilidades.backgroundImg(this);
		backgroundImg.setBounds(0, 0, this.getWidth(), 540);
		contentPanel.add(backgroundImg);

	}

}
