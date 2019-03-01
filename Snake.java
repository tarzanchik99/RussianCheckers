
public class Snake {//класс змейка
	public int lenght = 2;//начальный размер змеи
	public int direction = 0;//направления движения змейки
	
	public int sX[] = new int[300];
	public int sY[] = new int[300];
	
	public Snake(int x1, int y1, int x2, int y2) {//конструктор змейки
		sX[0] = x1;
		sX[1] = x2;
		sY[0] = y1;
		sY[1] = y2;
	}
	public void move() {//метод движения змейки
		for(int l = lenght; l > 0; l--) {//цикл роста змейки
			sX[l] = sX[l-1];
			sY[l] = sY[l-1];
		}
		
		
		if(direction == 0) sY[0]--;//вверх
		if(direction == 2) sY[0]++;//вниз
		if(direction == 1) sX[0]++;//вправо
		if(direction == 3) sX[0]--;//влево
		
		if(sX[0]>SnakeGameMain.WIDTH-1) sX[0] = 0;//переход после ухода за поле
		if(sX[0]<0) sX[0] = SnakeGameMain.WIDTH - 1;
		if(sY[0]>SnakeGameMain.HEIGHT-1) sY[0] = 0;
		if(sY[0]<0) sY[0] = SnakeGameMain.HEIGHT - 1;
	}
}
