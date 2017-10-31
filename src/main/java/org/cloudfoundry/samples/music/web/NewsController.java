package org.cloudfoundry.samples.music.web;

import org.cloudfoundry.samples.music.domain.News;
import org.cloudfoundry.samples.music.repositories.jpa.JpaNewsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/newsResource")
public class NewsController {
    private static final Logger logger = LoggerFactory.getLogger(NewsController.class);
    private JpaNewsRepository repository;

    @Autowired
    public NewsController(JpaNewsRepository repository) {
        this.repository = repository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<News> news() {
        return repository.findAll();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public News getById(@PathVariable String id) {
        logger.info("Getting news " + id);
        return repository.findOne(id);
    }
}
