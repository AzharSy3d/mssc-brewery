package com.azharuworld.msscbrewery.services.v2;

import com.azharuworld.msscbrewery.web.model.BeerDto;
import com.azharuworld.msscbrewery.web.model.v2.BeerDtoV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceV2Impl implements BeerServiceV2 {
    @Override
    public BeerDtoV2 getBeerById(UUID beerId) {
        return null;
    }

    @Override
    public BeerDtoV2 saveNewBeer(BeerDtoV2 beerDto) {
        return null;
    }

    @Override
    public void updateBeer(UUID beerId, BeerDto beerDto) {

    }

    @Override
    public void deleteBeerById(UUID beerId) {
       log.debug("Delete V2 Beer");
    }
}
