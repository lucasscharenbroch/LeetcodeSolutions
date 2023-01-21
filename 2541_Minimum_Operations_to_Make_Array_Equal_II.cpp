class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long above = 0, below = 0; // above = total difference of nums1[i] - nums2[i] where nums1[i] > nums2[i]
                                        // below = total difference of nums2[i] - nums1[i] where nums1[i] < nums2[i]
        
        for(int i = 0; i < nums1.size(); i++) {
            if(k == 0) {
                if(nums1[i] != nums2[i]) return -1;
                continue;
            }
            if(nums1[i] > nums2[i]) above += nums1[i] - nums2[i];
            else below += nums2[i] - nums1[i];
        }
        
        if(k == 0) return 0;
        if(above != below || above % k != 0) return -1;
        return above / k;
    }
};
