package biz.brioschi.lcmgenerator;

import biz.brioschi.lcmgenerator.literatemap.BoxConnection;
import biz.brioschi.lcmgenerator.literatemap.BoxesFilter;
import biz.brioschi.lcmgenerator.literatemap.LiterateCodeMap2BuilderMapper;
import biz.brioschi.lcmgenerator.literatemap.Box;
import biz.brioschi.lcmgenerator.builders.LiterateCodeBuilder;
import biz.brioschi.lcmgenerator.builders.PlantUMLBuilder;
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

    @Option(names = {"-o", "--output-file"}, description = "output literate code map file name", defaultValue = "./literate-code-map.svg")
    private String outputLiterateCodeMapFileName;

    @Option(names = {"-p", "--keep-plantuml-file"}, description = "keep the PlantUML intermediate file", defaultValue = "false")
    private boolean trackPlantUMLSourceFile;

    @Override
    public void run() {

        // Scan all the files
        FileSystemScanner fileSystemScanner = new FileSystemScanner(sourceDirectories);
        List<File> sourceUnits = fileSystemScanner.scanBaseDirectories();       // @LiterateMapInvoke(1, "FileSystemScanner", "scanBaseDirectories()")
        List<Box> boxes = new ArrayList<>();
        for (File sourceUnit : sourceUnits) {
            try (FileInputStream sourceUnitInputStream = new FileInputStream(sourceUnit)) {
                CharStream charInputStream = CharStreams.fromStream(sourceUnitInputStream);
                JavaAnalyzer javaAnalyzer = new JavaAnalyzer(charInputStream);
                boxes.addAll(javaAnalyzer.extractInfo());                       // @LiterateMapInvoke(2, "JavaAnalyzer", "extractInfo(...)")
            } catch (IOException e) {
                System.err.println("Exception reading a source unit: " + e.getMessage());
            }
        }

        // List all the boxes
        if (listAllBoxes) {
            for (Box box : boxes) {
                System.out.println("box:" + box.getName());
                for (BoxConnection connection: box.getConnections()) {
                    System.out.println("source:" + box.getName() + ",target:" + connection.getTargetBoxName());
                }
            }
        }

        // Filter list of boxes
        BoxesFilter boxesFilter = new BoxesFilter(validBoxes);
        List<Box> filteredBoxes = boxesFilter.filter(boxes);                 // @LiterateMapInvoke(3, "BoxesFilter", "filter(boxes)")

        // Generate the map description
        LiterateCodeBuilder literateCodeBuilder = new PlantUMLBuilder();
        LiterateCodeMap2BuilderMapper literateCodeMap2BuilderMapper = new LiterateCodeMap2BuilderMapper(literateCodeBuilder);
        literateCodeMap2BuilderMapper.mapBoxes(title, description, filteredBoxes);          // @LiterateMapInvoke(4, "LiterateCodeBuilder", "mapBoxes(filteredBoxes)")
        String source = literateCodeBuilder.getLiterateCodeMaoDescription();

        // Track PlantUML file
        if (trackPlantUMLSourceFile) {
            String plantUMLFileName = outputLiterateCodeMapFileName.replace(".svg", ".plantuml");
            try(PrintWriter plantUMLFile = new PrintWriter(plantUMLFileName)) {
                plantUMLFile.println(source);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Generate the map image
        try (OutputStream literateCodeMapFileOutputStream = new FileOutputStream(outputLiterateCodeMapFileName)) {
            PlantUMLGenerator plantUMLGenerator = new PlantUMLGenerator();
            plantUMLGenerator.generateSVGImage(source, literateCodeMapFileOutputStream);    // @LiterateMapInvoke(5, "PlantUMLGenerator", "generateSVGImage(...)")
        } catch (IOException e) {
            System.err.println("Exception writing the literate code map: " + e.getMessage());
        }

    }

}
