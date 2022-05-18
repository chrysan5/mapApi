//package com.mapApi.controller.util;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NaverLogin {
//
//    private WebDriver driver;
//    private WebElement element;
//    private String url;
//
//    // 1. 드라이버 설치 경로
//    public static String WEB_DRIVER_ID = "webdriver.chrome.driver";
//    public static String WEB_DRIVER_PATH = "C:/Users/mj/Desktop/study/chromedriver.exe";
//
//    public void naverLogin (){
//        // WebDriver 경로 설정
//        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
//
//        // 2. WebDriver 옵션 설정
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--start-maximized");
//        options.addArguments("--disable-popup-blocking");       //팝업안띄움
////        options.addArguments("headless");                       //브라우저 안띄움
//        options.addArguments("--disable-gpu");			//gpu 비활성화
////        options.addArguments("--blink-settings=imagesEnabled=false"); //이미지 다운 안받음
//
////        driver = new ChromeDriver(options);
////        try {
////            getDataList();
////        } catch (InterruptedException e) {
////            e.printStackTrace();
////        }
////        driver.close();
////        driver.quit();
//
//        driver = new ChromeDriver(options);
//        url = "https://www.naver.com/";
//    }
//
////    private List<String> getDataList() throws InterruptedException {
//////        일부분에 대한 ExpectedConditions의 로딩 조건이 true가 될때까지 기다렸다가
//////        로딩이 완료되면 바로 다음 명령어를 실행하면서 실행 시간을 단축할 수 있다.
//////                정확하지 않은 파싱결과를 얻을 가능성이 낮아진다.
////        List<String> list = new ArrayList<>();
////
////        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);	//⭐⭐⭐
////        //드라이버가 실행된 뒤 최대 10초 기다리겠다.
////
////        driver.get("https://www.naver.com/");    //브라우저에서 url로 이동한다.
////        //Thread.sleep(1000); //브라우저 로딩될때까지 잠시 기다린다.
////
////        webDriverWait.until(
////                ExpectedConditions.presenceOfElementLocated(By.cssSelector("#sentence-example-list .sentence-list li"))
////                //cssSelector로 선택한 부분이 존재할때까지 기다려라
////        );	//⭐⭐⭐
////
////        List<WebElement> elements = driver.findElements(By.cssSelector("#sentence-example-list .sentence-list li"));
////        for (WebElement element : elements) {
////            System.out.println("----------------------------");
////            System.out.println(element.getText());
////        }
////        return list;
////    }
//
//    public void activateBot() {
//        try {
//            driver.get(url);
//            Thread.sleep(2000); // 3. 페이지 로딩 대기 시간
//
//            // 4. 로그인 버튼 클릭
//            element = driver.findElement(By.className("link_login"));
//            element.click();
//
//            Thread.sleep(1000);
//
//            // ID 입력
//            element = driver.findElement(By.id("id"));
//            element.sendKeys("alswn5406");
//
//            // 비밀번호 입력
//            element = driver.findElement(By.id("pw"));
//            element.sendKeys("freedom7853");
//
//            // 전송
//            element = driver.findElement(By.className("btn_global"));
//            element.submit();
//        }catch (Exception e) {
//            e.printStackTrace();
//        } finally {
////            driver.close(); // 5. 브라우저 종료
//        }
//
//
//    }
//
//
////    public static void main(String[] args) {
////        NaverLogin bot1 = new NaverLogin();
////        bot1.activateBot();
////    }
//}
