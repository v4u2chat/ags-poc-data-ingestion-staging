package com.ags.poc.ingestion.entities;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "campaigndata")
public class CampaignDataInfo implements Serializable {

	private static final long serialVersionUID = 8776055445546382163L;
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "ID")
	private String id;

	@Column(name = "PRIORITY")
	private int priority;

	@Column(name = "MAX_DISPLAY_COUNT")
	private int maxVisitCount;

	@Column(name = "VISITED_COUNT")
	private int visitedCount;

	@Column(name = "SOURCE_ACCOUNT_NBR")
	private String nbr;

	@Column(name = "CAMPAIGN_TYPE")
	private String campaignType;

	@Column(name = "MESSAGE1")
	private String message1;

	@Column(name = "MESSAGE2")
	private String message2;

	@Column(name = "MESSAGE3")
	private String message3;

	@Column(name = "MESSAGE4")
	private String message4;

	@Column(name = "SOURCE_ACCOUNT_NBR_MD5")
	private String sourceAccountNbrMd5;

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getId() {
		return this.id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public String getSourceAccountNbrMd5() {
		return this.sourceAccountNbrMd5;
	}

	public void setSourceAccountNbrMd5(final String sourceAccountNbrMd5) {
		this.sourceAccountNbrMd5 = sourceAccountNbrMd5;
	}

	public String getNbr() {
		return this.nbr;
	}

	public void setNbr(final String nbr) {
		this.nbr = nbr;
	}

	public int getPriority() {
		return this.priority;
	}

	public void setPriority(final int priority) {
		this.priority = priority;
	}

	public int getVisitedCount() {
		return this.visitedCount;
	}

	public void setVisitedCount(final int visitedCount) {
		this.visitedCount = visitedCount;
	}

	public String getCampaignType() {
		return this.campaignType;
	}

	public void setCampaignType(final String campaignType) {
		this.campaignType = campaignType;
	}

	public String getMessage1() {
		return this.message1;
	}

	public void setMessage1(final String message1) {
		this.message1 = message1;
	}

	public String getMessage2() {
		return this.message2;
	}

	public void setMessage2(final String message2) {
		this.message2 = message2;
	}

	public String getMessage3() {
		return this.message3;
	}

	public void setMessage3(final String message3) {
		this.message3 = message3;
	}

	public String getMessage4() {
		return this.message4;
	}

	public void setMessage4(final String message4) {
		this.message4 = message4;
	}

	public int getMaxVisitCount() {
		return this.maxVisitCount;
	}

	public void setMaxVisitCount(final int maxVisitCount) {
		this.maxVisitCount = maxVisitCount;
	}

	@Override
	public String toString() {
		return "CampaignDataInfo [campaignType=" + this.campaignType + ", priority=" + this.priority + ", nbr=" + this.nbr
				+ ", maxVisitCount=" + this.maxVisitCount + ", visitedCount=" + this.visitedCount + ", message1=" + this.message1
				+ ", message2=" + this.message2 + ", message3=" + this.message3 + ", message4=" + this.message4 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.campaignType == null) ? 0 : this.campaignType.hashCode());
		result = prime * result + this.priority;
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final CampaignDataInfo other = (CampaignDataInfo) obj;
		if (this.campaignType == null) {
			if (other.campaignType != null) {
				return false;
			}
		} else if (!this.campaignType.equals(other.campaignType)) {
			return false;
		}
		if (this.priority != other.priority) {
			return false;
		}
		return true;
	}

	public int compareTo(final CampaignDataInfo b) {
		return new Integer(this.getPriority()).compareTo(new Integer(b.getPriority()));
	}

	public void incrementVisitedCount() {
		this.visitedCount++;
	}

}
