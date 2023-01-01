class Solution {
    public boolean wordPattern(String pattern, String s) {
        String[] words = s.split(" ");
        if(words.length != pattern.length()) return false;
        
        HashMap<String, Character> wordToChar = new HashMap<>();
        HashMap<Character, String> charToWord = new HashMap<>();
        
        // verify that each letter corresponds to exactly one word.
        for(int i = 0; i < words.length; i++) {
            if(wordToChar.get(words[i]) == null) wordToChar.put(words[i], pattern.charAt(i));
            if(charToWord.get(pattern.charAt(i)) == null) charToWord.put(pattern.charAt(i), words[i]);
            
            if(wordToChar.get(words[i]) != pattern.charAt(i)) return false;
            if(!(charToWord.get(pattern.charAt(i)).equals(words[i]))) return false;
        }
        
        return true;
    }
}
