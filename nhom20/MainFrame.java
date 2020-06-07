package nhom20;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class MainFrame extends JFrame implements ActionListener, KeyListener,
		Serializable {

	public static final int width = 600;
	public static final int height = 400;
	public static final int tableWidth = width - 10;
	public static final int tableHeight = height - 130;
	private final Color colorDefault = Color.white, colorNotFound = Color.pink;

	private String[] listSearch = { "Mã sản phẩm", "Tên sản phẩm", "Dung tích", "Nồng độ", "Xuất xứ", "Tình trạng", "Kiểu cách" };
	private String[] titleItem = listSearch;
	private JTable table;
	private JTextField tfSearch;
	private JLabel lbStatus;
	private JComboBox<String> cbSearchType;
	private int typeSearch = 0;

	private Data data = new Data();
	private ArrayList<Ruou> dataSearch = new ArrayList<Ruou>();

	private AddRuou addRuou;
	private EditRuou editRuou;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public MainFrame() {
		// create JFrame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(width, height);
		setTitle("Bảo trì rượu");
		setResizable(false);
		// add content
		//setJMenuBar(createJMenuBar());
		add(createMainPanel());

		read();
		updateTable(this.data.getListRuou());

		// display
		setLocationRelativeTo(null);
	}

	// create menuBar
	private JMenuBar createJMenuBar() {
		JMenuBar mb = new JMenuBar();
		String[] contacts = { "Thoát" };
		mb.add(createJMenu("Danh bạ", contacts, KeyEvent.VK_D));
		String[] help = { "Hướng dẫn", "", "Giới thiệu" };
		mb.add(createJMenu("Hướng dẫn", help, KeyEvent.VK_H));
		return mb;
	}

	// create menus
	private JMenu createJMenu(String menuName, String itemName[], int key) {
		JMenu m = new JMenu(menuName);
		m.addActionListener(this);
		m.setMnemonic(key);

		for (int i = 0; i < itemName.length; i++) {
			if (itemName[i].equals("")) {
				m.add(new JSeparator());
			} else {
				m.add(createJMenuItem(itemName[i]));
			}
		}

		return m;
	}

	// create menu item
	private JMenuItem createJMenuItem(String itName) {
		JMenuItem mi = new JMenuItem(itName);
		mi.addActionListener(this);
		return mi;
	}

	// create main panel
	private JPanel createMainPanel() {
		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.add(createSearchPanel(), BorderLayout.PAGE_START);
		mainPanel.add(createContactsPanel(), BorderLayout.CENTER);
		mainPanel.add(createStatusPanel(), BorderLayout.PAGE_END);
		return mainPanel;
	}

	// create search panel
	private JPanel createSearchPanel() {
		JPanel searchPanel = new JPanel(new BorderLayout(5, 5));
		searchPanel.setBorder(BorderFactory
				.createEtchedBorder(EtchedBorder.LOWERED));
		searchPanel.add(new JLabel("Tìm kiếm: "), BorderLayout.WEST);
		tfSearch = createJTextField();
		searchPanel.add(tfSearch, BorderLayout.CENTER);
		cbSearchType = createListSearch();
		searchPanel.add(cbSearchType, BorderLayout.EAST);
		return searchPanel;
	}

	// create comboBox to choose type search
	private JComboBox<String> createListSearch() {
		JComboBox<String> cb = new JComboBox<String>(listSearch);
		cb.addActionListener(this);
		return cb;
	}

	private JTextField createJTextField() {
		JTextField tf = new JTextField(20);
		tf.addKeyListener(this);
		return tf;
	}

	private JPanel createContactsPanel() {
		JPanel panel = new JPanel();
		panel.add(createTabelPanel());
		return panel;
	}

	private JPanel createTabelPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		table = createTable();
		loadData(table);
		JScrollPane scronllPanel = new JScrollPane(table);
		scronllPanel.setPreferredSize(new Dimension(tableWidth, tableHeight));
		panel.add(scronllPanel, BorderLayout.CENTER);
		return panel;
	}

	private JTable createTable() {
		JTable table = new JTable();
		table.setCellSelectionEnabled(false);
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		return table;
	}

	private void loadData(JTable table) {
		String data[][] = null;
		// don't edit table
		DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table.setModel(tableModel);
	}

	private JPanel createStatusPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		lbStatus = new JLabel();
		searchStatus(0, "", "");
		panel.add(lbStatus, BorderLayout.CENTER);
		panel.add(createButtonPanel(), BorderLayout.EAST);
		return panel;
	}

	// create button panel
	private JPanel createButtonPanel() {
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(createButton("Thêm"));
		buttonPanel.add(createButton("Sửa"));
		buttonPanel.add(createButton("Xóa"));
		return buttonPanel;
	}

	// create a button
	private JButton createButton(String buttonName) {
		JButton btn = new JButton(buttonName);
		btn.addActionListener(this);
		return btn;
	}

	public static void main(String[] args) {
		new MainFrame().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command == "Thêm") {
			themRuou();
			return;
		}
		if (command == "Sửa") {
			suaRuou();
			return;
		}
		if (command == "Xóa") {
			delete();
			return;
		}
		if (e.getSource() == cbSearchType) {
			resetSearch();
			return;
		}
		if (command == "Thoát") {
			System.exit(0);
		}
		if (command == "Hướng dẫn") {
			showHelp();
			return;
		}
		if (command == "Giới thiệu") {
			showAbout();
			return;
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		updateTable(search(typeSearch));
	}

	@Override
	public void keyTyped(KeyEvent arg0) {

	}

	public void updateData() {
		Collections.sort(data.getListRuou(), Ruou.PersonNameComparator);
		write();
		if (dataSearch.size() > 0) {
			updateTable(search(typeSearch));
		} else {
			updateTable(this.data.getListRuou());
		}
	}

	private int findIndexOfData() {
		int index = table.getSelectedRow();
		if (dataSearch.size() > 0) {
			for (int i = 0; i < data.getListRuou().size(); i++) {
				if (dataSearch.get(index) == data.getListRuou().get(i)) {
					System.out.println("index of data: " + i);
					return i;
				}
			}
		}
		return index;
	}

	private void themRuou() {
		if (addRuou == null) {
			addRuou = new AddRuou(this);
		}
		addRuou.display(true);
		tfSearch.requestFocus();
	}

	private void suaRuou() {
		System.out.println(table.getSelectedRow());
		int index = findIndexOfData();
		if (index >= 0) {
			if (editRuou == null) {
				editRuou = new EditRuou(this);
			}
			editRuou.setIndexRow(index);
			editRuou.display(true);
		} else {
			JOptionPane.showMessageDialog(null, "Bạn phải chọn một sản phẩm rượu!");
		}
		tfSearch.requestFocus();
	}

	private void delete() {
		int index = findIndexOfData();
		if (index >= 0) {
			int select = JOptionPane.showOptionDialog(null,
					"Bạn thực sự muốn xóa "
							+ data.getListRuou().get(index).getMaRuou() + " ?",
					"Xóa", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, null, null);
			if (select == 0) {
				data.getListRuou().remove(index);
				updateData();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Chọn một người rồi xóa!");
		}
		tfSearch.requestFocus();
	}

	private void updateTable(ArrayList<Ruou> list) {
		String data[][] = convertData(list);
		DefaultTableModel tableModel = new DefaultTableModel(data, titleItem) {
			@Override
			public boolean isCellEditable(int row, int column) {
				// all cells false
				return false;
			}
		};
		table.setModel(tableModel);
		// DefaultTableCellRenderer centerRenderer = new
		// DefaultTableCellRenderer();
		// centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		// for (int x = 0; x < titleItem.length; x++) {
		// table.getColumnModel().getColumn(x)
		// .setCellRenderer(centerRenderer);
		// }

	}

	private String[][] convertData(ArrayList<Ruou> list) {
		int size = list.size();
		String data[][] = new String[size][titleItem.length];
		for (int i = 0; i < size; i++) {
			Ruou ruou = list.get(i);
			data[i][0] = ruou.getMaRuou();
			data[i][1] = ruou.getTenRuou();
			data[i][2] = ruou.getDungTich();
                        data[i][3] = ruou.getNongDo();
                        data[i][4] = ruou.getTinhTrang();
                        data[i][5] = ruou.getXuatXu();
                        data[i][6] = ruou.getKieuCach();
		}
		return data;
	}

	private ArrayList<Ruou> search(int typeSearch) {
		int size = data.getListRuou().size();
		dataSearch.clear();
		String textFind = tfSearch.getText().trim().toLowerCase();
		if (textFind.length() == 0) {
			searchStatus(0, "", "");
			return this.data.getListRuou();
		}
		String textType = "";
		for (int i = 0; i < size; i++) {
			Ruou p = data.getListRuou().get(i);
			String text = "";
			if (typeSearch == 0) {
				text = p.getMaRuou();
				textType = " Rượu có mã phù hợp với \"";
			} else if (typeSearch == 1) {
				text = p.getNongDo();
				textType = " Rượu có nồng độ phù hợp với \"";
			} else if (typeSearch == 3) {
				text = p.getTenRuou();
				textType = " Rượu có thể loại phù hợp với \"";
			} else if (typeSearch == 2) {
				text = p.getDungTich();
				textType = " Rượu có dung tích phù hợp với \"";
			}
			text = text.trim().toLowerCase();
			if (text.indexOf(textFind) >= 0) {
				dataSearch.add(p);
			}
		}
		searchStatus(dataSearch.size(), textType + textFind + "\".", textFind);
		return dataSearch;
	}

	private void searchStatus(int count, String status, String textFind) {
		tfSearch.setBackground(colorDefault);
		if (textFind.length() == 0) {
			lbStatus.setText("Nhập vào thông tin tìm kiếm !");
		} else if (count > 0 && textFind.length() > 0) {
			lbStatus.setText("Tìm thấy " + count + status);
		} else if (count == 0 && textFind.length() > 0) {
			tfSearch.setBackground(colorNotFound);
			lbStatus.setText("Không tìm thấy" + status);
		}
	}

	private void resetSearch() {
		typeSearch = cbSearchType.getSelectedIndex();
		tfSearch.setText("");
		tfSearch.requestFocus();
		searchStatus(0, "", "");
		updateData();
	}

	public void write() { // ghi theo Object
		try {
			FileOutputStream f = new FileOutputStream("data");
			ObjectOutputStream oStream = new ObjectOutputStream(f);
			oStream.writeObject(data);
			oStream.close();
		} catch (IOException e) {
			System.out.println("Error Write file");
		}
	}

	public void read() {
		Data data = null;
		try {
			File file = new File("data");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileInputStream is = new FileInputStream(file);
			ObjectInputStream inStream = new ObjectInputStream(is);
			data = (Data) inStream.readObject();
			this.data = data;
			inStream.close();
		} catch (ClassNotFoundException e) {
			System.out.println("Class not found");
		} catch (IOException e) {
			System.out.println("Error Read file");
		}
	}

	private void showHelp() {
		new HelpAndAbout(0, "Hướng dẫn").setVisible(true);
	}

	private void showAbout() {
		new HelpAndAbout(1, "Giới thiệu").setVisible(true);
	}
}