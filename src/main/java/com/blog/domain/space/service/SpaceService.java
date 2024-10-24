package com.blog.domain.space.service;

import com.blog.domain.space.dto.*;

import java.util.List;

public interface SpaceService {
    SpaceResDto addSpace(SpaceReqDto spaceReqDto);

    SpaceResDto updateSpace(Long spaceId, UpdateSpaceReqDto updateSpaceReqDto);

    SpaceNameResDto updateSpaceName(Long spaceId, SpaceNameReqDto spaceNameReqDto);

    SpaceDateResDto updateSpaceDate(Long spaceId, SpaceDateReqDto spaceDateReqDto);

    SpaceCodeResDto updateSpaceCode(Long spaceId, SpaceCodeReqDto spaceCodeReqDto);

    SpaceDescriptionResDto updateSpaceDescription(Long spaceId, SpaceDescriptionReqDto spaceDescriptionReqDto);

    SpaceMaxMembersResDto updateMaxMembers(Long spaceId, SpaceMaxMembersReqDto spaceMaxMembersReqDto);

    List<SpaceResDto> findAllSpaces(int nationCode, int cityCode);

    SpaceResDto findOneSpace(Long spaceId);

    void deleteSpace(Long spaceId);

    SpaceMembersResDto addMembers(AddMemberReqDto addMemberReqDto);

    SpaceMembersResDto deleteMember(DeleteMemberReqDto deleteMemberReqDto);

    SpaceMembersResDto findMembers(Long spaceId);
}
