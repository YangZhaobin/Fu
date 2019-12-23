package com.yzb.test.LeetCode;

/**
 * 最佳买卖股票时机含冷冻期
 *
 *
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *      你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *      卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 *
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 */
public class MaxProfit {
    public static void main(String[] args) {
//        int[] prices = {6, 1, 2, 3, 4, 7};
        int[] prices = {1, 2, 3, 0, 2};
        System.out.println(maxProfit(prices));
    }

    public static int maxProfit(int[] prices) {
        int n = prices.length;

        if (n == 0 || n == 1) return 0;
        if (n == 2)
            return prices[1] > prices[0] ? prices[1] - prices[0] : 0;

        // buy 表示截至第i天，最后一个操作是买时的最大收益；
        int buy = 0 - Math.min(prices[1], prices[0]);
        // freeze 表示截至第i天，最后一个操作是冷冻期时的最大收益；
        int freeze = 0;
        // sell 表示截至第i天，最后一个操作是卖时的最大收益；
        int sell = Math.max(freeze, prices[1] - prices[0]);

        for (int i = 2; i < n; i++) {
            int oldBuy = buy;
            buy = Math.max(buy, freeze - prices[i]);
            freeze = sell;
            sell = Math.max(prices[i] + oldBuy, freeze);
        }

        return sell;
    }
}
