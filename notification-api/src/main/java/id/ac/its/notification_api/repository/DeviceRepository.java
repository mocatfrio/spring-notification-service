package id.ac.its.notification_api.repository;

import id.ac.its.notification_api.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceRepository extends JpaRepository<Device, UUID> {
    List<Device> findByUserIdAndTypeIn(Integer userId, Device.Type... type);
}
