package vo;

import java.util.ArrayList;
import java.util.List;

// Class representing a space (parent of folders)
class Space extends FileSystemItem {
    private List<Folder> folders;

    public Space(String name) {
        super(name);
        this.folders = new ArrayList<>();
    }

    public List<Folder> getFolders() {
        return folders;
    }

    public void addFolder(Folder folder) {
        folders.add(folder);
    }
}