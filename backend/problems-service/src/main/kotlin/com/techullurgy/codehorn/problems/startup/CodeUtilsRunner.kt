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
        cUtils = """
            #define __SIZE__ 100

            int __MAP_LINE_NO__ = 1;

            // Node for linked list in each hash bucket
            typedef struct __Node__ {
                int key;
                char* value;
                struct __Node__* next;
            } __Node__;

            __Node__* __hashTable__[__SIZE__]; // Array of linked list heads

            // Hash function for int keys
            unsigned int __hash__(int key) {
                return key % __SIZE__;
            }

            // Insert (key, value) into the map
            void __put__(int key, const char* value) {
                unsigned int index = __hash__(key);
                __Node__* newNode = malloc(sizeof(__Node__));
                newNode->key = key;
                newNode->value = strdup(value); // Duplicate the string
                newNode->next = __hashTable__[index];
                __hashTable__[index] = newNode;
            }

            char* __get__(int key) {
                unsigned int index = __hash__(key);
                __Node__* temp = __hashTable__[index];
                while (temp) {
                    if (temp->key == key) return temp->value;
                    temp = temp->next;
                }
                return NULL; // not found
            }

            void __freeMap__() {
                for (int i = 0; i < __SIZE__; i++) {
                    __Node__* temp = __hashTable__[i];
                    while (temp) {
                        __Node__* toFree = temp;
                        temp = temp->next;
                        free(toFree->value);
                        free(toFree);
                    }
                }
            }

            void __readFromFileAndSaveInMap__(char* fileName) {
              FILE *file = fopen(fileName, "r");
              if (file == NULL) {
                  perror("Error opening file");
                  return;
              }

              char line[1024];

              while (fgets(line, sizeof(line), file)) {
                size_t length = strlen(line);
                if (length > 0 && line[length - 1] == '\n') {
                    line[length - 1] = '\0';  // Replace newline with null terminator
                }
                char* a = (char*)malloc(sizeof(line));
                strcpy(a, line);
                __put__(__MAP_LINE_NO__++, a);
              }
              __MAP_LINE_NO__ = 1;

              fclose(file);
            }

            int __getInteger__() {
              int a;
              char* value = __get__(__MAP_LINE_NO__++);
              sscanf(value, "%d", &a);
              return a;
            }

            long long int __getLong__() {
              long long int a;
              char* value = __get__(__MAP_LINE_NO__++);
              sscanf(value, "%lld", &a);
              return a;
            }

            double __getDouble__() {
              double a;
              char* value = __get__(__MAP_LINE_NO__++);
              sscanf(value, "%lf", &a);
              return a;
            }

            char* __getString__() {
              char* value = __get__(__MAP_LINE_NO__++);
              return value;
            }
            
            void __writeOutputs__(const char* eResult, const char* result, const char* tNo) {
                char resultFileName[100];
                char eResultFileName[100];
            
                sprintf(resultFileName, "outputs/result%s.txt", tNo);
                sprintf(eResultFileName, "outputs/eResult%s.txt", tNo);
            
                FILE *resultFile = fopen(resultFileName, "w");
                if (resultFile == NULL) {
                    perror("Error opening result file");
                } else {
                    fprintf(resultFile, "%s", result);
                    fclose(resultFile);
                }
            
                FILE *eResultFile = fopen(eResultFileName, "w");
                if (eResultFile == NULL) {
                    perror("Error opening eResult file");
                } else {
                    fprintf(eResultFile, "%s", eResult);
                    fclose(eResultFile);
                }
            }
        """.trimIndent(),
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
              
              public static void writeResults(String eResult, String result, String tNo) {
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
        cMain = """
            int main(int argc, char *argv[]) {
                char* testcaseType = argv[0];
                char* tNo = argv[1];
                char filePath[100];
                sprintf(filePath, "/tmp/c/testcases/%s-input%s.txt", testcaseType, tNo);
                __readFromFileAndSaveInMap__(filePath);
                
                // Your code here
                
                // Your code ends here
                
                __writeOutputs__(expected, result);
            }
        """.trimIndent(),
        cppMain = "",
        javaMain = """
            public class Main {
              public static void main(String[] args) throws Exception {
                String testcaseType = args[0].toLowerCase();
                MainUtils.readFromFileAndSaveInMap("/tmp/java/testcases/"+testcaseType+"-input"+args[1]+".txt");
                
                // Your code starts here
                
                // Your code ends here
                
                MainUtils.writeResults(String.valueOf(eResult), String.valueOf(result), args[1]);
              }
            }
        """.trimIndent(),
        pythonMain = "",
        javascriptMain = "",
        cImports = """
            #include <stdio.h>
            #include <stdlib.h>
            #include <string.h>
        """.trimIndent(),
        cppImports = "",
        javaImports = """
            import java.io.*;
            import java.util.*;
        """.trimIndent(),
        pythonImports = "",
        javascriptImports = "",
    )
)