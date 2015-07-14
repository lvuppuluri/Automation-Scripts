package data;

import org.testng.annotations.DataProvider;

import calc.Calc;

public class calcdata {

		@DataProvider (name="calcdata")
		public static Object[][] calcdata() throws Exception{
		        Object[][] dataArray=Calc.getExcelArray("C:/Users/lakshmi/Documents/Workspace/Leap/src/test/resources/inscalc.xlsx",
		                "inscalc");
		        return(dataArray);
		}
	}
