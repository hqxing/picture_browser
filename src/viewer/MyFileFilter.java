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
		// 如果文件的后缀名合法，返回true
		for (String s : suffarr) {
			if (f.getName().toUpperCase().endsWith(s)) {
				return true;
			}
		}
		return f.isDirectory();
	}

	public String getDescription() {
		// 如果是目录，返回true,或者返回false
		return this.decription;
	}

}
