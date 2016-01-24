package action.uploadFilePro;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import javax.activation.DataHandler;

public class FileService {
	// 使用byte[]类型参数上传二进制文件
	public boolean uploadWithByte(byte[] file, String filename) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			fos.write(file);
			fos.close();
		} catch (Exception e) {
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		return true;
	}

	private void writeInputStreamToFile(InputStream is, OutputStream os)
			throws Exception {
		int n = 0;
		byte[] buffer = new byte[8192];
		while ((n = is.read(buffer)) > 0) {
			os.write(buffer, 0, n);
		}
	}

	// 使用DataHandler类型参数上传文件
	public boolean uploadWithDataHandler(DataHandler file, String filename) {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(filename);
			// 可通过DataHandler类的getInputStream方法读取上传数据
			writeInputStreamToFile(file.getInputStream(), fos);
			fos.close();
		} catch (Exception e) {
			return false;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
				}
			}
		}
		return true;
	}
}