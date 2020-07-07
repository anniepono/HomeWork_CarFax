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
	
	
	
	public static List<Double> getDoubleMiles (List<String> sortedMiles) {	
		List <Double> doubleMiles = new ArrayList<Double>();
		for (String s: sortedMiles) {
		s = s.substring(s.indexOf(' ')+1);
		s = s.substring(0, s.indexOf(' '));
		s = s.replaceAll(",", "");
		double d = Double.parseDouble(s);
		doubleMiles.add(d);
		}
		System.out.println(doubleMiles);
		return doubleMiles;
		
	}

}
