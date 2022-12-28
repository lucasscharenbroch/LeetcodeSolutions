class Solution {
public:
    int minStoneSum(vector<int>& piles, int k) {
        // always pick the largest pile
        
        priority_queue<int, vector<int>, less<int>> q;
        int result = 0;
        
        for(int& pile : piles) {
            q.push(pile);
            result += pile;
        }
        
        while(k--) {
            int current = q.top();
            q.pop();
            
            result -= current / 2;
            current -= current / 2;
            
            q.push(current);
        }
        
        return result;
    }
};
