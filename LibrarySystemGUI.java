import java.awt.*;
import javax.swing.*;
import java.util.*;

public final class LibrarySystemGUI extends JFrame {
    DefaultListModel<String> model = new DefaultListModel<>();
    ArrayList<Book> books = new ArrayList<>();
    JList<String> bookList = new JList<>(model);
    JTextField bookField = new JTextField(17);

    public LibrarySystemGUI() {
        setTitle("Library System");
        setSize(385, 280);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Arrays.asList("Java Basics", "Swing in Action", "Data Science 101", "Algorithms Unlocked",
                    "Operating System Concepts", "Python Crash Course", "Head First Design Patterns")
            .forEach(title -> books.add(new Book(title)));
        refresh();

        JButton issueBtn = new JButton("Issue"), returnBtn = new JButton("Return"),
                unissueBtn = new JButton("Unissue"), refreshBtn = new JButton("Refresh");

        issueBtn.addActionListener(e -> handle("Issue"));
        returnBtn.addActionListener(e -> handle("Return"));
        unissueBtn.addActionListener(e -> handle("Unissue"));
        refreshBtn.addActionListener(e -> refresh());

        JPanel panel = new JPanel();
        panel.add(new JLabel("Book:")); panel.add(bookField);
        panel.add(issueBtn); panel.add(returnBtn);
        panel.add(unissueBtn); panel.add(refreshBtn);

        add(new JScrollPane(bookList), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);
    }

    void refresh() {
        model.clear();
        for (Book b : books)
            model.addElement(b.title + (b.issued ? " (Issued)" : ""));
    }

    void handle(String action) {
        String title = bookField.getText().trim();
        if (title.isEmpty()) { showMsg("Enter book title."); return; }

        for (Book b : books) {
            if (b.title.equalsIgnoreCase(title)) {
                switch (action) {
                    case "Issue" -> {
                        if (!b.issued) { b.issued = true; showMsg("Book issued: " + title); }
                        else showMsg("Already issued!");
                    }
                    case "Return", "Unissue" -> {
                        if (b.issued) { b.issued = false; showMsg("Book returned: " + title); }
                        else showMsg("Book was not issued.");
                    }
                }
                refresh(); bookField.setText(""); return;
            }
        }
        showMsg("Book not found."); bookField.setText("");
    }

    void showMsg(String msg) { JOptionPane.showMessageDialog(this, msg); }

    static class Book {
        String title; boolean issued;
        Book(String t) { title = t; }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LibrarySystemGUI().setVisible(true));
    }
}