impl Solution {
    pub fn length_of_lis(nums: Vec<i32>) -> i32 {
        let mut x: Vec<i32> = Vec::new(); // x[j] = min elem in nums[0..i-1] s.t. the
                                          // longest ss (ending in it) has length (j + 1)
        
        for n in nums {
            let j = x.partition_point(|&y| y < n);
            if j == x.len() {
                x.push(n)
            } else if x[j] > n {
                x[j] = n;
            }
        }
        
        x.len() as i32
    }
}
