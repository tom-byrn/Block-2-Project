package settings;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Settings {

    //Using a HashMap so that we can pair settings
    private final Map<String, String> settings = new HashMap<>();
    private final Path settingsFilePath;

    // Constructor to load the settings from a file
    public Settings(String filePath) throws IOException {
        this.settingsFilePath = Paths.get(filePath);
        loadSettings();
    }

    // Load the settings from the file
    private void loadSettings() throws IOException {
        if (!Files.exists(settingsFilePath)) {
            // If the file doesn't exist, create it with default settings
            createDefaultSettings();
        }

        // Read the file line by line and populate the settings map
        try (BufferedReader reader = Files.newBufferedReader(settingsFilePath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=", 2); // Split key and value
                if (parts.length == 2) {
                    settings.put(parts[0].trim(), parts[1].trim());
                }
            }
        }
    }

    // Create a default settings file
    private void createDefaultSettings() throws IOException {
        settings.put("trigFunctions", "degrees");
        settings.put("graphTheme", "light");
        saveSettings();
    }

    // Save the current settings to the file
    public void saveSettings() throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(settingsFilePath)) {
            for (Map.Entry<String, String> entry : settings.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }

    // Get a setting by key
    public String getSetting(String key) {
        return settings.getOrDefault(key, null);
    }

    // Update a setting
    public void setSetting(String key, String value) throws IOException {
        settings.put(key, value);
        saveSettings(); // Save changes to the file
    }

    public static void main(String[] args) {
        try {
            // Example usage
            Settings settings = new Settings("settings.txt");
            System.out.println("Trig Functions: " + settings.getSetting("trigFunctions"));
            System.out.println("Graph Theme: " + settings.getSetting("graphTheme"));

            // Update a setting
            settings.setSetting("graphTheme", "dark");
            System.out.println("Updated Graph Theme: " + settings.getSetting("graphTheme"));
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
