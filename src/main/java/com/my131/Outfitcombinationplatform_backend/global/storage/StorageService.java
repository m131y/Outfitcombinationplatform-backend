package com.my131.Outfitcombinationplatform_backend.global.storage;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    // 파일 업로드 후 접근 가능한 url을 반환
    String upload(MultipartFile file, String folder);

    // 업로드 시 반환된 url로 파일 삭제
    void delete(String fileUrl);
}
