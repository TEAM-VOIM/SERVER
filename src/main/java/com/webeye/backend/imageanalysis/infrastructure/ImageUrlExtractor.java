package com.webeye.backend.imageanalysis.infrastructure;

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
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"'](//[^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);

        List<String> imageUrls = new ArrayList<>();

        while (matcher.find()) {
            String rawUrl = matcher.group(1);
            String fullUrl = "https:" + rawUrl;
            imageUrls.add(fullUrl);
        }
        log.info("extracted urls: {}", imageUrls);
        log.info("total number of images: {}", imageUrls.size());

        return imageUrls;
    }
}
