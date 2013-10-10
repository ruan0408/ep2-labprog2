public class Numero implements Empilhavel
{

  private double numero;
  

  /****** Construtor ******/


  public Numero(double n)
  {
    numero = n;
  }


  /****** Getters ******/


  public double getVal()
  {
    return numero;
  }


  /*public boolean ne(Numero n)
  {
  	return this.getVal() != n.getVal();
  }*/


}
