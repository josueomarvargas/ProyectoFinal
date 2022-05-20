package vistas.ventanas.custom.containers;

import java.awt.Color;
import java.awt.Font;
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
	/** Constante para el tipo de mensaje {@code ERROR}, el valor es 0 **/
	public static int ERROR = 0;
	/** Constante para el tipo de mensaje {@code CONFIRM}, el valor es 1 **/
	public static int CONFIRM = 1;
	/** Constante para el tipo de mensaje {@code MESSAGE}, el valor es 2 **/
	public static int MESSAGE = 2;

	private static int choose;
	private Icon icon;
	private static String yes = "Sí";
	private static String no = "No";
	private static String ok = "Vale";

	public void resetStrings() {
		yes = "Sí";
		no = "No";
		ok = "Vale";
	}

	public static void showMessage(Window parent, String message, String title, int type) {
		showOptionMessage(parent, message, title, type);
	}

	public static int showOptionMessage(Window parent, String message, String title, String btn1, String btn2,
			int type) {
		yes = btn1;
		no = btn2;
		OptionPanel op = new OptionPanel(parent, message, title, type);
		op.setVisible(true);
		return choose;
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
			okButton(dialog);
			break;
		case 1:
			icon = new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/ask.png"));

			MenuButton siBtn = new MenuButton();
			siBtn.setEnabled(true);
			Utilidades.configButtons(siBtn, yes);
			siBtn.setBounds((int) dialog.getWidth() / 2 - 80, (int) dialog.getHeight() - 50, 75, 32);
			siBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					choose = 1;
					dialog.dispose();
				}
			});
			dialog.add(siBtn);

			MenuButton noBtn = new MenuButton();
			noBtn.setEnabled(true);
			Utilidades.configButtons(noBtn, no);
			noBtn.setBounds((int) dialog.getWidth() / 2 + 40, (int) dialog.getHeight() - 50, 75, 32);
			noBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					choose = 0;
					dialog.dispose();
				}
			});
			dialog.add(noBtn);
			break;

		case 2:
			icon = new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/check.png"));
			okButton(dialog);
			break;

		}
		resetStrings();

	}

	private void okButton(Window dialog) {
		MenuButton valeBtn = new MenuButton();
		Utilidades.configButtons(valeBtn, ok);
		valeBtn.setBounds((int) dialog.getWidth() / 2 - 25, (int) dialog.getHeight() - 50, 50, 32);
		valeBtn.setEnabled(true);

		valeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				dialog.dispose();
			}
		});
		dialog.add(valeBtn);
	}

	// Error

}
