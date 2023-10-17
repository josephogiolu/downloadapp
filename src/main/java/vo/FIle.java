package vo;


import lombok.Data;

@Data
class File extends FileSystemItem {
    private String content;
    public File(String name, String content) {
        super(name);
        this.content = content;
    }


}

