package com.tsystems.javaschool.tasks.subsequence;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        try {
            if (y.size() < x.size()) {
                return false;
            }
            List tmp = new ArrayList();
            List tmpX = new ArrayList(x);
            for (Object o : y) {
                for (int j = 0; j < x.size(); j++) {
                    if (x.get(j).equals(o)) {
                        tmp.add(x.get(j));
                        x.remove(j);
                    }
                }
            }
            for (int i = 0; i < tmpX.size(); i++) {
                if (!tmpX.get(i).equals(tmp.get(i))) {
                    return false;
                }
            }
            return true;
        }
        catch (NullPointerException e){
            throw new IllegalArgumentException(e);
        }
    }
}
