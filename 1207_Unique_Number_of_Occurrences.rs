use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn unique_occurrences(arr: Vec<i32>) -> bool {
        let mut cnts = HashMap::new();
        
        for n in arr {
            *cnts.entry(n).or_insert(0) += 1;
        }
        
        let mut seen = HashSet::new();
        
        for cnt in  cnts.iter().map(|(_, v)| v) {
            if !seen.insert(cnt) {
                return false;
            }
        }
        
        true
    }
}
