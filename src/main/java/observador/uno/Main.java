package observador.uno;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Numeros n = new Numeros();
        ObservadorSumador sumador = new ObservadorSumador(n);
        n.Attach(sumador);
        ObservadorMayor mayor = new ObservadorMayor(n);
        n.Attach(mayor);
        ObservadorMenor menor = new ObservadorMenor(n);
        n.Attach(menor);
        ObservadorImpares imparesObserver = new ObservadorImpares(n);
        n.Attach(imparesObserver);

        n.Adicionar(10);
        n.Adicionar(20);
        n.Adicionar(60);
        n.Adicionar(28);
        n.Adicionar(69);
    }
}

interface IObservador {
    void Actualizar();
}

class Numeros {
    List<Integer> numeros;
    List<IObservador> observadores;

    public Numeros() {
        numeros = new ArrayList<>();
        observadores = new ArrayList<>();
    }

    public void Attach(IObservador observador) {
        observadores.add(observador);
    }

    public void Adicionar(int n) {
        if (!numeros.contains(n)) {
            numeros.add(n);
            Notificar();
        }
    }

    private void Notificar() {
        for (IObservador obs : observadores) {
            obs.Actualizar();
        }
    }

    public List<Integer> GetListaNumeros() {
        return numeros;
    }
}

class ObservadorSumador implements IObservador {
    private Numeros numeros;

    public ObservadorSumador(Numeros nums) {
        numeros = nums;
    }

    public void Actualizar() {
        int sumatoria = 0;
        for (int n : numeros.GetListaNumeros()) {
            sumatoria += n;
        }

        System.out.println("Sumatoria " + sumatoria);
    }
}

class ObservadorMayor implements IObservador {
    private Numeros numeros;

    public ObservadorMayor(Numeros nums) {
        numeros = nums;
    }

    public void Actualizar() {
        int mayor = Integer.MIN_VALUE;
        for (int n : numeros.GetListaNumeros()) {
            if (n > mayor) {
                mayor = n;
            }
        }

        System.out.println("El mayor es " + mayor);
    }
}

class ObservadorMenor implements IObservador {
    private Numeros numeros;

    public ObservadorMenor(Numeros nums) {
        numeros = nums;
    }

    public void Actualizar() {
        int menor = Integer.MAX_VALUE;
        for (int n : numeros.GetListaNumeros()) {
            if (n < menor) {
                menor = n;
            }
        }

        System.out.println("El menor es " + menor);
    }
}

class ObservadorImpares implements IObservador {
    private Numeros numeros;

    public ObservadorImpares(Numeros nums) {
        numeros = nums;
    }

    public void Actualizar() {
        List<Integer> impares = obtenerImpares(numeros.GetListaNumeros());

        // Sumar impares
        int sumaImpares = calcularSuma(impares);

        // Encontrar el mayor impar
        int mayorImpar = encontrarMayor(impares);

        // Encontrar el menor impar
        int menorImpar = encontrarMenor(impares);

        // Mostrar resultados
        System.out.println("Suma de impares: " + sumaImpares);
        System.out.println("Mayor impar: " + mayorImpar);
        System.out.println("Menor impar: " + menorImpar);
    }

    private List<Integer> obtenerImpares(List<Integer> lista) {
        List<Integer> impares = new ArrayList<>();
        for (int n : lista) {
            if (n % 2 != 0) {
                impares.add(n);
            }
        }
        return impares;
    }

    private int calcularSuma(List<Integer> lista) {
        int suma = 0;
        for (int n : lista) {
            suma += n;
        }
        return suma;
    }

    private int encontrarMayor(List<Integer> lista) {
        int mayor = Integer.MIN_VALUE;
        for (int n : lista) {
            if (n > mayor) {
                mayor = n;
            }
        }
        return mayor;
    }

    private int encontrarMenor(List<Integer> lista) {
        int menor = Integer.MAX_VALUE;
        for (int n : lista) {
            if (n < menor) {
                menor = n;
            }
        }
        return menor;
    }
}
