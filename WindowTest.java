import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class WindowTest{//�N���XMyClient
	public static void main(String[] args) throws InterruptedException{
		GameWindow gw = new GameWindow("�e�X�g�E�B���h�E",400,300);
		gw.setVisible(true);
		gw.change(new GameView());
		Thread.sleep(1000);
		gw.change(new TitleView());
	}
}

class GameWindow extends JFrame{
	private Thread th = null;
	public GameWindow(String title, int width, int height) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(width,height);
		setLocationRelativeTo(null);
		setResizable(false);
	}
	
	//��ʐ؂�ւ��p���\�b�h
	public void change(JPanel panel) {
		//ContentPane�ɂ͂ߍ��܂ꂽ�p�l�����폜
		getContentPane().removeAll();
		
		super.add(panel);//�p�l���̒ǉ�
		validate();//�X�V
		repaint();//�ĕ`��
	}
}

class TitleView extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,400,300);
 
		g.setColor(Color.BLACK);
		g.drawString("�^�C�g�����", 170, 50);
	}
}

class GameView extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,400,300);
		
		g.setColor(Color.WHITE);
		g.drawString("�Q�[�����", 170, 50);
		g.fillRect(150,125,100,50);
	}
}
