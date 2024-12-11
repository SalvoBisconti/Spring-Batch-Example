package com.advancia.stage.jobs;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.stereotype.Component;

import com.advancia.stage.model.Message;

@Component
public class MyFileWriter {

  @Autowired
  @Bean
  @StepScope
  public FlatFileItemWriter<Message> myFlatFileItemWriter(Resource myFileResource) {
    return new FlatFileItemWriterBuilder<Message>()
      .name("myFileWriter")
      .resource((WritableResource) myFileResource)
      .formatted()
      .format("%s;%s;%s;%s;%s")
      .names( new String[] {
        "id",
        "recipientId",
        "subject",
        "content",
        "status",
      })
      .build();
  }

}