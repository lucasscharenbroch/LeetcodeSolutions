class Solution {
public:
    int compareVersion(string version1, string version2, int i1 = 0, int i2 = 0) {
        if(i1 == version1.length() && i2 == version2.length()) return 0;
        
        int num1 = atoi(&version1[i1]);
        int num2 = atoi(&version2[i2]);
        
        if(num1 > num2) return 1;
        if(num2 > num1) return -1;
        
        i1 = find(version1.begin() + i1, version1.end(), '.') - version1.begin();
        i2 = find(version2.begin() + i2, version2.end(), '.') - version2.begin();
        
        // skip over '.'
        if(i1 != version1.length()) i1++;
        if(i2 != version2.length()) i2++;
        
        return compareVersion(version1, version2, i1, i2);
    }
};
