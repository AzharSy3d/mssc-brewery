package com.azharuworld.msscbrewery.services;

import com.azharuworld.msscbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {
    BeerDto getBeerById(UUID beerId);
}
