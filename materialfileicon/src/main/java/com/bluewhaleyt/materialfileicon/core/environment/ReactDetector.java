package com.bluewhaleyt.materialfileicon.core.environment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

class ReactDetector {

    private static final String[] REACT_KEYWORDS = {
            "React.",
            "ReactDOM.",
            "createReactClass",
            "createClass",
            "PropTypes.",
            "Component",
            "render("
    };

    protected static boolean isReactDirectory(String dirPath) {
        boolean hasPackageJson = isReactPackageJsonFile(dirPath + "/package.json");
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
                    if (isReactFile(current.getAbsolutePath())) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    protected static boolean isReactFile(String filePath) {
        String currentDir = new File(filePath).getParent();
        boolean isReactPackageJson = isReactPackageJsonFile(currentDir + "/package.json");
        if (!isReactPackageJson) return false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine();
            while (line != null) {
                for (String keyword : REACT_KEYWORDS) {
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

    protected static boolean isReactPackageJsonFile(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            var sb = new StringBuilder();
            var line = reader.readLine();
            while (line != null) {
                sb.append(line);
                line = reader.readLine();
            }

            var json = new JSONObject(sb.toString());

            var hasDependencies = json.has("dependencies") || json.has("devDependencies");
            var hasReact = false;
            if (hasDependencies) {
                var dependencies = json.optJSONObject("dependencies");
                var devDependencies = json.optJSONObject("devDependencies");
                if (dependencies != null) {
                    hasReact = dependencies.has("react") || dependencies.has("react-dom");
                }
                if (!hasReact && devDependencies != null) {
                    hasReact = devDependencies.has("react") || devDependencies.has("react-dom");
                }
            }
            var hasEntryPoint = json.has("main") && json.getString("main").endsWith(".js");

            return hasDependencies && hasReact && hasEntryPoint;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return false;
    }

}