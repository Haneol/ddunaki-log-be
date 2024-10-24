package com.blog.domain.schedule.constant;

public enum ScheduleResponseMessage {
    ADD_SCHEDULE("일정 추가 완료했습니다."),
    UPDATE_SCHEDULE("일정 수정 완료했습니다."),
    DELETE_SCHEDULE("일정 삭제 완료했습니다."),
    FIND_ONE_SCHEDULE("해당 일정 조회 완료했습니다."),
    FIND_ALL_SCHEDULES("전체 일정 조회 완료했습니다.");


    private final String message;

    ScheduleResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
