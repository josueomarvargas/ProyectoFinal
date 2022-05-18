package vistas.ventanas.custom.containers;

import java.awt.Font;
import java.awt.Point;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

import controlador.utils.views.Utilidades;
import vistas.ventanas.custom.components.MenuButton;

public class OptionPanel extends JDialog {

	private static final long serialVersionUID = 1L;
	public static int ERROR = 0;
	public static int CONFIRM = 1;

	private static int choose;
	private Icon icon;
	private Point position;

	public static void showMessage(Window parent, String message, String title, int type) {
		showOptionMessage(parent, message, title, type);
	}

	public static int showOptionMessage(Window parent, String message, String title, int type) {
		OptionPanel op = new OptionPanel(parent, message, title, type);
		op.setVisible(true);
		return choose;
	}

	public OptionPanel(Window parent, String message, String title, int type) {
		super(parent);
		getRootPane().setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		setModal(true);
		setLayout(null);
		setResizable(false);
		setUndecorated(true);
		setSize(350, 200);
		configDialog(this, type);
		Utilidades.centerWindow(parent, this);

		TitleBar bar = new TitleBar(this);
		bar.setTitle(title);
		bar.setBounds(0, 0, parent.getWidth(), 25);
		getContentPane().add(bar);

		JLabel img = new JLabel();
		img.setIcon(icon);
		img.setBounds(25, (int) getHeight() / 2 - 50, 50, 50);
		getContentPane().add(img);

		JLabel msg = new JLabel();
		msg.setFont(new Font("Arial", Font.PLAIN, 16));
		msg.setText("<html>" + message + "</html>");
		msg.setBounds(75, 50, (int) getWidth() - 75, 50);
		getContentPane().add(msg);

	}

	private void configDialog(Window dialog, Integer type) {
		switch (type) {
		case 0:
			icon = new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/warning.png"));
			MenuButton okBtn = new MenuButton();
			Utilidades.configButtons(okBtn, "Vale");
			okBtn.setBounds((int) dialog.getWidth() / 2 - 25, (int) dialog.getHeight() - 50, 50, 32);

			okBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					choose = 0;
					dialog.dispose();
				}
			});
			dialog.add(okBtn);
			break;
		case 1:
			icon = new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/ask.png"));

			MenuButton siBtn = new MenuButton();
			Utilidades.configButtons(siBtn, "Sí");
			siBtn.setBounds((int) dialog.getWidth() / 2 - 35, (int) dialog.getHeight() - 50, 50, 32);
			siBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					choose = 1;
					dialog.dispose();
				}
			});
			dialog.add(siBtn);

			MenuButton noBtn = new MenuButton();
			Utilidades.configButtons(noBtn, "No");
			noBtn.setText("No");
			noBtn.setBounds((int) dialog.getWidth() / 2 + 35, (int) dialog.getHeight() - 50, 50, 32);
			noBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					choose = 0;
					dialog.dispose();
				}
			});
			dialog.add(noBtn);
			break;

		}
	}

	// Error

}
