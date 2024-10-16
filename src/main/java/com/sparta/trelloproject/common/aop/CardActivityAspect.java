package com.sparta.trelloproject.common.aop;

import com.sparta.trelloproject.domain.auth.entity.AuthUser;
import com.sparta.trelloproject.domain.card.entity.Card;
import com.sparta.trelloproject.domain.card.entity.CardActivity;
import com.sparta.trelloproject.domain.card.repository.CardActivityRepository;
import com.sparta.trelloproject.domain.card.service.CardActivityService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class CardActivityAspect {

    private final HttpServletRequest request;
    private final CardActivityService cardActivityService;
    private final CardActivityRepository cardActivityRepository;

    @After("@annotation(CreateActivity)")
    public void createCardActivity(JoinPoint joinPoint) {
        AuthUser authUser = (AuthUser) request.getAttribute("authUser");

        String userId = String.valueOf(authUser.getUserId());
        String requestUrl = request.getRequestURI();
        String activity = "유저ID " + userId + "이 ";

        if(requestUrl.contains("create")){
            activity += "카드를 생성했습니다.";
        }

        if (requestUrl.contains("update")){
            activity += "카드를 수정했습니다.";
        }

        Card card = (Card) joinPoint.getArgs()[0];

        cardActivityService.createActivity(activity, card);

    }
}
