package vistas.ventanas.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import controlador.utils.ClasesEnum;
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

public class DatosObra extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private DefaultListModel<String> listModel;
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private MenuButton btnBorrarDatos, btnModificar = null, btnAddObra, btnVolver, btnVerPatrocinadores, addPortada,
			btnVerTrabajadores, btnVerEquipamiento, addTemporada;
	private TextField fieldNombre, fieldDuracion, fieldFecha, fieldPresupuesto, addCap;;
	private JRadioButton esTaquillera = null;
	private TitleBar bar;
	private JList<String> listCap;
	private JComboBox<Integer> comboBox = null;
	private CustomTab tabs;
	private Dimension size;
	private Window parent;
	private JDialog thisDialog;
	private String type;
	private JLabel fotoLabel;
	private File img = null;
	private boolean valido = true;
	private ObraAudiovisual obra = null;

	/**
	 * @wbp.parser.constructor
	 */
	public DatosObra(Window parent, boolean modal, ObraAudiovisual obra, String type) {
		super(parent);
		setModal(modal);
		this.parent = parent;
		this.thisDialog = this;
		this.setUndecorated(true);
		size = Utilidades.resizeWindow(this);
		setSize(size);
		//		Utilidades.centerWindow(parent, this);
		contentPanel.setLayout(null);
		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);

		// Cuando se va ha modificar la obra
		if (obra != null) {
			this.obra = obra;
			init();
			initValues(obra);
		} else {
			// Cuando se va ha añadir uno nuevo
			this.type = type;
			init();
		}

	}

	private void init() {
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

		if (obra != null) {
			initTabs(obra instanceof Pelicula ? ClasesEnum.PELICULA.getName() : ClasesEnum.SERIE.getName());
		} else {
			initTabs(type.equalsIgnoreCase(ClasesEnum.PELICULA.getName()) ? ClasesEnum.PELICULA.getName()
					: ClasesEnum.SERIE.getName());
		}

	}

	private void initTabs(String typeObra) {
		if (typeObra.equalsIgnoreCase(ClasesEnum.PELICULA.getName())) {
			tabs.setSelectedIndex(0);
			tabsFields(peliPanel);
			tabs.setEnabledAt(1, false);
			if (this.obra == null)
				this.obra = new Pelicula();
		} else {
			tabs.setSelectedIndex(1);
			tabsFields(seriePanel);
			tabs.setEnabledAt(0, false);
			if (this.obra == null) {
				this.obra = new Serie();
				comboBox.addItem(1);
			}
		}
	}

	private void tabsFields(JPanel panel) {

		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Datos Obra");
		lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblTitulo.setBounds(375, 32, 171, 36);
		panel.add(lblTitulo);
		// Nombre
		fieldNombre = new TextField();
		fieldNombre.setLabelText("Nombre");
		fieldNombre.setBounds(279, 79, 150, 50);
		panel.add(fieldNombre);

		// Duracion
		fieldDuracion = new TextField();
		fieldDuracion.setLabelText("Duracion");
		fieldDuracion.setBounds(462, 79, 150, 50);
		fieldDuracion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		panel.add(fieldDuracion);

		JLabel fechaError = new JLabel("<html>Error introduzca la fecha en el formato: yyyy-mm-dd</html>");
		fechaError.setBounds(250, 200, 200, 50);
		fechaError.setFont(new Font("Calibri", Font.PLAIN, 14));
		fechaError.setForeground(Color.RED);
		fechaError.setVisible(false);
		panel.add(fechaError);

		// Fecha
		fieldFecha = new TextField();
		fieldFecha.setLabelText("Fecha de estreno :");
		fieldFecha.setBounds(279, 140, 150, 50);
		fieldFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != 45)) {
					e.consume();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				LocalDate validate = Utilidades.validateDate(fieldFecha.getText());
				if (null == validate) {
					fechaError.setVisible(true);
					valido = false;
				} else {
					fechaError.setVisible(false);
					valido = true;
				}
			}
		});
		panel.add(fieldFecha);

		// Presupuesto
		fieldPresupuesto = new TextField();
		fieldPresupuesto.setLabelText("Presupuesto:");
		fieldPresupuesto.setBounds(464, 140, 148, 50);
		fieldPresupuesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
			}
		});
		panel.add(fieldPresupuesto);

		if (panel.equals(peliPanel)) {
			esTaquillera = new JRadioButton("Es Taquillera");
			esTaquillera.setBounds(562, 218, 109, 23);
			esTaquillera.setBackground(Color.WHITE);
			panel.add(esTaquillera);
		} else {
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(660, 160, 261, 130);
			panel.add(scrollPane);

			JLabel lblTemporada = new JLabel("Temporada :");
			lblTemporada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTemporada.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTemporada.setBounds(size.width - 320, 90, 100, 23);
			panel.add(lblTemporada);

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
			comboBox.setBounds(size.width - 290, 120, 80, 20);
			panel.add(comboBox);

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
			addCap.setBounds(660, 301, 260, 50);
			panel.add(addCap);
		}

		tabsButtons(panel, obra);
	}

	private void tabsButtons(JPanel panel, ObraAudiovisual obra) {

		// Botones
		btnVerPatrocinadores = new MenuButton();
		Utilidades.configButtons(btnVerPatrocinadores, "Ver patrocinadores");
		btnVerPatrocinadores.setBounds(size.width - 175, size.height - 120, 150, 25);
		btnVerPatrocinadores.setEnabled(true);
		btnVerPatrocinadores.addActionListener(this);
		panel.add(btnVerPatrocinadores);

		btnVerTrabajadores = new MenuButton();
		Utilidades.configButtons(btnVerTrabajadores, "Ver Trabajadores");
		btnVerTrabajadores.setBounds(size.width - 175, size.height - 150, 150, 25);
		btnVerTrabajadores.setEnabled(true);
		btnVerTrabajadores.addActionListener(this);
		panel.add(btnVerTrabajadores);

		btnVerEquipamiento = new MenuButton();
		Utilidades.configButtons(btnVerEquipamiento, "Ver Equipamiento");
		btnVerEquipamiento.setBounds(size.width - 175, size.height - 180, 150, 25);
		btnVerEquipamiento.setEnabled(true);
		btnVerEquipamiento.addActionListener(this);
		panel.add(btnVerEquipamiento);

		addTemporada = new MenuButton();
		Utilidades.configButtons(addTemporada, "Añadir temporada");
		addTemporada.setBounds(size.width - 190, 120, 150, 25);
		addTemporada.setEnabled(true);
		addTemporada.addActionListener(this);
		panel.add(addTemporada);

		btnBorrarDatos = new MenuButton();
		Utilidades.configButtons(btnBorrarDatos, null);
		btnBorrarDatos
		.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/delete.png")));
		btnBorrarDatos.setToolTipText("Borrar datos");
		btnBorrarDatos.setBounds(size.width - 300, size.height - 75, 50, 25);
		btnBorrarDatos.addActionListener(this);
		panel.add(btnBorrarDatos);

		btnModificar = new MenuButton();
		Utilidades.configButtons(btnModificar, null);
		btnModificar.setIcon(
				new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/edit_button.png")));
		btnModificar.setToolTipText("Modificar datos");
		btnModificar.setBounds(size.width - 225, size.height - 75, 50, 25);
		btnModificar.addActionListener(this);
		panel.add(btnModificar);

		btnAddObra = new MenuButton();
		Utilidades.configButtons(btnAddObra, null);
		btnAddObra.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/plus.png")));
		btnAddObra.setToolTipText("Aceptar");
		btnAddObra.setBounds(size.width - 150, size.height - 75, 50, 25);
		btnAddObra.addActionListener(this);
		panel.add(btnAddObra);

		btnVolver = new MenuButton();
		Utilidades.configButtons(btnVolver, null);
		btnVolver.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(size.width - 75, size.height - 75, 50, 25);
		btnVolver.addActionListener(this);
		btnVolver.setEnabled(true);
		panel.add(btnVolver);

		fotoLabel = new JLabel("Imagen no proporcionada");
		fotoLabel.setBounds(289, 201, 298, 292);
		panel.add(fotoLabel);

		addPortada = new MenuButton();
		Utilidades.configButtons(addPortada, "A\u00F1adir portada");
		addPortada.setBounds(10, 200, 125, 23);
		addPortada.addActionListener(this);
		addPortada.setEnabled(true);
		panel.add(addPortada);

		if (type == null) {
			btnAddObra.setEnabled(false);
			btnBorrarDatos.setEnabled(true);
			btnModificar.setEnabled(true);
		} else {
			btnAddObra.setEnabled(true);
			btnBorrarDatos.setEnabled(false);
			btnModificar.setEnabled(false);
		}

	}

	private void initValues(ObraAudiovisual obra) {

		if (obra instanceof Pelicula) {
			boolean esTaq = ((Pelicula) obra).isEsTaquillera() ? true : false;
			esTaquillera.setSelected(esTaq);
		} else {
			for (int i = 1; i == ((Serie) obra).getNombreCap().size(); i++) {
				comboBox.addItem(i);
			}
		}
		fieldNombre.setText(obra.getNombre());
		fieldDuracion.setText(Integer.toString(obra.getDuracion()));
		fieldPresupuesto.setText(Integer.toString(obra.getPresupuesto()));
		fieldFecha.setText(obra.getFechaEstreno() == null ? "" : obra.getFechaEstreno().toString());
		if (obra.getImgPath() != null) {
			if (!obra.getImgPath().isBlank()) {
				fotoLabel.setIcon(
						Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + obra.getImgPath())));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnBorrarDatos)) {
			if (obra != null) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Está usted seguro de que quiere eliminar esta obra audiovisual?", "Confirmación",
						OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					boolean ok = FactoryDAO.getDeleteData()
							.dataManage(new String[] { ClasesEnum.OBRA.getName(), Integer.toString(obra.getIdObra()) });
					if (ok) {
						OptionPanel.showMessage(thisDialog, "La obra seleccionada ha sido eliminada",
								"Obra audiovisual eliminado", OptionPanel.MESSAGE);
						thisDialog.dispose();
						parent.setVisible(true);
					} else
						OptionPanel.showMessage(thisDialog,
								"Ha occurido un error inesperado al intentar borrar la obra seleccionada",
								"Error eliminar obra", OptionPanel.ERROR);
				}
			}
		} else if (e.getSource().equals(btnModificar)) {
			if (obra != null && valido) {
				obra.setNombre(fieldNombre.getText());
				obra.setDuracion(Integer.parseInt(fieldDuracion.getText()));
				obra.setFechaEstreno(Utilidades.validateDate(fieldFecha.getText()));
				obra.setPresupuesto(Integer.parseInt(fieldPresupuesto.getText()));
				if (img != null)
					obra.setImgPath(img.getPath());
				if (obra instanceof Pelicula) {
					((Pelicula) obra).setEsTaquillera(esTaquillera.isSelected());
				}
				if (FactoryDAO.getUpdateData().dataManage(obra)) {
					OptionPanel.showMessage(thisDialog, "Se ha modificado correctamente los datos de la obra",
							"Modificación de datos", OptionPanel.MESSAGE);
				}
			} else {
				OptionPanel.showMessage(thisDialog, "Error al modificar, compruebe los datos e inténtelo de nuevo",
						"Error al intentar modificar", OptionPanel.MESSAGE);
			}
		} else if (e.getSource().equals(btnAddObra)) {
			boolean ok = false;
			if (valido) {
				int i = OptionPanel.showOptionMessage(thisDialog, "¿Estás segur@ de que quieres insertar esta obra?",
						"Confirmación", OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					obra.setNombre(fieldNombre.getText());
					obra.setDuracion(Integer.parseInt(fieldDuracion.getText()));
					obra.setFechaEstreno(Utilidades.validateDate(fieldFecha.getText()));
					obra.setPresupuesto(Integer.parseInt(fieldPresupuesto.getText()));
					if (obra instanceof Pelicula) {
						((Pelicula) obra).setEsTaquillera(esTaquillera.isSelected());
						obra.setTipo("Pelicula");
					} else
						obra.setTipo("Serie");

					ok = FactoryDAO.getInsertData().dataManage(obra);
				}
			}
			if (!valido || !ok) {
				OptionPanel.showMessage(thisDialog, "Error al añadir la obra, compruebe los datos e inténtelo de nuevo",
						"Error al intentar insertar", OptionPanel.MESSAGE);
			}

		} else if (e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "¿Quieres volver?",
					OptionPanel.CONFIRM);
			if (i == OptionPanel.CONFIRM) {
				thisDialog.dispose();
				parent.setVisible(true);
			}
		} else if (e.getSource().equals(addPortada)) {
			img = Utilidades.addFoto();
			if (img != null) {
				// Foto
				fotoLabel.setIcon(Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + img)));
				thisDialog.repaint();
			}
		} else if (e.getSource().equals(addTemporada)) {
			((Serie) obra).getNombreCap().add(new ArrayList<>());
			comboBox.addItem(comboBox.getItemCount() + 1);

		} else if (e.getSource().equals(btnVerPatrocinadores)) {

		} else if (e.getSource().equals(btnVerEquipamiento)) {

		} else if (e.getSource().equals(btnVerTrabajadores)) {

		}

	}
}
