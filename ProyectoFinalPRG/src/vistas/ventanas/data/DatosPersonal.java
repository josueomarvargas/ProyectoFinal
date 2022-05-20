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
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import com.toedter.calendar.JCalendar;

import Atxy2k.CustomTextField.RestrictedTextField;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.Actor;
import modelo.clases.Director;
import modelo.clases.Guionista;
import modelo.clases.TecnicoAudiovisual;
import modelo.clases.Trabajador;
import modelo.clases.Usuario;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;

public class DatosPersonal extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private TextField textTelefono;
	private TextField directorCategoria;
	private TextField dniField;
	private TextField textFecha;
	private TextField direccionField;
	private TextField nombreField;
	private TextField apellidoField;
	private TextField numPremioField;
	private TextField datoField;
	private MenuButton btnModificar;
	private MenuButton btnAdd;
	private MenuButton btnVolver;
	private MenuButton btnBorrarDatos;
	private MenuButton botonCalendar;
	private MenuButton btnUsuario;
	private JCalendar calendar;
	private JScrollPane scrollPane;

	private JPanel panelDirector;
	private JPanel panelActor;
	private JPanel panelGuionista;
	private JPanel panelAudiovisual;
	private JPanel panelCalendar;
	private JList<String> tecnicoList;
	private JList<String> actorList;
	private JList<String> guionistaList;
	private DefaultListModel<String> tecListModel = new DefaultListModel<>();
	private DefaultListModel<String> actorListModel = new DefaultListModel<>();
	private DefaultListModel<String> guionListModel = new DefaultListModel<>();
	private ButtonGroup grupo;
	private JRadioButton rdbDirector;
	private JRadioButton rdbtnActor;
	private JRadioButton rdbtnGuionista;
	private JRadioButton rdbtnTecnicoAudiovisual;
	private boolean entra = false;
	private Window parent;
	private Dimension size;
	private JDialog thisDialog;
	private RestrictedTextField r;
	private boolean valido = false;
	private Trabajador trabajador;
	private Usuario user;
	private List<String> especialidad = new ArrayList<>();
	private List<String> areaTrabajo = new ArrayList<>();
	private List<String> tipoGuion = new ArrayList<>();

	public DatosPersonal(Window parent, boolean modal, Trabajador trabajador, Usuario user) {
		super(parent);
		setModal(modal);

		this.setUndecorated(true);
		this.parent = parent;
		thisDialog = this;
		size = Utilidades.resizeWindow(this);
		setSize(size);
		Utilidades.centerWindow(parent, this);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);

		init();
		fields();
		radioButton();
		buttons();
		calendar();

		if (trabajador != null) {
			if (user != null) {
				this.user = user;
			}
			if (trabajador.getTipo().equalsIgnoreCase("director")
					|| trabajador.getTipo().equalsIgnoreCase("tecnicoaudiovisual")) {
				btnUsuario.setEnabled(true);
				this.user = new Usuario();
				this.user.setIdTrabajador(trabajador.getIdTrabajador());
			} else {
				btnUsuario.setEnabled(false);
			}
			this.trabajador = trabajador;
			btnModificar.setEnabled(true);
			btnBorrarDatos.setEnabled(true);
			btnAdd.setEnabled(false);
			initData();
		} else

		{
			btnModificar.setEnabled(false);
			btnBorrarDatos.setEnabled(false);
			btnAdd.setEnabled(true);
		}
		scrollPane.setViewportView(contentPanel);

		getContentPane().add(scrollPane, BorderLayout.CENTER);
	}

	private void init() {

		contentPanel.setLayout(null);
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, thisDialog.getWidth(), thisDialog.getHeight());

		JLabel titulo = new JLabel("Datos Personales");
		titulo.setFont(new Font("Calibri", Font.PLAIN, 28));
		titulo.setBounds(409, 36, 262, 29);
		contentPanel.add(titulo);

		// Tecnico
		panelAudiovisual = new JPanel();
		panelAudiovisual.setBackground(Color.WHITE);
		panelAudiovisual.setBounds(231, 353, 353, 160);
		panelAudiovisual.setLayout(null);
		panelAudiovisual.setVisible(false);
		contentPanel.add(panelAudiovisual);

		JScrollPane tecnicoScroll = new JScrollPane();
		tecnicoScroll.setBounds(92, 8, 261, 130);
		panelAudiovisual.add(tecnicoScroll);

		tecnicoList = new JList<>();
		tecnicoList.setModel(tecListModel);
		tecnicoList.setToolTipText("Click derecho para eliminar un dato");
		tecnicoList.setBounds(0, 0, 100, 100);
		tecnicoList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int i = OptionPanel.showOptionMessage(thisDialog, "¿Quieres eliminar este dato?",
							"Eliminar caracteristica", OptionPanel.CONFIRM);
					if (i == 1) {
						tecListModel = (DefaultListModel<String>) tecnicoList.getModel();
						tecListModel.remove(tecnicoList.getSelectedIndex());
					}
				}
			}
		});
		tecnicoScroll.setViewportView(tecnicoList);
		tecnicoList.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel areaLabel = new JLabel("Area Trabajo :");
		areaLabel.setBounds(10, 11, 88, 19);
		areaLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panelAudiovisual.add(areaLabel);

		// Actor
		panelActor = new JPanel();
		panelActor.setBounds(231, 353, 353, 160);
		panelActor.setBackground(Color.WHITE);
		panelActor.setLayout(null);
		contentPanel.add(panelActor);

		JScrollPane actorScroll = new JScrollPane();
		actorScroll.setBounds(92, 8, 261, 130);
		panelActor.add(actorScroll);

		actorList = new JList<>();
		actorList.setModel(actorListModel);
		actorList.setToolTipText("Click derecho para eliminar un dato");
		actorList.setBounds(0, 0, 100, 100);
		actorList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int i = OptionPanel.showOptionMessage(thisDialog, "¿Quieres eliminar este dato?",
							"Eliminar caracteristica", OptionPanel.CONFIRM);
					if (i == 1) {
						actorListModel = (DefaultListModel<String>) actorList.getModel();
						actorListModel.remove(actorList.getSelectedIndex());
					}
				}
			}
		});
		actorScroll.setViewportView(actorList);
		actorList.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel especialidadLabel = new JLabel("Especialidad :");
		especialidadLabel.setBounds(10, 10, 90, 19);
		panelActor.add(especialidadLabel);
		especialidadLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panelActor.setVisible(false);

		// Director
		panelDirector = new JPanel();
		panelDirector.setBounds(300, 350, 200, 100);
		panelDirector.setBackground(Color.WHITE);
		panelDirector.setLayout(null);

		directorCategoria = new TextField();
		directorCategoria.setLabelText("Categoria");
		directorCategoria.setBounds(100, 10, 140, 45);
		panelDirector.add(directorCategoria);
		panelDirector.setVisible(false);
		contentPanel.add(panelDirector);

		// Guionista
		panelGuionista = new JPanel();
		panelGuionista.setBounds(231, 353, 353, 160);
		panelGuionista.setBackground(Color.WHITE);
		panelGuionista.setLayout(null);
		contentPanel.add(panelGuionista);

		JScrollPane guionScroll = new JScrollPane();
		guionScroll.setBounds(92, 8, 261, 130);
		panelGuionista.add(guionScroll);

		guionistaList = new JList<>();
		guionistaList.setModel(guionListModel);
		guionistaList.setToolTipText("Click derecho para eliminar un dato");
		guionistaList.setBounds(0, 0, 100, 100);
		guionistaList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e)) {
					int i = OptionPanel.showOptionMessage(thisDialog, "¿Quieres eliminar este dato?",
							"Eliminar caracteristica", OptionPanel.CONFIRM);
					if (i == 1) {
						guionListModel = (DefaultListModel<String>) guionistaList.getModel();
						guionListModel.remove(guionistaList.getSelectedIndex());
					}
				}
			}
		});
		guionScroll.setViewportView(guionistaList);
		guionistaList.setBorder(BorderFactory.createLineBorder(Color.GRAY));

		JLabel guionLabel = new JLabel("Tipo Guion :");
		guionLabel.setBounds(10, 10, 90, 19);
		panelGuionista.add(guionLabel);
		guionLabel.setFont(new Font("Calibri", Font.PLAIN, 12));
		panelGuionista.setVisible(false);

	}

	private void fields() {
		JLabel error = new JLabel();
		error.setText("Ese carácter es inválido");
		error.setFont(new Font("Calibri", Font.PLAIN, 12));
		error.setForeground(Color.RED);

		// Nombre
		nombreField = new TextField();
		nombreField.setLabelText("Nombre");
		nombreField.setBounds(406, 137, 148, 45);
		nombreField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 410, 185);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
				valido = true;
			}
		});
		contentPanel.add(nombreField);

		// Apellido
		apellidoField = new TextField();
		apellidoField.setLabelText("Apellido");
		apellidoField.setBounds(594, 137, 148, 45);
		apellidoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 600, 185);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
				valido = true;
			}
		});
		contentPanel.add(apellidoField);

		// DNI
		dniField = new TextField();
		dniField.setLabelText("DNI");
		dniField.setBounds(224, 137, 148, 45);
		dniField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 225, 185);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
				valido = true;
			}
		});
		r = new RestrictedTextField(dniField);
		r.setLimit(9);
		contentPanel.add(dniField);

		// Dirección
		direccionField = new TextField();
		direccionField.setLabelText("Dirección");
		direccionField.setBounds(594, 206, 148, 45);
		direccionField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 600, 235);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
				valido = true;
			}
		});
		contentPanel.add(direccionField);

		// Num Premios
		numPremioField = new TextField();
		numPremioField.setLabelText("N.Premio\r\n");
		numPremioField.setBounds(629, 279, 56, 45);
		numPremioField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
				}
				valido = true;
			}
		});
		contentPanel.add(numPremioField);

		// Fecha nacimiento
		textFecha = new TextField();
		textFecha.setLabelText("Fecha de nacimiento");
		textFecha.setBounds(224, 206, 148, 45);
		textFecha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE) && (c != 45)) {
					e.consume();
				}
			}
		});
		contentPanel.add(textFecha);

		JLabel telferror = new JLabel();
		telferror.setText("El número de teléfono debe de ser de 9 dígitios y sin caracteres especiales");
		telferror.setFont(new Font("Calibri", Font.PLAIN, 12));
		telferror.setForeground(Color.RED);

		// Num telefono
		textTelefono = new TextField();
		textTelefono.setLabelText("N\u00FAmero de tel\u00E9fono\r\n");
		textTelefono.setBounds(409, 206, 148, 45);
		textTelefono.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, telferror, 400, 255);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c != KeyEvent.VK_BACK_SPACE)) {
					e.consume();
					toolTip(p);

				}
			}
		});
		contentPanel.add(textTelefono);

		datoField = new TextField();
		datoField.setLabelText("Insertar dato");
		datoField.setBounds(594, 369, 148, 45);
		datoField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, error, 600, 390);
				char c = e.getKeyChar();
				if ((c < '0') || (c > '9') && (c < 65) || (c > 90) && ((c < 97) || (c > 122))) {
					if ((c != KeyEvent.VK_BACK_SPACE) && (c != KeyEvent.VK_SPACE)) {
						e.consume();
						toolTip(p);
					}
				}
				valido = true;
			}
		});
		r = new RestrictedTextField(datoField);
		r.setOnlyText(true);
		datoField.setVisible(false);
		datoField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!datoField.getText().isEmpty()) {
					if (rdbtnActor.isSelected()) {
						actorListModel = (DefaultListModel<String>) actorList.getModel();
						actorListModel.addElement(datoField.getText());
						especialidad.add(datoField.getText());
					} else if (rdbtnTecnicoAudiovisual.isSelected()) {
						tecListModel = (DefaultListModel<String>) tecnicoList.getModel();
						tecListModel.addElement(datoField.getText());
						areaTrabajo.add(datoField.getText());
					} else {
						guionListModel = (DefaultListModel<String>) guionistaList.getModel();
						guionListModel.addElement(datoField.getText());
						tipoGuion.add(datoField.getText());
					}
					datoField.setText("");
					valido = true;
				} else {
					JLabel textoVacio = new JLabel("Error, no se puede insertar un dato vacío");
					textoVacio.setForeground(Color.RED);
					textoVacio.setFont(new Font("Calibri", Font.PLAIN, 14));
					Popup p = PopupFactory.getSharedInstance().getPopup(thisDialog, textoVacio, 660, 395);
					toolTip(p);
				}
			}
		});
		contentPanel.add(datoField);
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

	private void calendar() {
		panelCalendar = new JPanel();
		panelCalendar.setBackground(Color.CYAN);
		panelCalendar.setBounds(11, 262, 210, 169);
		panelCalendar.setLayout(null);
		entra = false;
		panelCalendar.setVisible(entra);
		contentPanel.add(panelCalendar);

		calendar = new JCalendar();
		calendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				if (evt.getOldValue() != null) {
					SimpleDateFormat ff = new SimpleDateFormat("yyyy-MM-dd");
					textFecha.setText(ff.format(calendar.getCalendar().getTime()));
				}
			}
		});
		calendar.setBounds(1, 1, 210, 169);
		panelCalendar.add(calendar);
	}

	private void radioButton() {

		// Director
		grupo = new ButtonGroup();
		rdbDirector = new JRadioButton("DIRECTOR");
		rdbDirector.addActionListener(this);
		rdbDirector.setBounds(321, 279, 109, 23);
		contentPanel.add(rdbDirector);
		grupo.add(rdbDirector);

		// Actor
		rdbtnActor = new JRadioButton("ACTOR");
		rdbtnActor.addActionListener(this);
		rdbtnActor.setBounds(321, 309, 109, 23);
		contentPanel.add(rdbtnActor);
		grupo.add(rdbtnActor);

		// Guionista
		rdbtnGuionista = new JRadioButton("GUIONISTA");
		rdbtnGuionista.addActionListener(this);
		rdbtnGuionista.setBounds(461, 279, 109, 23);
		contentPanel.add(rdbtnGuionista);
		grupo.add(rdbtnGuionista);

		rdbtnTecnicoAudiovisual = new JRadioButton("TECNICO AUDIOVISUAL");
		rdbtnTecnicoAudiovisual.addActionListener(this);
		rdbtnTecnicoAudiovisual.setBounds(461, 309, 148, 23);
		contentPanel.add(rdbtnTecnicoAudiovisual);
		grupo.add(rdbtnTecnicoAudiovisual);

	}

	private void buttons() {

		botonCalendar = new MenuButton();
		Utilidades.configButtons(botonCalendar, "");
		botonCalendar.setBackground(Color.WHITE);
		botonCalendar.setIcon(new ImageIcon(
				DatosPersonal.class.getResource("/vistas/ventanas/custom/components/img/Calendar-icon.png")));
		botonCalendar.addActionListener(this);
		botonCalendar.setBounds(177, 206, 45, 45);
		contentPanel.add(botonCalendar);

		btnBorrarDatos = new MenuButton();
		Utilidades.configButtons(btnBorrarDatos, "");
		btnBorrarDatos.setIcon(
				new ImageIcon(DatosPersonal.class.getResource("/vistas/ventanas/custom/components/img/delete.png")));
		btnBorrarDatos.setBounds(808, 490, 65, 23);
		contentPanel.add(btnBorrarDatos);
		btnBorrarDatos.addActionListener(this);
		btnBorrarDatos.setActionCommand("OK");

		btnModificar = new MenuButton();
		Utilidades.configButtons(btnModificar, "");
		btnModificar.setIcon(new ImageIcon(
				DatosPersonal.class.getResource("/vistas/ventanas/custom/components/img/edit_button.png")));
		btnModificar.setBounds(733, 490, 65, 23);
		contentPanel.add(btnModificar);
		btnModificar.setActionCommand("OK");
		btnModificar.addActionListener(this);

		btnAdd = new MenuButton();
		Utilidades.configButtons(btnAdd, "");
		btnAdd.setIcon(
				new ImageIcon(DatosPersonal.class.getResource("/vistas/ventanas/custom/components/img/check.png")));
		btnAdd.setBounds(883, 490, 65, 23);
		btnAdd.addActionListener(this);
		contentPanel.add(btnAdd);

		btnVolver = new MenuButton();
		Utilidades.configButtons(btnVolver, "");
		btnVolver.setIcon(
				new ImageIcon(DatosPersonal.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		btnVolver.setBounds(652, 490, 65, 23);
		contentPanel.add(btnVolver);
		btnVolver.addActionListener(this);
		btnVolver.setEnabled(true);

		btnUsuario = new MenuButton();
		Utilidades.configButtons(btnUsuario, "Configurar usuario");
		btnUsuario.setBounds(790, 435, 155, 24);
		btnUsuario.addActionListener(this);
		contentPanel.add(btnUsuario);
	}

	private void initData() {

		dniField.setText(trabajador.getDni());
		nombreField.setText(trabajador.getNombre());
		apellidoField.setText(trabajador.getApellido());
		textTelefono.setText(Integer.toString(trabajador.getNumTel()));
		textFecha.setText(trabajador.getFechaNac() != null ? trabajador.getFechaNac().toString() : "");
		direccionField.setText(trabajador.getDireccion());
		numPremioField.setText(Integer.toString(trabajador.getNumPremios()));

		if (trabajador instanceof Director) {
			rdbDirector.setSelected(true);
			rdbtnActor.setEnabled(false);
			rdbtnTecnicoAudiovisual.setEnabled(false);
			rdbtnGuionista.setEnabled(false);
			radioPanels("director");
			directorCategoria.setText(((Director) trabajador).getCategoria());

		} else if (trabajador instanceof Actor) {
			rdbDirector.setEnabled(false);
			rdbtnActor.setSelected(true);
			rdbtnTecnicoAudiovisual.setEnabled(false);
			rdbtnGuionista.setEnabled(false);
			radioPanels("actor");

			for (String string : ((Actor) trabajador).getEspecialidades()) {
				actorListModel.addElement(string);
			}

		} else if (trabajador instanceof TecnicoAudiovisual) {
			rdbDirector.setEnabled(false);
			rdbtnActor.setEnabled(false);
			rdbtnTecnicoAudiovisual.setSelected(true);
			rdbtnGuionista.setEnabled(false);
			radioPanels("tecnico");
			for (String string : ((TecnicoAudiovisual) trabajador).getAreaTrabajos()) {
				tecListModel.addElement(string);
			}
		} else {
			rdbDirector.setEnabled(false);
			rdbtnActor.setEnabled(false);
			rdbtnTecnicoAudiovisual.setEnabled(false);
			rdbtnGuionista.setSelected(true);
			radioPanels("guionista");
			for (String string : ((Guionista) trabajador).getTipoGuiones()) {
				guionListModel.addElement(string);
			}
		}

	}

	private void radioPanels(String type) {
		switch (type) {
		case "director":
			panelDirector.setVisible(true);
			panelActor.setVisible(false);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(false);
			datoField.setVisible(false);
			break;
		case "actor":
			panelDirector.setVisible(false);
			panelActor.setVisible(true);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(false);
			datoField.setVisible(true);
			break;
		case "tecnico":
			panelDirector.setVisible(false);
			panelActor.setVisible(false);
			panelGuionista.setVisible(false);
			panelAudiovisual.setVisible(true);
			datoField.setVisible(true);
			break;
		case "guionista":
			panelDirector.setVisible(false);
			panelActor.setVisible(false);
			panelGuionista.setVisible(true);
			panelAudiovisual.setVisible(false);
			datoField.setVisible(true);
			break;

		}
	}

	private void recogerData() {
		if (rdbDirector.isSelected()) {
			if (trabajador == null)
				trabajador = new Director();
			((Director) trabajador).setCategoria(directorCategoria.getText());
			trabajador.setTipo("Director");
		} else if (rdbtnActor.isSelected()) {
			if (trabajador == null)
				trabajador = new Actor();
			((Actor) trabajador).setEspecialidades(especialidad);
			trabajador.setTipo("Actor");
		} else if (rdbtnGuionista.isSelected()) {
			if (trabajador == null)
				trabajador = new Guionista();
			((Guionista) trabajador).setTipoGuiones(tipoGuion);
			trabajador.setTipo("Guionista");
		} else {
			if (trabajador == null)
				trabajador = new TecnicoAudiovisual();
			((TecnicoAudiovisual) trabajador).setAreaTrabajos(areaTrabajo);
			trabajador.setTipo("TecnicoAudiovisual");
		}
		trabajador.setDni(dniField.getText());
		trabajador.setNombre(nombreField.getText());
		trabajador.setApellido(apellidoField.getText());
		trabajador.setNumTel(Integer.parseInt(textTelefono.getText()));
		trabajador.setNumPremios(Integer.parseInt(numPremioField.getText()));
		trabajador.setDireccion(direccionField.getText());
		trabajador.setFechaNac(Utilidades.validateDate(textFecha.getText()));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		boolean ok = false;
		if (e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "Volver", OptionPanel.CONFIRM);
			if (i == OptionPanel.CONFIRM) {
				thisDialog.dispose();
				parent.setVisible(true);
			}
		} else if (e.getSource().equals(btnModificar)) {
			if (trabajador != null && valido) {
				recogerData();
				if (FactoryDAO.getUpdateData().dataManage(trabajador)) {
					OptionPanel.showMessage(thisDialog, "Se ha modificado correctamente los datos del trabajador",
							"Modificación de datos", OptionPanel.MESSAGE);
				}
				if (user != null) {
					FactoryDAO.getInsertData().dataManage(user);
				}
			} else {
				OptionPanel.showMessage(thisDialog, "Error al modificar, compruebe los datos e inténtelo de nuevo",
						"Error al intentar modificar", OptionPanel.MESSAGE);
			}
		} else if (e.getSource().equals(btnAdd)) {
			if (valido) {
				recogerData();
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Estás segur@ de que quieres insertar esta trabajador?", "Confirmación",
						OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					ok = FactoryDAO.getInsertData().dataManage(trabajador);
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
		} else if (e.getSource().equals(btnUsuario)) {
			DataUsuario du = new DataUsuario(thisDialog, true, user);
			thisDialog.dispose();
			du.setVisible(true);
		}

		else if (e.getSource().equals(rdbDirector)) {
			radioPanels("director");
		} else if (e.getSource().equals(rdbtnActor)) {
			radioPanels("actor");
		} else if (e.getSource().equals(rdbtnGuionista)) {
			radioPanels("guionista");
		} else if (e.getSource().equals(rdbtnTecnicoAudiovisual)) {
			radioPanels("tecnico");
		}
		if (e.getSource().equals(botonCalendar)) {
			if (!entra) {
				panelCalendar.setVisible(true);
				entra = true;
			} else {

				panelCalendar.setVisible(false);
				entra = false;

			}
		}

	}
}