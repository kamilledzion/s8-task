package com.silenteight.task4;

import static java.lang.Thread.State.RUNNABLE;
import static java.lang.Thread.State.WAITING;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.concurrent.BlockingQueue;
import org.junit.Test;

public class TaskRunnerTest {

  public static final int MAX_TASK_SIZE = 100;

  private BlockingQueue<Task> taskQueue;
  private TaskProducer producer;
  private TaskConsumer consumer;

  public TaskRunnerTest() {
    taskQueue = new MyArrayBlockingQueue<>(MAX_TASK_SIZE);
    producer = new TaskProducer(taskQueue);
    consumer = new TaskConsumer(taskQueue);
  }

  @Test
  public void consumerShouldWaitWhenQueueIsEmpty() throws InterruptedException {
    Thread consumerThread = new Thread(consumer);
    consumerThread.start();
    Thread.sleep(1000);

    assertThat(consumerThread.getState(), is(WAITING));
  }

  @Test
  public void consumerShouldWaitUntilQueueIsFull() {
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();

    while (taskQueue.remainingCapacity() == 75) {
      producerThread.interrupt();

      assertThat(consumerThread.getState(), is(WAITING));
    }
  }

  @Test
  public void producerShouldRunWhenQueueIsEmptyInHalf() {
    Thread producerThread = new Thread(producer);
    Thread consumerThread = new Thread(consumer);
    producerThread.start();
    consumerThread.start();

    while (taskQueue.remainingCapacity() == MAX_TASK_SIZE / 2) {
      consumerThread.interrupt();

      assertThat(producerThread.getState(), is(RUNNABLE));
    }
  }

  @Test
  public void producerShouldWaitWhenQueueIsFull() {
    Thread producerThread = new Thread(producer);
    producerThread.start();

    while (taskQueue.remainingCapacity() == 0) {
      assertThat(producerThread.getState(), is(WAITING));
    }
  }
}