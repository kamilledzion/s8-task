package com.silenteight.task1;

public class DisjointSet {

  private int[] rank;
  private int[] parent;
  private int size;

  public DisjointSet(int size) {
    rank = new int[size];
    parent = new int[size];
    this.size = size;
    makeSet();
  }

  private void makeSet() {
    for (int i = 0; i < size; i++) {
      parent[i] = i;
    }
  }

  public int find(int element) {
    if (parent[element] == element) {
      return element;
    }

    return find(parent[element]);
  }

  public void union(int x, int y) {
    int rootX = find(x);
    int rootY = find(y);

    if (rootX == rootY) {
      return;
    }

    if (rank[rootX] < rank[rootY]) {
      parent[rootX] = rootY;
    } else if (rank[rootY] < rank[rootX]) {
      parent[rootY] = rootX;
    } else {
      parent[rootY] = rootX;
      rank[rootX]++;
    }
  }
}