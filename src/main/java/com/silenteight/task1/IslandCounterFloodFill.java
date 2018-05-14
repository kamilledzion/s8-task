package com.silenteight.task1;

import static java.util.Arrays.stream;

import java.util.ArrayDeque;
import java.util.Queue;

public class IslandCounterFloodFill {

  private static final int ISLAND = 1;
  private static final int[][] NEIGHBOR = new int[][] {
      {-1, -1, 1, 0, 0, 1, 1, 1},
      {-1, 0, 1, -1, 1, -1, 0, 1}
  };

  private final int[][] map;
  private final int size;

  public IslandCounterFloodFill(int[][] map) {
    this.map = stream(map)
        .map((int[] row) -> row.clone())
        .toArray((int length) -> new int[length][]);

    this.size = map.length;
  }

  public int count() {
    int count = 0;

    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {

        if (isIsland(row, col)) {
          floodFill(row, col);
          count++;
        }
      }
    }

    return count;
  }

  private void floodFill(int x, int y) {
    Queue<Pair> queue = new ArrayDeque<>();
    queue.add(new Pair(x, y));

    while (!queue.isEmpty()) {
      Pair node = queue.poll();
      x = node.getX();
      y = node.getY();
      map[x][y] = 0;

      for (int i = 0; i < NEIGHBOR[0].length; i++) {
        int nextRow = x + NEIGHBOR[0][i];
        int nextCol = y + NEIGHBOR[1][i];

        if (isNeighbor(nextRow, nextCol)) {
          queue.add(new Pair(nextRow, nextCol));
        }
      }
    }
  }

  private boolean isIsland(int row, int col) {
    return map[row][col] == ISLAND;
  }

  private boolean isNeighbor(int row, int col) {
    return row >= 0 && col >= 0 &&
        row < map.length && col < map[row].length &&
        isIsland(row, col);
  }
}

class Pair {

  private int x;
  private int y;

  public Pair(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public int getX() {
    return x;
  }

  public void setX(int x) {
    this.x = x;
  }

  public int getY() {
    return y;
  }

  public void setY(int y) {
    this.y = y;
  }
}