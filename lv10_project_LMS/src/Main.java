import controllers.SystemManager;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * 학생 관리 시스템 - 다만 상속이 없었던
		 * 
		 * 상속 적용해서 다시!
		 * 다른 클래스들에 공통되는 부분이 있다면 해당 공통 부분을 상속을 시켜서 만들 수 있음
		 * 
		 * Student와 Subject 클래스를 사용함.
		 * 
		 * 
		 * 1. 학생 - 이름, 코드
		 * 2. 과목 - 과목 이름, (코드), 점수
		 * 
		 * 있어야하는 기능
		 * 학생 등록 (new Student)			- 완료
		 * 이름을 입력해서 간단하게 들어가는 로그인도 있으면 좋을 듯	- 완료
		 * 수강 신청 (new Subject)			- 진행중
		 * 성적 관리 (score 추가 또는 수정)	- 
		 * 
		 */

		SystemManager sm = SystemManager.getSM();
		sm.run();
		
	}

}
