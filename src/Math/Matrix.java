package Math;

import java.util.ArrayList;
import java.util.List;

import Util.Util;

public class Matrix {
    private int height = 0;
    private int width = 0;

    /**
     * Elements stored as a list of rows. This preserves similar notation as normal Matrix index notation 
     */
    private int[][] data;

    public Matrix(String src) {
        String[] rows = src.split(";");
        data = new int[rows.length][];

        for (int i = 0; i < rows.length; ++i) {
            String[] elements = rows[i].split(",");
            data[i] = new int[elements.length];

            for (int j = 0; j < elements.length; ++j) {
                data[i][j] = Integer.valueOf(elements[j]);
            }
        }
        this.height = data.length;
        this.width = data[0].length;
    }

    public Matrix(int[][] src) {
        this.data = Util.copy(src);
        this.height = data.length;
        this.width = data[0].length;
    }

    public int get(int row, int column) {
        if (row >= height || column >= width) {
            throw new IllegalArgumentException(String.format("Cannot access element [%d,%d] in Matrix of size %dx%d", row, column, height, width));
        }
        return data[row][column];
    }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    /**
     * Adds two matrices
     * 
     * @param oper operand
     * @return this + oper
     */
    public Matrix add(Matrix oper) {
        if (height != oper.getHeight() || width != oper.getWidth()) {
            throw new IllegalArgumentException("Cannot add matrices of differing dimentions");
        }

        int[][] result = new int[height][width];
        for (int i = 0; i < data.length; ++i) {
            for (int j = 0; j < data[i].length; ++j) {
                result[i][j] = data[i][j] + oper.get(i, j);
            }
        }
        return new Matrix(result);
    }

    public Matrix multiply(Matrix oper) {
        if (width != oper.getHeight()) {
            throw new IllegalArgumentException(String.format("Cannot multiply Matrix of dimention %dx%d by Matrix of %dx%d", height, width, oper.getHeight(), oper.getWidth()));
        }

        int[][] result = new int[height][];
        for (int i = 0; i < data.length; ++i) {
            result[i] = new int[oper.getWidth()];
            for (int j = 0; j < oper.getWidth(); ++j) {
                int sum = 0;
                for (int k = 0; k < width; ++k) {
                    sum += this.get(i,k)*oper.get(k,j);
                }
                result[i][j] = sum;
            }
        }

        return new Matrix(result);
    }

    /**
     * Implementation of the Strassen Algorithm  
     * 
     * @param oper operand to be multiply this by
     * @return this times oper
     */
    public Matrix fastMultiply(Matrix oper) {
        if (!(isSquare() && oper.isSquare() && height % 2 == 0 && oper.getHeight() % 2 == 0)) {
            throw new IllegalArgumentException("Can only fastmultiply matriceies ");
        }

        Matrix P1;
        return null;
    }

    public Matrix transpose() {
        int[][] result = new int[width][height];
        for (int j = 0; j < width; ++j) {
            for (int i = 0; i < height; ++i) {
                result[j][i] = data[i][j];
            }
        }
        return new Matrix(result);
    }

    public boolean isSquare() {
        return height == width;
    }

    public int det() {
        if (!isSquare()) {
            throw new IllegalStateException("Cannot find the determinant of an non-square Matrix");
        }

        //TODO

        return 0;
    }

    public Matrix cofactor(int row, int column) {
        int[][] resultSrc = new int[height - 1][width - 1];

        int a = 0;
        int b = 0;
        for (int c = 0; c < height; ++c) {
            if (c != row) {
                for (int d = 0; d < width; ++d) {
                    if (d != column) {
                        resultSrc[a++][b++] = data[c][d];
                    }
                }
            }
        }

        return new Matrix(resultSrc);
    }

    public String toString() {
        return Util.toStringSpaced(data);
    }
}
