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

public class Exit extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Exit dialog = new Exit();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Exit() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel txtrquieresSalirDel = new JLabel();
			txtrquieresSalirDel.setText("\u00BFQuieres salir del \r\nprograma?");
			txtrquieresSalirDel.setFont(new Font("Calibri", Font.PLAIN, 28));
			txtrquieresSalirDel.setBackground(SystemColor.menu);
			txtrquieresSalirDel.setBounds(50, 52, 346, 83);
			contentPanel.add(txtrquieresSalirDel);
		}
		{
			JButton btnNewButton = new JButton("S\u00CD");
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNewButton.setBounds(69, 167, 127, 38);
			contentPanel.add(btnNewButton);
		}
		{
			JButton btnNo = new JButton("NO");
			btnNo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnNo.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNo.setBounds(229, 167, 127, 38);
			contentPanel.add(btnNo);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

}
