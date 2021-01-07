package org.dasd.security;

import lombok.Getter;
import lombok.Setter;
import org.dasd.domain.Member;
import org.dasd.domain.MemberRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DasdSecurityUser extends User {

    private static final String ROLE_PREFIX="ROLE_";

    private Member member;

    public DasdSecurityUser(Member member){
        super(member.getUid(),"{noop}"+member.getUpw(),makeGrantedAuthority(member.getRoles()));
        //passowrd encoder 적용 이후에는 noop 생략
        this.member=member;
    }

    private static List<GrantedAuthority> makeGrantedAuthority(List<MemberRole> roles) {
        List<GrantedAuthority>list=new ArrayList<>();

        roles.forEach(role->list.add(new SimpleGrantedAuthority(ROLE_PREFIX+role.getRoleName())));

        return list;
    }


}
