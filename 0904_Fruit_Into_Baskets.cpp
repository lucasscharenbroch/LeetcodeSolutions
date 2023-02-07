class Solution {
public:
    int totalFruit(vector<int>& fruits) {
        int lastType = fruits[0]; // holds the type of the last picked fruit
        int lastLastType = -1; // holds the second most recent picked fruit type
        int streak = 0, result = 0, numLastType = 0;
        
        for(int& f : fruits) {
            if(f != lastType && f != lastLastType && lastLastType != -1) {
                lastLastType = lastType;
                lastType = f;
                result = max(streak, result);
                streak = numLastType + 1;
                numLastType = 1;
            } else {
                if(f != lastType && f != lastLastType) { // lastLastType = -1
                    lastLastType = f;
                }
                
                if(f == lastType) numLastType++;
                else if(f == lastLastType) { // swap lastLastType into lastType to match
                    int temp = lastLastType;
                    lastLastType = lastType;
                    lastType = temp;
                    numLastType = 1;
                }
                
                streak++;
            }
        }
        
        return max(streak, result);
    }
};
