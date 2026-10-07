import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    public List<String> removeInvalidParentheses(String s) {
        int leftToRemove = 0, rightToRemove = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                leftToRemove++;
            } else if (c == ')') {
                if (leftToRemove > 0) {
                    leftToRemove--;
                } else {
                    rightToRemove++;
                }
            }
        }
        
        Set<String> resultSet = new HashSet<>();
        dfs(s, 0, leftToRemove, rightToRemove, 0, 0, new StringBuilder(), resultSet);
        return new ArrayList<>(resultSet);
    }
    
    private void dfs(String s, int index, int l, int r, int lCount, int rCount, StringBuilder sb, Set<String> res) {
        if (index == s.length()) {
            if (l == 0 && r == 0) {
                res.add(sb.toString());
            }
            return;
        }
        
        char currentChar = s.charAt(index);
        int len = sb.length();
        
        if (rCount > lCount) {
            return;
        }
        
        if (currentChar == '(' && l > 0) {
            dfs(s, index + 1, l - 1, r, lCount, rCount, sb, res);
        }
        if (currentChar == ')' && r > 0) {
            dfs(s, index + 1, l, r - 1, lCount, rCount, sb, res);
        }
        
        sb.append(currentChar);
        if (currentChar == '(') {
            dfs(s, index + 1, l, r, lCount + 1, rCount, sb, res);
        } else if (currentChar == ')') {
            dfs(s, index + 1, l, r, lCount, rCount + 1, sb, res);
        } else {
            dfs(s, index + 1, l, r, lCount, rCount, sb, res);
        }
        sb.setLength(len);
    }
}