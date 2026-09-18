public class Plano {

    private String nome;
    private double contribuicaoMensal;

        public Plano(String nome, double contribuicaoMensal) {
            this.nome = nome;
            this.contribuicaoMensal = contribuicaoMensal;
        }
    
        public void exibirResumo(){
            System.out.println("Plano: "+ nome);
            System.out.println("Contribuição mensal: R$ " + contribuicaoMensal);
        }
    }
    