class Solution {
    private int[] romanValues = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private String[] romanText = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD",
                                     "D", "CM", "M"};
    
    public String intToRoman(int num) {
        String result = "";
        
        while(num != 0) {
            for(int i = romanValues.length - 1; i >= 0; i--) {
                if(num >= romanValues[i]) {
                    num -= romanValues[i];
                    result += romanText[i];
                    break;
                }    
            }
        }
        
        return result;
    }
}
