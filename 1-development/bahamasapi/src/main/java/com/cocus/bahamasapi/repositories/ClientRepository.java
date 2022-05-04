package com.cocus.bahamasapi.repositories;

import com.cocus.bahamasapi.entities.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>, ClientRepositoryCustom {

    /**
     * Find a Client by invoice Id
     *
     * @Query("SELECT c from Client c WHERE c.invoiceId = ?1")
     */
    Optional<Client> findByInvoiceId(Long invoiceId);
}
