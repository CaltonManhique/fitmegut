package com.fitmegut.dciwarehousefinalproject.service.impl;

import com.fitmegut.dciwarehousefinalproject.model.Member;
import com.fitmegut.dciwarehousefinalproject.model.Role;
import com.fitmegut.dciwarehousefinalproject.repository.MemberRepository;
import com.fitmegut.dciwarehousefinalproject.service.interfaces.MemberServiceInterface;
import com.fitmegut.dciwarehousefinalproject.web.dto.MemberRegistrationDto;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberServiceInterface {

    private static final int REGISTRATION = 1;
    private static final int RESET_PASSWORD = 2;

    private MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JavaMailSender mailSender;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JavaMailSender mailSender) {
        this.memberRepository = memberRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.mailSender = mailSender;
    }

    @Override
    public void save(MemberRegistrationDto memberDto, String siteURL) {

        String randomCode = RandomStringUtils.randomAlphanumeric(64);
        memberDto.setVerificationCode(randomCode);
        memberDto.setEnabled(false);

        Member member = new Member(memberDto.getFirstName(), memberDto.getLastName(),
                memberDto.getNickname(), memberDto.getBirthdate(), memberDto.getGender(), memberDto.getEmail(),
                memberDto.getPhoneNumber(), memberDto.getCountry(), memberDto.getCity(), memberDto.getAddress(),
                memberDto.getUserType(), bCryptPasswordEncoder.encode(memberDto.getPassword()), memberDto.getVerificationCode(),
                memberDto.isEnabled(), Arrays.asList(new Role("ROLE_MEMBER")));

        memberRepository.save(member);

        try {
            sendVerificationEmail(memberDto, siteURL, REGISTRATION);
        } catch (MessagingException | UnsupportedEncodingException e) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        }

    }

    private void sendVerificationEmail(MemberRegistrationDto memberDto, String siteURL, int actionType)
            throws MessagingException, UnsupportedEncodingException {

        String toAddress = memberDto.getEmail();
        String fromAddress = "fitmegut@gmail.com";
        String senderName = "fitmegut - clothing donation and exchange";
        String subject = "Please verify your [[action]]";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your [[action]]:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "fitmegut - clothing donation and exchange.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);

        if (actionType == REGISTRATION) {
            subject = subject.replace("[[action]]", "registration");
            content = content.replace("[[action]]", "registration");

        } else if (actionType == RESET_PASSWORD) {
            subject = subject.replace("[[action]]", "email to reset password");
            content = content.replace("[[action]]", "email to reset password");
        }
        helper.setSubject(subject);

        content = content.replace("[[name]]", memberDto.getFirstName() + " " + memberDto.getLastName());
        String verifyURL = siteURL + "/registration/verify?code=" + memberDto.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, AuthenticationException {

        Member member = memberRepository.findByEmail(username);

        if (member == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        } else if (!member.isEnabled()) {
            throw new DisabledException("User not verified, please verify your email before login.");
        }
        return new User(member.getEmail(), member.getPassword(), mapRolesToAuthorities(member.getRoles()));
    }

    @Override
    public boolean verify(String verificationCode) {
        Member member = memberRepository.findByVerificationCode(verificationCode);

        if (member == null || member.isEnabled()) {
            return false;
        } else {
            member.setVerificationCode(null);
            member.setEnabled(true);
            memberRepository.save(member);

            return true;
        }
    }

    @Override
    public boolean sendPasswordResetEmail(String email, String siteURL) {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException("Invalid username.");
        } else {
            MemberRegistrationDto memberDto = new MemberRegistrationDto(member.getId(), member.getFirstName(), member.getLastName(),
                    member.getNickname(), member.getBirthdate(), member.getGender(), member.getEmail(),
                    member.getPhoneNumber(), member.getCountry(), member.getCity(), member.getAddress(),
                    member.getUserType(), member.getPassword(), RandomStringUtils.randomAlphanumeric(64),
                    member.isEnabled());

            try {
                sendVerificationEmail(memberDto, siteURL, RESET_PASSWORD);
            } catch (MessagingException | UnsupportedEncodingException e) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            }
        }

        return true;
    }


}
