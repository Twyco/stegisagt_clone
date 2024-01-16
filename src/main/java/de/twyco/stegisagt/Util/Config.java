package de.twyco.stegisagt.Util;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    private final FileConfiguration fileConfiguration;
    private final File file;

    public Config(String fileName, File filePath) {
        file = new File(filePath, fileName);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        fileConfiguration = new YamlConfiguration();
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    public File getFile() {
        return file;
    }

    public FileConfiguration getFileConfiguration() {
        return fileConfiguration;
    }

    public void save() {
        try {
            fileConfiguration.save(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reload() {
        try {
            fileConfiguration.load(file);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

}
