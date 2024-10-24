package com.blog.domain.space.service;

import com.blog.domain.space.dao.SpaceRepository;
import com.blog.domain.space.domain.Space;
import com.blog.domain.space.dto.*;
import com.blog.domain.space.exception.SpaceNotFoundException;
import com.blog.domain.space.exception.SpaceUserNotFoundException;
import com.blog.domain.user.domain.User;
import com.blog.domain.user.domain.UserRole;
import com.blog.domain.user.repository.UserRepository;
import com.blog.global.exception.ForbiddenException;
import com.blog.global.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.blog.domain.schedule.constant.ScheduleExceptionMessage.NO_PERMISSION;
import static com.blog.domain.space.constant.SpaceExceptionMessage.*;

@Service
@RequiredArgsConstructor
public class SpaceServiceImpl implements SpaceService {

    private final SpaceRepository spaceRepository;
    private final UserRepository userRepository;

    private final SecurityUtils securityUtils;

    private User getLoginUser() {
        String loginUserEmail = securityUtils.getLoginUserEmail();
        return userRepository.findByEmail(loginUserEmail)
                .orElseThrow(() -> new SpaceUserNotFoundException(SPACE_USER_NOT_FOUND.getMessage()));
    }

    private void checkMemberAuthority(Space space, User user) {
        //유저가 해당 스페이스의 멤버가 아니면 에러
        if (space.getMembers() == null || !space.getMembers().contains(user)) {
            throw new ForbiddenException(NO_PERMISSION.getMessage());
        }
    }

    private void checkLeaderAuthority(Space space, User user) {
        //유저가 방장이 아니면 에러
        if (!space.getLeader().equals(user)) {
            throw new ForbiddenException();
        }
    }

    private void checkAdminAuthority(User user) {
        if (!user.getRole().equals(UserRole.ADMIN)) {
            throw new ForbiddenException();
        }
    }

    @Override
    public SpaceResDto addSpace(SpaceReqDto spaceReqDto) {

        User leader = userRepository.findById(spaceReqDto.getLeaderId())
                .orElseThrow(() -> new SpaceUserNotFoundException(SPACE_USER_NOT_FOUND.getMessage()));

        List<User> members = new ArrayList<>();
        members.add(leader);

        // 최대 멤버 수 체크
        if (members.size() > spaceReqDto.getMaxMembers()) {
            throw new IllegalArgumentException(MEMBER_LIMIT_EXCEEDED.getMessage());
        }

        // 새로운 Space 생성
        Space space = Space.builder()
                .leader(leader)
                .nationCode(spaceReqDto.getNationCode())
                .cityCode(spaceReqDto.getCityCode())
                .spaceName(spaceReqDto.getSpaceName())
                .description(spaceReqDto.getDescription())
                .startDate(spaceReqDto.getStartDate())
                .endDate(spaceReqDto.getEndDate())
                .maxMembers(spaceReqDto.getMaxMembers())
                .members(members)
                .build();

        spaceRepository.save(space);

        return SpaceResDto.entityToDto(space);
    }

    @Override
    public SpaceResDto updateSpace(Long spaceId, UpdateSpaceReqDto updateSpaceReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.update(updateSpaceReqDto.getSpaceName(), updateSpaceReqDto.getStartDate(), updateSpaceReqDto.getEndDate(), updateSpaceReqDto.getDescription(),
                updateSpaceReqDto.getNationCode(), updateSpaceReqDto.getCityCode(), updateSpaceReqDto.getMaxMembers());

        spaceRepository.save(space);

        return SpaceResDto.entityToDto(space);
    }


    //방장만 수정 가능
    @Override
    public SpaceNameResDto updateSpaceName(Long spaceId, SpaceNameReqDto spaceNameReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceNameReqDto.getSpaceId())
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.updateSpaceName(spaceNameReqDto.getSpaceName());

        spaceRepository.save(space);

        return SpaceNameResDto.entityToDto(space);
    }


    @Override
    public SpaceDateResDto updateSpaceDate(Long spaceId, SpaceDateReqDto spaceDateReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.updateDate(spaceDateReqDto.getStartDate(), spaceDateReqDto.getEndDate());

        spaceRepository.save(space);

        return SpaceDateResDto.entityToDto(space);
    }

    @Override
    public SpaceCodeResDto updateSpaceCode(Long spaceId, SpaceCodeReqDto spaceCodeReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.updateCode(spaceCodeReqDto.getNationCode(), spaceCodeReqDto.getCityCode());

        spaceRepository.save(space);

        return SpaceCodeResDto.entityToDto(space);
    }

    @Override
    public SpaceDescriptionResDto updateSpaceDescription(Long spaceId, SpaceDescriptionReqDto spaceDescriptionReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.updatedescription(space.getDescription());

        spaceRepository.save(space);

        return SpaceDescriptionResDto.entityToDto(space);
    }

    @Override
    public SpaceMaxMembersResDto updateMaxMembers(Long spaceId, SpaceMaxMembersReqDto spaceMaxMembersReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        space.updateMaxMembers(space.getMaxMembers());

        spaceRepository.save(space);

        return SpaceMaxMembersResDto.entityToDto(space);
    }

    @Override
    public List<SpaceResDto> findAllSpaces(int nationCode, int cityCode) {
        return List.of();
    }

    @Override
    public SpaceResDto findOneSpace(Long spaceId) {
        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        return SpaceResDto.entityToDto(space);
    }

    @Override
    public void deleteSpace(Long spaceId) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        spaceRepository.delete(space);

    }

    //유저 추가
    //유저가 max인원보다 많으면 에러
    @Override
    public SpaceMembersResDto addMember(SpaceMembersReqDto spaceMembersReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceMembersReqDto.getSpaceId())
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkMemberAuthority(space, user);

        // 현재 멤버 수가 최대 멤버 수를 초과하지 않도록 체크
        if (space.getMembers().size() >= space.getMaxMembers()) {
            throw new IllegalArgumentException(MEMBER_LIMIT_EXCEEDED.getMessage());
        }

        // 추가하려는 유저 찾기
        User newMember = userRepository.findById(spaceMembersReqDto.getUpdateMemberId())
                .orElseThrow(() -> new SpaceUserNotFoundException(SPACE_USER_NOT_FOUND.getMessage()));

        // 이미 멤버로 추가된 유저인지 확인
        if (space.getMembers().contains(newMember)) {
            throw new IllegalArgumentException(MEMBER_ALREADY_EXISTS.getMessage());
        }

        space.addMember(newMember);

        spaceRepository.save(space);

        return SpaceMembersResDto.entityToDto(space);
    }

    @Override
    public SpaceMembersResDto deleteMember(SpaceMembersReqDto spaceMembersReqDto) {
        User user = getLoginUser();

        Space space = spaceRepository.findById(spaceMembersReqDto.getSpaceId())
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        checkLeaderAuthority(space, user);

        // 강퇴할 유저 찾기
        User outMember = userRepository.findById(spaceMembersReqDto.getUpdateMemberId())
                .orElseThrow(() -> new SpaceUserNotFoundException(SPACE_USER_NOT_FOUND.getMessage()));

        // 해당 유저가 현재 멤버 리스트에 있는지 확인
        if (!space.getMembers().contains(outMember)) {
            throw new IllegalArgumentException(MEMBER_NOT_FOUND.getMessage());
        }

        space.deleteMember(outMember);

        spaceRepository.save(space);

        return SpaceMembersResDto.entityToDto(space);
    }

    @Override
    public SpaceMembersResDto findMembers(Long spaceId) {

        Space space = spaceRepository.findById(spaceId)
                .orElseThrow(() -> new SpaceNotFoundException(SPACE_NOT_FOUND.getMessage()));

        return SpaceMembersResDto.entityToDto(space);
    }
}
