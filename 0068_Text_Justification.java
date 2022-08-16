class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        int numWordChars = 0;
        int numWords = 0;
        String[] wordQueue = new String[maxWidth];
        ArrayList<String> result = new ArrayList<>();
        
        for(int i = 0; i < words.length; i++) {
            if(numWordChars + numWords + words[i].length() > maxWidth) { // words[i] to large for this line
                // justify and add filled line to result
                result.add(leftRightJustify(wordQueue, numWords, maxWidth, maxWidth - numWordChars));
                numWords = 0;
                numWordChars = 0;
            }
            
            // add words[i] to queue
            numWordChars += words[i].length();
            wordQueue[numWords++] = words[i];
            if(i == words.length - 1) { // last word, therefore last line, left justify it.
                result.add(leftJustify(wordQueue, numWords, maxWidth));
            }
        }
        
        return result;
    }
    
    private String leftJustify(String[] wordQueue, int numWords, int width) {
        char[] line = new char[width];
        int i = 0;
        
        for(int w = 0; w < numWords; w++) { // for each word
            // add the word, then a space
            for(int c = 0; c < wordQueue[w].length(); c++) {
                line[i++] = wordQueue[w].charAt(c);
            }
            if(w != numWords - 1) line[i++] = ' ';
        }
        
        while(i < width) line[i++] = ' '; // add remaining spaces
        
        return String.valueOf(line);
    }
    
    private String leftRightJustify(String[] wordQueue, int numWords, int width,
                                    int numSpaces) {
        char[] line = new char[width];
        int i = 0;
        
        for(int w = 0; w < numWords; w++) {
            for(int c = 0; c < wordQueue[w].length(); c++) // add word
                line[i++] = wordQueue[w].charAt(c);
            
            if(w != numWords - 1) { // add spaces
                int numSpacesToAdd;
                int remainingWords = numWords - w - 1;
                if(numSpaces % remainingWords == 0) numSpacesToAdd = numSpaces / remainingWords;
                else numSpacesToAdd = numSpaces / remainingWords + 1;
                
                for(int s = 0; s < numSpacesToAdd; s++) line[i++] = ' ';
                numSpaces -= numSpacesToAdd;
            }
            
        }
        
        while(i < width) line[i++] = ' '; // add remaining spaces 
        
        return String.valueOf(line);
    }
}
