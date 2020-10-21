package com.company.unused;

import com.company.model.State;

import java.util.List;

public class Node {
    State content;
    List<Node> children;
    State parent;

    public Node(State content, State parent) {
        this.content = content;
        this.parent = parent;
    }

    void addChild(Node child) {
        children.add(child);
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
}
