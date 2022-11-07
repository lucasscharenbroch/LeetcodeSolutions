class TimeMap {
    class TimestampValuePair {
        public String value;
        public int timestamp;
        
        public TimestampValuePair(String v, int t) {
            value = v;
            timestamp = t;
        }
    }
    
    HashMap<String, List<TimestampValuePair>> keyMap;

    public TimeMap() {
        keyMap = new HashMap<>();
    }
    
    public void set(String key, String value, int timestamp) {
        if(keyMap.get(key) == null) keyMap.put(key, new ArrayList<>());
        keyMap.get(key).add(new TimestampValuePair(value, timestamp));
    }
    
    public String get(String key, int timestamp) {
        if(keyMap.get(key) == null) return "";
        
        int i = binarySearch(keyMap.get(key), timestamp);
        if(i == -1) return "";
        else return keyMap.get(key).get(i).value;
    }
    
    private int binarySearch(List<TimestampValuePair> arr, int latestTimestamp) {
        if(arr.size() == 0) return -1;
        
        int left = 0, right = arr.size() - 1;
        
        while(left < right) {
            int mid = (left + right + 1) / 2;
            if(arr.get(mid).timestamp == latestTimestamp) return mid;
            if(arr.get(mid).timestamp < latestTimestamp) left = mid;
            else right = mid - 1;
        }
        
        return arr.get(left).timestamp <= latestTimestamp ? left : -1;
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */
