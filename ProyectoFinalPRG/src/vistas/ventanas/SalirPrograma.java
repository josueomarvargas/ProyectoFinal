package vistas.ventanas;

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

public class SalirPrograma extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnNo;
	private JButton btnSi;

	/**
	 * Create the dialog.
	 */
	public SalirPrograma() {
		this.setUndecorated(true);
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
			btnSi = new JButton("S\u00CD");
			btnSi.addActionListener(this);
			btnSi.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnSi.setBounds(69, 167, 127, 38);
			contentPanel.add(btnSi);
		}
		{
			btnNo = new JButton("NO");
			btnNo.addActionListener(this);
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


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnNo)) {
			this.dispose();
		}
		else if(e.getSource().equals(btnSi)) {

			System.exit(0);
		}

	}

}
