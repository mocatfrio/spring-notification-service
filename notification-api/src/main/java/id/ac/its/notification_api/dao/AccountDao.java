package id.ac.its.notification_api.dao;

import id.ac.its.notification_api.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountDao extends JpaRepository<Account, UUID> {
    Account getByUsername(String username);
}
