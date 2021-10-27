package com.ags.poc.ingestion.repository;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampaignDataRepository extends JpaRepository<CampaignDataInfo, Long> {

	List<CampaignDataInfo> findByNbr(String sourceAccountNbr);
	List<CampaignDataInfo> findBySourceAccountNbrMd5(String sourceAccountNbrMd5);
}
