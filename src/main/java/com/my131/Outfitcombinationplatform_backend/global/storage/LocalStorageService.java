package com.my131.Outfitcombinationplatform_backend.global.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@Service
@Profile("dev")
public class LocalStorageService implements StorageService{

    @Value("${storage.local.base-path:uploads}")
    private String basePath;

    @Value("${storage.local.base-url:http://localhost:8080/uploads}")
    private String baseUrl;

    @Override
    public String upload(MultipartFile file, String folder) {
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        Path dirPath = Paths.get(basePath, folder);
        Path filePath = dirPath.resolve(fileName);

        try {
            Files.createDirectories(dirPath);
            Files.copy(file.getInputStream(), filePath);
        } catch (IOException e) {
            throw new RuntimeException("로컬 파일 저장 실패: " + filePath, e);
        }

        log.info("로컬 파일 저장 완료: {}", filePath);
        return baseUrl + "/" + folder + "/" + fileName;
    }

    @Override
    public void delete(String fileUrl) {
        String relativePath = fileUrl.replace(baseUrl + "/", "");
        Path filePath = Paths.get(basePath, relativePath);

        try {
            Files.deleteIfExists(filePath);
            log.info("로컬 파일 삭제 완료: {}", filePath);
        } catch (IOException e) {
            throw new RuntimeException("로컬 파일 삭제 실패: " + filePath, e);
        }
    }
}
