package viewer;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {
	private String[] suffarr;
	private String decription;

	public MyFileFilter(String[] suffarr, String decription) {
		super();
		this.suffarr = suffarr;
		this.decription = decription;
	}

	public boolean accept(File f) {
		// ����ļ��ĺ�׺���Ϸ�������true
		for (String s : suffarr) {
			if (f.getName().toUpperCase().endsWith(s)) {
				return true;
			}
		}
		return f.isDirectory();
	}

	public String getDescription() {
		// �����Ŀ¼������true,���߷���false
		return this.decription;
	}

}
