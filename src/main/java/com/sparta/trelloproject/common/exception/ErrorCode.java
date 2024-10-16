package com.sparta.trelloproject.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    /*
    ex)
    예외명(대문자+'_') + (HttpStatus.오류코드, "메세지")
    STORE_FORBIDDEN(HttpStatus.FORBIDDEN,"사장님 권한을 가진 사용자만 가게를 생성할 수 있습니다."),
    STORE_BAD_REQUEST(HttpStatus.BAD_REQUEST,"사장님은 최대 3개의 가게까지만 운영할 수 있습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 가게를 찾을 수 없습니다."),


    throw new CustomException(ErrorCode.STORE_FORBIDDEN);
     */


    // Token ErrorCode
    TOKEN_NOT_FOUND(HttpStatus.NOT_FOUND, "조회 실패 : %s"),




    // User ErrorCode
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "조회 실패 : %s"),
    VALIDATION_ERROR(HttpStatus.BAD_REQUEST, "유효성 검사 실패 : %s"),
    SIGNUP_ERROR(HttpStatus.BAD_REQUEST, "회원가입 실패 : %s"),
    SIGNIN_ERROR(HttpStatus.BAD_REQUEST, "로그인 실패 : %s"),

    // Role ErrorCode
    PERMISSION_ERROR(HttpStatus.FORBIDDEN, "권한이 없습니다."),



    // Member ErrorCode
    ROLE_ERROR(HttpStatus.FORBIDDEN, "권한 없음 : %s"),
    ROLE_NOT_FOUND(HttpStatus.NOT_FOUND, "조회 실패 : %s"),
    SELF_REQUEST_FORBIDDEN(HttpStatus.FORBIDDEN, "본인 요청 불가 : %s"),
    USER_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "중복 요청 불가 : %s"),


    // Workspace ErrorCode
    WORKSPACE_NOT_FOUND(HttpStatus.NOT_FOUND, "워크스페이스를 찾을 수 없습니다."),




    // Board Errorcode
    BOARD_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 보드를 찾을 수 없습니다"),
    BOARD_FORBIDDEN(HttpStatus.FORBIDDEN, "보드를 수정/삭제할 권한이 없습니다."),
    BOARD_BAD_REQUEST(HttpStatus.BAD_REQUEST, "보드 요청에 잘못된 정보가 포함되어 있습니다."),
    BOARD_CREATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "보드 생성에 실패했습니다."),
    BOARD_UPDATE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "보드 수정에 실패했습니다."),
    BOARD_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "보드 삭제에 실패했습니다."),
    BOARD_TITLE_EMPTY(HttpStatus.BAD_REQUEST, "보드 제목은 필수입니다."),
    BOARD_READ_ONLY_MEMBER(HttpStatus.FORBIDDEN, "읽기 전용 권한입니다"),

    // List Errorcode




    // Card Errorcode
    CARD_NOT_FORBIDDEN(HttpStatus.FORBIDDEN, "카드 생성/수정 권한이 없습니다."),



    // Comment Errorcode
    Comment_FORBIDDEN(HttpStatus.FORBIDDEN, "ㅁㄴㅇㄹㄴㅁㅇㄹ"),


    // File Errorcode




    // Alarm Errorcode




    // Search Errorcode




    // 아래 코드 위에 ErrorCode 작성
    NOT_FOUND(HttpStatus.NOT_FOUND, "찾지못했습니다.");


    private final HttpStatus status;
    private final String message;

    ErrorCode(HttpStatus httpStatus, String message){
        this.status = httpStatus;
        this.message = message;
    }

    public String customMessage(String detail) {
        return String.format(message, detail); // 동적으로 메시지 포맷팅
    }
}
