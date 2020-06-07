package nhom20;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class EditRuou extends JDialog implements ActionListener, Serializable {

	private ThongTinRuou infor;
	private MainFrame mainFrame;
	private int indexRow;

	public int getIndexRow() {
		return indexRow;
	}

	public void setIndexRow(int indexRow) {
		this.indexRow = indexRow;
	}

	public EditRuou(MainFrame mainFrame) {
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setTitle("Sửa thông tin rượu");

		this.mainFrame = mainFrame;
		infor = new ThongTinRuou(mainFrame.getData().getListGroup());
		add(createMainPanel());

		pack();
		setLocationRelativeTo(null);
	}

	private JPanel createMainPanel() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(infor, BorderLayout.CENTER);
		panel.add(createButtonPanel(), BorderLayout.PAGE_END);
		return panel;
	}

	private JPanel createButtonPanel() {
		JPanel panel = new JPanel(new GridLayout(1, 3, 10, 10));
		panel.setBorder(new EmptyBorder(10, 10, 10, 10));
		panel.add(createButton("Xong"));
		panel.add(createButton("Hủy"));
		return panel;
	}

	private JButton createButton(String btnName) {
		JButton btn = new JButton(btnName);
		btn.addActionListener(this);
		return btn;
	}

	private void loadInfor() {
		Ruou p = mainFrame.getData().getListRuou().get(indexRow);
		infor.setListGroup(mainFrame.getData().getListGroup());
		infor.loadListGroup();
		infor.getTfName().setText(p.getMaRuou());
		infor.getTfTenRuou().setText(p.getTenRuou());
		infor.getTfDunTich().setText(p.getDungTich());
                infor.getTfNongDo().setText(p.getNongDo());
                infor.getTfXuatXu().setText(p.getXuatXu());
                infor.getTfTinhTrang().setText(p.getTinhTrang());
                infor.getTfKieuCach().setText(p.getKieuCach());
		infor.getCbGroup().setSelectedIndex(infor.indexGroupName(p.getTenRuou()));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand() == "Xong") {
			editPerson();
		}
		if (e.getActionCommand() == "Hủy") {
			cancel();
		}
	}

	private void editPerson() {
		Ruou p = infor.getInfor();
		if (p != null) {
			clearInput();
			setVisible(false);
			mainFrame.getData().getListRuou().set(indexRow, p);
			mainFrame.updateData();
		}
	}

	private void cancel() {
		clearInput();
		setVisible(false);
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
}