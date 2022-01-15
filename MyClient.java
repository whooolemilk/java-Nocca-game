import java.net.*;
import java.io.*;
import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

// ���y�Đ����ɕK�v
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

// aa
import java.awt.CardLayout;
import java.awt.BorderLayout;

public class MyClient extends JFrame implements MouseListener,MouseMotionListener, ActionListener {
	private JButton buttonArray[][];
  private JButton passButton;
  private JButton resetButton;
  private int myColor, myNumber;
  private int myTurn;
  private ImageIcon myIcon, yourIcon, mydoubleIcon, yourdoubleIcon, s_myIcon, s_yourIcon, s_doubleIcon, g_myIcon, g_yourIcon, s_mydoubleIcon, s_yourdoubleIcon;
	// private Container c;
  private JLayeredPane c, card2;
  private boolean mySelect;
	// private ImageIcon blackIcon, whiteIcon, boardIcon;
  private ImageIcon redIcon, blueIcon, orangeIcon, catIcon, rbIcon, brIcon, s_rbIcon, s_brIcon, s_redIcon, s_blueIcon, s_orangeIcon, s_catIcon, bgImg, guideIcon,g_redIcon, g_blueIcon;
	PrintWriter out;
  JLabel theLabel1;
  JLabel theLabel2;
  JLabel theLabel3;
  JLabel bg;
  JPanel card1,card3;
  private JButton guideArray[][];
  private JButton myArray[];
  private JButton yourArray[];
  //JLayeredPane layerPane;

  SoundPlayer theSoundPlayer2;
  
  JPanel cardPanel;
  CardLayout layout;

  int boardIconCount = 0;
  int whiteIconCount = 0;
  int blackIconCount = 0;  

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
		setSize(1200, 850);// �E�B���h�E�̃T�C�Y��ݒ肷��

  
		//c = getContentPane();// �t���[���̃y�C�����擾����
    c = new JLayeredPane(); 
    this.getContentPane().add(c);
    c.setBackground(Color.RED);// �E�B���h�E�̐F�̐ݒ�

    // board�̐ݒ�
    bgImg = new ImageIcon("img/board1.png");
    bg = new JLabel(bgImg);
    c.add(bg);
    bg.setBounds(250, 50,700,700);
    c.setLayer(bg,1000);


		// �A�C�R���̐ݒ�
    redIcon = new ImageIcon("img/zibun1.png");
    blueIcon = new ImageIcon("img/aite1.png");
    orangeIcon = new ImageIcon("img/masu1.png");
    guideIcon = new ImageIcon("img/gaido1.png");
    catIcon = new ImageIcon("img/cat21.png");
    rbIcon = new ImageIcon("img/akaue1.png");
    brIcon = new ImageIcon("img/aoue1.png");


    s_redIcon = new ImageIcon("img/s-zibun1.png");
    s_blueIcon = new ImageIcon("img/s-aite1.png");
    s_orangeIcon = new ImageIcon("img/s-masu1.png");
    s_catIcon = new ImageIcon("img/s-cat21.png");    
    s_rbIcon = new ImageIcon("img/s-akaue1.png");
    s_brIcon = new ImageIcon("img/s-aoue1.png");

    g_redIcon = new ImageIcon("img/g-zibun1.png");
    g_blueIcon = new ImageIcon("img/g-aite1.png");

		c.setLayout(null);// �������C�A�E�g�̐ݒ���s��Ȃ� 

    /* �J�[�h1 */
    card1 = new JPanel();
    card1.add(new JLabel("�^�C�g��"));

    // bgImg = new ImageIcon("img/board1.png");
    // bg = new JLabel(bgImg);
    // card1.add(bg);
    // bg.setBounds(250, 50,700,700);
    // card1.setLayer(bg,1000);

    /* �J�[�h2 */
    
    card2 = new JLayeredPane();
    card2.add(new JLabel("label"));
    card2.add(new JTextField("", 10));

    // �K�C�h�̐���ver2
		guideArray = new JButton[5][5];
		for(int j=0;j<5;j++){
      for(int i=0; i<5; i++){
        guideArray[j][i] = new JButton(orangeIcon);
        card2.add(guideArray[j][i]);
        guideArray[j][i].setBounds(i*70+530-70*j,j*70+5+70*i,140,140);
        guideArray[j][i].addMouseListener(this);
        card2.setLayer(guideArray[j][i], j*5+i+1);
        guideArray[j][i].setActionCommand(Integer.toString(j*5+i));
        //guideArray[j][i].setContentAreaFilled(false);
        //guideArray[j][i].setBorderPainted(false);
      }
		}

    JButton myAreaButton = new JButton(rbIcon);
    card2.add(myAreaButton);
    myAreaButton.setBounds(740,90,140,140);
    myAreaButton.addMouseListener(this);
    myAreaButton.setActionCommand("111");
    myAreaButton.setContentAreaFilled(false);
    myAreaButton.setBorderPainted(false);    

    JButton yourAreaButton = new JButton(brIcon);
    card2.add(yourAreaButton);
    yourAreaButton.setBounds(250,500,140,140);
    yourAreaButton.addMouseListener(this);
    yourAreaButton.setActionCommand("222");
    yourAreaButton.setContentAreaFilled(false);
    yourAreaButton.setBorderPainted(false); 

    guideArray[0][1].setIcon(redIcon);
    guideArray[0][2].setIcon(redIcon);
    guideArray[0][3].setIcon(redIcon);
    guideArray[0][4].setIcon(redIcon);

    guideArray[4][1].setIcon(blueIcon);
    guideArray[4][2].setIcon(blueIcon);
    guideArray[4][3].setIcon(blueIcon);
    guideArray[4][4].setIcon(blueIcon);

    /* �J�[�h3 */
    card3 = new JPanel();
    card3.add(new JCheckBox("checkbox1"));
    card3.add(new JCheckBox("checkbox2"));

    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);

    cardPanel.add(card1, "button");
    cardPanel.add(card2, "label");
    cardPanel.add(card3, "checkbox");

    /* �J�[�h�ړ��p�{�^�� */
    JButton firstButton = new JButton("First");
    firstButton.addActionListener(this);
    firstButton.setActionCommand("First");

    JButton prevButton = new JButton("Prev");
    prevButton.addActionListener(this);
    prevButton.setActionCommand("Prev");

    JButton nextButton = new JButton("Next");
    nextButton.addActionListener(this);
    nextButton.setActionCommand("Next");

    JButton lastButton = new JButton("Last");
    lastButton.addActionListener(this);
    lastButton.setActionCommand("Last");

    JPanel btnPanel = new JPanel();
    btnPanel.add(firstButton);
    btnPanel.add(prevButton);
    btnPanel.add(nextButton);
    btnPanel.add(lastButton);

    getContentPane().add(cardPanel, BorderLayout.CENTER);
    getContentPane().add(btnPanel, BorderLayout.PAGE_END);



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
		
	// ���b�Z�[�W��M�̂��߂̃X���b�h
	public class MesgRecvThread extends Thread {
		Socket socket;
		String myName;
		
		public MesgRecvThread(Socket s, String n){
			socket = s;
			myName = n;
		}
		
	  // �ʐM�󋵂��Ď����C��M�f�[�^�ɂ���ē��삷��
		public void run() {
			try{
				InputStreamReader sisr = new InputStreamReader(socket.getInputStream());
				BufferedReader br = new BufferedReader(sisr);
				out = new PrintWriter(socket.getOutputStream(), true);
				out.println(myName);//�ڑ��̍ŏ��ɖ��O�𑗂�
        String myNumberStr = br.readLine();
        int myNumberInt = Integer.parseInt(myNumberStr);

        if(myNumberInt % 2 != 0){
          myColor=0;// player1:red
          myNumber=111;
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
              Icon theIcon = guideArray[y][x].getIcon();

              if(theColor == myColor){// �����N���b�N�����A�C�R���̎�ނ����Ԃ񂾂�����
                clearSelectedIcon(theColor);// �����̑I�𕔕��ƃK�C�h������
                if(theIcon == myIcon){// ���ɁA�N���b�N�����A�C�R�����R�}��������
                  guideArray[y][x].setIcon(s_myIcon);// ���̃N���b�N�����A�C�R����I����Ԃ̃A�C�R���ɕύX
                  showGuide(x, y, theColor);// �K�C�h�\��         
                }else if(theIcon == mydoubleIcon){
                  guideArray[y][x].setIcon(s_mydoubleIcon);// ���̃N���b�N�����A�C�R����I����Ԃ̃A�C�R���ɕύX
                  showGuide(x, y, theColor);// �K�C�h�\��
                }else if(theIcon==s_myIcon){// �I����Ԃ���������
                  guideArray[y][x].setIcon(myIcon);
                }else if(theIcon==s_mydoubleIcon){// �I����Ԃ���������
                  guideArray[y][x].setIcon(mydoubleIcon);
                }
              }else{// �����N���b�N�����A�C�R���̎�ނ������Ă�������
                clearSelectedIcon(theColor);// �����̑I�𕔕��ƃK�C�h������
                if(theIcon == yourIcon){
                  guideArray[y][x].setIcon(s_yourIcon);
                  showGuide(x, y, theColor);// �K�C�h�\��   
                }else if(theIcon == yourdoubleIcon){
                  guideArray[y][x].setIcon(s_yourdoubleIcon);// �I����Ԃ̃A�C�R���ɕύX
                  showGuide(x, y, theColor);// �K�C�h�\��   
                }else if(theIcon==s_yourIcon){// �I����Ԃ���������
                  guideArray[y][x].setIcon(yourIcon);
                }else if(theIcon==s_yourdoubleIcon){
                  guideArray[y][x].setIcon(yourdoubleIcon);
                }              
              }
            }

            if(cmd.equals("PLACE")){
              String theBName = inputTokens[1];
              int theBnum = Integer.parseInt(theBName);
              int x = theBnum % 5;
              int y = theBnum / 5;
              int theColor = Integer.parseInt(inputTokens[2]);
              Icon theIcon = guideArray[y][x].getIcon();

              if(theColor==myColor){              
                clearPlacedIcon(theColor);
                if(theIcon==guideIcon){
                  guideArray[y][x].setIcon(myIcon);
                }else if(theIcon==g_yourIcon){
                  guideArray[y][x].setIcon(mydoubleIcon);
                }
              }else{
                clearPlacedIcon(theColor);
                if(theIcon==guideIcon){
                  guideArray[y][x].setIcon(yourIcon);
                }else if(theIcon==g_myIcon){
                  guideArray[y][x].setIcon(yourdoubleIcon);
                }
              }

            }

            // // FLIP�@�\
            // if(cmd.equals("FLIP")){
            //   String theBName = inputTokens[1];//�{�^���̖��O�i�ԍ��j�̎擾
            //   int theBnum = Integer.parseInt(theBName);//�{�^���̖��O�𐔒l�ɕϊ�����
            //   int i = theBnum / 8;
            //   int j = theBnum % 8;
            //   int theColor = Integer.parseInt(inputTokens[2]);//���l�ɕϊ�����
            //   if(theColor==myColor){
            //     buttonArray[i][j].setIcon(myIcon);//blackIcon�ɐݒ肷��
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
				System.err.println("�G���[���������܂���: " + e);
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{
		MyClient net = new MyClient();
    // TitleView ti = new TitleView();
		net.setVisible(true);
    // Thread.sleep(1000);
    // net.change(ti);
	}

  
  // //��ʐ؂�ւ��p���\�b�h
	// public void change(JPanel panel) {
	// 	//ContentPane�ɂ͂ߍ��܂ꂽ�p�l�����폜
    
	// 	getContentPane().removeAll();
	// 	getContentPane().add(panel);//�p�l���̒ǉ�
	// 	validate();//�X�V
	// 	repaint();//�ĕ`��
	// }

  // class TitleView extends JPanel{
  //   // �E�B���h�E���쐬����
	// 	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
	// 	setTitle("MyClient");// �E�B���h�E�̃^�C�g����ݒ肷��
	// 	setSize(1200, 850);// �E�B���h�E�̃T�C�Y��ݒ肷��

  
	// 	// c = getContentPane();// �t���[���̃y�C�����擾����
  //   c = new JLayeredPane(); 
  //   c.setBackground(Color.RED);
  // }

  public void actionPerformed(ActionEvent e){
    String cmd = e.getActionCommand();

    if (cmd.equals("First")){
      layout.first(cardPanel);
    }else if (cmd.equals("Last")){
      layout.last(cardPanel);
    }else if (cmd.equals("Next")){
      layout.next(cardPanel);
    }else if (cmd.equals("Prev")){
      layout.previous(cardPanel);
    }
  }
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
      //   //�u����
      //   System.out.println("���s");
      //   theSoundPlayer2 = new SoundPlayer("443_2.wav");
      //   String msg = "PLACE"+" "+theArrayIndex+" "+myColor;
      //   out.println(msg);
      //   out.flush();
      // } else {
      //   //�u���Ȃ�
      //   System.out.println("�����ɂ͔z�u�ł��܂���");
      // }
    }
  repaint();//��ʂ̃I�u�W�F�N�g��`�悵����
  // }
	}
	
	public void mouseEntered(MouseEvent e) {
    System.out.println("�z�o�[");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    // if (theIcon == myIcon){
    //   guideArray[y][x].setIcon(catIcon);
    // }
    card2.setLayer(guideArray[y][x],1000);

	}
	
	public void mouseExited(MouseEvent e) {
    System.out.println("�z�o�[�����");
    JButton theButton = (JButton)e.getComponent();
    String theArrayIndex = theButton.getActionCommand();
    Icon theIcon = theButton.getIcon();

    int temp = Integer.parseInt(theArrayIndex);
    int x = temp % 5;
    int y = temp / 5;

    System.out.println("x="+x);
    System.out.println("y="+y);

    //guideArray[y][x].setIcon(orangeIcon);
    card2.setLayer(guideArray[y][x],y*5+x+1);
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

        Icon theIcon2 = guideArray[posY][posX].getIcon();
        if(theIcon2 == orangeIcon){
          guideArray[posY][posX].setIcon(guideIcon);
        }else if(theColor==myColor){
          if(theIcon2 == yourIcon){
            guideArray[posY][posX].setIcon(g_yourIcon);
          }
        }else if(theColor!=myColor){
          if(theIcon2 == myIcon){
            guideArray[posY][posX].setIcon(g_myIcon);
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
          if(guideArray[j][i].getIcon() == s_myIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(myIcon);
          }else if(guideArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == g_yourIcon){// �K�C�h��Ԃ̑���̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(yourIcon);
          }else if(guideArray[j][i].getIcon() == s_mydoubleIcon){
            guideArray[j][i].setIcon(mydoubleIcon);
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(guideArray[j][i].getIcon() == s_yourIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(yourIcon);
          }else if(guideArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == g_myIcon){// �K�C�h��Ԃ̑���̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(myIcon);
          }else if(guideArray[j][i].getIcon() == s_yourdoubleIcon){
            guideArray[j][i].setIcon(yourdoubleIcon);
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
          if(guideArray[j][i].getIcon() == s_myIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == g_yourIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(yourIcon);
          }else if(guideArray[j][i].getIcon() == s_mydoubleIcon){
            guideArray[j][i].setIcon(yourIcon);                      
          }
        }
      }
    }else{
      for(int j=0;j<5;j++){// �܂��A���̑I�𕔕�������Ή�������
        for(int i=0; i<5; i++){
          if(guideArray[j][i].getIcon()==s_yourIcon){// �I����Ԃ̎����̃A�C�R�������Ƃɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == guideIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(orangeIcon);
          }else if(guideArray[j][i].getIcon() == g_myIcon){// �K�C�h�\�������𕁒ʂ̃}�X�ɖ߂�
            guideArray[j][i].setIcon(myIcon);
          }else if(guideArray[j][i].getIcon() == s_yourdoubleIcon){
            guideArray[j][i].setIcon(myIcon);                      
          }
        }
      }
    }
  }


  // �u����Ֆʂ��ǂ����𔻒肷��֐�
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
  //           //�{�^���̈ʒu�������
  //           int msgy = y + dy;
  //           int msgx = x + dx;
  //           int theArrayIndex = msgy*8 + msgx;
  //           //�T�[�o�ɏ��𑗂�
  //           String msg = "FLIP"+" "+theArrayIndex+" "+myColor;
  //           out.println(msg);
  //           out.flush();
  //         }
  //       }        
  //     }
  //   }
  //   return flag;
  // }

  // �Ђ�����Ԃ����Ƃ̂ł���Ֆʂ̌���Ԃ��֐�
  // ����s�Ő�����A�C�R���̎�ނ�ύX�Atrue�Ȃ�yourIcon�Afalse�Ȃ�myIcon�𐔂���
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



  // // ���A���A�{�[�h�A���ꂼ��̐��𐔂���֐�
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

  // // ���s������s���A�e�L�X�g�\������֐�
  // public void winJudge(){
  //   if(whiteIconCount==blackIconCount){
  //     theLabel2.setText("<html>�Ђ��킯����I</html>");
  //     System.out.println("�Ђ��킯");
  //   }else if(whiteIconCount>blackIconCount){
  //     if(myIcon == whiteIcon){
  //       theLabel2.setText("<html>�����I���߂łƂ��I</html>");
  //       System.out.println("����");
  //     }else{
  //       theLabel2.setText("<html>�����I�c�O�I</html>");
  //       System.out.println("����");
  //     }
  //   }else{
  //     if(myIcon == blackIcon){
  //       theLabel2.setText("<html>�����I���߂łƂ��I</html>");
  //       System.out.println("����");
  //     }else{
  //       theLabel2.setText("<html>�����I�c�O�I</html>");
  //       System.out.println("����");
  //     }
  //   }
  // }


  // // �p�X�𔻒�Ɏg���֐�
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

  // // �Q�[���̏I���𔻒肷�邽�߂̃J�E���g�֐�
  // // 0�Ȃ�Q�[���I��
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

  // // �p�X�ł��邩���肷�邽�߂̃J�E���g�֐�
  // // �O�Ȃ�ς��A����ȊO�Ȃ玟�̐l��
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
  
  // ���y�Đ�
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
