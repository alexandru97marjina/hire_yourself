package com.marjina.hire_yourself.controllers;

import com.marjina.hire_yourself.common.response.ResponseDTO;
import com.marjina.hire_yourself.services.files.FileStorageService;
import com.marjina.hire_yourself.services.files.dto.UploadFileResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.marjina.hire_yourself.common.util.consts.GlobalConst.SUCCESS;
import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/api/file/")
public class FileController {

    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;

    @PostMapping("/uploadFile")
    public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file) {
        return fileStorageService.storeFile(file);
    }

    @PostMapping("/uploadMultipleFiles")
    public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        return Arrays.stream(files)
                .map(this::uploadFile)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/getFilePath/{fileName:.+}")
    public ResponseEntity<ResponseDTO> downloadFile(@PathVariable String fileName) {
//        // Load file as Resource
//        Resource resource = fileStorageService.loadFileAsResource(fileName);
//
//        // Try to determine file's content type
//        String contentType = null;
//        try {
//            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
//        } catch (IOException ex) {
//            logger.info("Could not determine file type.");
//        }
//
//        // Fallback to the default content type if type could not be determined
//        if(contentType == null) {
//            contentType = "application/octet-stream";
//        }

        return ResponseEntity.ok(new ResponseDTO<>(SUCCESS, fileStorageService.getFilePath(fileName),
                "Successful user update", emptyList()));
    }

}
