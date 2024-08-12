package net.datasa.web3;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok을 사용하여 getter, setter, toString 등의 메서드를 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 갖는 생성자를 자동 생성
@NoArgsConstructor // 기본 생성자를 자동 생성
public class PersonDTO {
	String id; // 사용자 ID
	String name; // 사용자 이름
	int age; // 사용자 나이
}