import controllers.TowerController;

public class Main {

	public static void main(String[] args) {
		
		// 좀비와의 전투
				
		// 좀비와 용사는 둘 다 unit을 상속받음
		// 스탯은 이름, hp, atk, def, pos(층수)
				
		// interface를 사용하면 좀비들의 행동을 다 줄 수 있다.
				
		// 처음 적을 생성할 때 주어진 pos에 따라서 나오는 적의 종류가 정해짐.
		// 매층에 나오지는 않음.
		// 꼭 이런 방식일 필요는 없을지도
		
		// 	1	2	3	4	5	6	7	8	9	10	11	12
		//	X	X	Z	X	X	Z	X	X	Z	X	X	Z
		
		// class Unit (유닛의 기본 베이스)
		// interface fight (애들이 전부 가질 메서드)
		// abstract class special (뭔가 독특한 시스템을 가진 친구들 = 보스, 용사)
		
		// 용사는 cnt라는 멤버 변수를 가짐, 포션 수를 뜻함
		// 좀비킹은 shield를 가짐, 쉴드임.
		// 데미지를 받을 때 마다 일정 데미지만큼은 쉴드에서 빠지게 하는 것도 괜찮을듯
		
		TowerController tc = TowerController.getTC();
		tc.run();
		
	}

}
