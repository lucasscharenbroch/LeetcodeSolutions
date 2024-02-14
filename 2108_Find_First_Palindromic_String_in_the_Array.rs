impl Solution {
    pub fn first_palindrome(words: Vec<String>) -> String {
        fn is_palindrome(s: &&String) -> bool {
            s.bytes().eq(s.bytes().rev())
        }
        
        words.iter()
            .filter(is_palindrome)
            .next()
            .unwrap_or(&String::from(""))
            .clone()
    }
}
