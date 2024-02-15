package es.nextdigital.account.repository;

import es.nextdigital.account.model.Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {

    List<Account> findByCustomer(String documentNumber);

    Account findByIban(String iban);
}
