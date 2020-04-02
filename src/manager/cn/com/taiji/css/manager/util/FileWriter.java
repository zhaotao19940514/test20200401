/**
 * @Title FileWriter.java
 * @Package mytool
 * @Description TODO
 * @author yaonanlin
 * @date 2018年6月22日 上午11:15:41
 * @version V1.0
 */
package cn.com.taiji.css.manager.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

import org.springframework.web.multipart.MultipartFile;

import cn.com.taiji.common.entity.StringUUIDEntity;
import cn.com.taiji.common.manager.ManagerException;
import cn.com.taiji.common.manager.pub.FileHelper;

/**
 * @ClassName FileWriter
 * @Description 写文件工具
 * @author yaonl
 * @date 2018年06月22日 11:15:41
 * @E_mail yaonanlin@163.com
 */
public class FileWriter {
	/**
	 * 向文件内写入字符串  支持多线程
	 * @param str
	 * @param file
	 */
	public static void write(String str, File file) {
		RandomAccessFile randomAccessFile = null;
		FileLock lock = null;
		FileChannel channel = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			channel = randomAccessFile.getChannel();
			while (true) {
				try {
					lock = channel.lock();// 尝试获得文件锁，若文件正在被使用，则一直等待
					break;
				} catch (Exception e) {
					try {
						Thread.sleep(100);// 限制系统资源被等待循环占用过多
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					// logger.error("write::: some other thread is holding the
					// file...");
				}
			}
			if (lock != null) {
				randomAccessFile.seek(randomAccessFile.length());
				// CSV文件 bom头
//				randomAccessFile.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
				randomAccessFile.write(str.getBytes("UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (lock != null) {
					lock.release();
				}
				if (channel != null) {
					channel.close();
				}
				if (randomAccessFile != null) {
					randomAccessFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	/**
	 * 向文件内指定位置写入字符串  支持多线程
	 * @param str
	 * @param file
	 */
	public static void write(String str,Long offset, File file) {
		RandomAccessFile randomAccessFile = null;
		FileLock lock = null;
		FileChannel channel = null;
		try {
			randomAccessFile = new RandomAccessFile(file, "rw");
			channel = randomAccessFile.getChannel();
			while (true) {
				try {
					lock = channel.lock();// 尝试获得文件锁，若文件正在被使用，则一直等待
					break;
				} catch (Exception e) {
					try {
						Thread.sleep(100);// 限制系统资源被等待循环占用过多
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
					// logger.error("write::: some other thread is holding the
					// file...");
				}
			}
			if (lock != null) {
				randomAccessFile.seek(offset);
				// CSV文件 bom头
//				randomAccessFile.write(new byte[] { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF });
				randomAccessFile.write(str.getBytes("UTF-8"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (lock != null) {
					lock.release();
				}
				if (channel != null) {
					channel.close();
				}
				if (randomAccessFile != null) {
					randomAccessFile.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public static String savePic(MultipartFile file,StringUUIDEntity entity,String parentDir) throws ManagerException{
		String destDirPath = FileHelper.getDataPath().concat(File.separator).concat(parentDir).concat(File.separator);
		File destDir = new File(destDirPath);
		if(!destDir.exists()){destDir.mkdirs();}
		String destFilePath = destDirPath+generateFileName(entity,getSuffix(file.getOriginalFilename()));
		File destFile = new File(destFilePath);
		saveFile(file,destFile);
		return destDirPath;
//		return getFilePathlWithOutRoot(destDirPath);
	}
	public static String generateFileName(StringUUIDEntity entity,MultipartFile file){
		return entity.getId()+getSuffix(file.getOriginalFilename());
	}
	public static String getSuffix(String fileName){
		return fileName.substring(fileName.lastIndexOf("."));
	}
	public static String generateFileName(StringUUIDEntity entity,String suffix) {
		return entity.getId()+suffix;
	}
	/**
	 * 
	 * @param file
	 * @param destFile
	 * @throws ManagerException
	 */
	private static void saveFile(MultipartFile file,File destFile) throws ManagerException{
		OutputStream out = null;
		try {
			out = new FileOutputStream(destFile);
			out.write(file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
			throw new ManagerException("文件存储失败");
		}
		 finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new ManagerException("文件流关闭失败");
				}
			}
		}
	}
	public static String getFilePathlWithOutRoot(String filePath){
		return filePath.substring(filePath.indexOf("data")+5);
	}
}
