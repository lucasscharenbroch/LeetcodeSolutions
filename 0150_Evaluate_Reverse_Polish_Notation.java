class Solution {
    ArrayList<Integer> stack = new ArrayList<>();
    
    private boolean isInt(String s) {
        char firstChar = s.charAt(0);
        if(firstChar >= '0' && firstChar <= '9') return true;
        else if(firstChar == '-') return s.length() > 1;
        return false;
    }
    
    private void push(int n) {
        stack.add(n);
    }
    
    private int pop() {
        return stack.remove(stack.size() - 1);
    }
    
    public int evalRPN(String[] tokens) {
        
        for(String t : tokens) {
            if(isInt(t)) {
                push(Integer.parseInt(t));
            } else {
                switch(t.charAt(0)) {
                    case '+':
                        push(pop() + pop());
                        break;
                    case '-':
                        push(-pop() + pop());
                        break;
                    case '*':
                        push(pop() * pop());
                        break;
                    case '/': {
                        int divisor = pop();
                        push(pop() / divisor);
                        break;
                    }
                }
            }
        }
        
        return stack.get(0);
    }
}
