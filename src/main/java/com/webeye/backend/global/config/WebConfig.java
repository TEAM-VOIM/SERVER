package com.webeye.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns(
                        "https://voim.store",
                        "http://localhost:3000",
                        "chrome-extension://iofbhhcbidmfcmpjndglaignlfdojpcm",
                        "chrome-extension://jeppkpjgeheckphiogogbffdenhlkclh",
                        "chrome-extension://ecbaebehchfclbcglpabiclbgkjoihmf",
                        "chrome-extension://lhgneonbbjkppefiifpanchnbaigecil",
                        "chrome-extension://mphnlcljehhgppcoamgpnnaamidpjkch",
                        "chrome-extension://licieibgjjilbdjfheljdmllhbopbajo",
                        "chrome-extension://jbnhfmonamkklnglmdhklbfkojofhppk",
                        "chrome-extension://ehgaglekgllijnoglmdfeingpecfjbmb",
                        "chrome-extension://libhakfegdlojphbiaaejoopedaodbgj"
                )
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
