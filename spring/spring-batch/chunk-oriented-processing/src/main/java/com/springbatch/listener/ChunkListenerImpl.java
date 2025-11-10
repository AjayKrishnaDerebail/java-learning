package com.springbatch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.stereotype.Component;

/**
 * ChunkListener implementation that monitors chunk processing lifecycle.
 * Logs before chunk processing, after successful completion, and on errors.
 */
@Slf4j
@Component
public class ChunkListenerImpl implements ChunkListener {

  /**
   * Called before a chunk is processed.
   *
   * @param context the chunk context containing step and execution information
   */
  @Override
  public void beforeChunk(ChunkContext context) {
    log.debug(">>> Chunk Started - Step: {}", 
        context.getStepContext().getStepName());
  }

  /**
   * Called after a chunk is successfully processed.
   *
   * @param context the chunk context containing step and execution information
   */
  @Override
  public void afterChunk(ChunkContext context) {
    log.debug("<<< Chunk Completed - Step: {}, Items Read: {}", 
        context.getStepContext().getStepName(),
        context.getStepContext().getStepExecution().getReadCount());
  }

  /**
   * Called when an error occurs during chunk processing.
   *
   * @param context the chunk context containing step and execution information
   */
  @Override
  public void afterChunkError(ChunkContext context) {
    log.error("!!! Chunk Error - Step: {}, Error occurred during chunk processing", 
        context.getStepContext().getStepName());
  }
}
