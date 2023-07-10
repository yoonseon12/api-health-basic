package kr.co.wizclass.wizhealth.repository;


import kr.co.wizclass.wizhealth.repository.quertdslDto.GymDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GymRepositoryCustom {
    Page<GymDTO> findGym(Pageable pageable);
}
