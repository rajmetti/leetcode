// 3583. Count Special Triplets
// Medium
// You are given an integer array nums.

// A special triplet is defined as a triplet of indices (i, j, k) such that:

//     0 <= i < j < k < n, where n = nums.length
//     nums[i] == nums[j] * 2
//     nums[k] == nums[j] * 2

// Return the total number of special triplets in the array.

// Since the answer may be large, return it modulo 109 + 7.

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class Q3583{

    public static void main(String[] args) {
        int[] arr = IntStream.generate(() -> 0)
                     .limit(100_000)
                     .toArray();
        int result = specialTriplet(arr);
        System.out.println(result);
        
        
        // int specialTriplet1 = countTriplets(arr);
        // System.out.println(specialTriplet1);
    }
    public static int specialTriplet(int[] nums){
        int length = nums.length;
        long MOD = 1000000007L;

        Map<Integer, Integer> right = new HashMap<>();
        for(int i = 0; i < length; i ++){
            // count the occurrance of j or (j * 2) after j, right side
            right.put(nums[i], right.getOrDefault(nums[i], 0) + 1);
        }
        Map<Integer, Integer> left = new HashMap<>();
        long count = 0;
        for (int j = 0; j< length; j++){
            int val = nums[j];
            int j2 = val * 2;
            int leftCount = left.getOrDefault(j2, 0);
            int totalCount = right.getOrDefault(j2, 0);
            int zeros = (val == j2 ? 1 : 0); // for zero j,
            int rightCount = totalCount - leftCount - zeros;
            count = (count + (long) leftCount * rightCount) % MOD;
            left.put(val, left.getOrDefault(val, 0) + 1);
        }

        return (int) (count);
    }

    // public static int countTriplets(int[] nums) {
    //     final long MOD = 1_000_000_007L;
    //     int n = nums.length;

    //     Map<Integer, Integer> freqAfter = new HashMap<>();
    //     Map<Integer, Integer> freqBefore = new HashMap<>();

    //     // Fill freqAfter
    //     for (int x : nums) {
    //         freqAfter.put(x, freqAfter.getOrDefault(x, 0) + 1);
    //     }

    //     long result = 0;

    //     for (int j = 0; j < n; j++) {
    //         int mid = nums[j];

    //         // This element moves from after → before
    //         freqAfter.put(mid, freqAfter.get(mid) - 1);
    //         if (freqAfter.get(mid) == 0) {
    //             freqAfter.remove(mid);
    //         }

    //         int target = mid * 2;

    //         // count i < j
    //         long leftCount = freqBefore.getOrDefault(target, 0);

    //         // count k > j
    //         long rightCount = freqAfter.getOrDefault(target, 0);

    //         result = (result + leftCount * rightCount) % MOD;

    //         // add mid to freqBefore
    //         freqBefore.put(mid, freqBefore.getOrDefault(mid, 0) + 1);
    //     }

    //     return (int) result;
    // }

    // public static int specialTriplet1(int[] nums) {
    //     int n = nums.length;
    //     long MOD = 1000000007L;
        
    //     // Step 1: Total frequency of entire array
    //     Map<Integer, Integer> total = new HashMap<>();
    //     for (int num : nums) {
    //         total.put(num, total.getOrDefault(num, 0) + 1);
    //     }
        
    //     // Step 2: Left frequency (prefix, starts empty)
    //     Map<Integer, Integer> left = new HashMap<>();
    //     long ans = 0;
        
    //     for (int j = 0; j < n; j++) {
    //         int val = nums[j];
    //         long target = (long) val * 2;
    //         //if (target > Integer.MAX_VALUE) continue; // Unlikely, but safe
    //         int leftCount = left.getOrDefault((int) target, 0);
    //         int totalCount = total.getOrDefault((int) target, 0);
    //         int currMatches = (target == val ? 1 : 0);
    //         int rightCount = totalCount - leftCount - currMatches;
    //         // Add to answer (use long to avoid overflow in mult)
    //         ans = (ans + (long) leftCount * rightCount) % MOD;
    //         // Update left with current val
    //         left.put(val, left.getOrDefault(val, 0) + 1);
    //     }
        
    //     return (int) ans;
    // }

}