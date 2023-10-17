package controller;

import model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.ItemService;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create-space")
    public ResponseEntity<Item> createSpace() {
        Item space = itemService.createSpace("stc-assessments");
        return new ResponseEntity<>(space, HttpStatus.CREATED);
    }

    @PostMapping("/create-folder")
    public ResponseEntity<Item> createFolder(@RequestParam Long parentId, @RequestParam String folderName, @RequestParam String userEmail, @RequestParam String permissionLevel) {
        Item folder = itemService.createFolder(parentId, folderName, userEmail, permissionLevel);
        return new ResponseEntity<>(folder, HttpStatus.CREATED);
    }

    @PostMapping("/create-file")
    public ResponseEntity<Item> createFile(@RequestParam Long parentId, @RequestParam String fileName, @RequestParam String userEmail, @RequestParam String permissionLevel, @RequestParam byte[] fileContent) {
        Item file = itemService.createFile(parentId, fileName, userEmail, permissionLevel, fileContent);
        return new ResponseEntity<>(file, HttpStatus.CREATED);
    }
}
