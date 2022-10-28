class Solution {
    public String convertToTitle(int columnNumber) {
        if(columnNumber <= 26) return String.valueOf((char)(columnNumber - 1 + 'A'));
        return convertToTitle((columnNumber - 1) / 26) + convertToTitle(((columnNumber - 1) % 26) + 1);
    }
}
