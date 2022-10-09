class Solution {
    public String reverseWords(String s) {
        String result = "";
        int lastWordStart = s.length();
        for(int c = s.length() - 1; c >= 0; c--) {
            while(c >= 0 && s.charAt(c) != ' ') c--;
            if(c != lastWordStart - 1) {
                if(result.length() != 0) result += " ";
                result += s.substring(c + 1, lastWordStart);
            }
            lastWordStart = c;
        }
        return result;
    }
}
