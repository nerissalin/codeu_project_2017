// Copyright 2017 Google Inc.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package codeu.chat.client.maingui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.Border;

import codeu.chat.client.ClientContext;
import codeu.chat.client.Controller;
import codeu.chat.client.View;
import codeu.chat.util.Logger;

// Chat - top-level client application - Java Main GUI (using Java Swing)
public final class ChatMainGui {

  private final static Logger.Log LOG = Logger.newLog(ChatMainGui.class);

  private JFrame mainFrame;

  private final ClientContext clientContext;

  // Constructor - sets up the Chat Application
  public ChatMainGui(Controller controller, View view) {
    clientContext = new ClientContext(controller, view);
  }

  // Run the GUI client
  public void run() {

    try {

      initialize();
      mainFrame.setVisible(true);

    } catch (Exception ex) {
      System.out.println("ERROR: Exception in ChatMainGui.run. Check log for details.");
      LOG.error(ex, "Exception in ChatMainGui.run");
      System.exit(1);
    }
  }

  private Border paneBorder() {
    Border outside = BorderFactory.createLineBorder(Color.LIGHT_GRAY);
    Border inside = BorderFactory.createEmptyBorder(8, 8, 8, 8);
    return BorderFactory.createCompoundBorder(outside, inside);
  }

  // Initialize the GUI
  private void initialize() {

    // Outermost frame.
    // NOTE: may have tweak size, or place in scrollable panel.
    mainFrame = new JFrame("Chat");
    mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    mainFrame.setSize(790, 450);

    // Main View - outermost graphics panel.
    final JPanel mainViewPanel = new JPanel(new GridBagLayout());
    mainViewPanel.setBorder(paneBorder());
    mainViewPanel.setBackground(Color.white);

    // Build main panels - Users, Conversations, Messages.
    final MessagePanel messagesViewPanel = new MessagePanel(clientContext);
    messagesViewPanel.setBorder(paneBorder());
    final GridBagConstraints messagesViewC = new GridBagConstraints();

    // ConversationsPanel gets access to MessagesPanel
    final ConversationPanel conversationsViewPanel = new ConversationPanel(clientContext, messagesViewPanel);
    conversationsViewPanel.setBorder(paneBorder());
    final GridBagConstraints conversationViewC = new GridBagConstraints();

    final JPanel usersViewPanel = new UserPanel(clientContext, conversationsViewPanel);
    usersViewPanel.setBorder(paneBorder());
    final GridBagConstraints usersViewC = new GridBagConstraints();
    
    //Make a seperate class for this
    final JPanel titleViewPanel = new TitlePanel();
    titleViewPanel.setBorder(paneBorder());
    final GridBagConstraints titleViewC = new GridBagConstraints();

    // Placement of main panels.
    titleViewC.gridx = 0;
    titleViewC.gridy = 0;
    titleViewC.gridwidth = 4;
    titleViewC.gridheight = 1;
    titleViewC.fill = GridBagConstraints.BOTH;
    titleViewC.weightx = 0.3;
    titleViewC.weighty = 0.3;

    usersViewC.gridx = 0;
    usersViewC.gridy = 1;
    usersViewC.gridwidth = 1;
    usersViewC.gridheight = 3;
    usersViewC.fill = GridBagConstraints.BOTH;
    usersViewC.weightx = 0.3;
    usersViewC.weighty = 0.3;

    conversationViewC.gridx = 3;
    conversationViewC.gridy = 1;
    conversationViewC.gridwidth = 1;
    conversationViewC.gridheight = 3;
    conversationViewC.fill = GridBagConstraints.BOTH;
    conversationViewC.weightx = 0.7;
    conversationViewC.weighty = 0.3;

    messagesViewC.gridx = 1;
    messagesViewC.gridy = 1;
    messagesViewC.gridwidth = 2;
    messagesViewC.gridheight = 3;
    messagesViewC.fill = GridBagConstraints.BOTH;
    messagesViewC.weighty = 0.7;

    mainViewPanel.add(usersViewPanel, usersViewC);
    mainViewPanel.add(conversationsViewPanel, conversationViewC);
    mainViewPanel.add(messagesViewPanel, messagesViewC);
    mainViewPanel.add(titleViewPanel, titleViewC);

    mainFrame.add(mainViewPanel);
    mainFrame.pack();
  }
}
