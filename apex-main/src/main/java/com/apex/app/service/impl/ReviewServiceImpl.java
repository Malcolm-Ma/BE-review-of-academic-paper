package com.apex.app.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import com.apex.app.common.exception.Asserts;
import com.apex.app.controller.vo.*;
import com.apex.app.dao.ReviewDao;
import com.apex.app.dao.UserDao;
import com.apex.app.domain.bo.*;
import com.apex.app.domain.model.*;
import com.apex.app.domain.type.BiddingPrefEnum;
import com.apex.app.domain.type.ReviewStatusEnum;
import com.apex.app.mapper.*;
import com.apex.app.service.OrgService;
import com.apex.app.service.ReviewService;
import com.apex.app.service.UserAuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

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
    ReviewEvaluationMapper reviewEvaluationMapper;

    @Autowired
    ReviewDao reviewDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserAuthService userService;

    @Autowired
    OrgService orgService;

    @Value("${bidding-system.url}")
    String biddingSystemUrl;

    @Override
    public ReviewTaskOverallBo createReviewTask(ReviewCreateRequest request) {
        // Prepare to Insert paper base into db
        SubmissionBase paper = new SubmissionBase();
        BeanUtil.copyProperties(request, paper);
        paper.setId(UUID.randomUUID().toString());

        UserBase user;
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
//        reviewTask.setDeadline(request.getDeadline());
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

        BiddingPreference hostPref = new BiddingPreference();
        hostPref.setPreference(BiddingPrefEnum.CONFLICT.getValue());
        hostPref.setUserId(user.getId());
        hostPref.setOrgId(request.getOrgId());
        hostPref.setSubmissionId(paper.getId());
        biddingPreferenceMapper.insert(hostPref);

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
            BiddingPreference cur = searchList.get(0);
            if (request.getBiddingPref().getValue() == cur.getPreference()) {
                Asserts.fail("The bidding preference has been set to " + request.getBiddingPref().getDesc());
                return false;
            }
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

    @Override
    public BiddingPrefSummaryResponse getBiddingPrefSummary(String userId, String orgId) {
        BiddingPreferenceExample example = new BiddingPreferenceExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andOrgIdEqualTo(orgId);
        List<BiddingPreference> result = biddingPreferenceMapper.selectByExample(example);
        BiddingPrefSummaryResponse response = new BiddingPrefSummaryResponse();
        for (BiddingPreference preference : result) {
            byte pref = preference.getPreference();
            if (pref == BiddingPrefEnum.YES.getValue()) {
                response.setInterest(response.getInterest() + 1);
            }
            if (pref == BiddingPrefEnum.MAYBE.getValue()) {
                response.setMaybe(response.getMaybe() + 1);
            }
            if (pref == BiddingPrefEnum.NO.getValue()) {
                response.setNo(response.getNo() + 1);
            }
            if (pref == BiddingPrefEnum.CONFLICT.getValue()) {
                response.setConflict(response.getConflict() + 1);
            }
        }

        Integer total = reviewDao.getSubmissionCount(orgId);
        if (total == null) {
            Asserts.fail("Invalid org_id");
            return null;
        }
        response.setTotal(total);
        response.setUnsigned();

        return response;
    }

    @Override
    public AllocateBiddingResponse allocateBidding(AllocateBiddingRequest request) {
        String orgId = request.getOrgId();
        BiddingPreferenceExample preferenceExample = new BiddingPreferenceExample();
        preferenceExample.createCriteria().andOrgIdEqualTo(orgId);
        List<BiddingPreference> biddingPreferenceList = biddingPreferenceMapper.selectByExample(preferenceExample);
        if (biddingPreferenceList.size() == 0) {
            Asserts.fail("Invalid org_id, please try again");
            return null;
        }
        List<String> submissionIdList = reviewDao.getSubmissionIdList(orgId);
        Map<String, UserBase> userMap = userDao.getUserMapByOrgId(orgId);
        List<String> userIdList = userMap.keySet().stream().toList();
        Map<String, List<List<String>>> userPrefMap = new HashMap<>();
        for (String userId : userIdList) {
            List<String> prefYes = new ArrayList<>();
            List<String> prefNo = new ArrayList<>();
            // initially treat maybe as a whole submission
            List<String> prefMaybe = new ArrayList<>(submissionIdList);
            List<BiddingPreference> userBiddingList = biddingPreferenceList.stream()
                    .filter(u -> u.getUserId().equals(userId)).toList();
            for (BiddingPreference bidding : userBiddingList) {
                if (bidding.getPreference() == BiddingPrefEnum.YES.getValue()) {
                    prefYes.add(bidding.getSubmissionId());
                    prefMaybe.remove(bidding.getSubmissionId());
                }
                if (bidding.getPreference() == BiddingPrefEnum.CONFLICT.getValue()) {
                    prefMaybe.remove(bidding.getSubmissionId());
                }
                if (bidding.getPreference() == BiddingPrefEnum.NO.getValue()) {
                    prefNo.add(bidding.getSubmissionId());
                    prefMaybe.remove(bidding.getSubmissionId());
                }
            }
            userPrefMap.put(userId, new ArrayList<>(Arrays.asList(prefYes, prefMaybe, prefNo)));
        }

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("review_demand", request.getReviewDemand());
        requestBody.put("min_task_per_user", request.getMinTaskPerUser());
        requestBody.put("project_id_list", submissionIdList);
        requestBody.put("user_id_list", userIdList);
        requestBody.put("user_pref", userPrefMap);
        JSONObject requestJson = new JSONObject(requestBody);
        try {
            String json = requestJson.toString();
            String resultStr = HttpRequest.post(biddingSystemUrl).body(json).execute().body();
            JSONObject responseJson = new JSONObject(resultStr);
            if (responseJson.getInt("code") != 200) {
                Asserts.fail("Fail to make paper bidding, please try again");
                return null;
            }
            JSONArray resultRecord = responseJson.getByPath("data.result_record", JSONArray.class);
            List<PaperAllocation> resultRecordList = new ArrayList<>();
            for (int i = 0; i < resultRecord.size(); i++) {
                JSONObject object = resultRecord.getJSONObject(i);
                String resSubmissionId = object.get("review_id", String.class);
                String resUserId = object.get("user_id", String.class);
                PaperAllocation allocation = new PaperAllocation();
                allocation.setOrgId(orgId);
                allocation.setSubmissionId(resSubmissionId);
                allocation.setUserId(resUserId);
                resultRecordList.add(allocation);
            }
            reviewDao.deleteAllocationByOrgId(orgId);
            reviewDao.insertPaperAllocation(resultRecordList);
            AllocateBiddingResponse res = new AllocateBiddingResponse();
            BeanUtil.copyProperties(request, res);
            res.setMinTaskPerUser(responseJson.getInt("data.summary.min_task_per_user"));
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, PaperAllocationMapBo> getAllocationResult(String orgId) {
        Map<String, PaperAllocationMapBo> res = reviewDao.getAllocationResult(orgId);
        if (res.size() == 0) {
            return null;
        }
        return res;
    }

    @Override
    public List<ReviewTaskInfoBo> getReviewTaskByUser(GetReviewTaskRequest request) {
        String userId = request.getUserId();
        if (request.getUserId() == null) {
            userId = userService.getCurrentUser().getId();
        }
        List<ReviewTaskInfoBo> res = reviewDao.getReviewTaskByUserId(request.getOrgId(), userId, request.getReviewId());
        return res;
    }

    @Override
    public Boolean createNewReview(NewReviewRequest request) {
        String userId = userService.getCurrentUser().getId();
        ReviewTaskOverall task = reviewTaskOverallMapper.selectByPrimaryKey(request.getReviewId());
        if (task == null) {
            Asserts.fail("Invalid review id");
            return false;
        }
        String curOrgId = task.getOrgId();
        if (!orgService.checkUserBelonging(curOrgId, userId)) {
            Asserts.fail("Current user is not belong to the organization");
            return false;
        }
        if (request.getOverallEvaluation() < -3 && request.getOverallEvaluation() > 3) {
            Asserts.fail("Invalid evaluation score");
            return false;
        }
        if (request.getConfidence() < 1 && request.getConfidence() > 5) {
            Asserts.fail("Invalid confidence score");
            return false;
        }
        ReviewEvaluationExample example = new ReviewEvaluationExample();
        example.setOrderByClause("`review_index` DESC");
        example.createCriteria()
                .andReviewIdEqualTo(request.getReviewId())
                .andTypeEqualTo((byte) 1)
                .andUserIdEqualTo(userId);
        EvaluationCountsBo evaluationCounts = reviewDao.getEvaluationCounts(request.getReviewId());
        List<ReviewEvaluation> records = reviewEvaluationMapper.selectByExample(example);
        ReviewEvaluation newReview = new ReviewEvaluation();
        BeanUtil.copyProperties(request, newReview);
        newReview.setUserId(userId);
        newReview.setType((byte) 1);
        newReview.setReviewDate(new Date());
        newReview.setReviewIndex((byte) (evaluationCounts.getReviewCount() + 1));
        newReview.setActiveStatus((byte) 1);
        if (records.size() > 0) {
            List<ReviewEvaluation> updateItems = records.stream()
                    .filter(u -> u.getActiveStatus().equals((byte) 1))
                    .toList();
            updateItems.forEach(i -> {
                i.setActiveStatus((byte) 0);
                reviewEvaluationMapper.updateByPrimaryKey(i);
            });
        }
        reviewEvaluationMapper.insert(newReview);
        return true;
    }

    @Override
    public ReviewSummaryBo getReviewSummary(GetReviewTaskRequest request) {
        String userId = userService.getCurrentUser().getId();
        ReviewTaskOverall task = reviewTaskOverallMapper.selectByPrimaryKey(request.getReviewId());
        if (task == null) {
            Asserts.fail("Invalid review id");
            return null;
        }
        String curOrgId = task.getOrgId();
        if (!orgService.checkUserBelonging(curOrgId, userId)) {
            Asserts.fail("Current user is not belong to the organization");
            return null;
        }
        ReviewSummaryBo res = reviewDao.getReviewSummary(request.getReviewId());
        return res;
    }

    @Override
    public List<UserDisplayBo> getConflictInterestUsers(String submissionId) {
        return reviewDao.getConflictInterestUsers(submissionId);
    }

    @Override
    public Boolean createComment(CreateCommentRequest request) {
        String userId = userService.getCurrentUser().getId();
        ReviewTaskOverall task = reviewTaskOverallMapper.selectByPrimaryKey(request.getReviewId());
        if (task == null) {
            Asserts.fail("Invalid review id");
            return false;
        }
        String curOrgId = task.getOrgId();
        if (!orgService.checkUserBelonging(curOrgId, userId)) {
            Asserts.fail("Current user is not belong to the organization");
            return false;
        }
        EvaluationCountsBo evaluationCounts = reviewDao.getEvaluationCounts(request.getReviewId());
        ReviewEvaluation newReview = new ReviewEvaluation();
        newReview.setReviewId(request.getReviewId());
        newReview.setEvaluationContent(request.getComment());
        newReview.setUserId(userId);
        newReview.setReviewDate(new Date());
        newReview.setReviewIndex((byte) (evaluationCounts.getCommentCount() + 1));
        newReview.setActiveStatus((byte) 1);
        newReview.setType((byte) 0);
        reviewEvaluationMapper.insert(newReview);
        return true;
    }
}
