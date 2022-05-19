package vistas.ventanas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controlador.utils.views.Utilidades;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.table.TablaEquipamiento;
import vistas.ventanas.table.TablaPatrocinadores;
import vistas.ventanas.table.TablaPeliculasSeries;
import vistas.ventanas.table.TablaTrabajadores;

public class GestionDatos extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JDialog thisDialog;
	private TitleBar bar;
	private JButton btnGTrabajadores;
	private JButton btnGObrasAudiovisuales;
	private JButton btnGPatrocinadores;
	private JButton btnGEquipamiento;
	private JButton btnVolver;

	/**
	 * Create the dialog.
	 */
	public GestionDatos(Window parent, boolean modal) {
		super(parent);
		thisDialog = this;
		setModal(modal);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		this.setUndecorated(true);
		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);

		{
			JLabel txtGestinDeDatos = new JLabel();
			txtGestinDeDatos.setFont(new Font("Calibri", Font.PLAIN, 27));
			txtGestinDeDatos.setText("Gesti\u00F3n de Datos");
			txtGestinDeDatos.setBounds(151, 38, 224, 28);
			contentPanel.add(txtGestinDeDatos);
		}
		{
			btnGTrabajadores = new JButton("Gestionar Trabajadores");
			btnGTrabajadores.addActionListener(this);
			btnGTrabajadores.setFont(new Font("Calibri", Font.PLAIN, 13));
			btnGTrabajadores.setBounds(150, 87, 203, 34);
			contentPanel.add(btnGTrabajadores);
		}
		{
			btnGObrasAudiovisuales = new JButton("Gestionar Obras Audiovisuales");
			btnGObrasAudiovisuales.addActionListener(this);
			btnGObrasAudiovisuales.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGObrasAudiovisuales.setBounds(150, 142, 203, 34);
			contentPanel.add(btnGObrasAudiovisuales);
		}
		{
			btnGPatrocinadores = new JButton("Gestionar Patrocinadores");
			btnGPatrocinadores.addActionListener(this);
			btnGPatrocinadores.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGPatrocinadores.setBounds(150, 201, 203, 34);
			contentPanel.add(btnGPatrocinadores);
		}
		{
			btnGEquipamiento = new JButton("Gestionar Equipamiento");
			btnGEquipamiento.addActionListener(this);
			btnGEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGEquipamiento.setBounds(150, 256, 203, 34);
			contentPanel.add(btnGEquipamiento);
		}

		{

			btnVolver = new JButton("Volver");
			btnVolver.setBounds(150, 300, 100, 34);
			btnVolver.addActionListener(this);
			contentPanel.add(btnVolver);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnVolver)) {
			this.dispose();
		} else if (e.getSource().equals(btnGTrabajadores)) {
			TablaTrabajadores vTrabajadores = new TablaTrabajadores(thisDialog);
			vTrabajadores.setVisible(true);

		} else if (e.getSource().equals(btnGObrasAudiovisuales)) {
			TablaPeliculasSeries vAudiovisual = new TablaPeliculasSeries(thisDialog);
			vAudiovisual.setVisible(true);

		} else if (e.getSource().equals(btnGPatrocinadores)) {
			TablaPatrocinadores vPatrocinadores = new TablaPatrocinadores();
			vPatrocinadores.setVisible(true);
		} else if (e.getSource().equals(btnGEquipamiento)) {
			TablaEquipamiento vEquipamiento = new TablaEquipamiento();
			vEquipamiento.setVisible(true);

		}
	}
}
