// with extra space
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] result = nums1;
        nums1 = Arrays.copyOfRange(result, 0, m);
        
        int n1p = 0, n2p = 0; // pointers to nums1 and nums2
        
        // iterate through nums1 and nums2, adding the lower number to result
        while(n1p + n2p < m + n) {
            if(n1p == m) result[n1p + n2p] = nums2[n2p++];
            else if(n2p == n) result[n1p + n2p] = nums1[n1p++];
            else if(nums1[n1p] < nums2[n2p]) result[n1p + n2p] = nums1[n1p++];
            else result[n1p + n2p] = nums2[n2p++];
        }
    }
}

// in-place
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        
        int n1p = m - 1, n2p = n - 1; // pointers to nums1 and nums2
        int i = m + n - 1; // insertion point
        
        // iterate through nums1 and nums2, adding the higher number to the end of result
        while(i >= 0) {
            if(n1p == -1) nums1[i--] = nums2[n2p--];
            else if(n2p == -1) return; // nums1's lower elements are already in the correct place
            else if(nums1[n1p] > nums2[n2p]) nums1[i--] = nums1[n1p--];
            else nums1[i--] = nums2[n2p--];
        }
    }
}
