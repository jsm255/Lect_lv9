import controllers.ShopManager;
import models.Shop;

public class Main {

	public static void main(String[] args) {
		// 상점
		
		// 아이템 - 이름, 가격 (가격은 관리자가 변환 가능)
		// 카테고리 - 이름, 아이템 배열 (ArrayList) (이름 수정 가능)
		// 유저 - 이름, 지불한 총 금액 (금액은 계속해서 추가될 수 있게)
		// 상점 - 상점 이름, 카테고리 배열 (ArrayList), 오늘 매상
		
		
		/*	최종 기능
		 * 1. 회원가입		end
		 * 2. 로그인		end
		 * 3. 로그아웃		end
		 * 4. 상점 이용	(디버그를 위해 기본적인 아이템이 필요)
		 * 5. 관리자 메뉴
		 * 5 - 1. 아이템 관리
		 * 5 - 2. 카테고리 관리
		 * 5 - 3. 장바구니 관리
		 * 5 - 4. 유저 관리
		 * 5 - 4 - 1. 전체 유저 조회
		 * 5 - 4 - 2. 유저 추가 == 회원가입
		 * 5 - 4 - 3. 유저 삭제 == 강제탈퇴
		 * 
		 */
		
		Shop.setName("만물상");
		ShopManager.instance.run();

	}

}
