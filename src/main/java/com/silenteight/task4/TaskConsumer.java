package com.silenteight.task4;

import java.util.concurrent.BlockingQueue;

public class TaskConsumer implements Runnable {

  private BlockingQueue<Task> taskQueue;

  public TaskConsumer(BlockingQueue<Task> taskQueue) {
    this.taskQueue = taskQueue;
  }

  @Override
  public void run() {
    try {
      while (true) {
        taskQueue.take();
      }
    } catch (InterruptedException e) {
      e.getStackTrace();
    }
  }
}