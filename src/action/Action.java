package action;

import java.util.List;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JTextArea;
import javax.swing.event.AncestorEvent;
import javax.swing.filechooser.FileFilter;

import viewer.ViewerFileChooser;
import viewer.ViewerFrame;

public class Action implements ActionListener {

	private ViewerFrame frame;
	private ViewerFileChooser f;
	private String flag;
	private File currentFile;
	private File currentDirectory;
	private List currentFiles;
	private double range = 0.2;
	private JLabel lblNewLabel;
	private String name;

	public Action(ViewerFrame frame) {
		this.frame = frame;
	}

	// �˵����ܣ���ť����
	public void actionPerformed(ActionEvent e) {
		JButton jb = null;
		JMenuItem jmi = null;

		try {
			jmi = (JMenuItem) e.getSource();

			if (e.getActionCommand().equals("�˳�")) {
				System.exit(0);
			} else if (e.getActionCommand().equals("��")) {
				open(frame);
			}
			if (e.getActionCommand().equals("��һ��")) {
				last(frame);
			} else if (e.getActionCommand().equals("��һ��")) {
				next(frame);
			}
			if (e.getActionCommand().equals("�Ŵ�")) {
				zoom(frame, true);
			} else if (e.getActionCommand().equals("��С")) {
				zoom(frame, false);
			}
		} catch (Exception e1) {
			jb = (JButton) e.getSource();

			if (jb.getToolTipText().equals("open")) {
				open(frame);
			} else if (jb.getToolTipText().equals("last")) {
				last(frame);
			} else if (jb.getToolTipText().equals("next")) {
				next(frame);
			} else if (jb.getToolTipText().equals("magnify")) {
				zoom(frame, true);
			} else if (jb.getToolTipText().equals("reduce")) {
				zoom(frame, false);
			}
		}
		// ��������,���ڵĶԻ���
		if (e.getActionCommand().equals("��������")) {
			JDialog dialog = new JDialog(frame, true);
			dialog.setTitle("��������");
			dialog.setSize(300, 300);
			dialog.setLocation(400, 200);
			JTextArea t = new JTextArea(3, 1);
			t.setEditable(false);
			t.setText("���ļ��˵������д򿪺��˳���ť; �ڹ��������м���ܣ��ֱ��ǷŴ���С����һ�š���һ��; �ڰ����˵��а������˽������÷���");// setText()�����ı���ʾ������
			t.setLineWrap(true);// �����ı����Ļ��в��ԡ�
			t.setFont(new Font("�꿬��", Font.BOLD, 16));
			dialog.add(t);
			dialog.setVisible(true);
		} else if (e.getActionCommand().equals("����")) {
			JDialog dialog2 = new JDialog(frame, true);
			dialog2.setTitle("����");
			dialog2.setSize(300, 300);
			dialog2.setLocation(400, 200);
			dialog2.add(new Label("�����������Ȩ�鿪��������", Label.CENTER));
			dialog2.setVisible(true);
		}

	}

	// ��
	public void open(ViewerFrame frame) {
		f = new ViewerFileChooser();
		if (f.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			this.currentFile = f.getSelectedFile();
			String name = this.currentFile.getPath();
			File cd = f.getCurrentDirectory();
			if (cd != this.currentDirectory || this.currentDirectory == null) {
				FileFilter[] fileFilters = f.getChoosableFileFilters();
				File files[] = cd.listFiles();
				this.currentFiles = new ArrayList<File>();
				for (File file : files) {
	

							this.currentFiles.add(file);


				}
			}
			ImageIcon icon = new ImageIcon(name);
			frame.getJLabel().setIcon(icon);
		}
	}

	// ��һ��ͼ
	public void last(ViewerFrame frame) {
		// ����д򿪰���ͼƬ���ļ���
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile);// //��ȡ��ǰ�ļ����±�ֵ
			File file = null;
			// ����һ��
			if (index > 0) {
				file = (File) this.currentFiles.get(index - 1);
			} else {
				file = (File) this.currentFiles
						.get(this.currentFiles.size() - 1);
			}
			ImageIcon icon = new ImageIcon(file.getPath());
			frame.getJLabel().setIcon(icon);
			this.currentFile = file;
		}
	}

	// ��һ��ͼ
	public void next(ViewerFrame frame) {
		// ����д򿪰���ͼƬ���ļ���
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile);// ��ȡ��ǰ�ļ����±�ֵ
			ImageIcon icon = null;
			if (index < currentFiles.size() - 1) { // �������һ��ͼƬʱ
				File file = (File) this.currentFiles.get(index+1);
				icon = new ImageIcon(file.getPath());
				frame.getJLabel().setIcon(icon);
				this.currentFile = file;
			} else if (index == currentFiles.size() - 1) { // ���һ��ͼƬʱ
				File file = (File) this.currentFiles.get(0);
				icon = new ImageIcon(file.getPath());
				frame.getJLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}

	// �Ŵ���С
	public void zoom(ViewerFrame frame, boolean isEnlarge) {
		// ��ȡ�Ŵ������С�ĳ˱�
		double enLargeRange = isEnlarge ? 1 + range : 1 - range;
		// ��ȡĿǰ��ͼƬ
		ImageIcon icon = (ImageIcon) frame.getJLabel().getIcon();
		if (icon != null) {
			int width = (int) (icon.getIconWidth() * enLargeRange);
			// ��ȡ�ı��С���ͼƬ
			ImageIcon newIcon = new ImageIcon(icon.getImage()
					.getScaledInstance(width, -1, Image.SCALE_DEFAULT));
			// �ı���ʾ��ͼƬ
			frame.getJLabel().setIcon(newIcon);
		}
	}
}
