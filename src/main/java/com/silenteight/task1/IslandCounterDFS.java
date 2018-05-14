package com.silenteight.task1;

import static java.util.Arrays.stream;

public class IslandCounterDFS {

  private static final int ISLAND = 1;
  private static final int[][] NEIGHBOR = new int[][] {
      {-1, -1, 1, 0, 0, 1, 1, 1},
      {-1, 0, 1, -1, 1, -1, 0, 1}
  };

  private final int[][] map;
  private final int size;

  public IslandCounterDFS(int[][] map) {
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
          mark(row, col);
          count++;
        }
      }
    }

    return count;
  }

  private boolean isIsland(int row, int col) {
    return map[row][col] == ISLAND;
  }

  private void mark(int row, int col) {
    map[row][col] = 0;

    for (int i = 0; i < NEIGHBOR[0].length; i++) {
      int nextRow = row + NEIGHBOR[0][i];
      int nextCol = col + NEIGHBOR[1][i];

      if (isNeighbor(nextRow, nextCol)) {
        mark(nextRow, nextCol);
      }
    }
  }

  private boolean isNeighbor(int row, int col) {
    return row >= 0 && col >= 0 &&
        row < map.length && col < map[row].length &&
        isIsland(row, col);
  }
}