package cafe.springcafe.web;

import cafe.springcafe.dto.DownloadFileRequest;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
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
    MinioClient minioClient =
            MinioClient.builder()
                    .endpoint("http://localhost:9000") // TODO: custom application.properties
                    .credentials("minioadmin", "minioadmin") // TODO: same here
                    .build();

    Logger logger = LoggerFactory.getLogger(FileResource.class);

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file) {
        try (InputStream inputStream = file.getInputStream()) {
            minioClient.putObject(PutObjectArgs
                    .builder()
                    .bucket("confidential")
                    .object(file.getOriginalFilename())
                    .stream(inputStream, file.getSize(), -1)
                    .build());

            return ResponseEntity.ok("File uploaded successfully: " + file.getOriginalFilename());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error uploading file: " + e.getMessage());
        }
    }

    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestBody DownloadFileRequest request) {
        try {
            InputStream stream =
                    minioClient.getObject(GetObjectArgs
                            .builder()
                            .bucket("confidential")
                            .object(request.fileName())
                            .build());

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_PNG);
            headers.setContentDispositionFormData("attachment", request.fileName());

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(IOUtils.toByteArray(stream));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage().getBytes());
        }
    }
}