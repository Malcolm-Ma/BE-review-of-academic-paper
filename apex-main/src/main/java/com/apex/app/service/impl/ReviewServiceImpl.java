package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.ReviewCreateRequest;
import com.apex.app.controller.vo.SetBiddingRequest;
import com.apex.app.controller.vo.SubmissionListRequest;
import com.apex.app.domain.bo.ReviewTaskOverallBo;
import com.apex.app.domain.model.*;
import com.apex.app.domain.type.ReviewStatusEnum;
import com.apex.app.mapper.BiddingPreferenceMapper;
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
    BiddingPreferenceMapper biddingPreferenceMapper;

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

        return new ReviewTaskOverallBo(user, request.getOrgId(), paper, reviewTask, null);
    }

    @Override
    public List<ReviewTaskOverallBo> getSubmissionList(SubmissionListRequest request) {
        String orgId = request.getOrgId();

        ReviewTaskOverallExample reviewTaskOverallExample = new ReviewTaskOverallExample();
        reviewTaskOverallExample.createCriteria().andOrgIdEqualTo(orgId);
        List<ReviewTaskOverall> reviewTaskOveralls = reviewTaskOverallMapper.selectByExample(reviewTaskOverallExample);

        // Getting bidding preference
        BiddingPreferenceExample biddingPreferenceExample = new BiddingPreferenceExample();
        biddingPreferenceExample
                .createCriteria()
                .andOrgIdEqualTo(orgId)
                .andUserIdEqualTo(userService.getCurrentUser().getId());
        List<BiddingPreference> biddingPreferenceList = biddingPreferenceMapper.selectByExample(biddingPreferenceExample);

        List<ReviewTaskOverallBo> reviewTaskOverallBoList = new ArrayList<>();
        for (ReviewTaskOverall task : reviewTaskOveralls) {
            SubmissionBase paper = submissionBaseMapper.selectByPrimaryKey(task.getSubmissionId());
            BiddingPreference curPref = biddingPreferenceList.stream()
                    .filter(u -> u.getSubmissionId().equals(task.getSubmissionId()))
                    .findAny().orElse(null);
            SubmissionUserMergeExample mergeExample = new SubmissionUserMergeExample();
            mergeExample.createCriteria().andSubmissionIdEqualTo(paper.getId());
            List<SubmissionUserMerge> mergeList = submissionUserMergeMapper.selectByExample(mergeExample);
            // Because the relationship between paper_id and user_id is one to one currently,
            // we simply pick up index:0 for the merge record
            UserBase user = userService.getUserById(mergeList.get(0).getUserId());
            reviewTaskOverallBoList.add(new ReviewTaskOverallBo(user, request.getOrgId(), paper, task, curPref));
        }

        return reviewTaskOverallBoList;
    }

    @Override
    public boolean setBiddingPref(SetBiddingRequest request) {
        SubmissionBase submissionBase = submissionBaseMapper.selectByPrimaryKey(request.getSubmissionId());
        if (submissionBase == null) {
            Asserts.fail("Invalid submission_id");
            return false;
        }
        if (!submissionBase.getOrgId().equals(request.getOrgId())) {
            Asserts.fail("Invalid org_id");
            return false;
        }
        // Check whether the record is existed
        BiddingPreferenceExample example = new BiddingPreferenceExample();
        example.createCriteria()
                .andOrgIdEqualTo(request.getOrgId())
                .andUserIdEqualTo(userService.getCurrentUser().getId())
                .andSubmissionIdEqualTo(request.getSubmissionId());
        List<BiddingPreference> searchList = biddingPreferenceMapper.selectByExample(example);
        BiddingPreference biddingPreference = new BiddingPreference();
        if (searchList.size() > 0) {
            // update
            biddingPreference.setPreference(request.getBiddingPref().getValue());
            biddingPreferenceMapper.updateByExampleSelective(biddingPreference, example);
        } else {
            // create
            BeanUtil.copyProperties(request, biddingPreference);
            biddingPreference.setPreference(request.getBiddingPref().getValue());
            biddingPreferenceMapper.insert(biddingPreference);
        }
        return true;
    }
}
