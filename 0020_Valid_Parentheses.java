class Solution {
    private char correspondingParenthesis(char p) {
        switch(p) {
            case ')':
                return '(';
            case ']':
                return '[';
            default:
                return '{';
        }
    }
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        char c;
        
        for(int i = 0; i < s.length(); i++) {
            switch(c = s.charAt(i)){
                case '(': case '[': case '{':
                    stack.push(c);
                    break;
                case ')': case ']': case '}':
                    if(stack.empty() || stack.pop() != correspondingParenthesis(c)) {
                        return false;
                    }
                    break;
            }
        }
        return stack.empty();
    }
}
