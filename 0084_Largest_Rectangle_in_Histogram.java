// recursive solution (best case: O(nlgn), worst case: O(n^2))
class Solution {
    private int solve(int[] heights, int start, int end) {
        if(end <= start) return 0; // base case (0-length array)
        
        // find minimum value and its index
        int minVal = heights[start];
        int minIndex = start;
        
        for(int i = start + 1; i < end; i++) {
            if(heights[i] < minVal) {
                minVal = heights[i];
                minIndex = i;
            }
        }
        
        // the solution is one of the following:
        // 1.) it includes minIndex (therefore spans the entire array)
        // 2.) it does not include min index
        //     a) it is to the left of min index 
        //     b.) it is to the right
        // recursively find 2a, and 2b, then return the max of 1, 2a, and 2b
        return Math.max(minVal * (end - start), Math.max(solve(heights, start, minIndex),
                                                         solve(heights, minIndex + 1, end)));
    }
    
    public int largestRectangleArea(int[] heights) {
        return solve(heights, 0, heights.length);
    }
}

// stack solution (O(n))
class Solution {
    public int largestRectangleArea(int[] heights) {
        // stack remembers the effective heights and frequencies of previously visited bars
        Stack<Pair<Integer, Integer>> stack = new Stack<>();
        int maxArea = 0;
        
        for(int h = 0; h < heights.length; h++) {
            if(h == 0 || stack.peek().getKey() < heights[h]) {
                // if stack is empty or the top value is less than heights[h]
                stack.push(new Pair<>(heights[h], 1));
            } else if(stack.peek().getKey() == heights[h]) {
                // update the frequency of the top of the stack
                int newFrequency = 1 + stack.pop().getValue();
                stack.push(new Pair<>(heights[h], newFrequency));
            } else {
                // the height on the top of the stack is taller than 
                // heights[h]. calculate the max-sized rectangle using that height
                // (height * frequency), then pop it off the stack, adding its
                // frequency to the next highest value (on the top of the stack)
                
                int poppedFrequency;
                while(true) {
                    maxArea = Math.max(maxArea, stack.peek().getKey() * stack.peek().getValue());
                    poppedFrequency = stack.pop().getValue();
                    
                    if(stack.isEmpty() || stack.peek().getKey() < heights[h]) break;
                    
                    // add poppedFrequency to the pair at the top of the stack
                    int topHeight = stack.peek().getKey();
                    int topValue = stack.pop().getValue();
                    stack.push(new Pair<>(topHeight, topValue + poppedFrequency));
                }
                
                stack.push(new Pair<>(heights[h], poppedFrequency + 1)); // add heights[h] to stack
            }
        }
        
        // empty the stack, adjusting frequencies and maxArea accordingly
        while(!stack.isEmpty()) {
            maxArea = Math.max(maxArea, stack.peek().getKey() * stack.peek().getValue());
            int poppedFrequency = stack.pop().getValue();
            if(!stack.isEmpty()) {
                // add poppedFrequency to the frequency at the top of the stack
                poppedFrequency += stack.peek().getValue();
                stack.push(new Pair<>(stack.pop().getKey(), poppedFrequency));
            }
        }
        
        return maxArea;
    }
}
