import controllers.SystemManager;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * 학생 관리 시스템 - 다만 상속을 쓰는
		 * 
		 * 1. 학생 - 이름, 코드
		 * 2. 과목 - 과목 이름, (코드), 점수
		 * 
		 * 있어야하는 기능
		 * 학생 등록 (new Student)
		 * 수강 신청 (new Subject)
		 * 성적 관리 (score 추가 또는 수정)
		 * 
		 */

		SystemManager sm = SystemManager.getSM();
		sm.run();
		
	}

}
