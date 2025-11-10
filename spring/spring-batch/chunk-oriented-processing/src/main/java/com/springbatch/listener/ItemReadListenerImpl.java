package com.springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

/**
 * ItemReadListener implementation that monitors item reading operations.
 * Logs before read attempts, after successful reads, and on read errors.
 *
 * @param <T> the type of item being read
 */
@Slf4j
@Component
public class ItemReadListenerImpl<T> implements ItemReadListener<T> {

  /**
   * Called before attempting to read an item.
   */
  @Override
  public void beforeRead() {
    log.trace("Attempting to read next item...");
  }

  /**
   * Called after successfully reading an item.
   *
   * @param item the item that was read
   */
  @Override
  public void afterRead(T item) {
    log.debug("Successfully read item: {}", item);
  }

  /**
   * Called when an error occurs during item reading.
   *
   * @param ex the exception that occurred
   */
  @Override
  public void onReadError(Exception ex) {
    log.error("Error occurred while reading item", ex);
  }
}
