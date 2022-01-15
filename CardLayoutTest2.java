import javax.swing.*;
import java.awt.CardLayout;
import java.awt.BorderLayout;
import java.awt.event.*;

public class CardLayoutTest2 extends JFrame implements ActionListener{

  JPanel cardPanel;
  CardLayout layout;

  public static void main(String[] args){
    CardLayoutTest2 frame = new CardLayoutTest2();

    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(10, 10, 300, 200);
    frame.setTitle("タイトル");
    frame.setVisible(true);
  }

  CardLayoutTest2(){
    /* カード1 */
    JPanel card1 = new JPanel();
    card1.add(new JButton("button"));

    /* カード2 */
    JPanel card2 = new JPanel();
    card2.add(new JLabel("label"));
    card2.add(new JTextField("", 10));

    /* カード3 */
    JPanel card3 = new JPanel();
    card3.add(new JCheckBox("checkbox1"));
    card3.add(new JCheckBox("checkbox2"));

    cardPanel = new JPanel();
    layout = new CardLayout();
    cardPanel.setLayout(layout);

    cardPanel.add(card1, "button");
    cardPanel.add(card2, "label");
    cardPanel.add(card3, "checkbox");

    /* カード移動用ボタン */
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
  }

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
}