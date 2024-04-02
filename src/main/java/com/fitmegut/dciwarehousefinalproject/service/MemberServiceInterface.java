package com.fitmegut.dciwarehousefinalproject.service;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface MemberServiceInterface  extends UserDetailsService {

    Member save(MemberRegistrationDto memberRegistrationDto);

}
