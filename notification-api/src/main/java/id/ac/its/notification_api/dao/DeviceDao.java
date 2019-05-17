package id.ac.its.notification_api.dao;

import id.ac.its.notification_api.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DeviceDao extends JpaRepository<Device, UUID> {
    List<Device> findByUserIdAndTypeIn(Integer userId, Device.Type... type);
}
