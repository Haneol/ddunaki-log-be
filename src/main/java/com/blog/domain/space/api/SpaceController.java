package com.blog.domain.space.api;

import com.blog.domain.space.dto.SpaceReqDto;
import com.blog.domain.space.dto.SpaceResDto;
import com.blog.domain.space.service.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
