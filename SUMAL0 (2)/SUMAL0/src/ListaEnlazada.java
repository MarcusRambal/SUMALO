
//Necesario
import java.util.Random;

//No nceseario, para simulaciones
import java.util.HashSet;
//No nceseario, para simulaciones
import java.util.Set;

public class ListaEnlazada {

    Nodo cabeza;
    Enemigo e;
    private Nodo inicio;

    public ListaEnlazada() {
        this.cabeza = null;
    }

    public int obtenerCantidadNodos() {
        int contador = 0;
        Nodo nodoActual = cabeza;

        while (nodoActual != null) {
            contador++;
            nodoActual = nodoActual.siguiente;
        }

        return contador;
    }

    // Función para elegir un nodo aleatorio
    public Nodo obtenerNodoAlAzar() {
        Random rand = new Random();
        int cantidadNodos = obtenerCantidadNodos();
        System.out.println(cantidadNodos);
        int nodoElegido = rand.nextInt(cantidadNodos);

        Nodo nodoActual = cabeza;
        for (int i = 0; i < nodoElegido; i++) {
            if (nodoActual != null) {
                nodoActual = nodoActual.siguiente;
            }
        }

        return nodoActual;
    }

    //Necesario
    public void agregarElemento(String expresion) {
        Nodo nuevoNodo = new Nodo(expresion);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevoNodo;
        }
    }

    //no necesario, para simulaciones
    public void imprimirLista() {
        Nodo temp = cabeza;
        while (temp != null) {
            System.out.print(temp.expresion + " ");
            temp = temp.siguiente;
        }
        System.out.println();
    }

    //No necesario, para simulacione
    public int obtenerTamano() {
        int tamano = 0;
        Nodo temp = cabeza;

        while (temp != null) {
            tamano++;
            temp = temp.siguiente;
        }

        return tamano;
    }

    //Super importante donde se generan los primeros 4 nodos con los que se va a trabajar
    public Nodo[] seleccionarNodosAleatorios(int numeroJugador) {
        Nodo[] nodosCaidos = new Nodo[5];
        Random rand = new Random();

        for (int i = 0; i < 5; i++) {
            nodosCaidos[i] = cabeza; //Apunta al primer elemento para recorrer la lista
            int paso = rand.nextInt(81) + 1; //Genera numeros del 1 al 81
            for (int j = 0; j < paso; j++) {
                // Evitar seleccionar el mismo nodo que ya ha sido seleccionado
                do {
                    nodosCaidos[i] = nodosCaidos[i].siguiente;
                } while (nodoYaSeleccionado(nodosCaidos, i, nodosCaidos[i]) && nodosCaidos[i].siguiente != null);

                if (nodosCaidos[i].siguiente == null) {
                    break;
                }
            }
        }

        return nodosCaidos;
    }

    //No necesario, para simulaciones
    public boolean verificarNodosRepetidos(Nodo[] nodos) {
        // Utilizar un conjunto para rastrear nodos únicos
        Set<Nodo> nodosUnicos = new HashSet<>();

        for (Nodo nodo : nodos) {
            if (!nodosUnicos.add(nodo)) {
                // Si no se puede agregar al conjunto, significa que ya existe
                return true; // Hay nodos repetidos
            }
        }

        return false; // No hay nodos repetidos
    }

    //Importantisima para cuando se quite un nodo, se use para llamar a la que reemplaza el nodo
    //segun los casos posibles
    //1er caso: Se quita un nodo, el jugador recibe nuevo numero, existe un nodo actual con una expresion
    //que corresponde con el valor del jugador, se crea un nuevo nodo aleatorio sobre el que se quito, asegurandose
    //que sea diferente de los demas
    //2do caso: Se quita un nodo, el jugador recibe un nuevo numero, NO existe un nodo actual con una expresion
    //que corresponde con el valor del jugador, se crea un nuevo nodo asegurandose QUE CORRESPONDA al valor del jugador 
    public void verificarAntesDeReemplazar(Nodo[] nodosCaidos, int indiceSeleccionado, int numeroJugador, Enemigo e) {
        // Verifica si hay un nodo en nodosCaidos que corresponde al valor del jugador
        boolean nodoExistente = false;
        for (Nodo nodo : nodosCaidos) {
            int resultadoExpresion = MiPanel.evaluarExpresion(nodo.expresion);
            if (resultadoExpresion == numeroJugador) {
                nodoExistente = true;
                break;
            }
        }

        this.reemplazarNodo(nodosCaidos, indiceSeleccionado, numeroJugador, nodoExistente, e);
    }

    //Esta es la que se encarga de hacer el reemplazo del nodo
    public Nodo[] reemplazarNodo(Nodo[] nodosCaidos, int indiceSeleccionado, int numeroJugador, boolean nodoExistente, Enemigo e) {
        System.out.println(nodoExistente);
        System.out.println(numeroJugador);
        if (nodoExistente) {
            Random rand = new Random();

            // Comprobar si la lista no está vacía
            // Seleccionar aleatoriamente un nodo de la lista
            int paso = rand.nextInt(81) + 1;
            Nodo nodoNuevo = cabeza;

            for (int i = 0; i < paso; i++) {
                // Evitar un puntero nulo si la lista es más corta que el número de pasos
                if (nodoNuevo != null) {
                    nodoNuevo = nodoNuevo.siguiente;
                } else {
                    break;
                }
            }

            // Reemplazar solo el nodo seleccionado por el jugador con el nuevo nodo
            if (indiceSeleccionado >= 0 && indiceSeleccionado < nodosCaidos.length) {
                if (nodoNuevo != null && nodoNuevo.expresion != null) {
                    nodosCaidos[indiceSeleccionado].expresion = nodoNuevo.expresion;
                    // Se pueden realizar otras operaciones de reemplazo según tus necesidades
                } else {
                    // Manejar el caso cuando nodoNuevo o su expresión son nulos
                    // Puedes lanzar una excepción, asignar un valor predeterminado, etc.
                }
            }

            // Verificar que la nueva expresión sea diferente de las expresiones actuales
            boolean expresionDiferente = false;
            while (!expresionDiferente) {
                expresionDiferente = true;  // Suponemos que la expresión es diferente hasta que se demuestre lo contrario

                // Verificar si la nueva expresión es igual a alguna de las expresiones actuales
                for (Nodo nodo : nodosCaidos) {
                    if (nodo != null && nodo.expresion != null && nodoNuevo != null && nodoNuevo.expresion != null
                            && nodo.expresion.equals(nodoNuevo.expresion)) {
                        expresionDiferente = false;  // La expresión no es diferente, necesitamos generar una nueva
                        break;
                    }
                }

                // Generar una nueva expresión si es necesario
                if (!expresionDiferente) {
                    // Comprobar si la lista no está vacía
                    if (cabeza != null) {
                        // Generar una nueva expresión para el nodoNuevo
                        int nuevoPaso = rand.nextInt(81) + 1;
                        for (int i = 0; i < nuevoPaso; i++) {
                            // Evitar un puntero nulo si la lista es más corta que el número de pasos
                            if (nodoNuevo != null) {
                                nodoNuevo = nodoNuevo.siguiente;
                            } else {
                                break;
                            }
                        }
                    }
                }
            }

            // Reemplazar solo el nodo seleccionado por el jugador con el nuevo nodo
            if (indiceSeleccionado >= 0 && indiceSeleccionado < nodosCaidos.length) {
                if (nodoNuevo != null && nodoNuevo.expresion != null) {
                    nodosCaidos[indiceSeleccionado].expresion = nodoNuevo.expresion;
                    e.setPro(nodosCaidos[indiceSeleccionado].expresion);
                    // Se pueden realizar otras operaciones de reemplazo según tus necesidades
                } else {
                    // Manejar el caso cuando nodoNuevo o su expresión son nulos
                    // Puedes lanzar una excepción, asignar un valor predeterminado, etc.
                }
            }

        } else {
            nodosCaidos[indiceSeleccionado] = buscarNodoConExpresion(numeroJugador);
            e.setPro(nodosCaidos[indiceSeleccionado].expresion);
        }
        return nodosCaidos;
    }

    public void compararNodos(Nodo[] nodosCaidos, int numeroJugador) {
        boolean nodoEncontrado = false;
        for (Nodo nodosCaido : nodosCaidos) {
            int resultadoExpresion = MiPanel.evaluarExpresion(nodosCaido.expresion);
            // Comparar el resultado de la expresión con el número del jugador
            if (resultadoExpresion == numeroJugador) {
                nodoEncontrado = true;
                break;
            }
        }
        // Si no se encontró un nodo con la expresión correcta, asegurar que haya al menos un nodo
        if (!nodoEncontrado) {
            Random rand = new Random();
            int indiceCorrecto = rand.nextInt(5);
            nodosCaidos[indiceCorrecto] = buscarNodoConExpresion(numeroJugador);
        }
    }

    private Nodo buscarNodoConExpresion(int numero) {
        Nodo temp = cabeza;
        while (temp != null) {
            if (MiPanel.esExpresionCorrecta(numero, temp.expresion)) {
                return temp;
            }
            temp = temp.siguiente;
        }
        // Si no se encuentra un nodo con la expresión correcta, simplemente devolver el primer nodo
        return cabeza;
    }

    private static boolean nodoYaSeleccionado(Nodo[] nodosCaidos, int currentIndex, Nodo selectedNode) {
        for (int k = 0; k < currentIndex; k++) {
            if (nodosCaidos[k] == selectedNode) {
                return true;
            }
        }
        return false;
    }

}
