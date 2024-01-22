impl Solution {
    pub fn find_error_nums(nums: Vec<i32>) -> Vec<i32> {
        let mut nums = nums.clone();
        nums.sort();
        let n = nums.len();
        
        let mut duplicate = -1;
        let mut missing = -1;
        
        for i in 0..=n {
            let this = if i == n { (n + 1) as i32 } else { nums[i] };
            let last = if i == 0 { 0 as i32 } else { nums[i - 1] };
            
            if this == last {
                duplicate = this;
            } else if this != last + 1 {
                missing = last + 1;
            }
        }
        
        vec![duplicate, missing]
    }
}
