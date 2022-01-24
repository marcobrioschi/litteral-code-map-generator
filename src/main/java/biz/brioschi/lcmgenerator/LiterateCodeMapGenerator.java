package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.BoxesFilter;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMap2BuilderMapper;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.literatemap.builders.LiterateCodeBuilder;
import biz.brioschi.lcmgenerator.literatemap.builders.PlantUMLBuilder;
import biz.brioschi.lcmgenerator.providers.FileSystemScanner;
import biz.brioschi.lcmgenerator.providers.PlantUMLGenerator;
import biz.brioschi.lcmgenerator.sourceanalyzer.java.JavaAnalyzer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import picocli.CommandLine;
import picocli.CommandLine.Command;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static picocli.CommandLine.Option;

@Command
public class LiterateCodeMapGenerator implements Runnable {

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(new LiterateCodeMapGenerator());
        commandLine.execute(args);
    }

    @Option(names = {"-t", "--title"}, description = "title of the literatemap", defaultValue = "")
    private String title;

    @Option(names = {"-d", "--description"}, description = "description of the literatemap", defaultValue = "")
    private String description;

    @Option(names = {"-s", "--source-directories"}, description = "source directories to scan", defaultValue = ".", arity = "0..*", split = ",")
    private List<String> sourceDirectories;

    @Option(names = {"-f", "--filter-boxes"}, description = "boxes that can be showed", arity = "0..*", split = ",")
    private List<String> validBoxes;

    @Option(names = {"-l", "--list-boxes"}, description = "list all the boxes found in the source directories", defaultValue = "false")
    private boolean listAllBoxes;

    @Option(names = {"-o", "--output-file"}, description = "output literatemap file name", defaultValue = "./literate-code-map.svg")
    private String outputDiagramFileName;

    @Override
    public void run() {

        // Scan all the files
        FileSystemScanner fileSystemScanner = new FileSystemScanner(sourceDirectories);
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

        // List all the boxes
        if (listAllBoxes) {
            for (LiterateCodeMapBox box : boxes) {
                System.out.println("box:" + box.getName());
                for (BoxConnection connection: box.getConnections()) {
                    System.out.println("source:" + box.getName() + ",target:" + connection.getTargetBoxName());
                }
            }
        }

        // Filter list of boxes
        BoxesFilter boxesFilter = new BoxesFilter(validBoxes);
        List<LiterateCodeMapBox> filteredBoxes = boxesFilter.filter(boxes);

        // Generate the map description
        LiterateCodeBuilder literateCodeBuilder = new PlantUMLBuilder();
        LiterateCodeMap2BuilderMapper literateCodeMap2BuilderMapper = new LiterateCodeMap2BuilderMapper(literateCodeBuilder);
        literateCodeMap2BuilderMapper.mapBoxes(title, description, filteredBoxes);
        String source = literateCodeBuilder.getDiagramDescription();

        // Generate the map image
        try (OutputStream diagramFileOutputStream = new FileOutputStream(outputDiagramFileName)) {
            PlantUMLGenerator plantUMLGenerator = new PlantUMLGenerator();
            plantUMLGenerator.generateSVGDiagram(source, diagramFileOutputStream);
        } catch (IOException e) {
            System.err.println("Exception writing the literate code map literatemap: " + e.getMessage());
        }

    }

}
