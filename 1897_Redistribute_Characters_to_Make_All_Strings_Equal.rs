use std::collections::HashMap;

impl Solution {
    pub fn make_equal(words: Vec<String>) -> bool {
        // does each net-character-count divide words.len() ?
        let mut cnts = HashMap::new();
        
        for b in words.iter().flat_map(|s| s.bytes()) {
            *(cnts.entry(b).or_insert(0)) += 1;
        }
        
        cnts.iter().all(|(_, cnt)| cnt % words.len() == 0)
    }
}
