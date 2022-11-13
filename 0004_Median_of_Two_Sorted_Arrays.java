// O(n) approach
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

// Binary-Search
class Solution {
    // returns nums[partitionPoint-1] or INT_MIN, if that index is invalid.
    private int getLastInFirstPartition(int[] nums, int partitionPoint) {
        if(partitionPoint == 0) {
            return Integer.MIN_VALUE;
        }
        return nums[partitionPoint-1];
    }
    
    // returns nums[partitionPoint] or INT_MAX, if that index is invalid.
    private int getFirstInSecondPartition(int[] nums, int partitionPoint) {
        if(partitionPoint == nums.length) {
            return Integer.MAX_VALUE;
        }
        return nums[partitionPoint];
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // partition nums1 and nums2 such that neither lower partition has 
        // a higher element than either higher partition.
        
        // ensure that nums1 is the shortest of the two arrays (prevent out-of-bounds edgecases)
        if(nums1.length > nums2.length) {
            int[] temp = nums2;
            nums2 = nums1;
            nums1 = temp;
        }
        
        // binary search for correct partition.
        int low = 0;
        int high = nums1.length;
        int halfCount = (nums1.length + nums2.length) / 2;
        int partition1 = 0, partition2 = halfCount; // default when arr1.length == 0
        
        while(low < high) {
            int mid = (low + high) / 2;    
            partition1 = mid;
            partition2 = halfCount - mid;
            
            if(getLastInFirstPartition(nums1, partition1) > 
               getFirstInSecondPartition(nums2, partition2)) {
                high = mid - 1; // nums1 partition1 is too large
            } else if(getLastInFirstPartition(nums2, partition2) >
                      getFirstInSecondPartition(nums1, partition1)) {
                low = mid + 1; // nums1 partition1 is too small
            } else {
                high = low = mid;
                break; // correct partition found.
            }
        }
        partition1 = high;
        partition2 = halfCount - high; 
        
        // return median based on partition
        if((nums1.length + nums2.length) % 2 == 1) { // odd total
            return Math.min(getFirstInSecondPartition(nums1, partition1),
                            getFirstInSecondPartition(nums2, partition2));
        }
        // even total
        float highMid = Math.min(getFirstInSecondPartition(nums1, partition1),
                               getFirstInSecondPartition(nums2, partition2));
        float lowMid = Math.max(getLastInFirstPartition(nums1, partition1),
                              getLastInFirstPartition(nums2, partition2));
        return (highMid + lowMid) / 2;
    }
}

// Binary-Search
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int smallPartitionSize = (nums1.length + nums2.length + 1) / 2;
        boolean singleMedian = (nums1.length + nums2.length) % 2 == 1;
        int left = Math.max(0, smallPartitionSize - nums2.length);
        int right = Math.min(nums1.length, smallPartitionSize);
        
        // arr1 = [... X1 | Y1 ...]
        // arr2 = [... X2 | Y2 ...]
        // where | denotes the partition, and (index of X1 + 1) == | == index of Y1.
        // note that any of (X1, X2, Y1, and Y2) can be out of bounds.
        while(left <= right) {
            int pipe1 = (left + right) / 2; // let | = mid
            int pipe2 = smallPartitionSize - pipe1; // now |small partitions| == |large partitions| (+- 1)
            
            int X1 = (pipe1 == 0) ? Integer.MIN_VALUE : nums1[pipe1 - 1];
            int X2 = (pipe2 == 0) ? Integer.MIN_VALUE : nums2[pipe2 - 1];
            int Y1 = (pipe1 == nums1.length) ? Integer.MAX_VALUE : nums1[pipe1];
            int Y2 = (pipe2 == nums2.length) ? Integer.MAX_VALUE : nums2[pipe2];
            
            if(X1 <= Y2 && X2 <= Y1) { // Found a correct, equal-sized partition
                if(singleMedian) return Math.max(X1, X2);
                else return (Math.max(X1, X2) + Math.min(Y1, Y2)) / 2.0;
            } else if(X1 > Y2) right = pipe1 - 1;
            else left = pipe1 + 1;
        }
        
        return Double.NaN; // this line should never be reached
    }
}
