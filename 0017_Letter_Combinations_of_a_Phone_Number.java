class Solution {
    private HashMap<Character, String> digitLetters = new HashMap<>();
    {
        digitLetters.put('2', "abc");        
        digitLetters.put('3', "def");        
        digitLetters.put('4', "ghi");        
        digitLetters.put('5', "jkl");        
        digitLetters.put('6', "mno");        
        digitLetters.put('7', "pqrs");        
        digitLetters.put('8', "tuv");        
        digitLetters.put('9', "wxyz");        
    } 
    
    public List<String> letterCombinations(String digits) {
        ArrayList<String> combinations = new ArrayList<>();
        
        if(digits.length() == 0) {
            return combinations;
        } else if(digits.length() == 1) {
            // return each letter
            String letters = digitLetters.get(digits.charAt(0));
            for(int i = 0; i < letters.length(); i++) {
                combinations.add("" + letters.charAt(i));    
            }    
        } else {
            // recursively call letterCombinations, then add each possible 
            //first letter to each possible sub-combination
            List<String> subCombinations = letterCombinations(digits.substring(1));
            String firstLetters = digitLetters.get(digits.charAt(0));
            
            for(String subCombo : subCombinations) {
                for(int i = 0; i < firstLetters.length(); i++) {
                    combinations.add(firstLetters.charAt(i) + subCombo);
                }    
            }
        }
        
        return combinations;
    }
}
