package com.busanit501.boot501.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// 데이터만 전달하기.
@RestController
@Log4j2
public class RestSampleController {
  @GetMapping("/hiRest")
  public String[] hi(){
    log.info("hi test~~~~rest ");
    return new String[]{"오늘 점심 뭐먹지?","도시락","김치볶음밥"};
    // 스프링 부트는 기본적으로 잭슨 이라는 이름의 라이브러리 도구가 설치됨.
    // 역할 : 문자열 -> JSON 타입으로 자동 변환 해주는 도구.

    // 공공 데이터 , 관광관련된 데이터를 오픈소스, 받는 타입을 JSON
    // JSON -> 원하는 모델에 바로 맵핑.

  }
}







