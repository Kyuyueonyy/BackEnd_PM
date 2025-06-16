package org.scoula.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class BoardVO {
    private int number;
    private String title;
    private String content;
    private String writer;
    private Date regDate;
    private Date updateDate;
}
