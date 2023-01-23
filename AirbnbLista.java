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
			
			Lista listaID = new Lista(7000);
			String linha = MyIO.readLine();
			int IDlinha;
			
			while(!linha.equals("FIM")) {
				IDlinha = Integer.parseInt(linha);
				
				for (int i = 0; i < vetor.length; i++) {
					if (vetor[i].getRoomID() == IDlinha) {
						listaID.inserirFim(vetor[i]);;
						i = vetor.length;
					}
				}
				
				linha = MyIO.readLine();
			}
			
			int instrucoes = MyIO.readInt();
			String comando;
			String[] aux = new String[3];
			int valor , pos;
			
			for(int i = 0; i < instrucoes; i++) {
				comando = MyIO.readLine();
				aux = comando.split(" ");
				
				if(aux[0].equals("II")) {
					valor = Integer.parseInt(aux[1]);
					for (int j = 0; j < vetor.length; j++) {
						if (vetor[j].getRoomID() == valor) {
							listaID.inserirInicio(vetor[j]);
							j = vetor.length;
						}
					}
				}
				
				else if(aux[0].equals("I*")) {
					valor = Integer.parseInt(aux[2]);
					pos = Integer.parseInt(aux[1]);
					for (int j = 0; j < vetor.length; j++) {
						if (vetor[j].getRoomID() == valor) {
							listaID.inserir(vetor[j] , pos);
							j = vetor.length;
						}
					}
				}
				
				else if(aux[0].equals("IF")) {
					valor = Integer.parseInt(aux[1]);
					for (int j = 0; j < vetor.length; j++) {
						if (vetor[j].getRoomID() == valor) {
							listaID.inserirFim(vetor[j]);
							j = vetor.length;
						}
					}
				}
				
				else if(aux[0].equals("RI")) {
					System.out.println("(R) " + listaID.removerInicio().getRoomID());
				}
				
				else if(aux[0].equals("R*")) {
					pos = Integer.parseInt(aux[1]);
					System.out.println("(R) " + listaID.remover(pos).getRoomID());		
				}
				
				else if(aux[0].equals("RF")) {
					System.out.println("(R) " + listaID.removerFim().getRoomID());
				}
			}
			
			listaID.mostrar();

			arquivoTmp.close();
			arquivoTmp2.close();
			arquivoLeitura.close();
			arquivoLeitura2.close();
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		}

}

class Lista {
	private Acomodacao[] lista;
	private int primeiro;
	private int ultimo;
	private int tamanho;
	
	public Lista(int M) {
		
		tamanho = M;
		lista = new Acomodacao[tamanho];
		primeiro = 0;
		ultimo = 0;
	}

	public Lista() {
		tamanho = 10;
		lista = new Acomodacao[tamanho];
		primeiro = 0;
		ultimo = 0;
	}
	
	public boolean listaVazia() {
	
		if (primeiro == ultimo)
			return true;
		else
			return false;
	}
	
	public boolean listaCheia() {
		
		if (ultimo == tamanho)
			return true;
		else
			return false;
	}
	
	public void inserirInicio (Acomodacao novo) throws Exception {
		if (!listaCheia()) {
				for (int i = ultimo; i > 0; i--) {
					lista[i] = lista[i-1];
				}
				
				lista[0] = novo;
				ultimo++;		

		} else
			throw new Exception("Não foi possível inserir o novo item na lista: a lista está cheia!");
	}
	
	
	public void inserir(Acomodacao novo, int posicao) throws Exception {
		
		if (!listaCheia()) {
			if ((posicao >= 0) && (posicao <= ultimo)) {
				for (int i = ultimo; i > posicao; i--)
					lista[i] = lista[i-1];
				
				lista[posicao] = novo;
				ultimo++;
					
			} else
				throw new Exception("Não foi possível inserir o item na lista: posição inválida!");
		} else
			throw new Exception("Não foi possível inserir o novo item na lista: a lista está cheia!");
	}
	
	public void inserirFim(Acomodacao novo) throws Exception{
		if (!listaCheia()) {
			lista[ultimo] = novo;
			ultimo++;

	} else
		throw new Exception("Não foi possível inserir o novo item na lista: a lista está cheia!");
	}
	
	public Acomodacao removerInicio() throws Exception{
		Acomodacao removido;
		
		if(! listaVazia()) {
			removido = lista[0];
			ultimo--;
			
			for(int i = 0; i <= ultimo; i++) {
				lista[i] = lista[i+1];
			}
			
			return removido;
		}
		else {
			throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
		}
		
	}
	
	public Acomodacao remover(int posicao) throws Exception {
		
		Acomodacao removido;
		
		if (! listaVazia()) {
			if ((posicao >= 0) && (posicao < ultimo)) {
				removido = lista[posicao];
				
				ultimo--;
				
				for (int i = posicao; i < ultimo; i++)
					lista[i] = lista[i+1];
				
				return removido;
			} else
				throw new Exception("Não foi possível remover o item da lista: posição inválida!");
		} else
			throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");	
	}
	
	public Acomodacao removerFim() throws Exception{
		Acomodacao removido;
		
		if(! listaVazia()) {
			removido = lista[ultimo - 1];		
			ultimo--;
			
			return removido;
		}
		else {
			throw new Exception("Não foi possível remover o item da lista: a lista está vazia!");
		}
		
	}
	
	public void mostrar() throws Exception {
		
		if (! listaVazia()) {
			for (int i = 0; i < ultimo; i++) {
				System.out.print("[" + i + "]");
				lista[i].imprimir();
			}
		} else
			throw new Exception ("Não foi possível imprimir o conteúdo da lista: a lista está vazia!");
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