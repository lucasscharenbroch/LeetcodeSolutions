impl Solution {
    pub fn min_operations(s: String) -> i32 {
        let mut even_ones = 0; // cost to make all even indices '1' and all odd indices '0'
        let mut odd_ones = 0;
        
        s.chars().enumerate()
            .fold((), |_, (i, c)| {
                match (i % 2, c) {
                    (1, '1') => even_ones += 1,
                    (0, '1') => odd_ones += 1,
                    (0, '0') => even_ones += 1,
                    (1, '0') => odd_ones += 1,
                    _ => panic!(),
                }
            });
        
        std::cmp::min(even_ones, odd_ones)
    }
}
