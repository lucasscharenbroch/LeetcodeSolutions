class Allocator {
public:
    bool *mem; // array of memory slots, true => slot is in use
    int capacity;
    unordered_map<int, vector<pair<int, int>>> idMap; // idMap[i] => list of [start, size] pairs w/ id i
        
    Allocator(int n) {
        mem = new bool[n] {false};
        capacity = n;
    }
    
    ~Allocator() {
        delete [] mem;
    }
    
    int allocate(int size, int mID) {
        int freeSpaces = 0;
        for(int i = 0; i < capacity; i++) {
            if(mem[i] == false) freeSpaces++;
            else freeSpaces = 0;
            
            if(freeSpaces == size) {
                for(int j = i - size + 1; j <= i; j++) mem[j] = true;
                idMap[mID].push_back(pair<int, int> (i - size + 1, size));
                return i - size + 1;
            }
        }
        
        return -1;
    }
    
    int free(int mID) {
        int cellsFreed = 0;
        
        for(pair<int, int>& entry : idMap[mID]) { // free each block associated with this id
            for(int i = entry.first; i < entry.first + entry.second; i++) {
                mem[i] = false;
                cellsFreed++;
            }
        }
        idMap.erase(mID); // remove the record of these blocks
        
        return cellsFreed;
    }
};

/**
 * Your Allocator object will be instantiated and called as such:
 * Allocator* obj = new Allocator(n);
 * int param_1 = obj->allocate(size,mID);
 * int param_2 = obj->free(mID);
 */
