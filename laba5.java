import java.lang.Math;
class laba5{
	public static int N=9;
	public static double[] R2 = new double[N];
	public static double[] dR2 = new double[N];
	public static double[] dR1 = new double[N];
	public static double[] dR3 = new double[N];
	public static double[] R1 = new double[N];
	public static double[] R3 = new double[N];
	public static double[] Cx = new double[N];
	public static double[] dCx = new double[N];
	public static double[] Rx = new double[N];
	public static double[] dRx = new double[N];
	public static double[] tgb = new double[N];
	public static double[] dtgb = new double[N];
	public static double[] borderRxp = new double[N];
	public static double[] borderRxm = new double[N];
	public static double[] borderCxp = new double[N];
	public static double[] borderCxm = new double[N];
	public static double avCx = 0;
	public static double avRx = 0;
	public static double avdRx = 0;
	public static double avdCx = 0;
	public static double avtgb = 0;
	public static double avdtgb = 0;
	public static double w = 1500*2*3.14;
	public static double dw = w*0.01+1;
	public static double CN = 2e-6;
	public static double dCN = CN*0.01;
	public static void main(String[] args){
		System.out.println("dCN is "+dCN);
		System.out.println("LABA 5 IS HERE");
		//массив R2
		R2[0] = 150;
		for(int i=1;i<N;i++)
			R2[i]=40+R2[i-1];
		// Погрешность R2
		for(int i=0;i<N;i++){
			dR2[i] = R2[i]*0.002;
		}
		System.out.println("dR2");
		Print(dR2);
		//R!
		R1[0] = 6.2;
		R1[1] = 6.2;
		R1[2] = 6.3;
		R1[3] = 6.2;
		R1[4] = 6.3;
		R1[5] = 6.1;
		R1[6] = 6.2;
		R1[7] = 6.2;
		R1[8] = 6.2;
		// R3
		R3[0] = 349.9;
		R3[1] = 443;
		R3[2] = 534.9;
		R3[3] = 628;
		R3[4] = 722;
		R3[5] = 815;
		R3[6] = 908;
		R3[7] = 1000;
		R3[8] = 1095;
		// finding CX
		// finding Rx
		// FINDING TGB
		for(int i=0;i<N;i++){
			Cx[i] = CN*R3[i]/R2[i];
			Rx[i] = R1[i]*R2[i]/R3[i];
			tgb[i]=w*Cx[i]*Rx[i];
		}
		System.out.println("w is "+w);
		System.out.println("CX");
		Print(Cx);
		System.out.println("RX");
		Print(Rx);
		System.out.println("tgb");
		Print(tgb);
		for(int i=0;i<N;i++)
			dR1[i] = 0.1;
		// for(Double d:dR1)
			// d=0.1;
			// 0.2
		// System.out.println("DR1");
		// Print(dR1);
		dR3[0]=1;
		dR3[1]=1;
		dR3[2]=2;
		dR3[3]=2;
		dR3[4]=3;
		dR3[5]=3;
		dR3[6]=3;
		dR3[7]=3;
		dR3[8]=4;
		//Вычисление dRx
		for(int i=0;i<N;i++){
			if(i==0){
				 System.out.println("dCN "+dCN+" CN "+CN+" dR2[i] "+dR2[i]+" R2 "+R2[i]+" dR3 "+dR3[i]+" R3 "+R3[i]+" Rx "+Cx[i]);
			}
			dRx[i]=((dR1[i]/R1[i])+(dR2[i]/R2[i])+(dR3[i]/R3[i]))*Rx[i];
			dCx[i]=((dCN/CN)+(dR2[i]/R2[i])+(dR3[i]/R3[i]))*Cx[i];
			dtgb[i]=((dRx[i]/Rx[i])+(dCx[i]/Cx[i])+(dw/w))*tgb[i];
		}
		System.out.println("dRX");
		Print(dRx);
		System.out.println("dCX");
		Print(dCx);
		System.out.println("dtgb");
		Print(dtgb);
		// borders
		for(int i=0;i<N;i++){
			borderRxm[i] = Rx[i]-dRx[i];
			borderRxp[i] = Rx[i]+dRx[i];
			borderCxm[i] = Cx[i]-dCx[i];
			borderCxp[i] = Cx[i]+dCx[i];
		}
		System.out.println("Border RX - ");
		Print(borderRxm);
		System.out.println("Border RX + ");
		Print(borderRxp);
		System.out.println("Border CX - ");
		Print(borderCxm);
		System.out.println("Border CX + ");
		Print(borderCxp);
		//  Вычисление средних
		avRx = average(Rx);
		avdRx = average(dRx);
		avCx = average(Cx);
		avdCx = average(dCx);
		avtgb = average(tgb);
		avdtgb = average(dtgb);
		System.out.println(" Average RX = "+avRx);
		System.out.println(" Average dRX = "+avdRx);
		System.out.println(" Average CX = "+avCx);
		System.out.println(" Average dCX = "+avdCx);
		System.out.println(" Average tgb = "+avtgb);
		System.out.println(" Average dtgb = "+avdtgb);
		System.out.println("Real is 1.06");
		double newW = 1000*2*3.14;
		System.out.println("______ "+4.71e-6*w*Rx[Rx.length-1]);
		System.out.println("ver 2 "+315.8*w*4.71);
		System.out.println("delta cx from mult "+4.710*0.004);
		double qwer = 0.118072;
		double dqwer = qwer*0.004+0.00000005;
		System.out.println("delta tang = "+dqwer);
		double RXNEW = qwer/(newW*4.71e-6);
		double anotherRXNEW = qwer/(newW*4.71e-6)*(1/(1+qwer*qwer));
		System.out.println("RX IS "+RXNEW);
		double dwf = newW*0.0001;
		System.out.println("Another RX is "+anotherRXNEW);
		double dRXNEW = RXNEW*((dqwer/qwer)+(4.71*0.004/4.71)+(dw/w));
		double anotherdRXNEW = anotherRXNEW*((dqwer/qwer)+(4.71*0.004/4.71)+((dwf)/newW));
		System.out.println("dRX IS "+dRXNEW);
		System.out.println("Aother dRX IS "+anotherdRXNEW);
		System.out.println(dqwer/qwer);
		System.out.println("_____ "+4.71*0.004);
		System.out.println(4.71e-6*0.004/4.71e-6);
		System.out.println(((1000*0.01+1)/1000));
		System.out.println(2.5064*0.019);
		System.out.println("\n\n\n\n");
		System.out.println(0.01887/4.71);
		System.out.println(0.0000472293/0.0118072);
		System.out.println("real is "+newW+"dwf is "+dwf);
		System.out.println(dwf/newW);
		System.out.println(dw);

	}
	public static void Print(double[] array){
		for(int i=0;i<array.length;i++){
			System.out.println(array[i]+" ");
		}
		System.out.println();
	}
	public static double average(double[] array){
		double sum =0;
		for(Double d:array)
			sum+=d;
		return sum/array.length;
	}
}
