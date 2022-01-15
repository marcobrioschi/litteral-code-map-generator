package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.providers.FileSystemScanner;
import biz.brioschi.lcmgenerator.sourceanalyzer.JavaAnalyzer;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.util.List;

@Command
public class LiterateCodeMapGenerator implements Runnable {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new LiterateCodeMapGenerator());
        commandLine.execute(args);
    }

    @Parameters( description = "source directories", defaultValue = ".")
    private List<String> baseDirectories;

    @Override
    public void run() {
        FileSystemScanner fileSystemScanner = new FileSystemScanner(baseDirectories);
        List<File> sourceUnits = fileSystemScanner.scanBaseDirectories();
        for (File sourceUnit : sourceUnits) {
            JavaAnalyzer javaAnalyzer = new JavaAnalyzer(sourceUnit);
            javaAnalyzer.extractInfo();
        }
    }

}
