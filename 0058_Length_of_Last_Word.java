class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;
        int i;
        
        for(i = s.length() - 1; i >= 0 && s.charAt(i) == ' '; i--); // move i to end of last word
        for(; i >= 0 && s.charAt(i--) != ' '; count++); // count letters in last word
        
        return count;
    }
}
