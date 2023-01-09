class DataStream {
public:
    int value, k;
    int numEqual = 0; // number of consecutive elements equal to value
    
    DataStream(int value, int k) {
        this->value = value;
        this->k = k;
    }
    
    bool consec(int num) {
        if(num == value) numEqual++;
        else numEqual = 0;
        
        return numEqual >= k;
    }
};

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream* obj = new DataStream(value, k);
 * bool param_1 = obj->consec(num);
 */
