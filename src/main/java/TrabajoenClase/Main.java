import java.util.List;
import java.util.ArrayList;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Numeros n = new Numeros();
        ObservadorSumador sumador = new ObservadorSumador();
        n.Attach(sumador);
        ObservadorMayor mayor =new ObservadorMayor();
        n.Attach(mayor);

        ObservadorSumador sumador2 = new ObservadorSumador();
        DecoradorObservadorImprares decorador =  new DecoradorObservadorImprares(sumador2);
        n.Attach( decorador);

        ObservadorMayor mayor2 = new ObservadorMayor();
        DecoradorObservadorImprares decorador2 =  new DecoradorObservadorImprares(mayor2);
        n.Attach( decorador2);

        n.Adicionar(10);
        n.Adicionar(11);
        //n.Adicionar(13);


        n.Eliminar(10);

    }
}

interface IObservador
{
    void Actualizar(List<Integer> datos);
}

class DecoradorObservadorImprares implements IObservador
{
    IObservador observadorDecorado;
    public DecoradorObservadorImprares(IObservador obs)
    {
        observadorDecorado = obs;
    }
    public void Actualizar(List<Integer> datos)
    {
        List<Integer> impares=new ArrayList<>();

        for(Integer n : datos) {
            if (n % 2 == 1) {
                impares.add(n);
            }
        }

        observadorDecorado.Actualizar(impares);
    }
}

class Numeros
{
    List<Integer> numeros;
    List<IObservador> observadores;
    public Numeros()
    {
        numeros = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    public void Attach(IObservador observador)
    {
        observadores.add(observador);
    }

    public void Adicionar(int n)
    {
        if (!numeros.contains(n)){
            numeros.add(n);
            Notificar();
        }
    }

    public void Eliminar(int n)
    {
        if (numeros.contains(n)){
            numeros.remove(numeros.indexOf( n));
            Notificar();
        }
    }

    private void Notificar()
    {
        for(IObservador obs : observadores){
            obs.Actualizar(numeros);
        }
    }

    public List<Integer> GetListaNumeros(){
        return numeros;
    }
}

class ObservadorSumador implements IObservador
{
    public void Actualizar(List<Integer> datos)
    {
        int sumatoria=0;
        for (int n : datos)
        {
            sumatoria+=n;
        }

        System.out.println("Sumatoria "+sumatoria);
    }
}

class ObservadorMayor implements IObservador
{
    public void Actualizar(List<Integer> datos)
    {
        if(datos.size()==0) {
            System.out.println("No hay mayor de una lista vacia.");
            return;
        }

        int mayor = Integer.MIN_VALUE;
        for (int n : datos)
        {
            if(n>mayor){
                mayor = n;
            }

        }

        System.out.println("El mayor es "+ mayor);
    }
}

