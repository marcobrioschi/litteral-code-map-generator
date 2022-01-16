package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.model.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.providers.FileSystemScanner;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Command
public class LiterateCodeMapGenerator implements Runnable {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new LiterateCodeMapGenerator());
        commandLine.execute(args);
    }

    @Parameters(description = "source directories", defaultValue = ".", split = ",")
    private List<String> baseDirectories;

    @Override
    public void run() {
        FileSystemScanner fileSystemScanner = new FileSystemScanner(baseDirectories);
        List<File> sourceUnits = fileSystemScanner.scanBaseDirectories();
        List<LiterateCodeMapBox> boxes = new ArrayList<>();
        for (File sourceUnit : sourceUnits) {
            try (FileInputStream sourceUnitInputStream = new FileInputStream(sourceUnit)) {
                CharStream charInputStream = CharStreams.fromStream(sourceUnitInputStream);
                JavaAnalyzer javaAnalyzer = new JavaAnalyzer(charInputStream);
                boxes.addAll(javaAnalyzer.extractInfo());
            } catch (IOException e) {
                System.err.println("Exception reading a source unit: " + e.getMessage());
            }
        }
        // TODO use the data
        for (LiterateCodeMapBox box : boxes) {
            System.out.println(">>> " + box);
        }
    }

}
