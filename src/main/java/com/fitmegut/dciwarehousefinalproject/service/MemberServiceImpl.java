package com.fitmegut.dciwarehousefinalproject.service;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import com.fitmegut.dciwarehousefinalproject.model.Role;
import com.fitmegut.dciwarehousefinalproject.repository.MemberRepository;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberServiceInterface{

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Member save(MemberRegistrationDto memberDto) {

        Member member = new Member(memberDto.getFirstName(), memberDto.getLastName(),
                memberDto.getNickname(), memberDto.getBirthdate(), memberDto.getGender(), memberDto.getEmail(),
                memberDto.getPhoneNumber(), memberDto.getCountry(), memberDto.getCity(), memberDto.getAddress(),
                memberDto.getUserType(), bCryptPasswordEncoder.encode(memberDto.getPassword()), Arrays.asList(new Role("ROLE_MEMBER")));

        return memberRepository.save(member);
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Member member = memberRepository.findByEmail(username);

        if(member == null){
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new User(member.getEmail(), member.getPassword(), mapRolesToAuthorities(member.getRoles()));
    }
}
