package cafe.springcafe.service;

import cafe.springcafe.config.MinioProperties;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class MinioService {

    private final Logger logger = LoggerFactory.getLogger(MinioService.class);
    private final MinioClient minioClient;
    private final String bucketName;

    public MinioService(MinioProperties minioProperties) {
        this.minioClient = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getUsername(), minioProperties.getPassword())
                .build();
        this.bucketName = minioProperties.getBucket();
    }

    public void uploadFile(String objectName, InputStream inputStream, String contentType) throws Exception {
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .stream(inputStream, -1, 10485760) // 10MB buffer size
                .contentType(contentType)
                .build();
        minioClient.putObject(putObjectArgs);
        logger.info("File uploaded successfully: {}", objectName);
    }

    public InputStream downloadFile(String objectName) throws Exception {
        GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                .bucket(bucketName)
                .object(objectName)
                .build();
        logger.info("File downloaded successfully: {}", objectName);
        return minioClient.getObject(getObjectArgs);
    }
}