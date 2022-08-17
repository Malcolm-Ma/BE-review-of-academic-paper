package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.ReviewCreateRequest;
import com.apex.app.controller.vo.SubmissionListRequest;
import com.apex.app.domain.bo.ReviewTaskOverallBo;
import com.apex.app.domain.model.*;
import com.apex.app.domain.type.ReviewStatusEnum;
import com.apex.app.mapper.PaperBaseMapper;
import com.apex.app.mapper.PaperUserMergeMapper;
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
    PaperBaseMapper paperBaseMapper;

    @Autowired
    PaperUserMergeMapper paperUserMergeMapper;

    @Autowired
    ReviewTaskOverallMapper reviewTaskOverallMapper;

    @Autowired
    UserAuthService userService;

    @Override
    public ReviewTaskOverallBo createReviewTask(ReviewCreateRequest request) {
        // Prepare to Insert paper base into db
        PaperBase paper = new PaperBase();
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
        PaperUserMerge paperUserMerge = new PaperUserMerge();
        paperUserMerge.setPaperId(paper.getId());
        paperUserMerge.setUserId(user.getId());

        // Create review task overall
        ReviewTaskOverall reviewTask = new ReviewTaskOverall();
        reviewTask.setOrgId(request.getOrgId());
        reviewTask.setPaperId(paper.getId());
        reviewTask.setDeadline(request.getDeadline());
        reviewTask.setCreatedTime(new Date());
        reviewTask.setStatus(ReviewStatusEnum.PREPARING.getValue());
        reviewTask.setId(UUID.randomUUID().toString());

        // Insert data into db
        // Insert paper base
        paperBaseMapper.insert(paper);
        // Insert relation of user and paper
        paperUserMergeMapper.insert(paperUserMerge);
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
            PaperBase paper = paperBaseMapper.selectByPrimaryKey(task.getPaperId());
            PaperUserMergeExample mergeExample = new PaperUserMergeExample();
            mergeExample.createCriteria().andPaperIdEqualTo(paper.getId());
            List<PaperUserMerge> mergeList = paperUserMergeMapper.selectByExample(mergeExample);
            // Because the relationship between paper_id and user_id is one to one currently,
            // we simply pick up index:0 for the merge record
            UserBase user = userService.getUserById(mergeList.get(0).getUserId());
            reviewTaskOverallBoList.add(new ReviewTaskOverallBo(user, request.getOrgId(), paper, task));
        }

        return reviewTaskOverallBoList;
    }
}