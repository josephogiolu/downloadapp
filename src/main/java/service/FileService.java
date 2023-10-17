package service;

import model.FileContent;
import model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FileContentRepository;
import repository.FileRepository;

import java.io.File;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private FileContentRepository fileContentRepository;

    public Files getFileById(Long fileId) {
        return fileRepository.findById(fileId).orElse(null);
    }

    public byte[] getFileContent(Long fileId) {
        FileContent fileContent = fileContentRepository.findByFileId(fileId);
        return fileContent != null ? fileContent.getFileContent() : new byte[0];
    }
}
