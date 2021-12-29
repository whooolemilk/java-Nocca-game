import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// 音楽再生時に必要
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import java.lang.Math;

// import java.awt.Point;
// import java.awt.Color;
// import java.awt.BorderLayout;


public class MyClient extends JFrame implements MouseListener,MouseMotionListener {
	private JButton buttonArray[][];
  private JButton passButton;
  private JButton resetButton;
  private int myColor;
  private int myTurn;
  private ImageIcon myIcon, yourIcon;
	// private Container c;
  private JLayeredPane c;

	// private ImageIcon blackIcon, whiteIcon, boardIcon;
  private ImageIcon redIcon, blueIcon, orangeIcon, catIcon, bgImg;
	PrintWriter out;
  JLabel theLabel1;
  JLabel theLabel2;
  JLabel theLabel3;
  JLabel bg;
  private JButton guideArray[][];
  private JButton myArray[];
  private JButton yourArray[];
  //JLayeredPane layerPane;

  SoundPlayer theSoundPlayer2;

  int boardIconCount = 0;
  int whiteIconCount = 0;
  int blackIconCount = 0;

  // static final int BACKLAYER = 1;
  // static final int FORELAYER = 2;
  // BGImageLayeredPane layerPane;  

	public MyClient() {
		// 名前の入力ダイアログを開く
		String myName = JOptionPane.showInputDialog(null,"名前を入力してください","名前の入力",JOptionPane.QUESTION_MESSAGE);
		if(myName.equals("")){
			myName = "No name";// 名前がないときは，"No name"とする
		}

		String IPAddress = JOptionPane.showInputDialog(null,"IPアドレスを入力してください","IPアドレスの入力",JOptionPane.QUESTION_MESSAGE);
		if(IPAddress.equals("")){
			IPAddress = "localhost";// 名前がないときは，"localhoat"とする
		}

		// ウィンドウを作成する
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ウィンドウを閉じるときに，正しく閉じるように設定する
		setTitle("MyClient");// ウィンドウのタイトルを設定する
		setSize(1200, 850);// ウィンドウのサイズを設定する
		// c = getContentPane();// フレームのペインを取得する
    c = new JLayeredPane(); 
    this.getContentPane().add(c);
    c.setBackground(Color.RED);// ウィンドウの色の設定

    bgImg = new ImageIcon("img/board1.png");
    bg = new JLabel(bgImg);
    c.add(bg);
    bg.setBounds(250, 50,700,700);
    c.setLayer(bg,0);
    //guideArray[j][i].setContentAreaFilled(false);
    //bg.setBorderPainted(false);


		// アイコンの設定
		// whiteIcon = new ImageIcon("White1.jpg");
		// blackIcon = new ImageIcon("Black1.jpg");
		// boardIcon = new ImageIcon("GreenFrame1.jpg");
    myIcon = new ImageIcon("img/zibun1.png");
    yourIcon = new ImageIcon("img/aite1.png");
    orangeIcon = new ImageIcon("img/masu1.png");
    catIcon = new ImageIcon("img/cat21.png");

		c.setLayout(null);// 自動レイアウトの設定を行わない

		// // パスボタンの生成
    // passButton = new JButton();
    // c.add(passButton);
    // passButton.setText("パス");
    // passButton.setBounds(500,350,100,50);
    // passButton.addMouseListener(this);
    // passButton.setActionCommand("PASS");

    // // リセットボタンの生成
    // resetButton = new JButton();
    // c.add(resetButton);
    // resetButton.setText("リセット");
    // resetButton.setBounds(500,400,100,50);
    // resetButton.addMouseListener(this);
    // resetButton.setActionCommand("RESET");

    // // オセロボタンの生成
		// buttonArray = new JButton[5][5];
		// for(int j=0;j<5;j++){
    //   for(int i=0; i<5; i++){
		// 	buttonArray[j][i] = new JButton(Integer.toString(i));
		// 	c.add(buttonArray[j][i]);
		// 	buttonArray[j][i].setBounds(i*100+50,j*100+50,100,100);
		// 	buttonArray[j][i].addMouseListener(this);
		// 	buttonArray[j][i].setActionCommand(Integer.toString(j*5+i));
    //   }
		// }

    // controlLabel = new JLabel();
    // c.add(controlLabel);
    // controlLabel.addMouseListener(this);
    // controlLabel.setBackground(Color.BLUE);

    // yourArray = new  JButton[5];
    // for(int i=0; i<5; i++){
    //   yourArray[i] = new JButton(blueIcon);
    //   c.add(yourArray[i]);
    //   yourArray[i].setBounds(i*100+600-80*(i+1)+60*i+80, i*80+20, 70, 70);
    //   yourArray[i].setContentAreaFilled(false);
    //   yourArray[i].setBorderPainted(false);
    //   yourArray[i].addMouseListener(this);
    //   // layerPane.add(yourArray[i]);
		// 	// layerPane.setLayer(yourArray[i] , i);
    // }

    // myArray = new  JButton[5];
    // for(int i=0; i<5; i++){
    //   myArray[i] = new JButton(redIcon);
    //   c.add(myArray[i]);
    //   myArray[i].setBounds(i*100+115-80*(i+1)+60*i+80, i*80+500, 70, 70);
    //   myArray[i].setContentAreaFilled(false);
    //   myArray[i].setBorderPainted(false);
    //   myArray[i].addMouseListener(this);
    //   // layerPane.add(myArray[i]);
		// 	// layerPane.setLayer(myArray[i], i);
    // }
    
    // // ガイドの生成
		// guideArray = new JButton[9][5];
    // boolean f = false;
		// for(int j=0;j<9;j++){
    //   if(j <5){
    //     int n = j + 1;
    //     System.out.println(n);
    //     for(int i=0; i<n; i++){
    //       guideArray[j][i] = new JButton(orangeIcon);
    //       c.add(guideArray[j][i]);
    //       guideArray[j][i].setBounds(i*100+600-80*n+60*i,j*80+100,70,70);
    //       guideArray[j][i].setContentAreaFilled(false);
    //       guideArray[j][i].setBorderPainted(false);
    //       guideArray[j][i].addMouseListener(this);
    //       guideArray[j][i].setActionCommand(Integer.toString(5*(j+1)-4-4*i-1));
    //     }
    //   }
    //   else{
    //     int n = -j + 9;
    //     for(int i=0; i<n; i++){
    //       guideArray[j][i] = new JButton(orangeIcon);
    //       c.add(guideArray[j][i]);
    //       guideArray[j][i].setBounds(i*100+600-80*n+60*i,j*80+100,100,100);
    //       guideArray[j][i].setContentAreaFilled(false);
    //       guideArray[j][i].setBorderPainted(false);
    //       guideArray[j][i].addMouseListener(this);
    //       guideArray[j][i].setActionCommand(Integer.toString(j+17-4*i-1));
    //     }
    //   }
		// }

    // ガイドの生成ver2
		guideArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        guideArray[j][i] = new JButton(orangeIcon);
        c.add(guideArray[j][i]);
        guideArray[j][i].setBounds(i*70+530-70*j,j*70+5+70*i,140,140);
        guideArray[j][i].addMouseListener(this);
        c.setLayer(guideArray[j][i],j*5+i+1);
        guideArray[j][i].setActionCommand(Integer.toString(j*5+i));
        guideArray[j][i].setContentAreaFilled(false);
        guideArray[j][i].setBorderPainted(false);
      }
		}

    // boolean f = false;
		// for(int j=0;j<9;j++){
    //   if(j <5){
    //     int n = j + 1;
    //     System.out.println(n);
    //     for(int i=0; i<n; i++){
    //       guideArray[j][i] = new JButton(orangeIcon);
    //       c.add(guideArray[j][i]);
    //       guideArray[j][i].setBounds(i*100+600-80*n+60*i,j*80+100,70,70);
    //       guideArray[j][i].setContentAreaFilled(false);
    //       guideArray[j][i].setBorderPainted(false);
    //       guideArray[j][i].addMouseListener(this);
    //       guideArray[j][i].setActionCommand(Integer.toString(5*(j+1)-4-4*i-1));
    //     }
    //   }
    //   else{
    //     int n = -j + 9;
    //     for(int i=0; i<n; i++){
    //       guideArray[j][i] = new JButton(orangeIcon);
    //       c.add(guideArray[j][i]);
    //       guideArray[j][i].setBounds(i*100+600-80*n+60*i,j*80+100,100,100);
    //       guideArray[j][i].setContentAreaFilled(false);
    //       guideArray[j][i].setBorderPainted(false);
    //       guideArray[j][i].addMouseListener(this);
    //       guideArray[j][i].setActionCommand(Integer.toString(j+17-4*i-1));
    //     }
    //   }
		// }

  


    //frame.getContentPane().add(layerPane);


    // オセロの最初の４マス設定
    // buttonArray[3][3].setIcon(whiteIcon);
    // buttonArray[3][4].setIcon(blackIcon);
    // buttonArray[4][3].setIcon(blackIcon);
    // buttonArray[4][4].setIcon(whiteIcon);

    theLabel1 = new JLabel("白の数");
    c.add(theLabel1);
    theLabel1.setBounds(500,150,100,50);
    theLabel1.setBackground(Color.WHITE);
    theLabel1.setForeground(Color.BLACK);

    theLabel3 = new JLabel("黒の数");
    c.add(theLabel3);
    theLabel3.setBounds(500,100,100,50);
    theLabel3.setBackground(Color.WHITE);
    theLabel3.setForeground(Color.BLACK);  

    theLabel2 = new JLabel(" ");
    c.add(theLabel2);
    theLabel2.setBounds(500,200,100,100);
    theLabel2.setBackground(Color.WHITE);
    theLabel2.setForeground(Color.BLACK); 

    // サーバに接続する
		Socket socket = null;
		try {
		  // "localhost"は，自分内部への接続．localhostを接続先のIP Address（"133.42.155.201"形式）に設定すると他のPCのサーバと通信できる
			// 10000はポート番号．IP Addressで接続するPCを決めて，ポート番号でそのPC上動作するプログラムを特定する
			socket = new Socket(IPAddress, 10000);
		} catch (UnknownHostException e) {
			System.err.println("ホストの IP アドレスが判定できません: " + e);
		} catch (IOException e) {
			System.err.println("エラーが発生しました: " + e);
		}

		MesgRecvThread mrt = new MesgRecvThread(socket, myName);//受信用のスレッドを作成する
		mrt.start();// スレッドを動かす（Runが動く）
	}
		
	// メッセージ受信のためのスレッド
	public class MesgRecvThread extends Thread {
		Socket socket;
		String myName;
		
		public MesgRecvThread(Socket s, String n){
			socket = s;
			myName = n;
		}
		
	  // 通信状況を監視し，受信データによって動作する
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(myName);//接続の最初に名前を送る
        String myNumberStr = br.readLine();
        int myNumberInt = Integer.parseInt(myNumberStr);

        // if(myNumberInt % 2 != 0){
        //   myColor=0;// player1:Black
        //   myTurn=1;// 先行
        //   myIcon=blackIcon;
        //   yourIcon=whiteIcon;
        // }else{
        //   myColor=1;// player2:White
        //   myTurn=0;// 後攻
        //   myIcon=whiteIcon;
        //   yourIcon=blackIcon;
        // }

        // if(myTurn==1){
        //   theLabel2.setText("<html>あなたの番から始まるよ！</html>");
        //   System.out.println("あなたの番から始まるよ！");
        // }else{
        //   theLabel2.setText("<html>相手の番から始まるよ！</html>");
        //   System.out.println("相手の番から始まるよ！");
        // }

				while(true) {
					String inputLine = br.readLine();
          System.out.println("inputline="+inputLine);
					if (inputLine != null) {
						String[] inputTokens = inputLine.split(" ");
						String cmd = inputTokens[0];

            // // パス機能
            // if(cmd.equals("PASS")){
            //   myTurn = 1 - myTurn;
            //   theLabel2.setText("<html>ボタンパス成功です！</html>");
            //   System.out.println("ボタンパス成功");
            //   if(myTurn==1){
            //       theLabel2.setText("<html>あなたの番です！</html>");
            //       System.out.println("あなたの番です");
            //     }else{
            //       theLabel2.setText("<html>相手の番です！</html>");
            //       System.out.println("相手の番です");
            //   }
            // }
            // // リセット機能
            // if(cmd.equals("RESET")){
            //   for(int j=0;j<8;j++){
            //     for(int i=0; i<8; i++){
            //     buttonArray[j][i].setIcon(boardIcon);
            //     }
            //   }
            //   buttonArray[3][3].setIcon(whiteIcon);
            //   buttonArray[3][4].setIcon(blackIcon);
            //   buttonArray[4][3].setIcon(blackIcon);
            //   buttonArray[4][4].setIcon(whiteIcon); 
            //   if(myTurn==1){
            //       System.out.println("あなたの番からはじまるよ");
            //     }else{
            //       System.out.println("相手の番からはじまるよ！");
            //   }
            //   System.out.println("リセット成功");
            // }
         
            // // ひっくり返す機能
            // if(cmd.equals("PLACE")){
            //   String theBName = inputTokens[1];
            //   int theBnum = Integer.parseInt(theBName);
            //   int i = theBnum / 8;
            //   int j = theBnum % 8;
            //   int theColor = Integer.parseInt(inputTokens[2]);
              
            //   if(theColor==myColor){
            //     buttonArray[i][j].setIcon(myIcon);
            //     theSoundPlayer2.play();
            //   }else{
            //     buttonArray[i][j].setIcon(yourIcon);
            //   }
            //   colorCount();
            //   theLabel1.setText("<html>白の数："+whiteIconCount+"</html>");
            //   theLabel3.setText("<html>黒の数："+blackIconCount+"</html>");

            //   if (passJudgeCount() != 0){
            //     myTurn = 1 - myTurn;
            //     if(myTurn==1){
            //       theLabel2.setText("<html>あなたの番です！</html>");
            //       System.out.println("あなたの番です");
            //     }else{
            //       theLabel2.setText("<html>相手の番です！</html>");
            //       System.out.println("相手の番です");
            //     }
            //   }else{
            //     theLabel2.setText("<html>自動パス発生！</html>");
            //     System.out.println("自動パス成功");
            //     if(endJudgeCount() == 0){
            //       System.out.println("終了");
            //       colorCount();
            //       theLabel1.setText("<html>白の数："+whiteIconCount+"</html>");
            //       theLabel3.setText("<html>黒の数："+blackIconCount+"</html>");
            //       System.out.println("白の数"+whiteIconCount);
            //       System.out.println("黒の数"+blackIconCount);
            //       winJudge();// 勝敗判定を行い、テキスト表示
            //     }
            //   }
            // }

            // // FLIP機能
            // if(cmd.equals("FLIP")){
            //   String theBName = inputTokens[1];//ボタンの名前（番号）の取得
            //   int theBnum = Integer.parseInt(theBName);//ボタンの名前を数値に変換する
            //   int i = theBnum / 8;
            //   int j = theBnum % 8;
            //   int theColor = Integer.parseInt(inputTokens[2]);//数値に変換する
            //   if(theColor==myColor){
            //     buttonArray[i][j].setIcon(myIcon);//blackIconに設定する
            //   }else{
            //     buttonArray[i][j].setIcon(yourIcon);
            //   }
            // }
					}else{
						break;
					}
				}
				socket.close();
			} catch (IOException e) {
				System.err.println("エラーが発生しました: " + e);
			}
		}
	}


	public static void main(String[] args) {
		MyClient net = new MyClient();
		net.setVisible(true);
	}
  	
	public void mouseClicked(MouseEvent e) {
    System.out.println("クリック");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    System.out.println("theArrayIndex="+temp);
    
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    guideArray[y][x].setIcon(catIcon);

    //label.setText("x:" + point.x + ",y:" + point.y);
    // if(theArrayIndex.equals("RESET")){
    //   String msg = "RESET";
    //   out.println(msg);
    //   out.flush();
    // }     
    // if(myTurn==1){
    //   if(theArrayIndex.equals("PASS")){
    //     String msg = "PASS";
    //     out.println(msg);
    //     out.flush();
    //   }     

    if(theIcon == orangeIcon){
      //int temp = Integer.parseInt(theArrayIndex);
      //System.out.println("theArrayIndex="+temp);
      // int x = temp % 8;
      // int y = temp / 8;

      //System.out.println("judgeButton="+judgeButton(y, x));
      // if(judgeButton(temp)){
      //   //置ける
      //   System.out.println("実行");
      //   theSoundPlayer2 = new SoundPlayer("443_2.wav");
      //   String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
      //   out.println(msg);
      //   out.flush();
      // } else {
      //   //置けない
      //   System.out.println("そこには配置できません");
      // }
    }
  //   repaint();//画面のオブジェクトを描画し直す
  // }
	}
	
	public void mouseEntered(MouseEvent e) {
    // System.out.println("ホバー");
    // JButton theButton = (JButton)e.getComponent();
    // String theArrayIndex = theButton.getActionCommand();
    // Icon theIcon = theButton.getIcon();

    // int temp = Integer.parseInt(theArrayIndex);
    // int x = temp % 5;
    // int y = temp / 5;

    // System.out.println("x="+x);
    // System.out.println("y="+y);

    // guideArray[y][x].setIcon(catIcon);
    // c.setLayer(guideArray[y][x],1000);

	}
	
	public void mouseExited(MouseEvent e) {
    // System.out.println("ホバーおわり");
    // JButton theButton = (JButton)e.getComponent();
    // String theArrayIndex = theButton.getActionCommand();
    // Icon theIcon = theButton.getIcon();

    // int temp = Integer.parseInt(theArrayIndex);
    // int x = temp % 5;
    // int y = temp / 5;

    // System.out.println("x="+x);
    // System.out.println("y="+y);

    // guideArray[y][x].setIcon(orangeIcon);
    // c.setLayer(guideArray[y][x],y*5+x+1);
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

  // 置ける盤面かどうかを判定する関数
  // public boolean judgeButton(int temp) {
  //   boolean flag = false;

  //   int x = temp % 5;
  //   int y = temp / 5;
    
  //   for (int j=-1;j<2;j++){
  //     for (int i=-1;i<2;i++){
  //       int posX = x + i;
  //       int posY = y + j;

  //       if(isExceededArea(posX, posY)){
  //         continue;
  //       }

  //       Icon theIcon = buttonArray[posY][posX].getIcon();
  //       int flipNum = flipButtons(y, x, j, i, true);        

  //       if(flipNum >= 1){
  //         flag=true;
  //         for(int dy=j, dx=i, k=0; k<flipNum; k++, dy+=j, dx+=i){
  //           //ボタンの位置情報を作る
  //           int msgy = y + dy;
  //           int msgx = x + dx;
  //           int theArrayIndex = msgy*8 + msgx;
  //           //サーバに情報を送る
  //           String msg = "FLIP"+" "+theArrayIndex+" "+myColor;
  //           out.println(msg);
  //           out.flush();
  //         }
  //       }        
  //     }
  //   }
  //   return flag;
  // }

  // ひっくり返すことのできる盤面の個数を返す関数
  // 引数sで数えるアイコンの種類を変更、trueならyourIcon、falseならmyIconを数える
  // public int flipButtons(int y, int x, int j, int i, boolean s){
  //   int flipNum = 0;
  //   if ( (j==0) && (i==0) ) return 0;
  //   for(int dy=j, dx=i; ; dy+=j, dx+=i) {
  //     int posY = y + dy;
  //     int posX = x + dx;

  //     if(isExceededArea(posY, posX)){
  //       return 0;
  //     }

  //     Icon theIcon = buttonArray[posY][posX].getIcon();

  //     if(s){
  //       if(theIcon == boardIcon){
  //         flipNum = 0;
  //         break;
  //       }else if(theIcon == myIcon){
  //         break;
  //       }else if (theIcon == yourIcon){
  //         flipNum++;
  //       }
  //     }else{
  //       if(theIcon == boardIcon){
  //         flipNum = 0;
  //         break;
  //       }else if(theIcon == yourIcon){
  //         break;
  //       }else if (theIcon == myIcon){
  //         flipNum++;
  //       }
  //     }
  //   }
  //   return flipNum;
  // }

  // 座標が8×8のマス目を超えているかどうかを判定する関数
  // public boolean isExceededArea(int posX, int posY){
  //   return posX < 0 || posY < 0 || posX > 5 || posY > 5;
  // }

  // // 黒、白、ボード、それぞれの数を数える関数
  // public void colorCount(){
  //   boardIconCount = 0;
  //   whiteIconCount = 0;
  //   blackIconCount = 0;
  //   for(int ia = 0; ia <8; ia++){
  //     for(int ja = 0; ja < 8; ja++){
  //       Icon theIcon = buttonArray[ia][ja].getIcon();
  //       if(theIcon == boardIcon){
  //         boardIconCount ++;
  //       }else if(theIcon == whiteIcon){
  //         whiteIconCount ++;
  //       }else if(theIcon == blackIcon){
  //         blackIconCount ++;
  //       }
  //     }
  //   }
  // }

  // // 勝敗判定を行い、テキスト表示する関数
  // public void winJudge(){
  //   if(whiteIconCount==blackIconCount){
  //     theLabel2.setText("<html>ひきわけだよ！</html>");
  //     System.out.println("ひきわけ");
  //   }else if(whiteIconCount>blackIconCount){
  //     if(myIcon == whiteIcon){
  //       theLabel2.setText("<html>勝ち！おめでとう！</html>");
  //       System.out.println("勝ち");
  //     }else{
  //       theLabel2.setText("<html>負け！残念！</html>");
  //       System.out.println("負け");
  //     }
  //   }else{
  //     if(myIcon == blackIcon){
  //       theLabel2.setText("<html>勝ち！おめでとう！</html>");
  //       System.out.println("勝ち");
  //     }else{
  //       theLabel2.setText("<html>負け！残念！</html>");
  //       System.out.println("負け");
  //     }
  //   }
  // }


  // // パスを判定に使う関数
  // public boolean passJudge(int y, int x, boolean s) {
  //   boolean flag = false;
  //   int flipNum = 0;
  //   for (int j=-1; j<=1; j++){
  //     for (int i=-1; i<=1; i++){
  //       if ( (j==0) && (i==0) ) continue;
  //       if(s){
  //         if(myTurn==1){
  //           flipNum = flipButtons(y, x, j, i, false);
  //         }else{
  //           flipNum = flipButtons(y, x, j, i, true);
  //         }
  //       }else{
  //         if(myTurn==1){
  //           flipNum = flipButtons(y, x, j, i, true);
  //         }else{
  //           flipNum = flipButtons(y, x, j, i, false);
  //         }
  //       }
  //       if (flipNum >= 1){
  //         flag = true;
  //         break;
  //       }
  //     }
  //   }
  //   return flag;
  // }

  // // ゲームの終了を判定するためのカウント関数
  // // 0ならゲーム終了
  // public int endJudgeCount(){
  //   int count = 0;
  //   for (int j = 0; j < 8; j++) {
  //     for (int i = 0; i < 8; i++) {
  //       Icon theIcon = buttonArray[j][i].getIcon();
  //       if((theIcon == boardIcon) && passJudge(j, i, false)) {
  //         count++;
  //       }
  //     }
  //   }
  //   System.out.println("judgeCount="+count);
  //   return count;
  // }

  // // パスできるか判定するためのカウント関数
  // // ０ならぱす、それ以外なら次の人に
  // public int passJudgeCount(){
  //   int count = 0;
  //   for (int j = 0; j < 8; j++) {
  //       for (int i = 0; i < 8; i++) {
  //         Icon theIcon = buttonArray[j][i].getIcon();
  //         if((theIcon == boardIcon) && passJudge(j, i, true)) {
  //               count++;
  //           }
  //       }
  //   }
  //   return count;
  // }
  
  // 音楽再生
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
          long endTime = System.currentTimeMillis()+time*1000/44100;
          clip.start();
          while(true){
            if(stopFlag){//stopFlagがtrueになった終了
              clip.stop();
              return;
            }
            if(endTime < System.currentTimeMillis()){//曲の長さを過ぎたら終了
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
    }
  }
}