class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int totalG = 0, totalC = 0, currentStart = 0, currentTotal = 0;
        
        for(int i = 0; i < gas.length; i++) {
            if(currentTotal < 0) {
                // currentStart (or any index before i) cannot be a valid start
                currentStart = i;
                currentTotal = 0;
            }
            
            totalG += gas[i];
            totalC += cost[i];
            currentTotal += gas[i];
            currentTotal -= cost[i];
        }
        
        if(totalG < totalC) return -1; // if totalG < totalC there can't be a valid solution
        return currentStart;
    }
}
