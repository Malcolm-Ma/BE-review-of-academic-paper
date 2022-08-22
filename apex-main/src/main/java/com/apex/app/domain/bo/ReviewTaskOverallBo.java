package com.apex.app.domain.bo;

import cn.hutool.core.bean.BeanUtil;
import com.apex.app.domain.model.BiddingPreference;
import com.apex.app.domain.model.SubmissionBase;
import com.apex.app.domain.model.ReviewTaskOverall;
import com.apex.app.domain.model.UserBase;
import com.apex.app.domain.type.BiddingPrefEnum;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Objects;

/**
 * Review Task Overall Bo
 *
 * @author Mingze Ma
 */
@Data
public class ReviewTaskOverallBo {

    private String id;

    @ApiModelProperty(value = "key for frontend table render")
    private String key;

    private UserDisplayBo userInfo;

    private String orgId;

    private SubmissionBase submissionInfo;

    private ReviewTaskOverall reviewTask;

    private String biddingPreference;

    public ReviewTaskOverallBo(
            UserBase userBase,
            String orgId,
            SubmissionBase submissionInfo,
            ReviewTaskOverall reviewTask,
            BiddingPreference preference
    ) {
        setUserInfo(userBase);
        this.orgId = orgId;
        this.submissionInfo = submissionInfo;
        this.reviewTask = reviewTask;
        this.id = reviewTask.getId();
        this.key = reviewTask.getId();
        if (preference == null) {
            this.biddingPreference = "";
        } else {
            this.biddingPreference = Objects.requireNonNull(BiddingPrefEnum.parseValue(preference.getPreference())).name();
        }
    }

    public void setUserInfo(UserBase userBase) {
        UserDisplayBo userDisplay = new UserDisplayBo();
        BeanUtil.copyProperties(userBase, userDisplay);
        this.userInfo = userDisplay;
    }
}
