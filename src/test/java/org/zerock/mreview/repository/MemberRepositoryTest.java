package org.zerock.mreview.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Commit;
import org.zerock.mreview.entity.Member;

import javax.transaction.Transactional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReviewRepository reviewRepository;
    @Test
    public void insertMember(){
        IntStream.rangeClosed(1,100).forEach(i->{
            Member member = Member.builder()
                    .email("r" + i + "@ds.com")
                    .pw("1111")
                    .nickname("reviewer" + i)
                    .build();
            memberRepository.save(member);
        });
    }

    //안에 지워야할 내용이 있기 때문에 어노테이션 두 개 더 붙여줌 지연방식으로 순서대로 가려고
    @Commit
    @Transactional
    @Test
    public void testDeleteMember(){
        Long mid = 1L; //Member의 mid

        Member member = Member.builder().mid(mid).build();

        //기존
        /*memberRepository.deleteById(mid);
        reviewRepository.deleteByMember(member);*/

        //**** 순서주의 **** 리뷰를 지우고, 회원을 지워야함
        reviewRepository.deleteByMember(member);
        memberRepository.deleteById(mid);
    }



}