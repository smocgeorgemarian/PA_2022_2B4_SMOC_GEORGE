package com.company.homework;

import java.util.Comparator;

public class CustomComparator implements Comparator<Eveniment>{
    @Override
    public int compare(Eveniment o1, Eveniment o2) {
        if (o1.getEnd_time() != o2.getEnd_time())
            return o1.getEnd_time() - o2.getEnd_time();
        return 1;
    }
}
