package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.ReviewCreateRequest;
import com.apex.app.controller.vo.SubmissionListRequest;
import com.apex.app.domain.bo.ReviewTaskOverallBo;
import com.apex.app.domain.model.*;
import com.apex.app.domain.type.ReviewStatusEnum;
import com.apex.app.mapper.SubmissionBaseMapper;
import com.apex.app.mapper.SubmissionUserMergeMapper;
import com.apex.app.mapper.ReviewTaskOverallMapper;
import com.apex.app.service.ReviewService;
import com.apex.app.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Core paper reviewing service implementation
 *
 * @author Mingze Ma
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    SubmissionBaseMapper submissionBaseMapper;

    @Autowired
    SubmissionUserMergeMapper submissionUserMergeMapper;

    @Autowired
    ReviewTaskOverallMapper reviewTaskOverallMapper;

    @Autowired
    UserAuthService userService;

    @Override
    public ReviewTaskOverallBo createReviewTask(ReviewCreateRequest request) {
        // Prepare to Insert paper base into db
        SubmissionBase paper = new SubmissionBase();
        BeanUtil.copyProperties(request, paper);
        paper.setId(UUID.randomUUID().toString());

        UserBase user = null;
        if (request.getUserId() != null) {
            try {
                user = userService.getUserById(request.getUserId());
            } catch (UsernameNotFoundException e) {
                Asserts.fail(e.getMessage());
                return null;
            }
        } else {
            user = userService.getCurrentUser();
        }
        // Create relation of user and paper
        SubmissionUserMerge SubmissionUserMerge = new SubmissionUserMerge();
        SubmissionUserMerge.setSubmissionId(paper.getId());
        SubmissionUserMerge.setUserId(user.getId());

        // Create review task overall
        ReviewTaskOverall reviewTask = new ReviewTaskOverall();
        reviewTask.setOrgId(request.getOrgId());
        reviewTask.setSubmissionId(paper.getId());
        reviewTask.setDeadline(request.getDeadline());
        reviewTask.setCreatedTime(new Date());
        reviewTask.setStatus(ReviewStatusEnum.PREPARING.getValue());
        reviewTask.setId(UUID.randomUUID().toString());

        // Insert data into db
        // Insert paper base
        submissionBaseMapper.insert(paper);
        // Insert relation of user and paper
        submissionUserMergeMapper.insert(SubmissionUserMerge);
        // Insert review task overall
        reviewTaskOverallMapper.insert(reviewTask);

        return new ReviewTaskOverallBo(user, request.getOrgId(), paper, reviewTask);
    }

    @Override
    public List<ReviewTaskOverallBo> getSubmissionList(SubmissionListRequest request) {
        String orgId = request.getOrgId();

        ReviewTaskOverallExample example = new ReviewTaskOverallExample();
        example.createCriteria().andOrgIdEqualTo(orgId);
        List<ReviewTaskOverall> reviewTaskOveralls = reviewTaskOverallMapper.selectByExample(example);

        List<ReviewTaskOverallBo> reviewTaskOverallBoList = new ArrayList<>();

        for (ReviewTaskOverall task : reviewTaskOveralls) {
            SubmissionBase paper = submissionBaseMapper.selectByPrimaryKey(task.getSubmissionId());
            SubmissionUserMergeExample mergeExample = new SubmissionUserMergeExample();
            mergeExample.createCriteria().andSubmissionIdEqualTo(paper.getId());
            List<SubmissionUserMerge> mergeList = submissionUserMergeMapper.selectByExample(mergeExample);
            // Because the relationship between paper_id and user_id is one to one currently,
            // we simply pick up index:0 for the merge record
            UserBase user = userService.getUserById(mergeList.get(0).getUserId());
            reviewTaskOverallBoList.add(new ReviewTaskOverallBo(user, request.getOrgId(), paper, task));
        }

        return reviewTaskOverallBoList;
    }
}
