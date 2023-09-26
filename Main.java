package com.poo;

import java.util.Scanner;

// Classe que representa um nó da árvore
class No {
    int valor;  // Valor armazenado no nó
    No esquerdo;
    No direito;  // "Ponteiros" para os nós filhos à esquerda e à direita

    // Construtor do nó
    public No(int valor) {
        this.valor = valor;  // Inicializa o valor do nó
        esquerdo = null;
        direito = null;  // Inicializa os nós filhos como nulos
    }
}

// Classe que contém os métodos da árvore binária de busca
class ArvoreBinaria {
    No raiz;  // "Ponteiro" para o nó raiz da árvore
    
    // Exercício 1: Inserir
    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);  // Chama o método recursivo de inserção começando do nó raiz
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) 
            return new No(valor);  // Se o nó atual é nulo, cria um novo nó com o valor e retorna

        // Decide se deve inserir à esquerda ou à direita com base no valor
        if (valor < atual.valor)
            atual.esquerdo = inserirRecursivo(atual.esquerdo, valor);  // Se o valor é menor, insere à esquerda
        else
            atual.direito = inserirRecursivo(atual.direito, valor);  // Se o valor é maior ou igual, insere à direita

        return atual;  // Retorna o nó atual após a inserção
    }

    // Exercício 2: Percorrer em ordem
    public void mostrarEmOrdem() {
        mostrarEmOrdemRecursivo(raiz);  // Chama o método recursivo de em ordem começando do nó raiz
        System.out.println();  // Imprime uma linha vazia após mostrar todos os valores
    }

    private void mostrarEmOrdemRecursivo(No atual) {
        if (atual != null) {
            mostrarEmOrdemRecursivo(atual.esquerdo);  // Visita a subárvore esquerda
            System.out.print(atual.valor + " ");  // Imprime o valor do nó atual
            mostrarEmOrdemRecursivo(atual.direito);  // Visita a subárvore direita
        }
    }

    // Exercício 2: Percorrer em pré-ordem
    public void mostrarPreOrdem() {
        mostrarPreOrdemRecursivo(raiz);  // Chama o método recursivo de pré-ordem começando do nó raiz
        System.out.println();  // Imprime uma linha vazia após mostrar todos os valores
    }

    private void mostrarPreOrdemRecursivo(No atual) {
        if (atual != null) {
            System.out.print(atual.valor + " ");  // Imprime o valor do nó atual
            mostrarPreOrdemRecursivo(atual.esquerdo);  // Visita a subárvore esquerda
            mostrarPreOrdemRecursivo(atual.direito);  // Visita a subárvore direita
        }
    }

    // Exercício 2: Percorrer em pós-ordemm
    public void mostrarPosOrdem() {
        mostrarPosOrdemRecursivo(raiz);  // Chama o método recursivo de pós-ordem começando do nó raiz
        System.out.println();  // Imprime uma linha vazia após mostrar todos os valores
    }

    private void mostrarPosOrdemRecursivo(No atual) {
        if (atual != null) {
            mostrarPosOrdemRecursivo(atual.esquerdo);  // Visita a subárvore esquerda
            mostrarPosOrdemRecursivo(atual.direito);  // Visita a subárvore direita
            System.out.print(atual.valor + " ");  // Imprime o valor do nó atual
        }
    }

    // Exercício 3: Remover maior elemento
    public void removerMaior() {
        raiz = removerMaiorRecursivo(raiz);  // Chama o método recursivo de remoção do maior valor começando do nó raiz
    }

    private No removerMaiorRecursivo(No atual) {
        if (atual == null) 
            return null;  // Se o nó atual é nulo, retorna nulo

        // Se o nó atual não tem filho à direita, ele é o maior valor, então retorna o filho à esquerda
        if (atual.direito == null) 
            return atual.esquerdo;

        // Chama recursivamente o método para o filho à direita
        atual.direito = removerMaiorRecursivo(atual.direito);
        return atual;  // Retorna o nó atual após a remoção
    }

    // Exercício 4: Remover menor elemento
    public void removerMenor() {
        raiz = removerMenorRecursivo(raiz);  // Chama o método recursivo de remoção do menor valor começando do nó raiz
    }

    private No removerMenorRecursivo(No atual) {
        if (atual == null) 
            return null;  // Se o nó atual é nulo, retorna nulo

        // Se o nó atual não tem filho à esquerda, ele é o menor valor, então retorna o filho à direita
        if (atual.esquerdo == null) 
            return atual.direito;

        // Chama recursivamente o método para o filho à esquerda
        atual.esquerdo = removerMenorRecursivo(atual.esquerdo);
        return atual;  // Retorna o nó atual após a remoção
    }

    // Exercício 5: Remover elemento específico
    public void remover(int valor) {
        raiz = removerRecursivo(raiz, valor);  // Chama o método recursivo de remoção do valor específico começando do nó raiz
    }

    private No removerRecursivo(No atual, int valor) {
        if (atual == null) 
            return null;  // Se o nó atual é nulo, retorna nulo

        // Compara o valor com o valor do nó atual e decide se deve ir para a esquerda ou direita
        if (valor < atual.valor)
            atual.esquerdo = removerRecursivo(atual.esquerdo, valor);  // Se o valor é menor, vai para a esquerda
        
        else if (valor > atual.valor)
            atual.direito = removerRecursivo(atual.direito, valor);  // Se o valor é maior, vai para a direita
        
        else {
            // O valor a ser removido foi encontrado
            if (atual.esquerdo == null) 
                return atual.direito;  // Se não tem filho à esquerda, retorna o filho à direita
            if (atual.direito == null) 
                return atual.esquerdo;  // Se não tem filho à direita, retorna o filho à esquerda

            // Se o nó tem dois filhos, substitui o valor do nó pelo menor valor da subárvore direita
            No temp = atual;
            
            atual = minimo(temp.direito);
            
            atual.direito = removerMenorRecursivo(temp.direito);
            
            atual.esquerdo = temp.esquerdo;
        }

        return atual;  // Retorna o nó atual após a remoção
    }

    // Método para encontrar o nó com o menor valor numa subárvore
    private No minimo(No raiz) {
        No atual = raiz;
        
        while (atual.esquerdo != null) 
            atual = atual.esquerdo;  // Vai para a esquerda até encontrar o menor valor
        return atual;
    }
}

public class Main {
    public static void main(String[] args) {
        
        ArvoreBinaria arvore = new ArvoreBinaria();
        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("Escolha uma opção:");
            System.out.println("1: Inserir valor");
            System.out.println("2: Mostrar em ordem");
            System.out.println("3: Mostrar em pré-ordem");
            System.out.println("4: Mostrar em pós-ordem");
            System.out.println("5: Remover maior valor");
            System.out.println("6: Remover menor valor");
            System.out.println("7: Remover valor específico");
            System.out.println("0: Sair");

            int escolha = scanner.nextInt();

            if (escolha == 0) break;

            switch (escolha) {
                case 1:
                    System.out.print("Digite o valor para inserir: ");
                    int valor = scanner.nextInt();
                    arvore.inserir(valor);
                    break;
                    
                case 2:
                    System.out.println("Árvore em ordem:");
                    arvore.mostrarEmOrdem();
                    break;
                    
                case 3:
                    System.out.println("Árvore em pré-ordem:");
                    arvore.mostrarPreOrdem();
                    break;
                    
                case 4:
                    System.out.println("Árvore em pós-ordem:");
                    arvore.mostrarPosOrdem();
                    break;
                    
                case 5:
                    arvore.removerMaior();
                    System.out.println("Maior valor removido.");
                    break;
                    
                case 6:
                    arvore.removerMenor();
                    System.out.println("Menor valor removido.");
                    break;
                    
                case 7:
                    System.out.print("Digite o valor para remover: ");
                    int valorRemover = scanner.nextInt();
                    arvore.remover(valorRemover);
                    System.out.println("Valor " + valorRemover + " removido.");
                    break;
                    
            }
        }

        scanner.close();
    }
}
