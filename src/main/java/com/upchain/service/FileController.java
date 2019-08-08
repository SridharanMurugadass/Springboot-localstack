package com.upchain.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.upchain.service.AwsS3Client;

@RestController
public class FileController {

	private AwsS3Client amazonClient;

	@Autowired
	FileController(AwsS3Client amazonClient) {
		this.amazonClient = amazonClient;
	}

	@PostMapping("/uploadFile")
	public String uploadFile(@RequestPart(value = "files") MultipartFile files) throws IOException {
		amazonClient.amazonS3()
				.putObject(new PutObjectRequest(amazonClient.getAwsS3Bucket(), files.getName(), files.toString()));
		return "File Uploaded Successfully";
	}

	@GetMapping("/listFiles")
	public ObjectListing listFiles() {
		return amazonClient.amazonS3().listObjects(amazonClient.getAwsS3Bucket());
	}

	@DeleteMapping("/deleteFile/{id}")
	public String deleteFile(@PathVariable String id) {
		amazonClient.amazonS3().deleteObject(amazonClient.getAwsS3Bucket(), id);
		return "File Deleted Successfully";
	}

}
