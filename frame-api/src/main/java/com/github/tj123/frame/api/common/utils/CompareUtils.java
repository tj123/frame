package com.github.tj123.frame.api.common.utils;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompareUtils {


    public interface Compare<T> {

        void onAdd(List<T> content) throws Exception;

        void onDel(List<T> content) throws Exception;
    }

    public static <T> void remove(List<T> list, T var) {
        if (list == null || var == null || list.isEmpty()) {
            return;
        }
        Iterator<T> iterator = list.iterator();
        while (iterator.hasNext()) {
            T next = iterator.next();
            if (var.equals(next)) {
                iterator.remove();
                return;
            }
        }
    }

    public static void compareString(List<String> db, List<String> current, Compare<String> compare) throws Exception {
        if (db == null || db.isEmpty()) {
            compare.onAdd(current);
            return;
        }
        if (current == null || current.isEmpty()) {
            compare.onDel(db);
            return;
        }
        List<String> adds = new ArrayList<>();
        for (String cur : current) {
            if (!db.contains(cur)) {
                adds.add(cur);
                continue;
            }
            remove(db, cur);
        }
        if (!adds.isEmpty()) {
            compare.onAdd(adds);
        }
        if (!db.isEmpty()) {
            compare.onDel(db);
        }
    }


}
