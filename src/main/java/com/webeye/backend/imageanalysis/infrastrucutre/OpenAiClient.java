package com.webeye.backend.imageanalysis.infrastrucutre;

import com.webeye.backend.allergy.dto.response.AllergyResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisPrompt;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import java.net.MalformedURLException;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiClient {
    private final ChatClient chatClient;

    public AllergyResponse explainAllergy(ImageAnalysisRequest request) {

        String system = """
                You are an assistant that extracts allergy-causing ingredients.
                """;
        String user = """
                If there is a table in the attached images that describes exactly '원재료명' (ingredients name), check whether any of the ingredients from the example list are present. If they are, return only the names of those ingredients in Korean. Don't use your imagination, and if it's not there, do not say anything. No explanations are needed. Example: 계란, 우유, 메밀, 땅콩, 대두, 밀, 잣, 호두, 게, 새우, 오징어, 고등어, 조개, 복숭아, 토마토, 닭고기, 돼지고기, 쇠고기, 아황산류. Especially, let me know if there is peach.                 
                 """;
        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request, prompt, AllergyResponse.class);
    }


    private <T> T callWithStructuredOutput(ImageAnalysisRequest request, ImageAnalysisPrompt prompt, Class<T> clazz) {
        BeanOutputConverter<T> outputConverter = new BeanOutputConverter<>(clazz);

        String response = chatClient.prompt()
                .user(promptUserSpec -> {
                    try {
                        promptUserSpec.text(prompt.user() + outputConverter.getFormat());
                        for (String imageUrl : request.urls()) {
                            MimeType extension = ImageMimeType.fromExtension(extractFileExtension(imageUrl));
                            promptUserSpec.media(extension, new UrlResource(imageUrl));
                        }
                    } catch (MalformedURLException exception) {
                        log.error("MalformedURLException: callWithStructuredOutput() 에서 발생");
                        throw new RuntimeException();
                    }
                })
                .system(prompt.system())
                .call()
                .content();

        return outputConverter.convert(response);
    }

    private String extractFileExtension(String url) {
        String fileName = url.substring(url.lastIndexOf('/') + 1);
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex == -1) {
            return "";
        }
        return fileName.substring(dotIndex + 1);
    }
}


