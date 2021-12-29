import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;//画像処理に必要
import java.awt.geom.*;//画像処理に必要

public class ImageRotateSample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    public ImageRotateSample() {
        //ウィンドウを作成する
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じるときに，正しく閉じるように設定する
        this.setTitle("ImageRotateSample");		//ウィンドウのタイトルを設定する
        this.setSize(600,600);		//ウィンドウのサイズを設定する
        Container c = this.getContentPane();	//フレームのペインを取得する
        c.setLayout(null);		//自動レイアウトの設定を行わない
        c.setBackground(Color.CYAN);//ウィンドウの色の設定

        //アイコンの生成
        Icon icon1 = new ImageIcon("image1.png");//なにか画像ファイルをダウンロードしておく
        //RotateIconを使うとクリックしたときに反応は無くなります

        //0°の画像
        JButton theButton1 = new JButton(icon1);//ボタンを作成
        c.add(theButton1);//ペインに貼り付ける
        theButton1.setBounds(45,45,icon1.getIconWidth(),icon1.getIconHeight());//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        // Icon icon1_1 = new ImageIcon("image2.png");//なにか画像ファイルをダウンロードしておく
        // theButton1.setPressedIcon(icon1_1);//押されたときに表示されるアイコン
        // System.out.println("getIconWidth="+icon1.getIconWidth());//回転後のIconの幅を得る方法
        // System.out.println("getIconHeight="+icon1.getIconHeight());//回転後のIconの高さを得る方法
        // Icon icon1_2 = new ImageIcon("image3.png");//なにか画像ファイルをダウンロードしておく
        // theButton1.setRolloverIcon(icon1_2);//マウスが上に重なったときに表示されるアイコン

        //90°回転の画像
        Icon icon2 = (Icon) new RotateIcon(icon1,145);//なにか画像ファイルをダウンロードしておく
        JButton theButton2 = new JButton(icon2);//ボタンを作成
        c.add(theButton2);//ペインに貼り付ける
        theButton2.setBounds(300,45,icon2.getIconWidth(),icon2.getIconHeight());//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton2.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        System.out.println("getIconWidth="+icon2.getIconWidth());//回転後のIconの幅を得る方法
        System.out.println("getIconHeight="+icon2.getIconHeight());//回転後のIconの高さを得る方法

        //180°回転の画像
        Icon icon3 = (Icon) new RotateIcon(icon1,180);//なにか画像ファイルをダウンロードしておく
        JButton theButton3 = new JButton(icon3);//ボタンを作成
        c.add(theButton3);//ペインに貼り付ける
        theButton3.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theButton3.setBounds(45,300,icon3.getIconWidth(),icon3.getIconHeight());//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton3.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //270°回転の画像
        Icon icon4 = (Icon) new RotateIcon(icon1,270);//なにか画像ファイルをダウンロードしておく
        JButton theButton4 = new JButton(icon4);//ボタンを作成
        c.add(theButton4);//ペインに貼り付ける
        theButton4.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theButton4.setBounds(300,300,icon4.getIconWidth(),icon4.getIconHeight());//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton4.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
    }

    public static void main(String[] args) {
        ImageRotateSample gui = new ImageRotateSample();
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
    }

    //アイコンの回転のクラス
    //下記のURLのコードを利用
    //http://ateraimemo.com/Swing/RotatedIcon.html
    class RotateIcon implements Icon {
        private final Dimension d = new Dimension();
        private final Image image;
        private AffineTransform trans;
        protected RotateIcon(Icon icon, int rotate) {
            if (rotate % 90 != 0) {
                throw new IllegalArgumentException(rotate + ": Rotate must be (rotate % 90 == 0)");
            }
            d.setSize(icon.getIconWidth(), icon.getIconHeight());
            image = new BufferedImage(d.width, d.height, BufferedImage.TYPE_INT_ARGB);
            Graphics g = image.getGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();

            int numquadrants = (rotate / 90) % 4;
            if (numquadrants == 1 || numquadrants == -3) {
                trans = AffineTransform.getTranslateInstance(d.height, 0);
                int v = d.width;
                d.width = d.height;
                d.height = v;
            } else if (numquadrants == -1 || numquadrants == 3) {
                trans = AffineTransform.getTranslateInstance(0, d.width);
                int v = d.width;
                d.width = d.height;
                d.height = v;
            } else if (Math.abs(numquadrants) == 2) {
                trans = AffineTransform.getTranslateInstance(d.width, d.height);
            } else {
                trans = AffineTransform.getTranslateInstance(0, 0);
            }
            trans.quadrantRotate(numquadrants);
        }
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.translate(x, y);
            g2.drawImage(image, trans, c);
            g2.dispose();
        }
        @Override public int getIconWidth() {
            return d.width;
        }
        @Override public int getIconHeight() {
            return d.height;
        }
    }
}
