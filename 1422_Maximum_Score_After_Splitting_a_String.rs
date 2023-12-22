impl Solution {
    pub fn max_score(s: String) -> i32 {
        let mut n1 = s.chars().filter(|&c| c == '1').count() as i32;
        
        s.chars()
            .take(s.len() - 1)
            .scan(0, |n0, c| {
                match c {
                    '0' => *n0 += 1,
                    _ => n1 -= 1,
                }
                Some(*n0 + n1)
            })
            .max()
            .unwrap()
    }
}
