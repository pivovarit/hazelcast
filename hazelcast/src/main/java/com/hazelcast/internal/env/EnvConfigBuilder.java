package com.hazelcast.internal.env;

import com.hazelcast.config.Config;
import com.hazelcast.internal.config.MemberDomConfigProcessor;
import com.hazelcast.logging.ILogger;
import com.hazelcast.logging.Logger;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnvConfigBuilder {

    private static final ILogger LOGGER = Logger.getLogger(EnvConfigBuilder.class);

    public Config overwrite(Config config) {

        ConfigNode envConfigEntries = new EnvConfigSource().get();
        if (envConfigEntries.getChildren().size() > 0) {
            LOGGER.info("Discovered config overrides in environment variables: \n" + formatAsYaml(envConfigEntries, "hazelcast") + "\n");
        }

        try {
            new MemberDomConfigProcessor(true, config).buildConfig(new ElementAdapter(envConfigEntries));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return config;
    }

    public static String formatAsYaml(ConfigNode root, String rootnode) {
        StringBuilder acc = new StringBuilder(rootnode + ":");

        root.getChildren().forEach((s, node1) -> expand(acc, 1, node1));

        return acc.toString();
    }

    private static void expand(StringBuilder acc, int level, ConfigNode node) {
        acc
          .append("\n")
          .append(Stream.generate(() -> "  ").limit(level).collect(Collectors.joining()))
          .append(node.getName())
          .append(": ");

        if (node.getChildren().isEmpty()) {
            acc.append(node.getValue());
            return;
        }

        node.getChildren().forEach((s, node1) -> expand(acc, level + 1, node1));
    }
}
