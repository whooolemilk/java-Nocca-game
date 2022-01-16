import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// 音楽再生
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

// 画面遷移
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class MyClient extends JFrame implements MouseListener,MouseMotionListener, ActionListener {
  // フィールド宣言
  private static JButton boardArray[][];
  JButton winButtonArray[];// ボード5*5
  private JButton startButton, howToPlayButton, myAreaButton, yourAreaButton;
  private int myColor, myNumber, myTurn;
  private ImageIcon myIcon, yourIcon, character1Img, character2Img, character3Img;
  private ImageIcon mydoubleIcon, yourdoubleIcon;
  private ImageIcon s_myIcon, s_yourIcon, s_doubleIcon, s_mydoubleIcon, s_yourdoubleIcon; // 選択状態アイコン
  private ImageIcon g_myIcon, g_yourIcon;
  private JLayeredPane c, scene3;
  private JLabel bg;
  private JPanel scene1, scene2;
  private boolean mySelect, winFlag;
  private static ImageIcon redIcon, blueIcon, orangeIcon;
  private ImageIcon catIcon, rbIcon, brIcon;
  private ImageIcon s_rbIcon, s_brIcon, s_redIcon, s_blueIcon, s_orangeIcon, s_catIcon;
  private ImageIcon bgImg, guideIcon;
  private ImageIcon g_redIcon, g_blueIcon;
	PrintWriter out;
  SoundPlayer theSoundPlayer2;
  
  static JPanel scenePanel;
  static CardLayout layout;

  // コンストラクタ
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
		setSize(1024, 786);// ウィンドウのサイズを設定する
  
		//c = getContentPane();// フレームのペインを取得する
    // c = new JLayeredPane();//layerフレームのペインを作成
    // this.getContentPane().add(c);

    // boardの設定
    // bgImg = new ImageIcon("img/board1.png");
    // bg = new JLabel(bgImg);
    // c.add(bg);
    // bg.setBounds(250, 50,700,700);
    // c.setLayer(bg,1000);


		// アイコンの設定
    redIcon = new ImageIcon("img/zibun1.png");
    blueIcon = new ImageIcon("img/aite1.png");
    orangeIcon = new ImageIcon("img/masu1.png");
    guideIcon = new ImageIcon("img/gaido1.png");
    catIcon = new ImageIcon("img/cat21.png");
    rbIcon = new ImageIcon("img/akaue1.png");
    brIcon = new ImageIcon("img/aoue1.png");
    bgImg = new ImageIcon("img/board1.png");

    character1Img = new ImageIcon("img/character1.png");
    character2Img = new ImageIcon("img/character2.png");
    character3Img = new ImageIcon("img/character3.png");


    s_redIcon = new ImageIcon("img/s-zibun1.png");
    s_blueIcon = new ImageIcon("img/s-aite1.png");
    s_orangeIcon = new ImageIcon("img/s-masu1.png");
    s_catIcon = new ImageIcon("img/s-cat21.png");    
    s_rbIcon = new ImageIcon("img/s-akaue1.png");
    s_brIcon = new ImageIcon("img/s-aoue1.png");

    g_redIcon = new ImageIcon("img/g-zibun1.png");
    g_blueIcon = new ImageIcon("img/g-aite1.png");

		// c.setLayout(null);// 自動レイアウトの設定を行わない 

    /* -----------------------scene1------------------------- */
    scene1 = new JPanel();
    JLabel title = new JLabel("タイトル");
    scene1.add(title);
    scene1.setLayout(null);
    startButton = new JButton("はじめる");
    howToPlayButton = new JButton("あそびかた");
    scene1.add(startButton);
    scene1.add(howToPlayButton);
    startButton.addActionListener(this);
    startButton.setActionCommand("Start");

    title.setBounds(362,150, 300, 100);
    title.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 70));
    startButton.setBounds(362, 420, 300, 100);
    howToPlayButton.setBounds(362, 550, 300, 100);

    /* -----------------------scene2------------------------- */
    scene2 = new JPanel();
    scene2.setLayout(null);
    JButton c1Button = new JButton(character1Img);
    JButton c2Button = new JButton(character2Img);
    JButton c3Button = new JButton(character3Img);
    scene2.add(c1Button);
    scene2.add(c2Button);
    scene2.add(c3Button);

    // boardArray[j][i].setBounds(x,y,xs,ys);
    c1Button.addActionListener(this);
    c1Button.setActionCommand("character1");
    c2Button.addActionListener(this);
    c2Button.setActionCommand("character2");
    c3Button.addActionListener(this);
    c3Button.setActionCommand("character3");

    c1Button.setBounds(22, 200, 300, 300);
    c2Button.setBounds(362, 200, 300, 300);
    c3Button.setBounds(702, 200, 300, 300);



    /* -----------------------scene3------------------------- */
    scene3 = new JLayeredPane();
    scene3.add(new JLabel("label"));
    scene3.add(new JTextField("", 10));
    
    
    bg = new JLabel(bgImg);
    scene3.add(bg);
    bg.setBounds(250, 50,700,700);
    scene3.setLayer(bg,0);

    // ガイドの生成ver2
		boardArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        boardArray[j][i] = new JButton(orangeIcon);
        scene3.add(boardArray[j][i]);
        boardArray[j][i].setBounds(i*70+530-70*j,j*70+5+70*i,140,140);
        boardArray[j][i].addMouseListener(this);
        scene3.setLayer(boardArray[j][i], j*5+i+1);
        boardArray[j][i].setActionCommand(Integer.toString(j*5+i));
        boardArray[j][i].setContentAreaFilled(false);
        boardArray[j][i].setBorderPainted(false);
      }
		}

    winButtonArray = new JButton[2];
    for(int i=0; i<2; i++){
      String j = Integer.toString(i);
      winButtonArray[i] = new JButton(rbIcon);
      scene3.add(winButtonArray[i]);
      winButtonArray[i].setBounds(740-490*i,90+410*i,140,140);
      winButtonArray[i].addActionListener(this);
      winButtonArray[i].setActionCommand(j);
      winButtonArray[i].setContentAreaFilled(false);
      winButtonArray[i].setBorderPainted(false);
    }

    setUser();
    // myAreaButton = new JButton(rbIcon);
    // scene3.add(myAreaButton);
    // myAreaButton.setBounds(740,90,140,140);
    // myAreaButton.addActionListener(this);
    // myAreaButton.setActionCommand("1");
    // myAreaButton.setContentAreaFilled(false);
    // myAreaButton.setBorderPainted(false);    

    // yourAreaButton = new JButton(brIcon);
    // scene3.add(yourAreaButton);
    // yourAreaButton.setBounds(250,500,140,140);
    // yourAreaButton.addActionListener(this);
    // yourAreaButton.setActionCommand("0");
    // yourAreaButton.setContentAreaFilled(false);
    // yourAreaButton.setBorderPainted(false); 

    for(int i=0; i<5; i++){
      boardArray[0][i].setIcon(redIcon);
    }
    for(int i=0; i<5; i++){
      boardArray[4][i].setIcon(blueIcon);
    }

    scenePanel = new JPanel();
    layout = new CardLayout();
    scenePanel.setLayout(layout);

    scenePanel.add(scene1, "title");
    scenePanel.add(scene2, "charactorSelect");
    scenePanel.add(scene3, "Play");

    // /* カード移動用ボタン */
    // JButton firstButton = new JButton("First");
    // firstButton.addActionListener(this);
    // firstButton.setActionCommand("First");

    // JButton prevButton = new JButton("Prev");
    // prevButton.addActionListener(this);
    // prevButton.setActionCommand("Prev");

    // JButton nextButton = new JButton("Next");
    // nextButton.addActionListener(this);
    // nextButton.setActionCommand("Next");

    // JButton lastButton = new JButton("Last");
    // lastButton.addActionListener(this);
    // lastButton.setActionCommand("Last");

    // JPanel btnPanel = new JPanel();
    // btnPanel.add(firstButton);
    // btnPanel.add(prevButton);
    // btnPanel.add(nextButton);
    // btnPanel.add(lastButton);

    getContentPane().add(scenePanel, BorderLayout.CENTER);
    //getContentPane().add(btnPanel, BorderLayout.PAGE_END);



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
		
	// 内部クラス１：メッセージ受信のためのスレッド
	public class MesgRecvThread extends Thread {
		Socket socket;
		String myName;
		
    // 内部クラス１のコンストラクタ
		public MesgRecvThread(Socket s, String n){
			socket = s;
			myName = n;
		}
		
	  // 内部クラス１のメソッド：通信状況を監視し，受信データによって動作する
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(myName);//接続の最初に名前を送る
        System.out.println(myName);
        String myNumberStr = br.readLine();
        int myNumberInt = Integer.parseInt(myNumberStr);

        if(myNumberInt % 2 != 0){
          myColor=0;// player1:red
          myNumber=111;
          winFlag=false;
          myTurn=1;// 先行
          myIcon=redIcon;
          mydoubleIcon=rbIcon;
          yourdoubleIcon=brIcon;
          s_mydoubleIcon=s_rbIcon;
          s_yourdoubleIcon=s_brIcon;        
          s_doubleIcon=s_rbIcon;
          s_myIcon=s_redIcon;
          g_myIcon=g_redIcon;
          yourIcon=blueIcon;
          s_yourIcon=s_blueIcon;
          g_yourIcon=g_blueIcon;
        }else{
          myColor=1;// player2:blue
          myNumber=222;
          winFlag=false;
          myTurn=0;// 後攻
          myIcon=blueIcon;
          mydoubleIcon=brIcon;
          yourdoubleIcon=rbIcon;
          s_mydoubleIcon=s_brIcon;
          s_yourdoubleIcon=s_rbIcon;
          s_doubleIcon=s_brIcon;
          s_myIcon=s_blueIcon;
          g_myIcon=g_blueIcon;
          yourIcon=redIcon;
          s_yourIcon=s_redIcon;
          g_yourIcon=g_redIcon;
        }

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
              if(cmd.equals("SELECT")){
                String theBName = inputTokens[1];
                int theBnum = Integer.parseInt(theBName);
                int x = theBnum % 5;
                int y = theBnum / 5;
                int theColor = Integer.parseInt(inputTokens[2]);
                Icon theIcon = boardArray[y][x].getIcon();

                if(theColor == myColor){// もしクリックしたアイコンの種類がじぶんだったら
                  clearSelectedIcon(theColor);// 既存の選択部分とガイドを解除
                  if(theIcon == myIcon){// 次に、クリックしたアイコンがコマだったら
                    boardArray[y][x].setIcon(s_myIcon);// そのクリックしたアイコンを選択状態のアイコンに変更
                    showGuide(x, y, theColor);// ガイド表示         
                  }else if(theIcon == mydoubleIcon){
                    boardArray[y][x].setIcon(s_mydoubleIcon);// そのクリックしたアイコンを選択状態のアイコンに変更
                    showGuide(x, y, theColor);// ガイド表示
                  }else if(theIcon==s_myIcon){// 選択状態を解除する
                    boardArray[y][x].setIcon(myIcon);
                  }else if(theIcon==s_mydoubleIcon){// 選択状態を解除する
                    boardArray[y][x].setIcon(mydoubleIcon);
                  }
                }else{// もしクリックしたアイコンの種類があいてだったら
                  clearSelectedIcon(theColor);// 既存の選択部分とガイドを解除
                  if(theIcon == yourIcon){
                    boardArray[y][x].setIcon(s_yourIcon);
                    showGuide(x, y, theColor);// ガイド表示   
                  }else if(theIcon == yourdoubleIcon){
                    boardArray[y][x].setIcon(s_yourdoubleIcon);// 選択状態のアイコンに変更
                    showGuide(x, y, theColor);// ガイド表示   
                  }else if(theIcon==s_yourIcon){// 選択状態を解除する
                    boardArray[y][x].setIcon(yourIcon);
                  }else if(theIcon==s_yourdoubleIcon){
                    boardArray[y][x].setIcon(yourdoubleIcon);
                  }              
                }
              }

              if(cmd.equals("PLACE")){
                myTurn = 1 - myTurn;
                System.out.println("myTurn="+myTurn);
                String theBName = inputTokens[1];
                int theBnum = Integer.parseInt(theBName);
                int x = theBnum % 5;
                int y = theBnum / 5;
                int theColor = Integer.parseInt(inputTokens[2]);
                Icon theIcon = boardArray[y][x].getIcon();

                if(theColor==myColor){              
                  clearPlacedIcon(theColor);
                  if(theIcon==guideIcon){
                    boardArray[y][x].setIcon(myIcon);
                  }else if(theIcon==g_yourIcon){
                    boardArray[y][x].setIcon(mydoubleIcon);
                  }
                }else{
                  clearPlacedIcon(theColor);
                  if(theIcon==guideIcon){
                    boardArray[y][x].setIcon(yourIcon);
                  }else if(theIcon==g_myIcon){
                    boardArray[y][x].setIcon(yourdoubleIcon);
                  }
                }
                
                if(myColor==0){
                  int count = 0;
                  for(int i=0; i<5; i++){
                    if(boardArray[4][i].getIcon()==myIcon||boardArray[4][i].getIcon()==mydoubleIcon){
                        count++;
                    }
                  }
                  if(count>0){
                    winFlag=true;
                    System.out.println("winFlag0="+winFlag);
                  }else{
                    winFlag=false;
                    System.out.println("winFlag0="+winFlag);
                  }
                }else if(myColor==1){
                  int count = 0;
                  for(int i=0; i<5; i++){
                    if(boardArray[0][i].getIcon()==myIcon||boardArray[0][i].getIcon()==mydoubleIcon){
                        count++;
                    }
                  }
                  if(count>0){
                    winFlag=true;
                    System.out.println("winFlag1="+winFlag);
                  }else{
                    winFlag=false;
                    System.out.println("winFlag1="+winFlag);
                  }
                }
              }

              if(cmd.equals("WIN")){
                  String theBName = inputTokens[1];
                  int theBnum = Integer.parseInt(theBName);
                  int theColor = Integer.parseInt(inputTokens[2]);
                  if(theBnum!=theColor){
                    if(theBnum!=myColor){
                      System.out.println("勝ち");
                      winButtonArray[theBnum].setIcon(myIcon);
                    }else{
                      System.out.println("負け");
                      winButtonArray[theBnum].setIcon(yourIcon);
                    }
                  }else{
                    System.out.println("置くことが出来ません");
                  }

                  
                  clearSelectedIcon(theColor);

              }
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

  // メソッド
	public static void main(String[] args) throws InterruptedException{
		MyClient net = new MyClient();
    // TitleView ti = new TitleView();
		net.setVisible(true);
    // Thread.sleep(1000);
    // net.change(ti);
	}

  // メソッド
  public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();
    System.out.println("a-cmd"+cmd);
    System.out.println("myColor="+myColor);
    System.out.println(cmd.equals(Integer.toString(myColor)));

    if (cmd.equals("First")){
      layout.first(scenePanel);
    }else if (cmd.equals("Last")){
      layout.last(scenePanel);
    }else if (cmd.equals("Next") || cmd.equals("Start")){
      layout.next(scenePanel);
    }else if (cmd.equals("Prev")){
      layout.previous(scenePanel);
    }
    if (cmd.equals("character1")){
      JLabel chara1 = new JLabel(character1Img);
      scene3.add(chara1);
      chara1.setBounds(50, 50,300,300);
      scene3.setLayer(chara1,1000);
      layout.next(scenePanel);
    }else if(cmd.equals("character2")){
      JLabel chara2 = new JLabel(character2Img);
      scene3.add(chara2);
      chara2.setBounds(50, 50,300,300);
      scene3.setLayer(chara2,1000);
      layout.next(scenePanel);
    }else if(cmd.equals("character3")){
      JLabel chara3 = new JLabel(character3Img);
      scene3.add(chara3);
      chara3.setBounds(50, 50,300,300);
      scene3.setLayer(chara3,1000);
      layout.next(scenePanel);
    }

    boolean flag =false;
    if(myTurn==1){
      if(winFlag){
        if (cmd.equals("0")||cmd.equals("1")){
          JButton theButton = (JButton)e.getSource();
          String theNumber = theButton.getActionCommand();
          flag=true;
          String msg = "WIN"+" "+theNumber+" "+myColor;
          out.println(msg);
          out.flush();
          WinDialogWindow dlg = new WinDialogWindow(this);
          dlg.add(new JButton());
          setVisible(true);
        }
      }
    }
  }

  // メソッド
	public void mouseClicked(MouseEvent e) {
    System.out.println("クリック");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    System.out.println("theArrayIndex="+temp);
    System.out.println("theArrayIndex="+theIcon);
    
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    if(myTurn==1){
      // コマの選択状態を示す
      if(theIcon == myIcon || theIcon == s_myIcon || theIcon == mydoubleIcon || theIcon == s_mydoubleIcon){
        String msg = "SELECT"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
      // コマを置く
      if(theIcon == guideIcon || theIcon == g_yourIcon){
        String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
    }    

  repaint();//画面のオブジェクトを描画し直す
	}
	
	public void mouseEntered(MouseEvent e) {
    // System.out.println("ホバー");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    // System.out.println("x="+x);
    // System.out.println("y="+y);

    // if (theIcon == myIcon){
    //   boardArray[y][x].setIcon(catIcon);
    // }
    scene3.setLayer(boardArray[y][x],1000);

	}
	
	public void mouseExited(MouseEvent e) {
    // System.out.println("ホバーおわり");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    // System.out.println("x="+x);
    // System.out.println("y="+y);

    //boardArray[y][x].setIcon(orangeIcon);
    scene3.setLayer(boardArray[y][x],y*5+x+1);
	}
	
	public void mousePressed(MouseEvent e) {
	}
	
	public void mouseReleased(MouseEvent e) {
	}
	
	public void mouseDragged(MouseEvent e) {
	}

	public void mouseMoved(MouseEvent e) {
	}

  // 座標が8×8のマス目を超えているかどうかを判定する関数
  public boolean isExceededArea(int posY, int posX){
    return posX < 0 || posY < 0 || posX > 4 || posY > 4;
  }

  // ガイドの表示
  public void showGuide(int x, int y, int theColor){
    for (int j=-1;j<2;j++){
      for (int i=-1;i<2;i++){
        int posX = x + i;
        int posY = y + j;

        if(isExceededArea(posX, posY)){
          continue;// trueなら以降の処理をスキップ
        }

        Icon theIcon2 = boardArray[posY][posX].getIcon();
        if(theIcon2 == orangeIcon){
          boardArray[posY][posX].setIcon(guideIcon);
        }else if(theColor==myColor){
          if(theIcon2 == yourIcon){
            boardArray[posY][posX].setIcon(g_yourIcon);
          }
        }else if(theColor!=myColor){
          if(theIcon2 == myIcon){
            boardArray[posY][posX].setIcon(g_myIcon);
          }
        }
      }
    }
  }
  
  // 既存の選択部分やガイド部分を解除
  public void clearSelectedIcon(int theColor){
    if(theColor==myColor){
      for(int j=0;j<5;j++){// まず、他の選択部分があれば解除する
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_myIcon){// 選択状態の自分のアイコンをもとに戻す
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_yourIcon){// ガイド状態の相手のアイコンをもとに戻す
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == s_mydoubleIcon){
            boardArray[j][i].setIcon(mydoubleIcon);
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// まず、他の選択部分があれば解除する
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_yourIcon){// 選択状態の自分のアイコンをもとに戻す
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_myIcon){// ガイド状態の相手のアイコンをもとに戻す
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == s_yourdoubleIcon){
            boardArray[j][i].setIcon(yourdoubleIcon);
          }
        }
      }
    }
  }

  // 既存の選択部分やガイド部分を解除+placedアイコンをいどうさせる
  public void clearPlacedIcon(int theColor){
    if(theColor==myColor){
      for(int j=0;j<5;j++){// まず、他の選択部分があれば解除する
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_myIcon){// 選択状態の自分のアイコンをもとに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_yourIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == s_mydoubleIcon){
            boardArray[j][i].setIcon(yourIcon);                      
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// まず、他の選択部分があれば解除する
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon()==s_yourIcon){// 選択状態の自分のアイコンをもとに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_myIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == s_yourdoubleIcon){
            boardArray[j][i].setIcon(myIcon);                      
          }
        }
      }
    }
  }

  public static void resetBoard(){
    boardArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        boardArray[j][i].setIcon(orangeIcon);
      }
    }
    setUser();
  }

  public static void setUser(){
    for(int i=0; i<5; i++){
      boardArray[0][i].setIcon(redIcon);
    }
    for(int i=0; i<5; i++){
      boardArray[4][i].setIcon(blueIcon);
    }
  }
  
  // 内部クラス２：音楽再生
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

//ダイアログのためのクラス
//絵をクリックしたら閉じるようにしている
class WinDialogWindow extends JDialog implements ActionListener{
    WinDialogWindow(JFrame owner) {
        super(owner);//呼び出しもととの親子関係の設定．これをコメントアウトすると別々のダイアログになる

		    Container c = this.getContentPane();	//フレームのペインを取得する
        c.setLayout(null);		//自動レイアウトの設定を行わない
        c.setBackground(Color.green);

        setTitle("You Win!");//タイトルの設定
        setSize(800, 500);//大きさの設定
        setResizable(false);//拡大縮小禁止//trueにすると拡大縮小できるようになる
        setUndecorated(true); //タイトルを表示しない
        setModal(true);//上を閉じるまで下を触れなくする（falseにすると触れる）

        JButton rePlayButton = new JButton("もういっかい");
        JButton endButton = new JButton("タイトルに戻る");
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
      String cmd = e.getActionCommand();
      if (cmd.equals("REPLAY")){
        //layout.first(scenePanel);
        this.dispose();//Dialogを廃棄する
        MyClient.resetBoard();
      }else if (cmd.equals("END")){
        MyClient.layout.first(MyClient.scenePanel);
        this.dispose();//Dialogを廃棄する
      }
    }
}