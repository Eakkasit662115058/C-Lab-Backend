package se331.lab.Util;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class SupabaseStorageService {
    @Value("${supabase.storage.bucket}")
    String bucketName;
    @Value("${supabase.storage.endpoint_output}")
    String outputUrl;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private final S3Client s3Client;
    public String uploadFile(MultipartFile file) throws IOException {
        Path tempFile = Files.createTempFile("Upload-",file.getOriginalFilename());
        Files.copy(file.getInputStream(), tempFile, StandardCopyOption.REPLACE_EXISTING);

        String saltFileName = LocalDateTime.now().format(formatter) + "-" + file.getOriginalFilename();

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(saltFileName)
                .build();

        s3Client.putObject(putObjectRequest, tempFile);

//        String url = s3Client.utilities().getUrl(GetUrlRequest).toExternalForm();
        String url = String.format("%s/%s/%s", outputUrl, bucketName, saltFileName);

        Files.delete(tempFile);
        return url;
    }
}
