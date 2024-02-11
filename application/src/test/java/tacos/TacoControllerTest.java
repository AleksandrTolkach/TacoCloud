package tacos;

import static reactor.core.publisher.Mono.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import tacos.Ingredient.Type;
import tacos.data.TacoRepository;
import tacos.web.api.TacoController;

@ExtendWith({MockitoExtension.class, SpringExtension.class})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TacoControllerTest {

  @Test
  void shouldReturnRecentTacos() {
    List<Taco> tacos = LongStream.range(1, 16)
        .mapToObj(this::testTaco)
        .collect(Collectors.toList());

    Flux<Taco> tacoFlux = Flux.fromIterable(tacos);

    TacoRepository tacoRepository = Mockito.mock(TacoRepository.class);

    when(tacoRepository.findAll())
        .thenReturn(tacoFlux);

    WebTestClient webTestClient = WebTestClient.bindToController(new TacoController(tacoRepository))
        .build();

    webTestClient.get().uri("/api/tacos?recent")
        .exchange()
        .expectStatus().isOk();
  }

  private Taco testTaco(Long number) {
    return Taco.builder()
        .id(Optional.ofNullable(number).orElse(1L))
        .name("Taco " + number)
        .ingredients(List.of(new Ingredient("INGA", "Ingredient A", Type.WRAP),
            new Ingredient("INGB", "Ingredient B", Type.PROTEIN)))
        .build();
  }
}
