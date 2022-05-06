package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Rectangle;

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
	/**
	 * Create the dialog.
	 */
	public Menu() {
		//this.setUndecorated(true);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(SystemColor.activeCaption);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnMDatos = new JButton("Mis Datos");
			btnMDatos.addActionListener(this);
			btnMDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnMDatos.setBounds(107, 72, 240, 37);
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
			btnGDatos.setBounds(107, 120, 240, 37);
			contentPanel.add(btnGDatos);
		}
		{
			btnConsultarObra = new JButton("Consultar obras audiovisuales");
			btnConsultarObra.addActionListener(this);
			btnConsultarObra.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnConsultarObra.setBounds(107, 170, 240, 37);
			contentPanel.add(btnConsultarObra);
		}
		{
			JLabel lblMen = new JLabel("Men\u00FA");
			lblMen.setFont(new Font("Calibri", Font.PLAIN, 28));
			lblMen.setBounds(181, 21, 99, 29);
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
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnMDatos)) {
			AddDatosPersonal vMDatos= new AddDatosPersonal();
			vMDatos.setVisible(true);
		}
		else if(e.getSource().equals(btnGDatos)) {
			GestionDatos vGDatos= new GestionDatos();
			vGDatos.setVisible(true);

		}
		else if(e.getSource().equals(btnConsultarObra)) {
			TablaPeliculasSeries vCObras= new TablaPeliculasSeries();
			vCObras.setVisible(true);
		}
		else if(e.getSource().equals(btnCerrar)) {
			//CerrarMenu vCMenu= new CerrarMenu();
			//vCMenu.setVisible(true);
			int resp =JOptionPane.showConfirmDialog(null,"¿Estas seguro de que quieres cerrar Sesion?","Alerta!",JOptionPane.YES_NO_OPTION);
			if (resp==0) {
				this.dispose();
				
			}
		}



	}

}
