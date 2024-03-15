package com.projetoteste;

import com.projetoteste.entidades.Carro;
import com.projetoteste.repositorio.CarroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class ProjetoTesteApplication implements CommandLineRunner {
    
    @Autowired
    private CarroRepositorio carroRepositorio;

    @Override
    public void run(String... args) throws Exception {
        Scanner teclado = new Scanner(System.in);

        while (true) {
            System.out.println("Menu de opções!!!");
            System.out.println("1 - Adicionar carro");
            System.out.println("2 - Listar carros");
            System.out.println("3 - Alterar carro");
            System.out.println("4 - Sair");

            int opcao = Integer.parseInt(teclado.nextLine());

            if (opcao == 1) {
                System.out.print("Informe o modelo: ");
                String novoModelo = teclado.next();
                System.out.print("Informe a montadora: ");
                String novoMontadora = teclado.next();
                System.out.print("Informe o ano: ");
                String novoAno = teclado.next();

                Carro novo = new Carro();
                novo.setModelo(novoModelo);
                novo.setMontadora(novoMontadora);
                novo.setAno(novoAno);

                carroRepositorio.save(novo);
            }
            else if (opcao == 2) {
                for (Carro carro : carroRepositorio.findAll()) {
                    System.out.println("Modelo: " + carro.getModelo() + " | Montadora: " + carro.getMontadora() + " | Ano: " + carro.getAno());
                }
            } else if (opcao == 3) {
                // listando
                for (Carro c: carroRepositorio.findAll()) {
                    System.out.println("id: " + c.getId() + " - Modelo: " + c.getModelo() + " - Ano: " + c.getAno());
                }

                // acessando o carro que vai ser alterado
                System.out.println("Digite o id do carro que você quer alterar: ");
                long id = Integer.parseInt(teclado.nextLine());
                Carro c = carroRepositorio.findById(id).get();

                // alterando os campos do carro
                System.out.println("Digite o novo modelo: ");
                String modelo = teclado.nextLine();
                c.setModelo(modelo);

                // salvando as alterações no banco
                carroRepositorio.save(c);
            } else if (opcao == 4) {
                System.out.println("Obrigado por utilizar o programa!!");
                break;
            }
            else {
                System.out.println("Opção inválida!!");
            }
        }


    }
    public static void main(String[] args) {
        SpringApplication.run(ProjetoTesteApplication.class, args);
    }
    
}
