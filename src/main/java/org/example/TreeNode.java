package org.example;

import java.util.HashMap;
import java.util.Map;

class  TreeNode {
    Map<Character, TreeNode> children = new HashMap<>();
    boolean isEndOfWord = false;
}
