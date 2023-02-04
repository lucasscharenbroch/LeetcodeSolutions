class MyStack {
public:
    queue<int> q;
    int size = 0;
    
    void push(int x) {
        q.push(x);
    }
    
    int pop() {
        int s = q.size();
        for(int i = 0; i < s - 1; i++) {
            q.push(q.front());
            q.pop();
        }
        
        int t = q.front(); // value on top
        q.pop();
        return t;
    }
    
    int top() {
        int t; // value on top
        push(t = pop());
        return t;
    }
    
    bool empty() {
        return q.empty();
    }
};

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack* obj = new MyStack();
 * obj->push(x);
 * int param_2 = obj->pop();
 * int param_3 = obj->top();
 * bool param_4 = obj->empty();
 */
