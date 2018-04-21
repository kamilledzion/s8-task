package com.silenteight.task4;

import java.util.concurrent.ArrayBlockingQueue;

public class MyArrayBlockingQueue<E> extends ArrayBlockingQueue<E> {

  private final int maxSize;

  public MyArrayBlockingQueue(int capacity) {
    super(capacity);
    maxSize = capacity;
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
    return super.remainingCapacity() == (maxSize / 2) - 1;
  }
}