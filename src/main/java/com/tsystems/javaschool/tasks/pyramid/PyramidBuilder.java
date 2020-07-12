package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        int rowCounter = 0;
        int possibleLength = 0;
        int tmpLength;
        try {
            if (inputNumbers.size()>Integer.MAX_VALUE/2){
                throw new CannotBuildPyramidException();
            }
            while (possibleLength < inputNumbers.size()) {
                rowCounter++;
                tmpLength = possibleLength;
                possibleLength = tmpLength + rowCounter;
            }
            if (possibleLength != inputNumbers.size()) {
                throw new CannotBuildPyramidException();
            }
            Collections.sort(inputNumbers);
        } catch (RuntimeException e){
            throw new CannotBuildPyramidException();
        }


        int[][] tmpArray = new int[rowCounter][rowCounter];
        int offset = 0;
        for (int i = 0; i < rowCounter; i++) {
            for (int j = 0; j < rowCounter; j++) {
                if (j<=i) {
                    tmpArray[i][j] = inputNumbers.get(i + offset);
                    if (j < i) {
                        offset++;
                    }
                } else {
                    tmpArray[i][j] = Integer.MIN_VALUE; //fill gap with the least likely digit
                }
            }
        }
        int[][] result = new int[rowCounter][rowCounter*2-1];
        int[] digitRowWithZeros;
        for (int i = 0; i < rowCounter; i++) {
            int[] digitRow = new int[i+1];
            for (int j = 0; j < rowCounter*2-1; j++) {
                if ((j<rowCounter-1-i) || (j>rowCounter-1+i)){
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                    System.arraycopy(tmpArray[i], 0, digitRow, 0, i + 1);
                }
            }
            digitRowWithZeros = addZeros(digitRow);
            int t = 0;
            for (int j = 0; j < rowCounter*2-1; j++) {
                if (result[i][j]==1){
                    result[i][j] = digitRowWithZeros[t];
                    t++;
                }
            }
        }
        return result;
    }

    private int[] addZeros(int[] array){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length-1; i++) {
            list.add(array[i]);
            list.add(0);
        }
        list.add(array[array.length-1]);
        return list.stream()
                .mapToInt(x -> x)
                .toArray();
    }


}
