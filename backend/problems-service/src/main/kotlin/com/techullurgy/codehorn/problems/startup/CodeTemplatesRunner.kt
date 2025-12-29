package com.techullurgy.codehorn.problems.startup

import com.techullurgy.codehorn.problems.data.entities.CodeTemplates
import com.techullurgy.codehorn.problems.data.repositories.CodeTemplatesRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CodeTemplatesRunner(
    private val codeTemplatesRepository: CodeTemplatesRepository
): CommandLineRunner {

    override fun run(vararg args: String) {
        codeTemplates.forEach { codeTemplatesRepository.save(it) }
    }
}


private val codeTemplates = listOf(
    CodeTemplates(
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
            
            void __toLowercase__(char* str) {
              for (int i = 0; str[i]; i++) {
                  str[i] = tolower((unsigned char)str[i]);
              }
            }
        """.trimIndent(),
        cppUtils = """
            class MainUtils {
            public:
                static std::map<int, std::string> input_map;
                static int line_index;

                static void readFromFileAndSaveInMap(const std::string& filename) {
                    std::ifstream infile(filename);
                    std::string line;
                    while (std::getline(infile, line)) {
                        input_map[line_index] = line;
                        ++line_index;
                    }
                    line_index = 1;
                    
                    infile.close();
                }

                static std::string getString() {
                    return input_map[line_index++];
                }

                static int getInteger() {
                    return std::stoi(input_map[line_index++]);
                }

                static void writeOutputs(const std::string& eResult, const std::string& result, const std::string& tNo) {
                    std::ofstream expected("outputs/eResult"+tNo+".txt");
                    expected << eResult;
                    expected.close();

                    std::ofstream actual("outputs/result"+tNo+".txt");
                    actual << result;
                    actual.close();
                }
            };

            std::map<int, std::string> MainUtils::input_map;
            int MainUtils::line_index = 1;

            std::string toLowerCase(std::string str) {
                std::transform(str.begin(), str.end(), str.begin(), ::tolower);
                return str;
            }
        """.trimIndent(),
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
        pythonUtils = """
            class MainUtils:
              input_map = dict()
              line_index = 1

              @staticmethod
              def readFromFileAndSaveInDictionary(filename):
                with open(filename, "r") as file:
                  line = file.readline()
                  while line:
                    MainUtils.input_map[MainUtils.line_index] = line.strip()
                    MainUtils.line_index += 1
                    line = file.readline()
                MainUtils.line_index = 1
                
              @staticmethod
              def getString():
                ans = MainUtils.input_map[MainUtils.line_index]
                MainUtils.line_index += 1
                return ans

              @staticmethod
              def getInteger():
                ans = int(MainUtils.input_map[MainUtils.line_index])
                MainUtils.line_index += 1
                return ans
              
              @staticmethod
              def writeOutputs(eResult, result, tNo):
                expected_result_filename = "outputs/eResult"+tNo+".txt"
                result_filename = "outputs/result"+tNo+".txt"

                with open(expected_result_filename, "w") as file:
                  file.write(eResult)

                with open(result_filename, "w") as file:
                  file.write(result)
        """.trimIndent(),
        javascriptUtils = """
            class MainUtils {
              static inputMap = {};
              static lineIndex = 1;

              static async readFromFileAndSaveInMap(fileName) {
                  const fileStream = fs.createReadStream(fileName)
                  const rl = readline.createInterface({
                    input: fileStream,
                    crlfDelay: Infinity
                  })
                
                  return new Promise(res => {
                    rl.on('line', line => { MainUtils.inputMap[MainUtils.lineIndex++] = line })
                    rl.on('close', _ => {
                        MainUtils.lineIndex = 1
                        res() 
                    })
                  })
              }

              static getString() {
                const ans = MainUtils.inputMap[MainUtils.lineIndex];
                MainUtils.lineIndex++;
                return ans;
              }

              static getInteger() {
                const ans = parseInt(MainUtils.inputMap[MainUtils.lineIndex]);
                MainUtils.lineIndex++;
                return ans;
              }

              static writeOutputs(expectedResult, result, tNo) {
                const expectedPath = path.join("outputs", "eResult"+tNo+".txt");
                const resultPath = path.join("outputs", "result"+tNo+".txt");

                fs.writeFileSync(expectedPath, expectedResult);
                fs.writeFileSync(resultPath, result);
              }
            }
        """.trimIndent(),
        cMain = """
            int main(int argc, char *argv[]) {
                char* testcaseType = argv[1];
                char* tNo = argv[2];
                
                __toLowercase__(testcaseType);
                
                char filePath[100];
                sprintf(filePath, "/tmp/c/testcases/%s-input%s.txt", testcaseType, tNo);
                __readFromFileAndSaveInMap__(filePath);
                
                // Your code here
                
                // Your code ends here
                
                __writeOutputs__(eResult, result, tNo);
            }
        """.trimIndent(),
        cppMain = """
            int main(int argc, char* argv[]) {
                std::string testcaseType = toLowerCase(argv[1]);
                std::string tNo = argv[2];

                std::string inputFile = "/tmp/cpp/testcases/" + testcaseType + "-input" + tNo + ".txt";
                MainUtils::readFromFileAndSaveInMap(inputFile);

                // Your code starts here

                // Your code ends here

                MainUtils::writeOutputs("", "", tNo);

                return 0;
            }
        """.trimIndent(),
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
        pythonMain = """
            def main():
              testcaseType = sys.argv[1].lower()
              tNo = sys.argv[2]
              MainUtils.readFromFileAndSaveInDictionary("/tmp/python/testcases/"+testcaseType+"-input"+tNo+".txt")
            
              # Your code starts here
              # Your code ends here
            
              MainUtils.writeOutputs("", "", tNo)
            
            if __name__ == "__main__":
              main()
        """.trimIndent(),
        javascriptMain = $$"""
            (async () => {
              const args = process.argv.slice(2)
            
              const testcaseType = args[0].toLowerCase()
              const tNo = args[1]
            
              const testcasePath = `/tmp/javascript/testcases/${testcaseType}-input${tNo}.txt`
              await MainUtils.readFromFileAndSaveInMap(testcasePath)
              
              // Your code starts here
              // Your code ends here
              
              MainUtils.writeOutputs("", "", tNo);
            })()
        """.trimIndent(),
        cImports = """
            #include <stdio.h>
            #include <stdlib.h>
            #include <string.h>
        """.trimIndent(),
        cppImports = """
            #include <iostream>
            #include <fstream>
            #include <map>
            #include <string>
            #include <cctype>
            #include <algorithm>
        """.trimIndent(),
        javaImports = """
            import java.io.*;
            import java.util.*;
        """.trimIndent(),
        pythonImports = """
            import sys
        """.trimIndent(),
        javascriptImports = """
            const fs = require('fs');
            const path = require('path');
            const readline = require('readline')
        """.trimIndent(),
    )
)