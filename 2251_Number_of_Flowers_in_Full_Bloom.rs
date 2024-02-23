use std::cmp::Ordering;

impl Solution {
    pub fn full_bloom_flowers(flowers: Vec<Vec<i32>>, people: Vec<i32>) -> Vec<i32> {
        let mut openers = flowers.iter().map(|i| i[0]).collect::<Vec<_>>();
        let mut closers = flowers.iter().map(|i| i[1]).collect::<Vec<_>>();
        
        openers.sort();
        closers.sort();
        
        people.iter()
            .map(|i| {
                let n_started = match openers.binary_search_by(|x| if x > i { Ordering::Greater } else { Ordering::Less }) {
                    Err(j) => j,
                    _ => panic!(),
                };

                let n_ended = match closers.binary_search_by(|x| if x >= i { Ordering::Greater } else { Ordering::Less }) {
                    Err(j) => j,
                    _ => panic!(),
                };

                (n_started - n_ended) as i32
            }).collect()
    }
}
