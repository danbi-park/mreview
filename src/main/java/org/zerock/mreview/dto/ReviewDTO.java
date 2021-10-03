package org.zerock.mreview.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor //생성자에 매개변수가 없음 -> 기본생성자
public class ReviewDTO {
    private Long reviewnum;
    private Long mno;
    private Long mid;
    private String nickname;
    private String email;
    private int grade;
    private String text;
    private LocalDateTime regDate, modDate;





}
