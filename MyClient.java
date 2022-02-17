import java.net.*;
import java.io.*;
import javax.swing.*;

// import java.lang.*;
// import java.awt.*;
// import java.awt.image.*;
import java.awt.event.*;
//import java.util.*;

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
  // JButton
  private static JButton boardArray[][], winButtonArray[];
  private JButton startButton, retryButton, titleBackButton;
  
  private int myColor, turnNum;
  private JLayeredPane scene3;
  private JLabel bg, title, select, turnPanel, resultPanel;
  private JPanel scene1, scene2;
  private boolean winFlag, resultFlag;
  private PrintWriter out;
  SoundPlayer theSoundPlayer1;
  SoundPlayer theSoundPlayer2;
  private JPanel scenePanel;
  private CardLayout layout;
  
  // ImageIcon
  private ImageIcon myIcon, yourIcon;
  private ImageIcon mydoubleIcon, yourdoubleIcon;
  private ImageIcon s_myIcon, s_yourIcon, s_mydoubleIcon, s_yourdoubleIcon; // 選択状態アイコン
  private ImageIcon g_myIcon, g_yourIcon, ubutton, kbutton, nbutton;
  private ImageIcon g_mymasuIcon,g_yourmasuIcon;
  private ImageIcon bgImg, titleImg, selectImg, returnIcon;

  private static ImageIcon masuIcon;
  private ImageIcon n_rabitIcon, n_catIcon, n_bearIcon;
  private ImageIcon n_rbIcon, n_rcIcon, n_brIcon, n_bcIcon, n_crIcon, n_cbIcon;
  private ImageIcon s_rabitIcon, s_bearIcon, s_catIcon;
  private ImageIcon s_rbIcon,s_rcIcon,s_brIcon,s_bcIcon,s_crIcon,s_cbIcon;
  private ImageIcon g_r_masuIcon, g_b_masuIcon,g_c_masuIcon;
  private ImageIcon g_r_bearIcon,g_r_catIcon,g_b_rabitIcon,g_b_catIcon,g_c_rabitIcon,g_c_bearIcon;  
  private ImageIcon r_goalIcon, b_goalIcon, c_goalIcon;
  private ImageIcon r_myturn, b_myturn, c_myturn;
  private ImageIcon r_yourturn, b_yourturn, c_yourturn;
  private ImageIcon myTurn, yourTurn, myWin, myLose, myRetry;
  private ImageIcon myGoalIcon, yourGoalIcon;
  private ImageIcon r_win, b_win, c_win;
  private ImageIcon r_lose, b_lose, c_lose;
  private ImageIcon r_retry, b_retry, c_retry, titleBack;


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

    theSoundPlayer1 = new SoundPlayer("honeylemon.wav");
    theSoundPlayer1.SetLoop(true);//ＢＧＭとして再生を繰り返す
    theSoundPlayer1.play();

		// アイコンの設定
    // ふつうのマスのアイコン
    masuIcon = new ImageIcon("img/masu.png");

    // キャラクター＋ふつうのますのアイコン
    n_rabitIcon = new ImageIcon("img/normal-rabit.png");
    n_bearIcon = new ImageIcon("img/normal-bear.png");
    n_catIcon = new ImageIcon("img/normal-cat.png");

    // キャラクター２＋ふつうのますのアイコン
    n_rbIcon = new ImageIcon("img/rabitbear.png");
    n_rcIcon = new ImageIcon("img/rabitcat.png");
    n_brIcon = new ImageIcon("img/bearrabit.png");
    n_bcIcon = new ImageIcon("img/bearcat.png");
    n_crIcon = new ImageIcon("img/catrabit.png");
    n_cbIcon = new ImageIcon("img/catbear.png");

    // 選択状態の１キャラクターのみアイコン
    s_rabitIcon = new ImageIcon("img/s-rabit.png");
    s_bearIcon = new ImageIcon("img/s-bear.png");
    s_catIcon = new ImageIcon("img/s-cat.png");

    // 選択状態の２キャラクターアイコン
    s_rbIcon = new ImageIcon("img/s-rabitbear.png");
    s_rcIcon = new ImageIcon("img/s-rabitcat.png");
    s_brIcon = new ImageIcon("img/s-bearrabit.png");
    s_bcIcon = new ImageIcon("img/s-bearcat.png");
    s_crIcon = new ImageIcon("img/s-catrabit.png");
    s_cbIcon = new ImageIcon("img/s-catbear.png");

    // ガイド状態のマス
    g_r_masuIcon = new ImageIcon("img/g-r-masu.png");
    g_b_masuIcon = new ImageIcon("img/g-b-masu.png");
    g_c_masuIcon = new ImageIcon("img/g-c-masu.png");

    // ガイド状態のキャラクターます
    g_r_bearIcon = new ImageIcon("img/g-r-bear.png");
    g_r_catIcon = new ImageIcon("img/g-r-cat.png");
    g_b_rabitIcon = new ImageIcon("img/g-b-rabit.png");
    g_b_catIcon = new ImageIcon("img/g-b-cat.png");
    g_c_rabitIcon = new ImageIcon("img/g-c-rabit.png");
    g_c_bearIcon = new ImageIcon("img/g-c-bear.png");

    // ゴールアイコン
    r_goalIcon = new ImageIcon("img/r-win.png");
    b_goalIcon = new ImageIcon("img/b-win.png");
    c_goalIcon = new ImageIcon("img/c-win.png");

    // ゲームステージアイコン
    bgImg = new ImageIcon("img/stage.png");

    // タイトル画面
    titleImg = new ImageIcon("img/title2.png");

    // キャラクターセレクトボタン
    selectImg = new ImageIcon("img/select2.png");
    returnIcon = new ImageIcon("img/returnbutton21.png");
    ubutton = new ImageIcon("img/uselect1.png");
    kbutton = new ImageIcon("img/kselect1.png");
    nbutton = new ImageIcon("img/nselect1.png");

    // ターン表示
    r_myturn = new ImageIcon("img/r-myturn.png");
    r_yourturn = new ImageIcon("img/r-yourturn.png");
    b_myturn = new ImageIcon("img/b-myturn.png");
    b_yourturn = new ImageIcon("img/b-yourturn.png");
    c_myturn = new ImageIcon("img/c-myturn.png");
    c_yourturn = new ImageIcon("img/c-yourturn.png");

    // リザルト画面
    r_win = new ImageIcon("img/r-resultwin.png");
    b_win = new ImageIcon("img/b-resultwin.png");
    c_win = new ImageIcon("img/c-resultwin.png");
    r_lose = new ImageIcon("img/r-resultlose.png");
    b_lose = new ImageIcon("img/b-resultlose.png");
    c_lose = new ImageIcon("img/c-resultlose.png");
    r_retry = new ImageIcon("img/r-retry.png");
    b_retry = new ImageIcon("img/b-retry.png");
    c_retry = new ImageIcon("img/c-retry.png");
    titleBack = new ImageIcon("img/titleback.png");

    /* -----------------------scene1------------------------- */
    scene1 = new JPanel();
    scene1.setLayout(null);

    title = new JLabel(titleImg);
    scene1.add(title);
    title.setBounds(0, 0,1024,786);
    
    startButton = new JButton();
    scene1.add(startButton);
    startButton.addActionListener(this);
    startButton.setActionCommand("Start");
    startButton.setBounds(0, 0, 1024, 786);
    startButton.setContentAreaFilled(false);
    startButton.setBorderPainted(false);

    /* -----------------------scene2------------------------- */
    scene2 = new JPanel();
    scene2.setLayout(null);
    JButton returnButton = new JButton(returnIcon);
    JButton c1Button = new JButton(ubutton);
    JButton c2Button = new JButton(kbutton);
    JButton c3Button = new JButton(nbutton);
    scene2.add(c1Button);
    scene2.add(c2Button);
    scene2.add(c3Button);
    scene2.add(returnButton);

    select = new JLabel(selectImg);
    scene2.add(select);
    select.setBounds(0, 0,1024,786);


    // boardArray[j][i].setBounds(x,y,xs,ys);
    returnButton.addActionListener(this);
    returnButton.setActionCommand("RETURN");
    c1Button.addActionListener(this);
    c1Button.setActionCommand("character1");
    c2Button.addActionListener(this);
    c2Button.setActionCommand("character2");
    c3Button.addActionListener(this);
    c3Button.setActionCommand("character3");

    returnButton.setBounds(30, 50, 80, 80);
    c1Button.setBounds(85 , 600, 250, 60);
    c2Button.setBounds(385, 600, 250, 60);
    c3Button.setBounds(684, 600, 250, 60);

    returnButton.setContentAreaFilled(false);
    returnButton.setBorderPainted(false);
    c1Button.setContentAreaFilled(false);
    c1Button.setBorderPainted(false);
    c2Button.setContentAreaFilled(false);
    c2Button.setBorderPainted(false);
    c3Button.setContentAreaFilled(false);
    c3Button.setBorderPainted(false);

    /* -----------------------scene3------------------------- */
    scene3 = new JLayeredPane();
    
    bg = new JLabel(bgImg);
    scene3.add(bg);
    bg.setBounds(0, 0,1024, 786);
    scene3.setLayer(bg,0);

    // ガイドの生成ver2
		boardArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        boardArray[j][i] = new JButton(masuIcon);
        scene3.add(boardArray[j][i]);
        boardArray[j][i].setBounds(i*70+430-70*j,j*70+5+70*i,140,140);
        boardArray[j][i].addMouseListener(this);
        scene3.setLayer(boardArray[j][i], j*5+i+4);
        boardArray[j][i].setActionCommand(Integer.toString(j*5+i));
        boardArray[j][i].setContentAreaFilled(false);
        boardArray[j][i].setBorderPainted(false);
      }
		}

    turnPanel = new JLabel();
    turnPanel.setBounds(20, 20, 350, 100);
    scene3.add(turnPanel);
    scene3.setLayer(turnPanel, 32);

    // 上のほうが0で、下のほうが1
    winButtonArray = new JButton[2];
    for(int i=0; i<2; i++){
      String j = Integer.toString(i);
      winButtonArray[i] = new JButton();
      scene3.add(winButtonArray[i]);
      winButtonArray[i].setBounds(670-470*i,90+440*i,140,140);
      winButtonArray[i].addActionListener(this);
      scene3.setLayer(winButtonArray[i], 30+i);
      winButtonArray[i].setActionCommand(j);
      winButtonArray[i].setContentAreaFilled(false);
      winButtonArray[i].setBorderPainted(false);
    }

    scenePanel = new JPanel();
    layout = new CardLayout();
    scenePanel.setLayout(layout);

    scenePanel.add(scene1, "title");
    scenePanel.add(scene2, "charactorSelect");
    scenePanel.add(scene3, "Play");

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
          winFlag=false;
          turnNum=1;// 先行
        }else{
          myColor=1;// player2:blue
          winFlag=false;
          turnNum=0;// 後攻
        }

				while(true) {
					String inputLine = br.readLine();
          System.out.println("inputline="+inputLine);
					if (inputLine != null) {
						String[] inputTokens = inputLine.split(" ");
						String cmd = inputTokens[0];
            
            if(cmd.equals("SET")){
              String theCName = inputTokens[1];//キャラクターの種類が入る
              int theColor = Integer.parseInt(inputTokens[2]);
              if(theCName.equals("RABIT")){
                if(theColor==0){
                  for(int i=0; i<5; i++){
                    boardArray[0][i].setIcon(n_rabitIcon);
                    winButtonArray[0].setIcon(r_goalIcon);
                  }
                }else if(theColor==1){
                  for(int i=0; i<5; i++){
                    boardArray[4][i].setIcon(n_rabitIcon);
                    winButtonArray[1].setIcon(r_goalIcon);
                  }
                }
              }else if(theCName.equals("BEAR")){
                if(theColor==0){
                  for(int i=0; i<5; i++){
                    boardArray[0][i].setIcon(n_bearIcon);
                    winButtonArray[0].setIcon(b_goalIcon);
                  }
                }else if(theColor==1){
                  for(int i=0; i<5; i++){
                    boardArray[4][i].setIcon(n_bearIcon);
                    winButtonArray[1].setIcon(b_goalIcon);
                  }
                }
              }else if(theCName.equals("CAT")){
                if(theColor==0){
                  for(int i=0; i<5; i++){
                    boardArray[0][i].setIcon(n_catIcon);
                    winButtonArray[0].setIcon(c_goalIcon);
                  }
                }else if(theColor==1){
                  for(int i=0; i<5; i++){
                    boardArray[4][i].setIcon(n_catIcon);
                    winButtonArray[1].setIcon(c_goalIcon);
                  }
                }          
              }

              Icon player1Icon, player2Icon;
              player1Icon = boardArray[0][0].getIcon();
              player2Icon = boardArray[4][0].getIcon();

              
              int switchNum=0;
            
              if(player1Icon==n_rabitIcon&&player2Icon==n_bearIcon){
                switchNum=0;//rb
              }else if(player1Icon==n_rabitIcon&&player2Icon==n_catIcon){
                switchNum=1;//rc
              }else if(player1Icon==n_bearIcon&&player2Icon==n_rabitIcon){
                switchNum=2;//br
              }else if(player1Icon==n_bearIcon&&player2Icon==n_catIcon){
                switchNum=3;//bc
              }else if(player1Icon==n_catIcon&&player2Icon==n_rabitIcon){
                switchNum=4;//cr
              }else if(player1Icon==n_catIcon&&player2Icon==n_bearIcon){
                switchNum=5;//cb
              }
              
              switch(switchNum){
                case 0:
                  if(myColor==0){//rb
                    myTurn=r_myturn;
                    yourTurn=r_yourturn;
                    myWin=r_win;
                    myLose=r_lose;
                    myRetry=r_retry;
                    myGoalIcon=r_goalIcon;
                    yourGoalIcon=b_goalIcon;

                    myIcon=n_rabitIcon;
                    s_myIcon=s_rabitIcon;
                    g_myIcon=g_b_rabitIcon;
                    mydoubleIcon=n_rbIcon;
                    s_mydoubleIcon=s_rbIcon;
                    g_mymasuIcon=g_r_masuIcon;

                    yourIcon=n_bearIcon;
                    s_yourIcon=s_bearIcon;
                    g_yourIcon=g_r_bearIcon;
                    yourdoubleIcon=n_brIcon;
                    s_yourdoubleIcon=s_brIcon;        
                    g_yourmasuIcon=g_b_masuIcon;
                    turnPanel.setIcon(r_myturn);
                  }else if(myColor==1){
                    myTurn=b_myturn;
                    yourTurn=b_yourturn;
                    myWin=b_win;
                    myLose=b_lose;
                    myRetry=b_retry;
                    myGoalIcon=b_goalIcon;
                    yourGoalIcon=r_goalIcon;

                    myIcon=n_bearIcon;
                    s_myIcon=s_bearIcon;
                    g_myIcon=g_r_bearIcon;
                    mydoubleIcon=n_brIcon;
                    s_mydoubleIcon=s_brIcon;
                    g_mymasuIcon=g_b_masuIcon;

                    yourIcon=n_rabitIcon;
                    s_yourIcon=s_rabitIcon;
                    g_yourIcon=g_b_rabitIcon;
                    yourdoubleIcon=n_rbIcon;
                    s_yourdoubleIcon=s_rbIcon;        
                    g_yourmasuIcon=g_r_masuIcon;
                    turnPanel.setIcon(b_yourturn);
                  }
                  break;
                case 1:
                  if(myColor==0){//rc
                    myTurn=r_myturn;
                    yourTurn=r_yourturn;
                    myWin=r_win;
                    myLose=r_lose;
                    myRetry=r_retry;
                    myGoalIcon=r_goalIcon;
                    yourGoalIcon=c_goalIcon;

                    myIcon=n_rabitIcon;
                    s_myIcon=s_rabitIcon;
                    g_myIcon=g_c_rabitIcon;
                    mydoubleIcon=n_rcIcon;
                    s_mydoubleIcon=s_rcIcon;
                    g_mymasuIcon=g_r_masuIcon;

                    yourIcon=n_catIcon;
                    s_yourIcon=s_catIcon;
                    g_yourIcon=g_r_catIcon;
                    yourdoubleIcon=n_crIcon;
                    s_yourdoubleIcon=s_crIcon;        
                    g_yourmasuIcon=g_c_masuIcon;
                    turnPanel.setIcon(r_myturn);
                  }else if(myColor==1){
                    myTurn=c_myturn;
                    yourTurn=c_yourturn;
                    myWin=c_win;
                    myLose=c_lose;
                    myRetry=c_retry;
                    myGoalIcon=c_goalIcon;
                    yourGoalIcon=r_goalIcon;

                    myIcon=n_catIcon;
                    s_myIcon=s_catIcon;
                    g_myIcon=g_r_catIcon;
                    mydoubleIcon=n_crIcon;
                    s_mydoubleIcon=s_crIcon;
                    g_mymasuIcon=g_c_masuIcon;

                    yourIcon=n_rabitIcon;
                    s_yourIcon=s_rabitIcon;
                    g_yourIcon=g_c_rabitIcon;
                    yourdoubleIcon=n_rcIcon;
                    s_yourdoubleIcon=s_rcIcon;                  
                    g_yourmasuIcon=g_r_masuIcon;
                    turnPanel.setIcon(c_yourturn);
                  }
                  break;              
                case 2:
                  if(myColor==0){//br
                    myTurn=b_myturn;
                    yourTurn=b_yourturn;
                    myWin=b_win;
                    myLose=b_lose;
                    myRetry=b_retry;
                    myGoalIcon=b_goalIcon;
                    yourGoalIcon=r_goalIcon;

                    myIcon=n_bearIcon;
                    s_myIcon=s_bearIcon;
                    g_myIcon=g_r_bearIcon;
                    mydoubleIcon=n_brIcon;                 
                    s_mydoubleIcon=s_brIcon;
                    g_mymasuIcon=g_b_masuIcon;

                    yourIcon=n_rabitIcon;
                    s_yourIcon=s_rabitIcon;
                    g_yourIcon=g_b_rabitIcon;
                    yourdoubleIcon=n_rbIcon;
                    s_yourdoubleIcon=s_rbIcon;        
                    g_yourmasuIcon=g_r_masuIcon;
                    turnPanel.setIcon(b_myturn);
                  }else if(myColor==1){
                    myTurn=r_myturn;
                    yourTurn=r_yourturn;
                    myWin=r_win;
                    myLose=r_lose;
                    myRetry=r_retry;
                    myGoalIcon=r_goalIcon;
                    yourGoalIcon=b_goalIcon;

                    myIcon=n_rabitIcon;
                    s_myIcon=s_rabitIcon;
                    g_myIcon=g_b_rabitIcon;
                    mydoubleIcon=n_rbIcon;
                    s_mydoubleIcon=s_rbIcon;
                    g_mymasuIcon=g_r_masuIcon;

                    yourIcon=n_bearIcon;
                    s_yourIcon=s_bearIcon;
                    g_yourIcon=g_r_bearIcon;
                    yourdoubleIcon=n_brIcon;
                    s_yourdoubleIcon=s_brIcon;        
                    g_yourmasuIcon=g_b_masuIcon;
                    turnPanel.setIcon(r_yourturn);
                  }
                  break;
                case 3:
                  if(myColor==0){//bc　ここから
                    myTurn=b_myturn;
                    yourTurn=b_yourturn;
                    myWin=b_win;
                    myLose=b_lose;
                    myRetry=b_retry;
                    myGoalIcon=b_goalIcon;
                    yourGoalIcon=c_goalIcon;

                    myIcon=n_bearIcon;
                    s_myIcon=s_bearIcon;
                    g_myIcon=g_c_bearIcon;
                    mydoubleIcon=n_bcIcon;
                    s_mydoubleIcon=s_bcIcon;
                    g_mymasuIcon=g_b_masuIcon;

                    yourIcon=n_catIcon;
                    s_yourIcon=s_catIcon;
                    g_yourIcon=g_b_catIcon;
                    yourdoubleIcon=n_cbIcon;
                    s_yourdoubleIcon=s_cbIcon;        
                    g_yourmasuIcon=g_c_masuIcon;
                    turnPanel.setIcon(b_myturn);
                  }else if(myColor==1){
                    myTurn=c_myturn;
                    yourTurn=c_yourturn;
                    myWin=c_win;
                    myLose=c_lose;
                    myRetry=c_retry;
                    myGoalIcon=c_goalIcon;
                    yourGoalIcon=b_goalIcon;

                    myIcon=n_catIcon;
                    s_myIcon=s_catIcon;
                    g_myIcon=g_b_catIcon;
                    mydoubleIcon=n_cbIcon;
                    s_mydoubleIcon=s_cbIcon;
                    g_mymasuIcon=g_c_masuIcon;

                    yourIcon=n_bearIcon;
                    s_yourIcon=s_bearIcon;
                    g_yourIcon=g_c_bearIcon;
                    yourdoubleIcon=n_bcIcon;
                    s_yourdoubleIcon=s_bcIcon;        
                    g_yourmasuIcon=g_b_masuIcon;
                    turnPanel.setIcon(c_yourturn);
                  }
                  break;              
                case 4:
                  if(myColor==0){//cr
                    myTurn=c_myturn;
                    yourTurn=c_yourturn;
                    myWin=c_win;
                    myLose=c_lose;
                    myRetry=c_retry;
                    myGoalIcon=c_goalIcon;
                    yourGoalIcon=r_goalIcon;

                    myIcon=n_catIcon;
                    s_myIcon=s_catIcon;
                    g_myIcon=g_r_catIcon;
                    mydoubleIcon=n_crIcon;
                    s_mydoubleIcon=s_crIcon;
                    g_mymasuIcon=g_c_masuIcon;

                    yourIcon=n_rabitIcon;
                    s_yourIcon=s_rabitIcon;
                    g_yourIcon=g_c_rabitIcon;
                    yourdoubleIcon=n_rcIcon;
                    s_yourdoubleIcon=s_rcIcon;        
                    g_yourmasuIcon=g_r_masuIcon;
                    turnPanel.setIcon(c_myturn);
                  }else if(myColor==1){
                    myTurn=r_myturn;
                    yourTurn=r_yourturn;
                    myWin=r_win;
                    myLose=r_lose;
                    myRetry=r_retry;
                    myGoalIcon=r_goalIcon;
                    yourGoalIcon=c_goalIcon;

                    myIcon=n_rabitIcon;
                    s_myIcon=s_rabitIcon;
                    g_myIcon=g_c_rabitIcon;
                    mydoubleIcon=n_rcIcon;
                    s_mydoubleIcon=s_rcIcon;
                    g_mymasuIcon=g_r_masuIcon;
                    
                    yourIcon=n_catIcon;
                    s_yourIcon=s_catIcon;
                    g_yourIcon=g_r_catIcon;
                    yourdoubleIcon=n_crIcon;
                    s_yourdoubleIcon=s_crIcon;        
                    g_yourmasuIcon=g_c_masuIcon;
                    turnPanel.setIcon(r_yourturn);
                  }
                  break;               
                case 5:
                  if(myColor==0){//cb
                    myTurn=c_myturn;
                    yourTurn=c_yourturn;
                    myWin=c_win;
                    myLose=c_lose;
                    myRetry=c_retry;
                    myGoalIcon=c_goalIcon;
                    yourGoalIcon=b_goalIcon;

                    myIcon=n_catIcon;
                    s_myIcon=s_catIcon;
                    g_myIcon=g_b_catIcon;
                    mydoubleIcon=n_cbIcon;
                    s_mydoubleIcon=s_cbIcon;
                    g_mymasuIcon=g_c_masuIcon;
                    
                    yourIcon=n_bearIcon;
                    s_yourIcon=s_bearIcon;
                    g_yourIcon=g_c_bearIcon;
                    yourdoubleIcon=n_bcIcon;
                    s_yourdoubleIcon=s_bcIcon;        
                    g_yourmasuIcon=g_b_masuIcon;
                    turnPanel.setIcon(c_myturn);
                  }else if(myColor==1){
                    myTurn=b_myturn;
                    yourTurn=b_yourturn;
                    myWin=b_win;
                    myLose=b_lose;
                    myRetry=b_retry;
                    myGoalIcon=b_goalIcon;
                    yourGoalIcon=c_goalIcon;

                    myIcon=n_bearIcon;
                    s_myIcon=s_bearIcon;
                    g_myIcon=g_c_bearIcon;
                    mydoubleIcon=n_bcIcon;
                    s_mydoubleIcon=s_bcIcon;
                    g_mymasuIcon=g_b_masuIcon;
                    
                    yourIcon=n_catIcon;
                    s_yourIcon=s_catIcon;
                    g_yourIcon=g_b_catIcon;
                    yourdoubleIcon=n_cbIcon;
                    s_yourdoubleIcon=s_cbIcon;        
                    g_yourmasuIcon=g_c_masuIcon;
                    turnPanel.setIcon(b_yourturn);
                  }
                  break; 
              }
            }

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
              turnNum = 1 - turnNum;
              Icon turnIcon = turnPanel.getIcon();
              if(turnIcon==myTurn){
                turnPanel.setIcon(yourTurn);
              }else if(turnIcon==yourTurn){
                turnPanel.setIcon(myTurn);
              }
              System.out.println("turnNum="+turnNum);
              String theBName = inputTokens[1];
              int theBnum = Integer.parseInt(theBName);
              int x = theBnum % 5;
              int y = theBnum / 5;
              int theColor = Integer.parseInt(inputTokens[2]);
              Icon theIcon = boardArray[y][x].getIcon();

              if(theColor==myColor){              
                clearPlacedIcon(theColor);
                if(theIcon==g_mymasuIcon){
                  boardArray[y][x].setIcon(myIcon);
                }else if(theIcon==g_yourIcon){
                  boardArray[y][x].setIcon(mydoubleIcon);
                }
              }else{
                clearPlacedIcon(theColor);
                if(theIcon==g_yourmasuIcon){
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
            resultFlag=false;
            if(cmd.equals("WIN")){
              resultFlag=true;
              String theBName = inputTokens[1];
              int theBnum = Integer.parseInt(theBName);
              int theColor = Integer.parseInt(inputTokens[2]);
              
              result();

              retryButton.setIcon(myRetry);
              titleBackButton.setIcon(titleBack);
              if(theBnum!=theColor){
                if(theBnum!=myColor){
                  System.out.println("勝ち");
                  winButtonArray[theBnum].setIcon(myIcon);
                  resultPanel.setIcon(myWin);
                  
                }else{
                  System.out.println("負け");
                  winButtonArray[theBnum].setIcon(yourIcon);
                  resultPanel.setIcon(myLose);
                }
              }else{
                System.out.println("置くことが出来ません");
              }                  
              clearSelectedIcon(theColor);
              //popUp();
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

  public void result(){
    resultPanel = new JLabel();
    //resultPanel.setBackground(Color.GREEN);
    resultPanel.setBounds(262, 143, 500, 500);
    scene3.add(resultPanel);
    scene3.setLayer(resultPanel, 33);
    resultPanel.setOpaque(false);
    
    retryButton = new JButton();
    retryButton.setBounds(407, 470, 210, 60);
    scene3.add(retryButton);
    scene3.setLayer(retryButton, 34);
    retryButton.addActionListener(this);
    retryButton.setContentAreaFilled(false);
    retryButton.setBorderPainted(false);
    retryButton.setActionCommand("RETRY");

    titleBackButton = new JButton();
    titleBackButton.setBounds(407, 540, 210, 60);
    scene3.add(titleBackButton);
    scene3.setLayer(titleBackButton, 35);
    titleBackButton.addActionListener(this);
    titleBackButton.setContentAreaFilled(false);
    titleBackButton.setBorderPainted(false);    
    titleBackButton.setActionCommand("END");
  }

  // メソッド
  public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();
    System.out.println("a-cmd"+cmd);
    System.out.println("myColor="+myColor);
    System.out.println(cmd.equals(Integer.toString(myColor)));

    if(cmd.equals("RETRY")){
      if(resultFlag){
        resultPanel.setIcon(null);
        retryButton.setIcon(null);
        titleBackButton.setIcon(null);
        scene3.setLayer(resultPanel, 1);
        scene3.setLayer(retryButton, 2);
        scene3.setLayer(titleBackButton, 3);
        
        resetBoard(myIcon, yourIcon, myGoalIcon, yourGoalIcon, myColor);
        setUser(myIcon, yourIcon, myColor);

        resultFlag=false;
      }
    }else if(cmd.equals("END")){
      if(resultFlag){
        resultPanel.setIcon(null);
        retryButton.setIcon(null);
        titleBackButton.setIcon(null);
        clearBoard();
        layout.first(scenePanel);
        resultFlag=false;
      }
    }

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
      layout.next(scenePanel);
      String msg = "SET"+" "+"RABIT"+" "+myColor;
      out.println(msg);
      out.flush();
    }else if(cmd.equals("character2")){
      layout.next(scenePanel);
      String msg = "SET"+" "+"BEAR"+" "+myColor;
      out.println(msg);
      out.flush();
    }else if(cmd.equals("character3")){
      layout.next(scenePanel);
      String msg = "SET"+" "+"CAT"+" "+myColor;
      out.println(msg);
      out.flush();
    }else if(cmd.equals("RETURN")){
      layout.previous(scenePanel);
    }

    boolean flag =false;
    if(turnNum==1){
      if(winFlag){
        if (cmd.equals("0")||cmd.equals("1")){
          JButton theButton = (JButton)e.getSource();
          String theNumber = theButton.getActionCommand();
          flag=true;
          String msg = "WIN"+" "+theNumber+" "+myColor;
          out.println(msg);
          out.flush();
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

    if(turnNum==1){
      // コマの選択状態を示す
      theSoundPlayer2 = new SoundPlayer("kawaii.wav");
      theSoundPlayer2.play();
      if(theIcon == myIcon || theIcon == s_myIcon || theIcon == mydoubleIcon || theIcon == s_mydoubleIcon){
        String msg = "SELECT"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
      // コマを置く
      if(theIcon == g_mymasuIcon || theIcon == g_yourIcon){
        theSoundPlayer2 = new SoundPlayer("443_2.wav");
        theSoundPlayer2.play();
        String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
    }    

  repaint();//画面のオブジェクトを描画し直す
	}
	
	public void mouseEntered(MouseEvent e) {
    if(resultFlag==false){
      // System.out.println("ホバー");
      JButton theButton = (JButton)e.getComponent();
      String theArrayIndex = theButton.getActionCommand();

      int temp = Integer.parseInt(theArrayIndex);
      int x = temp % 5;
      int y = temp / 5;

      scene3.setLayer(boardArray[y][x],1000);
    }
	}
	
	public void mouseExited(MouseEvent e) {
    if(resultFlag==false){
      // System.out.println("ホバーおわり");
      JButton theButton = (JButton)e.getComponent();
      String theArrayIndex = theButton.getActionCommand();

      int temp = Integer.parseInt(theArrayIndex);
      int x = temp % 5;
      int y = temp / 5;

      scene3.setLayer(boardArray[y][x],y*5+x+4);
    }
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
    System.out.println("発動");
    for (int j=-1;j<2;j++){
      for (int i=-1;i<2;i++){
        int posX = x + i;
        int posY = y + j;
        if(isExceededArea(posX, posY)){
          continue;// trueなら以降の処理をスキップ
        }
        // 周辺のマスのアイコンをとってくる
        Icon theIcon2 = boardArray[posY][posX].getIcon();
        System.out.println(theIcon2);
        if(theIcon2 == masuIcon){
          if(theColor==myColor){
            boardArray[posY][posX].setIcon(g_mymasuIcon);
          }else{
            boardArray[posY][posX].setIcon(g_yourmasuIcon);
          }
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
          }else if(boardArray[j][i].getIcon() == g_mymasuIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(masuIcon);
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
          }else if(boardArray[j][i].getIcon() == g_yourmasuIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(masuIcon);
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
            boardArray[j][i].setIcon(masuIcon);
          }else if(boardArray[j][i].getIcon() == g_mymasuIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(masuIcon);
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
            boardArray[j][i].setIcon(masuIcon);
          }else if(boardArray[j][i].getIcon() == g_yourmasuIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(masuIcon);
          }else if(boardArray[j][i].getIcon() == g_myIcon){// ガイド表示部分を普通のマスに戻す
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == s_yourdoubleIcon){
            boardArray[j][i].setIcon(myIcon);                      
          }
        }
      }
    }
  }

  public static void resetBoard(ImageIcon myIcon, ImageIcon yourIcon, ImageIcon myGoalIcon, ImageIcon yourGoalIcon, int myColor){
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        boardArray[j][i].setIcon(masuIcon);
      }
    }
    setUser(myIcon, yourIcon, myColor);
    setWinArea(myGoalIcon, yourGoalIcon, myColor);
  }

  public static void setUser(ImageIcon myIcon, ImageIcon yourIcon, int myColor){
    if(myColor==0){
      for(int i=0; i<5; i++){
        boardArray[0][i].setIcon(myIcon);
        boardArray[4][i].setIcon(yourIcon);
      }
    }else{
      for(int i=0; i<5; i++){
        boardArray[0][i].setIcon(yourIcon);
        boardArray[4][i].setIcon(myIcon);
      }
    }
  }

  public static void setWinArea(ImageIcon myGoalIcon, ImageIcon yourGoalIcon, int myColor){
    if(myColor==0){
      winButtonArray[0].setIcon(myGoalIcon);
      winButtonArray[1].setIcon(yourGoalIcon);
    }else{
      winButtonArray[0].setIcon(yourGoalIcon);
      winButtonArray[1].setIcon(myGoalIcon);
    }
  }

  public static void clearBoard(){
    for(int i=0; i<5; i++){
      for(int j=0; j<5; j++){
        boardArray[j][i].setIcon(masuIcon);
        winButtonArray[0].setIcon(null);
        winButtonArray[0].setIcon(null);
      }
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
