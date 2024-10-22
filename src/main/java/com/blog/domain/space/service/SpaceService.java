package com.blog.domain.space.service;

import com.blog.domain.space.dto.*;

import java.util.List;

public interface SpaceService {
    SpaceResDto addSpace(SpaceReqDto spaceReqDto);

    SpaceResDto updateSpace(UpdateSpaceReqDto updateSpaceReqDto);

    SpaceNameResDto updateSpaceName(SpaceNameReqDto spaceNameReqDto);

    SpaceDateResDto updateSpaceDate(SpaceDateReqDto spaceDateReqDto);

    SpaceCodeResDto updateSpaceCode(SpaceCodeReqDto spaceCodeReqDto);

    SpaceDescriptionResDto updateSpaceDescription(SpaceDescriptionReqDto spaceDescriptionReqDto);

    SpaceMaxMembersResDto updateMaxMembers(SpaceMaxMembersReqDto spaceMaxMembersReqDto);

    List<SpaceResDto> findAllSpaces(int nationCode, int cityCode);

    SpaceResDto findOneSpace(Long spaceId);

    void deleteSpace(Long spaceId);

    SpaceMembersResDto addMember(SpaceMembersReqDto spaceMembersReqDto);

    SpaceMembersResDto deleteMember(SpaceMembersReqDto spaceMembersReqDto);

    SpaceMembersResDto findMembers(Long spaceId);
}
