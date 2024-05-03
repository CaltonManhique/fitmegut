package com.fitmegut.dciwarehousefinalproject.service.interfaces;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberServiceInterface  extends UserDetailsService {

    void save(MemberRegistrationDto memberDto, String siteURL);

    boolean verify(String code);

    boolean sendPasswordResetEmail(String email, String siteURL);

    MemberRegistrationDto findByEmail(String email);

    Member findMemberByEmail(String email);

}
