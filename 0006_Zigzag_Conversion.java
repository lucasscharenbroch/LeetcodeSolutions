class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1) // base-case
            return s;
        
        StringBuilder result = new StringBuilder(s.length());
        
        for(int i = 0; i < numRows; i++) {
            int current = i; // start at first char in row
            
            int numBelow = (numRows - i - 1) * 2 - 1; 
            int numAbove = i * 2 - 1;
            
            // top and bottom row only have one increment  
            if(i == 0) {
                numAbove = numBelow; 
            } else if(i == numRows - 1) {
                numBelow = numAbove;
            }
               
            
            while(true) {
                if(current >= s.length())
                    break;
                result.append(s.charAt(current));
                current += numBelow + 1;
                
                if(current >= s.length())
                    break;
                result.append(s.charAt(current));
                current += numAbove + 1;
            }
            
        }
        
        return result.toString();
    }
}
