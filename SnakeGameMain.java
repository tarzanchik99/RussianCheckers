import javax.swing.*;//библиотека дл€ графических интерфейсов
import java.awt.*;//библиотека swing дл€ оконного интерфейса
import java.awt.Graphics;//библиотека отображени€ динамических изображений
import java.awt.event.ActionEvent;//блок прослушивани€ событи€
import java.awt.event.ActionListener;//ќжидание событи€ с помощью таймера
import java.awt.event.KeyAdapter;//обработка событий клавиатуры
import java.awt.event.KeyEvent;//отслеживает нажатые клавиши

public class SnakeGameMain extends JPanel implements ActionListener{// ласс наследуемый от класса JPanel дл€ задани€ параметров оконного интерфейса игры
	public static JFrame jFrame;
	
	public static final int SCALE = 32;//размер блока
	public static final int WIDTH = 30;//ширина окна 30 клеток
	public static final int HEIGHT = 30;//высота окна 30 клеток
	public static int speed = 10;
	Snake s = new Snake(5,6,5,5);//координаты змейки
	
	Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1)), Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1))); //случайное по€вление €блока
	
	Timer timer = new Timer(2300/speed,this);//задана скорость змейки
	
	public SnakeGameMain() {//конструктор змеи
		timer.start();//старт движени€ змейки
		addKeyListener(new KeyBoard());//инициализаци€ клавиатуры
		setFocusable(true);//включение фокусного событи€
	}
	public void paint(Graphics g) {//графический художник
		g.setColor(Color.black); //цвет фона
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);//координаты фона
	
	
	for (int x = 0; x < WIDTH*SCALE; x+=SCALE) {//отрисовка вертикальных линий
		g.setColor(Color.white);
		g.drawLine(x, 0, x, HEIGHT*SCALE);
	}
	
	for (int y = 0; y < HEIGHT*SCALE; y+=SCALE) {//отрисовка горизонтальных линий
		g.setColor(Color.white);
		g.drawLine(0, y, WIDTH*SCALE, y);
	}
	
	g.setColor(Color.red);//цвет €блока
	g.fillOval(apple.posX*SCALE+4, apple.posY*SCALE+4, SCALE-8, SCALE-8);//
	
	for (int  l = 1; l < s.lenght; l++) {//отрисовка змейки
		g.setColor(Color.green);//цвет тела змейки
		g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE-6, SCALE-6);
		g.setColor(Color.white);//цвет головы змейки
		g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE-6, SCALE-6);
	}
	
	
	}
	public static void main (String[] args ) {
		jFrame = new JFrame("MORE GAMES");//заколовок
		jFrame.setSize(WIDTH*SCALE+6, HEIGHT*SCALE+32);//размер окна
		
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//завершение по закрытию
		jFrame.setResizable(false);//запрет на расширение окна
		jFrame.setLocationRelativeTo(null);//размещение по середине
		jFrame.add(new SnakeGameMain());//отрисовка
		jFrame.setVisible(true);//сделать видимым
	}
	@Override//объ€вление в базовом классе
	public void actionPerformed(ActionEvent e) {
		s.move();//задали движение змейке
		
		if((s.sX[0] == apple.posX)&&(s.sY[0] == apple.posY)) {;
		apple.setRandomPosition();
		s.lenght++;
		}
		for(int l = 1; l < s.lenght; l++) {//цикл взаимодействи€
			if((s.sX[l] == apple.posX)&&(s.sY[l] == apple.posY)) {//по€вление €блока
				apple.setRandomPosition();
			}
			if((s.sX[0] == s.sX[l])&&(s.sY[0] == s.sY[l])) {//условие остановки и перезапуска игры
				timer.stop();//конец игры
				JOptionPane.showMessageDialog(null, "GAME OVER! START OVER?");//сообщение, если съел себ€
				jFrame.setVisible(false);
				s.lenght = 2;
				s.direction = 0;
				apple.setRandomPosition();
				jFrame.setVisible(true);
				timer.start();
				
			}
		}
		repaint();//перерисовка змейки
	}
	
	public class KeyBoard extends KeyAdapter{//управление змейкой с клавиатуры
		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();
			
			if((key == KeyEvent.VK_UP) && (s.direction !=2)) s.direction = 0;//коды клавиш клавиатуры и запрет движени€ равное противоположному
			if((key == KeyEvent.VK_DOWN)&& (s.direction !=0)) s.direction = 2;
			if((key == KeyEvent.VK_LEFT) && (s.direction !=1))s.direction = 3;
			if((key == KeyEvent.VK_RIGHT) && (s.direction !=3))s.direction = 1;
		}
	}
}