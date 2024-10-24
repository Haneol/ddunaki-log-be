package com.blog.domain.space.dto;

import com.blog.domain.user.domain.User;
import com.blog.domain.user.dto.MemberDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SpaceMembersReqDto {
    private Long spaceId;
    private Long updateMemberId;
//    private List<MemberDto> members;
}
