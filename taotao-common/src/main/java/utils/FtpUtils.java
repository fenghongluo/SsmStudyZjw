package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * 文件ftp上传与下载工具类
 * @author admin
 *
 */
public class FtpUtils {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {  
	        FileInputStream in=new FileInputStream(new File("C:\\Users\\admin\\Desktop\\结构化数据预览示例图片.png"));  
	        boolean flag = uploadFile("127.0.0.1", 21, "admin", "sunway", "/picture/bb", "gaigeming1.jpg", in);  
	        System.out.println(flag);  
	    } catch (FileNotFoundException e) {  
	        e.printStackTrace();  
	    }  
	}
	
	/**
	 * 上传文件
	 * @param host ftp的ip
	 * @param port ftp的端口
	 * @param username ftp用户名
	 * @param password ftp密码
	 * @param ftpPath ftp文件路径   /picture/bb
	 * @param ftpFileName 文件名
	 * @param input 输入流
	 * @return bool
	 */
	public static boolean uploadFile(String host, int port, String username, String password, 
				String ftpPath,String ftpFileName, InputStream input) {
		boolean result = false;
		FTPClient ftp = new FTPClient();
		try {
			int reply;
			ftp.connect(host, port);// 连接FTP服务器
			// 如果采用默认端口，可以使用ftp.connect(host)的方式直接连接FTP服务器
			ftp.login(username, password);// 登录
			reply = ftp.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				ftp.disconnect();
				return result;
			}
			//切换到上传目录
			System.out.println(ftpPath);
			if (!ftp.changeWorkingDirectory(ftpPath)) {
				//如果目录不存在创建目录
				String[] dirs = ftpPath.split("/");
				String tempPath = "/";
				for (String dir : dirs) {
					System.out.println(dir);
					if (null == dir || "".equals(dir)) continue;
					tempPath += "/" + dir;
					if (!ftp.changeWorkingDirectory(tempPath)) {
						if (!ftp.makeDirectory(tempPath)) {
							return result;
						} else {
							ftp.changeWorkingDirectory(tempPath);
						}
					}
				}
			}
			//设置上传文件的类型为二进制类型
			ftp.setFileType(FTP.BINARY_FILE_TYPE);
			//上传文件
			if (!ftp.storeFile(ftpFileName, input)) {
				System.out.println("2s");
				return result;
			}
			input.close();
			ftp.logout();
			result = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (ftp.isConnected()) {
				try {
					ftp.disconnect();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
		return result;
	}
}
