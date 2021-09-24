package org.zerock.mreview.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.zerock.mreview.entity.Movie;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    //n+1문제를 해결하기위해 mi에 적용된 max()제거
//    @Query("select m, mi, avg(coalesce(r.grade,0)), count(distinct r) from Movie m " +
//            "left outer join MovieImage mi on mi.movie = m "+
//            "left outer join Review r on r. movie = m group by m")
    //서브쿼리 사용하여 max적용 가능 -> 조금의 성능 저하
/*    @Query("select m, i, count(r) from Movie m left join MovieImage i " +
            "on m=i.movie and i.inum=(select max(i2.inum) from MovieImage i2 " +
            "where i2.movie = m ) " +
            "left outer join Review r on r.movie = m group by m")
    Page<Object[]> getListPage(Pageable pageable);*/

    //평균 평점과 리뷰개수
/*  @Query("select m, mi from Movie m left outer join MovieImage mi " +
             "on mi.movie = m where m.mno =:mno ")*/

    @Query("select m, mi, avg(coalesce(r.grade, 0)), count(r) " +
            "from Movie m left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m " +
            "where m.mno =:mno group by mi ")
    List<Object[]> getMovieWithAll(Long mno);

    @Query("select m, mi, avg(coalesce(r.grade,0)), count(r) from Movie m " +
            "left outer join MovieImage mi on mi.movie = m " +
            "left outer join Review r on r.movie = m group by m ")
    Page<Object[]> getListPage(Pageable pageable);
}

