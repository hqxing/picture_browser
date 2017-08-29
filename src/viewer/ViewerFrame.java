package viewer;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.JToolBar;
import javax.swing.JMenuItem;

import action.Action;
import javax.swing.ImageIcon;
//������
public class ViewerFrame extends JFrame {
	private static ViewerFrame frame; 
	private JPanel contentPanel;
	private JLabel lblNewLabel;
	private JPanel contentPane;
	Action action=new Action(this);	
	 public JLabel getJLabel() {
		 return lblNewLabel;
	 }
	 public void setLabel(JLabel label){
		 lblNewLabel=label;
	 }
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 frame = new ViewerFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public ViewerFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 40, 1200, 800);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("�ļ�(F)");
		menuBar.add(fileMenu);
		
		JMenuItem openMenuItem = new JMenuItem("��");
		openMenuItem.addActionListener (action);
		fileMenu.add(openMenuItem);
		
		JMenuItem exitmenuItem = new JMenuItem("�˳�");
		exitmenuItem.addActionListener (action);
		fileMenu.add(exitmenuItem);
		
		JMenu toolMenu = new JMenu("����(T)");
		menuBar.add(toolMenu);
		
		JMenuItem lastMenuItem = new JMenuItem("��һ��");
		lastMenuItem.addActionListener(action);
		toolMenu.add(lastMenuItem);
		
		JMenuItem nextMenuItem = new JMenuItem("��һ��");
		nextMenuItem.addActionListener(action);
		toolMenu.add(nextMenuItem);
		
		JMenuItem magnifyMenuItem = new JMenuItem("�Ŵ�");
		magnifyMenuItem.addActionListener(action);
		toolMenu.add(magnifyMenuItem);
		
		JMenuItem reduceMenuItem = new JMenuItem("��С");
		reduceMenuItem.addActionListener(action);
		toolMenu.add(reduceMenuItem);
		
		JMenu helpMenu = new JMenu("����(H)");
		menuBar.add(helpMenu);
		
		JMenuItem helpMenuItem = new JMenuItem("��������");
		helpMenuItem.addActionListener(action);
		helpMenu.add(helpMenuItem);
		
		JMenuItem aboutMenuItem = new JMenuItem("����");
		aboutMenuItem.addActionListener(action);
		helpMenu.add(aboutMenuItem);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JToolBar toolBar = new JToolBar();
		contentPane.add(toolBar, BorderLayout.NORTH);
		toolBar.setFloatable(false);
		
		JButton openButton = new JButton();
		openButton.setIcon(new ImageIcon("images\\open.PNG"));
		openButton.setToolTipText("open");
		openButton.addActionListener(action);
		toolBar.add(openButton);
		
		JButton lastButton = new JButton();
		lastButton.setIcon(new ImageIcon("images\\last.PNG"));
		lastButton.setToolTipText("last");
		lastButton.addActionListener(action);
		toolBar.add(lastButton);
		
		JButton nextButton = new JButton();
		nextButton.setIcon(new ImageIcon("images\\next.PNG"));
		nextButton.setToolTipText("next");
		nextButton.addActionListener(action);
		toolBar.add(nextButton);
		
		JButton magnifyButton = new JButton();
		magnifyButton.setIcon(new ImageIcon("images\\magnify.PNG"));
		magnifyButton.setToolTipText("magnify");
		magnifyButton.addActionListener(action);
		toolBar.add(magnifyButton);
		
		JButton reduceButton = new JButton();
		reduceButton.setIcon(new ImageIcon("images\\reduce.PNG"));
		reduceButton.setToolTipText("reduce");
		reduceButton.addActionListener(action);
		toolBar.add(reduceButton);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
	    lblNewLabel = new JLabel();
	    lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(lblNewLabel);
	}

}
