package com.team2.leopold.repository;

import com.team2.leopold.entity.One2One;
import org.springframework.data.jpa.repository.JpaRepository;

public interface One2OneRepository extends JpaRepository<One2One, Integer> {
    //Page<One2One> insertPosts(One2One one2One);
    //코드에 문제가 있어서 주석처리 했습니다.
    //일단 Page<One2One>은 정체를 알 수 없는 데이터 타입이구요,
    //데이터를 삽입하는 함수는 Repository에서 기본적으로 제공해주므로 이 메소드를 정의할 필요가 없습니다.
    //One2OneService 파일에서 save 메소드를 이용해보세요. (UserService 참고)
}