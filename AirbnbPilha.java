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
			
			
			Pilha PilhaIds = new Pilha(7000);
			
			
			String codigo;
			codigo = MyIO.readString();

			int aux = 0;
			while (codigo.equals("FIM") == false){
			
				if(PilhaIds.pilhaCheia()) {
					try {
					PilhaIds.desempilhar();
					}
					catch(IOException ex) {
						System.out.println(ex.getMessage());
					}
				}
				
				if (PilhaIds.pilhaCheia() == false) {
					aux = Integer.parseInt(codigo);

					for (int i = 0; i < vetor.length; i++) {
						if (vetor[i].getRoomID() == aux) {
							PilhaIds.empilhar(vetor[i]);
							i = vetor.length;
						}
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
							if(PilhaIds.pilhaCheia() == true) {
								PilhaIds.desempilhar();
							}
							PilhaIds.empilhar(vetor[j]);
							j = vetor.length - 1;
						}

					}
				}	
					
			}
			PilhaIds.mostrar();
			

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

class Pilha {
	private Acomodacao pilha[];
	private int topo;
	
	public Pilha(int tamanho) {
		
		pilha = new Acomodacao[tamanho];
		topo = 0;
	}
	
	public Pilha() {
		
	}
	
	public boolean pilhaVazia() {
		
		if (topo == 0)
			return true;
		else
			return false;
	}
	
	public boolean pilhaCheia() {
		
		if (topo == pilha.length)
			return true;
		else
			return false;
	}
	
	public void empilhar(Acomodacao novo) throws Exception{
		
		if (! pilhaCheia()) {
			pilha[topo] = novo;
			topo++;
		} else
			throw new Exception("Não foi possível empilhar o elemento: a pilha está cheia!");
	}
	
	public Acomodacao desempilhar() throws Exception{
		
		Acomodacao desempilhado;
		
		if (! pilhaVazia()) {
			topo--;
			desempilhado = pilha[topo];
			return desempilhado;
		} else
			throw new Exception("Não foi possível desempilhar nenhum elemento: a pilha está vazia!");
	}
	
	public Acomodacao consultarTopo() throws Exception{
		
		Acomodacao top;
		
		if (! pilhaVazia()) {
			top = pilha[topo - 1];
			return top;
		} else
			throw new Exception("Não há nenhum elemento na pilha: a pilha está vazia!");
	}
	public void mostrar() {
		int posicao = 0;
		for(int i = 0; i < topo; i++) {
		System.out.print("[" + posicao + "] ");
		pilha[i].imprimir();
		posicao++;
		}
	}

}