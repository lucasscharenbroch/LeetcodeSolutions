class MinStack {
    private ArrayList<Integer> stack = new ArrayList<>();
    private ArrayList<Integer> minStack = new ArrayList<>();
    int size = 0;

    public MinStack() {
    }
    
    public void push(int val) {
        stack.add(val);
        if(size == 0) {
            minStack.add(val);
        } else {
            minStack.add(Math.min(val, minStack.get(size - 1)));
        }
        size++;
    }
    
    public void pop() {
        stack.remove(size - 1);
        minStack.remove(--size);
    }
    
    public int top() {
        return stack.get(size - 1);
    }
    
    public int getMin() {
        return minStack.get(size - 1);
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
