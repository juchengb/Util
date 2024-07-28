package com.example.util;

import java.io.File;

import net.lingala.zip4j.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.model.enums.AesKeyStrength;
import net.lingala.zip4j.model.enums.CompressionLevel;
import net.lingala.zip4j.model.enums.CompressionMethod;
import net.lingala.zip4j.model.enums.EncryptionMethod;

public class ZIPUtil {

	public void zipAndEncryptFolder(String targetFolderPath, String zipFilePath, String password) {
		try {
			File sourceFolder = new File(targetFolderPath);

			// create ZipFile
			ZipFile zipFile = new ZipFile(zipFilePath, password.toCharArray());

			// set zip parameters
			ZipParameters zipParameters = new ZipParameters();
			zipParameters.setCompressionMethod(CompressionMethod.DEFLATE);
			zipParameters.setCompressionLevel(CompressionLevel.NORMAL);
			zipParameters.setEncryptFiles(true); // Set the flag indicating that files are to be encrypted
			zipParameters.setEncryptionMethod(EncryptionMethod.AES);
			zipParameters.setAesKeyStrength(AesKeyStrength.KEY_STRENGTH_256);

			// add file in the sourceFolder to zip
			zipFile.addFolder(sourceFolder, zipParameters);

		} catch (Exception e) {
			throw new RuntimeException("Error while zipping and encrypting folder: " + targetFolderPath, e);
		}
	}
}
