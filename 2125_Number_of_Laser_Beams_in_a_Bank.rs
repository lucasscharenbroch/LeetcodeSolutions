impl Solution {
    pub fn number_of_beams(bank: Vec<String>) -> i32 {
        bank.iter()
            .map(|s| s.bytes().map(|b| (b - b'0') as i32).sum::<i32>())
            .filter(|&n| n != 0)
            .collect::<Vec<_>>()
            .windows(2)
            .map(|w| w.iter().product::<i32>())
            .sum()
    }
}
