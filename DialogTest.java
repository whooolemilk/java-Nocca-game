import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;//画像処理に必要
import java.awt.geom.*;//画像処理に必要

public class DialogTest extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    public DialogTest() {
        //ウィンドウを作成する
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じるときに，正しく閉じるように設定する
        this.setSize(1024, 786);		//ウィンドウのサイズを設定する
        Container c = this.getContentPane();	//フレームのペインを取得する
        c.setLayout(null);		//自動レイアウトの設定を行わない

        //ボタンの生成
        JButton theButton1 = new JButton("ダイアログを開く");//ボタンを作成
        theButton1.setBounds(245,245,150,30);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton1.setActionCommand("PUSH_Button1");//ボタンをクリックしたときにactionPerformedのtheCmdで受けとる文字列
        theButton1.addActionListener(this);//ボタンをクリックしたときにactionPerformedで受け取るため

        c.add(theButton1);//ペインに貼り付ける

     }

    public static void main(String[] args) {
        DialogTest gui = new DialogTest();
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

        //theButton1を押したときに，ダイアログを表示する
        if(theCmd.equalsIgnoreCase("PUSH_Button1")){
            WinDialogWindow dlg = new WinDialogWindow(this);
            dlg.add(new JButton());
            setVisible(true);
        }
    }
}

//ダイアログのためのクラス
//絵をクリックしたら閉じるようにしている
class WinDialogWindow extends JDialog implements ActionListener{
    WinDialogWindow(JFrame owner) {
        super(owner);//呼び出しもととの親子関係の設定．これをコメントアウトすると別々のダイアログになる

		    Container c = this.getContentPane();	//フレームのペインを取得する
        c.setLayout(null);		//自動レイアウトの設定を行わない

        // JButton theButton = new JButton();//画像を貼り付けるラベル
        // ImageIcon theImage = new ImageIcon("win.jpg");//なにか画像ファイルをダウンロードしておく
        // theButton.setIcon(theImage);//ラベルを設定
        // theButton.setBounds(0,0,526,234);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        // theButton.addActionListener(this);//ボタンをクリックしたときにactionPerformedで受け取るため
        // c.add(theButton);//ダイアログに貼り付ける（貼り付けないと表示されない

        setTitle("You Win!");//タイトルの設定
        setSize(800, 500);//大きさの設定
        setResizable(false);//拡大縮小禁止//trueにすると拡大縮小できるようになる
        setUndecorated(true); //タイトルを表示しない
        setModal(true);//上を閉じるまで下を触れなくする（falseにすると触れる）

        // JPanel scene4 = new JPanel();
        // c.add(scene4);
        // scene4.setBounds(112, 143, 800, 500);

        c.setBackground(Color.green);

        JButton rePlayButton = new JButton("もういっかい");
        JButton endButton = new JButton("あそびかた");
        c.add(rePlayButton);
        c.add(endButton);
        rePlayButton.addActionListener(this);
        rePlayButton.setActionCommand("REPLAY");
        endButton.addActionListener(this);
        endButton.setActionCommand("END");

        rePlayButton.setBounds(250, 200, 300, 100);
        endButton.setBounds(250, 350, 300, 100);

        //ダイアログの大きさや表示場所を変更できる
        //親のダイアログの中心に表示したい場合は，親のウィンドウの中心座標を求めて，子のダイアログの大きさの半分ずらす
        setLocation(owner.getBounds().x+owner.getWidth()/2-this.getWidth()/2,owner.getBounds().y+owner.getHeight()/2-this.getHeight()/2);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        this.dispose();//Dialogを廃棄する
    }
}