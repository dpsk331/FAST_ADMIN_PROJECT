package com.example.study.repository;

import com.example.study.StudyApplicationTests;
import com.example.study.model.entity.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends StudyApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type = "COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assertions.assertNotNull(newCategory);
        Assertions.assertEquals(newCategory.getType(), type);
        Assertions.assertEquals(newCategory.getTitle(), title);

    }

    @Test
    public void read() {

        String type = "COMPUTER";

        // Optional<Category> optionalCategory = categoryRepository.findById(1L);

        // ▲ 실제 검색 시 Long 형태의 Id가 아닌
        // 주로 select * from category where type = 'COMPUTER'; 와 같은 형태로 검색
        // 위와 같은 형태를 QueryMethod로 생성 ▼

        Optional<Category> optionalCategory = categoryRepository.findByType("COMPUTER");


        optionalCategory.ifPresent(c -> {
            Assertions.assertEquals(c.getType(), type);

            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });

    }
}
