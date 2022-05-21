package vistas.ventanas.table;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import controlador.utils.views.Utilidades;
import vistas.ventanas.custom.components.MenuButton;
import vistas.ventanas.custom.containers.OptionPanel;
import vistas.ventanas.custom.containers.TitleBar;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class TablaRelaciones extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private final JDialog thisDialog;
	private static Window owner;
	private JTable table;
	private MenuButton btnVolver;
	private MenuButton btnAdd;
	private static int id;

	public TablaRelaciones(Window parent, boolean modal, DefaultTableModel tableModel1, DefaultTableModel tableModel2,
			String titleText, String titleText2) {
		super(parent);
		thisDialog = this;
		setModal(modal);
		setUndecorated(true);
		setSize(Utilidades.resizeWindow(this));
		Utilidades.centerWindow(parent, this);
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().setLayout(new BorderLayout());

		TitleBar bar = new TitleBar(this);
		bar.setBounds(0, 0, this.getWidth(), 25);
		contentPanel.add(bar);

		JLabel title = new JLabel();
		title.setText(titleText);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.PLAIN, 18));
		title.setBounds(35, 36, 830, 38);
		contentPanel.add(title);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(35, 97, 830, 408);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		table = new JTable() {
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBounds(10, 39, 430, 250);
		table.setModel(tableModel1);
		Utilidades.resizeColumnWidth(table); // Redimensionar columnas
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		if (tableModel2 == null) {
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					if (table.getSelectedRow() != -1) {
						int i = table.getSelectedRow();
						id = Integer.parseInt(table.getValueAt(i, 0).toString());
						thisDialog.dispose();
						owner.setVisible(true);
					}
				}
			});
		}
		scrollPane.setViewportView(table);
		contentPanel.add(scrollPane);

		btnVolver = new MenuButton();
		btnVolver.setBounds(889, 470, 50, 30);
		btnVolver.setIcon(new ImageIcon(
				TablaPeliculasSeries.class.getResource("/vistas/ventanas/custom/components/img/arrow.png")));
		Utilidades.configButtons(btnVolver, "");
		btnVolver.setEnabled(true);
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int i = OptionPanel.showOptionMessage(thisDialog,
						"¿Estas segur@ de que quieres volver a la ventana anterior?", "Volver", OptionPanel.CONFIRM);
				if (i == OptionPanel.CONFIRM) {
					id = -2;
					thisDialog.dispose();
					parent.setVisible(true);
				}
			}
		});
		contentPanel.add(btnVolver);

		if (tableModel2 != null) {
			btnAdd = new MenuButton();
			Utilidades.configButtons(btnAdd, "");
			btnAdd.setIcon(new ImageIcon(
					TablaRelaciones.class.getResource("/vistas/ventanas/custom/components/img/plus.png")));
			btnAdd.setEnabled(true);
			btnAdd.setBounds(889, 428, 50, 30);
			btnAdd.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					thisDialog.dispose();
					id = addDataToRelation(thisDialog, tableModel2, titleText2);

				}
			});
			contentPanel.add(btnAdd);
		}

		getContentPane().add(contentPanel, BorderLayout.CENTER);
	}

	public static int addDataToRelation(Window parent, DefaultTableModel tableModel, String title2) {
		TablaRelaciones tblRelaciones = new TablaRelaciones(parent, true, tableModel, null, title2, null);
		tblRelaciones.setVisible(true);
		return id;
	}

	public static int showDataRelation(Window parent, DefaultTableModel tableModel1, DefaultTableModel tableModel2,
			String titleText1, String titleText2) {
		TablaRelaciones tblRelaciones = new TablaRelaciones(parent, true, tableModel1, tableModel2, titleText1,
				titleText2);
		owner = parent;
		tblRelaciones.setVisible(true);
		return id;
	}

}
