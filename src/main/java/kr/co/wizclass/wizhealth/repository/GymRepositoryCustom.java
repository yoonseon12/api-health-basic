package kr.co.wizclass.wizhealth.repository;


import kr.co.wizclass.wizhealth.domain.dto.gym.FindAllGymResponse;
import kr.co.wizclass.wizhealth.domain.entity.Gym;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymRepositoryCustom {
    Page<FindAllGymResponse> findGyms(Pageable pageable);

}
