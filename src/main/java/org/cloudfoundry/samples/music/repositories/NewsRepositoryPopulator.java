package org.cloudfoundry.samples.music.repositories;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.cloudfoundry.samples.music.domain.News;
import org.cloudfoundry.samples.music.repositories.jpa.JpaNewsRepository;
import org.cloudfoundry.samples.music.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

@Component
public class NewsRepositoryPopulator implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {

    private static final String NEWS_URL = "NEWS_URL";
    private static final String VCAP_SERVICES = "VCAP_SERVICES";
    private ApplicationContext applicationContext;
    private static final Logger logger = LoggerFactory.getLogger(NewsRepositoryPopulator.class);

    public NewsRepositoryPopulator() {
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;

    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        List<News> retrievedNews = getNews(event.getApplicationContext().getEnvironment());
        
        if (event.getApplicationContext().equals(applicationContext)) {
            JpaNewsRepository repo = BeanFactoryUtils.beanOfTypeIncludingAncestors(applicationContext, JpaNewsRepository.class);
            if (repo != null && repo.count() == 0) {
                for (News news : retrievedNews) {
                    repo.save(news);
                }
            }
        }
    }

    public List<News> getNews(Environment env) {
        List<News> result = new ArrayList<News>();

        try {
            String internalNewsUrl = env.getProperty(NEWS_URL);
            String internalNewsJson = JsonUtil.getJsonContentFromURL(internalNewsUrl);
            logger.info("Internal news");
            logger.info(internalNewsJson);
            News[] internalNews = new Gson().fromJson(internalNewsJson, News[].class);
            result = new ArrayList<News>(Arrays.asList(internalNews));

            String externalNewsUrl = JsonUtil.getVCAPServicesURL(env.getProperty(VCAP_SERVICES));
            String externalNewsJson = JsonUtil.getJsonContentFromURL(externalNewsUrl);
            logger.info("External news");
            logger.info(externalNewsJson);
            News[] externalNews = new Gson().fromJson(externalNewsJson, News[].class);

            result.addAll(Arrays.asList(externalNews));
        } catch (IOException e) {
            logger.error(e.getMessage());
            return result;
        }

        return result;
    }
}
