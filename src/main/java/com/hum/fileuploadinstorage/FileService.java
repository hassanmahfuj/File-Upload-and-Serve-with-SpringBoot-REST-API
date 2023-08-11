package com.hum.fileuploadinstorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {

	public static final String UPLOAD_PATH = "uploads";

	public String saveFile(MultipartFile file) throws IOException {
		String filePath = UPLOAD_PATH.concat("/").concat(file.getOriginalFilename());
		File f = new File(UPLOAD_PATH);
		if (!f.exists())
			f.mkdir();
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return filePath;
	}

	public InputStream getFile(String path) throws FileNotFoundException {
		String filePath = UPLOAD_PATH.concat("/").concat(path);
		InputStream is = new FileInputStream(filePath);
		return is;
	}
}
