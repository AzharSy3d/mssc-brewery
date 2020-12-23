package com.azharuworld.msscbrewery.web.controller;

import com.azharuworld.msscbrewery.services.BeerService;
import com.azharuworld.msscbrewery.web.model.BeerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BeerController.class)
public class BeerControllerTest {

    @MockBean
    BeerService beerService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    BeerDto validBeer;

    @Before
    public void setup(){
        validBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("Beer1").beerStyle("Pale Ace").upc(13123123L).build();
    }

    @Test
    public void getBeer() throws Exception {
        given(beerService.getBeerById(any(UUID.class))).willReturn(validBeer);

        mockMvc.perform(get("/api/v1/beer"+validBeer.getId().toString()).accept(APPLICATION_JSON)).
                andExpect(status().isOk()).
                andExpect((ResultMatcher) content().contentType(APPLICATION_JSON));

//                andExpect(jsonPath("$.id",is(validBeer.getId().toString()))).
//                andExpect(jsonPath("$.beerName",is("Beer1")));
    }

    @Test
    public void handlePost() throws  Exception{
        //given
        BeerDto beerDto = validBeer;
        beerDto.setId(null);
        BeerDto savedBeer = BeerDto.builder().id(UUID.randomUUID()).beerName("Saved beer").build();

        given(beerService.saveNewBeer(any())).willReturn(savedBeer);
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer").contentType(APPLICATION_JSON).content(beerDtoJson)).andExpect(status().isCreated());
    }
}
