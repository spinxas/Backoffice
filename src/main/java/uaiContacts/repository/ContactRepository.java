package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import uaiContacts.model.Trainees;

public interface ContactRepository extends PagingAndSortingRepository<Trainees, Integer> {
    Page<Trainees> findByNameLike(Pageable pageable, String name);
}