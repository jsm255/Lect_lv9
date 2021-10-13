import controllers.GameMaster;
import models.Member;

public class Main {

	public static void main(String[] args) {
		
		/* 
		 * Member : 멤버 이름, 체력, 공격력, 방어력, 장비 슬롯(3개 정도) - 장비 유무에 따라 스탯 변화
		 * 			파티중 여부(파티는 4명까지)
		 * (클래스, 컨트롤러 필요)
		 * -------> hp를 랜덤으로 먼저 돌리고 hp수치에 따라 대충 직업을 나눠서 스탯을 주면 될듯
		 * 
		 * Guild : 길드원 조회, 길드원 추가(이름, 스탯 랜덤, 장비는 주지 말자), 길드원 삭제
		 * 			파티원 교체, 정렬(정렬은 레벨 순이 낫겠다)
		 * (컨트롤러만 필요)
		 * 
		 * Store : 장비 슬롯에 해당하는 물품을 사거나 팔 수 있음
		 * 			(구매는 무기 따로 방어구 따로지만, 판매는 한꺼번에 가능)
		 * (장비 클래스 필요, 상점 컨트롤러 필요)
		 * 
		 * Equipment : 장비 => 장비에 자신이 가지고 있는 개수를 표시해주는게 좋겠다
		 * 
		 * SAVE / LOAD
		 * 
		 */
		
		GameMaster game = new GameMaster();
		game.run();
	}

}
