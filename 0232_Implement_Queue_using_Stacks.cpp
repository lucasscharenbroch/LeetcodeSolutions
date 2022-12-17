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

// a(n)
class MyQueue {
public:
    vector<int> in, out; // elements are stored in given order in %in%, and reverse order in %out%.
                         // %out% only needs to be updated after it is emptied, and it can
                         // be filled with all elements in the stack, which leads to amortized 
                         // constant time.
    int size = 0;
    
    void push(int x) {
        in.push_back(x);
        size++;
    }
    
    int pop() {
        int front = peek();
        out.pop_back();
        size--;
        return front;
    }
    
    int peek() {
        if(!out.size()) { // need to re-populate %out%: 
                          // copy all elements from %in% into %out% in reverse order
            for(int s = size; s > 0; s--) {
                out.push_back(in[in.size() - 1]);
                in.pop_back();
            }
            
            while(in.size()) in.pop_back(); // remove already-popped elements from %in%
            
            // copy 
            vector<int> temp = out;
            while(temp.size()) {
                in.push_back(temp[temp.size() - 1]);
                temp.pop_back();
            }
        }
        
        return out[out.size() - 1];
    }
    
    bool empty() {
        return size == 0;
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
