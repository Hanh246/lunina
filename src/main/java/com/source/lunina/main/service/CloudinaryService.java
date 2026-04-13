package com.source.lunina.main.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class CloudinaryService {

    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    public String uploadImage(MultipartFile file) {
        validateImage(file);

        try {
            Map<?, ?> result = cloudinary.uploader().upload(
                    file.getBytes(),
                    ObjectUtils.asMap(
                            "folder", "tutor-avatar",
                            "resource_type", "image"
                    )
            );
            return result.get("secure_url").toString();
        } catch (IOException e) {
            throw new RuntimeException("Gửi ảnh thất bại", e);
        }
    }

    public List<String> uploadListImages(List<MultipartFile> files) {
        if (files == null || files.isEmpty()) {
            throw new RuntimeException("Danh sách file trống");
        }

        List<String> urls = new ArrayList<>();

        for (MultipartFile file : files) {
            try {
                String url = uploadImage(file);
                urls.add(url);
            } catch (Exception e) {
                throw new RuntimeException("Lỗi khi upload file: " + file.getOriginalFilename(), e);
            }
        }

        return urls;
    }

    private void validateImage(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new RuntimeException("Vui lòng chọn một file để upload.");
        }
        if (!file.getContentType().startsWith("image/")) {
            throw new RuntimeException("Chỉ chấp nhận định dạng tệp tin là hình ảnh.");
        }
        String fileName = file.getOriginalFilename();
        if (fileName != null) {
            String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
            List<String> allowedExtensions = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

            if (!allowedExtensions.contains(extension)) {
                throw new RuntimeException("Định dạng file ." + extension + " không được hỗ trợ. Chỉ nhận: jpg, jpeg, png, gif, webp.");
            }
        }
        if (file.getSize() > 5 * 1024 * 1024) {
            throw new RuntimeException("Kích thước ảnh quá lớn! Tối đa là 5MB");
        }
    }
}
