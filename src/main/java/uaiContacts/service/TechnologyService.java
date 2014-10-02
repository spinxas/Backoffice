package uaiContacts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uaiContacts.repository.TechnologyRepository;
import uaiContacts.model.Technologies;
import uaiContacts.vo.TechnologyListVO;

@Service
@Transactional
public class TechnologyService {

    @Autowired
    private TechnologyRepository technologyRepository;

    @Transactional(readOnly = true)
    public TechnologyListVO findAll(int page, int maxResults) {
        Page<Technologies> result = executeQueryFindAll(page, maxResults);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindAll(lastPage, maxResults);
        }

        return buildResult(result);
    }

    public void save(Technologies technologies) {
        technologyRepository.save(technologies);
    }

    @Secured("ROLE_ADMIN")
    public void delete(int technologyID) {
        technologyRepository.delete(technologyID);
    }

    @Transactional(readOnly = true)
    public TechnologyListVO findByTechnologyLike(int page, int maxResults, String technology) {
        Page<Technologies> result = executeQueryFindByTechnology(page, maxResults, technology);

        if(shouldExecuteSameQueryInLastPage(page, result)){
            int lastPage = result.getTotalPages() - 1;
            result = executeQueryFindByTechnology(lastPage, maxResults, technology);
        }

        return buildResult(result);
    }

    private boolean shouldExecuteSameQueryInLastPage(int page, Page<Technologies> result) {
        return isUserAfterOrOnLastPage(page, result) && hasDataInDataBase(result);
    }

    private Page<Technologies> executeQueryFindAll(int page, int maxResults) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByTechnologyASC());

        return technologyRepository.findAll(pageRequest);
    }

    private Sort sortByTechnologyASC() {
        return new Sort(Sort.Direction.ASC, "technology");
    }

    private TechnologyListVO buildResult(Page<Technologies> result) {
        return new TechnologyListVO(result.getTotalPages(), result.getTotalElements(), result.getContent());
    }

    private Page<Technologies> executeQueryFindByTechnology(int page, int maxResults, String technology) {
        final PageRequest pageRequest = new PageRequest(page, maxResults, sortByTechnologyASC());

        return technologyRepository.findByTechnologyLike(pageRequest, "%" + technology + "%");
    }

    private boolean isUserAfterOrOnLastPage(int page, Page<Technologies> result) {
        return page >= result.getTotalPages() - 1;
    }

    private boolean hasDataInDataBase(Page<Technologies> result) {
        return result.getTotalElements() > 0;
    }
}