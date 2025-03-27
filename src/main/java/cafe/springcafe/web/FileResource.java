package cafe.springcafe.web;

import cafe.springcafe.dto.DownloadFileRequest;
import cafe.springcafe.service.MinioService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/file")
public class FileResource {

    private final MinioService minioService;
    private final Logger logger = LoggerFactory.getLogger(FileResource.class);

    public FileResource(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping(value = "/upload",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            minioService.uploadFile(
                    file.getOriginalFilename(),
                    inputStream,
                    file.getContentType()
            );
            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            logger.error("Error uploading file: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestBody DownloadFileRequest request) {
        try (InputStream stream = minioService.downloadFile(request.fileName())) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", request.fileName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(IOUtils.toByteArray(stream));
        } catch (Exception e) {
            logger.error("Error downloading file: {}", e.getMessage(), e);
            return ResponseEntity.status(500).body(("Error downloading file: " + e.getMessage()).getBytes());
        }
    }
}