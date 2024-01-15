impl Solution {
    pub fn check_possibility(nums: Vec<i32>) -> bool {
        let n = nums.len();
        
        if n <= 1 {
            return true;
        }
        
        let mut prefix_is_non_dec = vec![false; n];
        let mut suffix_is_non_dec = vec![false; n];
        
        prefix_is_non_dec[0] = true;
        for i in 1..n {
            if nums[i] < nums[i - 1] {
                break;
            }
            prefix_is_non_dec[i] = true;
        }
        
        suffix_is_non_dec[n - 1] = true;
        for i in (0..(n-1)).rev() {
            if nums[i + 1] < nums[i] {
                break;
            }
            suffix_is_non_dec[i] = true;
        }
        
        for i in 0..n {
            let (left_nd, left_elem) = if i == 0 {
                (true, std::i32::MIN)
            } else {
                (prefix_is_non_dec[i - 1], nums[i - 1])
            };
                
            let (right_nd, right_elem) = if i + 1 >= n {
                (true, std::i32::MAX)
            } else {
                (suffix_is_non_dec[i + 1], nums[i + 1])
            };
            
            if left_nd && right_nd && left_elem <= right_elem {
                return true;
            }
        }
        
        false
    }
}
