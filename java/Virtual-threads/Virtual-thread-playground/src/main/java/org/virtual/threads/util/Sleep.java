package org.virtual.threads.util;

import java.time.Duration;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Sleep {

  public static void sleepThread(final Long timeInMilliSeconds){
    try {
      Thread.sleep(Duration.ofMillis(timeInMilliSeconds));
    } catch (final InterruptedException e) {
      throw new RuntimeException(e);
    }

  }

}