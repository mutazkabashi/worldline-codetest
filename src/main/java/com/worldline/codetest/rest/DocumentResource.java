package com.worldline.codetest.rest;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.worldline.codetest.domain.Profile;
import com.worldline.codetest.exception.DocumentDeleteException;
import com.worldline.codetest.exception.ProfileIsNotExistException;
import com.worldline.codetest.rest.dto.DocumentDto;
import com.worldline.codetest.rest.messages.MessageConstants;
import com.worldline.codetest.rest.messages.ResponseMessage;
import com.worldline.codetest.service.DocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/documents")
@Api(value = "Document", description = "Endpoint for Docuemnt specific operations")
/**
 * Document Rest-end point which is responsible for uploading, Retrieving, and deleting Profile's Documents
 * @author mutaz.Abdelgadir
 *
 */
public class DocumentResource {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DocumentResource.class);
	private final DocumentService documentService;	
	
	public DocumentResource(DocumentService documentService) {
	    this.documentService = documentService;
	}
			 
	@POST
	@Path("/image-upload")
	@Consumes("multipart/form-data")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns param", notes = "Returns param", response = String.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageConstants.DOCUMENT_UPLOADED_SUCCESSFULLY, response = String.class),
			@ApiResponse(code = 400, message = MessageConstants.DOCUMENT_INVALID_PARAMETERS, response = String.class) })
	public Response uploadDocuemnt(MultipartFormDataInput input) throws IOException {
		// Get API input data
		Map<String, List<InputPart>> uploadForm = input.getFormDataMap();
		try {
			String fileName = uploadForm.get("fileName").get(0).getBodyAsString();
			String userName = uploadForm.get("userName").get(0).getBodyAsString();
			List<InputPart> inputParts = uploadForm.get("attachment");
			documentService.createOrUpdateDocuemnt(fileName, userName, inputParts);
			LOGGER.info("Document uploaded succefully " + fileName);
		} catch (ProfileIsNotExistException e) {
			LOGGER.error("Docment Uploading Failed ", e);
			ResponseMessage responseMessage = new ResponseMessage(e.getMessage());
			return Response.status(400).entity(responseMessage).build();
		} catch (Exception e) {
			LOGGER.error("Docment Uploading Failed ", e);
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.DOCUMENT_INVALID_PARAMETERS);
			return Response.status(400).entity(responseMessage).build();
		}
		ResponseMessage responseMessage = new ResponseMessage(MessageConstants.DOCUMENT_UPLOADED_SUCCESSFULLY);
		return Response.status(200).entity(responseMessage).build();
	}

	@Path("/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageConstants.PROFILE_UPDATED_SUCESSFULLY, response = DocumentDto.class),
			@ApiResponse(code = 400, message = MessageConstants.PROFILE_NOT_EXIST, response = ResponseMessage.class) })
	public Response getProfileDocuemnts(@PathParam("name") String name) {
		if (!StringUtils.isAlpha(name) || !StringUtils.isAlpha(name)) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID);
			return Response.status(400).entity(responseMessage).build();
		}
		List<String> documents = documentService.getProfileDocuments(name);
		return Response.status(200).entity(new DocumentDto(documents)).build();
	}

	@Path("/{name}")
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Returns param", notes = "Returns param", response = Profile.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = MessageConstants.PROFILE_UPDATED_SUCESSFULLY, response = ResponseMessage.class),
			@ApiResponse(code = 400, message = MessageConstants.PROFILE_NOT_EXIST, response = ResponseMessage.class) })
	public Response deleteProfileDocuemnts(@PathParam("name") String name) {
		if (!StringUtils.isAlpha(name) || !StringUtils.isAlpha(name)) {
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.PROFILE_PARAMETERS_ARE_NOT_VALID);
			return Response.status(400).entity(responseMessage).build();
		}
		try {
			documentService.deleteProfileDocuments(name);
			LOGGER.info("Document Deleted Successfully ");
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.DOCUMENT_DELETED_SUCCESSFULLY);
			return Response.status(200).entity(responseMessage).build();
		} catch (DocumentDeleteException dde) {
			LOGGER.error("Document Deletion Failed", dde);
			ResponseMessage responseMessage = new ResponseMessage(dde.getMessage());
			return Response.status(400).entity(responseMessage).build();
		} catch (Exception e) {
			LOGGER.error("Document Deletion Failed", e);
			ResponseMessage responseMessage = new ResponseMessage(MessageConstants.DOCUMENT_DELETED_FAIL);
			return Response.status(400).entity(responseMessage).build();
		}
	}

}