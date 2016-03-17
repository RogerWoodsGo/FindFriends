package com.miaozhen.wuwenchao.mr;

import java.util.Comparator;

/**
 * Created by beyondwu on 2016/2/19.
 */
public class CitedNum {
    private String CitedName;
    private Integer CitedNum;

    public CitedNum(String citedName, Integer citedNum) {
        CitedName = citedName;
        CitedNum = citedNum;
    }

    public String getCitedName() {
        return CitedName;
    }

    public Integer getCitedNum() {
        return CitedNum;
    }

}

class CitedComparator implements Comparator<CitedNum> {

    @Override
    public int compare(CitedNum o1, CitedNum o2) {

        return o1.getCitedNum() - o2.getCitedNum();
    }
}
