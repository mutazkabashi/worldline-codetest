package com.worldline.codetest.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.worldline.codetest.domain.Document;
import com.worldline.codetest.exception.DocumentDeleteException;
import com.worldline.codetest.exception.DocumentUploadException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.repository.DocumentRepository;

public class DocumentService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentService.class);

	private DocumentRepository documentRepository;
	private ProfileService profileService;
	private String uploadFilePath = "documents";

	public DocumentService(DocumentRepository documentRepository, ProfileService profileService) {
		super();
		this.documentRepository = documentRepository;
		this.profileService = profileService;
	}

	// Validation is already done on the rest-api end point, it is best practice to
	// make another validation
	// here but i will skip it for simplicity
	public void createOrUpdateDocuemnt(String fileName, String userName, List<InputPart> inputParts)
			throws ProfileIsNotExistException, DocumentUploadException, IOException {
		// TODO need to move to constructor
		createDirectory(uploadFilePath).getAbsolutePath();
		if (profileService.getProfile(userName) == null) {
			throw new ProfileIsNotExistException("No Profile Found for the following User " + userName);
		}

		for (InputPart inputPart : inputParts) {
			try {
				// Use this header for extra processing if required
				@SuppressWarnings("unused")
				MultivaluedMap<String, String> header = inputPart.getHeaders();

				// convert the uploaded file to inputstream
				InputStream inputStream = inputPart.getBody(InputStream.class, null);

				byte[] bytes = IOUtils.toByteArray(inputStream);
				// constructs upload file path
				fileName = uploadFilePath + "/" + fileName;
				writeFile(bytes, fileName);
				LOGGER.info("Document saved Successfully to HardDisk");
				Document document = new Document(userName, fileName);
				documentRepository.createOrUpdateDocument(document);
			} catch (Exception e) {
				throw new DocumentUploadException("Uploading Docuemnt Failed for user " + userName);
			}
		}
		return;
	}

	public List<String> getProfileDocuments(String name) {
		return documentRepository.getProfileDocuemnts(name);
	}

	public void deleteProfileDocuments(String name) throws DocumentDeleteException {

		List<String> deletedFiles = documentRepository.deleteProfileDocuemnts(name);
		try {
			deleteFile(deletedFiles);
			LOGGER.info("Profile's Documments deleted Successfully");
			return;
		} catch (IOException e) {
			throw new DocumentDeleteException("Removing Profile documents failed ");
		}
	}

	// Utility methods
	private void writeFile(byte[] content, String filename) throws IOException {
		File file = new File(filename);
		if (!file.exists()) {
			file.createNewFile();
		}
		FileOutputStream fop = new FileOutputStream(file);
		fop.write(content);
		fop.flush();
		fop.close();
	}

	private void deleteFile(List<String> fileNames) throws IOException {
		fileNames.stream().forEach(fileName -> {
			File file = new File(fileName);
			file.delete();
		});
	}

	private File createDirectory(String directoryPath) throws IOException {
		File dir = new File(directoryPath);
		if (dir.exists()) {
			return dir;
		}
		if (dir.mkdirs()) {
			return dir;
		}
		throw new IOException("Failed to create directory '" + dir.getAbsolutePath() + "' for an unknown reason.");
	}
}
