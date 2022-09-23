import java.util.ArrayList;
import java.util.Scanner;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.StringTokenizer;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.*;
import java.util.*;
import java.io.Serializable;
class Indicador implements Serializable{
	private int fila;
    private int columna;
    private int num;

    public Indicador(int fil, int col, int nm) {
        this.fila = fil;
        this.columna = col;
        this.num = nm;
    }

    public int getFila() {
        return this.fila;
    }

    public int getColumna() {
        return this.columna;
    }

    public int getNum() {
        return this.num;
    }

    public void setFila(int fil) {
        this.fila = fil;
    }

    public void setColumna(int col) {
        this.columna = col;
    }

    public void setNum(int nm) {
        this.num = nm;
    }
}
class Casilla implements Serializable{
	private int posX;
    private int posY;
    private boolean pintado;
    public Casilla(int pPosX, int pPosY, boolean estado) {
        this.posX = pPosX;
        this.posY = pPosY;
        this.pintado = estado;
    }

    public void setposX(int pPosX) {
        this.posX = pPosX;
    }

    public void setposY(int pPosy) {
        this.posY = pPosy;
    }

    public void setPintado(boolean estado) {
        this.pintado = estado;
    }

    public int getPosX() {
        return this.posX;
    }

    public int getPosY() {
        return this.posY;
    }

    public boolean getPintado() {
        return this.pintado;
    }
}
class Tablero implements Serializable{
	private int filas;
    private int columnas;
    private ArrayList<ArrayList<Casilla>> casillas;

    public Tablero(int fil, int col) {
        this.filas = fil;
        this.columnas = col;
        casillas = new ArrayList<ArrayList<Casilla>>();
    }

    public void setFilas(int fil) {
        this.filas = fil;
    }

    public void setColumnas(int col) {
        this.columnas = col;
    }

    public void setCasillas(ArrayList<ArrayList<Casilla>> pCasillas) {
        this.casillas = pCasillas;
    }

    public int getColumna() {
        return this.columnas;
    }

    public int getFilas() {
        return this.filas;
    }

    public ArrayList<ArrayList<Casilla>> getCasillas() {
        return this.casillas;
    }

    public void agregarCasillas(ArrayList<Casilla> aAgregar) {
        this.casillas.add(aAgregar);
    }

    public boolean estaPintado(Casilla casilla) {
        return (casillas.get(casilla.getPosX()).get(casilla.getPosY())).getPintado();        
    }
    public void pintar(Casilla casilla){
        (casillas.get(casilla.getPosX()).get(casilla.getPosY())).setPintado(true);
    }
    public void pintar(Casilla casilla1, Casilla casilla2){
        int x1 = casilla1.getPosX();
        int x2 = casilla2.getPosX();
        int y1 = casilla1.getPosY();
        int y2 = casilla2.getPosY();
        if ((x2 - x1) != 0) {
            //esto es para que cuando metan las coordanadas al reves igual siga sirviendo uwu

            int m = (y2 - y1) / (x2 - x1);
            //linea horizaontal
            if (m == 0){
                if (y2 > y1 && x1 == x2) {
                    int tempy;
                    tempy = y1;
                    y1 = y2;
                    y2 = tempy;
                }                
                for (int i = x1; i <= x2; i++) {
                    casillas.get(i).get(y1).setPintado(true);
                }                
            } //linea diagonal negativa :D
            else if (m == 1) {
                if (x1 > x2 && y1 > y2) {
                    int tempy;
                    tempy = y1;
                    y1 = y2;
                    y2 = tempy;
                }
                int i = x1, j = y1;                
                while (i <= x2 && j <= y2) {
                    casillas.get(i).get(i).setPintado(true);
                    i++;
                    j++;
                }                
            } //linea diagonal positiva
            else if (m == -1) {
                if (x2 > x1 && y2 < y1) {
                    int tempy, tempx;
                    tempy = y1;
                    tempx = x1;
                    y1 = y2;
                    y2 = tempy;
                    x1 = x2;
                    x2 = tempx;
                }
                int i = x1, j = y1;                
                while (i >= 0 && j <= y2) {
                    casillas.get(i).get(j).setPintado(true);
                    i--;
                    j++;
                }               
            }
        } else {
            if (x2 > x1 && y1 == y2) {
                int tempx;
                tempx = x1;
                x1 = x2;
                x2 = tempx;
            }            
            for (int i = y1; i <= y2; i++) {
                casillas.get(x1).get(i).setPintado(true);
            }           
        }     
    }
    public boolean estaPintado(Casilla casilla1, Casilla casilla2) {
        int x1 = casilla1.getPosX();
        int x2 = casilla2.getPosX();
        int y1 = casilla1.getPosY();
        int y2 = casilla2.getPosY();
        if ((x2 - x1) != 0) {
            int m = (y2 - y1) / (x2 - x1);   
            if(!(m!=0||m!=-1||m!=1)){
                System.out.println("Estas coordenadas no son una linea valida(diagonal u horizontal)\nVuelve a intentar");
            }  
        }
        else {
            int m = (x2 - x1) / (y2 - y1);
            if (m != 0) { 
               System.out.println("Estas coordenadas no son una linea vertical\nIntentalo de nuevo!");
                return false;
            }
        }
            
        boolean realizado = true;
        //creo que se puede hacer lo siguiente en el mismo bloque de if anterior pero no quiero correr riesgos asi que lo hago aqui

        if ((x2 - x1) != 0) {
            //esto es para que cuando metan las coordanadas al reves igual siga sirviendo uwu

            int m = (y2 - y1) / (x2 - x1);
            //linea horizaontal
            if (m == 0){
                if (y2 > y1 && x1 == x2) {
                    int tempy;
                    tempy = y1;
                    y1 = y2;
                    y2 = tempy;
                }
                for (int i = x1; i <= x2; i++) {
                    if ((casillas.get(i).get(y1).getPintado()) == false) {
                        realizado = false;
                        System.out.println("La coordenada " + (x1 + 1) + "-" + (i + 1) + "no es valida! vuelve a intentar\n");
                        return realizado;
                    }
                }                
                return realizado;
            } //linea diagonal negativa :D
            else if (m == 1) {
                if (x1 > x2 && y1 > y2) {
                    int tempy;
                    tempy = y1;
                    y1 = y2;
                    y2 = tempy;
                }
                int i = x1, j = y1;
                while (i <= x2 && j <= y2) {
                    if (casillas.get(i).get(j).getPintado() == false) {
                        realizado = false;
                        System.out.println("La coordenada " + (i + 1) + "-" + (j + 1) + "no es valida! vuelve a intentar\n");
                        return realizado;
                    }
                    i++;
                    j++;
                }                
                return realizado;
            } //linea diagonal positiva
            else if (m == -1) {
                if (x2 > x1 && y2 < y1) {
                    int tempy, tempx;
                    tempy = y1;
                    tempx = x1;
                    y1 = y2;
                    y2 = tempy;
                    x1 = x2;
                    x2 = tempx;
                }
                int i = x1, j = y1;
                while (i >= 0 && j <= y2) {
                    if (casillas.get(i).get(j).getPintado() == false) {
                        System.out.println("La coordenada " + (i + 1) + "-" + (j + 1) + "no es valida! vuelve a intentar\n");
                        return realizado;
                    }
                    i--;
                    j++;
                }               
                return realizado;
            }
        } else {
            if (x2 > x1 && y1 == y2) {
                int tempx;
                tempx = x1;
                x1 = x2;
                x2 = tempx;
            }
            for (int i = y1; i < y2; i++) {
                if ((casillas.get(x1).get(i).getPintado()) == false) {
                    realizado = false;
                    System.out.println("La coordenada " + (i + 1) + "-" + (y1 + 1) + " no es valida! uelve a intentar\n");
                    return realizado;
                }
            }            
            return realizado;
        }
        return realizado;
    }
}
class MatrizIndicador implements Serializable{
	private int filas;
    private int columnas;
    private ArrayList<ArrayList<Indicador>> indicadores;

    public MatrizIndicador(int fl, int col) {
        this.filas = fl;
        this.columnas = col;
        indicadores = new ArrayList<ArrayList<Indicador>>();
    }

    public int getFilas() {
        return this.filas;
    }

    public int getColumnas() {
        return this.columnas;
    }

    public ArrayList<ArrayList<Indicador>> getIndicadores() {
        return this.indicadores;
    }

    public void setFilas(int fil) {
        this.filas = fil;
    }

    public void setColumnas(int col) {
        this.columnas = col;
    }

    public void setIndicadores(ArrayList<ArrayList<Indicador>> indica) {
        this.indicadores = indica;
    }

    public void agregarIndicadores(ArrayList<Indicador> aAnaniadir) {
        this.indicadores.add(aAnaniadir);
    }
}
class MundoJuego implements Serializable{
	private Tablero tablero;//tablero lleno
    private Tablero tableroDelJuego;//tablero vacio
    private boolean estadoDelJuego;//si gano o no, carita feliz
    private MatrizIndicador indicadorFilas;
    private MatrizIndicador indicadorColumnas;
    private int filas;
    private int columnas;

    public boolean getEstadoDelJuego() {
        return this.estadoDelJuego;
    }

    public void setEstadoDelJuego(boolean pNuevoEstado) {
        this.estadoDelJuego = pNuevoEstado;
    }

    public Tablero getTablero() {
        return this.tablero;
    }
    public Tablero getTableroDelJuego(){
        return this.tableroDelJuego;
    }

    public MundoJuego() {
        this.tablero = new Tablero(0, 0);
        this.tableroDelJuego = new Tablero(0, 0);
        this.estadoDelJuego = false;
        this.indicadorColumnas = new MatrizIndicador(0, 0);
        this.indicadorFilas = new MatrizIndicador(0, 0);
        this.filas = 0;
        this.columnas = 0;
    }
	public MatrizIndicador getIndicadorFilas() {
        return indicadorFilas;
    }

    public void setIndicadorFilas(MatrizIndicador indicadorFilas) {
        this.indicadorFilas = indicadorFilas;
    }

    public MatrizIndicador getIndicadorColumnas() {
        return indicadorColumnas;
    }

    public void setIndicadorColumnas(MatrizIndicador indicadorColumnas) {
        this.indicadorColumnas = indicadorColumnas;
    }

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }
    public boolean iniciarJuego(String nombreNanograma) {
        Scanner archivo = null;
        //abrimos el archivo 
        try {
            archivo = new Scanner(new File(nombreNanograma));
        } catch (Exception ex) {
            System.out.println("Archivo no existe");
            System.exit(0);
        }
        //leemos el tamanio del tablero, y sabemos que los indicadores de filas tienen la mitad de columnas del tamanio del tablero y el mismo tamanio de filas
        this.filas = archivo.nextInt();
        this.indicadorFilas.setFilas(this.filas);
        this.indicadorFilas.setColumnas((this.filas / 2)+1);
        this.tablero.setFilas(this.filas);
        this.tablero.setColumnas(this.filas);
        this.tableroDelJuego.setFilas(this.filas);
        this.tableroDelJuego.setColumnas(this.filas);       
				for (int i = 0; i < this.filas; i++) {
            //creamos un arraylist que contendra la i fila de indicadores
            ArrayList<Indicador> temp = new ArrayList<Indicador>();
            //recorremos el temporal
            for (int j = 0; j < (this.filas / 2) + 1; j++) {
                //leemos los datos del; archivo
                int temp1 = archivo.nextInt();
                //creamos y agregamos el indicador con toda la informacion		
                temp.add(new Indicador(i, j, temp1));
            }
            //agregamos a la matriz de indicadores toda la informacion del temporal
            this.indicadorFilas.agregarIndicadores(temp);
        }
        //leemos las columnas
        this.columnas = archivo.nextInt();
        //sabemos que el tamanio de la matriz de los iundicadores de columna es lo contrario al de filas por lo tanto tiene el mismo tamanio de columnas y la mitad de filas
        this.indicadorColumnas.setColumnas(this.columnas);
        this.indicadorColumnas.setFilas((this.columnas / 2 + 1));
        //comenzamos con el proceso para la lectura de los indicadores de columna		
        for (int i = 0; i < (this.columnas / 2) + 1; i++) {
            ArrayList<Indicador> temp = new ArrayList<Indicador>();
            for (int j = 0; j < this.columnas; j++) {
                int temp1 = archivo.nextInt();
                temp.add(new Indicador(i, j, temp1));
            }
            this.indicadorColumnas.agregarIndicadores(temp);
        }
        //comenzamos con el proceso para leer el tablero con la informacion de la figura		
        for (int i = 0; i < this.tablero.getFilas(); i++) {
            //creamos el arraylist temporal que guarda las filas del tablero
            ArrayList<Casilla> temp = new ArrayList<Casilla>(); 
           //vamos a rellenar de una vez el tablero vacio con falsos
            ArrayList<Casilla> temp3 = new ArrayList<Casilla>();
            //creamos un booleano que representa el valor del pintado o no por defecto en falso           
            for (int j = 0; j < this.tablero.getColumna(); j++) {
                boolean temp2 = false;
                //leemos el dato
                int temp1 = archivo.nextInt();
                if (temp1 == 1) {
                    temp2 = true;
                }
                temp3.add(new Casilla(i, j, false));
                temp.add(new Casilla(i, j, temp2));
            }
            this.tableroDelJuego.agregarCasillas(temp3);
            this.tablero.agregarCasillas(temp);
        }
        archivo.close();
        return true;
    }

    public String darNombreNanogramo(int nanogramo) {
        switch (nanogramo) {
            case 1:
                return "estrella.txt";
            case 2:
                return "tridente.txt";
            case 3:
                return "botella.txt";
            case 4:
                return "corazon.txt";
            case 5:
                return "aguila.txt";
            case 6:
                return "candelabro.txt";
            case 7:
                return "arcoyflecha.txt";
            case 8:
                return "corona.txt";
            case 9:
                return "luna.txt";
            case 10:
                return "trebol.txt";
            default:
                System.out.println("Inserte el numero de tablero valido por favor!");
        }
        return "";
    }

    public Casilla leerUbicacionPixel(String coordenadas) {
        Casilla aRetornar = new Casilla(0, 0, true);
        StringTokenizer st = new StringTokenizer(coordenadas, "-");
        int aux = Integer.parseInt(st.nextToken());
        aux--;
        if (aux > this.filas) {
            System.out.println("Error! Inserte una fila valida");
            aRetornar = null;
        } else {
            aRetornar.setposX(aux);
        }
        aux = Integer.parseInt(st.nextToken());
        aux--;
        if (aux > this.columnas) {
            System.out.println("Error! Inserte una columna valida");
            aRetornar = null;
        } else {
            aRetornar.setposY(aux);
        }
        return aRetornar;
    }

    public boolean validarUbicacionPixeles(Casilla aValidar) {
        if(this.tablero.estaPintado(aValidar)){
            this.tableroDelJuego.pintar(aValidar);
            return true;
        }
        return false;
    }

    public boolean validarUbicacionPixeles(Casilla aValidar1, Casilla aValidar2) {
        if(this.tablero.estaPintado(aValidar1, aValidar2)){
            this.tableroDelJuego.pintar(aValidar1, aValidar2);
            return true;
        }
        return false;
    }
    public boolean gano(){
        for(int i=0;i<this.tablero.getFilas();i++){
            for(int j=0;j<this.tablero.getColumna();j++){
                Casilla aux1= this.tablero.getCasillas().get(i).get(j);
                Casilla aux2= this.tableroDelJuego.getCasillas().get(i).get(j);
                if(aux1.getPintado()!=aux2.getPintado()){
                    return false;
                }
            }
        }
        System.out.println("Felicidades!! has ganado!!!");
        return true;
    }
 }
 class MundoPantalla implements Serializable{
	 public void imprimirTablero(MundoJuego juegoActual) {
        int contador=1;
        for (int i = 0; i < (juegoActual.getTablero().getFilas()+1)/2; i++) {
            int aux=juegoActual.getTablero().getColumna();
            if (aux == 5) {
                System.out.print("          ");
            } else if (aux == 10) {
                System.out.print("                ");
            } else if (aux == 15) {
                System.out.print("                         ");
            }
            for (int j = 0; j < aux; j++) {                     
                if(juegoActual.getIndicadorColumnas().getIndicadores().get(i).get(j).getNum()==0){
                    System.out.print("    ");
                }
                else{
                    if(juegoActual.getIndicadorColumnas().getIndicadores().get(i).get(j).getNum()<9){
                        System.out.print(" "+juegoActual.getIndicadorColumnas().getIndicadores().get(i).get(j).getNum()+"  ");
                    }
                    else{
                        System.out.print(" "+juegoActual.getIndicadorColumnas().getIndicadores().get(i).get(j).getNum()+" ");
                    }
                }                
            }
            System.out.println("");
        }
        for(int i=0;i<juegoActual.getFilas();i++){
            for(int j=0;j<(juegoActual.getFilas()+1)/2;j++){
                int aux=juegoActual.getIndicadorFilas().getIndicadores().get(i).get(j).getNum();
                if(aux!=0){
                    System.out.print(" "+aux+" ");
                }
                else{
                    System.out.print("   ");
                }
            }
            System.out.print("|");
            for(int k=0;k<juegoActual.getColumnas();k++){
                if(juegoActual.getTableroDelJuego().getCasillas().get(i).get(k).getPintado()){
                    System.out.print("###|");
                }
                else{
                    System.out.print("   |");
                }
            }
            System.out.println(" "+contador);
            contador++;            
        }
        int aux=juegoActual.getTablero().getColumna();
        if (aux == 5) {
                System.out.print("          ");
            } else if (aux == 10) {
                System.out.print("                ");
            } else if (aux == 15) {
                System.out.print("                         ");
            }
        for(int i=0;i<juegoActual.getColumnas();i++){
            System.out.print(" "+(1+i)+"  ");
        }
        System.out.println("");
    } 
}

class Main implements Serializable{
  public static void main(String[] args) {       
 }       Scanner scan = new Scanner(System.in);
        //iniciamos el programa!!! emocion! 
        //pasos a seguir! pedir datos
        int tipoJuego;//nuevoJuego o cargar Juego
        int nanogramo;
        MundoJuego juegoActual = new MundoJuego();
        MundoPantalla pantalla = new MundoPantalla();
        String nombreNanogramo = "";
        do {
            System.out.println("Bievenido!!! \nEscoja una opcion!\n1. Nuevo Juego\n2. Cargar Juego\n0.salir");
            tipoJuego = scan.nextInt();
            if (tipoJuego == 1) {
                boolean aux = false;
                //leemos el numero del tablero carita feliz, aqui va la opcion 1, falta la de cargar juego jaja		
                do {
                    System.out.println("Selecciona el nanograma: \n5 x 5\n1. Estrella\n2. Tridente\n3. Botella\n4. Corazon\n 10 x 10\n5.  Aguila\n6. Candelabro\n7. Arco y flecha\n15 x 15\n8. Corona\n9. Luna\n10. Trebol");
                    nanogramo = scan.nextInt();
                    //con esto comprobamos que se ingreso un numero correspondiente a un tablero
                    nombreNanogramo = juegoActual.darNombreNanogramo(nanogramo);
                    if (!nombreNanogramo.equals("")) {
                        aux = true;
                    }
                    juegoActual.iniciarJuego(nombreNanogramo);
                } while (!aux);
                //juego inicializado
                juegoActual.iniciarJuego(nombreNanogramo);
            }
            else if(tipoJuego==2){
                String nombreArchivo="";
                System.out.println("Inserte el nombre del archivo a cargar sin txt, si quieres recuperar un juego anterior escribe: guardadoAutomatico ");
                nombreArchivo=scan.next();
                juegoActual=cargar(nombreArchivo);
            }            
            //comenzamos con el ciclo que sera el juego en si!
            int opcion;
            do {
                String coordenada = null;
                //se procede a la impresion que aun no quiero hacer, carita feliz 
                pantalla.imprimirTablero(juegoActual.getTableroDelJuego());
                pantalla.imprimirTablero(juegoActual.getTablero());
                System.out.println("Inserte una opcion!\n1. Pintar un pixel.\n2. pintar linea de pixeles.\n3. Guardar\n0. salir");
                opcion = scan.nextInt();
                //se llama a la funcion segun si es 1 o 2 o 0
                if (opcion == 1) {
                    Casilla auxCasilla = null;
                    System.out.println("Inserte la coordenada del pixel");
                    coordenada = scan.next();
                    System.out.println(coordenada);
                    auxCasilla = juegoActual.leerUbicacionPixel(coordenada);
                    if (auxCasilla == null) {
                        System.out.println("La coordenada ingresada no esta dentro del rango del nanogramo!\nvuelve a intenetarlo!");
                    } else {
                        //vamos a validar que el pixel pertenece al nanogramo 
                        if (juegoActual.validarUbicacionPixeles(auxCasilla)) {
                            System.out.println("Buen Trabajo!!");
                        } else {
                            System.out.print("La coordenada: ");
                            System.out.print(coordenada);
                            System.out.println(" no pertendece al nanogramo!\nIntentalo de nuevo!");
                        }
                    }
                } else if (opcion == 2) {
                    Casilla aux1 = null;
                    Casilla aux2 = null;
                    System.out.println("Inserte la coordenada del pixel 1");
                    String coordenada1 = null;
                    coordenada1 = scan.next();
                    System.out.println("Inserte la coordenada del pixel 2");
                    String coordenada2 = null;
                    coordenada2 = scan.next();
                    aux1 = juegoActual.leerUbicacionPixel(coordenada1);
                    aux2 = juegoActual.leerUbicacionPixel(coordenada2);
                    if (aux1 == null) {
                        System.out.print("La coordenada: ");
                        System.out.print(coordenada1);
                        System.out.println(" no esta dentro del rango del nanogramo!\nIntentalo de nuevo!");
                    } else if (aux2 == null) {
                        System.out.print("La coordenada: ");
                        System.out.print(coordenada2);
                        System.out.println(" no esta dentro del rango del nanogramo \nIntentalo de nuevo!");
                    } else {
                        if (juegoActual.validarUbicacionPixeles(aux1, aux2)) {
                            System.out.println("Buen trabajo! sigue asi!");
                        }
                    }
                }
                else if(opcion==3){
                    System.out.println("Inserte el nombre del archivo para guardar! sin txt");
                    String nombreaArchivo;
                    nombreaArchivo=scan.next();
                    guardar(nombreaArchivo, juegoActual);
                }                
                else if (opcion != 0) {
                    System.out.println("Por favor ingresa una opcion valida para el juego!");
                }
                if(opcion!=0){
                    guardar("guardadoAutomatico", juegoActual); 
                    juegoActual.setEstadoDelJuego(juegoActual.gano());
                }
                //falta actualizar                
                //falta comprobar si gano uwu 
                //falta imprimir
                		
            } while (opcion != 0 && juegoActual.getEstadoDelJuego() == false);
        } while (tipoJuego != 0);
        scan.close();
    }
    public static void guardar(String nombreArchivo, MundoJuego juegoAGuardar) {        
        FileOutputStream archivo = null;        
        String extension=".txt";
        nombreArchivo += extension;
        try {
            archivo = new FileOutputStream(new File(nombreArchivo));
            ObjectOutputStream proceso = new ObjectOutputStream(archivo);
            proceso.writeObject(juegoAGuardar);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Archivo no existe");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
    public static MundoJuego cargar(String nombreArchivo) {
        FileInputStream archivoCargado = null;
        MundoJuego m=new MundoJuego();
        String extension = ".txt";        
        nombreArchivo += extension;
        try {
            archivoCargado = new FileInputStream(new File(nombreArchivo));  
            ObjectInputStream entrada= new ObjectInputStream(archivoCargado);
            m = (MundoJuego)entrada.readObject();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            System.out.println("Archivo no existe");
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }        
        return m;
    }
}
