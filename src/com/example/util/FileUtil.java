package com.example.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.StandardOpenOption;

public class FileUtil {

//	========================= Folder =========================
	// 創建目錄
	public void createFolder(String folderPath) {
		try {
			Files.createDirectories(Paths.get(folderPath));
		} catch (IOException ex) {
			throw new RuntimeException("Error creating folder: " + folderPath, ex);
		}
	}

	// 檢查目錄是否存在
	public boolean checkFolder(String folderPath) {
		try {
			return Files.exists(Paths.get(folderPath));
		} catch (Exception ex) {
			throw new RuntimeException("Error checking folder path: " + folderPath, ex);
		}
	}

	// 檢查目錄內的檔案數量
	public boolean checkFileCount(String targetFolder, int Count) {
		File folder = new File(targetFolder);
		if (folder.exists() && folder.isDirectory()) {
			int fileCount = folder.list().length;
			return fileCount >= Count;
		}
		return false;
	}

	// 刪除目錄
	public void deleteFolder(String folderPath) {
		File folder = new File(folderPath);
		if (folder.exists() && folder.isDirectory()) {
			deleteRecursively(folder);
		}
	}

	// 遞迴刪除目錄及內容
	private void deleteRecursively(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File child : files) {
					deleteRecursively(child);
				}
			}
		}
		if (!file.delete()) {
			throw new RuntimeException("Failed to delete file or directory: " + file.getAbsolutePath());
		}

	}

//	=========================  File  =========================
	// 創建文件
	public void createFile(String filePath) {
		try {
			Files.write(Paths.get(filePath), new byte[0], StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING);
		} catch (IOException ex) {
			throw new RuntimeException("Error creating file: " + filePath, ex);
		}
	}

	// 檢查文件是否存在
	public boolean checkFile(String filePath) {
		try {
			return Files.exists(Paths.get(filePath));
		} catch (Exception ex) {
			throw new RuntimeException("Error checking file: " + filePath, ex);
		}
	}

	// 移動文件
	public void moveFile(String sourceFilePath, String destFolder) {
		try {
			Files.move(Paths.get(sourceFilePath), Paths.get(destFolder), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new RuntimeException("Error moving file from " + sourceFilePath + " to " + destFolder, ex);
		}
	}

	// 複製文件
	public void copyFile(String sourceFilePath, String destFolder) {
		try {
			Files.copy(Paths.get(sourceFilePath), Paths.get(destFolder), StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException ex) {
			throw new RuntimeException("Error moving file from " + sourceFilePath + " to " + destFolder, ex);
		}
	}

	// 讀取文件
	public String readFile(String filePath) throws IOException {
		StringBuilder content = new StringBuilder();
		try (FileReader fileReader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				content.append(line).append(System.lineSeparator());
			}
		}
		return content.toString();
	}

	// 刪除文件
	public boolean deleteFile(String filePath) {
		try {
			return Files.deleteIfExists(Paths.get(filePath));
		} catch (IOException ex) {
			throw new RuntimeException("Error deleting file: " + filePath, ex);
		}
	}

	// 寫入
	public void write(String filePath, String data) {
		try (FileOutputStream fos = new FileOutputStream(filePath)) {
			fos.write(data.getBytes());
			fos.flush();
		} catch (IOException ex) {
			throw new RuntimeException("Error writing to file: " + filePath, ex);
		}
	}
}
