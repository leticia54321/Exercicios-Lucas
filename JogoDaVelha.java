package exercicios;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Velha extends JFrame {

    private JButton[][] botoes = new JButton[3][3];
    private boolean vezDoJogadorO = true;
    private boolean jogoEncerrado = false;
    private JLabel placarO;
    private JLabel placarX;
    private int pontosO = 0;
    private int pontosX = 0;

    public Velha() {
        super("Jogo da Velha");
        setSize(700, 800);
        setResizable(false);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.black);

        JPanel painelPlacar = new JPanel();
        painelPlacar.setLayout(new GridLayout(1, 2));
        placarO = new JLabel("Jogador O: " + pontosO);
        placarO.setFont(new Font("Arial", Font.BOLD, 24));
        placarO.setHorizontalAlignment(SwingConstants.CENTER);
        placarX = new JLabel("Jogador X: " + pontosX);
        placarX.setFont(new Font("Arial", Font.BOLD, 24));
        placarX.setHorizontalAlignment(SwingConstants.CENTER);
        painelPlacar.add(placarO);
        painelPlacar.add(placarX);

        JPanel painelJogo = new JPanel();
        painelJogo.setLayout(new GridLayout(3, 3, 10, 10));
        painelJogo.setBackground(Color.black);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j] = new JButton();
                botoes[i][j].setFont(new Font("Calibri", Font.PLAIN, 200));
                botoes[i][j].setBackground(Color.white);
                botoes[i][j].setForeground(Color.black);
                painelJogo.add(botoes[i][j]);

                final int linha = i;
                final int coluna = j;
                botoes[i][j].addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        if (!jogoEncerrado && botoes[linha][coluna].getText().isEmpty()) { 
                            botoes[linha][coluna].setText(vezDoJogadorO ? "O" : "X"); 
                            vezDoJogadorO = !vezDoJogadorO;
                            if (verificarVencedor()) {
                                jogoEncerrado = true;
                                String vencedor = vezDoJogadorO ? "X" : "O";
                                JOptionPane.showMessageDialog(null, vencedor + " venceu!");

                                if (vencedor.equals("O")) {
                                    atualizarPlacarO();
                                } else {
                                    atualizarPlacarX();
                                }

                                reiniciarJogo();
                            } else if (tabuleiroCheio()) {
                                jogoEncerrado = true;
                                JOptionPane.showMessageDialog(null, "Empate!");
                                reiniciarJogo();
                            }
                        }                    
                    }
                });
            }          
        }

        add(painelPlacar, BorderLayout.NORTH);
        add(painelJogo, BorderLayout.CENTER);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void atualizarPlacarO() {
        pontosO++;
        placarO.setText("Jogador O: " + pontosO);
    }

    private void atualizarPlacarX() {
        pontosX++;
        placarX.setText("Jogador X: " + pontosX);
    }

    private boolean verificarVencedor() {
        for (int i = 0; i < 3; i++) {
            if (botoes[i][0].getText().equals(botoes[i][1].getText()) &&
                botoes[i][1].getText().equals(botoes[i][2].getText()) && 
                !botoes[i][0].getText().isEmpty()) {
                return true;
            }
            if (botoes[0][i].getText().equals(botoes[1][i].getText()) &&
                botoes[1][i].getText().equals(botoes[2][i].getText()) && 
                !botoes[0][i].getText().isEmpty()) {
                return true;
            }
        }

        if (botoes[0][0].getText().equals(botoes[1][1].getText()) &&
            botoes[1][1].getText().equals(botoes[2][2].getText()) &&
            !botoes[0][0].getText().isEmpty()) {
            return true;
        }
        if (botoes[0][2].getText().equals(botoes[1][1].getText()) &&
            botoes[1][1].getText().equals(botoes[2][0].getText()) &&
            !botoes[0][2].getText().isEmpty()) {
            return true;
        }

        return false;
    }

    private boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (botoes[i][j].getText().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    private void reiniciarJogo() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                botoes[i][j].setText("");
            }
        }
        jogoEncerrado = false;
        vezDoJogadorO = true;
    }

    public static void main(String[] args) {
        new Velha();
    }
}
