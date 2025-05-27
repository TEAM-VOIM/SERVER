package com.webeye.backend.imageanalysis.infrastructure;

import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import groovy.json.StringEscapeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
public class ImageUrlExtractor {
    public List<String> extractImageUrlFromHtml(String html) {
        html = StringEscapeUtils.unescapeJava(html);

        // http://, https://, // 모두 잡는 정규식
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"']((https?:)?//[^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);

        List<String> imageUrls = new ArrayList<>();

        while (matcher.find()) {
            String rawUrl = matcher.group(1);

            if (rawUrl.startsWith("//")) {
                rawUrl = "https:" + rawUrl;
            }
            else if (rawUrl.startsWith("http://")) {
                rawUrl = rawUrl.replaceFirst("http://", "https://");
            }

            imageUrls.add(rawUrl);
        }

        log.info("extracted urls: {}", imageUrls);
        log.info("total number of images: {}", imageUrls.size());

        if (imageUrls.isEmpty()) {
            throw new BusinessException(ErrorCode.IMAGE_URL_NOT_FOUND);
        }
        return imageUrls;
    }
}
