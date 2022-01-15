package biz.brioschi.lcmgenerator.providers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSystemScanner {

    private List<String> baseDirectories;
    private List<File> sourceUnits;

    public FileSystemScanner(List<String> baseDirectories) {
        this.baseDirectories = baseDirectories;
    }

    public List<File> scanBaseDirectories() {
        sourceUnits = new ArrayList<>();
        for (String baseDirectory : baseDirectories) {
            scanSingleDirectory(new File(baseDirectory));
        }
        return sourceUnits;
    }

    private void scanSingleDirectory(File currentDirectory) {
        for (File currentEntry : currentDirectory.listFiles()) {
            analyzeSingleEntry(currentEntry);
        }
    }

    private void analyzeSingleEntry(File currentEntry) {
        if (isValidSourceUnit(currentEntry)) {
            sourceUnits.add(currentEntry);
        } else if (currentEntry.isDirectory()) {
            scanSingleDirectory(currentEntry);
        }
    }

    private boolean isValidSourceUnit(File currentEntry) {
        return currentEntry.isFile() && currentEntry.getName().endsWith(".java");
    }

}
