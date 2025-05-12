package com.webeye.backend.imageanalysis.infrastructure;

import com.webeye.backend.allergy.dto.response.AllergyAiResponse;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.explanation.dto.request.ProductAnalysisRequest;
import com.webeye.backend.explanation.dto.request.ProductDetailAnalysisRequest;
import com.webeye.backend.explanation.dto.response.DetailExplanationResponse;
import com.webeye.backend.explanation.dto.response.PointExplanationResponse;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisPrompt;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.nutrition.dto.response.NutritionAiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeType;

import java.net.MalformedURLException;
import java.util.List;

import static com.webeye.backend.global.error.ErrorCode.FILE_EXTENSION_NOT_FOUND;
import static com.webeye.backend.global.error.ErrorCode.INVALID_IMAGE_URL;

@Slf4j
@Component
@RequiredArgsConstructor
public class OpenAiClient {
    private final ChatClient chatClient;

    public PointExplanationResponse explainProductPoint(ProductAnalysisRequest request) {
        String system = """
                You are an expert in analyzing product images and extracting key information that helps users make purchase decisions.
                Based on the image provided, extract only the most relevant and concise information that highlights the product's core value.
                The extracted list should contain no more than 6 items. Do not include unnecessary or overly detailed information—only what's essential for a buyer.
                """;
        String user = """
                Analyze the image and extract key product information in the format below.
                You may adjust the content depending on the product type, but make sure the extracted items are helpful for making a purchase decision.
                Limit the result to a maximum of 6 items, and keep each one clear and concise.
                Below is just a guideline — instead of following the content exactly, follow the intent.
                                              
                Example (Generate appropriately according to the product)
                - 핵심 특징 요약 (제품 포인트)
                - 당류 비교 (10g당 당류 함량 비교)
                - 제품 추천 대상
                - 상세 원재료
                - 영양 정보 (1개 10g 기준)
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request.urls(), prompt, PointExplanationResponse.class);
    }


    public DetailExplanationResponse explainProductDetail(ProductDetailAnalysisRequest request) {
        String system = """
                You are an expert in providing detailed explanations about products based on images.
                When a user provides a product description image along with the key elements of that description, you should offer a clear and detailed explanation of that element.
                In this explanation, you must provide very detailed information about that element from the image. Answer in Korean.
                """;

        String user = String.format("""
                Key descriptive element: %s
                I have provided a product description image along with the key descriptive elements extracted from the image.
                Please generate a detailed explanation of the provided key descriptive element.
                """, request.description());


        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request.urls(), prompt, DetailExplanationResponse.class);
    }

    public AllergyAiResponse explainAllergy(FoodProductAnalysisRequest request) {
        String system = """
                You are an OCR assistant that extracts and detects allergenic ingredients from Korean product label images. Always treat partial matches inside compound words as valid if they contain the full Korean name of an allergen.
                                                                               
                """;

        String user = """
                Step 1: Carefully examine the attached image(s).
                
                Identify the box that contains the full list of ingredients, labeled with '원재료명' or similar (e.g., '원재료 및 함량').
                
                Only extract text from this box — ignore all other parts of the image, including allergy warnings or separate notices.
                
                Step 2: From the identified box, extract the ingredient list **exactly as written**, without summarizing, translating, or omitting anything — including words like '분말', '가루', or '함유'.
                
                Step 3: Compare the text to the following list of allergenic ingredients:
                계란(EGG), 우유(MILK), 메밀(BUCKWHEAT), 땅콩(PEANUT), 대두(SOYBEAN), 밀(WHEAT), 잣(PINE_NUT), 호두(WALNUT), 게(CRAB), 새우(SHRIMP), 오징어(SQUID), 고등어(MACKEREL), 조개(SHELLFISH), 복숭아(PEACH), 토마토(TOMATO), 닭고기(CHICKEN), 돼지고기(PORK), 쇠고기(BEEF), 아황산류(SULFITE).

                Return true for an allergen if its full Korean name appears **anywhere inside any word** in the ingredient list — even if it is part of a compound word (e.g., "호두함유", "호두분말", "밀가루").
                
                Return false if the full Korean allergen word does not appear in any part of the ingredient text.
                
                Step 4: Output a list of booleans (true/false), in the same order as the allergen list above.
                 """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request.urls(), prompt, AllergyAiResponse.class);
    }

    public NutritionAiResponse explainNutrition(FoodProductAnalysisRequest request) {
        String system = """
                You are a nutrition description assistant.
                """;

        String user = """
                If the attached images contain 'nutrition information', please provide the amount of each nutrient in the format I sent.
                If the nutritional information is not included, set the isNutrientIncluded field to false; if it is included, set it to true.
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request.urls(), prompt, NutritionAiResponse.class);
    }

    public CosmeticResponse explainCosmetic(ProductAnalysisRequest request) {
        String system = """
                You are a cosmetic ingredients description assistant.
                """;

        String user = """
                Check the attached image for cosmetic ingredients.
                
                Use this mapping:
                {
                "아밀신남알": "amylCinnamal",
                "벤질알코올": "benzylAlcohol",
                "신나밀알코올": "cinnamylAlcohol",
                "시트랄": "citral",
                "유제놀": "eugenol",
                "하이드록시시트로넬알": "hydroxycitronellal",
                "이소유제놀": "isoeugenol",
                "아밀신나밀알코올": "amylCinnamylAlcohol",
                "벤질살리실레이트": "benzylSalicylate",
                "신남알": "cinnamal",
                "쿠마린": "coumarin",
                "제라니올": "geraniol",
                "하이드록시이소헥실3-사이클로헥센카복스알데하이드": "hydroxyisohexyl3CyclohexeneCarboxaldehyde",
                "아니스에탄올": "anisylAlcohol",
                "벤질신나메이트": "benzylCinnamate"
                }
                Return true only if the exact full Korean ingredient name appears continuously and separately; otherwise, return false.
                Ignore partial, similar, or incomplete matches.
                
                Note: "hicc" and "Hydroxyisohexyl 3-Cyclohexene Carboxaldehyde" are the same.
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(request.urls(), prompt, CosmeticResponse.class);
    }

    private <T> T callWithStructuredOutput(List<String> urls, ImageAnalysisPrompt prompt, Class<T> clazz) {
        BeanOutputConverter<T> outputConverter = new BeanOutputConverter<>(clazz);

        String response = chatClient.prompt()
                .user(promptUserSpec -> {
                    try {
                        promptUserSpec.text(prompt.user() + outputConverter.getFormat());
                        for (String imageUrl : urls) {
                            MimeType extension = ImageMimeType.fromExtension(extractFileExtension(imageUrl));
                            promptUserSpec.media(extension, new UrlResource(imageUrl));
                        }
                    } catch (MalformedURLException exception) {
                        log.error("MalformedURLException: callWithStructuredOutput() 에서 발생");
                        throw new BusinessException(INVALID_IMAGE_URL);
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
            throw new BusinessException(FILE_EXTENSION_NOT_FOUND);
        }
        return fileName.substring(dotIndex + 1);
    }

}


