package guru.springframework.spring5webfluxrest.controller;

import guru.springframework.spring5webfluxrest.domain.Category;
import guru.springframework.spring5webfluxrest.domain.Vendor;
import guru.springframework.spring5webfluxrest.repository.CategoryRepository;
import guru.springframework.spring5webfluxrest.repository.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

/**
 * Created by vishu on 02/10/21
 */
public class VendorControllerTest {

    WebTestClient webTestClient;
    VendorRepository vendorRepository;
    VendorController vendorController;

    @Before
    public void setUp() throws Exception {
        vendorRepository = Mockito.mock(VendorRepository.class);
        vendorController = Mockito.mock(VendorController.class);
        webTestClient = WebTestClient.bindToController(vendorController).build();
    }

    @Test
    public void listAll() {
        BDDMockito.given(vendorRepository.findAll())
                .willReturn(Flux.just(Vendor.builder().name("Vendor1").build(),
                        Vendor.builder().name("Vendor2").build()).checkpoint());

        webTestClient.get()
                .uri("/api/v1/vendors")
                .exchange()
                .expectBodyList(Vendor.class)
                .hasSize(2);
    }

    @Test
    public void findById() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().name("Vendor1").build()));

        webTestClient.get()
                .uri("/api/v1/vendors/anystring")
                .exchange()
                .expectBody(Category.class);
    }

    @Test
    public void createVendor() {
        BDDMockito.given(vendorRepository.saveAll(any(Publisher.class)))
                .willReturn(Flux.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().build());

        webTestClient.post()
                .uri("/api/v1/vendors")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isCreated();
    }

    @Test
    public void updateCategory() {
        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().build());

        webTestClient.put()
                .uri("/api/v1/vendors/anyid")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();
    }


    @Test
    public void patchCategory() {
        BDDMockito.given(vendorRepository.findById(anyString()))
                .willReturn(Mono.just(Vendor.builder().build()));

        BDDMockito.given(vendorRepository.save(any(Vendor.class)))
                .willReturn(Mono.just(Vendor.builder().build()));

        Mono<Vendor> vendorMono = Mono.just(Vendor.builder().name("asdf").build());

        webTestClient.patch()
                .uri("/api/v1/vendors/anyid")
                .body(vendorMono, Vendor.class)
                .exchange()
                .expectStatus()
                .isOk();

        BDDMockito.verify(vendorRepository).save(any());
    }
}