package vistas.ventanas.custom.containers;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import controlador.utils.views.Utilidades;
import vistas.ventanas.custom.components.TitleBarButton;

public class TitleBar extends JPanel {

	private static final long serialVersionUID = 1L;
	private TitleBarButton exit;
	private JLabel title;
	private int x;
	private int y;
	private Dimension size;

	public TitleBar(Container parent) {
		setOpaque(true);
		setLayout(null);
		setBackground(new Color(50, 150, 210));
		size = Utilidades.resizeWindow(parent);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					x = me.getX() + 3;
					y = me.getY() + 3;
				}
			}

		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent me) {
				if (SwingUtilities.isLeftMouseButton(me)) {
					parent.setLocation(me.getXOnScreen() - x, me.getYOnScreen() - y);
				}
			}
		});

		title = new JLabel("Filmers");
		title.setFont(new Font("Arial", Font.BOLD, 14));
		title.setIcon(
				new ImageIcon(TitleBar.class.getResource("/vistas/ventanas/custom/components/img/film_roll.png")));
		title.setBounds(5, 0, 250, 25);
		add(title);

		exit = new TitleBarButton();
		exit.setHoverColor(Color.RED);
		exit.setIcon(new ImageIcon(TitleBar.class.getResource("/vistas/ventanas/custom/components/img/exit.png")));
		exit.setBounds(size.width - 40, 0, 40, 25);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cerrar Sesion?", "Alerta!",
						JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					System.exit(0);
				}
			}
		});
		add(exit);
	}

	public void setTitle(String message) {
		title.setText(message);
	}

}
