package com.apex.app.controller.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mingze Ma
 */
@Data
public class BiddingPrefSummaryResponse {

    private Integer interest;

    private Integer maybe;

    private Integer no;

    private Integer conflict;

    private Integer unsigned;

    private Integer total;

    public BiddingPrefSummaryResponse() {
        this.interest = 0;
        this.maybe = 0;
        this.no = 0;
        this.conflict = 0;
        this.unsigned = 0;
        this.total = 0;
    }

    public void setUnsigned() {
        this.unsigned = total - interest - maybe - no - conflict;
    }
}
