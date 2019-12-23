package com.yzb.test.loadbalance.轮询;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Weight {
    String ip;
    Integer weight;
    Integer currentWeight;
}
