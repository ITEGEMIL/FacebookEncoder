package pl.kurs.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public List<File> getAllJsonFilesFromPath(String path) {
        return getAllFilesInDirectoryBySuffix(new File(path), ".json");
    }

    private List<File> getAllFilesInDirectoryBySuffix(File root, String suffix) {
        List<File> filesWithSuffix = new ArrayList<>();
        if (root.isDirectory()) {
            File[] files = root.listFiles();
            for (File file : files) {
                filesWithSuffix.addAll(getAllFilesInDirectoryBySuffix(file, suffix));
            }
        } else if (root.getName().endsWith(suffix)) {
            filesWithSuffix.add(root);
        }
        return filesWithSuffix;
    }
}
