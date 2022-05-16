package vistas.ventanas.data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.utils.views.Utilidades;

public class DatosEquipamiento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnAdd, btnBorrar, btnModificar, btnAceptar, btnVolver, btnAddFoto;
	private JTextField fieldNombre, fieldTipo, fieldCaracteristica;
	private JLabel fotoLabel;
	private File foto;

	public DatosEquipamiento(Window parent) {
		this.setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);

		contentPanel.setBackground(Color.WHITE);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel lblCaracteristica = new JLabel("Caractersticas:");
		lblCaracteristica.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCaracteristica.setBounds(124, 254, 136, 23);
		lblCaracteristica.setFont(new Font("Calibri", Font.PLAIN, 16));
		contentPanel.add(lblCaracteristica);

		JLabel lblDatosEquipamiento = new JLabel("Datos Equipamiento");
		lblDatosEquipamiento.setBounds(347, 47, 266, 29);
		lblDatosEquipamiento.setFont(new Font("Calibri", Font.PLAIN, 28));
		contentPanel.add(lblDatosEquipamiento);

		btnAdd = new JButton("A\u00F1adir a una obra");
		btnAdd.setBounds(30, 490, 119, 23);
		contentPanel.add(btnAdd);
		btnAdd.addActionListener(this);

		btnBorrar = new JButton("Borrar datos");
		btnBorrar.setBounds(179, 490, 93, 23);
		contentPanel.add(btnBorrar);
		btnBorrar.addActionListener(this);

		btnModificar = new JButton("Modificar datos");
		btnModificar.setBounds(300, 490, 105, 23);
		contentPanel.add(btnModificar);
		btnModificar.addActionListener(this);

		btnAceptar = new JButton("Aceptar");
		btnAceptar.setBounds(756, 490, 71, 23);
		contentPanel.add(btnAceptar);
		getRootPane().setDefaultButton(btnAceptar);

		btnVolver = new JButton("Volver");
		btnVolver.setBounds(850, 490, 63, 23);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);

		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(289, 104, 346, 29);
		contentPanel.add(comboBox);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombre.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblNombre.setBounds(124, 182, 136, 23);
		contentPanel.add(lblNombre);

		JLabel lblTipo = new JLabel("Tipo:");
		lblTipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTipo.setFont(new Font("Calibri", Font.PLAIN, 16));
		lblTipo.setBounds(124, 220, 136, 23);
		contentPanel.add(lblTipo);

		fieldNombre = new JTextField();
		fieldNombre.setBounds(289, 181, 346, 20);
		contentPanel.add(fieldNombre);

		fieldTipo = new JTextField();
		fieldTipo.setBounds(289, 219, 346, 20);
		contentPanel.add(fieldTipo);

		fieldCaracteristica = new JTextField();
		fieldCaracteristica.setBounds(289, 253, 346, 20);
		contentPanel.add(fieldCaracteristica);

		btnAddFoto = new JButton("A\u00F1adir foto");
		btnAddFoto.setBounds(153, 369, 119, 23);
		btnAddFoto.addActionListener(this);
		contentPanel.add(btnAddFoto);

		btnModificar.addActionListener(this);

		fotoLabel = new JLabel();
		fotoLabel.setBounds(289, 315, 346, 131);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAdd)) {

		} else if (e.getSource().equals(btnBorrar)) {
		} else if (e.getSource().equals(btnModificar)) {
		} else if (e.getSource().equals(btnAceptar)) {
		} else if (e.getSource().equals(btnVolver)) {
		} else if (e.getSource().equals(btnAddFoto)) {
			JFileChooser fileChooser = new JFileChooser();

			int response = fileChooser.showOpenDialog(null);

			if (response == JFileChooser.APPROVE_OPTION) {

				contentPanel.add(fotoLabel);
				foto = new File(fileChooser.getSelectedFile().getAbsolutePath());
				fotoLabel.setIcon(Utilidades.resizeIcon(fotoLabel, foto));
			}
		}

	}
}
