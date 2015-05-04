import java.lang.Math;
class Main
{
    static double R3=100;
    static double R0=1400;
    static double R1=136;
    static double R2=1000;
    static double E0 = 4;
    static double a0 = 0;
    static double b0 = 1;
    static double a1 = (-E0*R3)/((R0+R2)*(R1+R3));
    static double b1 = 1/(R0+R2);
    static double a0v = 4.433273e-004;
    static double a1v = 6.607585e-004;
    static double b1v = 4.412993e-004;
    //static double k = 4.007;
    static double k = 3.379;
    static double student = 2.776;
  public static void main(String[] args)
  {
    double fdr = (a0v+a1v*(-400))/(1+b1v*(-400));
    System.out.println(fdr);
    double[] dr = new double[7];
    dr[0]=1000;
    for (int i=1;i<7;i++)
    {
        dr[i]=dr[i-1]+150;
    }
    double[] ddr = new double[7];
    for (int i=0;i<7;i++){
        ddr[i]=dr[i]-R0;
    }
    System.out.println("drr - дельта Ri");
    for(double d:ddr){
        System.out.println(d);
    }
    System.out.println("fr рассчитанные самостоятельно (не в эксперименте)");
    double fr[] = new double[7];
    for (int i=0;i<7;i++){
        fr[i]=f(ddr[i]);
    }
    for(double f:fr){
        System.out.println(f);
    }
    double frv[] = new double[7];
    for (int i=0;i<7;i++){
        frv[i]=fv(ddr[i]);
    }
    System.out.println("frv  - арсчитанные в эксперименте");
    for (int i=0;i<7;i++){
        System.out.println(frv[i]);
    }
    double[] ui = new double[7];
    ui[0]=-0.319599;
    ui[1]=-0.186798;
    ui[2]=-0.068029;
    ui[3]=0.031947;
    ui[4]=0.1214;
    ui[5]=0.200830;
    ui[6]=0.270989;
    double[] systd = new double[7];
    for(int i=0;i<7;i++){
        systd[i]=ui[i]-(a1v*ddr[i])/(1+b1v*ddr[i]);
    }
    System.out.println("Систематическая погрешность");
    for (double d:systd){
        System.out.print(String.format("%.5g%n",d));
    }
    double[] si = new double[7];
    si[0]=3.169455e-003;
    si[1]=2.112971e-003;
    si[2]=1.056485e-003;
    si[3]=1.691202e-003;
    si[4]=1.751984e-003;
    si[5]=7.923640e-004;
    si[6]=7.9236873e-004;
    System.out.println("Дисперсия");
    for(int i=0;i<7;i++){
        System.out.println(si[i]);
    }
    double[] downBeyond = new double[7];
    double[] topBeyond = new double[7];
    for(int i=0;i<7;i++){
        downBeyond[i]=beyondMinus(systd[i],si[i]);
        topBeyond[i]=beyondPlus(systd[i],si[i]);
    }
    System.out.println("Верхняя граница:");
    for(double d:topBeyond)
        System.out.println(d+" ");
    System.out.println("Нижняя граница:");
    for(double d:downBeyond)
        System.out.println(d+" ");
    double[] Ai = new double[7];
    for(int i=0;i<7;i++){
        if (Math.abs(topBeyond[i])>Math.abs(downBeyond[i]))
            Ai[i]=Math.abs(topBeyond[i]);
        else Ai[i]=Math.abs(downBeyond[i]);
    }
    System.out.println("Предельная аддитивная погрешность");
    for(double d:Ai)
        System.out.println(d+" ");
    double absolute = -100;
    for(int i=0;i<7;i++){
        if (Ai[i]>absolute)
            absolute = Ai[i];
    }
    System.out.println("Абсолютная погрешность: "+absolute);
    double otnos = 0;
    double uiMax = 0;
    for(int i=0;i<7;i++)
        if (Math.abs(ui[i])>uiMax)
            uiMax = Math.abs(ui[i]);
    otnos = (absolute*(1/uiMax))*100;
    System.out.println("Относительная погрешнсоть: "+otnos+" %");
    System.out.println("UIMAX "+uiMax);

  }
  static double f(double r){
      return (a1*r)/(1+b1*r);
  }
  static double fv(double r){
      return (a0v+a1v*r)/(1+b1v*r);
  }
  static double beyondPlus(double r,double s){//граница аддитивных погрешностей
      //k = 4.007;
      return r+s*k;
  }
  static double beyondMinus(double r,double s){
      //k = 4.007;
      return r-s*k;
  }
}
