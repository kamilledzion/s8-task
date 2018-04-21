package com.silenteight.task4;

import static com.silenteight.task4.TaskRunner.MAX_TASK_SIZE;

import java.util.concurrent.ArrayBlockingQueue;

public class MyArrayBlockingQueue<E> extends ArrayBlockingQueue<E> {

  public MyArrayBlockingQueue(int capacity) {
    super(capacity);
  }

  @Override
  public E take() throws InterruptedException {
    synchronized (this) {
      while (super.isEmpty()) {
        wait();
      }
      if (isHalf()) {
        notifyAll();
      }
      return super.poll();
    }
  }

  @Override
  public void put(E e) throws InterruptedException {
    synchronized (this) {
      while (isFull()) {
        notifyAll();
        wait();
      }
      super.add(e);
    }
  }

  public boolean isFull() {
    return super.remainingCapacity() == 0;
  }

  private boolean isHalf() {
    return super.remainingCapacity() == (MAX_TASK_SIZE / 2) - 1;
  }
}