package com.ags.poc.ingestion.chroniclemap;

import java.io.Serializable;

public class CampaignDataInfo implements Serializable {

    private int priority;
    private int maxVisitCount;
    private int visitedCount;
    private String nbr;
    private String campaignType;
    private String message1;
    private String message2;
    private String message3;
    private String message4;

    public String getNbr() {
        return nbr;
    }

    public void setNbr(String nbr) {
        this.nbr = nbr;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(int visitedCount) {
        this.visitedCount = visitedCount;
    }

    public String getCampaignType() {
        return campaignType;
    }

    public void setCampaignType(String campaignType) {
        this.campaignType = campaignType;
    }

    public String getMessage1() {
        return message1;
    }

    public void setMessage1(String message1) {
        this.message1 = message1;
    }

    public String getMessage2() {
        return message2;
    }

    public void setMessage2(String message2) {
        this.message2 = message2;
    }

    public String getMessage3() {
        return message3;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    public String getMessage4() {
        return message4;
    }

    public void setMessage4(String message4) {
        this.message4 = message4;
    }

    public int getMaxVisitCount() {
        return maxVisitCount;
    }

    public void setMaxVisitCount(int maxVisitCount) {
        this.maxVisitCount = maxVisitCount;
    }

    @Override
    public String toString() {
        return "CampaignDataInfo [campaignType=" + campaignType + ", priority=" + priority + ", nbr=" + nbr
                + ", maxVisitCount=" + maxVisitCount + ", visitedCount=" + visitedCount + ", message1=" + message1
                + ", message2=" + message2 + ", message3=" + message3 + ", message4=" + message4 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((campaignType == null) ? 0 : campaignType.hashCode());
        result = prime * result + priority;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CampaignDataInfo other = (CampaignDataInfo) obj;
        if (campaignType == null) {
            if (other.campaignType != null)
                return false;
        } else if (!campaignType.equals(other.campaignType))
            return false;
        if (priority != other.priority)
            return false;
        return true;
    }

    public int compareTo(CampaignDataInfo b) {
        return new Integer(this.getPriority()).compareTo(new Integer(b.getPriority()));
    }

    public void incrementVisitedCount() {
        this.visitedCount++;
    }

}
