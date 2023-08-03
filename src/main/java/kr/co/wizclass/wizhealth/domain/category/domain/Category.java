package kr.co.wizclass.wizhealth.domain.category.domain;

import kr.co.wizclass.wizhealth.global.common.domain.BaseEntity;
import kr.co.wizclass.wizhealth.global.common.domain.DelYn;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@SQLDelete(sql = "update category set del_yn = 'Y' where id = ?")
@Table(name = "category")
public class Category extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "category_name", columnDefinition = "varchar(255)")//, length = 255)
    private String categoryName;

    @Enumerated(EnumType.STRING)
    @Column(name = "del_yn")
    private DelYn delYn;

    @Builder
    public Category(Long id, String categoryName, DelYn delYn) {
        this.id = id;
        this.categoryName = categoryName;
        this.delYn = delYn;
    }

    public void updateCategory(Category updateParam) {
        this.categoryName = updateParam.getCategoryName();
    }
}
