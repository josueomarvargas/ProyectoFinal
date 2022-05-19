package vistas.ventanas.data;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;


import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.ObraAudiovisual;
import modelo.clases.Patrocinador;
import modelo.clases.Pelicula;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.components.TextField;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Color;
import java.awt.Dimension;

public class DatosPatrocinador extends JDialog implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	private MenuButton btnBorrarDatos;
	private MenuButton btnModificar;
	private MenuButton btnAddPatro;
	private MenuButton btnVolver;
	private MenuButton addLogo;
	private Window parent;
	private JDialog thisDialog;
	private JLabel fotoLabel;
	private File img = null;
	private boolean valido = true;
	private Dimension size;
	private TitleBar bar;
	private Patrocinador patro=null;
	private String type;
	private CustomTab tabs;
	private JPanel patroPanel= new JPanel();
	private TextField fieldNombre, fieldCant, fieldCond;



	/**
	 * Create the dialog.
	 * @param object 
	 * @param pa 
	 * @param b 
	 */
	public DatosPatrocinador(Window parent, boolean modal, Patrocinador patro, String type) {
		super(parent);
		setModal(modal);
		this.parent=parent;
		this.thisDialog=this;
		this.setUndecorated(true);
		size=Utilidades.resizeWindow(this);
		setSize(size);
		contentPanel.setLayout(null);
		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);

		// Cuando se va ha modificar la obra
		if (patro != null) {
			this.patro = patro;
			init();
			initValues(patro);
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
		patroPanel.setBackground(Color.WHITE);
		tabs.add(patroPanel);
		patroPanel.setLayout(null);
		getContentPane().add(tabs);
		initTabs();



	}
	private void initTabs() {
		tabs.setSelectedIndex(0);
		tabsFields(patroPanel);
		this.patro=new Patrocinador();



	}

	private void tabsFields(JPanel panel) {
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText("Datos Patrocinador");
		lblTitulo.setFont(new Font("Calibri", Font.PLAIN, 28));
		lblTitulo.setBounds(375, 32, 171, 36);
		panel.add(lblTitulo);

		// Nombre
		fieldNombre = new TextField();
		fieldNombre.setLabelText("Nombre");
		fieldNombre.setBounds(279, 79, 150, 50);
		panel.add(fieldNombre);

		fieldCant = new TextField();
		fieldCant.setLabelText("Cantidad");
		fieldCant.setBounds(462, 79, 150, 50);
		panel.add(fieldCant);

		fieldCond = new TextField();
		fieldCond.setLabelText("Condicion");
		fieldCond.setBounds(645, 79, 150, 50);
		panel.add(fieldCond);
		tabsButtons(panel,patro);

	}
	private void tabsButtons(JPanel panel, Patrocinador obra) {
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

		btnVolver = new MenuButton();
		Utilidades.configButtons(btnVolver, null);
		btnVolver.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		btnVolver.setToolTipText("Volver");
		btnVolver.setBounds(size.width - 75, size.height - 75, 50, 25);
		btnVolver.addActionListener(this);
		btnVolver.setEnabled(true);
		panel.add(btnVolver);

		btnAddPatro = new MenuButton();
		Utilidades.configButtons(btnAddPatro, null);
		btnAddPatro.setIcon(new ImageIcon(getClass().getResource("/vistas/ventanas/custom/components/img/plus.png")));
		btnAddPatro.setToolTipText("Aceptar");
		btnAddPatro.setBounds(size.width - 150, size.height - 75, 50, 25);
		btnAddPatro.addActionListener(this);
		panel.add(btnAddPatro);

		fotoLabel = new JLabel("Imagen no proporcionada");
		fotoLabel.setBounds(462, 201, 298, 292);
		panel.add(fotoLabel);

		addLogo = new MenuButton();
		Utilidades.configButtons(addLogo, "A\u00F1adir Logo");
		addLogo.setBounds(462, 200, 125, 23);
		addLogo.addActionListener(this);
		addLogo.setEnabled(true);
		panel.add(addLogo);

		if (type == null) {
			btnAddPatro.setEnabled(false);
			btnBorrarDatos.setEnabled(true);
			btnModificar.setEnabled(true);
		} else {
			btnAddPatro.setEnabled(true);
			btnBorrarDatos.setEnabled(false);
			btnModificar.setEnabled(false);
		}
	}
	private void initValues(Patrocinador patro) {
		fieldNombre.setText(patro.getNombre());
		fieldCant.setText(Integer.toString(patro.getCantDinero()));
		fieldCond.setText(patro.getCondicion());
		if (patro.getImgPath() != null) {
			if (!patro.getImgPath().isBlank()) {
				fotoLabel.setIcon(
						Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + patro.getImgPath())));
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnBorrarDatos)) {
			if(patro!=null) {
				int i =OptionPanel.showOptionMessage(thisDialog,"¿Estas seguro que quieres eliminar ese patrocinador?", "Confirmacion", OptionPanel.CONFIRM);
				if(i==OptionPanel.CONFIRM) {
					boolean ok= FactoryDAO.getDeleteData().dataManage(new String[] {ClasesEnum.PATROCINADOR.getName(),Integer.toString(patro.getIdPatro())});
					if(ok) {
						OptionPanel.showMessage(thisDialog, "El patrocinador seleccionado ha sido eliminado", "Patrocinador eliminado", OptionPanel.MESSAGE);
						thisDialog.dispose();
						parent.setVisible(true);

					}
					else {
						OptionPanel.showMessage(thisDialog, "Ha ocurrido un error inesperado al intentar borrar el patrocinador seleccionado", "Error eliminar Patrocinador", OptionPanel.ERROR);
					}

				}

			}

		}else if(e.getSource().equals(btnModificar)) {
			if(patro!=null && valido) {
				patro.setNombre(fieldNombre.getText());
				patro.setCantDinero(Integer.parseInt(fieldCant.getText()));
				patro.setCondicion(fieldCond.getText());
				if (img != null) {
					patro.setImgPath(img.getPath());
				}
				if(FactoryDAO.getUpdateData().dataManage(patro)) {
					OptionPanel.showMessage(thisDialog, "Se ha modificado correctamente los datos de los patrocinadores", "Modificacion de Datos", OptionPanel.MESSAGE);
				}
			}else {
				OptionPanel.showMessage(thisDialog, "Error al modificar, compruebe los datos e inténtelo de nuevo",
						"Error al intentar modificar", OptionPanel.MESSAGE);
			}
		}else if(e.getSource().equals(btnAddPatro)) {
			boolean ok=false;
			if(valido) {
				int i = OptionPanel.showOptionMessage(thisDialog, "¿Estás segur@ de que quieres insertar este Patrocinador?",
						"Confirmación", OptionPanel.CONFIRM);
				if(i==OptionPanel.CONFIRM) {
					patro.setNombre(fieldNombre.getText());
					patro.setCantDinero(Integer.parseInt(fieldCant.getText()));
					patro.setCondicion(fieldCond.getText());
					ok= FactoryDAO.getInsertData().dataManage(patro);
				}
			}
			if(!valido || !ok) {
				OptionPanel.showMessage(thisDialog, "Error al añadir el patrocinador, compruebe los datos e inténtelo de nuevo",
						"Error al intentar insertar", OptionPanel.MESSAGE);
			}
		}else if(e.getSource().equals(btnVolver)) {
			int i = OptionPanel.showOptionMessage(thisDialog,
					"¿Estas segur@ de que quieres volver a la ventana anterior?", "¿Quieres volver?",
					OptionPanel.CONFIRM);
			if (i == OptionPanel.CONFIRM) {
				thisDialog.dispose();
				parent.setVisible(true);
			}

		}else if(e.getSource().equals(addLogo)) {
			img =Utilidades.addFoto();
			if (img != null) {
				// Foto
				fotoLabel.setIcon(Utilidades.resizeIcon(fotoLabel, new File(Utilidades.basePath + "/" + img)));
				thisDialog.repaint();
			}
		}
	}

}
