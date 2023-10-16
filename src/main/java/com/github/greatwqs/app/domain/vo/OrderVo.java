package com.github.greatwqs.app.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author greatwqs
 * Create on 2020/7/4
 */
@Getter
@Setter
@ToString
public class OrderVo {
    private Long id;

    private String orderaccount;

    private String orderclass;

    private Long subjectid;

    private SubjectVo subject;

    private Long tickettypeid;

    private TicketTypeVo ticketType;

    private String ticketno;

    private BigDecimal orderprice;

    private String paytype;

    private Date ordertime;

    private String orderdes;

    private Boolean isvalid;

    private Long createuserid;

    private Date createtime;

    private Long updateuserid;

    private Date updatetime;

    /***
     * 是否可以更新 或者 删除?
     */
    private Boolean canUpdateOrDelete;

    @JsonIgnore
    public String getSubjectName() {
        if (this.subject != null) {
            return this.subject.getName();
        } else {
            return "无";
        }
    }

    @JsonIgnore
    public String getTicketTypeName() {
        if (this.ticketType != null) {
            return this.ticketType.getName();
        } else {
            return "无";
        }
    }
}