public class User220003 extends Object {
	
	public static void perform() {
		
		System.out.println("＜＜＜ R2XXXX ○○○○3 ＞＞＞");
		
		ClockMachineForGregorian gregorianClock = new ClockMachineForGregorian();
		gregorianClock.perform();
		
		ClockMachineForJapanese japaneseClock = new ClockMachineForJapanese();
		japaneseClock.perform();
		
		ClockMachineForIzumonian izumonianClock = new ClockMachineForIzumonian();
		izumonianClock.perform();
		
		ClockMachineForBuddhist buddhistClock = new ClockMachineForBuddhist();
		buddhistClock.perform();
		
	}
	
}