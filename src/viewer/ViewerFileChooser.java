package viewer;

import javax.swing.JFileChooser;

//�ļ��Ի���
public class ViewerFileChooser extends JFileChooser {
	public ViewerFileChooser() {
		this.setAcceptAllFileFilterUsed(false);
		addFilter();
	}

	// �����ļ�������
	private void addFilter() {
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP" },
				"BMP (*.BMP)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".JPG",
				".JPEG", ".JPE", ".JFIF" }, "JPEG (*.JPG;*.JPEG;*.JPE;*.JFIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".GIF" },
				"GIF (*.GIF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".TIF",
				".TIFF" }, "TIFF (*.TIF;*.TIFF)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".PNG" },
				"PNG (*.PNG)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".ICO" },
				"ICO (*.ICO)"));
		this.addChoosableFileFilter(new MyFileFilter(new String[] { ".BMP",
				".JPG", ".JPEG", ".JPE", ".JFIF", ".GIF", ".TIF", ".TIFF",
				".PNG", ".ICO" }, "����ͼ���ļ�"));
	}

}
