class LFUCache {
public:
    int size = 0;
    int operationId = 0; // assigns each operation a number (to find LRU)
    int capacity;
    unordered_map<int, int> keyToValue;
    unordered_map<int, pair<int, int>> keyToPriority;
    map<pair<int, int>, int> priorityToKey;
    
    LFUCache(int capacity) {
        this->capacity = capacity;
    }
    
    int get(int key) {
        if(keyToPriority.find(key) == keyToPriority.end()) return -1;
        pair<int, int> priority = keyToPriority[key];
        
        priorityToKey.erase(priority);
        priority.first++;
        priority.second = operationId++;
        priorityToKey[priority] = key;
        keyToPriority[key] = priority;
            
        return keyToValue[key];
    }
    
    void put(int key, int value) {
        if(capacity == 0) return;
        if(keyToPriority.find(key) == keyToPriority.end()) size++;
        if(size > capacity) { // remove LFU key
            size--;
            int lfuKey = priorityToKey.begin()->second;
            keyToPriority.erase(lfuKey);
            priorityToKey.erase(priorityToKey.begin());
        }
        
        keyToValue[key] = value;
        
        pair<int, int> priority;
        
        if(keyToPriority.find(key) != keyToPriority.end()) {
            priority = keyToPriority[key];
            priorityToKey.erase(priority);
            priority.first++;
            priority.second = operationId++;
        } else {
            priority.first = 1;
            priority.second = operationId++;
        }
        
        priorityToKey[priority] = key;
        keyToPriority[key] = priority;
    }
};

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache* obj = new LFUCache(capacity);
 * int param_1 = obj->get(key);
 * obj->put(key,value);
 */
