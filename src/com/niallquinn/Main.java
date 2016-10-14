package com.niallquinn;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        JFrame frame = new ChessProject();
        frame.setDefaultCloseOperation(2);
        frame.pack();
        frame.setResizable(true);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }
}
