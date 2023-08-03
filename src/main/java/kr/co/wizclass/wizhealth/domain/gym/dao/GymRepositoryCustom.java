package kr.co.wizclass.wizhealth.domain.gym.dao;


import kr.co.wizclass.wizhealth.domain.gym.dto.FindAllGymResponse;
import kr.co.wizclass.wizhealth.domain.gym.dto.GymSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymRepositoryCustom {
    Page<FindAllGymResponse> findGymsV1(Pageable pageable);
    Page<FindAllGymResponse> findGymsV2(Pageable pageable, GymSearchCondition gymSearchCondition);
    Page<FindAllGymResponse> findGymsV3(Pageable pageable, GymSearchCondition gymSearchCondition);
    Page<FindAllGymResponse> findGymsV4(Pageable pageable, GymSearchCondition gymSearchCondition);

}
