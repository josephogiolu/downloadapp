package service.impl;

import model.PermissionGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PermissionsGroupRepo;
import service.PermissionGroupService;

@Service
public class PermissionGroupImpl implements PermissionGroupService {
    @Autowired
    private PermissionsGroupRepo permissionsGroupRepo;
    @Override
    public PermissionGroup createPermissionGroup(String permGroup) {
        PermissionGroup permissionGroup= new PermissionGroup();
        permissionGroup.setGroupName(permGroup);
        return permissionsGroupRepo.save(permissionGroup);
    }
}
