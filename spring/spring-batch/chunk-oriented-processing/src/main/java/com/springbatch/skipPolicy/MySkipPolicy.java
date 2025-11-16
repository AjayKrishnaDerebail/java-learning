package com.springbatch.skipPolicy;

import lombok.NonNull;
import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.validator.ValidationException;

public class MySkipPolicy implements SkipPolicy {

  @Override
  public boolean shouldSkip(@NonNull Throwable t, long skipCount) throws SkipLimitExceededException {
    if(t instanceof FlatFileParseException || t instanceof ValidationException || skipCount < 4){
      return true;
    }
    return false;
  }
}
