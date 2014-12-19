package gui;

import acp.ApplicationContextProvider;
import org.quartz.JobExecutionContext;
import org.quartz.SchedulerException;
import org.quartz.impl.StdScheduler;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.swing.*;
import java.util.List;

/**
 * Created by MMO on 18.12.2014.
 */
public class MainFrame extends JFrame {

    private JLabel goLabel;
    private JButton startButton;
    private JButton stopButton;

    private JLabel stopLabel;

    public MainFrame() {
        initComponents();
    }

    private void initComponents() {

        goLabel = new JLabel();
        startButton = new JButton();
        stopButton = new JButton();
        stopLabel = new JLabel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thread Controller");

        goLabel.setText("Go");

        stopButton.setText("Stop Execution");
        stopButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopExecution(evt);
            }
        });

        startButton.setText("Start Execution");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startExecution(evt);
            }
        });

        stopLabel.setText("Stop");
        startButton.setEnabled(false);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                      .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(startButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                      .addComponent(goLabel))
                                                      .addGroup(layout.createSequentialGroup()
                                                                      .addComponent(stopButton)
                                                                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                      .addComponent(stopLabel))).addContainerGap(27, Short.MAX_VALUE)));

        layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {stopButton});

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                      .addGroup(layout.createSequentialGroup()
                                      .addContainerGap()
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                      .addComponent(startButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                      .addComponent(goLabel))
                                      .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                      .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                      .addComponent(stopButton)
                                                      .addComponent(stopLabel)).addContainerGap(21, Short.MAX_VALUE))
                               );
        pack();
    }

    private void stopExecution(java.awt.event.ActionEvent evt) {
        AnnotationConfigApplicationContext ac = (AnnotationConfigApplicationContext) ApplicationContextProvider.getApplicationContext();
        StdScheduler bean = (StdScheduler) ac.getBean("parallelSchedulerFactoryBean");
        try {
            bean.pauseAll();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
        List<JobExecutionContext> allJobs = bean.getCurrentlyExecutingJobs();

        for (JobExecutionContext job : allJobs) {
            goLabel.setText(job.getNextFireTime().toString());
        }

        startButton.setEnabled(true);
        stopButton.setEnabled(false);

    }

    private void startExecution(java.awt.event.ActionEvent evt) {
        AnnotationConfigApplicationContext ac = (AnnotationConfigApplicationContext) ApplicationContextProvider.getApplicationContext();
        StdScheduler bean = (StdScheduler) ac.getBean("parallelSchedulerFactoryBean");
        try {
            bean.resumeAll();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }

        List<JobExecutionContext> allJobs = bean.getCurrentlyExecutingJobs();

        for (JobExecutionContext job : allJobs) {
            goLabel.setText(job.getNextFireTime().toString());
        }



        startButton.setEnabled(false);
        stopButton.setEnabled(true);

    }

}

