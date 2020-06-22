package homeWork_June21;

public class Utilities {
	
	public static String nameGenerator () {
		String name = "John ";
		name += (char)(65 + (int)(Math.random()*26));
		
		for (int i = 0; i < 4; i++) {
			name += (char)(97 + (int)(Math.random()*26));
		}
		
		name +=" Doe";
		return name;
	}
	
	public static String zipCodGenerator () {
		String zipCode = "";
		for (int i = 0; i<5; i++) {
		int randomNum = (int)(Math.random()*10); // generates random num 0-9 
		zipCode += String.valueOf(randomNum);
		}
		return zipCode;
		
	}


	public static String creditCardGenerator (int type) {

// 			1 = VISA --> 16 digits starts with 4
//			2 = MASTER CARD --> 16 digits starts with 5
//			3 = American Express --> 15 digits starts with 3
		String cardNum = "";
		int length;
		if (type == 3) {
			length = 15;
			cardNum += '3';
		} else if (type == 2) {
			length = 16;
			cardNum += 5;
		} else if (type == 1) {
			length = 16;
			cardNum += 4;
		} else 
		length = 1;
		
		for (int i = 0; i<(length-1); i++) {
			int randomNum = (int)(Math.random()*10); // generates random num 0-9 
			cardNum += String.valueOf(randomNum);
			}
		
		System.out.println(type);
		System.out.println(cardNum);
		return cardNum;
	}
	
	
	
	public static String ExpirationDateGenerator() {
		return "";
	}
	
	
	
	public static void assertEqualsText (String expected, String actual) {
		
		if (actual.equals(expected)) {
			System.out.println("PASSED");
		} else {
			System.out.println("FAILED\n" + "Expected:\n" + expected + "\n" + "Actual:\n" + actual );
		}}
}
