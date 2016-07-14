import java.util.Scanner;import java.util.Scanner;

public class RangeIP {		
	//Проверка правильности ввода  IP адреса 
	static boolean CheckIP (String str) {				
		if (str.matches("[0-9]{1,3}\u002E{1}[0-9]{1,3}\u002E{1}[0-9]{1,3}\u002E{1}[0-9]{1,3}")){			
			//если введёное выражение удовлетворяет регулярному выражению			
			String address[] = str.split("\\."); //разбиваем ip адрес по байтам исключая точку
			for (int i = 0; i < 3; i++) {
				if (Integer.parseInt(address[i]) > 255 || Integer.parseInt(address[i]) < 0) return false;				
			}
		}
		else return false;		
		return true;				
	}
	
	//определяем больший адрес
	static int BiggerIP(int a1, int b1, int c1, int d1, int a2, int b2, int c2, int d2){		
		if(a1 > a2) return 1;
		if(a2 > a1) return 2;		
		if(b1 > b2) return 1;
		if(b2 > b1) return 2;
		if(c1 > c2) return 1;
		if(c2 > c1) return 2;
		if(d1 > d2) return 1;
		if(d2 > d1) return 2;
		return 0;
	}
	
	//вывод ip адреса в консоль
	static void PrintIP(int a, int b, int c, int d){
		System.out.println(a + "." + b + "." + c + "." + d);		
	}
		
	//вывод диапазона допустимых адресов
	static void PrintRange(int a1, int b1, int c1, int d1, int a2, int b2, int c2, int d2){
		//довыводим 4-ый байт адресов		
		for (int i = d2; i <= ((a1 == a2 && b1 == b2 && c1 == c2)?d1:255); i++) PrintIP(a2, b2, c2, i);
		//довыводим 3-ий байт адресов + 4 байт адресов
		for (int i = (c2 + 1); i <= ((a1 == a2 && b1 == b2)?c1:255); i++){
			for (int j = 0; j <= 255; j++) PrintIP(a2, b2, i, j);				
		}
		//довыводим 2-ой байт адресов + 3 байт адресов + 4 байт адресов
		for (int i = (b2 + 1); i <= ((a1 == a2)?b1:255); i++){
			for (int j = 0; j <= 255; j++) {
				for (int k = 0; k <= 255; k++) PrintIP(a2, i, j, k);
			}
		}
		//выводим все оставшиеся адреса с 1-ый по 4-ый байты19
		for (int i = (a2 + 1); i <= a1; i++){
			for (int j = 0; j <= b1; j++){
				for (int k = 0; k <= c1; k++){
					for (int l = 0; l <= d1; l++) PrintIP(i, j, k, l);
				}
			}
		}
	}
	
	//вывод диапазона допустимых адресов
	static void outrange(String ipstr1, String ipstr2){
		
		//разбиваем ip адрес по байтам исключая точку
		String address1[] = ipstr1.split("\\."); 
		String address2[] = ipstr2.split("\\.");
		
		int a1 = Integer.parseInt(address1[0]);
		int b1 = Integer.parseInt(address1[1]);
		int c1 = Integer.parseInt(address1[2]);
		int d1 = Integer.parseInt(address1[3]);
		
		int a2 = Integer.parseInt(address2[0]);
		int b2 = Integer.parseInt(address2[1]);
		int c2 = Integer.parseInt(address2[2]);
		int d2 = Integer.parseInt(address2[3]);
		
		//вывод диапазона ip адресов от большего к меньшему
		switch (BiggerIP(a1, b1, c1, d1, a2, b2, c2, d2)){
		case 1: PrintRange(a1, b1, c1, d1, a2, b2, c2, d2);
				break;
		case 2: PrintRange(a2, b2, c2, d2, a1, b1, c1, d1);
				break;
		default: System.out.print("ip адреса равны: ");
				 PrintIP(a1, b1, c1, d1);
		}		
			
											
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub			
		Scanner scan = new Scanner(System.in);
		//сюда получим массив 4 байтов IP адреса				
		String[] address, address1, address2; //не получилось определить переменные внутри функций		
		
		System.out.print("Введите IP адреса через пробел или ввод: ");
		
		//Считываем ip адреса
		String ipstr1 = scan.next();
		String ipstr2 = scan.next();
		
		if (CheckIP(ipstr1))
			if(CheckIP(ipstr2)) outrange(ipstr1, ipstr2);
			else System.out.println("Второе выражение не является IP адресом.");
		else System.out.println("Первое выражение не является IP адресом.");
						
	}

}