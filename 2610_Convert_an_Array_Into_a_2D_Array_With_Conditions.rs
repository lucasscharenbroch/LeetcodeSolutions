use std::collections::HashMap;

impl Solution {
    pub fn find_matrix(nums: Vec<i32>) -> Vec<Vec<i32>> {
        let mut cnts = HashMap::new();
        
        for n in nums {
            *(cnts.entry(n).or_insert(0)) += 1;
        }
        
        let mut res = Vec::new();
        
        for (n, cnt) in cnts {
            for i in 0..cnt {
                if res.len() <= i {
                    res.push(Vec::new());
                }
                res[i].push(n);
            }
        }
        
        res
    }
}
