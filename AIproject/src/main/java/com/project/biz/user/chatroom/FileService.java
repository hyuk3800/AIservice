package com.project.biz.user.chatroom;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileService {

	
	
	
	public void fileUpload(MultipartFile multipartFile, HttpSession session) {

		String uploadDir = session.getServletContext().getRealPath("resources/uploadImg");
		
		
		
		File Folder = new File(uploadDir);
		
		if(!Folder.exists()) {
			try {
				Folder.mkdir();
				System.out.println("False");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
		}else {
			System.out.println("True");
		}
		
		Path copyOfLocation = Paths.get(uploadDir + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));
		
		try {
			Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
