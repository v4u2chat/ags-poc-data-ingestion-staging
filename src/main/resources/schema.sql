CREATE TABLE `ags-data-poc`.`campaigndata` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `SOURCE_ACCOUNT_NBR` varchar(45) DEFAULT NULL,
  `PRODUCT_CODE` varchar(45) DEFAULT NULL,
  `MAX_DISPLAY_COUNT` int(11) DEFAULT 0,
  `VISITED_COUNT` int(11) DEFAULT 0,
  `UPLOAD_DATE` date DEFAULT NULL,
  `DELETE_DATE` date DEFAULT NULL,
  `CAMPAIGN_TYPE` varchar(45) DEFAULT NULL,
  `MESSAGE1` longtext,
  `MESSAGE2` longtext,
  `MESSAGE3` longtext,
  `MESSAGE4` longtext,
  `PRIORITY` int(11) DEFAULT NULL,
  `ACTION` varchar(45) DEFAULT NULL,
  `SOURCE_ACCOUNT_NBR_MD5` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idx_campaigndata_SOURCE_ACCOUNT_NBR` (`SOURCE_ACCOUNT_NBR`),
  KEY `idx_campaigndata_SOURCE_ACCOUNT_NBR_MD5` (`SOURCE_ACCOUNT_NBR_MD5`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;