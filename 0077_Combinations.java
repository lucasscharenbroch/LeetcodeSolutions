class Solution {
    private List<List<Integer>> combine(int n, int k, int i) {
        LinkedList<List<Integer>> combos = new LinkedList<>();
        if(k == 0 || i > n) return combos;
        else if(k == 1) { // only one number
            // for each number, i to n, add it as a new combo
            for(; i <= n; i++) {
                combos.add(new LinkedList<Integer>(Arrays.asList(i)));
            }
        } else {
            // for each starting number, i to n, add all possible combinations starting with i
            for(; i <= n; i++) {
                List<List<Integer>> subcombos = combine(n, k - 1, i + 1);
                for(List<Integer> subcombo : subcombos) {
                    subcombo.add(0, i);
                    combos.add(subcombo);
                }
            }
        }
        
        return combos;
    }
    public List<List<Integer>> combine(int n, int k) {
        return combine(n, k, 1);
    }
}
