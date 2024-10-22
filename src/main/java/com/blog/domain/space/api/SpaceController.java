package com.blog.domain.space.api;

import com.blog.domain.space.constant.SpaceResponseMessage;
import com.blog.domain.space.dto.*;
import com.blog.domain.space.service.SpaceService;
import com.blog.global.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/space")
@RestController
public class SpaceController {
    private final SpaceService spaceService;

    @PostMapping("")
    public ResponseEntity<SpaceResDto> addSpace(@RequestBody SpaceReqDto spaceReqDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spaceService.addSpace(spaceReqDto));
    }

    @PutMapping("")
    public ResponseEntity<SpaceResDto> updateSpace(@RequestBody UpdateSpaceReqDto updateSpaceReqDto){
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.updateSpace(updateSpaceReqDto));
    }

    @GetMapping("/{spaceId}")
    public ResponseEntity<SpaceResDto> findOneSpace(@PathVariable Long spaceId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.findOneSpace(spaceId));
    }

    @DeleteMapping("/{spaceId}")
    public ResponseEntity<MessageDto> deleteSpace(@PathVariable Long spaceId){
        spaceService.deleteSpace(spaceId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(SpaceResponseMessage.DELETE_SPACE.getMessage()
                ));
    }

    @PostMapping("/member")
    public ResponseEntity<SpaceMembersResDto> addMember(@RequestBody SpaceMembersReqDto spaceMembersReqDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(spaceService.addMember(spaceMembersReqDto));
    }

    //참여하고 있는 멤버 조회
    @GetMapping("/member/{spaceId}")
    public ResponseEntity<SpaceMembersResDto> findAllMember(@PathVariable Long spaceId){
        return ResponseEntity.status(HttpStatus.OK)
                .body(spaceService.findMembers(spaceId));
    }

    @DeleteMapping("/member")
    public ResponseEntity<MessageDto> deleteMember(@RequestBody SpaceMembersReqDto spaceMembersReqDto){
        spaceService.deleteMember(spaceMembersReqDto);
        return ResponseEntity.status(HttpStatus.OK)
                .body(MessageDto.msg(SpaceResponseMessage.DELETE_MEMBER.getMessage()
                ));
    }
}
