class Solution {
public:
    int findKthPositive(vector<int>& arr, int k) {
        // binary search for last index where (arr[i] - 1) - i < k
        // (that is, last index such that arr[...i] has less than k missing ints)
        
        int l = -1, r = arr.size() - 1;
        while(l < r) {
            int mid = (l + r + 1) / 2;
            
            if(arr[mid] - 1 - mid < k) l = mid;
            else r = mid - 1;
        }
        
        if(l == -1) return k; // no such index: return k
        
        // return arr[i] + k - (arr[i] - 1 - i) (add k - num_missing to get kTH missing)
        return arr[l] + k - (arr[l] - 1 - l);
    }
};
