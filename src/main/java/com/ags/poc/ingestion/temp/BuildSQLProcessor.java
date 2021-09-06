package com.ags.poc.ingestion.staging;

import java.util.Map;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

@Component
public class BuildSQLProcessor implements Processor{

    //Region,Country,Item Type,Sales Channel,Order Priority,Order Date,Order ID,Ship Date,Units Sold,Unit Price,Unit Cost,Total Revenue,Total Cost,Total Profit

    private static final String INSERT_STMT = "INSERT INTO `SalesRecord` (`region`, `country`, `itemType`, `salesChannel`, `orderPriority`, `orderDate`, `orderld`, `shipDate`, `unitSold`, `unitPrice`, `unitCost`, `totalRevenue`, `totalCost`, `totalProfit`) VALUES (";
    private static final String CLOSE_PARANTHESIS = ")";
    private static final String TOTAL_PROFIT = "Total Profit";
    private static final String TOTAL_COST = "Total Cost";
    private static final String TOTAL_REVENUE = "Total evenue";
    private static final String UNIT_COST = "Unit Cost";
    private static final String UNIT_PRICE = "Unit Price";
    private static final String UNIT_SOLD = "Unit Sold";
    private static final String COMMA = ",";
    private static final String SHIP_DATE = "Ship Date";
    private static final String ORDERLD = "Order ID";
    private static final String ORDER_DATE = "Order Date";
    private static final String ORDER_PRIORITY = "Order Priority";
    private static final String SALES_CHANNEL = "Sales Channel";
    private static final String ITEM_TYPE = "Item Type";
    private static final String CLOSE_QUOTE_COMMA = "',";
    private static final String COUNTRY = "Country";
    private static final String REGION = "Region";
    private static final String SINGLE_QUOTE = "'";

    @Override
    public void process(Exchange exchange) throws Exception {

        Map<String,String> payload = exchange.getIn().getBody(Map.class);

        StringBuilder query= new StringBuilder(INSERT_STMT);

        if(payload!=null){
            query.append(SINGLE_QUOTE).append(payload.get(REGION)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(COUNTRY)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(ITEM_TYPE)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(SALES_CHANNEL)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(ORDER_PRIORITY)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(ORDER_DATE)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(ORDERLD)).append(CLOSE_QUOTE_COMMA);
            query.append(SINGLE_QUOTE).append(payload.get(SHIP_DATE)).append(CLOSE_QUOTE_COMMA);

            query.append(payload.get(UNIT_SOLD)).append(COMMA);
            query.append(payload.get(UNIT_PRICE)).append(COMMA);
            query.append(payload.get(UNIT_COST)).append(COMMA);
            query.append(payload.get(TOTAL_REVENUE)).append(COMMA);
            query.append(payload.get(TOTAL_COST)).append(COMMA);
            query.append(payload.get(TOTAL_PROFIT));
           
        }
        query.append(CLOSE_PARANTHESIS);
        exchange.getIn().setBody(query);
        payload = null;
        query = null;
    }
    
}
