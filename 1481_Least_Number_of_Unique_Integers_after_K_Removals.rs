use std::collections::HashMap;

impl Solution {
    pub fn find_least_num_of_unique_ints(arr: Vec<i32>, k: i32) -> i32 {
        let mut cnts = HashMap::new();
        
        for &n in arr.iter() {
            *cnts.entry(n).or_insert(0) += 1;
        }
        
        let mut cnts: Vec<i32> = cnts.iter()
            .map(|(_, &cnt)| cnt)
            .collect();
        
        cnts.sort();
        
        cnts.len() as i32 - // total unique elements minus
        cnts.iter()         // number of eradicated elements
            .scan(0, |acc, &c| {
                *acc += c;
                Some(*acc)
            })
            .zip(0..)
            .filter(|(e, _)| e > &k)
            .map(|(_, i)| i)
            .next()
            .unwrap_or(arr.len() as i32)
    }
}
