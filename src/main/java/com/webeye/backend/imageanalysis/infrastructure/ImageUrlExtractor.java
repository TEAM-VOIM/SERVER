package com.webeye.backend.imageanalysis.infrastructure;

import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.global.error.ErrorCode;
import groovy.json.StringEscapeUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class ImageUrlExtractor {
    public static List<String> extractImageUrlFromHtml(String html) {
        html = StringEscapeUtils.unescapeJava(html);

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

        if (imageUrls.isEmpty()) {
            throw new BusinessException(ErrorCode.IMAGE_URL_NOT_FOUND);
        }
        return imageUrls;
    }
}
