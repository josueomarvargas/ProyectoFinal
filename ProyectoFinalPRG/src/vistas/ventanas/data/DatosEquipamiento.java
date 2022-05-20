package vistas.ventanas.data;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;

import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.Equipamiento;
import vistas.dao.RelationData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.table.TablaRelaciones;

public class DatosEquipamiento extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JDialog thisDialog;
	private final Window parent;
	private MenuButton btnAdd, btnBorrarDatos, btnModificar, btnVolver, addFoto, btnVerObras;
	private TextField fieldNombre, addCategoria, addTipo;
	private JComboBox<String> comboBox = null;
	private JLabel fotoLabel;
	private Dimension size;
	private DefaultListModel<String> listModel = new DefaultListModel<>();
	private JList<String> listCaracteristica;
	private Equipamiento equip = null;
	private File img = null;
	private boolean valido = true;

	public DatosEquipamiento(Window parent, boolean modal, Equipamiento equip) {
		super(parent);
		this.parent = parent;
		setModal(modal);
		this.setUndecorated(true);
		size = Utilidades.resizeWindow(this);
		setSize(size);
		Utilidades.centerWindow(parent, this);
		thisDialog = this;
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);

		if (equip != null) {
			this.equip = equip;
			fields();
			initValues();
		} else {
			this.valido = false;
			fields();
		}

		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	private void fields() {
		JLabel lblDatosEquipamiento = new JLabel("Datos Equipamiento");
		lblDatosEquipamiento.setBounds(347, 47, 266, 29);
		lblDatosEquipamiento.setFont(new Font("Calibri", Font.BOLD, 28));
		contentPanel.add(lblDatosEquipamiento);

		JLabel error = new JLabel();
		error.setText("Ese caracter es inválido.");
		error.setFont(new Font("Calibri", Font.PLAIN, 14));
		error.setForeground(Color.RED);

		fieldNombre = new TextField();
		fieldNombre.setLabelText("Nombre");
		fieldNombre.setBounds(347, 150, 225, 45);
		fieldNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 350, 200);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				} else
					valido = true;
			}
		});
		contentPanel.add(fieldNombre);

		JLabel tipo = new JLabel("Tipo");
		tipo.setBounds(347, 235, 266, 14);
		tipo.setFont(new Font("Calibri", Font.PLAIN, 14));
		contentPanel.add(tipo);

		comboBox = new JComboBox<>();
		comboBox.setModel(vistas.ventanas.table.TablaEquipamiento.tipoEquip());
		comboBox.setBounds(347, 250, 225, 35);
		comboBox.setSelectedIndex(-1);
		contentPanel.add(comboBox);

		addTipo = new TextField();
		addTipo.setLabelText("Añadir tipo de equipamiento");
		addTipo.setToolTipText("Dale Enter para introducir un tipo");
		addTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!addTipo.getText().isEmpty()) {
					comboBox.addItem(addTipo.getText());
					addTipo.setText("");
					comboBox.setSelectedIndex(comboBox.getModel().getSize());
					valido = true;
				} else {
					Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog,
							new JLabel("Error, no se puede introducir un tipo vacío"), 350, 325);
					toolTip(p);
				}
			}
		});
		addTipo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 350, 360);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_ENTER)) {
						e.consume();
						toolTip(p);
					}
				} else
					valido = true;
			}
		});
		addTipo.setBounds(348, 300, 220, 50);
		contentPanel.add(addTipo);

		JLabel caracteristica = new JLabel("Caracteristicas:");
		caracteristica.setFont(new Font("Calibri", Font.PLAIN, 14));
		caracteristica.setBounds(660, 140, 150, 15);
		contentPanel.add(caracteristica);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(660, 160, 261, 130);
		contentPanel.add(scrollPane);

		listCaracteristica = new JList<>();
		listCaracteristica.setModel(listModel);
		listCaracteristica.setToolTipText("Click derecho para eliminar una caracteristica");
		listCaracteristica.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int i = OptionPanel.showOptionMessage(thisDialog, "¿Quieres eliminar esta caracteristica?",
							"Eliminar caracteristica", OptionPanel.CONFIRM);
					if (i == 1) {
						listModel = (DefaultListModel<String>) listCaracteristica.getModel();
						listModel.remove(listCaracteristica.getSelectedIndex());
					}
				}
			}
		});
		scrollPane.setViewportView(listCaracteristica);
		listCaracteristica.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		addCategoria = new TextField();
		addCategoria.setLabelText("Introducir caracteristicas");
		addCategoria.setToolTipText("Dale Enter para introducir una caracteristica");
		addCategoria.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 660, 315);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE) && (c != KeyEvent.VK_ENTER)) {
						e.consume();
						toolTip(p);
					}
				} else
					valido = true;
			}
		});
		addCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listModel = (DefaultListModel<String>) listCaracteristica.getModel();
				if (!addCategoria.getText().isEmpty()) {
					listModel.addElement(addCategoria.getText());
					equip.getCaracteristicas().add(addCategoria.getText());
					addCategoria.setText("");
					valido = true;
				} else {
					JLabel textoVacio = new JLabel("Error, no se puede insertar una categoria vacía");
					textoVacio.setForeground(Color.RED);
					textoVacio.setFont(new Font("Calibri", Font.PLAIN, 14));
					Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, textoVacio, 660, 335);
					toolTip(p);
				}
			}
		});
		addCategoria.setBounds(660, 301, 260, 50);
		contentPanel.add(addCategoria);
		buttons();
	}

	private void buttons() {
		// Botones
		btnVerObras = new MenuButton();
		Utilidades.configButtons(btnVerObras, "Ver obras");
		btnVerObras.setToolTipText("Mostrar las obras en las que se usa");
		btnVerObras.setBounds(size.width - 175, size.height - 120, 150, 25);
		btnVerObras.setEnabled(true);
		btnVerObras.addActionListener(this);
		contentPanel.add(btnVerObras);

		btnBorrarDatos = new MenuButton();
		Utilidades.configButtons(btnBorrarDatos, null);
		btnBorrarDatos
				.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/delete.png")));
		btnBorrarDatos.setToolTipText("Borrar datos");
		btnBorrarDatos.setBounds(size.width - 300, size.height - 75, 50, 25);
		btnBorrarDatos.addActionListener(this);
		contentPanel.add(btnBorrarDatos);

		btnModificar = new MenuButton();
		Utilidades.configButtons(btnModificar, null);
		btnModificar.setIcon(
				new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/edit_button.png")));
		btnModificar.setToolTipText("Modificar datos");
		btnModificar.setBounds(size.width - 225, size.height - 75, 50, 25);
		btnModificar.addActionListener(this);
		contentPanel.add(btnModificar);

		btnAdd = new MenuButton();
		Utilidades.configButtons(btnAdd, null);
		btnAdd.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/plus.png")));
		btnAdd.setToolTipText("Aceptar");
		btnAdd.setBounds(size.width - 150, size.height - 75, 50, 25);
		btnAdd.addActionListener(this);
		contentPanel.add(btnAdd);

		btnVolver = new MenuButton();
		Utilidades.configButtons(btnVolver, null);
		btnVolver.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(size.width - 75, size.height - 75, 50, 25);
		btnVolver.addActionListener(this);
		btnVolver.setEnabled(true);
		contentPanel.add(btnVolver);

		fotoLabel = new JLabel("Imagen no proporcionada");
		fotoLabel.setBounds(20, 223, 298, 292);
		contentPanel.add(fotoLabel);

		addFoto = new MenuButton();
		Utilidades.configButtons(addFoto, "A\u00F1adir imagen");
		addFoto.setBounds(10, 200, 125, 23);
		addFoto.addActionListener(this);
		addFoto.setEnabled(true);
		contentPanel.add(addFoto);

		if (equip == null) {
			this.equip = new Equipamiento();
			btnAdd.setEnabled(true);
			btnBorrarDatos.setEnabled(false);
			btnModificar.setEnabled(false);
		} else {
			btnAdd.setEnabled(false);
			btnBorrarDatos.setEnabled(true);
			btnModificar.setEnabled(true);
		}
	}

	private void initValues() {
		fieldNombre.setText(equip.getNombre());

		if (equip.getImgPath() != null) {
			if (!equip.getImgPath().isBlank()) {
				fotoLabel.setIcon(
						Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + equip.getImgPath())));
			}
		}

		boolean found = false;
		for (int i = 0; i < comboBox.getModel().getSize() && !found; i++) {
			if (equip.getTipo().equalsIgnoreCase(comboBox.getModel().getElementAt(i))) {
				comboBox.setSelectedIndex(i);
				found = true;
			}
		}

		listModel = new DefaultListModel<>();
		for (String categoria : equip.getCaracteristicas()) {
			listModel.addElement(categoria);
		}
		listCaracteristica.setModel(listModel);
	}

	private void updateData() {
		equip.setNombre(fieldNombre.getText());
		equip.setTipo((String) comboBox.getSelectedItem());
		if (img != null) {
			equip.setImgPath(img.getPath());
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

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean ok = false;
		if (e.getSource().equals(btnAdd)) {
			if (valido) {
				updateData();
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Estás segur@ de que quieres insertar este equipamiento?", "Confirmación",
						OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					ok = FactoryDAO.getInsertData().dataManage(equip);
				}
			}

			if (ok) {
				OptionPanel.showMessage(thisDialog, "Se ha añadido correctamente los datos del equipamiento",
						"Modificación de datos", OptionPanel.MESSAGE);
			} else if (!valido || !ok) {
				OptionPanel.showMessage(thisDialog,
						"Error al añadir el equipamiento, compruebe los datos e inténtelo de nuevo",
						"Error al intentar insertar", OptionPanel.MESSAGE);
			}

		} else if (e.getSource().equals(btnBorrarDatos)) {
			if (equip != null) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Está usted seguro de que quiere eliminar este equipamiento?", "Confirmación",
						OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					ok = FactoryDAO.getDeleteData().dataManage(
							new String[] { ClasesEnum.EQUIPAMIENTO.getName(), Integer.toString(equip.getIdEquip()) });
					if (ok) {
						OptionPanel.showMessage(thisDialog, "El equipamiento seleccionado ha sido eliminado",
								"Equipamiento eliminado", OptionPanel.MESSAGE);
						thisDialog.dispose();
						parent.setVisible(true);
					} else
						OptionPanel.showMessage(thisDialog,
								"Ha occurido un error inesperado al intentar borrar el equipamiento", "Error eliminar",
								OptionPanel.ERROR);
				}
			}
		} else if (e.getSource().equals(btnModificar)) {
			if (equip != null && valido) {
				updateData();
				if (FactoryDAO.getUpdateData().dataManage(equip)) {
					OptionPanel.showMessage(thisDialog, "Se ha modificado correctamente los datos del equipamiento",
							"Modificación de datos", OptionPanel.MESSAGE);
				}
			} else {
				OptionPanel.showMessage(thisDialog, "Error al modificar, compruebe los datos e inténtelo de nuevo",
						"Error al intentar modificar", OptionPanel.MESSAGE);
			}
		} else if (e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "Volver", OptionPanel.CONFIRM);
			if (i == OptionPanel.CONFIRM) {
				thisDialog.dispose();
				parent.setVisible(true);
			}
		} else if (e.getSource().equals(addFoto)) {
			img = Utilidades.addFoto();
			if (img != null) {
				// Foto
				fotoLabel.setIcon(Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + img)));
				thisDialog.repaint();
			}
		} else if (e.getSource().equals(btnVerObras)) {
			String[] id = { ClasesEnum.EQUIPOBRA.getName(), ClasesEnum.EQUIPAMIENTO.getName(),
					Integer.toString(equip.getIdEquip()) };
			DefaultTableModel relationData = FactoryDAO.getRelationData().dataManage(id);
			DefaultTableModel allData = RelationData.getObraModel();

			thisDialog.dispose();
			int newID = TablaRelaciones.showDataRelation(thisDialog, relationData, allData,
					"Obras en las que participa", "Añadir obras");

			if (newID == -1) {
				boolean okUpdate = FactoryDAO.getRelationData().updateRelation(new String[] {
						ClasesEnum.EQUIPOBRA.getName(), ClasesEnum.EQUIPAMIENTO.getName(), Integer.toString(newID) });

				if (!okUpdate) {
					OptionPanel.showMessage(thisDialog,
							"Error al añadir la obra que usa este equipamiento, inténtelo más tarde",
							"Añadir Equipamiento a la obra", OptionPanel.MESSAGE);
				}
			}
		}
	}
}
