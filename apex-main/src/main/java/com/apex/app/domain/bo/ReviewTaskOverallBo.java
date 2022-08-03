package com.apex.app.domain.bo;

import cn.hutool.core.bean.BeanUtil;
import com.apex.app.domain.model.PaperBase;
import com.apex.app.domain.model.ReviewTaskOverall;
import com.apex.app.domain.model.UserBase;
import lombok.Data;

/**
 * Review Task Overall Bo
 *
 * @author Mingze Ma
 */
@Data
public class ReviewTaskOverallBo {

    private UserDisplayBo userInfo;

    private String orgId;

    private PaperBase paperInfo;

    private ReviewTaskOverall reviewTask;

    public ReviewTaskOverallBo() {

    }

    public ReviewTaskOverallBo(UserBase userBase, String orgId, PaperBase paperInfo, ReviewTaskOverall reviewTask) {
        setUserInfo(userBase);
        this.orgId = orgId;
        this.paperInfo = paperInfo;
        this.reviewTask = reviewTask;
    }

    public void setUserInfo(UserBase userBase) {
        UserDisplayBo userDisplay = new UserDisplayBo();
        BeanUtil.copyProperties(userBase, userDisplay);
        this.userInfo = userDisplay;
    }
}
