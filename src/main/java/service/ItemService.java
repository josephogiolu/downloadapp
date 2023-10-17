package service;

import model.Item;

public abstract class ItemService {
    public abstract Item createSpace(String spaceName);

    public abstract Item createFolder(Long parentId, String folderName, String userEmail, String permissionLevel);

    public abstract Item createFile(Long parentId, String fileName, String userEmail, String permissionLevel, byte[] fileContent);
}
