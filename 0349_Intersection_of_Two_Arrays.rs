use std::collections::HashSet;

impl Solution {
    pub fn intersection(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        nums1.into_iter().collect::<HashSet<_>>()
            .intersection(&nums2.into_iter().collect::<HashSet<_>>())
            .copied()
            .collect::<Vec<_>>()
    }
}
