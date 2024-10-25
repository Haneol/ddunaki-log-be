package com.blog.domain.space.api;

import com.blog.domain.space.constant.SpaceResponseMessage;
import com.blog.domain.space.dto.*;
import com.blog.domain.space.service.SpaceService;
import com.blog.domain.space.service.SpaceServiceImpl;
import com.blog.global.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/space")
@RestController
public class SpaceController {
    private final SpaceService spaceService;
    private static final Logger logger = LoggerFactory.getLogger(SpaceServiceImpl.class);
    @PostMapping("")
    public ResponseEntity<SpaceResDto> addSpace(@RequestBody SpaceReqDto spaceReqDto) {
        logger.info("addSpace 들어오니 ?: {}");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spaceService.addSpace(spaceReqDto));
    }

    @PutMapping("/{spaceId}")
    public ResponseEntity<SpaceResDto> updateSpace(@PathVariable Long spaceId, @RequestBody UpdateSpaceReqDto updateSpaceReqDto) {
        logger.info("addSpace 들어오니 ?: {}");
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.updateSpace(spaceId, updateSpaceReqDto));
    }

    @GetMapping("/{spaceId}")
    public ResponseEntity<SpaceResDto> findOneSpace(@PathVariable Long spaceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.findOneSpace(spaceId));
    }

    @DeleteMapping("/{spaceId}")
    public ResponseEntity<MessageDto> deleteSpace(@PathVariable Long spaceId) {
        spaceService.deleteSpace(spaceId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(SpaceResponseMessage.DELETE_SPACE.getMessage()
                ));
    }

    @PostMapping("/member")
    public ResponseEntity<SpaceMembersResDto> addMember(@RequestBody AddMemberReqDto addMemberReqDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spaceService.addMembers(addMemberReqDto));
    }

    //참여하고 있는 멤버 조회
    @GetMapping("/member/{spaceId}")
    public ResponseEntity<SpaceMembersResDto> findAllMember(@PathVariable Long spaceId) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.findMembers(spaceId));
    }

    @DeleteMapping("/member")
    public ResponseEntity<MessageDto> deleteMember(@RequestBody DeleteMemberReqDto deleteSpaceMemberReqDto) {
        spaceService.deleteMember(deleteSpaceMemberReqDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(SpaceResponseMessage.DELETE_MEMBER.getMessage()
                ));
    }
}
