package com.springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

/**
 * ItemProcessListener implementation that monitors item processing operations.
 * Logs before processing, after successful processing, and on processing errors.
 *
 * @param <T> the type of item being processed (input)
 * @param <S> the type of item after processing (output)
 */
@Slf4j
@Component
public class ItemProcessListenerImpl<T, S> implements ItemProcessListener<T, S> {

  /**
   * Called before an item is processed.
   *
   * @param item the item to be processed
   */
  @Override
  public void beforeProcess(T item) {
    log.debug("Starting to process item: {}", item);
  }

  /**
   * Called after an item has been successfully processed.
   *
   * @param item the input item
   * @param result the result of processing (null if item was filtered out)
   */
  @Override
  public void afterProcess(T item, S result) {
    if (result == null) {
      log.debug("Item filtered out during processing: {}", item);
    } else {
      log.debug("Successfully processed item: {} -> {}", item, result);
    }
  }

  /**
   * Called when an error occurs during item processing.
   *
   * @param item the item that failed to process
   * @param e the exception that occurred
   */
  @Override
  public void onProcessError(T item, Exception e) {
    log.error("Error occurred while processing item: {}", item, e);
  }
}
