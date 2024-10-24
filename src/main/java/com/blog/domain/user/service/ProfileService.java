package com.blog.domain.user.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class ProfileService {
    private List<String> urlList = Arrays.asList(
            "https://i.imgur.com/VnZtNxH.png",
            "https://i.imgur.com/QHfydVa.png",
            "https://i.imgur.com/VXvXELL.png",
            "https://i.imgur.com/aXtrK5E.png",
            "https://i.imgur.com/D4UpBrx.png",
            "https://i.imgur.com/UIAinYd.png"
    );

    public String getRandomUrl(String input) {
        try {
            if (input == null || input.isEmpty()) {
                throw new IllegalArgumentException("Input cannot be null or empty");
            }
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            int hashCode = Arrays.hashCode(hashBytes);
            int index = Math.abs(hashCode) % urlList.size();
            System.out.println("index"+ index);
            return urlList.get(index);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while generating URL", e);
        }
    }
}
