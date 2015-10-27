/**
 * 
 */
package com.gome.autodeploy.common;

import java.io.File;
import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * @author bailu-ds
 *
 */
public class CosFileRename implements FileRenamePolicy {

	private String fileName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public File rename(File file) {
        File renameFile = new File(file.getParent(), fileName);  
        return renameFile;
	}
	
}
