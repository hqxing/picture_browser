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

	// 菜单功能，按钮功能
	public void actionPerformed(ActionEvent e) {
		JButton jb = null;
		JMenuItem jmi = null;

		try {
			jmi = (JMenuItem) e.getSource();

			if (e.getActionCommand().equals("退出")) {
				System.exit(0);
			} else if (e.getActionCommand().equals("打开")) {
				open(frame);
			}
			if (e.getActionCommand().equals("上一张")) {
				last(frame);
			} else if (e.getActionCommand().equals("下一张")) {
				next(frame);
			}
			if (e.getActionCommand().equals("放大")) {
				zoom(frame, true);
			} else if (e.getActionCommand().equals("缩小")) {
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
		// 帮助主题,关于的对话框
		if (e.getActionCommand().equals("帮助主题")) {
			JDialog dialog = new JDialog(frame, true);
			dialog.setTitle("帮助主题");
			dialog.setSize(300, 300);
			dialog.setLocation(400, 200);
			JTextArea t = new JTextArea(3, 1);
			t.setEditable(false);
			t.setText("在文件菜单中中有打开和退出按钮; 在工具栏中有几项功能，分别是放大、缩小、上一张、下一张; 在帮助菜单中帮助你了解该软件用法。");// setText()设置文本显示的内容
			t.setLineWrap(true);// 设置文本区的换行策略。
			t.setFont(new Font("标楷体", Font.BOLD, 16));
			dialog.add(t);
			dialog.setVisible(true);
		} else if (e.getActionCommand().equals("关于")) {
			JDialog dialog2 = new JDialog(frame, true);
			dialog2.setTitle("关于");
			dialog2.setSize(300, 300);
			dialog2.setLocation(400, 200);
			dialog2.add(new Label("软件解释所有权归开发者所有", Label.CENTER));
			dialog2.setVisible(true);
		}

	}

	// 打开
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

	// 上一张图
	public void last(ViewerFrame frame) {
		// 如果有打开包含图片的文件夹
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile);// //获取当前文件的下标值
			File file = null;
			// 打开上一个
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

	// 下一张图
	public void next(ViewerFrame frame) {
		// 如果有打开包含图片的文件夹
		if (this.currentFiles != null && !this.currentFiles.isEmpty()) {
			int index = this.currentFiles.indexOf(this.currentFile);// 获取当前文件的下标值
			ImageIcon icon = null;
			if (index < currentFiles.size() - 1) { // 不是最后一张图片时
				File file = (File) this.currentFiles.get(index+1);
				icon = new ImageIcon(file.getPath());
				frame.getJLabel().setIcon(icon);
				this.currentFile = file;
			} else if (index == currentFiles.size() - 1) { // 最后一张图片时
				File file = (File) this.currentFiles.get(0);
				icon = new ImageIcon(file.getPath());
				frame.getJLabel().setIcon(icon);
				this.currentFile = file;
			}
		}
	}

	// 放大缩小
	public void zoom(ViewerFrame frame, boolean isEnlarge) {
		// 获取放大或者缩小的乘比
		double enLargeRange = isEnlarge ? 1 + range : 1 - range;
		// 获取目前的图片
		ImageIcon icon = (ImageIcon) frame.getJLabel().getIcon();
		if (icon != null) {
			int width = (int) (icon.getIconWidth() * enLargeRange);
			// 获取改变大小后的图片
			ImageIcon newIcon = new ImageIcon(icon.getImage()
					.getScaledInstance(width, -1, Image.SCALE_DEFAULT));
			// 改变显示的图片
			frame.getJLabel().setIcon(newIcon);
		}
	}
}
