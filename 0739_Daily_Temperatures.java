class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] result = new int[temperatures.length];
        ArrayList<Integer> stack = new ArrayList<>(); // stores indices of increasing temperatures
        
        for(int i = temperatures.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && temperatures[i] >= temperatures[stack.get(stack.size() - 1)])
                stack.remove(stack.size() - 1); // pop the top temperature until it is warmer
            
            if(stack.isEmpty()) result[i] = 0;
            else result[i] = stack.get(stack.size() - 1) - i;
            
            stack.add(i);
        }
        
        return result;
    }
}
