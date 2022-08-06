class Solution {
    private ArrayList<List<Integer>> solutions = new ArrayList<List<Integer>>();
    private void findCombinations(int[] candidates, int target, int start, ArrayList<Integer> combo) {
        if(target == 0) { // combo is a correct solution
            solutions.add(new ArrayList<Integer>(combo));
        } else if(target > 0) {
            if(start == candidates.length) return;
            findCombinations(candidates, target, start + 1, combo); // 0
            combo.add(candidates[start]);
            findCombinations(candidates, target - candidates[start], start, combo); // >= 1
            combo.remove(combo.size() - 1);
        }
    }
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        findCombinations(candidates, target, 0, new ArrayList<Integer>());
        return solutions;
    }
}
