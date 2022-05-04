package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GestionDatos extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnGTrabajadores;
	private JButton btnGObrasAudiovisuales;
	private JButton btnGPatrocinadores;
	private JButton btnGEquipamiento;
	private JButton btnVolver;


	/**
	 * Create the dialog.
	 */
	public GestionDatos() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel txtrGestinDeDatos = new JLabel();
			txtrGestinDeDatos.setFont(new Font("Calibri", Font.PLAIN, 27));
			txtrGestinDeDatos.setBackground(SystemColor.menu);
			txtrGestinDeDatos.setText("Gesti\u00F3n de Datos");
			txtrGestinDeDatos.setBounds(111, 11, 224, 28);
			contentPanel.add(txtrGestinDeDatos);
		}
		{
			btnGTrabajadores = new JButton("Gestionar Trabajadores");
			btnGTrabajadores.addActionListener(this);
			btnGTrabajadores.setFont(new Font("Calibri", Font.PLAIN, 13));
			btnGTrabajadores.setBounds(132, 60, 203, 34);
			contentPanel.add(btnGTrabajadores);
		}
		{
			btnGObrasAudiovisuales = new JButton("Gestionar Obras Audiovisuales");
			btnGObrasAudiovisuales.addActionListener(this);
			btnGObrasAudiovisuales.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGObrasAudiovisuales.setBounds(132, 104, 203, 34);
			contentPanel.add(btnGObrasAudiovisuales);
		}
		{
			btnGPatrocinadores = new JButton("Gestionar Patrocinadores");
			btnGPatrocinadores.addActionListener(this);
			btnGPatrocinadores.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGPatrocinadores.setBounds(132, 143, 203, 34);
			contentPanel.add(btnGPatrocinadores);
		}
		{
			btnGEquipamiento = new JButton("Gestionar Equipamiento");
			btnGEquipamiento.addActionListener(this);
			btnGEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGEquipamiento.setBounds(132, 183, 203, 34);
			contentPanel.add(btnGEquipamiento);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				btnVolver = new JButton("Volver");
				btnVolver.addActionListener(this);
				btnVolver.setActionCommand("Cancel");
				buttonPane.add(btnVolver);
			}
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnVolver)) {
			this.dispose();
		}
		else if (e.getSource().equals(btnGTrabajadores)) {
			TablaTrabajadores vTrabajadores= new TablaTrabajadores();
			vTrabajadores.setVisible(true);

		}
		else if (e.getSource().equals(btnGObrasAudiovisuales)) {
			TablaPeliculasSeries vAudiovisual = new TablaPeliculasSeries();
			vAudiovisual.setVisible(true);

		}
		else if(e.getSource().equals(btnGPatrocinadores)) {
			TablaPatrocinadores vPatrocinadores=new TablaPatrocinadores();
			vPatrocinadores.setVisible(true);
		}
		else if(e.getSource().equals(btnGEquipamiento)) {
			TablaEquipamiento vEquipamiento= new TablaEquipamiento();
			vEquipamiento.setVisible(true);

		}
	}

}

