import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class pictureCardSample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    ImageIcon[] icons;
    JLabel theLabel1;
    JPanel p = new JPanel();//Container�ł������`��@�\���g���Ȃ��̂�JPanel���g���D�g�����͂قƂ��Container�Ɠ���

    public pictureCardSample() {
        //�E�B���h�E���쐬����
        this.add(p,BorderLayout.CENTER);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
        this.setTitle("pictureCardSample");		//�E�B���h�E�̃^�C�g����ݒ肷��
        this.setSize(300,300);		//�E�B���h�E�̃T�C�Y��ݒ肷��
        p.setLayout(null);//�������C�A�E�g�̐ݒ���s��Ȃ�
        p.setSize(300,300);//�p�l���̃T�C�Y�̓E�B���h�E�ɍ��킹��
        //Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        //c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�
        p.setBackground(Color.CYAN);//�E�B���h�E�̐F�̐ݒ�

        //�A�C�R���̐���
        icons = new ImageIcon[10];
        //�A�C�R���̓ǂݍ���
        for(int i=0; i<10 ; i++){
            icons[i] = new ImageIcon("number_"+i+".png");//number_0.png����number_9.png��p��
        }

        //�摜�̕\��
        theLabel1 = new JLabel(icons[0]);//�{�^�����쐬
        p.add(theLabel1);//�y�C���ɓ\��t����
        theLabel1.setBounds(100,80,icons[0].getIconWidth(),icons[0].getIconHeight());//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theLabel1.addMouseListener(this);//���x�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theLabel1.setForeground(Color.WHITE); //�����F�̐ݒ�DColor�̐ݒ�́C���̃y�[�W�����ĉ������@http://www.javadrive.jp/tutorial/color/

        //�{�^���̕\��
        JButton theButton = new JButton("�J�E���g�_�E��");//�{�^�����쐬�C�e�L�X�g�̕\��
        p.add(theButton);//�y�C���ɓ\��t����
        theButton.setBounds(70,250,160,25);//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton.setActionCommand("StartDown");
        theButton.addActionListener(this);
    }

    public static void main(String[] args) {
        pictureCardSample gui = new pictureCardSample();
        gui.setVisible(true);
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("�}�E�X���N���b�N����");
    }

    public void mousePressed(MouseEvent e) {
        System.out.println("�}�E�X��������");
    }

    public void mouseReleased(MouseEvent e) {
        System.out.println("�}�E�X�������");
    }

    public void mouseEntered(MouseEvent e) {
        System.out.println("�}�E�X��������");
    }

    public void mouseExited(MouseEvent e) {
        System.out.println("�}�E�X�E�o");
    }

    public void mouseDragged(MouseEvent e) {
        System.out.println("�}�E�X���h���b�O");
    }

    public void mouseMoved(MouseEvent e) {
        System.out.println("�}�E�X�ړ�");
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("�A�N�V��������");
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
                try{//Thread.sleep��Try-catch�łȂ��Ƃ����Ȃ�����
                    Thread.sleep(1000); //1000�~���bSleep����
                }catch(InterruptedException ee){}//�G���[���łĂ��Ȃɂ����Ȃ�
            }
        }
    }
}
