package observador;

import java.util.List;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Numeros n = new Numeros();
        ObservadorSumador sumador = new ObservadorSumador(n);
        n.SetSumador(sumador);
        ObservadorMayor mayor =new ObservadorMayor(n);
        n.SetMayor(mayor);

        n.Adicionar(10);
        n.Adicionar(20);
        n.Adicionar(60);
        n.Adicionar(28);

    }
}

class Numeros
{
    List<Integer> numeros;
    ObservadorSumador sumador;
    ObservadorMayor mayor;
    public Numeros()
    {
        numeros = new ArrayList<>();
    }

    public void SetSumador(ObservadorSumador sumador)
    {this.sumador=sumador;}

    public void SetMayor(ObservadorMayor mayor)
    {this.mayor=mayor;}

    public void Adicionar(int n)
    {
        if (!numeros.contains(n)){
            numeros.add(n);
            Notificar();
        }
    }

    private void Notificar()
    {
        sumador.Actualizar();
        mayor.Actualizar();
    }

    public List<Integer> GetListaNumeros(){
        return numeros;
    }
}

class ObservadorSumador
{
    private Numeros numeros;

    public ObservadorSumador(Numeros nums)
    {
        numeros = nums;
    }
    public void Actualizar()
    {
        int sumatoria=0;
        for (int n : numeros.GetListaNumeros())
        {
            sumatoria+=n;
        }

        System.out.println("Sumatoria "+sumatoria);
    }
}

class ObservadorMayor
{
    private Numeros numeros;

    public ObservadorMayor(Numeros nums)
    {
        numeros = nums;
    }
    public void Actualizar()
    {
        int mayor = Integer.MIN_VALUE;
        for (int n : numeros.GetListaNumeros())
        {
            if(n>mayor){
                mayor = n;
            }

        }

        System.out.println("El mayor es "+ mayor);
    }
}

