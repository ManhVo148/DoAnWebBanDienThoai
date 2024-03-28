package WebsiteBanDienThoai.service.impl;

import WebsiteBanDienThoai.entity.Category;
import WebsiteBanDienThoai.entity.Product;
import WebsiteBanDienThoai.repository.CategoryRepository;
import WebsiteBanDienThoai.repository.ProductRepository;
import WebsiteBanDienThoai.service.CategoryService;
import WebsiteBanDienThoai.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    @Override
    public Category getCateById(int id) {
        Optional<Category> optional = categoryRepository.findById(id);
        Category category=null;
        if(optional.isPresent()){
            category=optional.get();

        }else{
            throw new RuntimeException("Product not found by id::"+id);
        }
        return category;
    }

    @Override
    public Category saveCate(Category category) {
        return this.categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(int id){
        this.categoryRepository.deleteById(id);
    }

    @Override
    public void updateCategory(Category category) {
        this.categoryRepository.save(category);
    }

}