package com.github.greatwqs.app.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * Created by MyBatis Generator 2020/06/28
 * https://github.com/greatwqs/mybatis-generator-plugin
 */
@Getter
@Setter
@ToString
public class TicketTypeDto {
    private Long id;

    private String name;

    private Long createuserid;

    private Long updateuserid;
}