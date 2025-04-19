package com.techullurgy.codehorn.problems.startup

import com.techullurgy.codehorn.problems.data.entities.CodeUtils
import com.techullurgy.codehorn.problems.data.repositories.CodeUtilsRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CodeUtilsRunner(
    private val codeUtilsRepository: CodeUtilsRepository
): CommandLineRunner {

    override fun run(vararg args: String?) {
        codeUtils.forEach { codeUtilsRepository.save(it) }
    }
}


private val codeUtils = listOf(
    CodeUtils(
        cUtils = "",
        cppUtils = "",
        javaUtils = """
            class MainUtils {
              private static Map<Integer, String> map = new HashMap();
              private static int lineIndex = 1;

              public static void readFromFileAndSaveInMap(String fileName) {
                try(Scanner scanner = new Scanner(new File(fileName))) {
                  while(scanner.hasNextLine()) {
                    String current = scanner.nextLine();
                    map.put(lineIndex++, current);
                  }
                  lineIndex = 1;
                } catch(FileNotFoundException e) {}
              }

              public static int getInteger() {
                return Integer.parseInt(map.get(lineIndex++));
              }

              public static String getString() {
                return map.get(lineIndex++);
              }

              public static List<String> getListOfStrings() {
                List<String> ans = new ArrayList();
                int length = getInteger();
                for(int i = 0; i < length; i++) {
                  ans.add(getString());
                }
                return ans;
              }

              public static List<List<String>> getListOfListOfStrings() {
                List<List<String>> ans = new ArrayList();
                int rows = getInteger();
                for(int i = 0; i < rows; i++) {
                  List<String> inner = new ArrayList();
                  int columns = getInteger();
                  for(int j = 0; j < columns; j++) {
                    inner.add(getString());
                  }
                  ans.add(new ArrayList(inner));
                }
                return ans;
              }
              
              public static void writeResults(String result, String eResult, String tNo) {
                String resultFileName = "outputs/result" + tNo + ".txt";
                
                String eResultFileName = "outputs/eResult" + tNo + ".txt";
                
                try (FileWriter writer = new FileWriter(resultFileName)) {
                    writer.write(result);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
                try (FileWriter writer = new FileWriter(eResultFileName)) {
                    writer.write(eResult);
                } catch (IOException e) {
                    e.printStackTrace();
                }
              }
            }
        """.trimIndent(),
        pythonUtils = "",
        javascriptUtils = "",
        cMain = "",
        cppMain = "",
        javaMain = """
            public class Main {
              public static void main(String[] args) throws Exception {
                String testcaseType = args[0].toLowerCase();
                MainUtils.readFromFileAndSaveInMap("/tmp/java/testcases/"+testcaseType+"-input"+args[1]+".txt");
                
                // Your code starts here
                
                // Your code ends here
                
                MainUtils.writeResults(String.valueOf(result), String.valueOf(eResult), args[1]);
              }
            }
        """.trimIndent(),
        pythonMain = "",
        javascriptMain = "",
        cImports = "",
        cppImports = "",
        javaImports = """
            import java.io.*;
            import java.util.*;
        """.trimIndent(),
        pythonImports = "",
        javascriptImports = "",
    )
)