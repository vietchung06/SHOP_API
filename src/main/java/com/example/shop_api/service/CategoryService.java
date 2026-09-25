package com.example.shop_api.service;

import com.example.shop_api.JPA.entity.CategoryEntity;
import com.example.shop_api.entity.Category;
import com.example.shop_api.entity.Product;
import com.example.shop_api.exception.CategoryInUseException;
import com.example.shop_api.exception.CategoryNotFoundException;
import com.example.shop_api.exception.InvalidCategoryException;
import com.example.shop_api.exception.InvalidCustomerException;
import com.example.shop_api.repository.CategoryRepository;
import com.example.shop_api.repository.CategorysRepository;
import com.example.shop_api.repository.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
  private final CategorysRepository categorysRepository;
  private final ProductRepository productRepository;

    public CategoryService(CategorysRepository categorysRepository, ProductRepository productRepository) {
        this.categorysRepository = categorysRepository;
        this.productRepository = productRepository;
    }

    public List<CategoryEntity> getAll(){
        return categorysRepository.findAll();
    }
    public CategoryEntity getById(Long id){
      return categorysRepository.findById(id)
               .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục"));
    }
    public CategoryEntity create(CategoryEntity categoryEntity){
        if (categoryEntity.getFullName() == null || categoryEntity.getFullName().isBlank()){
            throw new InvalidCategoryException("Tên không được để trống");
        }
        return categorysRepository.save(categoryEntity);
    }

    public CategoryEntity update(Long id, CategoryEntity categoryEntity){
        if (categoryEntity == null){
            throw new InvalidCategoryException("Không được để trống thông tin khách hàng");
        }
        CategoryEntity oldCategory = getById(id);
        if (categoryEntity.getFullName() == null || categoryEntity.getFullName().isBlank()){
            throw new InvalidCategoryException("Tên không được để trống");
        }
        oldCategory.setFullName(categoryEntity.getFullName());
        oldCategory.setDescription(categoryEntity.getDescription());
        return categorysRepository.save(oldCategory);

    }
    public void deleteById(Long id){
        CategoryEntity category = categorysRepository.findById(id)
                        .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục id: "+ id));
        long count = productRepository.countByCategoryId(id);
        if (count > 0 ){
            throw new CategoryInUseException("Danh mục không thể xóa vì còn "+ count + "sản phẩm");
        }
         categorysRepository.deleteById(id);
    }

    //  Đếm sản phẩm theo categoryId
    public List<String> getCategoryWithProductCount(){
        List<CategoryEntity> categories = categorysRepository.findAll();
        List<String> result = new ArrayList<>();
        for (CategoryEntity category : categories){
            long count = productRepository.countByCategoryId(category.getId());
            result.add(
                    category.getFullName()
                            + " - "
                            + count
                            + " sản phẩm"
            );
        }
        return result;
        }
    //chuyển toàn bộ sản phẩm từ danh mục A sang B
    @Transactional
      public void moveProduct(Long from, Long to){
          CategoryEntity categoryFrom = categorysRepository.findById(from)
                  .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục muốn lấy: "+ from));
          CategoryEntity categoryTo = categorysRepository.findById(to)
                  .orElseThrow(()-> new CategoryNotFoundException("Không tìm thấy danh mục muốn nhận: "+ to));
          List<Product> products = productRepository.findByCategoryId(categoryFrom.getId());
          for (Product product : products) {
              product.setCategory(categoryTo);
              productRepository.save(product);
          }

      }

    }


