package com.marjina.hire_yourself.services.files;

import com.marjina.hire_yourself.services.files.dto.UploadFileResponse;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public interface FileStorageService {

    UploadFileResponse storeFile(MultipartFile file);

    Resource loadFileAsResource(String fileName);

    Path getFilePath(String filePath);

}
