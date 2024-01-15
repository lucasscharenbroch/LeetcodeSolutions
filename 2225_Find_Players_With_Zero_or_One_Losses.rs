use std::collections::{HashMap, HashSet};
impl Solution {
    pub fn find_winners(matches: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut players = HashSet::new();
        let mut losses = HashMap::new();
        
        for m in matches {
            players.insert(m[0]);
            players.insert(m[1]);
            *losses.entry(m[1]).or_insert(0) += 1;
        }
        
        let mut res = vec![vec![]; 2];
        for &player in players.iter() {
            let n_losses = *losses.entry(player).or_insert(0);
            if n_losses == 0 {
                res[0].push(player);
            } else if n_losses == 1 {
                res[1].push(player);
            }
        }
        
        res[0].sort();
        res[1].sort();
        
        res
    }
}
