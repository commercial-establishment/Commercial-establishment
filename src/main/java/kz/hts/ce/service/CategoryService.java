package kz.hts.ce.service;

import kz.hts.ce.entity.Category;
import kz.hts.ce.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService extends BaseService<Category, CategoryRepository>{

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

    public void delete(UUID id){ repository.delete(id); }
}
