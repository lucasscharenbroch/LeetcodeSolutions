class Solution {
    // globals
    private int sum = 0;
    private ArrayList<Integer> combination = new ArrayList<Integer>();
    private ArrayList<List<Integer>> solutions = new ArrayList<List<Integer>>();
    
    // adds all possible combinations of candidates (excluding indices left of start) 
    // that add up to target - sum
    private void findCombinations(int[] candidates, int start, int target) {
        if(start == candidates.length) return;
        int c = candidates[start];
        int n = 0; // the number of times candidates[start] is used
        do {
            if(sum == target) {
                solutions.add((List<Integer>) combination.clone());
                break;
            } else if(sum < target) {
                findCombinations(candidates, start + 1, target);
            }
            combination.add(c);
            sum += c;
            n++;
        } while(sum <= target);
        
        // remove footprint on sum and combiantion
        sum -= n * c;
        while(n-- > 0) {
            combination.remove(combination.size() - 1);    
        }
    }
    
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // find the combinations starting with each candidate
        for(int i = 0; i < candidates.length; i++) {
            int c = candidates[i];
            combination.add(c);
            sum += c;
            findCombinations(candidates, i, target);
            sum -= c;
            combination.remove(combination.size() - 1);
        }
        
        return solutions;
    }
}
