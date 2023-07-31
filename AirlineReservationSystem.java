import java.util.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.*;
import javax.swing.text.View;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AirlineReservationSystem extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JLabel lblNewLabel = new JLabel("Welcome to the Airline Reservation System");
	static ArrayList<Passenger> passengers = new ArrayList<>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AirlineReservationSystem frame = new AirlineReservationSystem();
					frame.pack();
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					frame.setLayout(new FlowLayout());
					frame.setBounds(100, 100, 746, 450);
					frame.contentPane = new JPanel();
					frame.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

					frame.setContentPane(frame.contentPane);
					frame.contentPane.setLayout(null);
					frame.lblNewLabel.setBounds(225, 73, 290, 27);
					frame.contentPane.add(frame.lblNewLabel);
					
					JLabel lblNewLabel_1 = new JLabel("1. Book Ticket");
					lblNewLabel_1.setBounds(257, 144, 142, 14);
					frame.contentPane.add(lblNewLabel_1);
					
					JLabel lblNewLabel_2 = new JLabel("2. Cancel Ticket");
					lblNewLabel_2.setBounds(257, 199, 150, 14);
					frame.contentPane.add(lblNewLabel_2);
					
					JLabel lblNewLabel_3 = new JLabel("3. View Passenger Details");
					lblNewLabel_3.setBounds(257, 254, 142, 14);
					frame.contentPane.add(lblNewLabel_3);
					
					JLabel lblNewLabel_4 = new JLabel("4. Exit");
					lblNewLabel_4.setBounds(257, 311, 142, 14);
					frame.contentPane.add(lblNewLabel_4);
					
					JButton bookTicket = new JButton("Click");
					bookTicket.addActionListener(new ActionListener() 
					{
						@Override
						public void actionPerformed(ActionEvent e) {
							// Open the custom dialog and get the input from the user
							bookticket dialog = new bookticket(frame);
							dialog.setVisible(true); // This will block until the dialog is closed

							if (dialog.isConfirmed()) 
							{
								String name = dialog.getNameInput();
								String departure = dialog.getDepartureInput();
								String destination = dialog.getDestinationInput();
								String travelDate = dialog.getDateInput();

								JOptionPane.showMessageDialog(frame, "Booking Details:\n" +
										"Name: " + name + "\n" +
										"Departure: " + departure + "\n" +
										"Destination: " + destination + "\n" +
										"Date: " + travelDate);
								Passenger passenger = new Passenger(name, departure, destination, travelDate);
								passengers.add(passenger);
							}
						}
					});
					bookTicket.setBounds(469, 140, 89, 23);
					frame.contentPane.add(bookTicket);
					
					JButton cancelTicket = new JButton("Click");
					cancelTicket.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Open the custom dialog and get the input from the user
							cancelticket dialog = new cancelticket(frame);
							dialog.setVisible(true); // This will block until the dialog is closed

							if (dialog.isConfirmed()) 
							{
								String name = dialog.getNameInput();
								String departure = dialog.getDepartureInput();
								String destination = dialog.getDestinationInput();
								String travelDate = dialog.getDateInput();
								for (int i = 0; i < passengers.size(); i++) {
									Passenger passenger = passengers.get(i);
									if (passenger.getName().equals(name) && 
									passenger.getDeparture().equals(departure) && 
									passenger.getDestination().equals(destination) &&
									passenger.getTravelDate().equals(travelDate) ) {
										passengers.remove(i);
										JOptionPane.showMessageDialog(frame,"deleted\n"+"successfully...\n");
										return;
									}
								}
								JOptionPane.showMessageDialog(frame,"Details not found!!!");
								
							}
						}
					});
					cancelTicket.setBounds(469, 195, 89, 23);
					frame.contentPane.add(cancelTicket);
					
					JButton viewPassengerDetails = new JButton("Click");
					viewPassengerDetails.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							// Open the custom dialog and get the input from the user
							viewticket dialog = new viewticket(frame);
							dialog.setVisible(true); // This will block until the dialog is closed
							String name = dialog.getNameInput();
							for (int i = 0; i < passengers.size(); i++) {
								Passenger passenger = passengers.get(i);
								if (passenger.getName().equals(name)) {
									JOptionPane.showMessageDialog(frame, "Booking Details:\n" +
                            											"Name: " + passenger.getName() + "\n" +
                            											"Departure: " + passenger.getDeparture() + "\n" +
																		"Destination: " + passenger.getDestination() + "\n" +
                            											"Date: " + passenger.getTravelDate());
									return;
								}
							}
							JOptionPane.showMessageDialog(frame,"Details not found!!!");
							
						}
					});
					viewPassengerDetails.setBounds(469, 250, 89, 23);
					frame.contentPane.add(viewPassengerDetails);
					
					JButton Exit = new JButton("Click");
					Exit.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							System.exit(0);
						}
					});
					Exit.setBounds(469, 311, 89, 23);
					frame.contentPane.add(Exit);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
					
	}	
}

class bookticket extends JDialog {
    private JTextField nameTextField;
    private JTextField departureTextField;
    private JTextField destinationTextField;
    private JTextField dateTextField;
    private boolean confirmed;

    public bookticket(JFrame parent) {
        super(parent, "Ticket Booking", true); // Modal dialog

        nameTextField = new JTextField(20);
        departureTextField = new JTextField(20);
        destinationTextField = new JTextField(20);
        dateTextField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose(); // Close the dialog
            }
        });

        // Layout the components
        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Name:"));
        add(nameTextField);
        add(new JLabel("Departure:"));
        add(departureTextField);
        add(new JLabel("Destination:"));
        add(destinationTextField);
        add(new JLabel("Date:"));
        add(dateTextField);
        add(new JLabel());
        add(submitButton);

        pack();
        setLocationRelativeTo(parent); // Center the dialog on the parent JFrame
    }

    public String getNameInput() {
        return nameTextField.getText();
    }

    public String getDepartureInput() {
        return departureTextField.getText();
    }

    public String getDestinationInput() {
        return destinationTextField.getText();
    }

    public String getDateInput() {
        return dateTextField.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

class cancelticket extends JDialog {
    private JTextField nameTextField;
    private JTextField departureTextField;
    private JTextField destinationTextField;
    private JTextField dateTextField;
    private boolean confirmed;

    public cancelticket(JFrame parent) {
        super(parent, "Ticket Cancellation", true); // Modal dialog

        nameTextField = new JTextField(20);
        departureTextField = new JTextField(20);
        destinationTextField = new JTextField(20);
        dateTextField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose(); // Close the dialog
            }
        });

        // Layout the components
        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Name:"));
        add(nameTextField);
        add(new JLabel("Departure:"));
        add(departureTextField);
        add(new JLabel("Destination:"));
        add(destinationTextField);
        add(new JLabel("Date:"));
        add(dateTextField);
        add(new JLabel());
        add(submitButton);

        pack();
        setLocationRelativeTo(parent); // Center the dialog on the parent JFrame
    }

    public String getNameInput() {
        return nameTextField.getText();
    }

    public String getDepartureInput() {
        return departureTextField.getText();
    }

    public String getDestinationInput() {
        return destinationTextField.getText();
    }

    public String getDateInput() {
        return dateTextField.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

class viewticket extends JDialog {
    private JTextField nameTextField;
    private JTextField departureTextField;
    private JTextField destinationTextField;
    private JTextField dateTextField;
    private boolean confirmed;

    public viewticket(JFrame parent) {
        super(parent, "Ticket View", true); // Modal dialog

        nameTextField = new JTextField(20);
        departureTextField = new JTextField(20);
        destinationTextField = new JTextField(20);
        dateTextField = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmed = true;
                dispose(); // Close the dialog
            }
        });

        // Layout the components
        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Name:"));
        add(nameTextField);
        add(new JLabel());
        add(submitButton);

        pack();
        setLocationRelativeTo(parent); // Center the dialog on the parent JFrame
    }

    public String getNameInput() {
        return nameTextField.getText();
    }

    public String getDepartureInput() {
        return departureTextField.getText();
    }

    public String getDestinationInput() {
        return destinationTextField.getText();
    }

    public String getDateInput() {
        return dateTextField.getText();
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}

class Passenger {
    private String name;
    private String departure;
    private String destination;
    private String travelDate;
    
    public Passenger(String name, String departure, String destination, String travelDate) {
        this.name = name;
        this.departure = departure;
        this.destination = destination;
        this.travelDate = travelDate;
    }
    
    public String getName() {
        return name;
    }
    
    public String getDeparture() {
        return departure;
    }
    
    public String getDestination() {
        return destination;
    }
    
    public String getTravelDate() {
        return travelDate;
    }
}