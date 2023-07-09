package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 각 테스트메소드가 실행된 후에 실행되는 메소드 callback메소드 같은 형태
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("testName1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testName2");
        repository.save(member2);

        Member result1 = repository.findByName("testName1").get();
        Member result2 = repository.findByName("testName2").get();

        assertThat(member1).isEqualTo(result1);
        assertThat(member2).isEqualTo(result2);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("testName1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("testName2");
        repository.save(member2);

        List<Member> expect = new ArrayList<>();
        expect.add(member1);
        expect.add(member2);

        List<Member> result = repository.findAll();
        assertThat(expect).isEqualTo(result);
        assertThat(result.size()).isEqualTo(2);

    }
}
