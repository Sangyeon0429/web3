package net.datasa.web3;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 사용자 정보 관리를 위한 컨트롤러 클래스입니다.
 */
@Controller // Spring MVC의 컨트롤러임을 나타냅니다.
@Slf4j // 로깅 기능을 제공합니다.
@RequiredArgsConstructor // final 필드에 대해 생성자를 자동으로 생성합니다.
public class PersonController {

	private final PersonService personService; // PersonService의 인스턴스를 주입받습니다.

	/**
	 * 테스트 데이터를 DB에 저장하고 루트 페이지로 리다이렉트합니다.
	 *
	 * @return 루트 페이지로 리다이렉트하는 문자열
	 */
	@GetMapping("test")
	public String test() {
		// 서비스의 test 메서드 호출
		personService.test();
		// 루트 페이지로 리다이렉트
		return "redirect:/";
	}

	/**
	 * 사용자 정보를 입력하는 페이지로 이동합니다.
	 *
	 * @return 입력 폼 페이지의 경로 문자열
	 */
	@GetMapping("save")
	public String save() {
		// inputForm 페이지로 이동
		return "inputForm";
	}

	/**
	 * 전달된 사용자 정보를 저장하고 루트 페이지로 리다이렉트합니다.
	 *
	 * @param dto 저장할 사용자 정보를 담고 있는 PersonDTO 객체
	 * @return 루트 페이지로 리다이렉트하는 문자열
	 */
	@PostMapping("save")
	public String save(@ModelAttribute PersonDTO dto) {
		// 전달된 DTO를 로그에 출력
		log.debug("전달된 값: {}", dto);
		// 서비스의 save 메서드 호출
		personService.save(dto);
		// 루트 페이지로 리다이렉트
		return "redirect:/";
	}

	/**
	 * 사용자 정보를 조회하는 페이지로 이동합니다.
	 *
	 * @return 조회 폼 페이지의 경로 문자열
	 */
	@GetMapping("select")
	public String select() {
		// selectForm 페이지로 이동
		return "selectForm";
	}

	/**
	 * 주어진 ID로 사용자 정보를 조회하고 조회 페이지로 이동합니다.
	 *
	 * @param id    조회할 사용자의 ID
	 * @param model Spring MVC의 Model 객체
	 * @return 조회 페이지의 경로 문자열
	 */
	@PostMapping("select")
	public String select(@RequestParam("id") String id, Model model) {
		// 서비스의 select 메서드를 호출하여 DTO를 얻음
		PersonDTO dto = personService.select(id);
		// 모델에 id와 person 속성을 추가
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		// select 페이지로 이동
		return "select";
	}

	/**
	 * 삭제 폼으로 이동합니다.
	 *
	 * @return 삭제 폼 페이지의 경로 문자열
	 */
	@GetMapping("delete")
	public String delete() {
		return "deleteForm";
	}

	/**
	 * 주어진 ID를 가진 사용자 정보를 삭제하고 결과 출력 페이지로 이동합니다.
	 *
	 * @param id    삭제할 사용자의 ID
	 * @param model Spring MVC의 Model 객체
	 * @return 출력할 결과 페이지의 경로 문자열
	 */
	@PostMapping("delete")
	public String delete(@RequestParam("id") String id, Model model) {
		boolean result = personService.delete(id);

		// 삭제 여부를 나타내는 result와 삭제 시도한 id를 모델에 저장
		// html 페이지로 포워딩해서 안내문구 출력
		// 1. xx는 없는 아이디입니다.
		// 2. xx를 삭제했습니다.
		model.addAttribute("id", id);
		model.addAttribute("result", result);
		return "delete";
	}

	/**
	 * 모든 사용자 정보를 조회하여 화면에 표시하는 페이지로 이동합니다.
	 *
	 * @param model Spring MVC의 Model 객체
	 * @return 모든 사용자 정보를 표시하는 페이지의 경로 문자열
	 */
	@GetMapping("selectAll")
	public String selectAll(Model model) {
		// "selectAll" 요청이 들어오면 이 메서드가 실행됩니다. Model 객체를 사용하여 데이터를 뷰로 전달합니다.

		List<PersonDTO> dtoList = personService.selectAll();
		// personService에서 모든 사용자 정보를 가져옵니다. 이 정보는 PersonDTO 객체의 리스트입니다.

		model.addAttribute("dtoList", dtoList);
		// 가져온 사용자 정보를 model에 추가합니다. 이렇게 하면 뷰에서 이 데이터를 사용할 수 있습니다.

		return "selectAll";
		// "selectAll"이라는 이름의 뷰 페이지로 이동합니다. 이 페이지에서 사용자 정보를 표시합니다.
	}

	/**
	 * 주어진 ID로 사용자 정보를 조회하고 select 페이지로 이동합니다.
	 *
	 * @param id    조회할 사용자의 ID
	 * @param model Spring MVC의 Model 객체
	 * @return select 페이지의 경로 문자열
	 */
	@GetMapping("view")
	public String view(@RequestParam("id") String id, Model model) {
		// 서비스의 select 메서드를 호출하여 DTO를 얻음
		PersonDTO dto = personService.select(id);
		// 모델에 id와 person 속성을 추가
		model.addAttribute("id", id);
		model.addAttribute("person", dto);
		// select 페이지로 이동
		return "select";
	}

	/**
	 * 주어진 ID로 사용자 정보를 조회하고 select 페이지로 이동합니다.
	 *
	 * @param id    조회할 사용자의 ID
	 * @param model Spring MVC의 Model 객체
	 * @return select 페이지의 경로 문자열
	 */
	@GetMapping("info" + "/{viewid}")
	public String info(@PathVariable("viewid") String id, Model model) {

		PersonDTO dto = personService.select(id);

		model.addAttribute("id", id);
		model.addAttribute("person", dto);

		return "select";
	}

	/**
	 * 주어진 ID로 사용자 정보를 삭제하고 모든 사용자 정보를 조회하여 selectAll 페이지로 이동합니다.
	 *
	 * @param id    삭제할 사용자의 ID
	 * @param model Spring MVC의 Model 객체
	 * @return selectAll 페이지의 경로 문자열
	 */
	@GetMapping("part_delete")
	public String part_delete(@RequestParam("id") String id, Model model) {
		// 주어진 ID를 사용하여 해당 회원 정보를 삭제합니다.
		personService.delete(id);

		// 삭제 후, 모든 회원 정보를 다시 가져와 리스트에 저장합니다.
		List<PersonDTO> dtoList = personService.selectAll();

		// 삭제된 회원의 ID를 모델에 추가합니다. (필요에 따라 View에서 사용 가능)
		model.addAttribute("id", id);

		// 모든 회원 리스트를 모델에 추가합니다.
		model.addAttribute("dtoList", dtoList);

		// "selectAll" 뷰를 반환합니다. (모든 회원 리스트를 보여주는 페이지로 이동)
		return "selectAll";
	}

	// 수정 페이지로 이동
	@GetMapping("update")
	public String update(@RequestParam("id") String id, Model model) {
		PersonDTO dto = personService.select(id);
		// 모델에 id와 person 속성을 추가
		model.addAttribute("person", dto);
		return "updateForm";
	}

	// 사용자가 폼에 입력한 값을 DB에 저장
	@PostMapping("update")
	public String update(@ModelAttribute PersonDTO dto) {
		// 전달된 DTO를 로그에 출력
		log.debug("전달된 값: {}", dto);
		// 서비스의 save 메서드 호출
		personService.update(dto);
		// 루트 페이지로 리다이렉트

		// "view?id=abc"
		return "redirect:/view?id=" + dto.getId();
	}
}