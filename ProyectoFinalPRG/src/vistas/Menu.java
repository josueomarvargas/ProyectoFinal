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

public class Menu extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Menu dialog = new Menu();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Menu() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JButton btnMDatos = new JButton("Mis Datos");
			btnMDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnMDatos.setBounds(107, 72, 240, 37);
			contentPanel.add(btnMDatos);
		}
		{
			JButton btnGDatos = new JButton("Gestionar Datos");
			btnGDatos.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnGDatos.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnGDatos.setBounds(107, 120, 240, 37);
			contentPanel.add(btnGDatos);
		}
		{
			JButton btnConsultarObra = new JButton("Consultar obras audiovisuales");
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
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Cerrar Sesi\u00F3n");
				okButton.setFont(new Font("Calibri", Font.PLAIN, 12));
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

}
