package dei.uc.pt.ar;

public class Validator {
	
	public Validator (){}
	
	public static boolean dateValidator(String day, String month, String year){
		boolean checkday=false;
		boolean checkmonth=false;
		boolean checkyear=false;
	    for(char ch :day.toCharArray()){
	        if(Character.isDigit(ch)){
	            checkday = true;
	        }
	    }
	    for(char ch :month.toCharArray()){
	        if(Character.isDigit(ch)){
	            checkmonth = true;
	        }
	    }
	    for(char ch :year.toCharArray()){
	        if(Character.isDigit(ch)){
	            checkyear = true;
	        }
	    }   
		
		if(checkday&&checkmonth&&checkyear){
			return true;
		}else{
			return false;
		}
	}

}
