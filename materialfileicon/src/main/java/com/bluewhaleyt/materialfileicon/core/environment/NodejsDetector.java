package com.bluewhaleyt.materialfileicon.core.environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class NodejsDetector {

    private static final String[] NODEJS_KEYWORDS = {
            "require(",
            "module.exports",
            "exports.",
            "global.",
            "process.",
            "console.",
            "__dirname",
            "__filename",
            "Buffer.",
            "crypto."
    };

    protected static boolean isNodeJsDirectory(String dirPath) {
        boolean hasPackageJson = isNodeJsPackageJsonFile(dirPath + "/package.json");
        if (!hasPackageJson) return false;

        try {
            File dir = new File(dirPath);
            Queue<File> queue = new LinkedList<>();
            queue.add(dir);

            while (!queue.isEmpty()) {
                File current = queue.poll();
                if (current.isDirectory()) {
                    File[] files = current.listFiles();
                    if (files != null) {
                        for (File file : files) {
                            queue.add(file);
                        }
                    }
                } else if (current.isFile() && current.getName().endsWith(".js")) {
                    if (isNodeJsFile(current.getAbsolutePath())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected static boolean isNodeJsFile(String filePath) {
        String currentDir = new File(filePath).getParent();
        boolean isNodeJsPackageJson = isNodeJsPackageJsonFile(currentDir + "/package.json");
        if (!isNodeJsPackageJson) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                for (String keyword : NODEJS_KEYWORDS) {
                    if (line.contains(keyword)) {
                        return true;
                    }
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    protected static boolean isNodeJsPackageJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            var sb = new StringBuilder();
            var line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }

            var json = new JSONObject(sb.toString());

            var hasDependencies = json.has("dependencies") || json.has("devDependencies");
            var hasNodeModules = false;
            if (hasDependencies) {
                var dependencies = json.optJSONObject("dependencies");
                var devDependencies = json.optJSONObject("devDependencies");
                if (dependencies != null) {
                    hasNodeModules = dependencies.has("express") || dependencies.has("koa");
                }
                if (!hasNodeModules && devDependencies != null) {
                    hasNodeModules = devDependencies.has("express") || devDependencies.has("koa");
                }
            }
            var hasEntryPoint = json.has("main") && json.getString("main").endsWith(".js");

            return hasDependencies && hasNodeModules && hasEntryPoint;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}
