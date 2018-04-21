package com.silenteight.task4;


import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;

public final class TaskGenerator {

  private static final int TASK_MIN_SIZE = 1;
  private static final int TASK_MAX_SIZE = 100;
  private static final String TASK_ELEMENTS = "1234567890+/*- ";

  public static Task generate() {
    int size = nextInt(TASK_MIN_SIZE, TASK_MAX_SIZE);
    String value = random(size, TASK_ELEMENTS);
    return new Task(value);
  }
}