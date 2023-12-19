impl Solution {
    pub fn image_smoother(img: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut res = vec![vec![0; img[0].len()]; img.len()];

        for i in 0..img.len() {
            for j in 0..img[0].len() {
                let mut sum = 0;
                let mut cnt = 0;
                
                for di in -1..=1 {
                    for dj in -1..=1 {
                        let (ti, tj) = (i + (di as usize), j + (dj as usize));
                        if ti < 0 || ti < 0 || ti >= img.len() || tj >= img[i].len() {
                            continue
                        }
                        cnt += 1;
                        sum += img[ti][tj];
                    }
                }
                
                res[i][j] = sum / cnt;
            }
        }
        
        res
    }
}
