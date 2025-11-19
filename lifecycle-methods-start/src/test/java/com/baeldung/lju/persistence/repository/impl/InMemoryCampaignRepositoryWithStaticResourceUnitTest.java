package com.baeldung.lju.persistence.repository.impl;

import com.baeldung.lju.domain.model.Campaign;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static com.baeldung.lju.InMemoryCampaignRepositoryUnitTest.logger;
public class InMemoryCampaignRepositoryWithStaticResourceUnitTest {

    static InMemoryCampaignRepository staticCampaignRepository;

    @BeforeAll
    static void setupStaticDataSource() {
        Campaign existingCampaign = new Campaign("C-1-CODE", "Campaign 1", "Campaign 1 Description");
        existingCampaign.setId(1L);
        staticCampaignRepository = new InMemoryCampaignRepository(new HashSet<>(Arrays.asList(existingCampaign)));
        logger.info("STATIC @BeforeAll STATIC Initialized Data Source");
        logger.info("Repository reference id: {}", System.identityHashCode(staticCampaignRepository));
        logger.info("Data Source has {} campaigns", staticCampaignRepository.findAll().size());
    }

    @AfterAll
    static void staticCleanup() {
        logger.info("STATIC @AfterAll cleanup");
        logger.info("Repository reference id: {}", System.identityHashCode(staticCampaignRepository));
        logger.info("Data Source has {} campaigns", staticCampaignRepository.findAll().size());
    }

    @Test
    void givenStaticDatasource_whenFindById_thenCampaignRetrieved() {
        // when
        Optional<Campaign> retrievedCampaign = staticCampaignRepository.findById(1L);

        // then
        Assertions.assertEquals("C-1-CODE", retrievedCampaign.get().getCode());
    }
}
