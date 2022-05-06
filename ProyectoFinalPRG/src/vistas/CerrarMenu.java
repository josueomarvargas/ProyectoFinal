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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import java.awt.Color;

public class CerrarMenu extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnNoAceptar;
	private JButton btnAceptar;
	private JButton btnCerrarSystem;


	/**
	 * Create the dialog.
	 */
	public CerrarMenu() {
		this.setUndecorated(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			btnAceptar = new JButton("S\u00CD");
			btnAceptar.addActionListener(this);
			btnAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnAceptar.setBounds(80, 173, 127, 38);
			contentPanel.add(btnAceptar);
			
		}
		{
			btnNoAceptar = new JButton("NO");
			btnNoAceptar.addActionListener(this);
			btnNoAceptar.setFont(new Font("Tahoma", Font.PLAIN, 18));
			btnNoAceptar.setBounds(236, 173, 119, 38);
			contentPanel.add(btnNoAceptar);
		}
		{
			JLabel lblEstasSeguro = new JLabel("\r\n\u00BF Estas seguro de que \r\n\r\nquieres cerrar sesi\u00F3n?");
			lblEstasSeguro.setBackground(new Color(240, 240, 240));
			lblEstasSeguro.setFont(new Font("Calibri", Font.PLAIN, 23));
			lblEstasSeguro.setBounds(10, 0, 414, 130);
			contentPanel.add(lblEstasSeguro);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAceptar)) {
			LogIn vLog = new LogIn();
			vLog.setVisible(true);
			this.dispose();
		}
		else if(e.getSource().equals(btnNoAceptar)) {
			this.dispose();

		}
	}

}
