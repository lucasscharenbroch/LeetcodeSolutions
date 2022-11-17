class Solution {
    public boolean canPartition(int[] nums) {
        int totalSum = 0;
        for(int num : nums) totalSum += num;
        
        if(totalSum % 2 == 1) return false; // odd sum can't have two equal partitions
        
        HashSet<Integer> partialSums = new HashSet<>();
        partialSums.add(0);
        
        for(int num : nums) {
            LinkedList<Integer> toAdd = new LinkedList<>();
            
            for(int partialSum : partialSums) {
                if(partialSum + num == totalSum / 2) return true;
                if(partialSum + num < totalSum / 2) toAdd.add(partialSum + num);
            }
            
            for(int ps : toAdd) partialSums.add(ps);
        }
        
        return false;
    }
}
