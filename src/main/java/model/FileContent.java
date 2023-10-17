package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class FileContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Item savedFile;
    private byte[] fileContent;
    public FileContent(Item savedFile, byte[] fileContent) {
        this.fileContent=fileContent;
        this.savedFile=savedFile;
    }


}
