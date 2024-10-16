package com.sparta.trelloproject.domain.member.repository;

import com.sparta.trelloproject.domain.member.entity.Member;
import com.sparta.trelloproject.domain.member.enums.Assign;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByUserId(Long userId);

    boolean existsByAssign(Assign assign);

    Optional<Member> findByUserId(Long userId);
}
