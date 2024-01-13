use std::collections::HashMap;

impl Solution {
    pub fn min_steps(s: String, t: String) -> i32 {
        let mut cnts = HashMap::new();
        
        for b in s.bytes() {
            *cnts.entry(b).or_insert(0) += 1;
        }
        
        for b in t.bytes() {
            match cnts.entry(b).or_insert(0) {
                n if *n > 0 => *n -= 1,
                _ => (),
            }
        }
        
        cnts.iter()
            .map(|(_, cnt)| cnt)
            .sum()
    }
}
