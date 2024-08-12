package net.datasa.web3;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

/**
 * 사용자 정보 관련 처리를 담당하는 서비스 클래스입니다.
 */
@Service // Spring의 서비스 레이어를 나타내는 어노테이션입니다.
@RequiredArgsConstructor // final 필드에 대해 생성자를 자동으로 생성합니다.
@Transactional // 트랜잭션 관리를 위한 어노테이션입니다.
public class PersonService {

	private final PersonRepository personRepository; // PersonRepository 인스턴스를 주입받습니다.

	/**
	 * 객체를 생성해서 DB에 저장합니다.
	 */
	public void test() {
		// 새로운 PersonEntity 객체 생성
		PersonEntity entity = new PersonEntity();
		entity.setId("abcde");
		entity.setName("김길동");
		entity.setAge(30);

		// 객체를 DB에 저장
		personRepository.save(entity);
	}

	/**
	 * PersonDTO 객체를 받아서 DB에 저장합니다.
	 *
	 * @param dto 저장할 PersonDTO 객체
	 */
	public void save(PersonDTO dto) {
		// PersonDTO를 PersonEntity로 변환
		PersonEntity entity = new PersonEntity();
		entity.setId(dto.getId());
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());

		// 변환된 객체를 DB에 저장
		personRepository.save(entity);
	}

	/**
	 * 주어진 ID로 사용자 정보를 조회하여 PersonDTO로 반환합니다.
	 *
	 * @param id 조회할 사용자의 ID
	 * @return 조회된 사용자 정보를 담고 있는 PersonDTO 객체
	 */
	public PersonDTO select(String id) {
		// ID로 PersonEntity 조회, 없으면 null 반환
		PersonEntity entity = personRepository.findById(id).orElse(null);

		if (entity == null)
			return null;

		// 조회된 엔티티를 DTO로 변환
		PersonDTO dto = new PersonDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setAge(entity.getAge());

		// 변환된 DTO 반환
		return dto;
	}

	/**
	 * 주어진 ID를 가진 사용자 정보를 삭제합니다.
	 *
	 * @param id 삭제할 사용자의 ID
	 * @return 삭제 성공 여부를 나타내는 boolean 값
	 */
	public boolean delete(String id) {
		boolean result = personRepository.existsById(id);

		if (result) {
			personRepository.deleteById(id);
		}
		return result;
	}

	/**
	 * 모든 사용자 정보를 조회하여 PersonDTO 리스트로 반환합니다.
	 *
	 * @return 모든 사용자 정보를 담고 있는 PersonDTO 객체들의 리스트
	 */
	public List<PersonDTO> selectAll() {
		// 모든 사용자 정보를 조회하여 PersonDTO 리스트로 반환합니다.

		List<PersonEntity> entityList = personRepository.findAll();
		// personRepository를 사용하여 데이터베이스에서 모든 사용자 엔티티를 조회합니다.
		// 이 엔티티들은 PersonEntity 객체의 리스트로 반환됩니다.

		List<PersonDTO> dtoList = new ArrayList<>();
		// PersonDTO 객체들을 저장할 새로운 리스트를 생성합니다.

		for (PersonEntity entity : entityList) {
			// entityList에 있는 각 PersonEntity 객체에 대해 반복합니다.

			PersonDTO dto = new PersonDTO();
			// 각 PersonEntity 객체를 PersonDTO 객체로 변환합니다.
			dto.setId(entity.getId());
			dto.setName(entity.getName());
			dto.setAge(entity.getAge());
			// 변환한 PersonDTO 객체는 dtoList에 추가됩니다.
			dtoList.add(dto);
		}

		return dtoList;
		// 변환된 PersonDTO 객체들의 리스트를 반환합니다.
	}

	/**
	 * 사용자 정보를 ID 기준으로 수정
	 * 
	 * @param dto
	 */
	public void update(PersonDTO dto) {
		PersonEntity entity = personRepository.findById(dto.getId())
				.orElseThrow(() -> new EntityNotFoundException("없는 ID"));
		// dto의 수정할 정보를 entity에 세팅
		entity.setName(dto.getName());
		entity.setAge(dto.getAge());
		// entity저장
		personRepository.save(entity);

	}
}