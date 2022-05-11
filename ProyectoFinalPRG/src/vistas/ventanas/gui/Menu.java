package vistas.ventanas.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modelo.clases.Trabajador;
import vistas.dao.CheckLogin;
import vistas.ventanas.data.DatosPersonal;
import vistas.ventanas.table.TablaPeliculasSeries;

public class Menu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnMDatos;
	private JButton btnGDatos;
	private JButton btnConsultarObra;
	private JButton btnCerrar;
	private JButton btnCerrarSystem;
	private static Trabajador user;

	/**
	 * Create the dialog.
	 */
	public Menu(LogIn parent, Dimension size, Trabajador usuario) {
		super(parent);
		this.setUndecorated(true);
		user = usuario;
		setBounds(100, 100, 550, 420);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnMDatos = new JButton("Mis Datos");
			btnMDatos.addActionListener(this);
			btnMDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnMDatos.setBounds(143, 110, 240, 37);
			contentPanel.add(btnMDatos);
		}
		{
			btnGDatos = new JButton("Gestionar Datos");
			btnGDatos.addActionListener(this);
			btnGDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnGDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnGDatos.setBounds(143, 175, 240, 37);
			contentPanel.add(btnGDatos);
		}
		{
			btnConsultarObra = new JButton("Consultar obras audiovisuales");
			btnConsultarObra.addActionListener(this);
			btnConsultarObra.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnConsultarObra.setBounds(143, 240, 240, 37);
			contentPanel.add(btnConsultarObra);
		}
		{
			JLabel lblMen = new JLabel("Men\u00FA");
			lblMen.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblMen.setBounds(214, 41, 99, 29);
			contentPanel.add(lblMen);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(SystemColor.textHighlight);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnCerrar = new JButton("Cerrar Sesi\u00F3n");
				btnCerrar.addActionListener(this);
				btnCerrar.setFont(new Font("Calibri", Font.PLAIN, 12));
				btnCerrar.setActionCommand("OK");
				buttonPane.add(btnCerrar);
				getRootPane().setDefaultButton(btnCerrar);
			}
		}
		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(490, 0, 60, 37);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);
		setLocation(size.width / 2 - getWidth() / 2, size.height / 2 - getHeight() / 2);

		if (user.getTipo().equals("Administrador")) {
			btnMDatos.setEnabled(false);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnMDatos)) {
			DatosPersonal vMDatos = new DatosPersonal();
			vMDatos.setVisible(true);
		} else if (e.getSource().equals(btnGDatos)) {
			GestionDatos vGDatos = new GestionDatos();
			vGDatos.setVisible(true);

		} else if (e.getSource().equals(btnConsultarObra)) {
			TablaPeliculasSeries vCObras = new TablaPeliculasSeries();
			vCObras.setVisible(true);
		} else if (e.getSource().equals(btnCerrar)) {
			// CerrarMenu vCMenu= new CerrarMenu();
			// vCMenu.setVisible(true);
			int resp = JOptionPane.showConfirmDialog(null, "¿Estas seguro de que quieres cerrar Sesion?", "Alerta!",
					JOptionPane.YES_NO_OPTION);
			if (resp == 0) {
				this.dispose();
				CheckLogin.logOut();

			}
		} else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}

	}

}
