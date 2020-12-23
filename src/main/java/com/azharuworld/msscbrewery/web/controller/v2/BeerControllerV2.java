package com.azharuworld.msscbrewery.web.controller.v2;

import com.azharuworld.msscbrewery.services.BeerService;
import com.azharuworld.msscbrewery.services.v2.BeerServiceV2;
import com.azharuworld.msscbrewery.web.model.BeerDto;
import com.azharuworld.msscbrewery.web.model.v2.BeerDtoV2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v2/beer")
@RestController
public class BeerControllerV2 {

    private final BeerServiceV2 beerServiceV2;

    public BeerControllerV2(BeerServiceV2 beerServiceV2) {
        this.beerServiceV2 = beerServiceV2;
    }


    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDtoV2> getBeer(@PathVariable UUID beerId){
        return new ResponseEntity<BeerDtoV2>(beerServiceV2.getBeerById(beerId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BeerDto> handlePost(BeerDtoV2 beerDtoV2){
        BeerDtoV2 savedBeerDto = beerServiceV2.saveNewBeer(beerDtoV2);

        HttpHeaders headers = new HttpHeaders();
        //todo add url host name
        headers.add("location", "/api/v1/beer/"+savedBeerDto.getId().toString());

        return  new ResponseEntity<>(headers,HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public  ResponseEntity handleUpdate(@PathVariable UUID beerId, BeerDto beerDto){
        beerServiceV2.updateBeer(beerId,beerDto);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteBeer(@PathVariable UUID beerId)
    {
        beerServiceV2.deleteBeerById(beerId);
    }




}