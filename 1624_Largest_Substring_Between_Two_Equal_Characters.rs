use std::collections::HashMap;

impl Solution {
    pub fn max_length_between_equal_characters(s: String) -> i32 {
        let mut last_idx = HashMap::new();
        
        s.bytes().enumerate().map(|(i, e)| {
            (i - *last_idx.entry(e).or_insert(i)) as i32 - 1
        }).max().unwrap()
    }
}
