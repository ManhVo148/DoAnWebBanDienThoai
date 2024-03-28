package WebsiteBanDienThoai.service;

import WebsiteBanDienThoai.entity.Category;
import WebsiteBanDienThoai.entity.Product;
import WebsiteBanDienThoai.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface CategoryService  {
    public List<Category> getAllCategories();
    public Category getCateById(int id);
    public Category saveCate(Category category);
    public void deleteCategoryById(int id);

    public void updateCategory(Category category);
}
