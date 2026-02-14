package org.example.patterns.singleton;

public class AppLogger {
    private static final AppLogger INSTANCE = new AppLogger();

    private AppLogger() {}

    public static AppLogger getInstance() {
        return INSTANCE;
    }

    public void info(String message) {
        System.out.println("[INFO] " + message);
    }

    public void error(String message) {
        System.out.println("[ERROR] " + message);
    }
}
