impl Solution {
    pub fn colored_cells(n: i32) -> i64 {
        // sum of the first n terms in the squence
        // 1, 4, 8, 12, 16, ...
        
        // 1 + 4 + 8 + 12 + ... + 4n
        // 1 + 4(1 + 2 + 3 + ... + n)
        // 1 + 4(n*(n + 1)/2)
        
        let n: i64 = (n - 1).into();
        
        1 + 4 * n * (n + 1) / 2
    }
}
