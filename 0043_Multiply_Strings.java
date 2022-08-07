class Solution {
    private void addStrings(StringBuilder n1, StringBuilder n2) {
        // zero extend both (to prevent carry overflow)
        n1.insert(0, "0");
        n2.insert(0, "0");
        
        // zero extend the smallest
        while(n1.length() != n2.length()) {
            StringBuilder smaller = (n1.length() < n2.length()) ? n1 : n2;
            smaller.insert(0, "0");
        }
        
        int carry = 0;    
        for(int i = n1.length() - 1; i >= 0; i--) {
            int digit1 = n1.charAt(i) - '0';
            int digit2 = n2.charAt(i) - '0';
            int digitSum = digit1 + digit2 + carry;
            n1.setCharAt(i, (char) (digitSum % 10 + '0')); 
            carry = digitSum / 10;
        }
        
        // removing leading zeroes
        while(n1.length() > 1 && n1.charAt(0) == '0') n1.deleteCharAt(0);
        while(n2.length() > 1 && n2.charAt(0) == '0') n2.deleteCharAt(0);
    }
    
    public String multiply(String num1, String num2) {
        StringBuilder result = new StringBuilder("0");
        StringBuilder n1 = new StringBuilder(num1);
        StringBuilder n2 = new StringBuilder(num2);
        
        for(int i = num1.length() - 1; i >= 0; i--) {
            for(int j = 0; j < num1.charAt(i) - '0'; j++)
                addStrings(result, n2);
            n2.append('0');
        }
        
        return result.toString();
    }
}
