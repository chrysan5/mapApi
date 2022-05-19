package com.mapApi.service;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KakaoMap {

    private WebDriver driver;
    private WebElement element;

    // 1. 드라이버 설치 경로
    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
    public static String WEB_DRIVER_PATH = "C:/Users/mj/Desktop/study/chromedriver.exe";

    public void naverLogin (){
        // WebDriver 경로 설정
        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");       //팝업안띄움
//        options.addArguments("headless");                       //브라우저 안띄움
        options.addArguments("--disable-gpu");			//gpu 비활성화
//        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음

        driver = new ChromeDriver(options);
    }


    public void activateBot() {
        try {
            String url = "https://place.map.kakao.com/1569355921";
            driver.get(url);
            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간

            //카페이름
            element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[1]/div[1]/div[2]/div/h2"));
            String title = element.getText();
            //평점
            element = driver.findElement(By.xpath("//*[@id=\"mArticle\"]/div[5]/div[2]/div/em"));
            String star = element.getText();
            //이미지
            element = driver.findElement(By.className("bg_present"));
            String bgImage = element.getCssValue("background-image");
            //url 자르기
            String newBgImage =bgImage.substring(5,bgImage.length()-2);
            log.info(newBgImage);



            log.info(star);
            log.info(bgImage);
            log.info(title);

        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.close(); // 5. 브라우저 종료
        }


    }

}

