class Solution {
    
    private HashMap<Character, Integer> romanDigits = new HashMap<>();
    
    {
        romanDigits.put('I', 1);    
        romanDigits.put('V', 5);    
        romanDigits.put('X', 10);    
        romanDigits.put('L', 50);    
        romanDigits.put('C', 100);    
        romanDigits.put('D', 500);    
        romanDigits.put('M', 1000);    
    }
    
             
    public int romanToInt(String s) {
        int result = 0;
        
        char currentDigit, nextDigit;
        
        for(int i = 0; i < s.length(); i++) {
            currentDigit = s.charAt(i);         
            
            if(i < s.length() - 1) {
                // check if smaller digit precedes a larger one
                nextDigit = s.charAt(i + 1);
                if(romanDigits.get(currentDigit) < romanDigits.get(nextDigit)) {
                    // if so, subtract the smaller digit    
                    result -= romanDigits.get(currentDigit);        
                    continue;
                }
            }    
            
            result += romanDigits.get(currentDigit);
        }
        
        return result;
    }
}
