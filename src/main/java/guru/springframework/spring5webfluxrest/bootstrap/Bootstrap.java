package guru.springframework.spring5webfluxrest.bootstrap;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by vishu on 02/10/21
 */

@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final VendorRepository vendorRepository;

    public Bootstrap(CategoryRepository categoryRepository, VendorRepository vendorRepository) {
        this.categoryRepository = categoryRepository;
        this.vendorRepository = vendorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if(categoryRepository.count().block() == 0) {
            System.out.println("#### Loading data at bootstrap ####");

            categoryRepository.save(
                    Category.builder().description("Fruits").build()
            ).block();
            categoryRepository.save(
                    Category.builder().description("Dried").build()
            ).block();
            categoryRepository.save(
                    Category.builder().description("Fresh").build()
            ).block();
            categoryRepository.save(
                    Category.builder().description("Exotic").build()
            ).block();
            categoryRepository.save(
                    Category.builder().description("Nuts").build()
            ).block();
            System.out.println("### Total loaded categories: " + categoryRepository.count().block() + " ###");

            vendorRepository.save(
                    Vendor.builder().name("Western Tasty Fruits Ltd.").build()
            ).block();
            vendorRepository.save(
                    Vendor.builder().name("Exotic Fruits Company").build()
            ).block();
            vendorRepository.save(
                    Vendor.builder().name("Home Fruits").build()
            ).block();
            vendorRepository.save(
                    Vendor.builder().name("Fun Fresh Fruits Ltd.").build()
            ).block();
            vendorRepository.save(
                    Vendor.builder().name("Nuts for Nuts Company").build()
            ).block();
            System.out.println("### Total loaded vendors: " + vendorRepository.count().block() + " ###");

        }
    }
}
