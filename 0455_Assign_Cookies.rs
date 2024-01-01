impl Solution {
    pub fn find_content_children(gs: Vec<i32>, ss: Vec<i32>) -> i32 {
        let (mut gs, mut ss) = (gs.clone(), ss.clone());
        gs.sort();
        ss.sort();
        
        let mut res = 0;
        
        // satisfy least greedy child first, with minimal-sized cookie.
        
        let (mut i, mut j) = (0, 0);
        
        while i < gs.len() && j < ss.len() {
            if gs[i] <= ss[j] {
                i += 1;
            }
            j += 1;
        }
        
        i as i32
    }
}
