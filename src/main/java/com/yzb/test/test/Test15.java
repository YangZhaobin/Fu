package com.yzb.test.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Test15 {
    public static void main(String[] args) {
        System.out.println(restoreIpAddresses("010010"));
    }

    public static List<String> ret;
    public static List<String> restoreIpAddresses(String s) {
        ret = new ArrayList<>();
        backtraceing(s, 0, new ArrayList<>());
        return ret;
    }

    public static void backtraceing(String s, int start, List<String> list) {
        int len = s.length();
        if (start > len) return;
        if (start == len && list.size() == 4) {
            String r = "";
            for (int i = 0; i < list.size(); i++) {
                r += list.get(i);
                if (i != 3) r += ".";
            }
            ret.add(r);
            return;
        }

        for (int i = start; i < start + 3 && i < len; i++) {
            String ip = s.substring(start, i + 1);
            if (Long.parseLong(ip) > 255 || Long.parseLong(ip) < 0 || !ip.equals(Long.parseLong(ip) + ""))
                return;
            list.add(ip);
            backtraceing(s, i + 1, list);
            list.remove(list.size() - 1);
        }

    }
}
