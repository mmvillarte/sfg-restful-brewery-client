package guru.springframework.sfgrestfulbreweryclient.client;

import guru.springframework.sfgrestfulbreweryclient.model.BeerDto;
import guru.springframework.sfgrestfulbreweryclient.model.BeerPagedList;
import guru.springframework.sfgrestfulbreweryclient.model.v2.BeerStyleEnum;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface BeerClient {
    // List Beers
    Mono<BeerPagedList> getBeerList(Integer pageNumber,
                                    Integer pageSize,
                                    String beerName,
                                    String beerStyle,
                                    Boolean showInventoryOnHand);

    // Create New Beer
    Mono<ResponseEntity> createBeer(String beerName,
                                    BeerStyleEnum beerStyle,
                                    String upc,
                                    Integer quantityOnHand,
                                    String price);

    // Get Beer by id
    Mono<BeerDto> getBeerById(String uuid,
                              Boolean showInventoryOnHand);

    // Update beer by id
    Mono<ResponseEntity> updateBeer(String beerId,
                                    String beerName,
                                    String beerStyle,
                                    String upc,
                                    Integer quantityOnHand,
                                    String price);

    // Delete Beer
    Mono<ResponseEntity> deleteBeer(String beerId);

    // Get Beer by UPC
    Mono<BeerDto> getBeerByUPC(String upc);
}
