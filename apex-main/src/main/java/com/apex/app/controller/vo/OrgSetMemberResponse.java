package com.apex.app.controller.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * Request of setting members into org
 *
 * @author Mingze Ma
 */
@Data
public class OrgSetMemberResponse {

    private List<String> successList;

    private Map<String, String> failureList;

}
