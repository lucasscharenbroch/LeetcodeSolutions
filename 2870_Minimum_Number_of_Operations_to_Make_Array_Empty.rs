use std::collections::HashMap;

impl Solution {
    fn num_moves(n: i32) -> i32 {
        match n % 3 {
            0 => n / 3,
            1 => 2 + (n - 4) / 3,
            2 => 1 + (n - 2) / 3,
            _ => panic!(),
        }
    }
    
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut freq = HashMap::new();
        
        for n in nums {
            *(freq.entry(n).or_insert(0)) += 1;
        }
        
        let mut res = 0;
        
        for (k, &f) in freq.iter() {
            if f == 1 {
                return -1;
            }
            
            res += Self::num_moves(f);
        }
        
        res
    }
}
