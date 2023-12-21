impl Solution {
    pub fn max_width_of_vertical_area(points: Vec<Vec<i32>>) -> i32 {
        let mut p: Vec<i32> = points
            .iter()
            .map(|v| v[0])
            .collect();
        
        p.sort();
        
        (0..(p.len() - 1))
            .map(|i| p[i + 1] - p[i])
            .max()
            .unwrap()
    }
}
