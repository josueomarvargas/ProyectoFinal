package vistas.ventanas.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import controlador.utils.dao.FactoryDAO;
import controlador.utils.views.Utilidades;
import vistas.dao.GetData;
import vistas.ventanas.custom.containers.CustomTab;
import vistas.ventanas.custom.containers.TitleBar;
import vistas.ventanas.data.DatosPatrocinador;

public class TablaPatrocinadores extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JPanel patroPanel=new JPanel();
	private final JDialog thisDialog;
	private JTable tablePatro;
	private JTextField idField;
	private JTextField nombreField;
	private JTextField cantField;
	private JTextField condField;
	private JTextField textField;
	private JButton btnAceptar;
	private JButton btnBuscar;
	private JButton btnAnadir;
	private JButton btnVolver;
	private JButton btnCerrarSystem;
	private int x = 780;
	private int y = 20;
	private JScrollPane scrollPane;
	private Object[][] data;
	private String[] column;
	private TitleBar bar;
	private int filterX = 780;
	private int filterY = 20;
	private int filterWidth = 160;
	private int filterHeight = 20; 
	/**
	 * Create the dialog.
	 */
	public TablaPatrocinadores(Window parent, boolean modal) {
		setModal(true);
		this.setUndecorated(true);
		thisDialog=this;
		setSize(Utilidades.resizeWindow(this));
		//Utilidades.centerWindow(this);
		getContentPane().setBackground(Color.WHITE);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);

		//tabla();
		bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		getContentPane().add(bar);
		
		CustomTab tabs= new CustomTab();
		//tabs.setTabPlacement(JTabbedPane.BOTTOM);
		tabs.setBackground(Color.WHITE);
		patroPanel.setBackground(Color.WHITE);
		tabs.add("Patrocinador",patroPanel);
		patroPanel.setLayout(null);
		getContentPane().add(tabs);
		
		
		menuFiltro(patroPanel);
		buttons(patroPanel);
		

		btnCerrarSystem = new JButton("X");
		btnCerrarSystem.setForeground(Color.RED);
		btnCerrarSystem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnCerrarSystem.setBounds(496, 0, 55, 29);
		btnCerrarSystem.addActionListener(this);
		contentPanel.add(btnCerrarSystem);

		btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(x, 352, 100, 23);
		contentPanel.add(btnBuscar);

		
		
		textField = new JTextField();
		textField.setBounds(x, 317, 100, 20);
		contentPanel.add(textField);
		btnBuscar.addActionListener(this);
	}
private void buttons(JPanel panel) {
	filterY += 350;
	filterHeight = 30;
	
	btnAceptar = new JButton("Aceptar");
	btnAceptar.setBounds(780, 50, 160, 30);
	btnAceptar.addActionListener(this);
	panel.add(btnAceptar);
	
	btnAnadir = new JButton("+");
	btnAnadir.setBounds(880, 40, 50, 30);
	btnAnadir.addActionListener(this);
	panel .add(btnAnadir);
	
	btnVolver = new JButton("\u2190");
	btnVolver.setBounds(780, 40, 50, 30);
	btnVolver.addActionListener(this);
	panel.add(btnVolver);
	
	}
private void menuFiltro(JPanel panel) {
	filterY = 20;
	patroPanel.setLayout(null);
	// Etiqueta: Id
		JLabel idLabel = new JLabel("Id:");
		idLabel.setBounds(780, 45, 160, 20);
		panel.add(idLabel);

	// TextField: Id
	idField = new JTextField();
	idField.setBounds(780, 20, 160, 25);
	panel.add(idField);

	// Etiqueta: Nombre
	JLabel nombreLabel = new JLabel("Nombre:");
	nombreLabel.setBounds(780, 45, 160, 20);
	panel.add(nombreLabel);


	// TextField: Nombre
	nombreField = new JTextField();
	nombreField.setBounds(780, 20, 160, 25);
	panel.add(nombreField);

	// Etiqueta: Cantidad
	JLabel cantLabel = new JLabel("Guionista:");
	cantLabel.setBounds(780, 45, 160, 20);
	panel.add(cantLabel);

	// TextField: Cantidad
	cantField = new JTextField();
	cantField.setBounds(780, 20, 160, 25);
	panel.add(cantField);

	// Etiqueta: Num Trabajadores
	JLabel condLabel = new JLabel("Numero Trabajadores");
	condLabel.setBounds(780, 45, 160, 20);
	panel.add(condLabel);

	// TextField: Num Trabajadores
	condField = new JTextField();
	condField.setBounds(780, 20, 160, 25);
	panel.add(condField);
}


	private void tabla() {

		// Recoger los datos de los trabajdores
		data = FactoryDAO.getGetData().checkInfo(GetData.PATROCINADOR);
		column = new String[] { "ID", "Nombre", "Dinero" };

		// Scroll panel
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(25, 25, 600, 400);

		// Crear una tabla
		table = new JTable();
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setModel(new DefaultTableModel(data, column)); // Añadir los datos a la tabla
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table); // Añadir la tabla al scroll panel
		contentPanel.add(scrollPane);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnAnadir)) {
			DatosPatrocinador vPatrocinador = new DatosPatrocinador();
			vPatrocinador.setVisible(true);
		} else if (e.getSource().equals(btnVolver)) {
			this.dispose();
		} else if (e.getSource().equals(btnCerrarSystem)) {
			System.exit(0);
		}
	}

}
