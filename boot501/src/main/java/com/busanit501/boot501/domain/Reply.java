package com.busanit501.boot501.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "board")
public class Reply extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    // 외래키 설정. 마치 디비에서 설정하는 것과 비슷.
    //FetchType.LAZY , 영속성 컨텍스트 = 1차 캐시 테이블,
    // 실제 테이블 과 동기화 놀이.
    // 실제 테이블에 반영하는 정책을 모아서 반영한다.
    @ManyToOne(fetch = FetchType.LAZY)
    private Board board;

    private String replyText;

    private String replyer;

}
