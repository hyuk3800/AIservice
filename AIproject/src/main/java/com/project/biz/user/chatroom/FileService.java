package com.project.biz.user.chatroom;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {

	
	private String uploadDir = "E:\\upload\\tmp";
	
	public void fileUpload(MultipartFile multipartFile) {
		
		
		Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath("¾ß¾ß¾ß.jpg"));
		
		try {
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
