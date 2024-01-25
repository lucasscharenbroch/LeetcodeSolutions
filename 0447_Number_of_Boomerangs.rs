impl Solution {
    fn dist(a: &Vec<i32>, b: &Vec<i32>) -> f64 {
        (((a[0] - b[0]).pow(2) + (a[1] - b[1]).pow(2)) as f64).sqrt()
    }
    
    pub fn number_of_boomerangs(points: Vec<Vec<i32>>) -> i32 {
        let n = points.len();
        let mut res = 0;
        
        for i in 0..n {
            let mut v: Vec<f64> = vec![];
            for j in 0..n {
                if i == j { continue; }
                
                let d = Self::dist(&points[i], &points[j]);
                
                for &e in v.iter() {
                    if e == d {
                        res += 2;
                    }
                }
                
                v.push(d);
            }
        }
        
        res
    }
}
