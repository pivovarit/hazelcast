package com.hazelcast.internal.env;

import java.util.LinkedHashMap;
import java.util.Map;

class ConfigNode {
    private final ConfigNode parent;
    private final String name;
    private final Map<String, ConfigNode> children = new LinkedHashMap<>();
    private String value;

    public ConfigNode(String name) {
        this.name = name;
        this.parent = null;
    }

    public ConfigNode(String name, ConfigNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Map<String, ConfigNode> getChildren() {
        return children;
    }

    public ConfigNode getParent() {
        return parent;
    }
}
