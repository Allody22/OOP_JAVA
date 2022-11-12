package ru.nsu.mbogdanov2;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**Main idea of the algorithm.
 * We find prefix function of substring (the explanation is in the findPrefixArray method)
 * Then we check the original string and if we get a mismatch, we check the prefix from the letter
 * that stands to the left of the last mismatch letter and move our substring according to the algorithm
 */
public class KnuthMorrisPratt {
    BufferedReader file;
    String substring;
    List<Integer>  ansList;

    /**Constructor of the algorithm class.
     * It will help us to get answer and to store input information
     *
     * @param file input file with text
     * @param substring pattern that should be found
     * @throws IOException exception in case there are no file
     */
    public KnuthMorrisPratt(BufferedReader file, String substring) throws IOException {
        this.file = file;
        this.substring = substring;
        ansList = algorithmSearch(file,substring);
    }

    /** Method to find array p of prefixes.
     * Element p[i] is equal to maximal number of matching prefixes
     * and matching suffixes before this element
     *
     * @param substring input pattern
     * @return array with counted prefixes
     */

    public static int[] findPrefixArray(String substring) {
        int patternLen = substring.length();
        int[] prefixArray = new int[patternLen + 1];
        prefixArray[0] = -1;
        prefixArray[1] = 0;
        int prefixLen = 0;
        int currentId = 1;
        while (currentId < patternLen) {
            if (substring.charAt(prefixLen) == substring.charAt(currentId)) {
                prefixLen++;
                currentId++;
                prefixArray[currentId] = prefixLen;

            }
            else if (prefixLen > 0) {
                prefixLen = prefixArray[prefixLen];

            }
            else {
                currentId++;
                prefixArray[currentId] = 0;
            }
        }
        return prefixArray;
    }

    /** Algorithm and its main idea.
     * If (substring is not matching) then
     * We check the prefix of the letter that stays to the left of the last wrong letter
     * If (this prefix is bigger than 0) then
     * We move substring to the right on (prefix+1) number
     * If (this prefix is zero) then
     * We check another letter stays to the left of that left letter
     * If (this letter is first) then
     * We move substring to the right on 1
     *
     * @param file input file with full text
     * @param pattern substring that should be found
     * @return list of all matching indexes
     * @throws IOException exception in case there are no file
     */
    public static List<Integer> algorithmSearch(BufferedReader file, String pattern) throws IOException {
        int currentId = 0;
        int idInPattern = 0;
        int patternLen = pattern.length();

        List<Integer> matchesId = new ArrayList<>();
        int[] prefixLen = findPrefixArray(pattern);
        int c;
        while ((c = file.read()) != -1) {
            char currentLetter = (char) c;
            if (pattern.charAt(idInPattern) == currentLetter) {
                idInPattern++;
                currentId++;

                if (idInPattern == patternLen) {
                    matchesId.add(currentId-idInPattern);
                    idInPattern = prefixLen[idInPattern];
                }
            }
            else {
                idInPattern = prefixLen[idInPattern];
                if (idInPattern < 0) {
                    currentId++;
                    idInPattern++;
                }
            }
        }
        return matchesId;
    }
}