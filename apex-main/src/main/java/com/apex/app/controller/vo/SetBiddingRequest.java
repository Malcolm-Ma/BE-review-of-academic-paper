package com.apex.app.controller.vo;

import com.apex.app.domain.type.BiddingPrefEnum;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/** bidding setting request
 * @author Mingze Ma
 */
@Data
public class SetBiddingRequest {

    @NotEmpty
    private String userId;

    @NotEmpty
    private String submissionId;

    @NotEmpty
    private String orgId;

    @NotNull
    private BiddingPrefEnum biddingPref;

}
