package guru.springframework.spring5webfluxrest.repository;

import guru.springframework.spring5webfluxrest.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishu on 02/10/21
 */
@Repository
public interface CategoryRepository extends ReactiveMongoRepository<Category, String> {
}
