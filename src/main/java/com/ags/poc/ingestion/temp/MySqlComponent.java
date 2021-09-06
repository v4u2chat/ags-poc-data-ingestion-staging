package com.ags.poc.ingestion.staging;

import javax.sql.DataSource;

import org.apache.camel.component.sql.SqlComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MySqlComponent extends SqlComponent{
    
    @Autowired
    private DataSource dataSource;

}
