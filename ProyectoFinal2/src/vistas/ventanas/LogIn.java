package vistas.ventanas;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.utils.views.Utilidades;
import vistas.ventanas.custom.containers.PanelLogin;
import vistas.ventanas.custom.containers.TitleBar;

public class LogIn extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private PanelLogin panelLogin;
	private TitleBar bar;

	public LogIn() {
		this.setUndecorated(true);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(this, null);

		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPane.add(bar);

		panelLogin = new PanelLogin(this);
		panelLogin.setBackground(new Color(255, 255, 255));
		panelLogin.setBounds((int) getWidth() / 2 - 175, 45, (int) getWidth() / 3 + 30, 475);
		contentPane.add(panelLogin);

		JLabel backgroundImg = Utilidades.backgroundImg(this);
		backgroundImg.setBounds(0, 0, this.getWidth(), 540);
		contentPane.add(backgroundImg);

	}

}
