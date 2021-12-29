import javax.swing.*;
import java.lang.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;//�摜�����ɕK�v
import java.awt.geom.*;//�摜�����ɕK�v

public class ImageRotateSample extends JFrame implements MouseListener,MouseMotionListener,ActionListener{
    public ImageRotateSample() {
        //�E�B���h�E���쐬����
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�E�B���h�E�����Ƃ��ɁC����������悤�ɐݒ肷��
        this.setTitle("ImageRotateSample");		//�E�B���h�E�̃^�C�g����ݒ肷��
        this.setSize(600,600);		//�E�B���h�E�̃T�C�Y��ݒ肷��
        Container c = this.getContentPane();	//�t���[���̃y�C�����擾����
        c.setLayout(null);		//�������C�A�E�g�̐ݒ���s��Ȃ�
        c.setBackground(Color.CYAN);//�E�B���h�E�̐F�̐ݒ�

        //�A�C�R���̐���
        Icon icon1 = new ImageIcon("image1.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        //RotateIcon���g���ƃN���b�N�����Ƃ��ɔ����͖����Ȃ�܂�

        //0���̉摜
        JButton theButton1 = new JButton(icon1);//�{�^�����쐬
        c.add(theButton1);//�y�C���ɓ\��t����
        theButton1.setBounds(45,45,icon1.getIconWidth(),icon1.getIconHeight());//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton1.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        // Icon icon1_1 = new ImageIcon("image2.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        // theButton1.setPressedIcon(icon1_1);//�����ꂽ�Ƃ��ɕ\�������A�C�R��
        // System.out.println("getIconWidth="+icon1.getIconWidth());//��]���Icon�̕��𓾂���@
        // System.out.println("getIconHeight="+icon1.getIconHeight());//��]���Icon�̍����𓾂���@
        // Icon icon1_2 = new ImageIcon("image3.png");//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        // theButton1.setRolloverIcon(icon1_2);//�}�E�X����ɏd�Ȃ����Ƃ��ɕ\�������A�C�R��

        //90����]�̉摜
        Icon icon2 = (Icon) new RotateIcon(icon1,145);//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JButton theButton2 = new JButton(icon2);//�{�^�����쐬
        c.add(theButton2);//�y�C���ɓ\��t����
        theButton2.setBounds(300,45,icon2.getIconWidth(),icon2.getIconHeight());//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton2.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        System.out.println("getIconWidth="+icon2.getIconWidth());//��]���Icon�̕��𓾂���@
        System.out.println("getIconHeight="+icon2.getIconHeight());//��]���Icon�̍����𓾂���@

        //180����]�̉摜
        Icon icon3 = (Icon) new RotateIcon(icon1,180);//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JButton theButton3 = new JButton(icon3);//�{�^�����쐬
        c.add(theButton3);//�y�C���ɓ\��t����
        theButton3.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theButton3.setBounds(45,300,icon3.getIconWidth(),icon3.getIconHeight());//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton3.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���

        //270����]�̉摜
        Icon icon4 = (Icon) new RotateIcon(icon1,270);//�Ȃɂ��摜�t�@�C�����_�E�����[�h���Ă���
        JButton theButton4 = new JButton(icon4);//�{�^�����쐬
        c.add(theButton4);//�y�C���ɓ\��t����
        theButton4.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
        theButton4.setBounds(300,300,icon4.getIconWidth(),icon4.getIconHeight());//�{�^���̑傫���ƈʒu��ݒ肷��D(x���W�Cy���W,x�̕�,y�̕��j
        theButton4.addMouseListener(this);//�{�^�����}�E�X�ł�������Ƃ��ɔ�������悤�ɂ���
    }

    public static void main(String[] args) {
        ImageRotateSample gui = new ImageRotateSample();
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
    }

    //�A�C�R���̉�]�̃N���X
    //���L��URL�̃R�[�h�𗘗p
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
