package io;

import java.io.File;

public interface ArrayReader {

    double[] readOneDimensionalArray(File file);

    double[] readOneDimensionalArray(String filePath);

    double[][] readTwoDimensionalArray(File file);

    double[][] readTwoDimensionalArray(String filePath);

}
