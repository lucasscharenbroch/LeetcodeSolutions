use std::collections::{HashMap, HashSet};

impl Solution {
    fn sorted_cnts(s: &str) -> Vec<i32> {
        let mut cnts = HashMap::new();
        
        for b in s.bytes() {
            *cnts.entry(b).or_insert(0) += 1;
        }
        
        let mut cnts = cnts.iter().map(|(_, &cnt)| cnt).collect::<Vec<i32>>();
        cnts.sort();
        cnts
    }
    
    fn unique_chars(s: &str) -> HashSet<u8> {
        let mut set = HashSet::new();
        
        for b in s.bytes() {
            set.insert(b);
        }
        
        set
    }
    
    pub fn close_strings(word1: String, word2: String) -> bool {
        Self::sorted_cnts(&word1) == Self::sorted_cnts(&word2) &&
        Self::unique_chars(&word1) == Self::unique_chars(&word2)
    }
}
