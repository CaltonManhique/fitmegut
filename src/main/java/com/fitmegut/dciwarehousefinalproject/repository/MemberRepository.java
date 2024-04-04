package com.fitmegut.dciwarehousefinalproject.repository;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);

    @Query("SELECT m FROM Member m WHERE m.verificationCode = ?1")
    Member findByVerificationCode(String code);
}
