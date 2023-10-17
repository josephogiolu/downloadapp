package service.impl;

import model.Item;
import model.PermissionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PermissionRepository;
import service.PermissionGroupService;
import model.Permission;
import service.PermissionService;
import java.util.List;

@Service
public class PermissionImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private PermissionGroupService permissionGroupService;

    @Override
    public void assignPermissionGroup(Item item, String groupName, String... permissionLevels) {
        PermissionGroup group = permissionGroupService.createPermissionGroup(groupName);
        for (String permissionLevel : permissionLevels) {
            assignPermission(item, group, permissionLevel);
        }
    }

    @Override
    public void assignPermission(Item item, String userEmail, String permissionLevel) {
        Permission permission = new Permission();
        permission.setUserEmail(userEmail);
        setPemission(item, permissionLevel, permission);
    }

    @Override
    public void assignPermission(Item item, PermissionGroup group, String permissionLevel) {
        Permission permission = new Permission();
        permission.setGroup(group);
        setPemission(item, permissionLevel,permission);
    }

    private void setPemission(Item item, String permissionLevel,Permission permission) {
        permission.setItem(item);
        permission.setPermissionLevel(permissionLevel);
        permissionRepository.save(permission);
    }

    @Override
    public boolean hasPermission(Item item, String userEmail, String requiredPermission) {
        List<Permission> permissions = permissionRepository.findByItemAndUserEmail(item, userEmail);
        return permissions.stream().anyMatch(p -> p.getPermissionLevel().equals(requiredPermission));
    }
}
