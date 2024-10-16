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

    // Member ErrorCode




    // Worksapce ErrorCode
    WORKSPACE_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 워크스페이스를 찾을 수 없습니다."),



    // Board Errorcode




    // List Errorcode




    // Card Errorcode
    CARD_NOT_FORBIDDEN(HttpStatus.FORBIDDEN, "카드 생성/수정 권한이 없습니다."),
    CARD_NOT_FOUND(HttpStatus.NOT_FOUND,"해당 카드를 찾을 수 없습니다"),



    // Comment Errorcode
    Comment_FORBIDDEN(HttpStatus.FORBIDDEN, "댓글 작성 권한이 없습니다."),
    Comment_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    Comment_AUTH_FORBIDDEN(HttpStatus.FORBIDDEN,"작성자가 아니므로 수정/삭제가 불가능합니다."),
    Comment_BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 형식의 텍스트/이모지입니다."),

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
