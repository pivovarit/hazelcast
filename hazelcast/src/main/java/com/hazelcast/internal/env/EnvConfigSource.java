package com.hazelcast.internal.env;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

class EnvConfigSource implements ConfigSource {

    private static final String PREFIX = "HZ_";

    private final Map<String, String> entries = System.getenv().entrySet()
      .stream()
      .filter(e -> e.getKey().startsWith(PREFIX))
      .collect(toMap(e -> e.getKey()
        .replaceFirst(PREFIX, "")
        .replace("__", "-")
        .toLowerCase(), Map.Entry::getValue));

    @Override
    public ConfigNode get() {
        ConfigNode root = new ConfigNode("hazelcast");
        entries.entrySet().forEach(e -> ConfigSource.parseEntry(e, root));
        return root;
    }

    @Override
    public Map<String, String> entries() {
        return entries;
    }
}
