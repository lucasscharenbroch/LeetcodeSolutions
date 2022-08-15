class Solution {
    private boolean isInt(String s) {
        int i = 0; // iterator for s
        
        if(i < s.length() && s.charAt(i) == '+' || s.charAt(i) == '-') i++; // skip sign
        
        if(i == s.length()) return false; // there must be 1 or more digits
        
        while(i < s.length() && Character.isDigit(s.charAt(i))) i++; // skip digits
        
        return i == s.length();
    }
    
    private boolean isDecimal(String s) {
        int i = 0; // iterator for s
        int numNonDigits = 0;
        
        if(i < s.length() && s.charAt(i) == '+' || s.charAt(i) == '-'){
            i++; // skip sign
            numNonDigits++;
        }
        
        while(i < s.length() && Character.isDigit(s.charAt(i))) i++; // skip digits
        
        if(i < s.length() && s.charAt(i) == '.') {
            i++; // skip decimal point
            numNonDigits++;
        }
        
        while(i < s.length() && Character.isDigit(s.charAt(i))) i++; // skip digits
        
        if(numNonDigits == i) return false; // a valid decimal number must have a digit
        
        if(i < s.length() && Character.toLowerCase(s.charAt(i)) == 'e') 
            return i + 1 < s.length() && isInt(s.substring(i + 1));
        else
            return i == s.length();
    }
    
    public boolean isNumber(String s) {
        return isInt(s) || isDecimal(s);
    }
}
