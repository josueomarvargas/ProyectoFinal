package vistas;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;

public class CerrarSesionMenú extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CerrarSesionMenú dialog = new CerrarSesionMenú();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CerrarSesionMenú() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JTextArea txtrestasSeguroDe = new JTextArea();
			txtrestasSeguroDe.setBackground(SystemColor.menu);
			txtrestasSeguroDe.setFont(new Font("Calibri", Font.PLAIN, 28));
			txtrestasSeguroDe.setText("\u00BFEst\u00E1s seguro de que\r\n quieres cerrar la sesi\u00F3n?");
			txtrestasSeguroDe.setBounds(76, 62, 292, 83);
			contentPanel.add(txtrestasSeguroDe);
		}
		{
			JButton btnNewButton = new JButton("S\u00CD");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBounds(80, 173, 127, 38);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("NO");
			btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton_1.setBounds(236, 173, 119, 38);
			contentPanel.add(btnNewButton_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
