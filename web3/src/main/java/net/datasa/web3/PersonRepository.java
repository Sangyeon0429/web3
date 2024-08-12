package net.datasa.web3;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Spring에서 이 인터페이스가 리포지토리임을 나타냄
public interface PersonRepository extends JpaRepository<PersonEntity, String> {
	// JpaRepository를 확장하여 기본적인 CRUD 기능을 제공
	// PersonEntity는 엔티티 타입, String은 엔티티의 기본 키 타입
}