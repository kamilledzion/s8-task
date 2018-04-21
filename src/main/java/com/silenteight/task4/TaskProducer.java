package com.silenteight.task4;

import static com.silenteight.task4.TaskGenerator.generate;

import java.util.concurrent.BlockingQueue;

public class TaskProducer implements Runnable {

  private BlockingQueue<Task> taskQueue;

  public TaskProducer(BlockingQueue<Task> taskQueue) {
    this.taskQueue = taskQueue;
  }

  @Override
  public void run() {
    while (true) {
      Task task = generate();
      System.out.println(task.getValue());
      try {
        taskQueue.put(task);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}