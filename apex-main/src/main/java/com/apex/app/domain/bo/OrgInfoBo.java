package com.apex.app.domain.bo;

import com.apex.app.domain.model.OrgBase;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * Full Org information BO
 *
 * @author Mingze Ma
 */
@Data
public class OrgInfoBo extends OrgBase {

    @ApiModelProperty("Member in the org list")
    private List<OrgMemberBo> memberList;

    @ApiModelProperty("Org manager list")
    private List<OrgMemberBo> managerList;

    @ApiModelProperty("Deleted(disabled) member in the org list")
    private List<OrgMemberBo> disabledList;

    public OrgInfoBo() {
        super();
        memberList = new ArrayList<>();
        managerList = new ArrayList<>();
        disabledList = new ArrayList<>();
    }

    public void appendMembers(List<OrgMemberBo> allMemberList, Boolean blindMode) {
        for (OrgMemberBo member : allMemberList) {
            OrgMemberBo newMember = new OrgMemberBo();
            if (blindMode) {
                newMember.setId(member.getId());
                newMember.setMemberSince(member.getMemberSince());
                newMember.setType(member.getType());
                newMember.setEnableStatus(member.getEnableStatus());
            } else {
                newMember = member;
            }
            if (member.getType() >= 2) {
                managerList.add(newMember);
            }
            if (member.getType() == 1) {
                memberList.add(newMember);
            }
            if (member.getType() == 0) {
                disabledList.add(newMember);
            }
        }
    }

}
