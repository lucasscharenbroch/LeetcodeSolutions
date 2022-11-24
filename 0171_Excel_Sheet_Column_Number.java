class Solution {
    public int titleToNumber(String columnTitle) {
        int result = 0;
        int multiplier = 1;
        
        for(int c = columnTitle.length() - 1; c >= 0; c--) {
            result += (columnTitle.charAt(c) - 'A' + 1) * multiplier;
            multiplier *= 26;
        }
        
        return result;
    }
}
