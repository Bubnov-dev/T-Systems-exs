package com.tsystems.javaschool.tasks.pyramid;

import java.io.Externalizable;
import java.util.*;

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
        try {
            Collections.sort(inputNumbers);
            int width;
            int height = 0;
            Map<Integer, Integer> numberMap = new HashMap();
            Iterator<Integer> iter = inputNumbers.iterator();
            int pos = 0;
            int len = 1;
            Integer tmpVal;
            while (iter.hasNext()) {
                pos = 1 - len;
                for (int i = 0; i < len; i++) {
                        numberMap.put(iter.next(), pos);
                    pos += 2;
                }
                len++;
                height++;
            }
            pos -= 2;
            width = pos * 2 + 1;

            int[][] resTable = new int[height][width];
            len = 1;
            int tmp = len;
            int floor = 0;
            for (Map.Entry<Integer, Integer> entry : numberMap.entrySet()) {
                System.out.println(entry);
                resTable[floor][entry.getValue() + pos] = entry.getKey();
                if (--tmp == 0) {
                    len++;
                    floor++;
                    tmp = len;
                }
            }
            return resTable;
        }
        catch (Error | Exception e){
            throw new CannotBuildPyramidException();
        }
    }



}
