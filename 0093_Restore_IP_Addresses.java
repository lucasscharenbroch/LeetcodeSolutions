class Solution {
    private boolean isValidNumber(String s) {
        int n; // will hold s's int value
        
        // verify that s has no non-digit characters
        try {
            n = Integer.parseInt(s);
        } catch (NumberFormatException e) {
            return false;
        }
        
        // verify that n <= 255 and has no leading zeroes
        return n <= 255 && ((Integer) n).toString().equals(s);
    }
    
    private List<String> restoreIpAddresses(String s, int numDots) {
        List<String> result = new ArrayList<String>();
        
        if(numDots == 0) {
            // if s is valid, add it to result, otherwise return {}
            if(isValidNumber(s))
                result.add(s);
            
            return result;
        } else {
            // check if substrings of length 1-3 at the beginning of s are valid
            for(int l = 1; l <= 3; l++) {  
                if(l <= s.length() && isValidNumber(s.substring(0, l))) {
                    // for each valid interpretation:
                    // recursively add the valid ip addresses to result
                    List<String> subresult = restoreIpAddresses(s.substring(l), numDots - 1);
                    
                    for(String sr : subresult) {
                        result.add(s.substring(0, l) + "." + sr);
                    }
                }
            }
        }
        
        return result;
    }
    
    public List<String> restoreIpAddresses(String s) {
        return restoreIpAddresses(s, 3);
    }
}
