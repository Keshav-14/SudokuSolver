
package Sudoku;

public class Main {
    
    public static void main(String[] args){
        int size = 9;
        Sudoku puzzle = new Sudoku(size);
        puzzle.setPuzzle();
        
        puzzle.solvePuzzle();
        puzzle.displayPuzzle();
    }
}
