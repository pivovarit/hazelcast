package com.hazelcast.internal.env;

import java.util.Map;

interface ConfigSource {
    ConfigNode get();

    Map<String, String> entries();

    static void parseEntry(Map.Entry<String, String> entry, ConfigNode root) {
        ConfigNode last = root;
        for (String s : entry.getKey().split("_")) {
            ConfigNode node = last.getChildren().get(s);
            if (node == null) {
                node = new ConfigNode(s);
                last.getChildren().put(s, node);
            }
            last = node;
        }

        last.setValue(entry.getValue());
    }
}
