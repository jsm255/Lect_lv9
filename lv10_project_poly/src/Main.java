import controllers.GameController;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * 다형성 사용 rpg
		 * 
		 * 타이틀도 있음!
		 * 
		 * 플레이어 캐릭터도 선택가능 (직업이라던가)
		 * 몬스터도 몇가지 종류로 나뉨
		 * 
		 * Unit
		 *  ㄴ 플레이어
		 *     ㄴ 전사라던가
		 *     ㄴ 마법사라던가
		 *     ㄴ 그외라던가
		 *  ㄴ 몬스터
		 *     ㄴ 슬라임 	- 매턴 회복함 (어떤 직업에 회복 저하를 줄 예정)
		 *     ㄴ 도적	- 공격력이 높음 (방어력이 높은 직업이 있음)
		 *     ㄴ 골렘	- 방어력이 높음 (방어력을 낮추거나 공격력이 높은 직업이 있음)
		 *     
		 *
		 * 전투를 시작하면 랜덤으로 몇마리의 몹을 배정해주고 전투를 진행
		 * 
		 */
		
		GameController gc = GameController.getGC();
		gc.run();

	}

}
