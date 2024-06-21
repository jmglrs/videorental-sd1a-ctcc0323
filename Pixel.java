package swingexamples;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

public class Pixel {

    // Temporary database to store account details
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private static ArrayList<RentalRecord> rentalHistory = new ArrayList<>();
    private static HashMap<Integer, String> movieTitles = new HashMap<>();
    private static JFrame rentalFrame;
    private static JFrame frame;

    static {
        movieTitles.put(1, "Forest");
        movieTitles.put(2, "The Reclamation");
        movieTitles.put(3, "The Dark Wizard");
    }
    

    public static void main(String[] args) {
        // Create a frame with a title
        frame = new JFrame("Pixel Flix");

        // Set the default close operation of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the default size of the frame
        frame.setSize(800, 600);

        // Optionally, set the minimum size of the frame
        frame.setMinimumSize(new Dimension(400, 300));
        
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);

        // Load and set the custom logo as the frame icon
        try {
            Image icon = ImageIO.read(new File("official_logo.png"));
            frame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom panel to hold the input dialog
        BackgroundPanel panel = new BackgroundPanel();
        panel.setLayout(new GridBagLayout());

        frame.add(panel);

        // Create buttons for the options
        JButton createAccountButton = new JButton("Create Account");
        JButton mainLoginButton = new JButton("Log in");
        JButton exitButton = new JButton("Exit");
        

        // Set the background and text color for the buttons
        Color buttonBackgroundColor = new Color(139, 69, 19); // Brown
        Color buttonTextColor = Color.WHITE;
        
        createAccountButton.setBackground(buttonBackgroundColor);
        createAccountButton.setForeground(buttonTextColor);

        mainLoginButton.setBackground(buttonBackgroundColor);
        mainLoginButton.setForeground(buttonTextColor);

        exitButton.setBackground(buttonBackgroundColor);
        exitButton.setForeground(buttonTextColor);

        // Set the preferred size of the buttons
        createAccountButton.setPreferredSize(new Dimension(250, 60));
        mainLoginButton.setPreferredSize(new Dimension(250, 60));
        exitButton.setPreferredSize(new Dimension(250, 60));

        // Create a GridBagConstraints instance to specify constraints for each component
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(createAccountButton, gbc);

        gbc.gridy = 1; // Move to next row
        panel.add(mainLoginButton, gbc);

        gbc.gridy = 2; // Move to next row
        panel.add(exitButton, gbc);

        // Create the account creation frame
        final JFrame createAccountFrame = new JFrame("Create Account - Pixel Flix");

        // Set the default close operation of the frame
        createAccountFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the default size of the frame
        createAccountFrame.setSize(800, 600);

        // Optionally, set the minimum size of the frame
        createAccountFrame.setMinimumSize(new Dimension(400, 300));

        // Set the same icon for the create account frame
        try {
            Image icon = ImageIO.read(new File("official_logo.png"));
            createAccountFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom panel for the create account frame
        BackgroundPanel createAccountPanel = new BackgroundPanel();
        createAccountPanel.setLayout(new GridBagLayout());

        createAccountFrame.add(createAccountPanel);
        
        Dimension screenSize1 = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX1 = (int) ((screenSize1.getWidth() - createAccountFrame.getWidth()) / 2);
        int centerY1 = (int) ((screenSize1.getHeight() - createAccountFrame.getHeight()) / 2);
        createAccountFrame.setLocation(centerX1, centerY1);

        // Create text fields for username and password
        final JTextField usernameField = new JTextField(20);
        final JPasswordField passwordField = new JPasswordField(20);

        // Create a button to submit the account creation
     // Create a button to submit the account creation
        JButton submitButton = new JButton("Submit");

        // Set the background and text color for the submit button
        Color submitButtonBackgroundColor = new Color(139, 69, 19); // Brown
        Color submitButtonTextColor = Color.WHITE;

        submitButton.setBackground(submitButtonBackgroundColor);
        submitButton.setForeground(submitButtonTextColor);


        // Create a back arrow button with just the arrow (no box)
        JButton backButton = new JButton();
        backButton.setOpaque(false);
        backButton.setContentAreaFilled(false);
        backButton.setBorderPainted(false); // Remove border

        try {
            Image backArrowImage = ImageIO.read(new File("back_arrow.png"));
            backButton.setIcon(new ImageIcon(backArrowImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH))); // Fixed size
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add action listener to the back arrow button
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the main frame and hide the create account frame
                frame.setVisible(true);
                createAccountFrame.setVisible(false);
            }
        });

        // Add components to the create account panel
        GridBagConstraints gbcCreate = new GridBagConstraints();
        gbcCreate.anchor = GridBagConstraints.CENTER;
        gbcCreate.insets = new Insets(10, 10, 10, 10);
        gbcCreate.gridx = 0;
        gbcCreate.gridy = 0;

        // Add back arrow button to top left corner and center it
        gbcCreate.anchor = GridBagConstraints.NORTHWEST;
        createAccountPanel.add(backButton, gbcCreate);

        // Move to next row for username components
        gbcCreate.gridy++;

        // JLabel for "Enter your username:" with white text color
        JLabel usernameLabel = new JLabel("<html><font color='#FFFFFF'><b>Enter your username:</b></font></html>");
        usernameLabel.setForeground(new Color(128, 0, 128));
        createAccountPanel.add(usernameLabel, gbcCreate);

        // Move to next row for username field
        gbcCreate.gridy++;
        createAccountPanel.add(usernameField, gbcCreate);

        // Move to next row for password label
        gbcCreate.gridy++;

        // JLabel for "Enter your password:" with white text color
        JLabel passwordLabel = new JLabel("<html><font color='#FFFFFF'><b>Enter your password:</b></font></html>");
        passwordLabel.setForeground(new Color(128, 0, 128));
        createAccountPanel.add(passwordLabel, gbcCreate);

        // Move to next row for password field
        gbcCreate.gridy++;
        createAccountPanel.add(passwordField, gbcCreate);

        // Move to next row for submit button
        gbcCreate.gridy++;
        gbcCreate.anchor = GridBagConstraints.CENTER; // Center submit button horizontally and vertically
        createAccountPanel.add(submitButton, gbcCreate);

        // Create the login frame
        final JFrame loginFrame = new JFrame("Log In - Pixel Flix");

        // Set the default close operation of the login frame
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the default size of the login frame
        loginFrame.setSize(800, 600);

        // Optionally, set the minimum size of the login frame
        loginFrame.setMinimumSize(new Dimension(400, 300));
        
        Dimension screenSize2 = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX2 = (int) ((screenSize2.getWidth() - loginFrame.getWidth()) / 2);
        int centerY2 = (int) ((screenSize2.getHeight() - loginFrame.getHeight()) / 2);
        loginFrame.setLocation(centerX, centerY);

        // Set the same icon for the login frame
        try {
            Image icon = ImageIO.read(new File("official_logo.png"));
            loginFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a custom panel for the login frame
        BackgroundPanel loginPanel = new BackgroundPanel();
        loginPanel.setLayout(new GridBagLayout());

        loginFrame.add(loginPanel);

        // Create text fields for username and password in the login panel
        final JTextField loginUsernameField = new JTextField(20);
        final JPasswordField loginPasswordField = new JPasswordField(20);

        // Create a button to submit the login
        JButton loginButton = new JButton("Log In");
        
     // Set the background and text color for the submit button
        Color loginButtonBackgroundColor = new Color(139, 69, 19); // Brown
        Color loginButtonTextColor = Color.WHITE;

        loginButton.setBackground(loginButtonBackgroundColor);
        loginButton.setForeground(loginButtonTextColor);

        // Create a back arrow button for login frame
        JButton loginBackButton = new JButton();
        loginBackButton.setOpaque(false);
        loginBackButton.setContentAreaFilled(false);
        loginBackButton.setBorderPainted(false);

        try {
            Image backArrowImage = ImageIO.read(new File("back_arrow.png"));
            loginBackButton.setIcon(new ImageIcon(backArrowImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add action listener to the back arrow button in login frame
        loginBackButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the main frame and hide the login frame
                frame.setVisible(true);
                loginFrame.setVisible(false);
            }
        });

        // Add components to the login panel
        GridBagConstraints gbcLogin = new GridBagConstraints();
        gbcLogin.anchor = GridBagConstraints.CENTER;
        gbcLogin.insets = new Insets(10, 10, 10, 10);
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;

        // Add back arrow button to top left corner and center it
        gbcLogin.anchor = GridBagConstraints.NORTHWEST;
        loginPanel.add(loginBackButton, gbcLogin);

        // Move to next row for username components
        gbcLogin.gridy++;

        // JLabel for "Enter your username:" with white text color
        JLabel loginUsernameLabel = new JLabel("<html><font color='#FFFFFF'><b>Enter your username:</b></font></html>");
        loginUsernameLabel.setForeground(new Color(128, 0, 128));
        loginPanel.add(loginUsernameLabel, gbcLogin);

        // Move to next row for username field
        gbcLogin.gridy++;
        loginPanel.add(loginUsernameField, gbcLogin);

        // Move to next row for password label
        gbcLogin.gridy++;

        // JLabel for "Enter your password:" with white text color
        JLabel loginPasswordLabel = new JLabel("<html><font color='#FFFFFF'><b>Enter your password:</b></font></html>");
        loginPasswordLabel.setForeground(new Color(128, 0, 128));
        loginPanel.add(loginPasswordLabel, gbcLogin);

        // Move to next row for password field
        gbcLogin.gridy++;
        loginPanel.add(loginPasswordField, gbcLogin);

        // Move to next row for login button
        gbcLogin.gridy++;
        gbcLogin.anchor = GridBagConstraints.CENTER; // Center login button horizontally and vertically
        loginPanel.add(loginButton, gbcLogin);

        // Event listener for createAccountButton to show the create account frame
        createAccountButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                createAccountFrame.setVisible(true);
            }
        });

        // Event listener for mainLoginButton to show the login frame
        mainLoginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                loginFrame.setVisible(true);
            }
        });

        // Event listener for exitButton to exit the application
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Show the message
                JOptionPane.showMessageDialog(null, "Thank you for using Pixelflix. Exiting…");
                // Exit the application
                System.exit(0);
            }
        });


        // Event listener for submitButton to create a new account
     // Event listener for submitButton to create a new account
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (!username.isEmpty() && !password.isEmpty()) {
                    if (userDatabase.containsKey(username)) {
                        JOptionPane.showMessageDialog(createAccountFrame, "Username already exists. Please choose a different username.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        userDatabase.put(username, password);
                        JOptionPane.showMessageDialog(createAccountFrame, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");

                        // Hide the create account frame and show the main frame (Pixel Flix)
                        createAccountFrame.setVisible(false);
                        frame.setVisible(true);
                    }
                } else {
                    JOptionPane.showMessageDialog(createAccountFrame, "Please enter a username and password.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        // Event listener for loginButton to handle user login
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = loginUsernameField.getText();
                String password = new String(loginPasswordField.getPassword());

                if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
                    JOptionPane.showMessageDialog(loginFrame, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    loginFrame.setVisible(false);
                    // Show the rental frame
                    showRentalFrame();
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Invalid username or password. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set the frame to be visible
        frame.setVisible(true);
    }
    
    /**
     * Displays the rental frame for renting videos, including a table of available videos and
     * fields for entering rental information. Also includes buttons for renting a video,
     * viewing rental history, and logging out.
     */
    private static void showRentalFrame() {
        // Create a new JFrame for renting videos
        rentalFrame = new JFrame("Rent a Video - Pixel Flix");

        // Set the default close operation, size, and minimum size of the rental frame
        rentalFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        rentalFrame.setSize(800, 600);
        rentalFrame.setMinimumSize(new Dimension(600, 400));

        // Center the rental frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - rentalFrame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - rentalFrame.getHeight()) / 2);
        rentalFrame.setLocation(centerX, centerY);

        // Set the icon image of the rental frame
        try {
            Image icon = ImageIO.read(new File("official_logo.png"));
            rentalFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a JPanel with a background image for the rental frame
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image backgroundImage = ImageIO.read(new File("a.jpg"));
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setLayout(null);

        // Define the column names and data for the rental table
        String[] columnNames = {"ID", "Movie Title", "Price", "Photo"};
        final Object[][] data = {
                {1, "Forest", 4.99, "forest_pic.jpg"},
                {2, "The Reclamation", 3.99, "reclamation_pic.jpg"},
                {3, "The Dark Wizard", 5.99, "dark_wizard_pic.jpg"},
        };

        // Create a default table model for the rental table
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public Class<?> getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 2:
                        return Double.class;
                    case 3:
                        return ImageIcon.class;
                    default:
                        return String.class;
                }
            }
        };

        // Create a JTable to display the rental table
        JTable rentalTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component component = super.prepareRenderer(renderer, row, column);
                if (!isRowSelected(row)) {
                    String description = (String) getValueAt(row, 1);
                    if ("Forest".equals(description) || "The Reclamation".equals(description) || "The Dark Wizard".equals(description)) {
                        component.setBackground(Color.decode("#C65911"));
                        component.setForeground(Color.WHITE);
                    } else {
                        component.setBackground(getBackground());
                        component.setForeground(getForeground());
                    }
                }
                return component;
            }
        };

        // Set the row height and header properties for the rental table
        rentalTable.setRowHeight(120);
        rentalTable.getTableHeader().setReorderingAllowed(false);

        // Set cell renderers for the photo column and center align the other columns
        rentalTable.getColumnModel().getColumn(3).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                label.setHorizontalAlignment(JLabel.CENTER);
                if (value instanceof String) {
                    String imagePath = (String) value;
                    ImageIcon icon = new ImageIcon(imagePath);
                    Image img = icon.getImage();
                    Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
                    label.setIcon(new ImageIcon(scaledImg));
                }
                return label;
            }
        });
        rentalTable.getColumnModel().getColumn(3).setPreferredWidth(120);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        rentalTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        rentalTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        rentalTable.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);

        // Set custom header renderer for the rental table
        JTableHeader header = rentalTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(139, 69, 19)); // Set background color to brown
                label.setForeground(Color.WHITE); // Set text color to white
                label.setFont(label.getFont().deriveFont(Font.BOLD)); // Set font to bold
                label.setOpaque(true); // Make the label opaque
                label.setHorizontalAlignment(JLabel.CENTER); // Center align the text
                return label;
            }
        });

        // Create a JScrollPane for the rental table and add it to the background panel
        JScrollPane scrollPane = new JScrollPane(rentalTable);
        scrollPane.setBounds(50, 50, 700, 350);
        backgroundPanel.add(scrollPane);

        // Add labels, text fields, and buttons for renting a video, viewing rental history, and logging out
        JLabel idLabel = new JLabel("Enter the number of the video you want to rent:");
        idLabel.setBounds(50, 410, 300, 30);
        idLabel.setForeground(new Color(255, 255, 255));
        backgroundPanel.add(idLabel);

        final JTextField idField = new JTextField();
        idField.setBounds(350, 410, 100, 30);
        backgroundPanel.add(idField);

        JLabel durationLabel = new JLabel("Enter the rental duration (in day/s):");
        durationLabel.setBounds(50, 450, 300, 30);
        durationLabel.setForeground(new Color(255, 255, 255));
        backgroundPanel.add(durationLabel);

        final JTextField durationField = new JTextField();
        durationField.setBounds(350, 450, 100, 30);
        backgroundPanel.add(durationField);

     // Create a button to rent a video
        JButton rentButton = new JButton("Rent Video");

        // Set the background and text color for the rent button
        Color rentButtonBackgroundColor = new Color(139, 69, 19); // Brown
        Color rentButtonTextColor = Color.WHITE;

        rentButton.setBackground(rentButtonBackgroundColor);
        rentButton.setForeground(rentButtonTextColor);

        rentButton.setBounds(350, 490, 100, 30);
        backgroundPanel.add(rentButton);

        JButton viewHistoryButton = new JButton("View Rental History");
        
     // Set the background and text color for the Rental button
        Color viewHistoryButtonBackgroundColor = new Color(139, 69, 19); // Brown
        Color viewHistoryButtonTextColor = Color.WHITE;

        viewHistoryButton.setBackground(viewHistoryButtonBackgroundColor);
        viewHistoryButton.setForeground(viewHistoryButtonTextColor);
        
        viewHistoryButton.setBounds(460, 490, 160, 30);
        backgroundPanel.add(viewHistoryButton);

        JButton logoutButton = new JButton("Logout");
        
     // Set the background and text color for the Logout button
        Color logoutButtonButtonBackgroundColor = Color.white; // Red
        Color logoutButtonButtonTextColor = Color.red;

        logoutButton.setBackground(logoutButtonButtonBackgroundColor);
        logoutButton.setForeground(logoutButtonButtonTextColor);
        
        logoutButton.setBounds(630, 490, 100, 30);
        backgroundPanel.add(logoutButton);

        // Add action listeners for the rent button, view history button, and logout button
        rentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int videoId = Integer.parseInt(idField.getText());
                    int duration = Integer.parseInt(durationField.getText());
                    double pricePerDay = 0.0;

                    String movieTitle = movieTitles.getOrDefault(videoId, "Unknown");

                    for (Object[] row : data) {
                        if ((int) row[0] == videoId) {
                            pricePerDay = (double) row[2];
                            break;
                        }
                    }

                    if (pricePerDay > 0) {
                        double rentalAmount = pricePerDay * duration;
                        String transactionCode = generateTransactionCode();

                        rentalHistory.add(new RentalRecord(videoId, duration, rentalAmount, transactionCode));

                        JOptionPane.showMessageDialog(rentalFrame,
                                "Video rented successfully!\nMovie Title: " + movieTitle +
                                "\nTransaction Code: " + transactionCode +
                                "\nRental Amount: PHP " + String.format("%.2f", rentalAmount));
                    } else {
                        JOptionPane.showMessageDialog(rentalFrame, "Invalid Video ID.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(rentalFrame, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rentalFrame.setVisible(false); // Hide the rental frame
                showRentalHistoryFrame(); // Show the rental history frame
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create custom button text
              
                int response = JOptionPane.showConfirmDialog(
                    rentalFrame,
                    "Are you sure you want to log out?",
                    "Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );

                if (response == JOptionPane.YES_OPTION) {
                    JOptionPane.showMessageDialog(rentalFrame, "Logging out...");
                    rentalFrame.dispose(); // Close the rental frame
                    frame.setVisible(true); // Show the main frame
                }
            }
        });


        // Set the content pane of the rental frame and make it visible
        rentalFrame.setContentPane(backgroundPanel);
        rentalFrame.setVisible(true);
    }

    
    private static void showRentalHistoryFrame() {
        // Create a new JFrame for the rental history
        final JFrame historyFrame = new JFrame("Rental History - Pixel Flix");

        // Set the size and minimum size of the history frame
        historyFrame.setSize(800, 600);
        historyFrame.setMinimumSize(new Dimension(600, 400));

        // Center the history frame on the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - historyFrame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - historyFrame.getHeight()) / 2);
        historyFrame.setLocation(centerX, centerY);

        // Load and set the custom logo as the frame icon
        try {
            Image icon = ImageIO.read(new File("official_logo.png"));
            historyFrame.setIconImage(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set the background image of the history frame
        JLabel background = new JLabel(new ImageIcon("a.jpg"));
        background.setBounds(0, 0, 800, 600);

        // Define the column names for the rental history table
        String[] columnNames = {"Movie Title", "Duration", "Amount", "Transaction Code"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Populate the rental history table with data from rentalHistory
        for (RentalRecord record : rentalHistory) {
            String movieTitle = movieTitles.getOrDefault(record.getVideoId(), "Unknown");
            double amount = record.getAmount();
            String formattedAmount = String.format("%.2f", amount); // Format the amount to two decimal places
            model.addRow(new Object[]{movieTitle, record.getDuration(), formattedAmount, record.getTransactionCode()});
        }

        // Create a JTable to display the rental history
        JTable historyTable = new JTable(model);
        historyTable.setRowHeight(30);

        // Create a JScrollPane to contain the history table
        JScrollPane scrollPane = new JScrollPane(historyTable);
        scrollPane.setBounds(50, 50, 700, 400);

        // Create a JPanel to hold the scroll pane and the background image
        JPanel panel = new JPanel(null);
        panel.add(scrollPane);
        panel.add(background); // Add the background image as the bottom layer

        // Set custom cell renderer for the history table to customize its appearance
        historyTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                component.setForeground(Color.WHITE); // Set text color to white
                component.setBackground(Color.decode("#C65911")); // Set background color
                return component;
            }
        });

        // Set custom cell renderer for the history table header
        JTableHeader header = historyTable.getTableHeader();
        header.setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBackground(new Color(139, 69, 19)); // Set background color to brown
                label.setForeground(Color.WHITE); // Set text color to white
                label.setFont(label.getFont().deriveFont(Font.BOLD)); // Set font to bold
                label.setOpaque(true); // Make the label opaque
                label.setHorizontalAlignment(JLabel.CENTER); // Center align the text
                return label;
            }
        });

        // Add a window listener to the history frame to show the rental frame when the history frame is closed
        historyFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                rentalFrame.setVisible(true); // Make the rental frame visible
            }
        });

        // Set the content pane of the history frame and make it visible
        historyFrame.setContentPane(panel);
        historyFrame.setVisible(true);
    }

    static class RentalRecord {
        private final int videoId; // The ID of the rented video
        private final int duration; // The duration of the rental in days
        private final double amount; // The rental amount
        private final String transactionCode; // The transaction code for the rental

        // Constructs a RentalRecord object with the specified video ID, duration, amount, and transaction code
        public RentalRecord(int videoId, int duration, double amount, String transactionCode) {
            this.videoId = videoId;
            this.duration = duration;
            this.amount = amount;
            this.transactionCode = transactionCode;
        }

        // Gets the ID of the rented video
        public int getVideoId() {
            return videoId;
        }

        // Gets the duration of the rental in days
        public int getDuration() {
            return duration;
        }

        // Gets the rental amount
        public double getAmount() {
            return amount;
        }

        // Gets the transaction code for the rental
        public String getTransactionCode() {
            return transactionCode;
        }
    }
    
  

 // Generates a unique transaction code based on the current system time in milliseconds
 // The transaction code format is "TXN" followed by the current system time
 private static String generateTransactionCode() {
     return "TXN" + System.currentTimeMillis();
 }

 // Custom JPanel class to display a background image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        // Constructor to initialize the background image
        public BackgroundPanel() {
            try {
                backgroundImage = ImageIO.read(new File("a.jpg")); // Load the background image
            } catch (IOException e) {
                e.printStackTrace(); // Print any errors that occur during loading
            }
        }

        // Override the paintComponent method to paint the background image
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Call the superclass's paintComponent method

            // Check if the background image is not null
            if (backgroundImage != null) {
                // Draw the background image at the top-left corner of the panel
                // and scale it to fill the entire panel
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

}
