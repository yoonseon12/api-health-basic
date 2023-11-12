package study.todo.domain.gym.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GymSearchCondition {
    private String gymName;
    private List<Long> searchCategories;
}
