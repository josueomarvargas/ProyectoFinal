package vistas.ventanas.table;

import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import controlador.utils.ClasesEnum;
import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import modelo.clases.Patrocinador;
import vistas.dao.GetData;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosPatrocinador;
import vistas.ventanas.custom.components.TextField;


public class TablaPatrocinadores extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel patroPanel=new JPanel();
	private final JDialog thisDialog;
	private JTable tablePatro=null;
	private TextField idField;
	private TextField nombreField;
	private TextField cantField;
	private TextField condField;
	private MenuButton btnBuscar;
	private MenuButton btnAnadir;
	private MenuButton btnVolver;
	private MenuButton btnRefrescar;
	private Window parent;
	private JScrollPane scrollPane;
	private TitleBar bar;
		private TableModel model;
	private int index;
	private DatosPatrocinador dataPatro;
	private CustomTab tabs = null;


	/**
	 * Create the dialog.
	 */
	public TablaPatrocinadores(Window parent, boolean modal) {
		setModal(true);
		this.setUndecorated(true);
		this.parent = parent;
		setSize(Utilidades.resizeWindow(this));
		//Utilidades.centerWindow(parent,this);
		thisDialog=this;
		contentPanel.setBackground(Color.WHITE);
		init();
	}
	private void init() { 
		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);
		contentPanel.setLayout(null);
		loadTables();
		buttons();
		menuFiltro();
		tablePatro.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tablePatro.getSelectedRow() != -1) {
					index = tablePatro.getSelectedRow();
					model = tablePatro.getModel();
				}
				openData(index, model);
				thisDialog.dispose();
			}
		});
		switch(new String("administrador")) {
		case "administrador":
			btnAnadir.setEnabled(true);
			btnVolver.setEnabled(true);
			btnRefrescar.setEnabled(true);
			break;
		default:
			break;	
		}
		getContentPane().add(contentPanel);
	}
	private void loadTables() {
		tabs = new CustomTab();
		tabs.setBounds(15, 50, 750, 480);
		tabs.setTabPlacement(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.WHITE);
		patroPanel.setBackground(Color.WHITE);
		tabs.add("Pelicula", patroPanel);
		contentPanel.add(tabs);
		tablePatro = tabla(patroPanel, ClasesEnum.PELICULA.getName(), tablePatro);
		
	}
	private void openData(int i, TableModel tableModel) {
		thisDialog.setVisible(false);
		int id = Integer.parseInt(tableModel.getValueAt(i, 0).toString());
		Patrocinador pa = (Patrocinador) GetData.getDatos(ClasesEnum.PATROCINADOR.getName()).get(id);
		dataPatro = new DatosPatrocinador(thisDialog, true, pa, null);
		dataPatro.setVisible(true);
	}
	/**
	 * Abrir ventana con los datos
	 * 
	 * @param model2
	 * @param index2
	 * 
	 **/


	/*
	 * CustomTab tabs= new CustomTab(); //tabs.setTabPlacement(JTabbedPane.BOTTOM);
	 * tabs.setBackground(Color.WHITE); patroPanel.setBackground(Color.WHITE);
	 * tabs.add("Patrocinador",patroPanel); patroPanel.setLayout(null);
	 * getContentPane().add(tabs);
	 * 
	 * 
	 * menuFiltro(patroPanel); buttons(patroPanel);
	 * 
	 * 
	 * btnCerrarSystem = new JButton("X"); btnCerrarSystem.setForeground(Color.RED);
	 * btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
	 * btnCerrarSystem.setBounds(496, 0, 55, 29);
	 * btnCerrarSystem.addActionListener(this); contentPanel.add(btnCerrarSystem);
	 * textField = new JTextField(); textField.setBounds(x, 317, 100, 20);
	 * contentPanel.add(textField);
	 */

private void buttons() {
	btnAnadir = new MenuButton();
	btnAnadir.setBounds(890, 455, 50, 30);
	btnAnadir.setIcon(new ImageIcon(
			TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
	Utilidades.configButtons((MenuButton) btnAnadir, "");
	btnAnadir.addActionListener(this);
	contentPanel.add(btnAnadir);

	btnRefrescar = new MenuButton();
	btnRefrescar.setBounds(835, 455, 50, 30);
	btnRefrescar.setIcon(new ImageIcon(
			TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/refresh.png")));
	
	Utilidades.configButtons((MenuButton) btnRefrescar, "");
	btnRefrescar.addActionListener(this);
	contentPanel.add(btnRefrescar);

	btnVolver = new MenuButton();
	btnVolver.setBounds(780, 455, 50, 30);
	btnVolver.setIcon(new ImageIcon(
			TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
	Utilidades.configButtons((MenuButton) btnVolver, "");
	btnVolver.addActionListener(this);
	contentPanel.add(btnVolver);
}
private void menuFiltro() {
	patroPanel.setLayout(null);
	// Etiqueta: Id
	//	JLabel idLabel = new JLabel("Id:");
	//idLabel.setBounds(780, 45, 160, 20);
	//panel.add(idLabel);

	// TextField: Id
	idField = new TextField();
	idField.setBounds(780, 50, 160, 25);
	idField.setLabelText("Id");
	contentPanel.add(idField);

	// Etiqueta: Nombre
	//JLabel nombreLabel = new JLabel("Nombre:");
	//nombreLabel.setBounds(780, 45, 160, 20);
	//panel.add(nombreLabel);


	// TextField: Nombre
	nombreField = new TextField();
	nombreField.setBounds(780, 100, 160, 25);
	nombreField.setLabelText("Nombre");
	contentPanel.add(nombreField);

	// Etiqueta: Cantidad
	//JLabel cantLabel = new JLabel("Guionista:");
	//cantLabel.setBounds(780, 45, 160, 20);
	//panel.add(cantLabel);

	// TextField: Cantidad
	cantField = new TextField();
	cantField.setBounds(780, 150, 160, 25);
	cantField.setLabelText("Cantidad");

	contentPanel.add(cantField);

	// Etiqueta: Num Trabajadores
	//JLabel condLabel = new JLabel("Numero Trabajadores");
	//condLabel.setBounds(780, 45, 160, 20);
	//panel.add(condLabel);

	// TextField: Num Trabajadores
	condField = new TextField();
	condField.setBounds(780, 200, 160, 25);
	condField.setLabelText("Condicion");
	contentPanel.add(condField);



	// Boton para buscar
	btnBuscar = new MenuButton();
	btnBuscar.setBounds(780, 250, 160, 35);
	btnBuscar.setIcon(new ImageIcon(
			TablaPatrocinadores.class.getResource("/vistas/ventanas/custom/components/img/search.png")));
	Utilidades.configButtons( (MenuButton) btnBuscar, "");
	btnBuscar.setEnabled(false);
	btnBuscar.addActionListener(this);
	contentPanel.add(btnBuscar);

}


private JTable tabla(JPanel panel, String patrocinador, JTable table) {

	// Recoger los datos de los trabajdores
	//data = FactoryDAO.getGetData().checkInfo(GetData.PATROCINADOR);
	//column = new String[] { "ID", "Nombre", "Dinero" };

	// Scroll panel
	scrollPane = new JScrollPane();
	scrollPane.setBounds(0, 0, 740, 440);
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	// Crear una tabla
	table = new JTable() {
		private static final long serialVersionUID = 1L;

		public boolean isCellEditable(int row, int column) {
			return false;
		}

	};
	table.setBackground(new Color(255,255,255));
	table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	// Añadir los datos a la tabla
	table.setModel(tableModel(patrocinador)); 
	Utilidades.resizeColumnWidth(table); // Redimensionar columnas
	table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
	scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
	panel.add(scrollPane);// Añadir el scroll panel al panel de la pestaña
	return table;
}

private DefaultTableModel tableModel(String patocinador) {
	// Recoger los datos de las pelis/series
	Object[][] data=FactoryDAO.getGetData().dataManage(patocinador);
	String[] column=null;
	// Inicializar la tabla
	column = new String[] {"ID", "Nombre", "Cantidad(mil)","Condicion"};

	return new DefaultTableModel(data,column);

}


@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource().equals(btnBuscar)) {

	}
	else if (e.getSource().equals(btnAnadir)) {
		dataPatro= new DatosPatrocinador(thisDialog,true, null,ClasesEnum.PATROCINADOR.getName());

	}
	if (dataPatro != null) {
		thisDialog.setVisible(false);
		dataPatro.setVisible(true);
	}
	else if (e.getSource().equals(btnVolver)) {
		int i = OptionPanel.showOptionMessage(thisDialog,
				"¿Estas segur@ de que quieres volver a la ventana anterior?", "¿Quieres volver?",
				OptionPanel.CONFIRM);
		if (i == 1) {
			thisDialog.dispose();
			parent.setVisible(true);
		}
	}
	else if (e.getSource().equals(btnRefrescar)) {
		tablePatro.setModel(tableModel(ClasesEnum.PATROCINADOR.getName()));
		Utilidades.resizeColumnWidth(tablePatro);

	}
		
}

}
