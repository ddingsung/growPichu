package Poketmon;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;




public class manager extends JFrame implements MouseListener{
	JTable manager;
	Vector list, col;
	JLabel title;
	Poketmon_MainGUI main;
	public manager(Poketmon_MainGUI main){
		setTitle("관리 창");
		this.main = main;
		
		PoketmonDAO dao = new PoketmonDAO();

		add(title = new JLabel("목록"));
		title.setFont(new Font("맑은고딕", Font.BOLD, 30));
		title.setBounds(110, 10, 80, 30);
		

		col = new Vector();
		col.add("아이디");
		col.add("닉네임");
		col.add("레벨");
		col.add("골드");
		col.add("경험치");
		col.add("에너지");
		col.add("gm");

		DefaultTableModel model = new DefaultTableModel(dao.viewMember(), col) {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		manager = new JTable(model);
		manager.addMouseListener(this);
		JScrollPane scroll = new JScrollPane(manager);
		jTableSet();
		add(scroll);
		scroll.setBounds(40, 50, 400, 350);

		setLayout(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		setSize(500, 500);
		setLocationRelativeTo(null);
	}

	public void jTableSet() {
		manager.getTableHeader().setReorderingAllowed(false);
		manager.getTableHeader().setResizingAllowed(false);
		manager.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);

		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		DefaultTableCellRenderer celAlignRight = new DefaultTableCellRenderer();
		celAlignRight.setHorizontalAlignment(JLabel.RIGHT);
		DefaultTableCellRenderer celAlignLeft = new DefaultTableCellRenderer();
		celAlignLeft.setHorizontalAlignment(JLabel.LEFT);
	}
	public void jTableRefresh() {
		PoketmonDAO dao = new PoketmonDAO();
		DefaultTableModel model =
				new DefaultTableModel(dao.viewMember(), getColumn());
		manager.setModel(model);
	}
	public Vector getColumn() {
		Vector col = new Vector();
		col.add("아이디");
		col.add("닉네임");
		col.add("레벨");
		col.add("골드");
		col.add("경험치");
		col.add("에너지");
		col.add("gm");
		return col;
	}
//	public static void main(String args[]) {
//		new manager();
//		
//	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int r = manager.getSelectedRow();
		String nick =(String)manager.getValueAt(r, 1);
		managerProc mem = new managerProc(nick, this, main);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
		
		
	}


