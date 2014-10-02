package uaiContacts.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import uaiContacts.model.Technologies;
import uaiContacts.service.TechnologyService;
import uaiContacts.vo.TechnologyListVO;

import java.util.Locale;

@Controller
@RequestMapping(value = "/protected/technology")
public class TechnologyController {

    private static final String DEFAULT_PAGE_DISPLAYED_TO_USER = "0";

    @Autowired
    private TechnologyService technologyService;

    @Autowired
    private MessageSource messageSource;

    @Value("5")
    private int maxResults;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView welcome() {
        return new ModelAndView("technologyList");
    }

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAll(@RequestParam int page, Locale locale) {
        return createListAllResponse(page, locale);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> create(@ModelAttribute("technology") Technologies technology,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        technologyService.save(technology);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.create.success");
        }

        return createListAllResponse(page, locale, "message.create.success");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
    public ResponseEntity<?> update(@PathVariable("id") int technologyId,
                                    @RequestBody Technologies technology,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        if (technologyId != technology.getId()) {
            return new ResponseEntity<String>("Bad Request", HttpStatus.BAD_REQUEST);
        }

        technologyService.save(technology);

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.update.success");
        }

        return createListAllResponse(page, locale, "message.update.success");
    }

    @RequestMapping(value = "/{technologyId}", method = RequestMethod.DELETE, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable("technologyId") int technologyId,
                                    @RequestParam(required = false) String searchFor,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {


        try {
            technologyService.delete(technologyId);
        } catch (AccessDeniedException e) {
            return new ResponseEntity<Object>(HttpStatus.FORBIDDEN);
        }

        if (isSearchActivated(searchFor)) {
            return search(searchFor, page, locale, "message.delete.success");
        }

        return createListAllResponse(page, locale, "message.delete.success");
    }

    @RequestMapping(value = "/{technology}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> search(@PathVariable("technology") String technology,
                                    @RequestParam(required = false, defaultValue = DEFAULT_PAGE_DISPLAYED_TO_USER) int page,
                                    Locale locale) {
        return search(technology, page, locale, null);
    }

    private ResponseEntity<?> search(String technology, int page, Locale locale, String actionMessageKey) {
        TechnologyListVO technologyListVO = technologyService.findByTechnologyLike(page, maxResults, technology);

        if (!StringUtils.isEmpty(actionMessageKey)) {
            addActionMessageToVO(technologyListVO, locale, actionMessageKey, null);
        }

        Object[] args = {technology};

        addSearchMessageToVO(technologyListVO, locale, "message.search.for.active", args);

        return new ResponseEntity<TechnologyListVO>(technologyListVO, HttpStatus.OK);
    }

    private TechnologyListVO listAll(int page) {
        return technologyService.findAll(page, maxResults);
    }

    private ResponseEntity<TechnologyListVO> returnListToUser(TechnologyListVO technologyListVO) {
        return new ResponseEntity<TechnologyListVO>(technologyListVO, HttpStatus.OK);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale) {
        return createListAllResponse(page, locale, null);
    }

    private ResponseEntity<?> createListAllResponse(int page, Locale locale, String messageKey) {
        TechnologyListVO technologyListVO = listAll(page);

        addActionMessageToVO(technologyListVO, locale, messageKey, null);

        return returnListToUser(technologyListVO);
    }

    private TechnologyListVO addActionMessageToVO(TechnologyListVO technologyListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return technologyListVO;
        }

        technologyListVO.setActionMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return technologyListVO;
    }

    private TechnologyListVO addSearchMessageToVO(TechnologyListVO technologyListVO, Locale locale, String actionMessageKey, Object[] args) {
        if (StringUtils.isEmpty(actionMessageKey)) {
            return technologyListVO;
        }

        technologyListVO.setSearchMessage(messageSource.getMessage(actionMessageKey, args, null, locale));

        return technologyListVO;
    }

    private boolean isSearchActivated(String searchFor) {
        return !StringUtils.isEmpty(searchFor);
    }
}