public class Solution {
    public int reverseBits(int n) {
        int result = 0;
        
        for(int bitPtr = 1; bitPtr != 0; bitPtr <<= 1) {
            result <<= 1;
            if((bitPtr & n) != 0) {
                result |= 1;
            }
        }
        
        return result;
    }
}
