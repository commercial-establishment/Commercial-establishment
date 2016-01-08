package kz.hts.ce.service;

import kz.hts.ce.entity.Category;
import kz.hts.ce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.data.history.Revisions;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository> {

    @Autowired
    protected CategoryService(CategoryRepository repository) {
        super(repository);
    }

    public List<Category> findAll() {
        return repository.findAll();
    }

    public Category findByName(String categoryName) {
        return repository.findByName(categoryName);
    }

    public void delete(UUID id) {
        repository.delete(id);
    }

    public List<Category> getHistory(long time) {
        List<Category> allCategories = findAll();
        List<Category> categories = new ArrayList<>();
        for (Category categoryFromAllCategories : allCategories) {
            Revisions<Integer, Category> revisions = repository.findRevisions(categoryFromAllCategories.getId());
            List<Revision<Integer, Category>> revisionList = revisions.getContent();
            Category category = null;
            for (Revision<Integer, Category> revision : revisionList) {
                long dateTimeInMillis = revision.getMetadata().getRevisionDate().getMillis();
                if (time < dateTimeInMillis) {
                    category = revision.getEntity();
                }
            }
            if (category != null) categories.add(category);
        }

        return categories;
    }
}
