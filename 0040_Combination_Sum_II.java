class Solution {
    private void findCombinations(int[] candidates, int start, int target, 
                                  List<List<Integer>> solutions, List<Integer> combo) {
        if(target == 0) { // found a solution
            solutions.add(new ArrayList<Integer>(combo));
        } else if(target > 0) {
            // each iteration represents a 0-use, each recursion represents a 1-use
            for(int i = start; i < candidates.length; i++) {
                // do not recurse with duplicates
                if(i != start && candidates[i] == candidates[i - 1]) continue;
                combo.add(candidates[i]);
                findCombinations(candidates, i + 1, target - candidates[i], solutions, combo);
                combo.remove(combo.size() - 1);
            }    
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates, 0, candidates.length); // for duplicate-checking
        
        List<List<Integer>> solutions = new ArrayList<List<Integer>>();    
        findCombinations(candidates, 0, target, solutions, new ArrayList<Integer>()); 
        return solutions;
    }
}
