class Solution {
public:
    int rangeBitwiseAnd(int left, int right) {
        // for any bit that is in num2 (right) but not in num1 (left), 
        // all bits to the right of that must have been zero at some point
        
        int onlyInRight = right & ~left;
        
        // count the number of columns zeroed
        int numCols = 0;
        while(onlyInRight != 0) {
            numCols++;
            onlyInRight >>= 1;
        }
        
        return left & ((unsigned int) -1 << numCols);
    }
};
