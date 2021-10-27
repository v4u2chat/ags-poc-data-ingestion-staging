package com.ags.poc.ingestion.batch;

import com.ags.poc.ingestion.entities.CampaignDataInfo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CampaignDataInfoRowMapper implements RowMapper<CampaignDataInfo> {

	@Override
	public CampaignDataInfo mapRow(final ResultSet rs, final int rowNum) throws SQLException {
		final CampaignDataInfo campaignDataInfo = new CampaignDataInfo();
		campaignDataInfo.setId(rs.getString("ID"));
		campaignDataInfo.setNbr(rs.getString("SOURCE_ACCOUNT_NBR"));
		campaignDataInfo.setCampaignType(rs.getString("PRODUCT_CODE"));
		campaignDataInfo.setMessage1(rs.getString("MESSAGE1"));
		campaignDataInfo.setMessage2(rs.getString("MESSAGE2"));
		campaignDataInfo.setMessage3(rs.getString("MESSAGE3"));
		campaignDataInfo.setMessage4(rs.getString("MESSAGE4"));
		campaignDataInfo.setPriority(rs.getInt("PRIORITY"));
		campaignDataInfo.setMaxVisitCount(rs.getInt("MAX_DISPLAY_COUNT"));
		campaignDataInfo.setVisitedCount(rs.getInt("VISITED_COUNT"));
		return campaignDataInfo;
	}
}
