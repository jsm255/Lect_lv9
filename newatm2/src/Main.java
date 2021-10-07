import controller.BankManager;
import models.Bank;

public class Main {

	public static void main(String[] args) {
		
		/*
		 * atm
		 * 
		 * 1. 회원가입 / 회원탈퇴
		 * 2. 로그인 / 로그아웃
		 * 3. 계좌개설 / 철회(회원당 3개 계좌 제한)
		 * 4. 뱅킹기능 (입금/출금/이체/조회) ====> 이체 해야됨
		 * 5. 파일처리 (저장/로드)
		 * 6. 관리자모드 (admin/0000 -> 전체 유저 조회 / 전체 계좌 조회)
		 * 
		 */
		
		/* 
		 * models 패키지 안에 객체화할 클래스들을 정의
		 * ㄴ User
		 * ㄴ Account
		 * ㄴ Bank
		 */
		
		/*
		 * controller 패키지 안에 객체 처리를 할 기능(메소드) 정의
		 * ㄴ UserManager (User 타입의 객체배열)
		 * ㄴ AccountManager (Account 타입의 객체배열)
		 * ㄴ BankManager
		 * ㄴ FileManager
		 */
		
		Bank.setName("GREEN");
		BankManager.instance.run();

	}

}
