import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
 
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class WindowTest{//クラスMyClient
	public static void main(String[] args) throws InterruptedException{
		GameWindow gw = new GameWindow("テストウィンドウ",400,300);
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
	
	//画面切り替え用メソッド
	public void change(JPanel panel) {
		//ContentPaneにはめ込まれたパネルを削除
		getContentPane().removeAll();
		
		super.add(panel);//パネルの追加
		validate();//更新
		repaint();//再描画
	}
}

class TitleView extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0,0,400,300);
 
		g.setColor(Color.BLACK);
		g.drawString("タイトル画面", 170, 50);
	}
}

class GameView extends JPanel{
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0,0,400,300);
		
		g.setColor(Color.WHITE);
		g.drawString("ゲーム画面", 170, 50);
		g.fillRect(150,125,100,50);
	}
}
