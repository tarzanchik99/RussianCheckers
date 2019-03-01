public class Apple{//класс €блоко
	public int posY;
	public int posX;
	
	public Apple(int x, int y) {//инициализаци€ позиции €блока
		posX = x;
		posY = y;
	}
	
	public void setRandomPosition() {//по€вление €блока
		posX = Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1));
		posY = Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1));
	}
}