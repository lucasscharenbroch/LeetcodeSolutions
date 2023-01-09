class Solution {
public:
    long long maxKelements(vector<int>& nums, int k) {
        // greedy: always choose the largest num
      
        priority_queue<int> heap(nums.begin(), nums.end());
        long long result = 0;
        
        while(k--) {
            int current = heap.top();
            heap.pop();
            
            result += current;
            heap.push(ceil((double) current / 3.0));
        }
            
        return result;
    }
};
