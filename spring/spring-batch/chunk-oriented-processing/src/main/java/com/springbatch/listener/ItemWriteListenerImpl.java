package com.springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;
import org.springframework.stereotype.Component;

/**
 * ItemWriteListener implementation that monitors item writing operations.
 * Logs before write attempts, after successful writes, and on write errors.
 *
 * @param <S> the type of item being written
 */
@Slf4j
@Component
public class ItemWriteListenerImpl<S> implements ItemWriteListener<S> {

  /**
   * Called before attempting to write a chunk of items.
   *
   * @param items the chunk of items to be written
   */
  @Override
  public void beforeWrite(Chunk<? extends S> items) {
    log.debug("Preparing to write {} items", items.size());
  }

  /**
   * Called after successfully writing a chunk of items.
   *
   * @param items the chunk of items that were written
   */
  @Override
  public void afterWrite(Chunk<? extends S> items) {
    log.debug("Successfully wrote {} items", items.size());
  }

  /**
   * Called when an error occurs during item writing.
   *
   * @param exception the exception that occurred
   * @param items the chunk of items that failed to write
   */
  @Override
  public void onWriteError(Exception exception, Chunk<? extends S> items) {
    log.error("Error occurred while writing {} items", items.size(), exception);
    items.forEach(item -> log.error("Failed to write item: {}", item));
  }
}
