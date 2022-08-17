class Solution {
    public String simplifyPath(String path) {
        ArrayList<String> folderRecord = new ArrayList<>();
        
        Scanner pathScanner = new Scanner(path);
        pathScanner.useDelimiter("/");
        
        while(pathScanner.hasNext()) {
            String next = pathScanner.next();
            if(next.length() == 0 || next.equals(".")); // ignore "" and "."
            else if(next.equals("..")) {
                if(!folderRecord.isEmpty())
                    folderRecord.remove(folderRecord.size() - 1); // pop last
            } else folderRecord.add(next);
        }
        
        if(folderRecord.isEmpty()) return "/"; // empty path edgecase
        
        // assemble canonical path
        String canonicalPath = "";
        for(String folder : folderRecord) canonicalPath += '/' + folder;
        return canonicalPath;
    }
}
