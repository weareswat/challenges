package com.ix.challenge.topic;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryIT {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository repository;

    @Test
    public void whenFindAll_thenReturnCorrectOrder(){
        //given
        Post post1 = new Post(1, 6, 4);
        Post post2 = new Post(2, 600, 400);
        entityManager.persist(post1);
        entityManager.persist(post2);
        entityManager.flush();

        //when
        Iterable<Post> found = repository.findAll(Sort.by(Sort.Direction.DESC, "rating"));

        //then
        Assert.assertTrue(found.iterator().hasNext());
        Assert.assertTrue(found.iterator().next().getId() == 2);
    }

}
