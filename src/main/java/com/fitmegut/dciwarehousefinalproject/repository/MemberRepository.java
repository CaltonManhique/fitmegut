package com.fitmegut.dciwarehousefinalproject.repository;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Member findByEmail(String email);
}
