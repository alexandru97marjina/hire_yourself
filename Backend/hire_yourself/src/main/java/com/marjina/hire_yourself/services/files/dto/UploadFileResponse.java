package com.marjina.hire_yourself.services.files.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Data
@NoArgsConstructor
public class UploadFileResponse {
    private String fileName;
    private Path filePath;
    private String fileType;
    private long size;

    public UploadFileResponse(String fileName, Path filePath, String fileType, long size) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.filePath = filePath;
        this.size = size;
    }

}
