package nhom20;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class AddRuou extends JDialog implements ActionListener, Serializable {

	private ThongTinRuou infor;
	private MainFrame mainFrame;

	public AddRuou(MainFrame mainFrame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Thêm rượu");

		this.mainFrame = mainFrame;
		infor = new ThongTinRuou(mainFrame.getData().getListGroup());
		add(createMainPanel());

		pack();
		setLocationRelativeTo(null);
	}

	// create main panel contains all control
	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		// add information panel
		panel.add(infor, BorderLayout.CENTER);
		// add button panel
		panel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return panel;
	}

	// create button panel with three button
	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 5, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(createButton("Thêm"));
		panel.add(createButton("Nhập lại"));
		panel.add(createButton("Hủy"));
		return panel;
	}

	// create a button
	private JButton createButton(String btnName) {
		JButton btn = new JButton(btnName);
		btn.addActionListener(this);
		return btn;
	}

	// Action

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Hủy") {
			cancel();
		}
		if (e.getActionCommand() == "Nhập lại") {
			resetData();
		}

		if (e.getActionCommand() == "Thêm") {
			addRuou();
		}
	}

	private void cancel() {
		clearInput();
		setVisible(false);
	}

	private void resetData() {
		clearInput();
	}

	private void addRuou() {
		Ruou p = infor.getInfor();
		if (p != null) {
			clearInput();
			setVisible(false);
			mainFrame.getData().getListRuou().add(p);
			mainFrame.updateData();
		}
	}

	private void clearInput() {
		infor.getTfName().setText("");
		infor.getTfTenRuou().setText("");
		infor.getTfDunTich().setText("");
		infor.getTfName().requestFocus();
	}

	public void display(boolean visible) {
		loadInfor();
		setVisible(visible);
	}

	private void loadInfor() {
		infor.setListGroup(mainFrame.getData().getListGroup());
		infor.loadListGroup();
	}
}
