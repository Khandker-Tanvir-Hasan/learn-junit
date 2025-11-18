package com.baeldung.lju;

import com.baeldung.lju.domain.model.Campaign;
import com.baeldung.lju.persistence.repository.CampaignRepository;
import com.baeldung.lju.persistence.repository.impl.InMemoryCampaignRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.ILoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class InMemoryCampaignRepositoryUnitTest {

    @Test
    void givenEmptyDataSource_whenFindAllCampaigns_thenEmptyListRetrieved()
    {
//        given
        CampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());
//        when
        List<Campaign> campaigns = repository.findAll();
//        then
        Assertions.assertTrue(campaigns.isEmpty());
    }

    /**
        Happy Path Case
    **/
    @Test
    void givenExistingCampaign_whenFindById_thenCampaignRetrieved() {
        // given
        Campaign campaign = new Campaign("P-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        // when
        Optional<Campaign> retrievedCampaign = repository.findById(1L);

        // then
        Assertions.assertTrue(retrievedCampaign.isPresent());
        Assertions.assertEquals(campaign, retrievedCampaign.get());
    }

    /**
        Corner Case
    **/
    @Test
    void givenExistingCampaign_whenFindByNonExistingId_thenNoCampaignRetrieved() {
        //given
        Campaign campaign = new Campaign("P-1-CODE", "Campaign 1", "Campaign 1 Description");
        campaign.setId(1L);
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(Set.of(campaign));

        //when
        Optional<Campaign> retrievedCampaign = repository.findById(2L);

        //then
        Assertions.assertTrue(retrievedCampaign.isEmpty());

    }
    /**
        Side Effect
    */
    @Test
    void givenEmptyDataSource_whenSave_thenCampaignIsAssignedId() {
        // given
        InMemoryCampaignRepository repository = new InMemoryCampaignRepository(new HashSet<>());

        // when

        Campaign campaign= new Campaign("P-NEW-CODE", "New Campaign", "New Campaign Description");
        repository.save(campaign);
        Campaign campaign2= new Campaign("P-NEW-2", "New Campaign2", "New Campaign Description 2");
        repository.save(campaign2);

        // then
        System.out.println(campaign.getId());
        System.out.println(campaign2.getId());
        Assertions.assertTrue(campaign.getId() != null);
    }
}
