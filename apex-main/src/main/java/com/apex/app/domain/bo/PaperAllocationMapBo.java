package com.apex.app.domain.bo;

import com.apex.app.domain.model.UserBase;
import lombok.Data;

import java.util.List;

/**
 * @author Mingze Ma
 */
@Data
public class PaperAllocationMapBo {

    private String submissionId;

    private List<UserBase> userList;

}
