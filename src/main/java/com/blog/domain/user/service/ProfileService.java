package com.blog.domain.user.service;

import com.blog.domain.user.config.exception.BusinessLogicException;
import com.blog.domain.user.config.exception.ExceptionCode;
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
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            int hashCode = Arrays.hashCode(hashBytes);
            int index = Math.abs(hashCode) % urlList.size();
            return urlList.get(index);
        } catch (NoSuchAlgorithmException e) {
            throw new BusinessLogicException(ExceptionCode.NO_SUCH_ALGORITHM);
        }
    }
}
