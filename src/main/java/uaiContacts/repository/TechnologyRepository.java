package uaiContacts.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import uaiContacts.model.Technologies;

public interface TechnologyRepository extends PagingAndSortingRepository<Technologies, Integer> {
    Page<Technologies> findByTechnologyLike(Pageable pageable, String technology);
}