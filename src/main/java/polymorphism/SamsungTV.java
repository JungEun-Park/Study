package polymorphism;

public class SamsungTV implements TV {
	
	private Speaker speaker;
	private int price;
	

	public SamsungTV() {
		System.out.println("===> Samsung TV(1) 객체 생성");
	}
	
	
	
	public void setSpeaker(Speaker speaker) {
		System.out.println("===> SetSpeaker() 호출");
		this.speaker = speaker;
	}



	public void setPrice(int price) {
		System.out.println("===> SetPrice() 호출");
		this.price = price;
	}



	public void powerOn() {
		System.out.println("SamsungTV --- 전원 켠다.(가격: "+price+")");
	}
	public void powerOff() {
		System.out.println("SamsungTV --- 전원 끈다.");
	}
	
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}
}
