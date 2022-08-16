class Solution {
    public String addBinary(String a, String b) {
        StringBuilder result = new StringBuilder(Math.max(a.length(), b.length()) + 1);
        
        int iA = 0, iB = 0; // iterators for a and b, respectively
        int carry = 0;
        
        while(iA < a.length() || iB < b.length()) {
            int sum = (iA < a.length() ? a.charAt(a.length() - iA - 1) - '0' : 0) + 
                      (iB < b.length() ? b.charAt(b.length() - iB - 1) - '0' : 0) + carry;
            
            carry = sum / 2;
            sum %= 2;
            
            result.append((char) ((int) '0' + sum));
            iA++; iB++;
        }
        
        if(carry != 0) result.append('1');
        
        return result.reverse().toString();
    }
}
