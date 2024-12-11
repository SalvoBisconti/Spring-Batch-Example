package com.advancia.stage.listners;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.annotation.OnReadError;
import org.springframework.stereotype.Component;

@Component
public class DbReaderListener {
  private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  @OnReadError
  public void onReadError(Exception e) {
    log.error(e.getMessage());
  }

}