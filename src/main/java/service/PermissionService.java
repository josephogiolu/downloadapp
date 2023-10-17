package service;

import model.Item;
import model.PermissionGroup;

public interface PermissionService {
    void assignPermissionGroup(Item item, String groupName, String... permissionLevels);

    void assignPermission(Item item, String userEmail, String permissionLevel);

    void assignPermission(Item item, PermissionGroup group, String permissionLevel);

    boolean hasPermission(Item item, String userEmail, String requiredPermission);
}
