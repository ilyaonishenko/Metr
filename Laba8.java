import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.Math;
class Laba8{
	static double[][] secondResistorTop = new double[7][4];
	static double[][] secondResistorBack =new double[7][4];
	static double[][] fourthResistorTop = new double[7][4];
	static double[][] fourthResistorBack = new double[7][4];
	static double[][] sixResistorTop = new double[7][4];
	static double[][] sixResistorBack = new double[7][4];
	static double[][] secondResistorTop2 = new double[7][4];
	static double[][] secondResistorBack2 =new double[7][4];
	static double[][] fourthResistorTop2 = new double[7][4];
	static double[][] fourthResistorBack2 = new double[7][4];
	static double[][] sixResistorTop2 = new double[7][4];
	static double[][] sixResistorBack2 = new double[7][4];
	static double[] averageArray2 = new double[7];
	static double[] averageArray4 = new double[7];
	static double[] averageArray6 = new double[7];
	static double[] deviationArray2 = new double[7];
	static double[] deviationArray4 = new double[7];
	static double[] deviationArray6 = new double[7];
	static double[] deformation = new double[7];
	static double aveR2;
	static double aveR4;
	static double aveR6;
	static boolean cochranR2;
	static boolean cochranR4;
	static boolean cochranR6;
	static double tenzoS2;
	static double tenzoS4;
	static double tenzoS6;
	static double krit = 0.3535;
	public static void main(String[] args){
		try{
			Scanner scan = new Scanner(new File("input.txt"));
			secondResistorTop = makeArray(scan);
			secondResistorBack = makeArray(scan);
			fourthResistorTop = makeArray(scan);
			fourthResistorBack = makeArray(scan);
			sixResistorTop = makeArray(scan);
			sixResistorBack = makeArray(scan);
			scan.close();
		}catch(IOException e){
			System.err.println("Fs");
		}
		aveR2 = averageOfResistor(secondResistorTop,secondResistorBack);
		aveR4 = averageOfResistor(fourthResistorTop,fourthResistorBack);
		aveR6 = averageOfResistor(sixResistorTop,sixResistorBack);
		System.out.println("Усредненные значения сопротивления тензоризистора при прогибе 0");
		System.out.println("aveR2= "+aveR2+" aveR4= "+aveR4+" aveR6= "+aveR6);
		//Вычисление относительных изменения сопротивления
		secondResistorTop2 = makeRelativelyArray(secondResistorTop,aveR2);
		secondResistorBack2 = makeRelativelyArray(secondResistorBack,aveR2);
		fourthResistorTop2 = makeRelativelyArray(fourthResistorTop,aveR4);
		fourthResistorBack2 = makeRelativelyArray(fourthResistorBack,aveR4);
		sixResistorTop2 = makeRelativelyArray(sixResistorTop,aveR6);
		sixResistorBack2 = makeRelativelyArray(sixResistorBack,aveR6);
		// Вычисление средних арифметических значений этих величин.
		averageArray2 = averageOfArray(secondResistorTop2,secondResistorBack2);
		averageArray4 = averageOfArray(fourthResistorTop2,fourthResistorBack2);
		averageArray6 = averageOfArray(sixResistorTop2,sixResistorBack2);
		System.out.println("Второй резистор прямо");
		PrintDouble(secondResistorTop2);
		System.out.println("Второй резистор обратно");
		PrintDouble(secondResistorBack2);
		System.out.println("Четвертный резистор прямо");
		PrintDouble(fourthResistorTop2);
		System.out.println("Четвертый резистор обратно");
		PrintDouble(fourthResistorBack2);
		System.out.println("Шестой резистор прямо");
		PrintDouble(sixResistorTop2);
		System.out.println("Шестой резистор обратно");
		PrintDouble(sixResistorBack2);
		System.out.println("Среднее арифметическое 2");
		Print(averageArray2);
		System.out.println("Среднее арифметическое 4");
		Print(averageArray4);
		System.out.println("Среднее арифметическое 6");
		Print(averageArray6);
		//Вычисление средне-квадратичного отклонения
		deviationArray2 = standartDeviation(secondResistorTop2,secondResistorBack2,averageArray2);
		deviationArray4 = standartDeviation(fourthResistorTop2,fourthResistorBack2,averageArray4);
		deviationArray6 = standartDeviation(sixResistorTop2,sixResistorBack2,averageArray6);
		System.out.println("Среднее квадратичное отклонение 2 тензо");
		Print(deviationArray2);
		System.out.println("Среднее квадратичное отклонение 4 тензо");
		Print(deviationArray4);
		System.out.println("Среднее квадратичное отклонение 6 тензо");
		Print(deviationArray6);
		//Определение верна ли какая-то гипотеза по критерию корча
		// если false = нет
		cochranR2 = CochranCrit(deviationArray2);
		cochranR4 = CochranCrit(deviationArray4);
		cochranR6 = CochranCrit(deviationArray6);
		System.out.println("Для второго "+cochranR2);
		System.out.println("Для четвертого "+cochranR4);
		System.out.println("Для шестого "+cochranR6);
		// ни для кого гипотеза не отклонена
		//девормация
		for(int i=0;i<7;i++)
			deformation[i]=i*15;
		tenzoS2 = tenzoKof(averageArray2,deviationArray2,deformation);
		tenzoS4 = tenzoKof(averageArray4,deviationArray4,deformation);
		tenzoS6 = tenzoKof(averageArray6,deviationArray6,deformation);
		System.out.println("Коэффициент тензочувствительности для 2 резистора "+tenzoS2);
		System.out.println("Коэффициент тензочувствительности для 4 резистора "+tenzoS4);
		System.out.println("Коэффициент тензочувствительности для 6 резистора "+tenzoS6);
	}
	static double[][] makeArray(Scanner sc){
		double[][] answerArray = new double[7][4];
		String text;
		String[] stringArray;
		for(int i=0;i<4;i++){
			text = sc.nextLine();
			stringArray = text.split(" ");
			for(int j=0;j<7;j++){
				answerArray[j][i] = Math.round(Double.parseDouble(stringArray[j])*10000)/10000.0d;
			}
		}
		return answerArray;
	}
	static void Print(double[] array){
		for(int i=0;i<array.length;i++)
			System.out.print(array[i]+" ");
		System.out.println();
	}
	static void PrintDouble(double[][] array){
		for(int i=0;i<7;i++){
			for(int j=0;j<4;j++)
				System.out.print(array[i][j]+" ");
			System.out.println();
		}
		System.out.println();
	}
	static double averageOfResistor(double[][] array1,double[][] array2){
		double answer=0;
		int N=4;
		for(int i=0;i<N;i++){
			answer +=array1[0][i];
			answer +=array2[6][i];
		}
		answer = Math.round(answer/(2*N)*10000)/10000.0d;
		return answer;
	}
	static double[][] makeRelativelyArray(double[][] array,double var){
		double[][] answerArray = new double[7][4];
		for(int i=0;i<7;i++){
			for(int j=0;j<4;j++){
				answerArray[i][j] = (array[i][j]-var)/var;
			}
		}
		return answerArray;
	}
	static double[] averageOfArray(double[][] array1, double[][] array2){
		double[] answerArray = new double[7];
		for(int i=0;i<7;i++){
			for(int j=0;j<4;j++){
				answerArray[i] +=array1[i][j]+array2[i][j];
			}
			answerArray[i] = answerArray[i]/8;
		}
		return answerArray;
	}
	static double[] standartDeviation(double[][] darray1,double[][] darray2,double[] array){
		double[] answerArray = new double[7];
		for(int i=0;i<7;i++){
			for(int j=0;j<4;j++){
				answerArray[i] +=(darray1[i][j]-array[i])*(darray1[i][j]-array[i]);
				answerArray[i] +=(darray2[i][j]-array[i])*(darray2[i][j]-array[i]);
			}
			answerArray[i] = Math.sqrt(answerArray[i]/7);
		}
		return answerArray;
	}
	static boolean CochranCrit(double[] array){
		double max=-100;
		double sum=0;
		double answer=0;
		for(int i=0;i<array.length;i++){
			sum+=Math.pow(array[i],2);
			if(max<Math.pow(array[i],2))
				max = Math.pow(array[i],2);
		}
		answer = max/sum;
		if(answer>krit)
			return false;
		else return true;
	}
	static double tenzoKof(double[] array1,double[] array2,double array3[]){
		double answer=0;
		double sum=0;
		double sum2=0;
		for(int i=0;i<array1.length;i++){
			sum+=(array1[i]*array3[i])/Math.pow(array2[i],2);
			sum2+=Math.pow(array3[i]/array2[i],2);
		}
		answer = sum/sum2;
		return answer;
	}
}
