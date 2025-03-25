import java.util.ArrayList;
import java.util.Scanner;


public class Banco {

	private String nome;
	private ArrayList<Conta> contas = new ArrayList<Conta>();

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void getContas() {
		System.out.println("== Lista de Contas ==");
	for (Conta i : contas) {
		  	
			System.out.println(String.format("Titular: %s", i.cliente.getNome()));
			System.out.println(String.format("Agencia: %d", i.agencia));
			System.out.println(String.format("Numero: %d", i.numero));
			System.out.println(String.format("Saldo: %.2f", i.saldo));

			System.out.println("-----------");
		}
		System.out.println("=================");
	}

	public void setContas(Conta conta) {
		contas.add(conta);
	}
	
	public void menu(){
		Scanner sc = new Scanner(System.in);
		int opcao;
		do {System.out.println("Selecione o tipo de conta: \n" + "1: Criar Corrente \n"
				+ "2: Criar Poupança \n" + "3: Todas as Contas \n" + "4: Entrar na Conta  \n" + "0: Desligar");

		opcao = sc.nextInt();
		switch (opcao) {
		case 1:
			System.out.println("Digite seu Nome: ");
			String newName = sc.next();
			Cliente cliente = new Cliente();
			cliente.setNome(newName);
			Conta cc = new ContaCorrente(cliente);
			setContas(cc);
			break;
		case 2:
			System.out.println("Digite seu Nome: ");
			String nomePoupanca = sc.next();
			Cliente clientePoupanca = new Cliente();
			clientePoupanca.setNome(nomePoupanca);
			Conta poupanca = new ContaCorrente(clientePoupanca);
			setContas(poupanca);
			break;
		case 3:
			getContas();
			break;
		case 4:
			System.out.println("Digite seu Nome: ");
			String nomeCliente = sc.next();
			for(Conta conta : contas){
				if(conta.getNome().equals(nomeCliente)){
					 conta.imprimirInfosComuns();
					 int opcaoMenu;
					 do {
						 System.out.println("Selecione a Operação "+conta.getNome()+" \n" +"1: Saldo \n"+ "2: Sacar \n"
									+ "3: Depositar \n" + "4: Transferir \n" + "0: Sair");
						 opcaoMenu = sc.nextInt();
						 switch (opcaoMenu) {
							case 1:
								System.out.println(String.format("Saldo: %.2f", conta.saldo));								
								break;
							case 2:
								System.out.println("Digite o valor para sacar: ");
								double valorSaque = sc.nextDouble();
								conta.sacar(valorSaque);
								System.out.println(String.format("Saldo: %.2f", conta.saldo));
								break;
							case 3:
								System.out.println("Digite o valor para Depositar: ");
								double valorDeposito = sc.nextDouble();
								conta.depositar(valorDeposito);
								System.out.println(String.format("Saldo: %.2f", conta.saldo));
								break;
							case 4:
								System.out.println("Digite o nome do cliente para transferir: ");
								String nomeTransferencia = sc.next();
								for(Conta contaTransferência : contas){
									if(contaTransferência.getNome().equals(nomeTransferencia)){
										System.out.println("Digite o valor transferir: ");
										double valorTrasferencia = sc.nextDouble();
										conta.transferir(valorTrasferencia, contaTransferência);
									}};								
								System.out.println(String.format("Saldo: %.2f", conta.saldo));
								break;
							default:
								System.out.println("Opção inválida. Insira um número de 0 a 9.");
						 }

					 }while(opcaoMenu != 0);
				}
				else {
					System.out.println("A conta não existe");
				}

			};
			break;
		default:
			System.out.println("Opção inválida. Insira um número de 0 a 9.");
		}
		} while (opcao != 0);
		sc.close();		
	}

}
