package com.advancia.stage.configuration;

import java.sql.SQLException;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class DbRowVerificationSkipper implements SkipPolicy {
	  @Override
	  public boolean shouldSkip(Throwable t, long skipCount) throws SkipLimitExceededException {
	    if(t instanceof SQLException) {
	      return false;
	    } else if(skipCount <= 10) {
	      return true;
	    } else {
	      return false;
	    }
	  }

	}
