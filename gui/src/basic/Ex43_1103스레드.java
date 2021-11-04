package basic;

// Thread
// Thread를 상속시킬 수 있게 만들어보기

// 스레드가 두 개가 돌아가는 예제이다!

class PlayGame extends Thread { 
	// Thread는 일반 상속으로 받을 수 있음
	
	public boolean play;
	
	public PlayGame() {
		// TODO Auto-generated constructor stub
	}
	
	// thread가 가진 메서드 중 run을 가져옴
	@Override
	public void run() {
		this.play = true;
		
		while(this.play) {			// 스레드 1
			System.out.println("정신없이 즐기는 중 >>>");
			try {
				sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
}

class PlayMusic implements Runnable {
	// 얘는 인터페이스로 받을 수 있음
	// 얘가 확장성이 훨씬 좋다!!
	
	public boolean play;

	@Override
	public void run() {
		play = true;
		
		while(play) {
			System.out.println("음악이 흐르고...");
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}	
}

public class Ex43_1103스레드 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		PlayGame play = new PlayGame();
////		play.run();		// X
//		play.start();		// Thread의 메서드는 보통 start로 구동을 시킨다
//		
//		for(int n = 0; n<10; n++) {		// 스레드 2
//			System.out.println("n : " + n);
//			if(n == 8) {
//				System.out.println("앗, 엄마가 등장했다!");
////				play.stop();		// 더 이상 쓰지 않는 구문이다.
//				play.play = false;
//			}
//			
//			try {
//				Thread.sleep(1000);
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
//		}
		
//		PlayMusic que = new PlayMusic();
//		que.run(); // 얘는 또 run임 일관성이 없음
		
		Runnable music = new PlayMusic();	// (업 캐스팅으로) runnable을 하나 만듦
		Thread thread = new Thread(music);	// thread에 집어 넣음
		thread.start();						// 히히 발사!
		
		for(int n = 0; n<10; n++) {
			System.out.println("n : " + n);
			
			if(n == 7) {
				System.out.println("선생님 등장!");
				// ? stop()을 쓰지 않고 -> 스레드를 종료 (상속 개념으로)
				// music 이 PlayMusic으로 형변환이 가능하면 true
				if(music instanceof PlayMusic) {	// 타입 캐스팅을 할때는 꼭 검증이 필요하다!!!!
					PlayMusic stop = (PlayMusic) music;		// 다운 캐스팅
					stop.play = false;
				}
			}
			
			try {
				Thread.sleep(1000);
				
			} catch (Exception e) {
				// TODO: handle exception
			} 
		}
	}

}
