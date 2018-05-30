package com.atibusinessgroup.fmp.comparator;

import java.util.Comparator;

import com.atibusinessgroup.fmp.domain.dto.Category;

public class CategoryComparator implements Comparator<Category> {
    public int compare(Category o1, Category o2) {
       return o1.getCatNo() + "".compareTo(o2.getCatNo() + "");
    }
}
