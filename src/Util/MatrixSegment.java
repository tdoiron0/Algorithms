package Util;

import Math.Matrix;

public class MatrixSegment {
    private Matrix data;
    private int top;
    private int bottom; 
    private int left; 
    private int right;
    private int height;
    private int width; 

    public MatrixSegment(Matrix data, int top, int bottom, int left, int right) {
        this.data = data;
        this.top = top; 
        this.bottom = bottom; 
        this.left = left; 
        this.right = right;
        this.height = bottom - top + 1;
        this.width = right - left + 1;
    }

    public int get(int row, int column) {
        if (top + row > bottom && left + column > right) {
            throw new IllegalArgumentException(String.format("Cannot access element [%d,%d] in Matrix of size %dx%d", row, column, height, width));
        }
        return data.get(top + row, left + column);
    }
}
