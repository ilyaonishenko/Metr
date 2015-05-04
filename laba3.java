import java.lang.Math;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
class laba3{
	static final int N = 21;
	static int E0=20;
	static double U0=14.86;
	static int R = 300;
	static double Uem =0;//Электродинамический
	static double Rvem = 75;//Rв
	static double Rwem = 100; //Rц
	static double Ued =0;//Электромагнитный
	static double Rwed = 100;
	static double Rved = 100;
	static double deltaem =0;
	static double deltaed =0;
	static double yem = 0;
	static double yed = 0;
	static double deltained = 0;
	static double deltainem = 0;
	static double yinem = 0;
	static double yined = 0;
	static double[] prodFirst = new double[N];
	static double[] prodSecond = new double[N];
	static double[][] pop = new double[N][5]; //стркоа столбец
	static double Upd1 = 5.6;
	static double Upd2 = 5.7;
	static double Kpd1 = 0;
	static double Kpd2 = 0;
	static double average1 =0;
	static double average2 =0;
	public static void main(String[] args){
		System.out.println("START");
		System.out.println("delta Rem");
		deltaem = delta(Rvem,Rwem);
		System.out.println("delta Rem= "+deltaem);
		System.out.println("delta Red");
		deltaed = delta(Rved,Rwed);
		System.out.println("delta Red= "+deltaed);
		yem = yy(deltaem);
		System.out.println("yem = "+yem);
		yed = yy(deltaed);
		System.out.println("yed = "+yed);
		deltainem = 0.5*7.5/100;
		deltained = 0.5*15/100;
		System.out.println("deltainem "+deltainem);
		System.out.println("deltained "+deltained);
		yinem = deltainem*7.5/7.2;
		yined = deltained*15/8.3;
		System.out.println("yinem "+yinem);
		System.out.println("yined "+yined);
		try{
			Scanner sc = new Scanner(new File("input1.txt"));
			prodFirst = makeArray1(sc);
			sc = new Scanner(new File("input2.txt"));
			prodSecond = makeArray1(sc);
			sc = new Scanner(new File("input3.txt"));
			pop = makeArray2(sc);
			sc.close();

		} catch(IOException e){
			System.out.println("not so easy");
		}
		average1 = average(prodFirst);
		average2 = average(prodSecond);
		System.out.println("average first is "+average1);
		System.out.println("average second is "+average2);
		Kpd1 = 20*Math.log10(Upd1/average1);
		Kpd2 = 20*Math.log10(Upd2/average2);
		System.out.println("KPD1 is "+Kpd1);
		System.out.println("KPD2 is "+Kpd2);
	}
	public static double delta(double R1,double R2){
		// R1 = Rvem R2 = Rwem
		return (E0*(((R*R1)/(R+R1))/(R2+(R*R1)/(R+R1))))-(E0*(R/(R2+R)));
	}
	public static double yy(double delta){
		return Math.abs((delta/U0)*100);
	}
	public static double[] makeArray1 (Scanner sc){
		double[] array = new double[N];
		String text;
		String[] stringArray;
		text = sc.nextLine();
		stringArray = text.split(" ");
		for(int i=0;i<N;i++)
			array[i] = Double.parseDouble(stringArray[i]);
		return array;
	}
	public static double[][] makeArray2(Scanner sc){
		double[][] array = new double[N][5];
		String text;
		String[] stringArray;
		for(int i=0;i<5;i++){
			text = sc.nextLine();
			stringArray = text.split(" ");
			for(int j=0;j<N;j++)
				array[j][i]=Double.parseDouble(stringArray[j]);
		}
		return array;
	}
	static void Print(double[] array){
		for(int i=0;i<array.length;i++)
			System.out.println(array[i]+" ");
		System.out.println();
	}
	static void PrintDouble(double[][] array){
		for(int i=0;i<N;i++){
			for(int j=0;j<5;j++)
				System.out.print(array[i][j]+" ");
			System.out.println();
			// i стркоа j = столбец
		}
		System.out.println();
	}
	static double average(double[] array){
		double answer=0;
		double sum=0;
		for(int i=0;i<N;i++){
			sum+=Math.pow(array[i],2);
		}
		answer = sum/20;
		answer = Math.pow(answer,0.5);
		return answer;
	}
}
