package codeu.chat.client.maingui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import codeu.chat.client.ClientContext;
import codeu.chat.common.ConversationSummary;
import codeu.chat.common.Message;
import codeu.chat.common.User;

// NOTE: JPanel is serializable, but there is no need to serialize MessagePanel
// without the @SuppressWarnings, the compiler will complain of no override for serialVersionUID
@SuppressWarnings("serial")
public final class TitlePanel extends JPanel {

  private final JLabel titleLabel = new JLabel("Chat", JLabel.CENTER);

  public TitlePanel() {
    super(new GridBagLayout());
    initialize();
  }

  private void initialize() {

    // Title bar - Aesthetic
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleLabel.setForeground(Color.decode("#EFF5F7"));
    titleLabel.setFont(new Font("Hoefler Text", Font.BOLD, 36));
    
    this.setBackground(Color.decode("#2A96A5"));
    this.add(titleLabel);
  }
  
}
