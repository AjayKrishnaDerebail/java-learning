package org.virtual.threads.section1;

import java.util.concurrent.CountDownLatch;

@SuppressWarnings("unused")
public class Main {
  private static final int MAX_PLATFORM_THREADS = 10;
  private static final int MAX_VIRTUAL_THREADS = 100;

  static void main() throws InterruptedException {
    //platformBuilderAndDaemonThreadsWithCountdownLatch();
    virtualThreadsWithCountDownLatch();
  }

  public static void platformBuilderAndDaemonThreadsWithCountdownLatch()
      throws InterruptedException {

    var latch = new CountDownLatch(MAX_PLATFORM_THREADS);
    var builder = Thread.ofPlatform().daemon().name("daemon",10);
    for(var i=0; i< MAX_PLATFORM_THREADS; i++) {
      int j = i ;
      Thread thread = builder.unstarted(() -> {
        Task.ioIntensiveTask(j);
        latch.countDown();
      });
      thread.start();
    }
    latch.await();
  }

  /**
   * Virtual threads are daemon threads by default
   */
  public static void virtualThreadsWithCountDownLatch()
      throws InterruptedException {

    var latch = new CountDownLatch(MAX_VIRTUAL_THREADS);
    var builder = Thread.ofVirtual().name("virtual-",1);
    for(var i=0; i< MAX_VIRTUAL_THREADS; i++) {
      int j = i ;
      Thread thread = builder.unstarted(() -> {
        Task.ioIntensiveTask(j);
        latch.countDown();
      });
      thread.start();
    }
    latch.await();
  }

}