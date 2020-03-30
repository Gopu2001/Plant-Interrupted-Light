package dayLengthPlants;

import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;
import javax.swing.Box;
import java.io.IOException;

/**
 * Control main view
 *
 * @author Anmol Kapoor
 * @version 1.0
 */
public class Main
{
    private JFrame window;
    private JButton home; private JButton anim; private JButton conc; private JButton quiz;
    private JPanel startPanel;
    private JPanel animPanel;
    private JPanel biggerContainer;
    private JPanel container;
    private JPanel btnCont;
    private JPanel tabs;
    private JButton exitbtn;
    private JButton qNext; private JButton qBack;
    private JButton next; private JButton back;
    private ActionListener actionListener;
    private ActionListener s9animListener;
    private ActionListener s14animListener;
    private ActionListener quiz1Listener; private ActionListener quiz2Listener; private ActionListener quiz3Listener; 
    private int animCounter;
    private int correctCounter;
    private int quizCounter;
    private int width; private int height;
    private JLabel spacer1; private JLabel spacer2;
    private JLabel dispImage;
    private JLabel dispCaption;
    private JFrame alert;
    private String alertTextA; private String alertTextB; private String alertTextC; private String alertTextD; private String alertTextE; private String alertTextF; private String alertTextG;
    private JPanel alertPanel;
    
    public static void main(String[] args) {
        new Main();
    }
    
    /**
     * Constructor for objects of class Main
     */
    @SuppressWarnings("serial")
	public Main()
    {
        window = new JFrame("The Effect of Interrupted Days and Nights");
        width = 850; height = 775;
        
        window.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
        window.getRootPane().getActionMap().put("Cancel", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setLayout(new BorderLayout());
        
        biggerContainer = new JPanel();
        biggerContainer.setLayout(new BoxLayout(biggerContainer, BoxLayout.Y_AXIS));
        container = new JPanel();
        container.setPreferredSize(new Dimension(width,height-50));
        btnCont = new JPanel();
        btnCont.setLayout(new BoxLayout(btnCont, BoxLayout.X_AXIS));
        
        tabs = new JPanel();
        home = new JButton("Introduction");
        anim = new JButton("Animation");
        conc = new JButton("Conclusion");
        quiz = new JButton("Quiz");
        exitbtn = new JButton("Exit Program");
        
        animPanel = new JPanel();
        animCounter = 0;
        alertTextA = "";
        alertTextB = "";
        alertTextC = "";
        alertTextD = "";
        alertTextE = "";
        alertTextF = "";
        alertTextG = "";
        actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                switch(event.getActionCommand()) {
                    case "home" : startIntroduction(); break;
                    case "anim" : animCounter = 0; startAnimation(); break;
                    case "conc" : startConclusion(); break;
                    case "quiz" : quizCounter = 1; startQuiz(); break;
                    case "next" : animCounter++; startAnimation(); break;
                    case "back" : animCounter--; startAnimation(); break;
                    case "qnex" : quizCounter++; startQuiz(); break;
                    case "qbac" : quizCounter--; startQuiz(); break;
                    case "exit" : System.exit(0); break;
                    case "anim1a": anim1Alert(1, false); break;
                    case "anim1b": if(alert != null) {alert.setVisible(false);}
                    anim1Alert(2, false); break;
                    case "anim1c": if(alert != null) {alert.setVisible(false);}
                    anim1Alert(3, true); break;
                    case "anim2a": if(alert != null) {alert.setVisible(false);}
                    anim1Alert(1, false); break;
                    case "anim2b": if(alert != null) {alert.setVisible(false);}
                    anim1Alert(2, true); break;
                    case "anim2c": if(alert != null) {alert.setVisible(false);}
                    anim1Alert(3, false); break;
                    case "close": alert.setVisible(false); break;
                    default: break;
                }
            }
        };
        home.setActionCommand("home");
        home.addActionListener(actionListener);
        anim.setActionCommand("anim");
        anim.addActionListener(actionListener);
        conc.setActionCommand("conc");
        conc.addActionListener(actionListener);
        quiz.setActionCommand("quiz");
        quiz.addActionListener(actionListener);
        exitbtn.setActionCommand("exit");
        exitbtn.addActionListener(actionListener);
        
        tabs.add(home);
        tabs.add(anim);
        tabs.add(conc);
        tabs.add(quiz);
        
        startIntroduction();

        window.pack();
        window.setBounds(0,0,width,height);
        window.setResizable(false);
        window.setVisible(true);
        window.setLocationRelativeTo(null);
    }
    
    public BufferedImage loadBImage(String filename)
    {
    	BufferedImage bimg = null;
    	try {
    		bimg = ImageIO.read(getClass().getResourceAsStream(filename));
    	} catch (IOException e) {
    		System.exit(0);
    	}
    	return bimg;
    }
    
    /**
     * 
     * @return "Introduction"
     */
    public String startIntroduction()
    {
        animCounter = 0;
        quizCounter = 0;
        biggerContainer.removeAll();
        container.removeAll();
        startPanel = new JPanel();
        startPanel.setLayout(new BorderLayout());
        JLabel title = new JLabel("<html><h1><span style='color:#ffffff'>...................</span><span style='color:#000000'>The Effect of Interrupted Days and Nights</span></h1><html>");
        startPanel.add(title, BorderLayout.NORTH);
        JLabel showImage = new JLabel(new ImageIcon(loadBImage("PlantCover.JPG")));
        startPanel.add(showImage, BorderLayout.WEST);
        startPanel.add(new JLabel("      ")); //spacer
        JLabel openText = new JLabel("<html><p>For flowering plants to cross pollinate, they<br>"+
        "must produce flowers at the same time of year.<br>"+
        "How do plants that may be different in size or<br>"+
        "age synchronize their flowering? In the 1930s,<br>"+
        "James Bonner and Karl Hammer discovered<br>"+
        "that the key flowering trigger in some plants is<br>"+
        "the length of night, which explains why a<br>"+
        "species will flower during a particular season<br>"+
        "year after year.<br><br>"+
        "For historical reasons, plants are described as<br>"+
        "short-day or long-day plants, rather than the<br>"+
        "more appropriate long-night and short-night<br>"+
        "plants. Day neutral plants, in which the<br>"+
        "flowering trigger does not depend on a<br>"+
        "specific length of darkness, are perhaps more<br>"+
        "common than short- or long-day plants. In this<br>"+
        "animation, we will examine flowering<br>"+
        "experiments performed on cockleblur, a short-<br>"+
        "day plant.<p><html>");
        
        openText.setFont(new Font(openText.getFont().toString(), Font.PLAIN, 18));
        startPanel.add(openText, BorderLayout.EAST);
        
        tabs.setBackground(Color.WHITE);
        tabs.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        container.add(tabs, BorderLayout.NORTH);
        container.add(exitbtn, BorderLayout.EAST);
        startPanel.setBackground(Color.WHITE);
        startPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
        container.add(startPanel);
        container.setBackground(Color.WHITE);
        biggerContainer.setBackground(Color.WHITE);
        btnCont.setBackground(Color.WHITE);
        window.add(biggerContainer);
        biggerContainer.add(container);
        
        container.updateUI();
        
        return "Introduction";
    }
    
    /**
     * 
     * @return "Animation"
     */
    public String startAnimation()
    {
        quizCounter = 0;
        if(startPanel != null) { startPanel.removeAll(); container.removeAll(); }
        dispCaption = new JLabel();
        dispCaption.setFont(new Font(dispCaption.getFont().toString(), Font.PLAIN, 14));
        dispImage = new JLabel();
        
        // just in case
        container.remove(startPanel);
        //container.remove(animPanel);
        //container.remove(dispCaption);
        spacer1 = new JLabel("                      ");
        spacer2 = new JLabel("                      ");
        spacer1.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        spacer2.setBorder(BorderFactory.createLineBorder(Color.WHITE, 5));
        next = new JButton("Next");
        next.setActionCommand("next");
        next.addActionListener(actionListener);
        
        back = new JButton("Back");
        back.setActionCommand("back");
        back.addActionListener(actionListener);
        
        animPanel = new JPanel();
        switch(animCounter) {
            case 0:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant1.JPG")));
            dispCaption.setText("<html><p>The cocklebur, <i>Xanthium strumarium</i>, is a short day plant that<br>"+
            "begins to flower in the late summer and fall when day length no<br>"+
            "longer exceeds 15 hours.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 1:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant1.JPG")));
            dispCaption.setText("<html><p>"+
            "In experiments by Karl Hamner and James Bonner, cocklebur<br>"+
            "plants were grown continuously on a 16-hour light/8-hour dark<br>"+
            "cycle. Under these conditions, the plants remained in a vegetative,<br>"+
            "nonflowering state indefinitely.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 2:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant2.JPG")));
            dispCaption.setText("<html><p>"+
            "Reducing the photoperiod by just one hour produced plants that<br>"+
            "flowered abundantly.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 3:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant3.JPG")));
            dispCaption.setText("<html><p>"+
            "Reducing the photoperiod by just one hour produced plants that<br>"+
            "flowered abundantly.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 4:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant4.JPG")));
            dispCaption.setText("<html><p><i>Click on the correct answer.</i></p></html>");
            alertTextA = "From these experiments, it is not possible to determine the critical factor in flowering. Both factors are varied together in this experiment.</p></html>";
            alertTextB = alertTextA;
            alertTextC = alertTextA;
            JButton choiceA = new JButton("A");
            JButton choiceB = new JButton("B");
            JButton choiceC = new JButton("C");
            choiceA.setActionCommand("anim1a");
            choiceA.addActionListener(actionListener);
            choiceB.setActionCommand("anim1b");
            choiceB.addActionListener(actionListener);
            choiceC.setActionCommand("anim1c");
            choiceC.addActionListener(actionListener);
            animPanelAdd();
            container.add(choiceA, BorderLayout.SOUTH);
            container.add(choiceB, BorderLayout.SOUTH);
            container.add(choiceC, BorderLayout.SOUTH);
            container.add(spacer2);
            //biggerContainer.add(next, BorderLayout.SOUTH);
            break;
            
            case 5:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant5.JPG")));
            dispCaption.setText("<html><p>"+
            "Hamner and Bonner also experimented with photoperiods that<br>"+
            "were not on a 24-hour cycle, but extended to 48-hour cycles. In<br>"+
            "the new treatment, plants received light during the first 16 hours,<br>"+
            "followed by a 32-hour dark period. Plants flowered under these<br>"+
            "conditions.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 6:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant6.JPG")));
            dispCaption.setText("<html><p>"+
            "Other plants received treatments on 12-hour cycles, with 4 hours<br>"+
            "of light followed by 8 hours of darkness. Plants did not flower<br>"+
            "under these conditions.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 7:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant7.JPG")));
            dispCaption.setText("<html><p><i>Click on the correct answer.</i></p></html>");
            alertTextA = "Notice that a light period of 4 hours was insufficient to trigger flowering. Try Again.</p></html>";
            alertTextB = "From these experiments, it appears that 9 hours or more of darkness after a light period triggers flowering.</p></html>";
            alertTextC = "In all cases in which the period of darkness was less than 9 hours, the plant did not flower. Try Again.</p></html>";
            JButton anim2choiceA = new JButton("A");
            JButton anim2choiceB = new JButton("B");
            JButton anim2choiceC = new JButton("C");
            anim2choiceA.setActionCommand("anim2a");
            anim2choiceA.addActionListener(actionListener);
            anim2choiceB.setActionCommand("anim2b");
            anim2choiceB.addActionListener(actionListener);
            anim2choiceC.setActionCommand("anim2c");
            anim2choiceC.addActionListener(actionListener);
            animPanelAdd();
            container.add(anim2choiceA, BorderLayout.SOUTH);
            container.add(anim2choiceB, BorderLayout.SOUTH);
            container.add(anim2choiceC, BorderLayout.SOUTH);
            container.add(spacer2);
            //biggerContainer.add(next, BorderLayout.SOUTH);
            break;
            
            case 8:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant8.JPG")));
            dispCaption.setText("<html><p>"+
            "Hamner and Bonner's experiments pointed to the length of<br>"+
            "darkness as important in flowering in cocklebur, with 9 hours as<br>"+
            "the critical length. To test whether a continuous 9 hours is<br>"+
            "important, they interrupted the dark period with 1 minute of<br>"+
            "illumination. This treatment resulted in no flowering.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 9:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant9.JPG")));
            dispImage.setLayout(null);
            dispCaption.setText("<html><p>"+
            "The investigators determined that short-day and long-day plants<br>"+
            "had a timing mechanism that measures the length of a<br>"+
            "continuous dark period. With this in mind, predict whether each<br>"+
            "of these conditions results in flowering. Click <i>'YES'</i><br>"+
            "for flowering and <i>'NO'</i> for no flowering at the<br>"+
            "appropriate position in the table above.</p></html>");
            alertTextA = "<html><p>No. Short-day plants require a long night of uninterrupted darkness in order to flower.</p></html>";
            alertTextB = "<html><p>No. A long night of uninterrupted darkness promotes flowering in short-day plants.</p></html>";
            alertTextC = "<html><p>No. Long-day plants flower when the nights are short or when long nights are interrupted by periods of illumination.</p></html>";
            alertTextD = "<html><p>No. A long night of uninterrupted darkness inhibits flowering in long-day plants.</p></html>";
            alertTextE = "<html><p>Correct! You've correctly predicted the consequences of each light condition for short-day and long-day plants. Both types of plants measure the length of the dark period. Notice that in long-day plants, interrupting a long night with a short period of illumination - essentially, shortening the night - triggers flowering, just as a short night does.</p></html>";
            JButton sh1y = new JButton("YES"); sh1y.setBounds(10, 120,186/2,48);
            JButton sh1n = new JButton("NO"); sh1n.setBounds(103,120,186/2,48);
            JButton sh2y = new JButton("YES"); sh2y.setBounds(10, 224,186/2,48);
            JButton sh2n = new JButton("NO"); sh2n.setBounds(103,224,186/2,48);
            JButton sh3y = new JButton("YES"); sh3y.setBounds(10, 328,186/2,48);
            JButton sh3n = new JButton("NO"); sh3n.setBounds(103,328,186/2,48);
            JButton sh4y = new JButton("YES"); sh4y.setBounds(10, 432,186/2,48);
            JButton sh4n = new JButton("NO"); sh4n.setBounds(103,432,186/2,48);
            JButton lo1y = new JButton("YES"); lo1y.setBounds(603,120,186/2,48);
            JButton lo1n = new JButton("NO"); lo1n.setBounds(696,120,186/2,48);
            JButton lo2y = new JButton("YES"); lo2y.setBounds(603,224,186/2,48);
            JButton lo2n = new JButton("NO"); lo2n.setBounds(696,224,186/2,48);
            JButton lo3y = new JButton("YES"); lo3y.setBounds(603,328,186/2,48);
            JButton lo3n = new JButton("NO"); lo3n.setBounds(696,328,186/2,48);
            JButton lo4y = new JButton("YES"); lo4y.setBounds(603,432,186/2,48);
            JButton lo4n = new JButton("NO"); lo4n.setBounds(696,432,186/2,48);
            
            correctCounter = 0;
            s9animListener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    switch(event.getActionCommand()) {
                        case "sh1y" : anim2Alert(alertTextA); break;
                        case "sh1n" : sh1y.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} sh1n.setActionCommand(null); break;
                        case "sh2y" : anim2Alert(alertTextA); break;
                        case "sh2n" : sh2y.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} sh2n.setActionCommand(null); break;
                        case "sh3y" : sh3n.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} sh3y.setActionCommand(null); break;
                        case "sh3n" : anim2Alert(alertTextB); break;
                        case "sh4y" : anim2Alert(alertTextA); break;
                        case "sh4n" : sh4y.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} sh4n.setActionCommand(null); break;
                        case "lo1y" : lo1n.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} lo1y.setActionCommand(null); break;
                        case "lo1n" : anim2Alert(alertTextC); break;
                        case "lo2y" : lo2n.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} lo2y.setActionCommand(null); break;
                        case "lo2n" : anim2Alert(alertTextC); break;
                        case "lo3y" : anim2Alert(alertTextD); break;
                        case "lo3n" : lo3y.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} lo3n.setActionCommand(null); break;
                        case "lo4y" : lo4n.setVisible(false); correctCounter++; if(correctCounter == 8) {anim2Alert(alertTextE);} lo4y.setActionCommand(null); break;
                        case "lo4n" : anim2Alert(alertTextC); break;
                        default: break;
                    }
                }
            };
            
            sh1y.setActionCommand("sh1y"); sh1y.addActionListener(s9animListener);
            sh1n.setActionCommand("sh1n"); sh1n.addActionListener(s9animListener);
            sh2y.setActionCommand("sh2y"); sh2y.addActionListener(s9animListener);
            sh2n.setActionCommand("sh2n"); sh2n.addActionListener(s9animListener);
            sh3y.setActionCommand("sh3y"); sh3y.addActionListener(s9animListener);
            sh3n.setActionCommand("sh3n"); sh3n.addActionListener(s9animListener);
            sh4y.setActionCommand("sh4y"); sh4y.addActionListener(s9animListener);
            sh4n.setActionCommand("sh4n"); sh4n.addActionListener(s9animListener);
            lo1y.setActionCommand("lo1y"); lo1y.addActionListener(s9animListener);
            lo1n.setActionCommand("lo1n"); lo1n.addActionListener(s9animListener);
            lo2y.setActionCommand("lo2y"); lo2y.addActionListener(s9animListener);
            lo2n.setActionCommand("lo2n"); lo2n.addActionListener(s9animListener);
            lo3y.setActionCommand("lo3y"); lo3y.addActionListener(s9animListener);
            lo3n.setActionCommand("lo3n"); lo3n.addActionListener(s9animListener);
            lo4y.setActionCommand("lo4y"); lo4y.addActionListener(s9animListener);
            lo4n.setActionCommand("lo4n"); lo4n.addActionListener(s9animListener);
            
            dispImage.add(sh1y);
            dispImage.add(sh1n);
            dispImage.add(sh2y);
            dispImage.add(sh2n);
            dispImage.add(sh3y);
            dispImage.add(sh3n);
            dispImage.add(sh4y);
            dispImage.add(sh4n);
            dispImage.add(lo1y);
            dispImage.add(lo1n);
            dispImage.add(lo2y);
            dispImage.add(lo2n);
            dispImage.add(lo3y);
            dispImage.add(lo3n);
            dispImage.add(lo4y);
            dispImage.add(lo4n);
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 10:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant10.JPG")));
            dispCaption.setText("<html><p>"+
            "The nature of this timing mechanism has been partially revealed<br>"+
            "and involves a pigment, called phytochrome. Phytochrome exists<br>"+
            "in two forms - a P<sub>r</sub> form and a P<sub>fr</sub> form, which refer to red-light<br>"+
            "absorbing and far-red-light absorbing forms.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 11:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant11.JPG")));
            dispCaption.setText("<html><p>"+
            "Red light converts P<sub>r</sub>, which is inactive, into P<sub>fr</sub>, the biologically<br>"+
            "active form. In turn, far-red light converts P<sub>fr</sub> back into P<sub>r</sub>. P<sub>fr</sub> also<br>"+
            "steadily reverts back to the P<sub>r</sub> form at night. Some of the P<sub>fr</sub><br>"+
            "molecules also decay.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 12:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant12.GIF")));
            dispCaption.setText("<html><p>"+
            "Sunlight contains more red than far-red light. As night begins,<br>"+
            "phytochrome is almost all in the P<sub>fr</sub> form. Over a period of several<br>"+
            "hours in the dark, these molecules begin to convert back to P<sub>r</sub> or<br>"+
            "or to break down. Depending on the length of darkness, only some<br>"+
            "of the P<sub>fr</sub> remains.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 13:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant13.GIF")));
            dispCaption.setText("<html><p>"+
            "If a flash of red light interrupts the long night, the phytochrome<br>"+
            "molecules reset themselves, converting from P<sub>r</sub> back into P<sub>fr</sub>.</p></html>");
            animPanelAdd();
            //biggerContainer.add(next, BorderLayout.EAST);
            break;
            
            case 14:
            animPanelRemove();
            dispImage.setIcon(new ImageIcon(loadBImage("AnimPlant14.JPG")));
            dispImage.setLayout(null);
            dispCaption.setText("<html><p>"+
            "Night interruptions have opposite effects in short-day and long-<br>"+
            "day plants. In long-day plants, it promotes flowering. Determine<br>"+
            "how the series of light flashes would affect flowering in short-day<br>"+
            "and long-day plants. Click <i>'YES'</i> for flowering and <i>'NO'</i> for<br>"+
            "no flowering at the appropriate position in the table above.</p></html>");
            alertTextA = "<html><p>No. Long, uninterrupted nights promote flowering in short-day plants.</p></html>";
            alertTextB = "<html><p>No. Note that a final flash of red light essentially 'resets' the night, creating a large pool of P<sub>fr</sub> forms. This form of phytochrome inhibits flowering in short-day plants.</p></html>";
            alertTextC = "<html><p>No. Note that a final flash of far-red light essentially eliminates the pool of P<sub>fr</sub>. Because P<sub>fr</sub> inhibits flowering in short-day plants, plants grown under these conditions flower.</p></html>";
            alertTextD = "<html><p>No. Long uninterrupted nights inhibit flowering in long-day plants.</p></html>";
            alertTextE = "<html><p>No. Note that a final flash of red light essentially 'resets' the night, creating a large pool of P<sub>fr</sub> forms. This form of phytochrome promotes flowering in long-day plants.</p></html>";
            alertTextF = "<html><p>No. Note that a final flash of far-red light essentially eliminates the pool of P<sub>fr</sub>. Because P<sub>fr</sub> is needed to promote flowering in long-day plants, plants grown under these conditions do not flower.</p></html>";
            alertTextG = "<html><p>Correct! You have determined the effect of these light treatments on the short- and long-day plants. A final flash of red light essentially 'resets' the night, creating a large pool of P<sub>fr</sub> forms. This form of phytochrome inhibits flowering in short-day plants, but stimulates flowering in long-day plants.</p></html>";
            sh1y =         new JButton("YES"); sh1y.setBounds(30,63,82,39);
            sh1n =          new JButton("NO"); sh1n.setBounds(112,63,82,39);
            sh2y =         new JButton("YES"); sh2y.setBounds(29,164,82,40);
            sh2n =          new JButton("NO"); sh2n.setBounds(111,164,82,40);
            sh3y =         new JButton("YES"); sh3y.setBounds(29,207,82,39);
            sh3n =          new JButton("NO"); sh3n.setBounds(111,207,82,39);
            sh4y =         new JButton("YES"); sh4y.setBounds(29,250,82,39);
            sh4n =          new JButton("NO"); sh4n.setBounds(111,250,82,39);
            JButton sh5y = new JButton("YES"); sh5y.setBounds(29,292,82,39);
            JButton sh5n =  new JButton("NO"); sh5n.setBounds(111,292,82,39);
            JButton sh6y = new JButton("YES"); sh6y.setBounds(29,335,82,39);
            JButton sh6n =  new JButton("NO"); sh6n.setBounds(111,335,82,39);
            
            lo1y =         new JButton("YES"); lo1y.setBounds(628,63,82,39);
            lo1n =          new JButton("NO"); lo1n.setBounds(710,63,82,39);
            lo2y =         new JButton("YES"); lo2y.setBounds(627,164,82,40);
            lo2n =          new JButton("NO"); lo2n.setBounds(709,164,82,40);
            lo3y =         new JButton("YES"); lo3y.setBounds(627,207,82,39);
            lo3n =          new JButton("NO"); lo3n.setBounds(709,207,82,39);
            lo4y =         new JButton("YES"); lo4y.setBounds(627,250,82,39);
            lo4n =          new JButton("NO"); lo4n.setBounds(709,250,82,39);
            JButton lo5y = new JButton("YES"); lo5y.setBounds(627,292,82,39);
            JButton lo5n =  new JButton("NO"); lo5n.setBounds(709,292,82,39);
            JButton lo6y = new JButton("YES"); lo6y.setBounds(627,335,82,39);
            JButton lo6n =  new JButton("NO"); lo6n.setBounds(709,335,82,39);
            
            correctCounter = 0;
            s14animListener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    switch(event.getActionCommand()) {
                        case "sh1y" : sh1n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh1y.setActionCommand(null); break;
                        case "sh1n" : anim2Alert(alertTextA); break;
                        case "sh2y" : anim2Alert(alertTextB); break;
                        case "sh2n" : sh2y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh2n.setActionCommand(null); break;
                        case "sh3y" : sh3n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh3y.setActionCommand(null); break;
                        case "sh3n" : anim2Alert(alertTextC); break;
                        case "sh4y" : sh4n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh4y.setActionCommand(null); break;
                        case "sh4n" : anim2Alert(alertTextC); break;
                        case "sh5y" : anim2Alert(alertTextB); break;
                        case "sh5n" : sh5y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh5n.setActionCommand(null); break;
                        case "sh6y" : sh6n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} sh6y.setActionCommand(null); break;
                        case "sh6n" : anim2Alert(alertTextC); break;
                        
                        case "lo1y" : anim2Alert(alertTextD); break;
                        case "lo1n" : lo1y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo1n.setActionCommand(null); break;
                        case "lo2y" : lo2n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo2y.setActionCommand(null); break;
                        case "lo2n" : anim2Alert(alertTextE); break;
                        case "lo3y" : anim2Alert(alertTextF); break;
                        case "lo3n" : lo3y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo3n.setActionCommand(null); break;
                        case "lo4y" : anim2Alert(alertTextF); break;
                        case "lo4n" : lo4y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo4n.setActionCommand(null); break;
                        case "lo5y" : lo5n.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo5y.setActionCommand(null); break;
                        case "lo5n" : anim2Alert(alertTextE); break;
                        case "lo6y" : anim2Alert(alertTextF); break;
                        case "lo6n" : lo6y.setVisible(false); correctCounter++; if(correctCounter == 12) {anim2Alert(alertTextG);} lo6n.setActionCommand(null); break;
                        default: break;
                    }
                }
            }; 
            
            sh1y.setActionCommand("sh1y"); sh1y.addActionListener(s14animListener);
            sh1n.setActionCommand("sh1n"); sh1n.addActionListener(s14animListener);
            sh2y.setActionCommand("sh2y"); sh2y.addActionListener(s14animListener);
            sh2n.setActionCommand("sh2n"); sh2n.addActionListener(s14animListener);
            sh3y.setActionCommand("sh3y"); sh3y.addActionListener(s14animListener);
            sh3n.setActionCommand("sh3n"); sh3n.addActionListener(s14animListener);
            sh4y.setActionCommand("sh4y"); sh4y.addActionListener(s14animListener);
            sh4n.setActionCommand("sh4n"); sh4n.addActionListener(s14animListener);
            sh5y.setActionCommand("sh5y"); sh5y.addActionListener(s14animListener);
            sh5n.setActionCommand("sh5n"); sh5n.addActionListener(s14animListener);
            sh6y.setActionCommand("sh6y"); sh6y.addActionListener(s14animListener);
            sh6n.setActionCommand("sh6n"); sh6n.addActionListener(s14animListener);
            
            lo1y.setActionCommand("lo1y"); lo1y.addActionListener(s14animListener);
            lo1n.setActionCommand("lo1n"); lo1n.addActionListener(s14animListener);
            lo2y.setActionCommand("lo2y"); lo2y.addActionListener(s14animListener);
            lo2n.setActionCommand("lo2n"); lo2n.addActionListener(s14animListener);
            lo3y.setActionCommand("lo3y"); lo3y.addActionListener(s14animListener);
            lo3n.setActionCommand("lo3n"); lo3n.addActionListener(s14animListener);
            lo4y.setActionCommand("lo4y"); lo4y.addActionListener(s14animListener);
            lo4n.setActionCommand("lo4n"); lo4n.addActionListener(s14animListener);
            lo5y.setActionCommand("lo5y"); lo5y.addActionListener(s14animListener);
            lo5n.setActionCommand("lo5n"); lo5n.addActionListener(s14animListener);
            lo6y.setActionCommand("lo6y"); lo6y.addActionListener(s14animListener);
            lo6n.setActionCommand("lo6n"); lo6n.addActionListener(s14animListener);
            
            dispImage.add(sh1y);
            dispImage.add(sh1n);
            dispImage.add(sh2y);
            dispImage.add(sh2n);
            dispImage.add(sh3y);
            dispImage.add(sh3n);
            dispImage.add(sh4y);
            dispImage.add(sh4n);
            dispImage.add(sh5y);
            dispImage.add(sh5n);
            dispImage.add(sh6y);
            dispImage.add(sh6n);
            
            dispImage.add(lo1y);
            dispImage.add(lo1n);
            dispImage.add(lo2y);
            dispImage.add(lo2n);
            dispImage.add(lo3y);
            dispImage.add(lo3n);
            dispImage.add(lo4y);
            dispImage.add(lo4n);
            dispImage.add(lo5y);
            dispImage.add(lo5n);
            dispImage.add(lo6y);
            dispImage.add(lo6n);
            animPanelAdd();
            break;
            
            default: System.exit(0);
            break;
        }
        btnCont.revalidate(); btnCont.repaint();
        container.updateUI();
        return "Animation";
    }
    
    public void animPanelRemove()
    {
        biggerContainer.removeAll();
        btnCont.removeAll();
        container.removeAll();
        animPanel.removeAll();
        container.add(tabs);
        container.add(exitbtn);
    }
    
    public void animPanelAdd()
    {
        biggerContainer.add(container);
        biggerContainer.add(Box.createVerticalGlue());
        biggerContainer.add(btnCont);
        animPanel.add(dispImage);
        container.add(animPanel);
        if(animCounter > 0) { btnCont.add(back);}
        btnCont.add(Box.createHorizontalGlue());
        if(animCounter < 14) { btnCont.add(next); }
        container.add(dispCaption);
        container.add(spacer2);
    }
    
    public void anim1Alert(int choice, boolean correct)
    {
        if(alert != null) {alert.setVisible(false);}
        alert = new JFrame("Alert");
        alertPanel = new JPanel();
        alertPanel.removeAll();
        alert.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        alert.setLayout(new BorderLayout());
        if(!correct) {
            switch(choice) {
                case 1: alert.add(new JLabel("<html><p>Incorrect. "+alertTextA), BorderLayout.NORTH); break;
                case 2: alert.add(new JLabel("<html><p>Incorrect. "+alertTextB), BorderLayout.NORTH); break;
                case 3: alert.add(new JLabel("<html><p>Incorrect. "+alertTextC), BorderLayout.NORTH); break;
            }
        } else {
            switch(choice) {
                case 1: alert.add(new JLabel("<html><p>Correct. "+alertTextA), BorderLayout.NORTH); break;
                case 2: alert.add(new JLabel("<html><p>Correct. "+alertTextB), BorderLayout.NORTH); break;
                case 3: alert.add(new JLabel("<html><p>Correct. "+alertTextC), BorderLayout.NORTH); break;
            }
        }
        JButton closeBtn = new JButton("Close");
        closeBtn.setActionCommand("close");
        closeBtn.addActionListener(actionListener);
        alertPanel.add(closeBtn, BorderLayout.SOUTH);
        alert.add(alertPanel);
        alert.pack();
        alert.setSize(300,300);
        alert.setResizable(false);
        alert.setVisible(true);
    }
    
    public void anim2Alert(String alertText)
    {
        if(alert != null) {alert.setVisible(false);}
        alert = new JFrame("Alert");
        alertPanel = new JPanel();
        alertPanel.removeAll();
        alert.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        alert.setLayout(new BorderLayout());
        JButton closeBtn = new JButton("Close");
        closeBtn.setActionCommand("close");
        closeBtn.addActionListener(actionListener);
        alert.add(new JLabel(alertText), BorderLayout.NORTH);
        alertPanel.add(closeBtn, BorderLayout.SOUTH);
        alert.add(alertPanel);
        alert.pack();
        alert.setSize(300,300);
        alert.setResizable(false);
        alert.setVisible(true);
    }
    
    /**
     * 
     * @return "Conclusion"
     */
    public String startConclusion()
    {
        animCounter = 0;
        quizCounter = 0;
        biggerContainer.removeAll();
        container.removeAll();
        startPanel.removeAll();
        container.add(tabs); container.add(exitbtn, BorderLayout.EAST);
        JLabel endText = new JLabel(
        "<html><center><h1>The Effect of Interrupted Days and Nights</h1></center><p>"+
        "How do plants measure the length of a dark night? The photoreceptor<br>"+
        "molecule, phytochrome, appears to play a key role. The active form of<br>"+
        "phytochrome - P<sub>fr</sub> - promotes flowering in long-day plants, but inhibits<br>"+
        "flowering in short-day plants.<br><br>"+
        "It was once hypothesized that the timing mechanism might simply be the<br>"+
        "slow conversion of a phytochrome during the night from the P<sub>fr</sub><br>"+
        "form - produced during the light hours - to the P<sub>r</sub> form. Such<br>"+
        "phytochrome conversion would function as an 'hourglass,' and the effect<br>"+
        "of a night would depend simply upon whether all the phytochrome had<br>"+
        "been converted. However, this suggestion is inconsistent with many<br>"+
        "experimental observations, such as the fact that when a plant is subjected<br>"+
        "to a dark period several days in duration, the plant's sensitivity to a light<br>"+
        "flash during the long night varies on a roughly 24-hour cycle. Such data<br>"+
        "suggest instead that the phytochrome is only a photoreceptor, and that<br>"+
        "the timekeeping role is played by a biological clock that is linked to the<br>"+
        "phytochrome (which sets the clock) and also to the production of flowers.<br>"+
        "</p></html>");
        endText.setFont(new Font(endText.getFont().getFontName(), Font.PLAIN, 24));
        startPanel.add(endText);
        container.add(startPanel);
        biggerContainer.add(container);
        container.updateUI();
        return "Conclusion";
    }
    
    /**
     * 
     * @return "Quiz"
     */
    public String startQuiz()
    {
        animCounter = 0;
        dispImage = new JLabel(); dispImage.setLayout(null);
        biggerContainer.removeAll();
        container.removeAll();
        btnCont.removeAll();
        container.add(tabs); container.add(exitbtn, BorderLayout.EAST);
        
        qNext = new JButton("Next");
        qNext.setActionCommand("qnex");
        qNext.addActionListener(actionListener);
        
        qBack = new JButton("Back");
        qBack.setActionCommand("qbac");
        qBack.addActionListener(actionListener);
        
        switch(quizCounter) {
            case 1: 
            dispImage.setIcon(new ImageIcon(loadBImage("QuizPlant1.JPG")));
            JButton A = new JButton("A"); A.setBounds(72,130,45,45);
            JButton B = new JButton("B"); B.setBounds(72,173,45,45);
            JButton C = new JButton("C"); C.setBounds(72,216,45,45);
            JButton D = new JButton("D"); D.setBounds(72,259,45,45);
            
            A.setFont(new Font(A.getFont().getFontName(), Font.PLAIN, 15));
            B.setFont(new Font(B.getFont().getFontName(), Font.PLAIN, 15));
            C.setFont(new Font(C.getFont().getFontName(), Font.PLAIN, 15));
            D.setFont(new Font(D.getFont().getFontName(), Font.PLAIN, 15));
            
            alertTextA = "<html><p>Incorrect. Although having a short day typically means that a long night follows, it is the long night that is the important factor in flowering.</p></html>";
            alertTextB = "<html><p>Incorrect. Try again.</p></html>";
            alertTextC = "<html><p>Incorrect. Try again.</p></html>";
            alertTextD = "<html><p>Correct! Experiments show that one or more long nights of uninterrupted darkness triggers flowering in short-day plants.</p></html>";
            
            quiz1Listener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    switch(event.getActionCommand()) {
                        case "A" : quizAlert(alertTextA); break;
                        case "B" : quizAlert(alertTextB); break;
                        case "C" : quizAlert(alertTextC); break;
                        case "D" : A.setVisible(false); B.setVisible(false); C.setVisible(false); quizAlert(alertTextD); break;
                    }
                }
            };
            
            A.setActionCommand("A"); A.addActionListener(quiz1Listener); dispImage.add(A);
            B.setActionCommand("B"); B.addActionListener(quiz1Listener); dispImage.add(B);
            C.setActionCommand("C"); C.addActionListener(quiz1Listener); dispImage.add(C);
            D.setActionCommand("D"); D.addActionListener(quiz1Listener); dispImage.add(D);
            
            break;
            
            case 2: 
            dispImage.setIcon(new ImageIcon(loadBImage("QuizPlant2.JPG")));
            A = new JButton("A"); A.setBounds(72,173,45,45);
            B = new JButton("B"); B.setBounds(72,216,45,45);
            
            A.setFont(new Font(A.getFont().getFontName(), Font.PLAIN, 15));
            B.setFont(new Font(B.getFont().getFontName(), Font.PLAIN, 15));
            
            alertTextA = "<html><p>Incorrect. Short-day plants require one or more long nights of uninterrupted darkness in order to flower.</p></html>";
            alertTextB = "<html><p>Correct! Short-day plants require one or more long nights of uninterrupted darkness in order to flower.</p></html>";
            
            quiz2Listener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    switch(event.getActionCommand()) {
                        case "A" : quizAlert(alertTextA); break;
                        case "B" : A.setVisible(false); quizAlert(alertTextB); break;
                    }
                }
            };
            
            A.setActionCommand("A"); A.addActionListener(quiz2Listener); dispImage.add(A);
            B.setActionCommand("B"); B.addActionListener(quiz2Listener); dispImage.add(B);
            
            break;
            
            case 3: 
            dispImage.setIcon(new ImageIcon(loadBImage("QuizPlant3.JPG")));
            A = new JButton("A"); A.setBounds(72,173,45,45);
            B = new JButton("B"); B.setBounds(72,216,45,45);
            
            A.setFont(new Font(A.getFont().getFontName(), Font.PLAIN, 15));
            B.setFont(new Font(B.getFont().getFontName(), Font.PLAIN, 15));
            
            alertTextA = "<html><p>Incorrect. If a flash of red light interrupts a long night, the phytochrome molecules reset themselves converting from P<sub>r</sub> back into P<sub>fr</sub>. In short-day plants, P<sub>fr</sub> inhibits flowering.</p></html>";
            alertTextB = "<html><p>Correct! If a flash of red light interrupts a long night, the phytochrome molecules reset themselves converting from P<sub>r</sub> back into P<sub>fr</sub>. In short-day plants, P<sub>fr</sub> inhibits flowering.</p></html>";
            
            quiz3Listener = new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    switch(event.getActionCommand()) {
                        case "A" : quizAlert(alertTextA); break;
                        case "B" : A.setVisible(false); quizAlert(alertTextB); break;
                    }
                }
            };
            
            A.setActionCommand("A"); A.addActionListener(quiz3Listener); dispImage.add(A);
            B.setActionCommand("B"); B.addActionListener(quiz3Listener); dispImage.add(B);
            
            break;
        }
        container.add(dispImage);
        biggerContainer.add(container);
        if(quizCounter > 1) { btnCont.add(qBack);}
        btnCont.add(Box.createHorizontalGlue());
        if(quizCounter < 3) { btnCont.add(qNext); }
        biggerContainer.add(btnCont);
        container.updateUI();
        btnCont.revalidate(); btnCont.repaint();
        return "Quiz";
    }
    
    /**
     * Quiz alert method
     */
    public void quizAlert(String alertText)
    {
        if(alert != null) {alert.setVisible(false);}
        alert = new JFrame("Alert");
        alertPanel = new JPanel();
        alertPanel.removeAll();
        alert.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        alert.setLayout(new BorderLayout());
        JButton closeBtn = new JButton("Close");
        closeBtn.setActionCommand("close");
        closeBtn.addActionListener(actionListener);
        alert.add(new JLabel(alertText), BorderLayout.NORTH);
        alertPanel.add(closeBtn, BorderLayout.SOUTH);
        alert.add(alertPanel);
        alert.pack();
        alert.setSize(300,300);
        alert.setResizable(false);
        alert.setVisible(true);
    }
}