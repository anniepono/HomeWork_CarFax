package homeWorkJune24;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UtilityClass {
	
	public static void checkModelList (List actualModelList) {
		List <String> expectedModels = Arrays.asList(" Model 3 ", " Model S ", " Model X ");
		if (actualModelList.containsAll(expectedModels)) {
			System.out.println("PASSED" + "\rExpected List: \r" + expectedModels+ "\rActual List: \r" + actualModelList);
		}
	}
	
	
	
	public static void checkSourceContains (String expectedText, String pageSource) {
		if (pageSource.contains(expectedText)) {
//			System.out.println(pageSource);
//			System.out.println(expectedText);
			System.out.println("PASSED. Page contains " + expectedText);
		} else {
//			System.out.println(pageSource);
//			System.out.println(expectedText);
			System.out.println("FAILED. Text: " + expectedText + " was not found on the page");
			
		}
		
	}

}
