public class Teste
{
  public static void main(String args[])
  {
    Pilha pilha = new Pilha();
    Numero temp;
     
    for(int i = 0; i < 10; i++)
    {
      pilha.push(new Numero(i));
    }

    temp = (Numero) pilha.look();

    System.out.println("HUE: " + temp.getVal() );
  }


}
