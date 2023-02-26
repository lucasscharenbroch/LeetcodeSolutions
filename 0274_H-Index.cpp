class Solution {
public:
    int hIndex(vector<int>& citations) {
        // binary-search
        sort(citations.begin(), citations.end());
        
        int low = 0, high = citations.size();
        
        while(low < high) {
            int mid = (low + high + 1) / 2;
            
            auto first_geq = lower_bound(citations.begin(), citations.end(), mid);
            int num_geq = citations.end() - first_geq;
            auto last_leq = upper_bound(citations.begin(), citations.end(), mid);
            int num_leq = last_leq - citations.begin() + 1;
            
            if(num_geq < mid) high = mid - 1;
            else if(num_leq < citations.size() - mid) low = mid + 1;
            else low = mid;
        }
        
        return low;
    }
};
