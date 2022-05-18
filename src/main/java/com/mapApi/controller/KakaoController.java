package com.mapApi.controller;


import com.mapApi.dto.CrawlingDto;
import com.mapApi.service.KakaoMap;
import com.mapApi.service.KakaoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.List;


@Slf4j
@RestController
//@RequestMapping("/kakao/")
@RequiredArgsConstructor
public class KakaoController {

    private final WebClient kakaoWebClient;
    private final KakaoService kakaoService;
    private final KakaoMap kakaoMap;


 /*   @GetMapping("/search5")
//    public String search(@RequestParam String query) {
    public KakaoApiDto search() {
//        Mono<String> mono = WebClient.builder().baseUrl("http://dapi.kakao.com")
        KakaoApiDto kakaoApiDto = WebClient.builder().baseUrl("http://dapi.kakao.com")
                .build().get()
                .uri(builder -> builder.path("/v2/local/search/keyword.json")
                        .queryParam("query", "스터디카페")
                        .queryParam("category_group_code", "CE7")
                        .queryParam("x", "37.5606")
                        .queryParam("y", "126.9433")
                        .queryParam("radius", "100")
                        .queryParam("size", "3")
                        .build()
                )
                .header("Authorization", "KakaoAK " + kakao_apikey)
                .retrieve()
                .bodyToMono(KakaoApiDto.class)
                .block();

//        log.info("kakaoresponse = {}", kakaoApiDto.toString());
//        log.info("kakaoresponse = {}", kakaoApiDto.getClass().getName()); //dto 임
//        log.info("kakaoresponse = {}", kakaoApiDto.getDocuments().get(0).getPlace_url()); //첫번째거만 얻을때

        for (KakaoApiDto.Document document : kakaoApiDto.getDocuments()) {
            log.info(document.getPlace_url());
        }
        return kakaoApiDto;
    }*/


    //위에꺼 리팩토링 한거임
    @GetMapping("/search5")
    public List<CrawlingDto.Response> search() throws IOException, InterruptedException {
        return kakaoService.search();
    }

//
//    @GetMapping("/t")
//    public void TT(){
//        naverLogin.naverLogin();
//        naverLogin.activateBot();
//    }
//
//    @GetMapping("/tt")
//    public void TTy(){
//        melonBot.melon();
//        melonBot.activateBot();
//    }

    @GetMapping("/ttt")
    public void TTyj(){
        kakaoMap.naverLogin();
        kakaoMap.activateBot();
    }






    //KakaoConfig로 webClient 주입 받을경우 이렇게 쓰면 됨
/*    @GetMapping("/search")
    public String search2() {
        Mono<String> mono = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/local/search/keyword.json")
                        .queryParam("query", "스터디카페")
                        .queryParam("category_group_code", "CE7")
                        .queryParam("x", "37.5606")
                        .queryParam("y", "126.9433")
                        .queryParam("radius", "100")
                        .queryParam("size", "1")
                        .build()
                )
                .retrieve().bodyToMono(String.class);
//        log.info("mono = {}", mono.block().toString());
        log.info("결과ㅣ 타입:"+ mono); //MonoFlatMap
        return mono.block(); //얘는 결과가 string 이다.아니다
    }*/



/*    @GetMapping("/search2")
//    public KakaoApiDto getCoordinate(User user) {
    public KakaoApiDto getCoordinate() {
//        String address = user.getAddressHome();
        Mono<KakaoApiDto> kakaoApiDtoMono = kakaoWebClient.get()
                .uri(builder -> builder.path("/v2/local/search/keyword.json")
                        .queryParam("query", "스터디카페")
                        .queryParam("category_group_code", "CE7")
                        .queryParam("x", "37.5606")
                        .queryParam("y", "126.9433")
                        .queryParam("radius", "100")
                        .queryParam("size", "1")
                        .build()
                )
                .retrieve() //어떻게 response를 추출할 것인지를 결정함
                .bodyToMono(KakaoApiDto.class); //body 를 추출
        log.info(kakaoApiDtoMono.getClass().getName());
        return kakaoApiDtoMono.block(); //결과를 동기적으로 처리함
    }*/

}