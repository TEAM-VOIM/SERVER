package com.webeye.backend.imageanalysis.infrastructure;

import com.webeye.backend.allergy.dto.response.AllergyAiResponse;
import com.webeye.backend.cosmetic.dto.response.CosmeticResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisRequest;
import com.webeye.backend.imageanalysis.dto.response.ImageAnalysisResponse;
import com.webeye.backend.product.dto.response.DetailExplanationResponse;
import com.webeye.backend.global.error.BusinessException;
import com.webeye.backend.healthfood.dto.HealthFoodAiResponse;
import com.webeye.backend.imageanalysis.dto.request.ImageAnalysisPrompt;
import com.webeye.backend.productdetail.domain.type.OutlineType;
import com.webeye.backend.product.dto.request.FoodProductAnalysisRequest;
import com.webeye.backend.nutrition.dto.response.NutritionAiResponse;
import com.webeye.backend.rawmaterial.dto.response.RawMaterialAiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
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


    public DetailExplanationResponse explainProductDetail(OutlineType outline, List<String> urls) {
        String system = """
                You are an expert in providing detailed explanations about products based on images.
                When a user provides a product description image along with the key outline of that description, you should offer a clear and detailed explanation of that element.
                In this explanation, you must provide very detailed information about that element from the image. Answer in Korean.
                """;

        String user = String.format("""
                Key Outline: %s
                Please generate a detailed explanation of the provided outline.
                """, outline.getPrompt());


        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(urls, prompt, DetailExplanationResponse.class);
    }

    public AllergyAiResponse explainAllergy(List<String> urls) {
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
        return callWithStructuredOutput(urls, prompt, AllergyAiResponse.class);
    }

    public NutritionAiResponse explainNutrition(List<String> urls) {
        String system = """
                You are a nutrition description assistant.
                """;

        String user = """
                If the attached images contain 'nutrition information', please provide the amount of each nutrient in the format I sent.
                If the nutritional information is not included, set the isNutrientIncluded field to false; if it is included, set it to true.
                You are given a nutrition label image. Extract the number of grams that the nutritional values are based on.
                This is typically written as "per 100g", "per 1 serving (XXg)", "100 g당" (in Korean), or similar.
                Return only the numeric gram value in a field named nutrientReferenceAmount.
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(urls, prompt, NutritionAiResponse.class);
    }


    public CosmeticResponse explainCosmetic(List<String> urls) {
        String system = """
                You are an expert in identifying harmful cosmetic ingredients based on Korean labels.
                You always return exact matches based on a predefined Korean-to-English mapping.
                """;

        String user = """
                Carefully examine the attached image. Focus only on **Korean ingredient names** that appear under the section labeled **"전성분"**.

                Your task is to check for the exact **presence** of the following Korean ingredient names — but **only within the "전성분" section**.

                These names must appear **exactly and completely**, including spacing and punctuation.
                Ignore any matches that appear **outside of the "전성분" section**, or are **partial/similar**.
                
                Use this mapping:
                {
                  "아보벤존": "avobenzone",
                  "이소프로필 알코올": "isopropylAlcohol",
                  "소듐 라우릴/라우레스 설페이트 (SLS, SLES)": "sodiumLaurylSulfate",
                  "트리에탄올아민": "triethanolamine",
                  "폴리에틸렌 글라이콜 (PEGs)": "polyethyleneGlycol",
                  "합성 착색료": "syntheticColorant",
                  "이소프로필 메틸페놀": "isopropylMethylphenol",
                  "소르빅 애씨드": "sorbicAcid",
                  "호르몬류": "hormone",
                  "디부틸 하이드록시 톨루엔 (BHT)": "dibutylHydroxyToluene",
                  "파라벤류 (Methyl-, Ethyl-, Propylparaben 등)": "parabens",
                  "트리클로산": "triclosan",
                  "부틸 하이드록시아니솔 (BHA)": "butylatedHydroxyanisole",
                  "옥시벤존": "oxybenzone",
                  "이미다졸리디닐 우레아, 디아졸리디닐 우레아, DMDM 하이단토인 등": "imidazolidinylUrea",
                  "미네랄 오일, 파라핀오일": "mineralOil",
                  "티몰": "thymol",
                  "트라이아이소프로판올아민": "triisopropanolamine",
                  "인공 향료 (Synthetic Fragrance, Parfum)": "syntheticFragrance",
                  "페녹시에탄올": "phenoxyethanol"
                }
                Return true only if the exact full Korean ingredient name appears continuously and separately; otherwise, return false.
                Ignore partial, similar, or incomplete matches.
                
                
                Note: "인공 향료 (Synthetic Fragrance, Parfum)" is considered the same as "향료" or "Fragrance" — treat all of them as matching "syntheticFragrance".
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(urls, prompt, CosmeticResponse.class);
    }

    public HealthFoodAiResponse explainHealthFood(List<String> urls) {
        String system = """
                You are a food label OCR expert. Your task is to extract ingredient names from Korean health supplement product images.
                You must return only a list of ingredient names, in JSON format. Do not summarize or explain anything.
                """;

        String user = """
                Please examine the attached image of a Korean health food product.

                Look for the section that lists ingredients. This section is usually labeled with:
                - '원재료명'
                - '원재료 및 함량'
                - '영양·기능정보'
                - or similar titles
                
                From this section, extract only the ingredient names. For example:
                - "비타민C 100mg" → "비타민C"
                - "베타카로틴함유" → "베타카로틴"
                - "정제수(물)" → "정제수"
                
                Ignore quantities (mg, %, g), descriptors (함유, 분말), or anything in parentheses
                
                Return only the names of the ingredients in the following JSON format:
                {
                 "itemNames": ["비타민C", "베타카로틴", "정제수"]
                }

                If no ingredients are found, return:
                {
                  "itemNames": []
                }
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(urls, prompt, HealthFoodAiResponse.class);
    }

    public ImageAnalysisResponse explainImage(ImageAnalysisRequest request) {
        String system = """
                You are a helpful and concise assistant that specializes in describing images.
                Always provide clear, accurate, and human-like descriptions of the image content.
                Focus on the most important visual details such as objects, people, actions, scenes, and context.
                Avoid speculation unless necessary, and do not include irrelevant information. Answer in Korean
                """;
        String user = """
                Please describe the contents of this image in detail.
                """;

        ImageAnalysisPrompt prompt = new ImageAnalysisPrompt(system, user);
        return callWithStructuredOutput(List.of(request.url()), prompt, ImageAnalysisResponse.class);
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


    public RawMaterialAiResponse explainRawMaterial(FoodProductAnalysisRequest request) {
        Message systemMessage = new SystemMessage("""
                너는 원재료 식품의 이름을 반환하는 어시스턴트이다.
                """);

        Message userMessage = new UserMessage(String.format("""
                %s
                다음은 온라인 쇼핑몰의 식품 제목입니다. 불필요한 수식어나 단위를 제거하고, 핵심 식품명만 추출하세요. 부위명(예: 살, 조각, 포, 덩어리 등)은 제거하고, 일반 식재료명을 사용하세요. 가능한 한 짧고 일반적인 명사 형태로 출력하십시오.
                (참고로 "고구마순"은 "고구마_줄기" 이다.)
                예시:
                1. 달콤한 꿀고구마, 1박스, 10kg 못난이 (꿀&호박 랜덤발송) → 꿀고구마
                2. 건나물 말린 토란줄기 토란대 미얀마산 1kg, 1개 → 토란대_줄기
                3. [수산맥] 수율90%%내외 박달홍게 프리미엄 선주직송, 1박스, 3kg (고급형10미) → 붉은대게
                """, request.title()));

        Prompt prompt = new Prompt(List.of(systemMessage, userMessage));
        String result = chatClient.prompt(prompt).call().content();

        return RawMaterialAiResponse.builder()
                .name(result)
                .build();
    }

}


