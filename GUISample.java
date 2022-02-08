import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import javax.swing.Timer;//Timerの利用時に必要

import java.io.File;//音楽再生時に必要
import javax.sound.sampled.AudioFormat;//音楽再生時に必要
import javax.sound.sampled.AudioSystem;//音楽再生時に必要
import javax.sound.sampled.Clip;//音楽再生時に必要
import javax.sound.sampled.DataLine;//音楽再生時に必要

public class GUISample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    JButton theButton1;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton[] theButtonMulti;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton theButtonSoundStart1;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton theButtonSoundStart2;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton theButtonSoundStop;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JLabel theLabelA;//どこからでもアクセスできるように，クラスのメンバとして宣言
    ImageIcon icon1;
    JTextField theTextFieldForScrollBar;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JScrollBar theScrollBar;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton theButtonStart;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JButton theButtonStop;//どこからでもアクセスできるように，クラスのメンバとして宣言
    Timer timer;//Timerを利用する．import javax.swing.Timerをimportする必要がある（上に追加する）
    JProgressBar theProgressBar;//どこからでもアクセスできるように，クラスのメンバとして宣言
    JLabel theLabelForProgressBar;//どこからでもアクセスできるように，クラスのメンバとして宣言
    SoundPlayer theSoundPlayer1;//どこからでもアクセスできるように，クラスのメンバとして宣言
    SoundPlayer theSoundPlayer2;//どこからでもアクセスできるように，クラスのメンバとして宣言

    public GUISample() {
        //ウィンドウを作成する
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ウィンドウを閉じるときに，正しく閉じるように設定する
        this.setTitle("GUISample");		//ウィンドウのタイトルを設定する
        this.setSize(1000,600);		//ウィンドウのサイズを設定する
        Container c = this.getContentPane();	//フレームのペインを取得する
        c.setLayout(null);		//自動レイアウトの設定を行わない
        c.setBackground(Color.ORANGE);//ウィンドウの色の設定

        //ボタンの生成と配置
        theButton1 = new JButton("ボタンのサンプル");//ボタンを作成，テキストの表示
        c.add(theButton1);//ペインに貼り付ける
        theButton1.setBounds(45,45,140,45);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //絵の付いたボタンの生成と配置
        icon1 = new ImageIcon("images.png");//なにか画像ファイルをダウンロードしておく
        JButton theButton2 = new JButton(icon1);//ボタンを作成，画像を設定する
        c.add(theButton2);//ペインに貼り付ける
        theButton2.setBounds(45,90,208,242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButton2.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //ボタンの区別の方法はいくつもあります．何で区別してかまいません．
        //この演習では，ActionCommandで区別する例を使っています．
        //ActionCommandで区別

        //複数ボタンの区別の例（ActionCommandで区別のため，setActionCommandに入れています．mouseClicked内で，getActionCommandを使って取り出しています）
        JButton[] theButtonMulti = new JButton[3];
        for( int i = 0; i<3 ; i++){
          theButtonMulti[i] = new JButton("Button "+i);//ボタンを作成，テキストの表示
          c.add(theButtonMulti[i]);//ペインに貼り付ける
          theButtonMulti[i].setBounds(45+i*90,350,80,25);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
          theButtonMulti[i].addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
          theButtonMulti[i].setActionCommand(Integer.toString(i));//ボタンに情報を付加する（何のボタンかを判断するために利用），Integer.toStringを使っているのは，setActionCommandが文字しか受け取らないため
        }
        theLabelA = new JLabel("結果表示ラベル");
        c.add(theLabelA);
        theLabelA.setBounds(320,350,120,25);
        theLabelA.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //音再生用のボタンの作成
        theButtonSoundStart1 = new JButton("音楽の再生1(BGM)");//ボタンを作成，テキストの表示
        c.add(theButtonSoundStart1);//ペインに貼り付ける
        theButtonSoundStart1.setBounds(45,400,150,25);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButtonSoundStart1.setActionCommand("PlaySound1");
        theButtonSoundStart1.addActionListener(this);

        theButtonSoundStop = new JButton("音楽の停止");//ボタンを作成，テキストの表示
        c.add(theButtonSoundStop);//ペインに貼り付ける
        theButtonSoundStop.setBounds(200,400,100,25);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButtonSoundStop.setActionCommand("StopSound");
        theButtonSoundStop.addActionListener(this);

        theButtonSoundStart2 = new JButton("音楽の再生2(効果音)");//ボタンを作成，テキストの表示
        c.add(theButtonSoundStart2);//ペインに貼り付ける
        theButtonSoundStart2.setBounds(45,430,150,25);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButtonSoundStart2.setActionCommand("PlaySound2");
        theButtonSoundStart2.addActionListener(this);

        //色付きラベルの作成
        JLabel theLabel1 = new JLabel("青色文字のラベル");
        c.add(theLabel1);
        theLabel1.setBounds(275,25,140,25);
        theLabel1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theLabel1.setForeground(Color.BLUE); //文字色の設定．Colorの設定は，このページを見て下さい　http://www.javadrive.jp/tutorial/color/

        //背景色付きラベルの作成
        JLabel theLabel2 = new JLabel("白色文字/黒背景ラベル");
        c.add(theLabel2);
        theLabel2.setBounds(275,50,140,25);
        theLabel2.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theLabel2.setForeground(Color.WHITE); //文字色の設定．Colorの設定は，このページを見て下さい　http://www.javadrive.jp/tutorial/color/
        theLabel2.setBackground(Color.BLACK); //文字の背景色の設定．
        theLabel2.setOpaque(true);//ラベルを不透明にしないと背景色が見えないので，不透明にするする

        //画像付きラベルの作成
        ImageIcon icon2 = new ImageIcon("images.png");//なにか画像ファイルをダウンロードしておく
        JLabel theLabel3 = new JLabel(icon2);
        c.add(theLabel3);
        theLabel3.setBounds(275,90,208,242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theLabel3.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theLabel3.setForeground(Color.WHITE); //文字色の設定．Colorの設定は，このページを見て下さい　http://www.javadrive.jp/tutorial/color/

        //画像付きラベルの作成
        ImageIcon icon3 = new ImageIcon("images.png");//なにか画像ファイルをダウンロードしておく
        JLabel theLabel4 = new JLabel(icon3);
        c.add(theLabel4);
        theLabel4.setBounds(275,150,208,242);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theLabel4.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        theLabel4.setForeground(Color.WHITE); //文字色の設定．Colorの設定は，このページを見て下さい　http://www.javadrive.jp/tutorial/color/
        
        //テキストフィールドの作成
        JTextField theTextField = new JTextField("テキストフィールド");
        c.add(theTextField);
        theTextField.setBounds(500,25,140,25);
        theTextField.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //テキストエリアの作成
        JTextArea theTextArea = new JTextArea("テキストエリア\n複数行入るの特徴");
        c.add(theTextArea);
        theTextArea.setBounds(500,50,140,80);
        theTextArea.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        
        //スクロールボックスのあるテキストエリア
        JTextArea theTextArea2 = new JTextArea("スクロール付きのテキストエリア\n複数行入るの特徴\n1\n2\n3\n4\n5");
        // JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS を取るとスクロールバーは必要なときに出てきます
        JScrollPane theScrollPane = new JScrollPane(theTextArea2, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        c.add(theScrollPane);
        theScrollPane.setBounds(650,50,140,80);
        theScrollPane.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする   

        //チェックボックスの作成
        JPanel theCheckPanel = new JPanel();
        c.add(theCheckPanel);
        JCheckBox theCheck1 = new JCheckBox("White");
        JCheckBox theCheck2 = new JCheckBox("Red");
        JCheckBox theCheck3 = new JCheckBox("Green");
        JCheckBox theCheck4 = new JCheckBox("Blue");
        theCheckPanel.add(theCheck1);//ペインに貼り付ける
        theCheckPanel.add(theCheck2);//ペインに貼り付ける
        theCheckPanel.add(theCheck3);//ペインに貼り付ける
        theCheckPanel.add(theCheck4);//ペインに貼り付ける
        theCheckPanel.setBounds(500,250,300,35);
        theCheckPanel.setBackground(Color.CYAN);
        theCheck1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //ラジオボタンの作成
        JPanel theRadioPanel = new JPanel();

        c.add(theRadioPanel);
        JRadioButton theRadio1 = new JRadioButton("White");
        JRadioButton theRadio2 = new JRadioButton("Red");
        JRadioButton theRadio3 = new JRadioButton("Green");
        JRadioButton theRadio4 = new JRadioButton("Blue");
        theRadioPanel.add(theRadio1);//ペインに貼り付ける
        theRadioPanel.add(theRadio2);//ペインに貼り付ける
        theRadioPanel.add(theRadio3);//ペインに貼り付ける
        theRadioPanel.add(theRadio4);//ペインに貼り付ける
        theRadioPanel.setBounds(500,300,300,35);
        theRadioPanel.setBackground(Color.green);

        ButtonGroup theRaidoGroup = new ButtonGroup();//グループを作り排他的に動く
        theRaidoGroup.add(theRadio1);
        theRaidoGroup.add(theRadio2);
        theRaidoGroup.add(theRadio3);
        theRaidoGroup.add(theRadio4);

        theRadio1.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする

        //リストボックスの作成と配置
        Choice theChoice = new Choice();//リストボックスの作成
        theChoice.add("白");//リスト項目の追加
        theChoice.add("赤");//リスト項目の追加
        theChoice.add("緑");//リスト項目の追加
        theChoice.add("青");//リスト項目の追加
        c.add(theChoice);//ペインに貼り付ける
        theChoice.setBounds(500,150,140,20);

        //メニューバーの作成
        JMenuBar theMenuBar = new JMenuBar();

        //メニューの作成
        JMenu theMenu = new JMenu("色の選択");
        theMenuBar.add(theMenu);//メニューバーに追加

        //メニュー項目の作成
        JMenuItem mi1 = new JMenuItem("White");
        JMenuItem mi2 = new JMenuItem("Red");
        JMenuItem mi3 = new JMenuItem("Green");
        JMenuItem mi4 = new JMenuItem("Blue");

        //メニュー項目のメニューへの追加
        theMenu.add(mi1);
        theMenu.add(mi2);
        theMenu.add(mi3);
        theMenu.add(mi4);

        //メニュー項目をaddActionListenerリスナーへの追加
        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);

        //メニューバーを配置する
        this.setJMenuBar(theMenuBar);//メニューバーの配置はフレームへ

        //スクロールバー
        theScrollBar = new JScrollBar(JScrollBar.HORIZONTAL, 30, 5, 0, 105);
        theScrollBar.setPreferredSize(new Dimension(150, 17));
        c.add(theScrollBar);
        theScrollBar.setBounds(500,350,300,20);
        theScrollBar.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        //スクロールバーの値を表示するおテキストフィールド
        theTextFieldForScrollBar = new JTextField(Integer.toString(theScrollBar.getValue()));
        c.add(theTextFieldForScrollBar);
        theTextFieldForScrollBar.setBounds(820,350,40,20);
        theTextFieldForScrollBar.addActionListener(this);//アクション（テキストフィールドの場合はEnter押したとき発生）, ActionListenerをimplementsする必要があります
        theTextFieldForScrollBar.setActionCommand("ScrollBarChange");

        //プログレスバー
        theProgressBar = new JProgressBar(1, 100);
        theProgressBar.setValue(50);
        c.add(theProgressBar);
        theProgressBar.setBounds(500,400,300,10);
        theProgressBar.addMouseListener(this);//ボタンをマウスでさわったときに反応するようにする
        //Startボタン
        theButtonStart = new JButton("Start");//ボタンを作成，テキストの表示
        c.add(theButtonStart);//ペインに貼り付ける
        theButtonStart.setBounds(840,400,70,20);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButtonStart.addActionListener(this);//アクション（ボタンを押したとき発生）．その他のマウスの動きには反応しない），ActionListenerをimplementsする必要があります．addMouseListenerの場合には，mouseClickedに書く．
        theButtonStart.setActionCommand("ProgressBarStart");//アクションコマンドを設定
        //Stopボタン
        theButtonStop = new JButton("Stop");//ボタンを作成，テキストの表示
        c.add(theButtonStop);//ペインに貼り付ける
        theButtonStop.setBounds(920,400,70,20);//ボタンの大きさと位置を設定する．(x座標，y座標,xの幅,yの幅）
        theButtonStop.addActionListener(this);//アクション（ボタンを押したとき発生）．その他のマウスの動きには反応しない），ActionListenerをimplementsする必要があります．addMouseListenerの場合には，mouseClickedに書く．
        theButtonStop.setActionCommand("ProgressBarStop");//アクションコマンドを設定
        //プログレスバーの値を表示するラベル
        theLabelForProgressBar = new JLabel(Integer.toString(theProgressBar.getValue()));
        c.add(theLabelForProgressBar);
        theLabelForProgressBar.setBounds(820,390,40,20);

    }

    public static void main(String[] args) {
        GUISample gui = new GUISample();
        gui.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("マウスをクリックした");
        Object theObj = e.getComponent();
        System.out.println(theObj);
        System.out.println("クラス名＝" + theObj.getClass().getName());
        String theClass = theObj.getClass().getName();//クラス名を使って動きを変える

        //JButtonクラスの時の動き
        if(theClass.equals("javax.swing.JButton")){
            System.out.println("ボタンをクリック");
            //オブジェクトで区別する例
            String theCmd = ((JButton)theObj).getActionCommand();//theObjを一度JButtonにキャストしてから，getActionCommandを呼んでいます．theObjは，型がObjectなので，JButtonにしないと，getActionCommandが呼べないため
            if(theCmd.equals("0")){
                theLabelA.setText("Click Button "+theCmd);
            }

            //ボタンに付けたテキストで区別する例
            String theText = ((JButton)theObj).getText();
            if(theText.equals("Button 1")){
                theLabelA.setText("Click "+theText);
            }

            //ボタンに付けられたイメージ（ICON）で区別する例
            Icon theIcon = ((JButton)theObj).getIcon();
            if(theIcon == icon1){
                theLabelA.setText("Click "+theIcon.toString());
            }
        }

        //JLabelの時の動き
        if(theClass.equals("javax.swing.JLabel")){
            System.out.println("ラベルをクリック");
            //ラベルは区別方法があまりありませんのでが，オブジェクト同士で比較して，区別は可能です．
            if(e.getSource()==theLabelA){
                theLabelA.setText("Click LabelA");
            }else{
                theLabelA.setText("Click LabelA以外");
            }
        }
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("マウスを押した");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("マウスを放した");

        Object theObj = e.getComponent();
        String theClass = theObj.getClass().getName();//クラス名を使って動きを変える

        //Scrollバーの動き
        if(theClass.equals("javax.swing.JScrollBar")){
            //本当なら，数字以外が入った場合の対処（Try-catchを書くべき）
            //スクロールバーの現在の値を表示
            JScrollBar tempScrollBar = (JScrollBar)e.getComponent();
            theTextFieldForScrollBar.setText(Integer.toString(tempScrollBar.getValue()));
        }
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
        Object theObj = e.getSource();
        System.out.println("クラス名＝" + theObj.getClass().getName());
        String theClass = theObj.getClass().getName();//クラス名を使って動きを変える

        //ActionCommandの内容で動きを制御する
        if(theCmd.equals("ScrollBarChange")){
            //Scrollバーの位置を数字に合わせる
            JTextField tempTextField = (JTextField)e.getSource();
            int theValue = Integer.parseInt(tempTextField.getText());
            theScrollBar.setValue(theValue);
        }
        //プログレスバーの開始処理，setActionCommandで，"ProgressBarStart""を設定している
        if(theCmd.equals("ProgressBarStart")){
            theLabelA.setText("ProgressBarStart");

            timer = new Timer(100 , this);
            timer.setActionCommand("timer");
            theProgressBar.setValue(0);
            theLabelForProgressBar.setText("0");
            theButtonStart.setEnabled(false);//Startボタンが触れないようにする
            timer.start();
        }
        //プログレスバーの終了処理，setActionCommandで，"ProgressBarStop"を設定している
        if(theCmd.equals("ProgressBarStop")){
            theLabelA.setText("ProgressBarStop");
            timer.stop();
            theButtonStart.setEnabled(true);//Startボタンが触れるようにする
        }
        //音楽再生
        if(theCmd.equals("PlaySound1")){
            theSoundPlayer1 = new SoundPlayer("5425.wav");
            theSoundPlayer1.SetLoop(true);//ＢＧＭとして再生を繰り返す
            theSoundPlayer1.play();
        }
        if(theCmd.equals("PlaySound2")){
            theSoundPlayer2 = new SoundPlayer("443_2.wav");
            theSoundPlayer2.play();
        }
        //音楽停止
        if(theCmd.equals("StopSound")){
            theSoundPlayer1.stop();
        }
        //Timerの処理
        if(theCmd.equals("timer")){
            theLabelA.setText("Timer");
            int count = theProgressBar.getValue();
            count = count + 1;
            theProgressBar.setValue(count);
            theLabelForProgressBar.setText(Integer.toString(count));
            if(count>=100){//タイマーをとめる
                timer.stop();
            }
        }

        //メニューの選択に対応した動き
        if(theClass.equals("javax.swing.JMenuItem")){
            if(theCmd.equals("White")){
                theButton1.setForeground(Color.WHITE);
            }else if(theCmd.equals("Red")){
                theButton1.setForeground(Color.RED);
            }else if(theCmd.equals("Green")){
                theButton1.setForeground(Color.GREEN);
            }else if(theCmd.equals("Blue")){
                theButton1.setForeground(Color.BLUE);
            }
        }
    }

    //下記を利用しています．一部書き換えています(停止機能を付加)    ．
    //http://nautilus2580.hatenablog.com/entry/2015/11/07/223457
    //mp3をwavに変換は，下記のサイトでできます
    //http://online-audio-converter.com/ja/
    public class SoundPlayer{
        private AudioFormat format = null;
        private DataLine.Info info = null;
        private Clip clip = null;
        boolean stopFlag = false;
        Thread soundThread = null;
        private boolean loopFlag = false;

        public SoundPlayer(String pathname){
            File file = new File(pathname);
            try{
                format = AudioSystem.getAudioFileFormat(file).getFormat();
                info = new DataLine.Info(Clip.class, format);
                clip = (Clip) AudioSystem.getLine(info);
                clip.open(AudioSystem.getAudioInputStream(file));
                //clip.setLoopPoints(0,clip.getFrameLength());//無限ループとなる
            }catch(Exception e){
                e.printStackTrace();
            }
        }

        public void SetLoop(boolean flag){
            loopFlag = flag;
        }

        public void play(){
            soundThread = new Thread(){
                public void run(){
                    long time = (long)clip.getFrameLength();//44100で割ると再生時間（秒）がでる
                    System.out.println("PlaySound time="+time);
                    long endTime = System.currentTimeMillis()+time*1000/44100;
                    clip.start();
                    System.out.println("PlaySound time="+(int)(time/44100));
                    while(true){
                        if(stopFlag){//stopFlagがtrueになった終了
                            System.out.println("PlaySound stop by stopFlag");
                            clip.stop();
                            return;
                        }
                        System.out.println("endTime="+endTime);
                        System.out.println("currentTimeMillis="+System.currentTimeMillis());
                        if(endTime < System.currentTimeMillis()){//曲の長さを過ぎたら終了
                            System.out.println("PlaySound stop by sound length");
                            if(loopFlag) {
                                clip.loop(1);//無限ループとなる
                            } else {
                                clip.stop();
                                return;
                            }
                        }
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            soundThread.start();
        }

        public void stop(){
            stopFlag = true;
            System.out.println("StopSound");
        }

    }
}
