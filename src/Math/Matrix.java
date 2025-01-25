package Math;

import java.util.ArrayList;
import java.util.List;

public class Matrix {
    private int height = 0;
    private int width = 0;

    /**
     * Elements stored as a list of rows. This preserves similar notation as normal matrix index notation 
     */
    private List<List<Integer>> data = new ArrayList<>();

    public Matrix(String src) {
        String[] rows = src.split(";");
        for (int i = 0; i < rows.length; ++i) {
            data.add(new ArrayList<>());
            String[] elements = rows[i].split(",");
            for (String e : elements) {
                data.get(i).add(Integer.valueOf(e));
            }
        }
        height = data.size();
        width = data.getFirst().size();
    }

    public Matrix(int[][] src) {
        this.data = new ArrayList<>();
        for (int i = 0; i < src.length; ++i) {
            this.data.add(Util.Util.boxedList(src[i]));
        }

        if (this.data.size() == 0) {
            this.height = 0;
            this.width = 0;
        } else {
            this.height = this.data.size();
            this.width = this.data.getFirst().size();
        }
    }

    public Matrix(List<List<Integer>> src) {
        if (src == null) {
            throw new IllegalArgumentException("src cannot be null");
        }

        if (src.size() == 0) {
            this.data = new ArrayList<>();
            this.height = 0;
            this.width = 0;
        } else {
            this.data = new ArrayList<>(src);
            this.height = this.data.size();
            this.width = this.data.getFirst().size();
        }
    }

    public int get(int row, int column) {
        if (row >= height || column >= width) {
            throw new IllegalArgumentException(String.format("Cannot access element [%d,%d] in matrix of size %dx%d", row, column, height, width));
        }
        return data.get(row).get(column);
    }

    public int getHeight() { return height; }
    public int getWidth() { return width; }

    public Matrix multiply(Matrix oper) {
        if (width != oper.getHeight()) {
            throw new IllegalArgumentException(String.format("Cannot multiply matrix of dimention %dx%d by matrix of %dx%d", height, width, oper.getHeight(), oper.getWidth()));
        }

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < data.size(); ++i) {
            result.add(new ArrayList<>());
            for (int j = 0; j < oper.getWidth(); ++j) {
                int sum = 0;
                for (int k = 0; k < width; ++k) {
                    sum += this.get(i,k)*oper.get(k,j);
                }
                result.get(i).add(sum);
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
        List<List<Integer>> result = new ArrayList<>();
        for (int j = 0; j < width; ++j) {
            result.add(new ArrayList<>());
            for (int i = 0; i < height; ++i) {
                result.getLast().add(this.get(i,j));
            }
        }
        return new Matrix(result);
    }

    public boolean isSquare() {
        return height == width;
    }

    public int det() {
        if (!isSquare()) {
            throw new IllegalStateException("Cannot find the determinant of an non-square matrix");
        }

        //TODO

        return 0;
    }

    public Matrix cofactor(int row, int column) {
        List<List<Integer>> resultSrc = new ArrayList<>();

        for (int i = 0; i < height; ++i) {
            if (i != row) {
                resultSrc.add(new ArrayList<>());
                for (int j = 0; j < width; ++j) {
                    if (j != column) {
                        resultSrc.getLast().add(data.get(i).get(j));
                    }
                }
            }
        }

        return new Matrix(resultSrc);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.size(); ++i) {
            sb.append(data.get(i).toString().replaceAll(",", ""));
            sb.append(((i + 1 < data.size()) ? '\n' : 0));
        }
        return sb.toString();
    }
}
