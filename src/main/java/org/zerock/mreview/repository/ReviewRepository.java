package org.zerock.mreview.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Member;
import org.zerock.mreview.entity.Movie;
import org.zerock.mreview.entity.Review;

import javax.persistence.Entity;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    //review를 불러오고 member를 불러오게 한다.
    @EntityGraph(attributePaths = {"member"}, type = EntityGraph.EntityGraphType.FETCH)
    List<Review> findByMovie(Movie movie);

    //비효율을 줄이기 위해
    @Modifying
    @Query("delete from Review mr where mr.member = :member ")
    void deleteByMember(Member member);
}
