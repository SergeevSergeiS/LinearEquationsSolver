import static java.lang.Math.round;

public class Complex {
    public double real;
    public double imaginary;
    Complex(String num){
        int iIdx = num.indexOf("i");

        if(iIdx != -1){
            int minusIdx = num.lastIndexOf("-");
            int plusIdx = num.lastIndexOf("+");
            //img
            if(minusIdx == -1 && plusIdx == -1){
                real = 0;
                if(num.length() == 1){
                    imaginary = 1;
                }else {
                    imaginary = Double.parseDouble(num.substring(0, num.length() - 1));
                }
            }
            //-img minus == 0, plus == -1
            if(minusIdx == 0 && plusIdx == -1){
                real = 0;
                if(num.length() == 2){
                    imaginary = -1;
                }else {
                    imaginary = Double.parseDouble(num.substring(0, num.length() - 1));
                }
            }
            //-real+img minus == 0, plus == smth
            if(minusIdx == 0 && plusIdx > 0){
                real = Double.parseDouble(num.substring(0, plusIdx));
                if(Math.abs(iIdx - plusIdx) == 1){
                    imaginary = 1;
                }else {
                    imaginary = Double.parseDouble(num.substring(plusIdx, num.length() - 1));
                }
            }
            //real+img minus == -1, plus == smth
            if(minusIdx == -1 && plusIdx > 0){
                real = Double.parseDouble(num.substring(0, plusIdx));
                if(Math.abs(iIdx - plusIdx) == 1){
                    imaginary = 1;
                }else {
                    imaginary = Double.parseDouble(num.substring(plusIdx, num.length() - 1));
                }
            }
            //-real-img minus == smth plus == -1
            if(minusIdx > 0 && plusIdx == -1){
                real = Double.parseDouble(num.substring(0, minusIdx));
                if(Math.abs(minusIdx - iIdx) == 1){
                    imaginary = -1;
                }else {
                    imaginary = Double.parseDouble(num.substring(minusIdx, num.length() - 1));
                }
            }

        }else{
            //real or -real
            real = Double.parseDouble(num);
            imaginary = 0;
        }
    }
    Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public double[] getComplex(){
        return new double[]{real, imaginary};
    }

    public void setComplex(double[] complex){
        this.real = complex[0];
        this.imaginary = complex[1];
    }

    public void setComplex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public static Complex Multiplication(double num1, Complex num2){
        double real = num1 * num2.real;
        double img = num1 * num2.imaginary;
        return new Complex(real, img);
    }

    public static Complex Multiplication(Complex num1, Complex num2){
        double real = num1.real * num2.real + num1.imaginary * num2.imaginary * -1;
        double img = num1.real * num2.imaginary + num1.imaginary * num2.real;
        //System.out.println("Multiply: " + num1 + " x " + num2 + " = " + new Complex(real,img) );
        return new Complex(real, img);
    }

    public static Complex division(Complex num1, Complex num2){
        double real = (num1.real * num2.real + num1.imaginary * num2.imaginary)/(Math.pow(num2.real, 2) + Math.pow(num2.imaginary, 2));
        double img = 0;
        if(num1.imaginary != 0) {
            img = num1.imaginary / num2.real; //aka img/real
        }
        if(num2.imaginary != 0) { //real/img (forgot negative sign?)
            img = -(num1.real*num2.imaginary)/(Math.pow(num2.real,2) + Math.pow(num2.imaginary, 2));
        }
        if(num1.imaginary != 0 && num2.imaginary != 0) { //img/img
            img = (num1.imaginary * num2.real - num1.real * num2.imaginary) / (Math.pow(num2.real, 2) + Math.pow(num2.imaginary, 2));
        }
        //System.out.println("Division: " + num1 + " / " + num2 + " = " + new Complex(real,img) );
        return new Complex(real, img);
    }

    public static Complex division(double num1, Complex num2){
        double real = (num1*num2.real)/(Math.pow(num2.real,2) + Math.pow(num2.imaginary, 2));
        double img = 0;
        if(num2.imaginary != 0) {
            img = (num1*num2.imaginary)/(Math.pow(num2.real,2) + Math.pow(num2.imaginary, 2));
        }
        return new Complex(real, img);
    }

    public static Complex division(Complex num1, double num2){
        double real = num1.real / num2;
        double img = 0;
        if(num1.imaginary != 0) {
            img = num1.imaginary / num2;
        }
        return new Complex(real, img);
    }

    public static Complex Addition(Complex num1, Complex num2){
        double real = num1.real + num2.real;
        double img = num1.imaginary + num2.imaginary;
        return new Complex(real, img);
    }

    public static Complex Addition(double num1, Complex num2){
        double real = num1 + num2.real;
        double img = num2.imaginary;
        return new Complex(real, img);
    }

    public static boolean Compare(Complex num1, double num2){
        if(num1.real == num2 && num1.imaginary == 0){
            return true;
        }
        return false;
    }

    public static boolean roundCompare(Complex num1, double num2){
        if(round(num1.real) == num2 && num1.imaginary == 0){
            return true;
        }
        return false;
    }
    @Override
    public String toString(){
        //only real
        if(imaginary == 0 && real != 0){
            return Double.toString(real);
        }
        //only img
        if(real == 0 && imaginary != 0){
            return imaginary + "i";
        }
        if(imaginary == 1 && real == 0){
            return "i";
        }
        if(imaginary == -1 && real == 0){
            return "-i";
        }
        if(imaginary == 1 && real != 0){
            return real + "+i";
        }
        if(imaginary == -1 && real != 0){
            return real + "-i";
        }
        if(imaginary < 0){
            return real + "" + imaginary + "i";
        }
        if(real == 0 && imaginary == 0){
            return "0";
        }
        return real + "+" + imaginary + "i";

    }
}
