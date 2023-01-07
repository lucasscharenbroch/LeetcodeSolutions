class Solution {
public:
    string largestNumber(vector<int>& nums) {
        vector<string> stringArray;
        
        for(int& num : nums) stringArray.push_back(to_string(num));
        
        sort(stringArray.begin(), stringArray.end(), 
             [](string& a, string& b) { return (a + b) > (b + a); });
        
        if(stringArray[0] == "0") return "0";
        
        string result;
        
        for(string& str : stringArray) result += str;
        
        return result;
    }
};
