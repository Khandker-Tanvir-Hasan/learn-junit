package com.baeldung.lju.service;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.service.impl.DefaultCampaignService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class CampaignServiceUnitTest {



    @Mock
    private CampaignRepository campaignRepository;

    @InjectMocks
    private DefaultCampaignService service;


    @BeforeEach
    void setUp() {
        this.campaignRepository = Mockito.mock(CampaignRepository.class);
        this.service = new DefaultCampaignService(campaignRepository);
    }

    @Test
    public void givenCampaignId_whenClosingACampaign_thenReturnExpectedResult() {

//        given

        Campaign campaign = new Campaign("test-code", "test-name", "test-description");

        /**
         * this findById is being called inside the closeCampaign method and this is returning the campaign object
         * this is the place where we will return our mock campaign
        * */
        Mockito.when(campaignRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(campaign));

        /**
         * this is where we will trigger the actual method that we will be testing
         */

//      when
        Optional<Campaign> optionalCampaign = service.closeCampaign(Mockito.anyLong());

//        then
        assertTrue(optionalCampaign.isPresent());

    }

    @Test
    void givenWrongCampaignId_whenClosingACampaign_thenReturnEmptyOptional() {
        Mockito.when(campaignRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.empty());

        assertTrue(service.closeCampaign(Mockito.anyLong()).isEmpty());

    }
}
