package com.yzb.test.test;

import java.util.*;

public class Test13 {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
    }

    public static List<String> restoreIpAddresses(String s) {
        Set<String> ret = new HashSet<>();


        find(s, 0, 0, new ArrayList<>(), ret);

        return new ArrayList<>(ret);
    }

    public static void find(String s, int index, int lastPot,
                     List<String> list, Set<String> ret) {
        if (list.size() == 4) {
            String r = "";
            for (int i = 0; i < list.size(); i++) {
                String str = list.get(i);

                if (Long.parseLong(str) > 255 || Long.parseLong(str) < 0 || !str.equals(Long.parseLong(str) + ""))
                    return;
                r += str;
                if (i != 3) r += ".";
            }
            ret.add(r);
            return;
        }

        if (0 == s.length()) {
            return;
        }

        if (index == s.length() + 1) {
            return;
        }

        // 加点
        if (index != 0) {
            if (list.size() == 3) {
                String t = s.substring(lastPot);
                List<String> addPotList = new ArrayList<>(list);
                addPotList.add(t);
                find(s, index + 1, index, addPotList, ret);
            }
            else {
                String t = s.substring(lastPot, index);
                List<String> addPotList = new ArrayList<>(list);
                addPotList.add(t);
                find(s, index + 1, index, addPotList, ret);
            }
        }


        // 不加点
        find(s, index + 1, lastPot, list, ret);

    }
}
