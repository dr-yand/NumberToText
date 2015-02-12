package transformation.util;

import java.util.HashMap;
import java.util.Map;

public class NumTextTransformation {
	private static Map<Integer,String> hm100 = new HashMap<Integer, String>();
	private static Map<Integer,String> hm10 = new HashMap<Integer, String>();
	private static Map<Integer,String> hm1m = new HashMap<Integer, String>();
	private static Map<Integer,String> hm1t = new HashMap<Integer, String>();
	static {
		
		/*Названия сотен*/
		hm100.put(9, "девятьсот");
		hm100.put(8, "восемьсот");
		hm100.put(7, "семьсот");
		hm100.put(6, "шестьсот");
		hm100.put(5, "пятьсот");
		hm100.put(4, "четыреста");
		hm100.put(3, "триста");
		hm100.put(2, "двести");
		hm100.put(1, "сто");		
		
		/*Названия десятков */
		hm10.put(9, "девяносто");
		hm10.put(8, "восемьдесят");
		hm10.put(7, "семьдесят");
		hm10.put(6, "шестьдесят");
		hm10.put(5, "пятьдесят");
		hm10.put(4, "сорок");
		hm10.put(3, "тридцать");
		hm10.put(2, "двадцать");
		hm10.put(19, "девятнадцать");
		hm10.put(18, "восемнадцать");
		hm10.put(17, "семнадцать");
		hm10.put(16, "шестнадцать");
		hm10.put(15, "пятнадцать");
		hm10.put(14, "четырнадцать");
		hm10.put(13, "тринадцать");
		hm10.put(12, "двенадцать");
		hm10.put(11, "одиннадцать");
		hm10.put(10, "десять");
		
		/*Названия тысяч для чисел от 1 000 до 9 000*/
		hm1t.put(9, "девять");
		hm1t.put(8, "восемь");
		hm1t.put(7, "семь");
		hm1t.put(6, "шесть");
		hm1t.put(5, "пять");
		hm1t.put(4, "четыре");
		hm1t.put(3, "три");
		hm1t.put(2, "две");
		hm1t.put(1, "одна");
		
		/*Названия миллионов для чисел от 1 000 000 до 9 000 000*/
		hm1m.put(9, "девять");
		hm1m.put(8, "восемь");
		hm1m.put(7, "семь");
		hm1m.put(6, "шесть");
		hm1m.put(5, "пять");
		hm1m.put(4, "четыре");
		hm1m.put(3, "три");
		hm1m.put(2, "два");
		hm1m.put(1, "один");
	}
	
	/**
	 * <b>numberToText</b>
	 * Процедура перевода числа в текстовое представление
	 * @param - число от 0 до 999 999 999
	 * @return - текстовое представление числа или ошибку NumTextTransformException если входное число больше 999 999 999
	 */
	
	public static String numberToText(int num) throws NumTextTransformException{
		//Получаем знак числа
		int sign=(num<0?-1:1);
		num=Math.abs(num);
		
		//Получаем количество миллионов, тысяч и сотен
		int millions=num/1000000;
		int thousands=num%1000000/1000;
		int hundreds=num%1000;
		
		String result="";
		 
		if(millions>0){
			//Выделяем количество сотен, десятков и единиц в миллионе
			//915 тысяч
			int d1=millions/100;//9 
			int d2=millions%100;//15
			int d3=millions%100/10;//1
			int d4=millions%10;//5
			
			if(d1>0)result+=hm100.get(d1);
			
			if(hm10.get(d2)!=null&&d2>9){
				result+=" "+hm10.get(d2);
				result+=" миллионов";
			}
			else{
				if(d3>0)result+=" "+hm10.get(d3);
				if(d4>0)result+=" "+hm1m.get(d4);
				
				if(d4==0)result+=" миллионов";
				if(d4==1)result+=" миллион";
				if(d4>1&&d4<5)result+=" миллиона";
				if(d4>=5)result+=" миллионов";
			}
		}
 
		if(thousands>0){
			int d1=thousands/100; 
			int d2=thousands%100; 
			int d3=thousands%100/10;
			int d4=thousands%10;
			
			if(d1>0)result+=" "+hm100.get(d1); 
			
			if(hm10.get(d2)!=null&&d2>9){
				result+=" "+hm10.get(d2);
				result+=" тысяч";
			}
			else{
				if(d3>0)result+=" "+hm10.get(d3);
				if(d4>0)result+=" "+hm1t.get(d4);
				
				if(d4==0)result+=" тысяч";
				if(d4==1)result+=" тысяча";				
				if(d4>1&&d4<5)result+=" тысячи";
				if(d4>=5)result+=" тысяч";
			}
		}
		if(hundreds>0){ 
			int d1=hundreds/100;
			int d2=hundreds%100;
			int d3=hundreds%100/10;
			int d4=hundreds%10;
			
			if(d1>0)result+=" "+hm100.get(d1);
			
			if(hm10.get(d2)!=null&&d2>9){
				result+=" "+hm10.get(d2);
			}
			else{ 
				if(d3>0)result+=" "+hm10.get(d3);
				if(d4>0)result+=" "+hm1m.get(d4);
			}
		}
		if (num==0){
			result="ноль";
		}
		if(sign<0){
			result="минус "+result;
		}
		
		if(num>999999999) throw new NumTextTransformException("Неверная запись числа");
		
		return result;
	}
	
	/**
	 * <b>numberToText</b>
	 * Процедура перевода числа в текстовое представление
	 * @param - строка вида от 0 до 999 999 999
	 * @return - текстовое представление числа или ошибку NumTextTransformException если входное число больше 999 999 999
	 * или некорректны входные данные
	 */
	
	public static String numberToText(String numStr) throws NumTextTransformException, NumberFormatException{
		int num = Integer.parseInt(numStr);		
		return numberToText(num); 
	}
}
