class Solution {
    private ArrayList<List<Integer>> solutions = new ArrayList<List<Integer>>();
    
    private void findCombinations(int[] candidates, int target, int start, ArrayList<Integer> combo) {
        if(target == 0) {
            solutions.add(new ArrayList<Integer>(combo));
        } else if(target > 0) {
            if(start == candidates.length) return;
            int nextNonDuplicate;
            // advance nextNonDuplicate
            for(nextNonDuplicate = start + 1; nextNonDuplicate < candidates.length && 
                                              candidates[start] == candidates[nextNonDuplicate];
                                              nextNonDuplicate++);
            findCombinations(candidates, target, nextNonDuplicate, combo); // 0
            combo.add(candidates[start]);
            findCombinations(candidates, target - candidates[start], start + 1, combo); // 1+
            combo.remove(combo.size() - 1);
        }
    }
    
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates, 0, candidates.length); // sort so duplicates can be handled
        findCombinations(candidates, target, 0, new ArrayList<Integer>());
        return solutions;
    }
}
