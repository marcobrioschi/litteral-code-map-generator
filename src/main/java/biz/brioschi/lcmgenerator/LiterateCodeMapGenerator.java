package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.diagram.DiagramMapper;
import biz.brioschi.lcmgenerator.diagram.LiterateCodeMapBox;
import biz.brioschi.lcmgenerator.diagram.builders.DiagramBuilder;
import biz.brioschi.lcmgenerator.diagram.builders.PlantUMLBuilder;
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

    @Option(names = {"-s", "--source-directories"}, description = "source directories to scan", defaultValue = ".", arity = "0..*", split = ",")
    private List<String> sourceDirectories;

    @Option(names = {"-f", "--filter-boxes"}, description = "boxes that can be showed", arity = "0..*", split = ",")
    private List<String> validBoxes;

    @Option(names = {"-o", "--output-file"}, description = "output diagram file name", defaultValue = "./literate-code-map.svg")
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

        // Generate the diagram description
        DiagramBuilder diagramBuilder = new PlantUMLBuilder();
        DiagramMapper diagramMapper = new DiagramMapper(diagramBuilder);
        diagramMapper.mapBoxes(boxes);
        String source = diagramBuilder.getDiagramDescription();

        // Generate the diagram image
        try (OutputStream diagramFileOutputStream = new FileOutputStream(outputDiagramFileName)) {
            PlantUMLGenerator plantUMLGenerator = new PlantUMLGenerator();
            plantUMLGenerator.generateSVGDiagram(source, diagramFileOutputStream);
        } catch (IOException e) {
            System.err.println("Exception writing the literate code map diagram: " + e.getMessage());
        }

    }

}
