class Solution {
public:
    // returns total number of trips buses can make in %time% time
    long long num_trips(vector<int>& bus_times, long long time) {
        long long trips = 0;
        
        for(int& bus_time : bus_times) trips += time / bus_time;
        
        return trips;
    }
    
    long long minimumTime(vector<int>& bus_times, int totalTrips) {
        // binary-search-the-answer
        long long low = 0, high = bus_times[0] * (long long) totalTrips;
        
        while(low < high) {
            long long mid = (low + high) / 2;
            if(num_trips(bus_times, mid) >= totalTrips) high = mid;
            else low = mid + 1;
        }
        
        return low;
    }
};
