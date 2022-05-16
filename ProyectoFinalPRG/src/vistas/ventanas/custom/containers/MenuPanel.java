package vistas.ventanas.custom.containers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.EmptyBorder;

import controlador.utils.views.Utilidades;
import vistas.dao.CheckLogin;
import vistas.ventanas.GestionDatos;
import vistas.ventanas.LogIn;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.data.DatosPersonal;
import vistas.ventanas.table.TablaPeliculasSeries;

public class MenuPanel extends CustomPanel {

	private static final long serialVersionUID = 1L;
	private MenuButton btnMDatos;
	private MenuButton btnGDatos;
	private MenuButton btnConsultarObra;
	private MenuButton btnCerrar;

	public void setModificarEnable(boolean isEnabled) {
		btnMDatos.setEnabled(isEnabled);
	}

	public void setGestionarEnable(boolean isEnabled) {
		btnGDatos.setEnabled(isEnabled);
	}

	public MenuPanel(Window parent) {
		int width = (int) getWidth() / 2 + 50;
		setLayout(null);

		JLabel lblMenu = new JLabel("Men\u00FA");
		lblMenu.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblMenu.setBounds(width + 10, 55, 109, 30);
		add(lblMenu);

		btnMDatos = new MenuButton();
		Utilidades.configButtons(btnMDatos, "Mis Datos");
		btnMDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatosPersonal vMDatos = new DatosPersonal();
				vMDatos.setVisible(true);
			}
		});
		btnMDatos.setBounds(width, 130, 250, 40);
		add(btnMDatos);

		btnGDatos = new MenuButton();
		Utilidades.configButtons(btnGDatos, "Gestionar Datos");
		btnGDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionDatos vGDatos = new GestionDatos(parent, true);
				vGDatos.setVisible(true);
			}
		});
		btnGDatos.setBounds(width, 200, 250, 40);
		add(btnGDatos);

		btnConsultarObra = new MenuButton();
		Utilidades.configButtons(btnConsultarObra, "Consultar obras audiovisuales");
		btnConsultarObra.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.dispose();
				TablaPeliculasSeries vCObras = new TablaPeliculasSeries(parent, true);
				vCObras.setVisible(true);
			}
		});
		btnConsultarObra.setBounds(width, 270, 250, 40);
		add(btnConsultarObra);

		btnCerrar = new MenuButton();
		btnCerrar.setBorder(new EmptyBorder(5, 0, 5, 0));
		Utilidades.configButtons(btnCerrar, null);
		btnCerrar.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/logout.png")));
		btnCerrar.setBounds(width, 320, 50, 40);
		btnCerrar.setToolTipText("Cerrar sesión");
		btnCerrar.setBackground(new Color(255, 255, 255, 100));
		btnCerrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int resp = JOptionPane.showConfirmDialog(parent, "¿Estas seguro de que quieres cerrar Sesion?",
						"Alerta!", JOptionPane.YES_NO_OPTION);
				if (resp == 0) {
					parent.dispose();
					CheckLogin.logOut();
					LogIn login = new LogIn();
					Utilidades.centerWindow(parent, login);
					login.setVisible(true);
				}
			}
		});
		add(btnCerrar);
	}

}
