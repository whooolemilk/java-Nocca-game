import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// ���y�Đ�
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

// ��ʑJ��
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class MyClient extends JFrame implements MouseListener,MouseMotionListener, ActionListener {
  // �t�B�[���h�錾
  private static JButton boardArray[][];
  JButton winButtonArray[];// �{�[�h5*5
  private JButton startButton, howToPlayButton, myAreaButton, yourAreaButton;
  private int myColor, myNumber, myTurn;
  private ImageIcon myIcon, yourIcon, character1Img, character2Img, character3Img;
  private ImageIcon mydoubleIcon, yourdoubleIcon;
  private ImageIcon s_myIcon, s_yourIcon, s_doubleIcon, s_mydoubleIcon, s_yourdoubleIcon; // �I����ԃA�C�R��
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

  // �R���X�g���N�^
	public MyClient() {
		// ���O�̓��̓_�C�A���O���J��
		String myName = JOptionPane.showInputDialog(null,"���O����͂��Ă�������","���O�̓���",JOptionPane.QUESTION_MESSAGE);
		if(myName.equals("")){
			myName = "No name";// ���O���Ȃ��Ƃ��́C"No name"�Ƃ���
		}

		String IPAddress = JOptionPane.showInputDialog(null,"IP�A�h���X����͂��Ă�������","IP�A�h���X�̓���",JOptionPane.QUESTION_MESSAGE);
		if(IPAddress.equals("")){
			IPAddress = "localhost";// ���O���Ȃ��Ƃ��́C"localhoat"�Ƃ���
		}

		// �E�B���h�E���쐬����
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
		setTitle("MyClient");// �E�B���h�E�̃^�C�g����ݒ肷��
		setSize(1024, 786);// �E�B���h�E�̃T�C�Y��ݒ肷��
  
		//c = getContentPane();// �t���[���̃y�C�����擾����
    // c = new JLayeredPane();//layer�t���[���̃y�C�����쐬
    // this.getContentPane().add(c);

    // board�̐ݒ�
    // bgImg = new ImageIcon("img/board1.png");
    // bg = new JLabel(bgImg);
    // c.add(bg);
    // bg.setBounds(250, 50,700,700);
    // c.setLayer(bg,1000);


		// �A�C�R���̐ݒ�
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

		// c.setLayout(null);// �������C�A�E�g�̐ݒ���s��Ȃ� 

    /* -----------------------scene1------------------------- */
    scene1 = new JPanel();
    JLabel title = new JLabel("�^�C�g��");
    scene1.add(title);
    scene1.setLayout(null);
    startButton = new JButton("�͂��߂�");
    howToPlayButton = new JButton("�����т���");
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

    // �K�C�h�̐���ver2
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

    // /* �J�[�h�ړ��p�{�^�� */
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



    // �T�[�o�ɐڑ�����
		Socket socket = null;
		try {
		  // "localhost"�́C���������ւ̐ڑ��Dlocalhost��ڑ����IP Address�i"133.42.155.201"�`���j�ɐݒ肷��Ƒ���PC�̃T�[�o�ƒʐM�ł���
			// 10000�̓|�[�g�ԍ��DIP Address�Őڑ�����PC�����߂āC�|�[�g�ԍ��ł���PC�㓮�삷��v���O��������肷��
			socket = new Socket(IPAddress, 10000);
		} catch (UnknownHostException e) {
			System.err.println("�z�X�g�� IP �A�h���X������ł��܂���: " + e);
		} catch (IOException e) {
			System.err.println("�G���[���������܂���: " + e);
		}

		MesgRecvThread mrt = new MesgRecvThread(socket, myName);//��M�p�̃X���b�h���쐬����
		mrt.start();// �X���b�h�𓮂����iRun�������j
	}
		
	// �����N���X�P�F���b�Z�[�W��M�̂��߂̃X���b�h
	public class MesgRecvThread extends Thread {
		Socket socket;
		String myName;
		
    // �����N���X�P�̃R���X�g���N�^
		public MesgRecvThread(Socket s, String n){
			socket = s;
			myName = n;
		}
		
	  // �����N���X�P�̃��\�b�h�F�ʐM�󋵂��Ď����C��M�f�[�^�ɂ���ē��삷��
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(myName);//�ڑ��̍ŏ��ɖ��O�𑗂�
        System.out.println(myName);
        String myNumberStr = br.readLine();
        int myNumberInt = Integer.parseInt(myNumberStr);

        if(myNumberInt % 2 != 0){
          myColor=0;// player1:red
          myNumber=111;
          winFlag=false;
          myTurn=1;// ��s
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
          myTurn=0;// ��U
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

            // // �p�X�@�\
            // if(cmd.equals("PASS")){
            //   myTurn = 1 - myTurn;
            //   theLabel2.setText("<html>�{�^���p�X�����ł��I</html>");
            //   System.out.println("�{�^���p�X����");
            //   if(myTurn==1){
            //       theLabel2.setText("<html>���Ȃ��̔Ԃł��I</html>");
            //       System.out.println("���Ȃ��̔Ԃł�");
            //     }else{
            //       theLabel2.setText("<html>����̔Ԃł��I</html>");
            //       System.out.println("����̔Ԃł�");
            //   }
            // }
            // // ���Z�b�g�@�\
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
            //       System.out.println("���Ȃ��̔Ԃ���͂��܂��");
            //     }else{
            //       System.out.println("����̔Ԃ���͂��܂��I");
            //   }
            //   System.out.println("���Z�b�g����");
            // }
              if(cmd.equals("SELECT")){
                String theBName = inputTokens[1];
                int theBnum = Integer.parseInt(theBName);
                int x = theBnum % 5;
                int y = theBnum / 5;
                int theColor = Integer.parseInt(inputTokens[2]);
                Icon theIcon = boardArray[y][x].getIcon();

                if(theColor == myColor){// �����N���b�N�����A�C�R���̎�ނ����Ԃ񂾂�����
                  clearSelectedIcon(theColor);// �����̑I�𕔕��ƃK�C�h������
                  if(theIcon == myIcon){// ���ɁA�N���b�N�����A�C�R�����R�}��������
                    boardArray[y][x].setIcon(s_myIcon);// ���̃N���b�N�����A�C�R����I����Ԃ̃A�C�R���ɕύX
                    showGuide(x, y, theColor);// �K�C�h�\��         
                  }else if(theIcon == mydoubleIcon){
                    boardArray[y][x].setIcon(s_mydoubleIcon);// ���̃N���b�N�����A�C�R����I����Ԃ̃A�C�R���ɕύX
                    showGuide(x, y, theColor);// �K�C�h�\��
                  }else if(theIcon==s_myIcon){// �I����Ԃ���������
                    boardArray[y][x].setIcon(myIcon);
                  }else if(theIcon==s_mydoubleIcon){// �I����Ԃ���������
                    boardArray[y][x].setIcon(mydoubleIcon);
                  }
                }else{// �����N���b�N�����A�C�R���̎�ނ������Ă�������
                  clearSelectedIcon(theColor);// �����̑I�𕔕��ƃK�C�h������
                  if(theIcon == yourIcon){
                    boardArray[y][x].setIcon(s_yourIcon);
                    showGuide(x, y, theColor);// �K�C�h�\��   
                  }else if(theIcon == yourdoubleIcon){
                    boardArray[y][x].setIcon(s_yourdoubleIcon);// �I����Ԃ̃A�C�R���ɕύX
                    showGuide(x, y, theColor);// �K�C�h�\��   
                  }else if(theIcon==s_yourIcon){// �I����Ԃ���������
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
                      System.out.println("����");
                      winButtonArray[theBnum].setIcon(myIcon);
                    }else{
                      System.out.println("����");
                      winButtonArray[theBnum].setIcon(yourIcon);
                    }
                  }else{
                    System.out.println("�u�����Ƃ��o���܂���");
                  }

                  
                  clearSelectedIcon(theColor);

              }
					}else{
						break;
					}
				}
				socket.close();
			} catch (IOException e) {
				System.err.println("�G���[���������܂���: " + e);
			}
		}
	}

  // ���\�b�h
	public static void main(String[] args) throws InterruptedException{
		MyClient net = new MyClient();
    // TitleView ti = new TitleView();
		net.setVisible(true);
    // Thread.sleep(1000);
    // net.change(ti);
	}

  // ���\�b�h
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

  // ���\�b�h
	public void mouseClicked(MouseEvent e) {
    System.out.println("�N���b�N");
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
      // �R�}�̑I����Ԃ�����
      if(theIcon == myIcon || theIcon == s_myIcon || theIcon == mydoubleIcon || theIcon == s_mydoubleIcon){
        String msg = "SELECT"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
      // �R�}��u��
      if(theIcon == guideIcon || theIcon == g_yourIcon){
        String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
        out.println(msg);
        out.flush();
      }
    }    

  repaint();//��ʂ̃I�u�W�F�N�g��`�悵����
	}
	
	public void mouseEntered(MouseEvent e) {
    // System.out.println("�z�o�[");
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
    // System.out.println("�z�o�[�����");
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

  // ���W��8�~8�̃}�X�ڂ𒴂��Ă��邩�ǂ����𔻒肷��֐�
  public boolean isExceededArea(int posY, int posX){
    return posX < 0 || posY < 0 || posX > 4 || posY > 4;
  }

  // �K�C�h�̕\��
  public void showGuide(int x, int y, int theColor){
    for (int j=-1;j<2;j++){
      for (int i=-1;i<2;i++){
        int posX = x + i;
        int posY = y + j;

        if(isExceededArea(posX, posY)){
          continue;// true�Ȃ�ȍ~�̏������X�L�b�v
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
  
  // �����̑I�𕔕���K�C�h����������
  public void clearSelectedIcon(int theColor){
    if(theColor==myColor){
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_myIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_yourIcon){// �K�C�h��Ԃ̑���̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == s_mydoubleIcon){
            boardArray[j][i].setIcon(mydoubleIcon);
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_yourIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_myIcon){// �K�C�h��Ԃ̑���̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(myIcon);
          }else if(boardArray[j][i].getIcon() == s_yourdoubleIcon){
            boardArray[j][i].setIcon(yourdoubleIcon);
          }
        }
      }
    }
  }

  // �����̑I�𕔕���K�C�h����������+placed�A�C�R�������ǂ�������
  public void clearPlacedIcon(int theColor){
    if(theColor==myColor){
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon() == s_myIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_yourIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            boardArray[j][i].setIcon(yourIcon);
          }else if(boardArray[j][i].getIcon() == s_mydoubleIcon){
            boardArray[j][i].setIcon(yourIcon);                      
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(boardArray[j][i].getIcon()==s_yourIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            boardArray[j][i].setIcon(orangeIcon);
          }else if(boardArray[j][i].getIcon() == g_myIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
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
  
  // �����N���X�Q�F���y�Đ�
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
          long time = (long)clip.getFrameLength();//44100�Ŋ���ƍĐ����ԁi�b�j���ł�
          long endTime = System.currentTimeMillis()+time*1000/44100;
          clip.start();
          while(true){
            if(stopFlag){//stopFlag��true�ɂȂ����I��
              clip.stop();
              return;
            }
            if(endTime < System.currentTimeMillis()){//�Ȃ̒������߂�����I��
              if(loopFlag) {
                clip.loop(1);//�������[�v�ƂȂ�
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

//�_�C�A���O�̂��߂̃N���X
//�G���N���b�N���������悤�ɂ��Ă���
class WinDialogWindow extends JDialog implements ActionListener{
    WinDialogWindow(JFrame owner) {
        super(owner);//�Ăяo�����ƂƂ̐e�q�֌W�̐ݒ�D������R�����g�A�E�g����ƕʁX�̃_�C�A���O�ɂȂ�

		    Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�
        c.setBackground(Color.green);

        setTitle("You Win!");//�^�C�g���̐ݒ�
        setSize(800, 500);//�傫���̐ݒ�
        setResizable(false);//�g��k���֎~//true�ɂ���Ɗg��k���ł���悤�ɂȂ�
        setUndecorated(true); //�^�C�g����\�����Ȃ�
        setModal(true);//������܂ŉ���G��Ȃ�����ifalse�ɂ���ƐG���j

        JButton rePlayButton = new JButton("������������");
        JButton endButton = new JButton("�^�C�g���ɖ߂�");
        c.add(rePlayButton);
        c.add(endButton);
        rePlayButton.addActionListener(this);
        rePlayButton.setActionCommand("REPLAY");
        endButton.addActionListener(this);
        endButton.setActionCommand("END");

        rePlayButton.setBounds(250, 200, 300, 100);
        endButton.setBounds(250, 350, 300, 100);
        //�_�C�A���O�̑傫����\���ꏊ��ύX�ł���
        //�e�̃_�C�A���O�̒��S�ɕ\���������ꍇ�́C�e�̃E�B���h�E�̒��S���W�����߂āC�q�̃_�C�A���O�̑傫���̔������炷
        setLocation(owner.getBounds().x+owner.getWidth()/2-this.getWidth()/2,owner.getBounds().y+owner.getHeight()/2-this.getHeight()/2);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
      String cmd = e.getActionCommand();
      if (cmd.equals("REPLAY")){
        //layout.first(scenePanel);
        this.dispose();//Dialog��p������
        MyClient.resetBoard();
      }else if (cmd.equals("END")){
        MyClient.layout.first(MyClient.scenePanel);
        this.dispose();//Dialog��p������
      }
    }
}