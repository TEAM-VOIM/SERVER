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
    private static final int MAX_IMAGE_COUNT = 20;

    public static List<String> extractImageUrlFromHtml(String html) {
        String unescapedHtml = unescapeHtml(html);
        List<String> imageUrls = extractImageUrls(unescapedHtml);
        List<String> trimmedUrls = trimToMaxSize(imageUrls, MAX_IMAGE_COUNT);

        log.info("extracted urls: {}", trimmedUrls);
        log.info("total number of images: {}", trimmedUrls.size());

        if (trimmedUrls.isEmpty()) {
            throw new BusinessException(ErrorCode.IMAGE_URL_NOT_FOUND);
        }

        return trimmedUrls;
    }

    private static String unescapeHtml(String html) {
        return StringEscapeUtils.unescapeJava(html);
    }

    private static List<String> extractImageUrls(String html) {
        Pattern pattern = Pattern.compile("<img[^>]+src=[\"']((https?:)?//[^\"']+)[\"']");
        Matcher matcher = pattern.matcher(html);

        List<String> imageUrls = new ArrayList<>();

        while (matcher.find()) {
            String rawUrl = matcher.group(1);

            if (rawUrl.startsWith("//")) {
                rawUrl = "https:" + rawUrl;
            } else if (rawUrl.startsWith("http://")) {
                rawUrl = rawUrl.replaceFirst("http://", "https://");
            }

            imageUrls.add(rawUrl);
        }

        return imageUrls;
    }

    private static List<String> trimToMaxSize(List<String> urls, int maxSize) {
        if (urls.size() <= maxSize) {
            return urls;
        }
        return new ArrayList<>(urls.subList(urls.size() - maxSize, urls.size()));
    }
}