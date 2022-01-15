import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class pictureCardSample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    ImageIcon[] icons;
    JLabel theLabel1;
    JPanel p = new JPanel();//Containerでが強制描画機能が使えないのでJPanelを使う．使い方はほとんどContainerと同じ

    public pictureCardSample() {
        //ウィンドウを作成する
        this.add(p,BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じるときに，正しく閉じるように設定する
        this.setTitle("pictureCardSample");		//ウィンドウのタイトルを設定する
        this.setSize(300,300);		//ウィンドウのサイズを設定する
        p.setLayout(null);//自動レイアウトの設定を行わない
        p.setSize(300,300);//パネルのサイズはウィンドウに合わせる
        //Container c = this.getContentPane();	//フレームのペインを取得する
        //c.setLayout(null);		//自動レイアウトの設定を行わない
        p.setBackground(Color.CYAN);//ウィンドウの色の設定

        //アイコンの生成
        icons = new ImageIcon[10];
        //アイコンの読み込み
        for(int i=0; i<10 ; i++){
            icons[i] = new ImageIcon("number_"+i+".png");//number_0.pngからnumber_9.pngを用意
        }

        //画像の表示
        theLabel1 = new JLabel(icons[0]);//ボタンを作成
        p.add(theLabel1);//ペインに貼り付ける
        theLabel1.setBounds(100,80,icons[0].getIconWidth(),icons[0].getIconHeight());//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theLabel1.addMouseListener(this);//ラベルをマウスでさわったときに反応するようにする
        theLabel1.setForeground(Color.WHITE); //文字色の設定．Colorの設定は，このページを見て下さい　http://www.javadrive.jp/tutorial/color/

        //ボタンの表示
        JButton theButton = new JButton("カウントダウン");//ボタンを作成，テキストの表示
        p.add(theButton);//ペインに貼り付ける
        theButton.setBounds(70,250,160,25);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton.setActionCommand("StartDown");
        theButton.addActionListener(this);
    }

    public static void main(String[] args) {
        pictureCardSample gui = new pictureCardSample();
        gui.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("マウスをクリックした");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("マウスを押した");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("マウスを放した");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("マウスが入った");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("マウス脱出");
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("マウスをドラッグ");
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("マウス移動");
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("アクション発生");
        System.out.println(e.getSource());
        String theCmd = e.getActionCommand();
        System.out.println("ActionCommand: "+theCmd);

        if(theCmd.equals("StartDown")){
            //
            for(int i = 9; i>=0; i--){
                theLabel1.setIcon(icons[i]);
                p.repaint();
                p.paintImmediately(p.getBounds());
                System.out.println(i);
                try{//Thread.sleepはTry-catchでないとつかえないため
                    Thread.sleep(1000); //1000ミリ秒Sleepする
                }catch(InterruptedException ee){}//エラーがでてもなにもしない
            }
        }
    }
}
