package vo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// Class representing a folder (parent of files)
class Folder extends FileSystemItem {
    private List<File> files;

    public Folder(String name) {
        super(name);
        this.files = new ArrayList<>();
    }

    public List<File> getFiles() {
        return files;
    }

    public void addFile(File file) {
        files.add(file);
    }
}
