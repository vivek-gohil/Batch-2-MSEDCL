package com.msedcl.main.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class FileUploadController {

	@Value("${file.upload-dir}")
	private String uploadDir;

	@GetMapping("upload")
	public String showUploadPage() {
		return "file-upload";
	}

	@PostMapping("upload")
	public String uploadFile(MultipartFile file, Model model) {

		try {
			if (file.isEmpty()) {
				model.addAttribute("error", "File is empty!!");
				log.info("File is empty");
				return "file-upload";
			} else if (file.getBytes().length <= 52_42_880) {
				log.info("Size :: " + file.getBytes().length);
				String newName = LocalDate.now().toString() + file.getOriginalFilename();
				log.info("NewName=" + newName);
				File destination = new File(uploadDir + newName);
				file.transferTo(destination);
				log.info("File transfer completed!!");
				model.addAttribute("success", "File uploaded successfully!!");
			} else {
				log.info("Throws exception!!");
				throw new MaxUploadSizeExceededException(5);
			}

		} catch (IllegalStateException | IOException e) {
			log.info("Exception");
			log.info(e.getMessage());
			model.addAttribute("error", "Fail to upload file");
		} catch (MaxUploadSizeExceededException e) {
			log.info("Exception");
			log.info(e.getMessage());
			model.addAttribute("error", "Max upload file size reached!!");
		}

		return "file-upload";
	}
}
