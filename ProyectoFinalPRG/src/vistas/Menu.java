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
			JTextArea txtrMen = new JTextArea();
			txtrMen.setBackground(SystemColor.menu);
			txtrMen.setToolTipText("");
			txtrMen.setText("Men\u00FA");
			txtrMen.setTabSize(15);
			txtrMen.setFont(new Font("Calibri", Font.PLAIN, 27));
			txtrMen.setBounds(168, 11, 111, 37);
			contentPanel.add(txtrMen);
		}
		{
			JButton btnNewButton = new JButton("Mis Datos");
			btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton.setBounds(107, 72, 240, 37);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNewButton_1 = new JButton("Gestionar Datos");
			btnNewButton_1.setFont(new Font("Calibri", Font.PLAIN, 16));
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNewButton_1.setBounds(107, 120, 240, 37);
			contentPanel.add(btnNewButton_1);
		}
		{
			JButton btnNewButton_2 = new JButton("Consultar obras audiovisuales");
			btnNewButton_2.setFont(new Font("Calibri", Font.PLAIN, 15));
			btnNewButton_2.setBounds(107, 170, 240, 37);
			contentPanel.add(btnNewButton_2);
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
