package com.webeye.backend.imageanalysis.infrastructure;

import com.webeye.backend.global.error.BusinessException;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

import static com.webeye.backend.global.error.ErrorCode.UNSUPPORTED_IMAGE_TYPE;

public enum ImageMimeType {
    PNG("png", MimeTypeUtils.IMAGE_PNG),
    JPEG("jpeg", MimeTypeUtils.IMAGE_JPEG),
    JPG("jpg", MimeTypeUtils.IMAGE_JPEG),
    GIF("gif", MimeTypeUtils.IMAGE_GIF),
    WEBP("webp", MimeTypeUtils.parseMimeType("image/webp"));

    private final String extension;
    private final MimeType mimeType;

    private static final Map<String, MimeType> EXTENSION_TO_MIMETYPE_MAP;

    static {
        EXTENSION_TO_MIMETYPE_MAP = Arrays.stream(values())
                .collect(Collectors.toMap(type -> type.extension, type -> type.mimeType));
    }

    ImageMimeType(String extension, MimeType mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    public static MimeType fromExtension(String extension) {
        String lowerExtension = extension.toLowerCase();
        MimeType mimeType = EXTENSION_TO_MIMETYPE_MAP.get(lowerExtension);
        if (mimeType == null) {
            throw new BusinessException(UNSUPPORTED_IMAGE_TYPE);
        }
        return mimeType;
    }
}