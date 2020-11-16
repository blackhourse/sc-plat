package com.example.demo.leetcode;

import java.util.HashMap;

public class Main {


    public static void main(String[] args) {


        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int[] ints;
        int[] nums = new int[]{2, 7, 11, 15};
        int tagret = 9;
        for (int i = 0; i < nums.length; i++) {
            int i1 = tagret - nums[i];
            if (map.containsKey(i1)){
                 ints = new int[]{map.get(i1), i};
            }else {
                map.put(nums[i],i);
            }

        }


    }


}
