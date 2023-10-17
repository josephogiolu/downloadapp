package controller;

import model.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.FileService;
import service.PermissionService;
import vo.NotFoundException;

@RestController
@RequestMapping("/api/files")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/{fileId}/download")
    public ResponseEntity<byte[]> downloadFile(@PathVariable Long fileId, @RequestParam String userEmail) {
        Files file = fileService.getFileById(fileId);
        if (file == null) {
            throw new NotFoundException("File not found");
        }
        // Get the binary data of the file
        byte[] fileContent = fileService.getFileContent(fileId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", file.getName());
        return new ResponseEntity<>(fileContent, headers, HttpStatus.OK);
    }
}

