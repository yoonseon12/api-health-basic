package kr.co.wizclass.wizhealth.domain.dto.gym;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class GymSearchCondition {
    private String gymName;
    private List<Long> searchCategories;
}
