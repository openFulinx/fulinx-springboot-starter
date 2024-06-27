package com.fulinx.spring.core.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@Slf4j
public class FileHashCalculator {

    public static String calculateSHA256(String filePath) throws IOException, NoSuchAlgorithmException {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] buffer = new byte[8192];
            int read;
            while ((read = fis.read(buffer)) > 0) {
                digest.update(buffer, 0, read);
            }

            // Convert the digest to a hexadecimal representation
            StringBuilder result = new StringBuilder();
            for (byte b : digest.digest()) {
                result.append(String.format("%02x", b));
            }
            return result.toString();
        } catch (IOException | NoSuchAlgorithmException e) {
            log.error("Error calculating SHA-256 hash for file {}: {}", filePath, e.getMessage());
            throw e; // 抛出异常，由调用方处理
        }
    }
}
