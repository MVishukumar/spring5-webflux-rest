package guru.springframework.spring5webfluxrest.repository;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vishu on 02/10/21
 */
@Repository
public interface VendorRepository extends ReactiveMongoRepository<Vendor, String> {
}
