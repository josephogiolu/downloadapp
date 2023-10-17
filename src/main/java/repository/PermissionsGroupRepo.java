package repository;

import model.PermissionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionsGroupRepo extends JpaRepository<PermissionGroup, Long> {
}
