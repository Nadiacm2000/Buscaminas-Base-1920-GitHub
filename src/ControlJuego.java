import java.util.ArrayList;
import java.util.Random;

/**
 * Clase gestora del tablero de juego.
 * Guarda una matriz de enteros representado el tablero.
 * Si hay una mina en una posición guarda el número -1
 * Si no hay una mina, se guarda cuántas minas hay alrededor.
 * Almacena la puntuación de la partida
 * @author jesusredondogarcia
 *
 */
public class ControlJuego {
	private final static int MINA = -1;
	final int MINAS_INICIALES = 20;
	final int LADO_TABLERO = 10;

	private int [][] tablero;
	private int puntuacion;
	
	
	public ControlJuego() {
		//Creamos el tablero:
		tablero = new int[LADO_TABLERO][LADO_TABLERO];
		
		//Inicializamos una nueva partida
		inicializarPartida();
	}
	
	
	/**Método para generar un nuevo tablero de partida:
	 * La estructura tablero debe existir. 
	 * Al final el tablero se habrá inicializado con tantas minas como marque la variable MINAS_INICIALES. 
	 * 			El resto de posiciones que no son minas guardan en el entero cuántas minas hay alrededor de la celda
	 */
	public void inicializarPartida(){
		puntuacion = 0;
		//TODO: Repartir minas e inicializar puntuacion. Si hubiese un tablero anterior, lo pongo todo a cero para inicializarlo.
		int RandomI;
		int RandomJ;
		
		for (int i = 0; i < LADO_TABLERO; i++) {
			for (int j = 0; j < LADO_TABLERO; j++) {
				tablero[i][j] = 0;
			}
		}
		
		int sumMina = 0;
		do{
			RandomI = (int)(Math.random()*10);
			RandomJ = (int)(Math.random()*10);
			if (tablero[RandomI][RandomJ] != MINA){
				tablero[RandomI][RandomJ] = MINA;
				sumMina++;
			}
		}while(sumMina!=20);
			
		//Al final del metodo hay que guardar el numero de minas para las casillas que no son mina:
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				if (tablero[i][j] != MINA){
					tablero[i][j] = calculoMinasAdjuntas(i,j);
				}
			}
		}
		
		depurarTablero();
	}
	
	/**Calculo de las minas adjuntas: 
	 * Para calcular el n�mero de minas tenemos que tener en cuenta que no nos salimos nunca del tablero.
	 * Por lo tanto, como mucho la i y la j valdran LADO_TABLERO-1.
	 * Por lo tanto, como poco la i y la j valdran 0.
	 * @param i: posicion vertical de la casilla a rellenar
	 * @param j: posicion horizontal de la casilla a rellenar
	 * @return : El número de minas que hay alrededor de la casilla [i][j]
	 **/
	private int calculoMinasAdjuntas(int posX, int posY){
		int contMinas=0;
		for (int i = posX-1; i <= posX+1; i++) {
			for (int j = posY-1; j <= posY+1; j++) {
				if ((i>=0) && (j>=0) && (i<LADO_TABLERO) && (j<LADO_TABLERO)){
					if(tablero[i][j] == MINA) {
						contMinas++;
					}
				}
			}
		}
		
		return contMinas;
	}
	
	/**
	 * Método que nos permite 
	 * La casilla nunca debe haber sido abierta antes, no es controlado por el ControlJuego. Por lo tanto siempre sumaremos puntos
	 * @param i: posición verticalmente de la casilla a abrir
	 * @param j: posición horizontalmente de la casilla a abrir
	 * @return : Verdadero si no ha explotado una mina. Falso en caso contrario.
	 */
	public boolean abrirCasilla(int i, int j){
		if(tablero[i][j] != MINA) {
			puntuacion++;
			tablero[i][j] = calculoMinasAdjuntas(i, j);
			return true;
		}else {
			return false;
		}
		
	}
	
	
	
	/**
	* Método que checkea si se ha terminado el juego porque se han abierto todas las casillas.
	* @return Devuelve verdadero si se han abierto todas las celdas que no son minas.
	**/
	public boolean esFinJuego(){
		return puntuacion == ((LADO_TABLERO*LADO_TABLERO)-MINAS_INICIALES);
	}
	
	
	/**
	 * Método que pinta por pantalla toda la información del tablero, se utiliza para depurar
	 */
	public void depurarTablero(){
		System.out.println("---------TABLERO--------------");
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]+"\t");
			}
			System.out.println();
		}
		System.out.println("\nPuntuacion: "+puntuacion);
	}

	/**
	 * Método que se utiliza para obtener las minas que hay alrededor de una celda
	 * El tablero tiene que estar ya inicializado, por lo tanto no hace falta calcularlo, símplemente consultarlo
	 * @param i : posición vertical de la celda.
	 * @param j : posición horizontal de la cela.
	 * @return Un entero que representa el número de minas alrededor de la celda
	 */
	public int getMinasAlrededor(int i, int j) {
		
		return tablero[i][j];
	}

	/**
	 * Método que devuelve la puntuación actual
	 * @return Un entero con la puntuación actual
	 */
	public int getPuntuacion() {
		return puntuacion;
	}
	
}
