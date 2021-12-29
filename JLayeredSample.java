import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class JLayeredSample extends JFrame implements MouseListener{
	private JLayeredPane c;
	private JButton button_a, button_b, button_c;

	public JLayeredSample() {
	    
		//ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じるときに，正しく閉じるように設定する
		setTitle("JLayeredPane");//ウィンドウのタイトルを設定する
		setSize(500,500);//ウィンドウのサイズを設定する
		c = new JLayeredPane();
		this.getContentPane().add(c);
		
		//アイコンの設定
		ImageIcon image_a = new ImageIcon("cat1.png");
		ImageIcon image_b = new ImageIcon("cat1.png");
		ImageIcon image_c = new ImageIcon("cat1.png");
		
		button_a = new JButton();
		button_a.setIcon(image_a);//イメージを設定
		c.add(button_a);//ペインに貼り付ける
		button_a.setBounds(10, 10,208, 242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        button_a.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
		c.setLayer(button_a,50); //レイヤーの数が大きい方が手前
    button_a.setContentAreaFilled(false);
    button_a.setBorderPainted(false);

		button_b = new JButton();
		button_b.setIcon(image_b);//イメージを設定
		c.add(button_b);//ペインに貼り付ける
		button_b.setBounds(100, 100,208, 242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        button_b.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
		c.setLayer(button_b,100); //レイヤーの数が大きい方が手前
    button_b.setContentAreaFilled(false);
    button_b.setBorderPainted(false);
		
		button_c = new JButton();
		button_c.setIcon(image_c);//イメージを設定
		c.add(button_c);//ペインに貼り付ける
		button_c.setBounds(190, 190,208, 242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        button_c.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
		c.setLayer(button_c,200); //レイヤーの数が大きい方が手前
    button_c.setContentAreaFilled(false);
    button_c.setBorderPainted(false);
		
		//ボタンの生成と配置
        JButton theButton1 = new JButton("レイヤーの変更");//ボタンを作成，テキストの表示
        c.add(theButton1);//ペインに貼り付ける
        theButton1.setBounds(350,30,140,45);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
	}
	
	public static void main(String[] args) {
		JLayeredSample form = new JLayeredSample();
		form.setVisible(true);
	}
	
	public void mouseClicked(MouseEvent e) {//ボタンをクリックしたときの処理
		System.out.println("クリック");
		
		JButton theButton = (JButton)e.getComponent();//型が違うのでキャストする

		System.out.println(theButton.getText());
		
		if(theButton.getText().equals( "レイヤーの変更")){
			
			//レイヤーを順に変更する
			int layer_a = c.getLayer(button_a);
			int layer_b = c.getLayer(button_b);
			int layer_c = c.getLayer(button_c);
			
			System.out.println("button_a = "+c.getLayer(button_a));
			System.out.println("button_b = "+c.getLayer(button_b));
			System.out.println("button_c = "+c.getLayer(button_c));

			c.setLayer(button_a, layer_c);
			c.setLayer(button_b, layer_a);
			c.setLayer(button_c, layer_b);
			
			System.out.println("button_a = "+c.getLayer(button_a));
			System.out.println("button_b = "+c.getLayer(button_b));
			System.out.println("button_c = "+c.getLayer(button_c));
						
			repaint();//オブジェクトの再描画を行う
		}
	}
	
	public void mouseEntered(MouseEvent e) {//マウスがオブジェクトに入ったときの処理
		System.out.println("マウスが入った");
	}
	
	public void mouseExited(MouseEvent e) {//マウスがオブジェクトから出たときの処理
		System.out.println("マウス脱出");
	}
	
	public void mousePressed(MouseEvent e) {//マウスでオブジェクトを押したときの処理（クリックとの違いに注意）
		System.out.println("マウスを押した");
	}
	
	public void mouseReleased(MouseEvent e) {//マウスで押していたオブジェクトを離したときの処理
		System.out.println("マウスを放した");
	}
	
	public void mouseDragged(MouseEvent e) {//マウスでオブジェクトとをドラッグしているときの処理
		System.out.println("マウスをドラッグ");
	}

	public void mouseMoved(MouseEvent e) {//マウスがオブジェクト上で移動したときの処理
		System.out.println("マウス移動");
	}
}
