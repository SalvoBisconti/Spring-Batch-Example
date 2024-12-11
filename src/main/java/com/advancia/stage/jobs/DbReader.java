package com.advancia.stage.jobs;

import javax.sql.DataSource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;

import com.advancia.stage.model.Message;
import com.advancia.stage.model.mappers.MessageRowMapper;

@Component
public class DbReader {
	
	  @Autowired
	  @Bean
	  @StepScope
	  public JdbcCursorItemReader<Message> messageItemReader( DataSource dataSource) {
	    DriverManagerDataSource myDbDataSource = new DriverManagerDataSource();
	    myDbDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
	    myDbDataSource.setUrl("jdbc:mysql://localhost:3306/SpringEx");
	    myDbDataSource.setUsername("root");
	    myDbDataSource.setPassword("bisconti98");

	    return new JdbcCursorItemReaderBuilder<Message>()
	      .name("messageItemReader")
	      .dataSource(myDbDataSource)
	      .sql("SELECT * FROM Message")
	      .rowMapper(new MessageRowMapper())
	      .build();
	  }

}
