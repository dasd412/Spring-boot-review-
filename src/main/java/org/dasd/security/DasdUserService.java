package org.dasd.security;

import lombok.extern.java.Log;
import org.dasd.persistence.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Log
public class DasdUserService implements UserDetailsService {//커스텀 UserDetailService

    @Autowired
    private MemberRepository repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        return repo.findById(username).filter(member->member!=null)
                .map(member->new DasdSecurityUser(member)).get();
    }
}
