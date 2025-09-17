import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVComparator {

    public static void main(String[] args) {
        String filePath1 = "playerInfo1.csv";  // Path to the first CSV file
        String filePath2 = "playerInfo2.csv"; // Path to the second CSV file

        try {
            if (compareCSVFiles(filePath1, filePath2)) {
                System.out.println("\nThe two CSV files are IDENTICAL.");
            } else {
                System.out.println("\nThe two CSV files are DIFFERENT.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Compare two CSV files.
     * 
     * @param filePath1 Path to the first CSV file.
     * @param filePath2 Path to the second CSV file.
     * @return true if the files are identical, false if there are differences.
     * @throws IOException If there's an error reading the files.
     */
    public static boolean compareCSVFiles(String filePath1, String filePath2) throws IOException {
        // Read the CSV files
        List<String[]> rows1 = readCSVFile(filePath1);
        List<String[]> rows2 = readCSVFile(filePath2);

        // Print row sizes to debug the sizes of both CSVs
        System.out.println("File 1 row count: " + rows1.size());
        System.out.println("File 2 row count: " + rows2.size());

        // Ensure the rows are not empty
        if (rows1.isEmpty() || rows2.isEmpty()) {
            System.out.println("One of the files is empty.");
            return false;
        }

        // Get the column names (from the first row)
        String[] columnNames1 = rows1.get(0);
        String[] columnNames2 = rows2.get(0);

        // Print column names to debug
        System.out.println("File 1 columns: " + String.join(", ", columnNames1));
        System.out.println("File 2 columns: " + String.join(", ", columnNames2));

        // Compare the headers
        if (!compareRows(columnNames1, columnNames2)) {
            System.out.println("\nThe headers are different between the two files:");
            printRowDifference(columnNames1, columnNames2, columnNames1, columnNames2, 1);  // Print header differences
            return false;
        }

        boolean filesAreIdentical = true;

        // Get the maximum row count for comparison (based on the larger file)
        int maxRows = Math.max(rows1.size(), rows2.size());

        // Compare rows for both files, but limit comparison to the minimum row count
        for (int i = 1; i < Math.min(rows1.size(), rows2.size()); i++) {
            String[] row1 = rows1.get(i);
            String[] row2 = rows2.get(i);

            // Print rows being compared (for debugging)
            System.out.println("\nComparing Row " + (i + 1));
            System.out.println("File 1: " + String.join(", ", row1));
            System.out.println("File 2: " + String.join(", ", row2));

            // Check if rows are different (both in length and content)
            if (row1.length != row2.length) {
                System.out.println("\nDifference found at Row " + (i + 1));
                System.out.println("Column length mismatch:");
                System.out.println("File 1 has " + row1.length + " columns, File 2 has " + row2.length + " columns.");
                printRowDifference(row1, row2, columnNames1, columnNames2, i + 1);
                filesAreIdentical = false;
            } else if (!compareRows(row1, row2)) {
                System.out.println("\nDifference found at Row " + (i + 1));
                printRowDifference(row1, row2, columnNames1, columnNames2, i + 1);
                filesAreIdentical = false;
            }
        }

        // Check for additional rows in File 2 (which File 1 doesn't have)
        if (rows2.size() > rows1.size()) {
            System.out.println("\nFile 2 has additional rows starting from Row " + (rows1.size() + 1) + ":");
            for (int i = rows1.size(); i < rows2.size(); i++) {
                String[] row1 = new String[columnNames1.length];  // Create empty row for File 1
                printRowDifference(row1, rows2.get(i), columnNames1, columnNames2, i + 1);
            }
            filesAreIdentical = false;
        }

        return filesAreIdentical;  // Return true if no differences were found
    }

    /**
     * Read CSV file and return a list of string arrays (rows).
     * 
     * @param filePath Path to the CSV file.
     * @return List of string arrays representing rows in the CSV.
     * @throws IOException If there's an error reading the file.
     */
    private static List<String[]> readCSVFile(String filePath) throws IOException {
        List<String[]> rows = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        while ((line = reader.readLine()) != null) {
            // Split the line by commas (assuming no commas within the data)
            String[] columns = line.split(",");
            rows.add(columns);
        }

        reader.close();
        return rows;
    }

    /**
     * Compare two rows (arrays of columns).
     * 
     * @param row1 First row (array of columns).
     * @param row2 Second row (array of columns).
     * @return true if the rows are identical, false otherwise.
     */
    private static boolean compareRows(String[] row1, String[] row2) {
        // Compare each column, ensuring not to access out-of-bounds
        for (int i = 0; i < Math.min(row1.length, row2.length); i++) {
            if (!getValue(row1, i).trim().equals(getValue(row2, i).trim())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get value at index i in row or return "N/A" if the value is missing.
     */
    private static String getValue(String[] row, int index) {
        if (row == null || index >= row.length) {
            return "N/A";
        }
        return row[index] != null ? row[index] : "N/A";
    }

    /**
     * Print the differences between two rows with column names.
     * 
     * @param row1 First row.
     * @param row2 Second row.
     * @param columnNames1 Column names for file 1.
     * @param columnNames2 Column names for file 2.
     * @param rowNum The row number being compared.
     */
    private static void printRowDifference(String[] row1, String[] row2, String[] columnNames1, String[] columnNames2, int rowNum) {
        int maxLength = Math.max(row1.length, row2.length);

        // Print column differences for each column index
        for (int i = 0; i < maxLength; i++) {
            // Handle missing columns by checking if they exist before accessing
            String value1 = getValue(row1, i);
            String value2 = getValue(row2, i);

            // Check if the values are different
            if (!value1.trim().equals(value2.trim())) {
                // Determine the column name, or print "N/A" if the column doesn't exist
                String columnName1 = (i < columnNames1.length) ? columnNames1[i] : "N/A";
                String columnName2 = (i < columnNames2.length) ? columnNames2[i] : "N/A";

                // Print the column names and values that differ
                System.out.println("Difference found at Row " + rowNum + " - Column \"" +
                        (columnName1.equals("N/A") ? columnName2 : columnName1) + "\" differs:");
                System.out.println("File 1: \"" + value1 + "\"");
                System.out.println("File 2: \"" + value2 + "\"");
            }
        }
    }
}
