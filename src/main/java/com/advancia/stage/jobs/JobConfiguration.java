package com.advancia.stage.jobs;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.repeat.CompletionPolicy;
import org.springframework.batch.repeat.policy.CompositeCompletionPolicy;
import org.springframework.batch.repeat.policy.SimpleCompletionPolicy;
import org.springframework.batch.repeat.policy.TimeoutTerminationPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.advancia.stage.configuration.DbRowVerificationSkipper;
import com.advancia.stage.listners.DbReaderListener;
import com.advancia.stage.model.Message;

@Configuration
public class JobConfiguration {
	
	  @Autowired
	  private DataSource batchDataSource;
	  @Autowired
	  private JdbcCursorItemReader<Message> messageItemReader;
	  @Autowired
	  private DbReaderListener DbReaderListener;
	  @Autowired
	  private ItemWriter myFlatFileItemWriter;
	  
	  @Bean
	  public Step step(JobRepository jobRepository) {
	    StepBuilder stepBuilderOne = new StepBuilder("step1", jobRepository);
	    
	  return stepBuilderOne.chunk(completionPolicy(), batchTransactionManager(batchDataSource))
	      .reader(messageItemReader)
	      .writer(myFlatFileItemWriter)
	      .faultTolerant()
	      .skipPolicy(new DbRowVerificationSkipper())
	      .listener(DbReaderListener)
	      .build();
	  }
	  
	  @Bean
	  public Job job(JobRepository jobRepository) {

		  return new JobBuilder("job", jobRepository).incrementer(new RunIdIncrementer()).start(step(jobRepository)).build();
	  }
	  
	  @Bean
	  public CompletionPolicy completionPolicy() {
		  CompositeCompletionPolicy policy = new CompositeCompletionPolicy();
		  policy.setPolicies(new CompletionPolicy[] {
			new TimeoutTerminationPolicy(1000), new SimpleCompletionPolicy(10)
		  });
		  return policy;		  
	  }
	  
	  @Bean 
	  public DataSourceTransactionManager batchTransactionManager(DataSource dataSource) {
		  return new DataSourceTransactionManager(dataSource);
	  }

}
