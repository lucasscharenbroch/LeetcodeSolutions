class Solution {
    private String say(String s) {
        String result = "";    
        int count = 1;
        for(int i = 0; i < s.length(); i++) {
            if(i + 1 == s.length() || s.charAt(i) != s.charAt(i + 1)) {
                result += ((Integer) count).toString(); // count
                result += ((Character) s.charAt(i)).toString(); // say
                count = 1;
            } else {
                count++;
            }
        }
        return result;
    } 
    
    public String countAndSay(int n) {
        if(n == 1) return "1";
        return say(countAndSay(n - 1));
    }
}
