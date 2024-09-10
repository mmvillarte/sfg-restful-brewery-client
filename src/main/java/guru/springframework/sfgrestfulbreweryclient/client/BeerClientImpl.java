package guru.springframework.sfgrestfulbreweryclient.client;

import guru.springframework.sfgrestfulbreweryclient.config.WebClientConfig;
import guru.springframework.sfgrestfulbreweryclient.model.BeerDto;
import guru.springframework.sfgrestfulbreweryclient.model.BeerPagedList;
import guru.springframework.sfgrestfulbreweryclient.model.v2.BeerStyleEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

// Interface implementation
@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {
    private final WebClient webClient;

    @Override
    public Mono<BeerPagedList> getBeerList(Integer pageNumber,
                                           Integer pageSize,
                                           String beerName,
                                           String beerStyle,
                                           Boolean showInventoryOnHand) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .queryParamIfPresent("pageNumber", Optional.of(Optional.ofNullable(pageNumber).orElse(1)))
                        .queryParamIfPresent("pageSize", Optional.of(Optional.ofNullable(pageSize).orElse(25)))
                        .queryParamIfPresent("beerName", Optional.of(Optional.ofNullable(beerName)))
                        .queryParamIfPresent("beerStyle", Optional.of(Optional.ofNullable(beerStyle)))
                        .queryParamIfPresent("showInventoryOnHand", Optional.of(Optional.ofNullable(showInventoryOnHand).orElse(false)))
                        .build())
                .retrieve()
                .bodyToMono(BeerPagedList.class);
    }

    @Override
    public Mono<ResponseEntity> createBeer(String beerName,
                                           BeerStyleEnum beerStyle,
                                           String upc,
                                           Integer quantityOnHand,
                                           String price) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .queryParam("beerName", Optional.of(Optional.ofNullable(beerName)))
                        .queryParam("beerStyle", Optional.of(Optional.ofNullable(beerStyle)))
                        .queryParam("upc", Optional.of(Optional.ofNullable(upc)))
                        .queryParamIfPresent("quantityOnHand", Optional.of(Optional.ofNullable(quantityOnHand)))
                        .queryParam("price", Optional.of(Optional.ofNullable(price)))
                        .build())
                .retrieve()
                .bodyToMono(ResponseEntity.class);
    }

    @Override
    public Mono<BeerDto> getBeerById(String uuid,
                                     Boolean showInventoryOnHand) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .queryParamIfPresent("showInventoryOnHand", Optional.of(Optional.ofNullable(showInventoryOnHand).orElse(false)))
                        .build(uuid))
                .retrieve()
                .bodyToMono(BeerDto.class);
    }

    @Override
    public Mono<ResponseEntity> updateBeer(String beerId,
                                           String beerName,
                                           String beerStyle,
                                           String upc,
                                           Integer quantityOnHand,
                                           String price) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .queryParam("beerId", Optional.of(Optional.ofNullable(beerId)))
                        .queryParam("beerName", Optional.of(Optional.ofNullable(beerName)))
                        .queryParam("beerStyle", Optional.of(Optional.ofNullable(beerStyle)))
                        .queryParam("upc", Optional.of(Optional.ofNullable(upc)))
                        .queryParamIfPresent("quantityOnHand", Optional.of(Optional.ofNullable(quantityOnHand)))
                        .queryParam("price", Optional.of(Optional.ofNullable(price)))
                        .build())
                .retrieve()
                .bodyToMono(ResponseEntity.class);
    }

    @Override
    public Mono<ResponseEntity> deleteBeer(String beerId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .queryParam("beerId", Optional.of(Optional.ofNullable(beerId)))
                        .build())
                .retrieve()
                .bodyToMono(ResponseEntity.class);
    }

    @Override
    public Mono<BeerDto> getBeerByUPC(String upc) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(WebClientConfig.LOCAL_PATH)
                        .build(upc))
                .retrieve()
                .bodyToMono(BeerDto.class);
    }
}
