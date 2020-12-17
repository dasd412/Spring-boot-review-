package org.dasd;

import java.util.Arrays;
import java.util.List;
import lombok.extern.java.Log;
import org.dasd.domain.Member;
import org.dasd.domain.Profile;
import org.dasd.persistence.MemberRepository;
import org.dasd.persistence.ProfileRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit//<-테스트 결과 커밋

public class ProfileTests {

    @Autowired
    MemberRepository memberRepo;

    @Autowired
    ProfileRepository profileRepo;

    @Test
    public void testInsertMember(){
        IntStream.range(1,101).forEach(
                i->{
                    Member member=new Member();
                    member.setUid("user"+i);
                    member.setUpw("pw"+i);
                    member.setUName("name"+i);

                    memberRepo.save(member);
                }

        );

    }

    @Test
    public void testInsertProfile(){

        Member member=new Member();
        member.setUid("user1");

        for (int i = 1; i <5 ; i++) {

            Profile profile1=new Profile();

            profile1.setFName("face"+i+".jpg");

            if(i==1){
                profile1.setCurrent(true);
            }

            profile1.setMember(member);
            profileRepo.save(profile1);
        }

    }

    @Test
    public void testFetchJoin1(){
        List<Object[]> result=memberRepo.getMemberWithProfileCount("user1");

        result.forEach(arr-> System.out.println(Arrays.toString(arr)));

    }


    @Test
    public void testFetchJoin2(){

        List<Object[]>result=memberRepo.getMemberWithCurrentProfile("user1");

        result.forEach(arr-> System.out.println(Arrays.toString(arr)));

    }

}

