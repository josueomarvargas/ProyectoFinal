package vistas.ventanas;

import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.utils.views.Utilidades;
import modelo.clases.Trabajador;
import vistas.dao.CheckLogin;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.containers.CustomPanel;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.table.TablaEquipamiento;
import vistas.ventanas.table.TablaPatrocinadores;
import vistas.ventanas.table.TablaPeliculasSeries;
import vistas.ventanas.table.TablaTrabajadores;

public class GestionDatos extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final CustomPanel customPanel = new CustomPanel();
	private final JDialog thisDialog;
	private final Trabajador user = CheckLogin.getLogin();
	private TitleBar bar;
	private MenuButton btnGTrabajadores;
	private MenuButton btnGObrasAudiovisuales;
	private MenuButton btnGPatrocinadores;
	private MenuButton btnGEquipamiento;
	private MenuButton btnVolver;
	private Window parent;

	/**
	 * Create the dialog.
	 */
	public GestionDatos(Window parent, boolean modal) {
		super(parent);
		setModal(modal);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		this.setUndecorated(true);
		thisDialog = this;
		this.parent = parent;
		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, 960, 25);
		contentPanel.add(bar);

		customPanel.setBounds(243, 36, 472, 477);
		customPanel.setLayout(null);
		{
			JLabel txtGestinDeDatos = new JLabel();
			txtGestinDeDatos.setHorizontalAlignment(SwingConstants.CENTER);
			txtGestinDeDatos.setFont(new Font("Calibri", Font.BOLD, 34));
			txtGestinDeDatos.setText("Gesti\u00F3n de Datos");
			txtGestinDeDatos.setBounds(100, 10, 275, 55);
			customPanel.add(txtGestinDeDatos);
		}
		{
			btnGTrabajadores = new MenuButton();
			btnGTrabajadores.setEnabled(true);
			Utilidades.configButtons(btnGTrabajadores, "Gestionar Trabajadores");
			btnGTrabajadores.addActionListener(this);
			btnGTrabajadores.setFont(new Font("Calibri", Font.BOLD, 15));
			btnGTrabajadores.setBounds(128, 104, 215, 40);
			customPanel.add(btnGTrabajadores);
		}
		{
			btnGObrasAudiovisuales = new MenuButton();
			btnGObrasAudiovisuales.setEnabled(true);
			Utilidades.configButtons(btnGObrasAudiovisuales, "Gestionar Obras Audiovisuales");
			btnGObrasAudiovisuales.addActionListener(this);
			btnGObrasAudiovisuales.setFont(new Font("Calibri", Font.BOLD, 15));
			btnGObrasAudiovisuales.setBounds(128, 156, 215, 40);
			customPanel.add(btnGObrasAudiovisuales);
		}
		{
			btnGPatrocinadores = new MenuButton();
			btnGPatrocinadores.setEnabled(true);
			Utilidades.configButtons(btnGPatrocinadores, "Gestionar Patrocinadores");
			btnGPatrocinadores.addActionListener(this);
			btnGPatrocinadores.setFont(new Font("Calibri", Font.BOLD, 15));
			btnGPatrocinadores.setBounds(128, 215, 215, 40);
			customPanel.add(btnGPatrocinadores);
		}
		{
			btnGEquipamiento = new MenuButton();
			btnGEquipamiento.setEnabled(true);
			Utilidades.configButtons(btnGEquipamiento, "Gestionar Equipamiento");
			btnGEquipamiento.addActionListener(this);
			btnGEquipamiento.setFont(new Font("Calibri", Font.BOLD, 15));
			btnGEquipamiento.setBounds(128, 270, 215, 40);
			customPanel.add(btnGEquipamiento);
		}

		{

			btnVolver = new MenuButton();
			btnVolver.setEnabled(true);
			btnVolver.setIcon(
					new ImageIcon(GestionDatos.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
			Utilidades.configButtons(btnVolver, "");
			btnVolver.setBounds(175, 337, 100, 34);
			btnVolver.addActionListener(this);
			customPanel.add(btnVolver);
		}

		contentPanel.add(customPanel);

		JLabel backgroundImg = Utilidades.backgroundImg(this);
		backgroundImg.setBounds(0, 0, this.getWidth(), 540);
		contentPanel.add(backgroundImg);

		if (user.getTipo().equalsIgnoreCase("administrador") || user.getTipo().equalsIgnoreCase("director")) {
			btnGTrabajadores.setEnabled(true);
			btnGObrasAudiovisuales.setEnabled(true);
			btnGPatrocinadores.setEnabled(true);
			btnGEquipamiento.setEnabled(true);
			if (user.getTipo().equalsIgnoreCase("director"))
				btnGEquipamiento.setEnabled(false);
		} else {
			btnGTrabajadores.setEnabled(false);
			btnGObrasAudiovisuales.setEnabled(false);
			btnGPatrocinadores.setEnabled(false);
			btnGEquipamiento.setEnabled(true);

		}

		getContentPane().add(contentPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolver)) {
			this.dispose();
			parent.setVisible(true);
		} else if (e.getSource().equals(btnGTrabajadores)) {
			TablaTrabajadores vTrabajadores = new TablaTrabajadores(thisDialog, true);
			thisDialog.dispose();
			vTrabajadores.setVisible(true);

		} else if (e.getSource().equals(btnGObrasAudiovisuales)) {

			TablaPeliculasSeries vAudiovisual = new TablaPeliculasSeries(thisDialog, true);
			thisDialog.dispose();
			vAudiovisual.setVisible(true);

		} else if (e.getSource().equals(btnGPatrocinadores)) {
			TablaPatrocinadores vPatrocinadores = new TablaPatrocinadores(thisDialog, true);
			thisDialog.dispose();
			vPatrocinadores.setVisible(true);
		} else if (e.getSource().equals(btnGEquipamiento)) {
			TablaEquipamiento vEquipamiento = new TablaEquipamiento(thisDialog, true);
			thisDialog.dispose();

			vEquipamiento.setVisible(true);

		}
	}
}
