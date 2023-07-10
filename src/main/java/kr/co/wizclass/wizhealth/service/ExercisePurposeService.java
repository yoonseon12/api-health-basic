package kr.co.wizclass.wizhealth.service;

import kr.co.wizclass.wizhealth.domain.dto.exercisePurpose.ExercisePurposeRequest;
import kr.co.wizclass.wizhealth.repository.ExercisePurposeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ExercisePurposeService {
    private final ExercisePurposeRepository exercisePurposeRepository;

    @Transactional
    public void create(ExercisePurposeRequest exercisePurposeRequest) {

    }
}
