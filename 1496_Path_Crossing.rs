impl Solution {
    pub fn is_path_crossing(path: String) -> bool {
        let mut vis: std::collections::HashSet<(i32, i32)> = std::collections::HashSet::new();
        vis.insert((0, 0));
        
        let mut x = 0;
        let mut y = 0;
        
        for c in path.chars() {
            let (dx, dy) = match c {
                'N' => (0, 1),
                'S' => (0, -1),
                'E' => (1, 0),
                'W' => (-1, 0),
                _ => panic!(),
            };
            
            x += dx;
            y += dy;
            
            if vis.contains(&(x, y)) {
                return true;
            }
            vis.insert((x, y));
        }
        
        false
    }
}
