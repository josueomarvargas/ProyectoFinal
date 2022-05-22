package vistas.ventanas.data;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

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
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Pelicula;
import modelo.clases.Serie;
import modelo.clases.Trabajador;
import vistas.dao.CheckLogin;
import vistas.dao.RelationData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.table.TablaRelaciones;

public class DatosObra extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel peliPanel = new JPanel();
	private final JPanel seriePanel = new JPanel();
	private Trabajador user = CheckLogin.getLogin();
	private MenuButton btnBorrarDatos, btnModificar = null, btnAddObra, btnVolver, btnVerPatrocinadores, addPortada,
			btnVerTrabajadores, btnVerEquipamiento, addTemporada;
	private TextField fieldNombre, fieldDuracion, fieldFecha, fieldPresupuesto, addCap;;
	private JRadioButton esTaquillera = null;
	private DefaultListModel<String> listModel;
	private JList<String> listCap;
	private TitleBar bar;
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

		Utilidades.centerWindow(parent, this);

		contentPanel.setLayout(null);

		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);

		// Cuando se va ha modificar la obra
		if (obra != null) {
			this.obra = obra;
			init();
			initValues();
		} else {
			// Cuando se va ha añadir uno nuevo
			this.type = type;
			this.valido = false;
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
			if (this.obra == null) {
				this.obra = new Pelicula();
				this.obra.setTipo("Pelicula");
			}
		} else {
			tabs.setSelectedIndex(1);
			tabsFields(seriePanel);
			tabs.setEnabledAt(0, false);
			if (this.obra == null) {
				this.obra = new Serie();
				obra.setTipo("Serie");
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
=======

<<<<<<< HEAD
>>>>>>> 0eb93779c040273dc6c5e24fe7d9afc3a015e84d

=======
>>>>>>> 02931d77581c2c5fc75eaf1ab1c10763fc16ee96
		JLabel invalidCharacter = new JLabel();
		invalidCharacter.setText("Ese caracter es inválido.");
		invalidCharacter.setFont(new Font("Calibri", Font.PLAIN, 14));
		invalidCharacter.setForeground(Color.RED);

<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 89bb8fce0cc912af3e1e9b80e4fef56badfa70da
=======

>>>>>>> 0eb93779c040273dc6c5e24fe7d9afc3a015e84d
=======
>>>>>>> 02931d77581c2c5fc75eaf1ab1c10763fc16ee96
		// Nombre
		fieldNombre = new TextField();
		fieldNombre.setLabelText("Nombre");
		fieldNombre.setBounds(280, 80, 150, 50);
		fieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, invalidCharacter, 285, 140);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
			}
		});
		panel.add(fieldNombre);

		// Duracion
		fieldDuracion = new TextField();
		fieldDuracion.setLabelText("Duracion");
		fieldDuracion.setBounds(462, 80, 150, 50);
		fieldDuracion.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, invalidCharacter, 465, 140);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					valido = false;
					e.consume();
					toolTip(p);
				}
				valido = true;

			}
		});
		panel.add(fieldDuracion);

		JLabel fechaError = new JLabel("<html>Error introduzca la fecha en el formato: yyyy-mm-dd</html>");
		fechaError.setBounds(250, 220, 200, 50);
		fechaError.setFont(new Font("Calibri", Font.PLAIN, 14));
		fechaError.setForeground(Color.RED);
		fechaError.setVisible(false);
		panel.add(fechaError);

		// Fecha
		fieldFecha = new TextField();
		fieldFecha.setLabelText("Fecha de estreno :");
		fieldFecha.setBounds(279, 160, 150, 50);
		fieldFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != 45)) {
					e.consume();
				}
			}
		});
		fieldFecha.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
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
		fieldPresupuesto.setBounds(464, 160, 148, 50);
		fieldPresupuesto.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, invalidCharacter, 464, 220);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					valido = false;
					e.consume();
					toolTip(p);
				}
				valido = true;
			}
		});
		panel.add(fieldPresupuesto);

		if (panel.equals(peliPanel)) {
			esTaquillera = new JRadioButton("Es Taquillera");
			esTaquillera.setBounds(562, 240, 109, 23);
			esTaquillera.setBackground(Color.WHITE);
			panel.add(esTaquillera);
		} else {

			JLabel lblTemporada = new JLabel("Temporada :");
			lblTemporada.setHorizontalAlignment(SwingConstants.RIGHT);
			lblTemporada.setFont(new Font("Calibri", Font.PLAIN, 14));
			lblTemporada.setBounds(size.width - 320, 90, 100, 23);
			panel.add(lblTemporada);

			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(660, 160, 261, 130);
			panel.add(scrollPane);

			listCap = new JList<>();
			listCap.setToolTipText("Click derecho para eliminar un capitulo");
			listCap.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (SwingUtilities.isRightMouseButton(e)) {
						int i = OptionPanel.showOptionMessage(thisDialog, "¿Quieres eliminar este capítulo?",
								"Eliminar caracteristica", OptionPanel.CONFIRM);
						if (i == 1) {
							listModel = (DefaultListModel<String>) listCap.getModel();
							listModel.remove(listCap.getSelectedIndex());
						}
					}
				}
			});
			scrollPane.setViewportView(listCap);
			listCap.setBorder(BorderFactory.createLineBorder(Color.GRAY));

			comboBox = new JComboBox<>();
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
			addCap.setBounds(660, 301, 260, 50);
			addCap.addKeyListener(new KeyAdapter() {
				@Override
				public void keyTyped(KeyEvent e) {
					Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, invalidCharacter, 660, 360);
					char c = e.getKeyChar();
					if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
						if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_ENTER)) {
							valido = false;
							e.consume();
							toolTip(p);
						}
					}
					valido = true;
				}
			});
			addCap.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					listModel = (DefaultListModel<String>) listCap.getModel();
					if (!addCap.getText().isEmpty()) {
						listModel.addElement(addCap.getText());
						((Serie) obra).getNombreCap().get(comboBox.getSelectedIndex()).add(addCap.getText());
						addCap.setText("");
					} else {
						Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog,
								new JLabel("Error, no se puede introducir un tipo vacío"), 285, 140);
						toolTip(p);
					}
				}
			});
			panel.add(addCap);

			addTemporada = new MenuButton();
			Utilidades.configButtons(addTemporada, "Añadir temporada");
			addTemporada.setBounds(size.width - 190, 120, 150, 25);
			addTemporada.setEnabled(true);
			addTemporada.addActionListener(this);
			panel.add(addTemporada);
		}

		tabsButtons(panel);
	}

	private void tabsButtons(JPanel panel) {

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
		fotoLabel.setBounds(289, 250, 298, 292);
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
		if (user.getTipo().equalsIgnoreCase("tecnicoaudiovisual")) {
			btnVerEquipamiento.setEnabled(true);
			btnVerPatrocinadores.setEnabled(false);
			btnVerTrabajadores.setEnabled(false);
		}

	}

	private void initValues() {

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

	private void toolTip(Popup p) {
		p.show();
		// create a timer to hide the popup later
		Timer t = new Timer(500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				p.hide();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	private void updateData() {
		obra.setNombre(fieldNombre.getText());
		obra.setDuracion(Integer.parseInt(fieldDuracion.getText()));
		obra.setFechaEstreno(Utilidades.validateDate(fieldFecha.getText()));
		obra.setPresupuesto(Integer.parseInt(fieldPresupuesto.getText()));
		if (img != null)
			obra.setImgPath(img.getPath());
		if (obra instanceof Pelicula) {
			((Pelicula) obra).setEsTaquillera(esTaquillera.isSelected());
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean ok = false;
		if (e.getSource().equals(btnBorrarDatos)) {
			if (obra != null) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Está usted seguro de que quiere eliminar esta obra audiovisual?", "Confirmación",
						OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					ok = FactoryDAO.getDeleteData()
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
				updateData();
				if (FactoryDAO.getUpdateData().dataManage(obra)) {
					OptionPanel.showMessage(thisDialog, "Se ha modificado correctamente los datos de la obra",
							"Modificación de datos", OptionPanel.MESSAGE);
				}
			} else {
				OptionPanel.showMessage(thisDialog, "Error al modificar, compruebe los datos e inténtelo de nuevo",
						"Error al intentar modificar", OptionPanel.MESSAGE);
			}
		} else if (e.getSource().equals(btnAddObra)) {
			if (valido) {
				updateData();
				int i = OptionPanel.showOptionMessage(thisDialog, "¿Estás segur@ de que quieres insertar esta obra?",
						"Confirmación", OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					ok = FactoryDAO.getInsertData().dataManage(obra);
				}
			}
			if (ok) {
				OptionPanel.showMessage(thisDialog, "Se ha añadido correctamente los datos de la obra",
						"Modificación de datos", OptionPanel.MESSAGE);
				thisDialog.dispose();
			} else if (!valido || !ok) {
				OptionPanel.showMessage(thisDialog,
						"Error al añadir la obra, compruebe los datos e inténtelo de nuevo",
						"Error al intentar insertar", OptionPanel.MESSAGE);
			}

		} else if (e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "Volver", OptionPanel.CONFIRM);
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

		} else if (e.getSource().equals(btnVerEquipamiento)) {
			viewRelationData(ClasesEnum.EQUIPOBRA.getName(), ClasesEnum.OBRA.getName(),
					"Equipamiento que se usa en la obra", "Añadir equipamiento a la obra");
		} else if (e.getSource().equals(btnVerTrabajadores)) {
			viewRelationData(ClasesEnum.PARTICIPA.getName(), ClasesEnum.OBRA.getName(),
					"Trabajadores que participan en la obra", "Añadir trabajador a la obra");
		} else if (e.getSource().equals(btnVerPatrocinadores)) {
			viewRelationData(ClasesEnum.PROMOCIONA.getName(), ClasesEnum.OBRA.getName(),
					"Patrocinadores que patrocinan en la obra", "Añadir patrocinadores a la obra");
		}

	}

	private void viewRelationData(String relationTable, String individualTable, String title1, String title2) {
		int newID = -1;
		String[] id = { relationTable, individualTable, Integer.toString(obra.getIdObra()) };
		DefaultTableModel relationData = FactoryDAO.getRelationData().dataManage(id);
		DefaultTableModel allData = null;

		if (relationTable.equals(ClasesEnum.EQUIPOBRA.getName())) {
			allData = RelationData.getEquipModel();
		} else if (relationTable.equals(ClasesEnum.PARTICIPA.getName())) {
			allData = RelationData.getTrabajadorModel();
		} else {
			allData = RelationData.getPatroModel();
		}
		if (relationData != null && allData != null) {
			newID = TablaRelaciones.showDataRelation(thisDialog, relationData, allData, title1, title2);
		} else {
			newID = TablaRelaciones.addDataToRelation(thisDialog, allData, title2);
		}
		if (newID != -1 && newID != -2) {
			boolean okUpdate = FactoryDAO.getRelationData()
					.updateRelation(new String[] { relationTable, individualTable, Integer.toString(newID) });

			if (okUpdate) {
				OptionPanel.showMessage(thisDialog, "se ha insertado correctamente la información a la obra",
						"Inserción de datos", OptionPanel.MESSAGE);
			} else {
				OptionPanel.showMessage(thisDialog, "Error al añadir información a la obra, inténtelo más tarde",
						"Error en la conexión a la base de datos", OptionPanel.MESSAGE);
			}
		}
	}
}
