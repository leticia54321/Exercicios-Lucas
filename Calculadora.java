import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame implements ActionListener {

    private JTextField display;
    private double valor1, valor2, resultado;
    private String operador;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4));

        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "^", "√", "+"
        };

        for (String textoBotao : botoes) {
            JButton botao = new JButton(textoBotao);
            botao.addActionListener(this);
            panel.add(botao);
        }

        add(panel, BorderLayout.CENTER);

        JButton botaoIgual = new JButton("=");
        botaoIgual.addActionListener(this);
        add(botaoIgual, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.matches("\\d")) {
            display.setText(display.getText() + comando);
        } else if (comando.equals("+") || comando.equals("-") || comando.equals("*") || comando.equals("/")) {
            valor1 = Double.parseDouble(display.getText());
            operador = comando;
            display.setText("");
        } else if (comando.equals("^")) {
            valor1 = Double.parseDouble(display.getText());
            operador = "^";
            display.setText("");
        } else if (comando.equals("√")) {
            valor1 = Double.parseDouble(display.getText());
            resultado = Math.sqrt(valor1);
            display.setText(String.valueOf(resultado));
        } else if (comando.equals("=")) {
            valor2 = Double.parseDouble(display.getText());
            switch (operador) {
                case "+":
                    resultado = valor1 + valor2;
                    break;
                case "-":
                    resultado = valor1 - valor2;
                    break;
                case "*":
                    resultado = valor1 * valor2;
                    break;
                case "/":
                    resultado = valor1 / valor2;
                    break;
                case "^":
                    resultado = Math.pow(valor1, valor2);
                    break;
            }
            display.setText(String.valueOf(resultado));
        }
    }

    public static void main(String[] args) {
        new Calculadora();
    }
}
