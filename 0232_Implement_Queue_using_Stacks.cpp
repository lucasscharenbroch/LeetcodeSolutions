// Naive O(n)
class MyQueue {
public:
    vector<int> stack;
    
    void push(int x) {
        stack.push_back(x);
    }
    
    int pop() {
        // pop all elements from stack into temp, keep the last
        // element to be popped (first), then push the rest back.
        vector<int> temp;
        while(stack.size() > 1) {
            temp.push_back(stack[stack.size() - 1]);
            stack.pop_back();
        }
        int front = stack[0];
        stack.pop_back();
        
        while(temp.size()) {
            stack.push_back(temp[temp.size() - 1]);
            temp.pop_back();
        }
        return front;
    }
    
    int peek() {
        vector<int> temp;
        while(stack.size() > 1) {
            temp.push_back(stack[stack.size() - 1]);
            stack.pop_back();
        }
        int front = stack[0];
        
        while(temp.size()) {
            stack.push_back(temp[temp.size() - 1]);
            temp.pop_back();
        }
        return front;
    }
    
    bool empty() {
        return !stack.size();
    }
};

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue* obj = new MyQueue();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->peek();
 * bool param_4 = obj->empty();
 */
