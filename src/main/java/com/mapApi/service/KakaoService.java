package com.mapApi.service;

import com.mapApi.dto.KakaoApiDto;
import com.mapApi.dto.CrawlingDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class KakaoService {
    private final WebClient kakaoWebClient;

    public List<CrawlingDto.Response> search() throws IOException, InterruptedException {
        KakaoApiDto kakaoApiDto = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/local/search/keyword.json")
                        .queryParam("query", "스터디카페")
                        .queryParam("category_group_code", "CE7")
                        .queryParam("x", "15.1")
                        .queryParam("y", "15.2")
                        .queryParam("radius", "100")
                        .queryParam("size", "3")
                        .build()
                )
                .retrieve()
                .bodyToMono(KakaoApiDto.class)
                .block();


        int cnt = 1;
        String title;
        String img;
        String star;
        String imgUrl;

        WebDriver driver;
        WebElement element;

        for (KakaoApiDto.Document document : kakaoApiDto.getDocuments()) {
            log.info(document.getPlace_url());
        }

        List<CrawlingDto.Response> crawlingDtoList = new ArrayList<>();

        for (KakaoApiDto.Document document : kakaoApiDto.getDocuments()) {

            log.info("url list ========================================");
            log.info(document.getPlace_url());
            log.info(String.valueOf("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+cnt));
            cnt += 1;


            String place_url = document.getPlace_url();
    //            String place_url = "https://place.map.kakao.com/1569355921";
    //            String place_url = "https://place.map.kakao.com/1608642290";

            // 드라이버 설치 경로
            String WEB_DRIVER_ID = "webdriver.chrome.driver";
            String WEB_DRIVER_PATH = "C:/Users/mj/Desktop/study/chromedriver.exe"; //폴더위치
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

            // WebDriver 옵션 설정
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            options.addArguments("--disable-popup-blocking");       //팝업안띄움
            options.addArguments("headless");                       //브라우저 안띄움
            options.addArguments("--disable-gpu");            //gpu 비활성화
            //        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음

            driver = new ChromeDriver(options);

            driver.get(place_url);
            Thread.sleep(5000); // 3. 페이지 로딩 대기 시간


            CrawlingDto.Response response = new CrawlingDto.Response();


            //카페이름
            if(!driver.findElements(By.xpath("//*[@id=\"mArticle\"]/div[1]/div[1]/div[2]/div/h2")).isEmpty()) {
                element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[1]/div[1]/div[2]/div/h2"));
                title = element.getText();
                log.info("-----------------------------" + title);
                response.setTitle(title);
            }else {
                title = null;
                log.info("------------title없음-----------------");
            }


            //이미지
            if(!driver.findElements(By.className("bg_present")).isEmpty()) {
                element = driver.findElement(By.className("bg_present"));
                String bgImage = element.getCssValue("background-image");
                img = bgImage.substring(5, bgImage.length() - 2);
                log.info("-----------------------------" + img);
                response.setImgUrl(img);
            }else{
                img = null;
                log.info("------------img없음-----------------");
            }


            //평점
            if(!driver.findElements(By.xpath("//*[@id=\"mArticle\"]/div[5]/div[2]/div/em")).isEmpty()){
                element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[5]/div[2]/div/em"));
                star = element.getText();
                log.info("-----------------------------" + star);
                response.setStar(star);
            }else{
                star = null;
                log.info("-------------star없음----------------");
            }

            crawlingDtoList.add(response);

        }
        log.info(crawlingDtoList.toString());
        return crawlingDtoList;
    }
}



