impl Solution {
    pub fn judge_circle(moves: String) -> bool {
        moves.bytes().fold((0, 0), |(x, y), c| {
            match c {
                b'L' => (x - 1, y), 
                b'R' => (x + 1, y),
                b'U' => (x, y + 1),
                b'D' => (x, y - 1),
                _ => panic!(),
            }
        }) == (0, 0)
    }
}
