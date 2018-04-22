package com.silenteight.task1;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class IslandServiceTest {

  @Test
  public void countShouldReturnFour() {
    int[][] map = {
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {1, 1, 1, 0, 0, 0, 1, 0, 0},
        {1, 1, 0, 0, 0, 1, 1, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0},
        {1, 1, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 0, 0, 0, 0},
        {0, 0, 0, 0, 0, 1, 1, 0, 0},
    };

    assertThat(new IslandService(map).count(), is(4));
  }

  @Test
  public void countShouldReturnFive() {
    int[][] map = {
        {1, 1, 0, 0, 0},
        {0, 1, 0, 0, 1},
        {1, 0, 0, 1, 1},
        {0, 0, 0, 0, 0},
        {1, 0, 1, 0, 1}
    };

    assertThat(new IslandService(map).count(), is(5));
  }

  @Test
  public void countShouldReturnOneForHugeMap() {
    int size = 10000;
    int[][] map = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        map[i][j] = 1;
      }
    }

    assertThat(new IslandService(map).count(), is(1));
  }
}