import javax.swing.*;//���������� ��� ����������� �����������
import java.awt.*;//���������� swing ��� �������� ����������
import java.awt.Graphics;//���������� ����������� ������������ �����������
import java.awt.event.ActionEvent;//���� ������������� �������
import java.awt.event.ActionListener;//�������� ������� � ������� �������
import java.awt.event.KeyAdapter;//��������� ������� ����������
import java.awt.event.KeyEvent;//����������� ������� �������

public class SnakeGameMain extends JPanel implements ActionListener{//����� ����������� �� ������ JPanel ��� ������� ���������� �������� ���������� ����
	public static JFrame jFrame;
	
	public static final int SCALE = 32;//������ �����
	public static final int WIDTH = 30;//������ ���� 30 ������
	public static final int HEIGHT = 30;//������ ���� 30 ������
	public static int speed = 10;
	Snake s = new Snake(5,6,5,5);//���������� ������
	
	Apple apple = new Apple(Math.abs((int) (Math.random() * SnakeGameMain.WIDTH - 1)), Math.abs((int) (Math.random() * SnakeGameMain.HEIGHT - 1))); //��������� ��������� ������
	
	Timer timer = new Timer(2300/speed,this);//������ �������� ������
	
	public SnakeGameMain() {//����������� ����
		timer.start();//����� �������� ������
		addKeyListener(new KeyBoard());//������������� ����������
		setFocusable(true);//��������� ��������� �������
	}
	public void paint(Graphics g) {//����������� ��������
		g.setColor(Color.black); //���� ����
		g.fillRect(0, 0, WIDTH*SCALE, HEIGHT*SCALE);//���������� ����
	
	
	for (int x = 0; x < WIDTH*SCALE; x+=SCALE) {//��������� ������������ �����
		g.setColor(Color.white);
		g.drawLine(x, 0, x, HEIGHT*SCALE);
	}
	
	for (int y = 0; y < HEIGHT*SCALE; y+=SCALE) {//��������� �������������� �����
		g.setColor(Color.white);
		g.drawLine(0, y, WIDTH*SCALE, y);
	}
	
	g.setColor(Color.red);//���� ������
	g.fillOval(apple.posX*SCALE+4, apple.posY*SCALE+4, SCALE-8, SCALE-8);//
	
	for (int  l = 1; l < s.lenght; l++) {//��������� ������
		g.setColor(Color.green);//���� ���� ������
		g.fillRect(s.sX[l]*SCALE+3, s.sY[l]*SCALE+3, SCALE-6, SCALE-6);
		g.setColor(Color.white);//���� ������ ������
		g.fillRect(s.sX[0]*SCALE+3, s.sY[0]*SCALE+3, SCALE-6, SCALE-6);
	}
	
	
	}
	public static void main (String[] args ) {
		jFrame = new JFrame("MORE GAMES");//���������
		jFrame.setSize(WIDTH*SCALE+6, HEIGHT*SCALE+32);//������ ����
		
		jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//���������� �� ��������
		jFrame.setResizable(false);//������ �� ���������� ����
		jFrame.setLocationRelativeTo(null);//���������� �� ��������
		jFrame.add(new SnakeGameMain());//���������
		jFrame.setVisible(true);//������� �������
	}
	@Override//���������� � ������� ������
	public void actionPerformed(ActionEvent e) {
		s.move();//������ �������� ������
		
		if((s.sX[0] == apple.posX)&&(s.sY[0] == apple.posY)) {;
		apple.setRandomPosition();
		s.lenght++;
		}
		for(int l = 1; l < s.lenght; l++) {//���� ��������������
			if((s.sX[l] == apple.posX)&&(s.sY[l] == apple.posY)) {//��������� ������
				apple.setRandomPosition();
			}
			if((s.sX[0] == s.sX[l])&&(s.sY[0] == s.sY[l])) {//������� ��������� � ����������� ����
				timer.stop();//����� ����
				JOptionPane.showMessageDialog(null, "GAME OVER! START OVER?");//���������, ���� ���� ����
				jFrame.setVisible(false);
				s.lenght = 2;
				s.direction = 0;
				apple.setRandomPosition();
				jFrame.setVisible(true);
				timer.start();
				
			}
		}
		repaint();//����������� ������
	}
	
	public class KeyBoard extends KeyAdapter{//���������� ������� � ����������
		public void keyPressed(KeyEvent event) {
			int key = event.getKeyCode();
			
			if((key == KeyEvent.VK_UP) && (s.direction !=2)) s.direction = 0;//���� ������ ���������� � ������ �������� ������ ����������������
			if((key == KeyEvent.VK_DOWN)&& (s.direction !=0)) s.direction = 2;
			if((key == KeyEvent.VK_LEFT) && (s.direction !=1))s.direction = 3;
			if((key == KeyEvent.VK_RIGHT) && (s.direction !=3))s.direction = 1;
		}
	}
}