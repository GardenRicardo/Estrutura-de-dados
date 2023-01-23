import java.io.*;

public class Airbnb {

	public static void main(String[] args) {
		MyIO.setCharset("UTF-8");
		int tamanho = 0;
		try {
			FileReader arquivoLeitura = new FileReader(
					"/tmp/dados_airbnb.txt");
			FileReader arquivoLeitura2 = new FileReader(
					"/tmp/dados_airbnb.txt");
			BufferedReader arquivoTmp = new BufferedReader(arquivoLeitura);
			BufferedReader arquivoTmp2 = new BufferedReader(arquivoLeitura2);

			String texto = arquivoTmp.readLine();

			while (texto != null) {
				texto = arquivoTmp.readLine();
				tamanho++;
			}

			Acomodacao[] vetor = new Acomodacao[tamanho - 1];

			String dados = arquivoTmp2.readLine();
			dados = arquivoTmp2.readLine();
			for (int i = 0; i < vetor.length; i++) {

				vetor[i] = new Acomodacao();
				vetor[i].ler(dados);
				dados = arquivoTmp2.readLine();
			}
			
			
			PilhaCelula PilhaIds = new PilhaCelula();
			PilhaCelula PilhaInversa = new PilhaCelula();
			
			String codigo;
			codigo = MyIO.readString();

			int aux = 0;
			while (codigo.equals("FIM") == false){
				
					aux = Integer.parseInt(codigo);

					for (int i = 0; i < vetor.length; i++) {
						if (vetor[i].getRoomID() == aux) {
							PilhaIds.empilhar(vetor[i]);
							i = vetor.length;
						}
					}
					codigo = MyIO.readString();
				}
				
				

			int instrucoes = MyIO.readInt();
			char comando;
			for (int i = 0; i < instrucoes; i++) {
				comando = MyIO.readChar();
				
				if (comando == 'D') {
					System.out.println("(D) " + PilhaIds.consultarTopo().getRoomID());
					PilhaIds.desempilhar();
					String enter = MyIO.readString();
				} else if (comando == 'E') {
					int pesquisa;
					int aux2 = 0;
					char espaco = MyIO.readChar();
					pesquisa = MyIO.readInt();
					aux2 = pesquisa;

					for (int j = 0; j < vetor.length; j++) {
						if (vetor[j].getRoomID() == aux2) {
							PilhaIds.empilhar(vetor[j]);
							j = vetor.length - 1;
						}

					}
				}	
					
			}
			
			while(PilhaIds.pilhaVazia() == false) {
				PilhaInversa.empilhar(PilhaIds.desempilhar());
			}
			
			PilhaInversa.mostrar();

			arquivoTmp.close();
			arquivoTmp2.close();
			arquivoLeitura.close();
			arquivoLeitura2.close();
		}

		catch (Exception erro) {
			System.out.println(erro.getMessage());
		}
	}

}

class Acomodacao {

		private int roomID;
		private int hostID;
		private String roomType;
		private String country;
		private String city;
		private String neighbourhood;
		private int reviews;
		private double overallSatisfaction;
		private int accommodates;
		private double bedrooms;
		private double price;
		private String propertyType;
		
		public Acomodacao() {
			int roomID = 0;
			int hostID = 0;
			String roomType = null;
			String country = null;
			String city = null;
			String neighbourhood = null;
			int reviews = 0;
			double overallSatisfaction = 0;
			int accommodates = 0;
			double bedrooms = 0;
			double price = 0;
			String propertyType = null;
		}
		public Acomodacao(int roomID , int hostID , String roomType , String country , String city , String neighbourhood ,
		int reviews , double overallSatisfaction , int accommodates , double bedrooms , double price , String propertyType) {
			
		}
		
		public void setRoomID(String []x) {
			roomID = Integer.parseInt(x[0]);
			
		}
		public int getRoomID() {
			return roomID;
		}
		
		public void setHostID(String [] x) {
			hostID = Integer.parseInt(x[1]);
		}
		public int getHostID() {
			return hostID;
		}
		
		public void setRoomType(String []x ) {
			roomType = x[2];
		}
		public String getRoomType() {
			return roomType;
		}
		
		public void setCountry(String []x) {
				country = x[3];
		}
		public String getCountry() {
			return country;
		}
		
		public void setCity(String []x) {
				city = x[4];
		}
		public String getCity() {
			return city;
		}
		
		public void setNeighbourhood(String []x) {
			neighbourhood = x[5];
		}
		public String getNeighbourhood() {
			return neighbourhood;
		}
		
		public void setReviews(String[] x) {
			reviews = Integer.parseInt(x[6]);
		}
		public int getReviews() {
			return reviews;
		}
		
		public void setOverallSatisfaction(String[] x) {
			overallSatisfaction = Double.valueOf(x[7]).doubleValue();
		}
		public Double getOverallSatisfaction() {
			return overallSatisfaction;
		}
		
		public void setAccommodates(String[] x) {
			accommodates = Integer.parseInt(x[8]);
		}
		public int getAccommodates() {
			return accommodates;
		}
		
		public void setBedrooms(String[] x) {
			bedrooms = Double.valueOf(x[9]).doubleValue();
		}
		public Double getBedrooms() {
			return bedrooms;
		}
		
		public void setPrice(String[] x) {
			price = Double.valueOf(x[10]).doubleValue();
		}
		public Double getPrice() {
			return price;
		}
		
		public void setPropertyType(String[] x) {
			propertyType = x[11];
		}
		public String getPropertyType() {
			return propertyType;
		}
		
		
		
		
		public Acomodacao clone() {
			Acomodacao copia;
			copia = new Acomodacao(roomID , hostID , roomType , country , city , neighbourhood ,
					reviews , overallSatisfaction , accommodates , bedrooms , price , propertyType);
			return (copia);
		}
		
		public void ler(String linha) {
			String ler = linha;
			
			String[] vetorLer = new String[12];
			vetorLer = ler.split("\t");
			setRoomID(vetorLer);
			setHostID(vetorLer);
			setRoomType(vetorLer);
			setCountry(vetorLer);
			setCity(vetorLer);
			setNeighbourhood(vetorLer);
			setReviews(vetorLer);
			setOverallSatisfaction(vetorLer);
			setAccommodates(vetorLer);
			setBedrooms(vetorLer);
			setPrice(vetorLer);
			setPropertyType(vetorLer);
		}
		
		public void imprimir() {
			System.out.println("[" + getRoomID() + " ## " + getHostID() + " ## " + getRoomType() + " ## " + getCountry()
			+ " ## " + getCity() + " ## " + getNeighbourhood() + " ## " + getReviews() + " ## " + getOverallSatisfaction()
			+ " ## " + getAccommodates() + " ## " + getBedrooms() + " ## " + getPrice() + " ## " + getPropertyType() + "]");
		}
		
	}

class PilhaCelula {
	private Celula fundo;
	private Celula topo;
	
	public PilhaCelula() {
		
		Celula sentinela;
		
		sentinela = new Celula();
		fundo = sentinela;
		topo = sentinela;
	}
	
	public boolean pilhaVazia() {
		
		if (fundo == topo)
			return true;
		else
			return false;
	}
	
	public void empilhar(Acomodacao novo) {
		
		Celula novaCelula;
		
		novaCelula = new Celula(novo);
		novaCelula.setProximo(topo);
		topo = novaCelula;
	}
	
	public Acomodacao desempilhar() throws Exception {
		
		Acomodacao desempilhado;
		
		if (! pilhaVazia()) {
			desempilhado = topo.getItem();
			topo = topo.getProximo();
			return desempilhado;
		} else
			throw new Exception ("Não foi possível desempilhar nenhum item: a pilha está vazia!");
	}
	
	public Acomodacao consultarTopo() throws Exception {
		
		if (! pilhaVazia()) {
			return (topo.getItem());
		} else
			throw new Exception("Não foi possível consultar o elemento do topo da pilha: a pilha está vazia!");
	}
	
	public void mostrar() {
		
		if(pilhaVazia() == false) {
		int posicao = 0;
		Celula aux = topo;
		
		while(aux.getProximo() != null){
		System.out.print("[" + posicao + "]");
		aux.getItem().imprimir();
		aux = aux.getProximo();
		posicao++;
		}
		
		}
		else{
			System.out.println("A pilha está vazia");
		}
		
	}
}

class Celula {

		private Acomodacao item;
		private Celula proximo;
		
		public Celula(Acomodacao item) {
			
			this.item = item;
			this.proximo = null;
		}
		
		public Celula() {
		
			this.item = new Acomodacao();
			this.proximo = null;
		}
		
		public Acomodacao getItem() {
			return item;
		}
		
		public void setItem(Acomodacao item) {
			this.item = item;
		}

		public Celula getProximo() {
			return proximo;
		}

		public void setProximo(Celula proximo) {
			this.proximo = proximo;
		}

	}