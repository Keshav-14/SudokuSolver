
package Sudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class Sudoku {
    
    int [][] sudokuPuzzle;
    
    public Sudoku(int size){
       this.sudokuPuzzle = new int[size][size];
    }
    
    public void setPuzzle(){
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the Puzzle : ");
        for (int[] sudokuPuzzle1 : this.sudokuPuzzle) {
            for (int j = 0; j < this.sudokuPuzzle.length; j++) {
                sudokuPuzzle1[j] = in.nextInt();
            }
        }
    }
    
    public void displayPuzzle(){
        int sqrt = (int)Math.sqrt(this.sudokuPuzzle.length);
        
        System.out.println("\n");
        for(int i = 0 ; i < this.sudokuPuzzle.length ; i++){
            for(int j = 0 ; j < this.sudokuPuzzle.length ; j++){
                if(j % sqrt == 0){
                    System.out.print(" |");
                }
                System.out.print(" " + this.sudokuPuzzle[i][j]);
            }
            System.out.println(" |");
            if(i % sqrt == 2){
                for(int j = 0 ; j < this.sudokuPuzzle.length + sqrt; j++){
                    System.out.print(" _");
                }
                System.out.println(" _");
            }
        }
    }
    
    private boolean isGridSafe(int row, int col, int num){
        int sqrt = (int)Math.sqrt(this.sudokuPuzzle.length);
        
        int stRow = row - row % sqrt;
        int stCol = col - col % sqrt;
        
        for(int i = stRow; i < stRow + sqrt ; i++){
            for(int j = stCol; j < stCol ; j++){
                if(this.sudokuPuzzle[i][j] == num){
                    return false;
                }
            }
        }
        
        return true;
    }
    
    private boolean isRowSafe(int row, int num){
        for(int i = 0 ; i < this.sudokuPuzzle.length ; i++){
            if(this.sudokuPuzzle[row][i] == num){
                return false;
            }
        }
        return true;
    }
    
    private boolean isColumnSafe(int col, int num){
        for(int i = 0 ; i < this.sudokuPuzzle.length ; i++){
            if(this.sudokuPuzzle[i][col] == num){
                return false;
            }
        }
        return true;
    }
    
    private boolean isCellSafe(int row, int col, int num){
        return isRowSafe(row, num) && isColumnSafe(col, num) &&
                isGridSafe(row, col, num);
    }
    
    private ArrayList<Integer> isPuzzleSolved(){
        
        // if not solved return the indices of that cell
        ArrayList<Integer> indices = new ArrayList<>();
        
        for(int i = 0; i < this.sudokuPuzzle.length; i++){
            for(int j = 0 ; j < this.sudokuPuzzle.length ; j++){
                if(this.sudokuPuzzle[i][j] == 0){
                    indices.add(i);
                    indices.add(j);
                    return indices;
                }
            }
        }
        return null;
    }
    
    private int[] findEmptyCell() {
        for (int i = 0; i < this.sudokuPuzzle.length; i++) {
            for (int j = 0; j < this.sudokuPuzzle.length; j++) {
                if (this.sudokuPuzzle[i][j] == 0) {
                    return new int[] {i, j};
                }
            }
        }
        return null; 
    }
    
    public boolean solvePuzzle() {
        int[] emptyCell = findEmptyCell();
        
        if (emptyCell == null) {
            return true; 
        }

        int row = emptyCell[0];
        int col = emptyCell[1];

        for (int num = 1; num <= this.sudokuPuzzle.length; num++) {
            if (isCellSafe(row, col, num)) {
                this.sudokuPuzzle[row][col] = num;

                if (solvePuzzle()) {
                    return true;
                }

                this.sudokuPuzzle[row][col] = 0; 
            }
        }
        return false;
    }

}
