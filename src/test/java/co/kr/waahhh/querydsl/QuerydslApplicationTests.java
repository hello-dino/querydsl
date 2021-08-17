package co.kr.waahhh.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Dino dino = new Dino();
		em.persist(dino);

		JPAQueryFactory query = new JPAQueryFactory(em);
		QDino qDino = QDino.dino;

		Dino result = query
				.selectFrom(qDino)
				.fetchOne();

		assertThat(result).isEqualTo(dino);
		assertThat(result.getId()).isEqualTo(dino.getId());
	}

}
