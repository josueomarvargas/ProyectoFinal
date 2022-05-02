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

public class GestionDatos extends JDialog {

	private final JPanel contentPanel = new JPanel();



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
			JButton btnNewButton = new JButton("Gestionar Trabajadores");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 13));
			btnNewButton.setBounds(132, 60, 203, 34);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnGestionarObrasAudiovisuales = new JButton("Gestionar Obras Audiovisuales");
			btnGestionarObrasAudiovisuales.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGestionarObrasAudiovisuales.setBounds(132, 104, 203, 34);
			contentPanel.add(btnGestionarObrasAudiovisuales);
		}
		{
			JButton btnGestionarPatrocinadores = new JButton("Gestionar Patrocinadores");
			btnGestionarPatrocinadores.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGestionarPatrocinadores.setBounds(132, 143, 203, 34);
			contentPanel.add(btnGestionarPatrocinadores);
		}
		{
			JButton btnGestionarEquipamiento = new JButton("Gestionar Equipamiento");
			btnGestionarEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 12));
			btnGestionarEquipamiento.setBounds(132, 183, 203, 34);
			contentPanel.add(btnGestionarEquipamiento);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton cancelButton = new JButton("Volver");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}

