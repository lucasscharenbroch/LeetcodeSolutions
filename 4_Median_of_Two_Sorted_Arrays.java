/* solution 1 (linear) */

class Solution {
    public double median(int[] nums) {
        if(nums.length % 2 == 1) { // there is a single middle number
            return (double) nums[nums.length / 2]; // return the middle number
        } 
        // return average of two middle numbers
        double halfAbove = nums[nums.length / 2];
        double halfBelow = nums[nums.length / 2 - 1];
        return (halfAbove + halfBelow) / 2;
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // zero-length edge-cases
        if(nums1.length == 0) {
            return median(nums2);
        } else if(nums2.length == 0) {
            return median(nums1);
        }
        
        int ptr1, ptr2;
        int halfCount = (nums1.length + nums2.length) / 2;
        double numHalfAbove, numHalfBelow; 
        
        // find numHalfAbove
        ptr1 = nums1.length - 1;
        ptr2 = nums2.length - 1;
        
        for(int i = 0; i < halfCount; i++) {
            if(ptr1 < 0) {
                ptr2--;        
            } else if(ptr2 < 0) {
                ptr1--; 
            } else if(nums1[ptr1] > nums2[ptr2]) {
                ptr1--;
            } else {
                ptr2 --;
            }
        }
        
        if(ptr1 < 0) {
            numHalfAbove = nums2[ptr2]; 
        } else if(ptr2 < 0) {
            numHalfAbove = nums1[ptr1];
        } else {
            numHalfAbove = nums1[ptr1] > nums2[ptr2] ? nums1[ptr1] : nums2[ptr2];
        }
        
        // find numHalfBelow
        ptr1 = ptr2 = 0;
        
        for(int i = 0; i < halfCount; i++) {
            if(ptr1 >= nums1.length) {
                ptr2++;
            } else if(ptr2 >= nums2.length) {
                ptr1++;
            } else if(nums1[ptr1] < nums2[ptr2]) {
                ptr1++;
            } else {
                ptr2 ++;
            }
        }
        
        if(ptr1 >= nums1.length) {
            numHalfBelow = nums2[ptr2];
        } else if (ptr2 >= nums2.length) {
            numHalfBelow = nums1[ptr1];
        } else {
        numHalfBelow = nums1[ptr1] < nums2[ptr2] ? nums1[ptr1] : nums2[ptr2];
        }
        
        // return average of (numHalfBelow, numHalfAbove)
        return (numHalfAbove + numHalfBelow) / 2;
    }
}
