import java.util.Scanner;
import java.util.List;
import java.util.LinkedList;

public class Solution {

  public static void main(String[] args) {
    
    Scanner scanner = new Scanner(System.in);
    int row_start = scanner.nextInt();
    int column_start = scanner.nextInt();
    int row_goal = scanner.nextInt();
    int column_goal = scanner.nextInt();

    int knight_minMoves_fromStart_toGoal =
        knightBFS_find_minMoves_fromStart_toGoal(row_start, column_start, row_goal, column_goal);

    System.out.println(knight_minMoves_fromStart_toGoal);
  }

  /**
   * Applying Breadth First Search to find the minimum moves of the knight from start to goal.
   *
   * @return An integer, representing the min moves.
   */
  public static int knightBFS_find_minMoves_fromStart_toGoal(
      int row_start, int column_start, int row_goal, int column_goal) {

    // To be consistent with a board designation from 1x1 to 8x8, rows and columns with index=0 are not applied,
    boolean[][] visited = new boolean[9][9];
    visited[row_start][column_start] = true;

    LinkedList<Point> queue_moves = new LinkedList<Point>();
    Point start = new Point(row_start, column_start, 0);
    queue_moves.add(start);

    int minMoves = 0;

    while (!queue_moves.isEmpty()) {

      Point current = queue_moves.removeFirst();
      if (current.row == row_goal && current.column == column_goal) {
        minMoves = current.moves_fromStart;
        break;
      }
      add_nextMoves_toQueue(current, queue_moves, visited);
    }
    return minMoves;
  }

  private static void add_nextMoves_toQueue(
      Point current, LinkedList<Point> queue_moves, boolean[][] visited) {

    // All possible moves for a knight from a given position, as per the rules of chess.
    int[][] knightMoves = new int[][] {{1, 1, -1, -1, 2, 2, -2, -2}, {2, -2, 2, -2, 1, -1, 1, -1}};

    for (int i = 0; i < 8; i++) {

      int row_nextMove = current.row + knightMoves[0][i];
      int column_nextMove = current.column + knightMoves[1][i];

      if (isValidMove(row_nextMove, column_nextMove, visited)) {

        queue_moves.add(new Point(row_nextMove, column_nextMove, current.moves_fromStart + 1));
        visited[row_nextMove][column_nextMove] = true;
      }
    }
  }

  private static boolean isValidMove(int row, int column, boolean[][] visited) {
    if (row >= 1 && row <= 8 && column >= 1 && column <= 8 && visited[row][column] == false) {
      return true;
    }
    return false;
  }

  static class Point {
    int row;
    int column;
    int moves_fromStart;

    public Point(int row, int column, int moves_fromStart) {
      this.row = row;
      this.column = column;
      this.moves_fromStart = moves_fromStart;
    }
  }
}
