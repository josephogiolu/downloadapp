package service.impl;


import model.FileContent;
import model.Item;
import model.ItemType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.FileContentRepository;
import repository.ItemRepository;
import service.ItemService;
import service.PermissionService;
import vo.NotFoundException;
import vo.PermissionDeniedException;

@Service
public class ItemServiceImpl extends ItemService
{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileContentRepository fileContentRepository;

    @Autowired
    private PermissionService permissionService;
 @Override
    public Item createSpace(String spaceName) {
        Item space = new Item();
        space.setType(ItemType.SPACE);
        space.setName(spaceName);
        Item savedSpace = itemRepository.save(space);
        permissionService.assignPermissionGroup(savedSpace, "adminGroup", "VIEW", "EDIT");
        return savedSpace;
    }
@Override
    public Item createFolder(Long parentId, String folderName, String userEmail, String permissionLevel) {
        Item parent = itemRepository.findById(parentId).orElseThrow(() -> new NotFoundException("Parent item not found"));
        validatePermission(parent, userEmail, "EDIT");
        Item folder = new Item();
        folder.setType(ItemType.FOLDER);
        folder.setName(folderName);
        folder.setParent(parent);
        Item savedFolder = itemRepository.save(folder);
        permissionService.assignPermission(savedFolder, userEmail, permissionLevel);
        return savedFolder;
    }
@Override
    public Item createFile(Long parentId, String fileName, String userEmail, String permissionLevel, byte[] fileContent) {
        Item parent = itemRepository.findById(parentId).orElseThrow(() -> new NotFoundException("Parent item not found"));
        validatePermission(parent, userEmail, "EDIT");
        Item file = new Item();
        file.setType(ItemType.FILE);
        file.setName(fileName);
        file.setParent(parent);
        Item savedFile = itemRepository.save(file);
        FileContent savedFileContent = fileContentRepository.save(new FileContent(savedFile, fileContent));
        permissionService.assignPermission(savedFile, userEmail, permissionLevel);
        return savedFile;
    }

    private void validatePermission(Item item, String userEmail, String requiredPermission) {
        if (!permissionService.hasPermission(item, userEmail, requiredPermission)) {
            throw new PermissionDeniedException("Permission denied");
        }
    }
}

