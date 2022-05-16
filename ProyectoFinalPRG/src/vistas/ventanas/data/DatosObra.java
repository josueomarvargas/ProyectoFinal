package vistas.ventanas.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.exceptions.CustomExceptions;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Serie;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;

public class DatosObra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultListModel<String> listModel;
	private JFileChooser fc = new JFileChooser();
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private MenuButton btnBorrarDatos, btnModificar, btnAceptar, btnVolver, btnVerPatrocinadores, addPortada;
	private TextField fieldNombre, fieldDuracion, fieldFecha, fieldPresupuesto, addCap;
	private JRadioButton esTaquillera;
	private JLabel fotoLabel;
	private File foto;
	private TitleBar bar;
	private JList<String> listCap;
	private JComboBox<Integer> comboBox;
	private CustomTab tabs;
	private Dimension size;
	private JDialog thisDialog;
	private Window parent;
	private ObraAudiovisual obra;

	/**
	 * @wbp.parser.constructor
	 */
	public DatosObra(Window parent, boolean modal) {
		super(parent);
		setModal(modal);
		this.parent = parent;
		init();
	}

	public DatosObra(Window parent, boolean modal, ObraAudiovisual obra) {
		super(parent);
		setModal(modal);
		this.parent = parent;
		this.obra = obra;
		init();

	}

	private void init() {
		this.thisDialog = this;
		this.setUndecorated(true);
		size = Utilidades.resizeWindow(this);
		setSize(size);
		Utilidades.centerWindow(parent, this);
		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);

		tabs = new CustomTab();
		tabs.setTabPlacement(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.WHITE);
		peliPanel.setBackground(Color.WHITE);
		tabs.add("Pelicula", peliPanel);
		seriePanel.setBackground(Color.WHITE);
		tabs.add("Serie", seriePanel);
		peliPanel.setLayout(null);
		seriePanel.setLayout(null);
		getContentPane().add(tabs);

		tabsFields(peliPanel, obra instanceof Pelicula ? obra : null);
		tabsFields(seriePanel, obra instanceof Serie ? obra : null);
	}

	private void tabsFields(JPanel panel, ObraAudiovisual obra) {

		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Datos Obra");
		lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblTitulo.setBounds(376, 32, 171, 36);
		panel.add(lblTitulo);

		// Nombre
		fieldNombre = new TextField();
		fieldNombre.setLabelText("Nombre");
		fieldNombre.setBounds(244, 83, 150, 50);
		panel.add(fieldNombre);

		// Duracion
		fieldDuracion = new TextField();
		fieldDuracion.setLabelText("Duracion");
		fieldDuracion.setBounds(244, 144, 105, 50);
		panel.add(fieldDuracion);

		// Fecha
		fieldFecha = new TextField();
		fieldFecha.setLabelText("Fecha de estreno :");
		fieldFecha.setBounds(562, 83, 148, 50);
		panel.add(fieldFecha);

		// Presupuesto
		fieldPresupuesto = new TextField();
		fieldPresupuesto.setLabelText("Presupuesto:");
		fieldPresupuesto.setBounds(562, 144, 83, 50);
		panel.add(fieldPresupuesto);

		if (panel.equals(peliPanel)) {
			esTaquillera = new JRadioButton("Es Taquillera");
			esTaquillera.setBounds(562, 218, 109, 23);
			panel.add(esTaquillera);
		} else {
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(449, 220, 261, 130);
			panel.add(scrollPane);

			JLabel lblTemporada = new JLabel("Temporada :");
			lblTemporada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTemporada.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTemporada.setBounds(314, 222, 104, 23);
			panel.add(lblTemporada);

			addCap = new TextField();
			addCap.setLabelText("Introducir nuevos capitulos");
			addCap.setToolTipText("Dale Enter para introducir un capítulo");
			addCap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listModel = (DefaultListModel<String>) listCap.getModel();
					if (!addCap.getText().isEmpty()) {
						listModel.addElement(addCap.getText());
						((Serie) obra).getNombreCap().get(comboBox.getSelectedIndex()).add(addCap.getText());
						addCap.setText("");
					} else {
						new CustomExceptions(thisDialog, "No puede insertar un capítulo sin nombre",
								"Error al insertar un capítulo");
					}
				}
			});
			addCap.setBounds(449, 362, 260, 50);
			panel.add(addCap);

			listCap = new JList<>();
			scrollPane.setViewportView(listCap);
			listCap.setBorder(BorderFactory.createLineBorder(Color.GRAY));

			comboBox = new JComboBox<>();
			comboBox.setSelectedIndex(-1);
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (comboBox.getSelectedIndex() != -1) {
						listModel = new DefaultListModel<>();
						for (String cap : ((Serie) obra).getNombreCap().get(comboBox.getSelectedIndex())) {
							listModel.addElement(cap);
						}
						listCap.setModel(listModel);

					}
				}
			});
			comboBox.setBounds(337, 256, 83, 20);
			panel.add(comboBox);

		}

		btnVerPatrocinadores = new MenuButton();
		Utilidades.configButtons(btnVerPatrocinadores, "Ver patrocinadores");
		btnVerPatrocinadores.setBounds(size.width - 175, size.height - 125, 150, 25);
//		btnVerPatrocinadores.addActionListener(this);
		panel.add(btnVerPatrocinadores);

		btnBorrarDatos = new MenuButton();
		Utilidades.configButtons(btnBorrarDatos, null);
		btnBorrarDatos
				.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/delete.png")));
		btnBorrarDatos.setToolTipText("Borrar datos");
		btnBorrarDatos.setBounds(size.width - 300, size.height - 75, 50, 25);
//		btnBorrarDatos.addActionListener(this);
		panel.add(btnBorrarDatos);

		btnModificar = new MenuButton();
		Utilidades.configButtons(btnModificar, null);
		btnModificar.setIcon(
				new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/edit_button.png")));
		btnModificar.setToolTipText("Modificar datos");
		btnModificar.setBounds(size.width - 225, size.height - 75, 50, 25);
		btnModificar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (obra != null) {
					obra.setDatos(obra.getIdObra(), fieldNombre.getText(), Integer.parseInt(fieldDuracion.getText()),
							Utilidades.validateDate(fieldFecha.getText()), Integer.parseInt(fieldPresupuesto.getText()),
							obra.getTipo(), getName());
					if (obra instanceof Pelicula) {
					} else {

					}

					System.out.println(FactoryDAO.getUpdateData().checkInfo(obra));
				}

			}

		});
		panel.add(btnModificar);

		btnAceptar = new MenuButton();
		Utilidades.configButtons(btnAceptar, null);
		btnAceptar.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/check.png")));
		btnAceptar.setToolTipText("Aceptar");
		btnAceptar.setBounds(size.width - 150, size.height - 75, 50, 25);
//		btnAceptar.addActionListener(this);
		panel.add(btnAceptar);

		btnVolver = new MenuButton();
		Utilidades.configButtons(btnVolver, null);
		btnVolver.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(size.width - 75, size.height - 75, 50, 25);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Estas segur@ de que quieres volver a la ventana anterior?", "¿Quieres volver?",
						OptionPanel.CONFIRM);
				if (i == 1) {
					thisDialog.dispose();
					parent.setVisible(true);
				}

			}

		});
		panel.add(btnVolver);

		if (obra != null) {
			initValues(obra);
			btnAceptar.setEnabled(false);

		} else {
			btnModificar.setEnabled(false);

		}

		fotoLabel = new JLabel();
		fotoLabel.setBounds(10, 280, 300, 150);
		panel.add(fotoLabel);

		addPortada = new MenuButton();
		Utilidades.configButtons(addPortada, "A\u00F1adir portada");
		addPortada.setBounds(10, 250, 125, 23);
		addPortada.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				FileFilter filter = new FileNameExtensionFilter("JPEG file", "jpg", "jpeg", "png");
				fc.setFileFilter(filter);
				int response = fc.showOpenDialog(null);
				try {
					if (response == JFileChooser.APPROVE_OPTION) {
						String pathName = fc.getSelectedFile().getPath();
						ImageIcon icon = new ImageIcon(pathName);
						// Foto
						fotoLabel.setIcon(icon);
					} else {
						JOptionPane.showMessageDialog(null, "Feel Free to Look Later");
					}
				} catch (Exception ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}

			}
		});
		panel.add(addPortada);
//
//		JLabel background = Utilidades.backgroundImg(this);
//		background.setBounds(0, 0, getWidth(), getHeight());
//		panel.add(background);

	}

	private void initValues(ObraAudiovisual obra) {

		if (obra instanceof Pelicula) {
			tabs.setSelectedIndex(0);
			tabs.setEnabledAt(1, false);
			boolean esTaq = ((Pelicula) obra).isEsTaquillera() ? true : false;
			esTaquillera.setSelected(esTaq);
		} else {
			tabs.setSelectedIndex(1);
			tabs.setEnabledAt(0, false);
			for (int i = 1; i == ((Serie) obra).getNombreCap().size(); i++) {
				comboBox.addItem(i);
			}
		}
		fieldNombre.setText(obra.getNombre());
		fieldDuracion.setText(Integer.toString(obra.getDuracion()));
		fieldPresupuesto.setText(Integer.toString(obra.getPresupuesto()));
		fieldFecha.setText(obra.getFechaEstreno() == null ? "" : obra.getFechaEstreno().toString());
	}
}
