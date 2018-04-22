package com.silenteight.task1;

import static java.util.Arrays.stream;

import java.util.HashSet;
import java.util.Set;

public class IslandCounter {

  private static final int ISLAND = 1;
  private static final int[][] NEIGHBOR = new int[][] {
      {-1, -1, 1, 0, 0, 1, 1, 1},
      {-1, 0, 1, -1, 1, -1, 0, 1}
  };

  private final int[][] map;
  private final int size;
  private final DisjointSet disjointSet;

  public IslandCounter(int[][] map) {
    this.map = stream(map)
        .map((int[] row) -> row.clone())
        .toArray((int length) -> new int[length][]);

    this.size = map.length;
    disjointSet = new DisjointSet(size * size);
  }

  public int count() {
    findIsland();

    Set<Integer> countSet = new HashSet<>();
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (isIsland(row, col)) {
          int parentElement = disjointSet.find(calculatePosition(row, col));
          countSet.add(parentElement);
        }
      }
    }
    return countSet.size();
  }

  private int calculatePosition(int row, int col) {
    return row * size + col;
  }

  private void findIsland() {
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        if (isIsland(row, col)) {
          checkNeighbor(row, col);
        }
      }
    }
  }

  private boolean isIsland(int row, int col) {
    return map[row][col] == ISLAND;
  }

  private void checkNeighbor(int row, int col) {
    for (int i = 0; i < NEIGHBOR[0].length; i++) {
      int nextRow = row + NEIGHBOR[0][i];
      int nextCol = col + NEIGHBOR[1][i];

      if (isNeighbor(nextRow, nextCol)) {
        disjointSet.union(calculatePosition(row, col), calculatePosition(nextRow, nextCol));
      }
    }
  }

  private boolean isNeighbor(int row, int col) {
    return row >= 0 && col >= 0 &&
        row < map.length && col < map[row].length &&
        isIsland(row, col);
  }
}