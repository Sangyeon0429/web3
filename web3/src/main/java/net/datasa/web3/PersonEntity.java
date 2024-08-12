package net.datasa.web3;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

// DB의 테이블과 매핑되는 클래스
@Data // Lombok을 사용하여 getter, setter, toString 등의 메서드를 자동 생성
@Entity // JPA를 사용하여 이 클래스가 엔티티임을 나타냄
@Table(name = "person") // 이 클래스가 DB의 "person" 테이블과 매핑됨을 나타냄
public class PersonEntity {

	@Id // 기본 키(Primary Key)를 나타냄
	@Column(name = "id", nullable = false, length = 30) // "id" 열과 매핑, null 불가, 최대 길이 30
	private String id;

	@Column(name = "name", nullable = false, length = 50) // "name" 열과 매핑, null 불가, 최대 길이 50
	private String name;

	@Column(name = "age") // "age" 열과 매핑
	private Integer age;
}