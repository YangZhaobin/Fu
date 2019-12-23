package com.yzb.test.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class FullJustify {

    public static void main(String[] args) {
        String[] words = {"Science","is","what","we","understand",
                "well","enough","to","explain", "to","a","computer.","Art","is","everything","else","we","do"};

        String[] words1 = {"What","must","be","acknowledgment","shall","be"};
        System.out.println(fullJustify(words, 20));
    }

    public static List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ret = new ArrayList<>();

        int len = words.length;
        if (len == 0) return ret;
        int[] lens = new int[len];
        for (int i = 0; i < len; i++) {
            lens[i] = words[i].length();
        }

        int startIndex = 0;
        int tmpSize = 0;
        for (int i = 0; i < len; i++) {
            tmpSize += lens[i];

            if (tmpSize < maxWidth) {
                tmpSize += 1;
            }
            else if (tmpSize == maxWidth) {
                String str = "";
                for (int j = startIndex; j < i; j++) {
                    str += words[j];
                    str += " ";
                }
                str += words[i];
                ret.add(str);

                startIndex = i + 1;
                tmpSize = 0;
            }
            else {
                int size = maxWidth;
                int j = startIndex;
                for (; j < i; j++) {
                    size -= lens[j];
                }
                int n = i - startIndex - 1;
                String str = "";
                j = startIndex;
                while (n != 0 && j < i) {
                    str += words[j++];
                    int zeroNum = size / n;
                    if (size % n != 0)
                        zeroNum++;
                    for (int k = 0; k < zeroNum; k++)
                        str += " ";

                    size -= zeroNum;
                    n--;
                }
                str += words[i - 1];
                for (int k = 0; k < size; k++)
                    str += " ";
                ret.add(str);

                tmpSize = 0;
                startIndex = i--;
            }
        }
        if (tmpSize != 0) {
            String str = "";
            for (int i = startIndex; i < len; i++) {
                str += words[i];
                if (i != len - 1) str += " ";
            }

            int ll = str.length();
            for (int i = 0; i < maxWidth - ll; i++) {
                str += " ";
            }

            ret.add(str);
        }
        return ret;
    }
}
