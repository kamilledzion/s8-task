package com.silenteight.task4;

import java.util.concurrent.BlockingQueue;

public class TaskRunner {

  private static final int MAX_TASK_SIZE = 100;

  public static void main(String[] args) {
    BlockingQueue<Task> taskQueue = new MyArrayBlockingQueue<>(MAX_TASK_SIZE);
    TaskProducer producer = new TaskProducer(taskQueue);
    TaskConsumer consumer = new TaskConsumer(taskQueue);

    new Thread(producer).start();
    new Thread(producer).start();

    new Thread(consumer).start();
    new Thread(consumer).start();
    new Thread(consumer).start();
    new Thread(consumer).start();
  }
}