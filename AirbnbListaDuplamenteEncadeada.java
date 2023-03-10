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
			
			ListaDuplamenteEncadeada listaID = new ListaDuplamenteEncadeada();
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

class CelulaDE {
	private Acomodacao item;
	private CelulaDE proximo;
	private CelulaDE anterior;
	
	public CelulaDE(Acomodacao item) {
		
		this.item = item;
		this.proximo = null;
		this.anterior = null;
	}
	
	public CelulaDE() {
	
		this.item = new Acomodacao();
		this.proximo = null;
		this.anterior = null;
	}
	
	public Acomodacao getItem() {
		return item;
	}
	
	public void setItem(Acomodacao item) {
		this.item = item;
	}

	public CelulaDE getProximo() {
		return proximo;
	}

	public void setProximo(CelulaDE proximo) {
		this.proximo = proximo;
	}

	public CelulaDE getAnterior() {
		return anterior;
	}

	public void setAnterior(CelulaDE anterior) {
		this.anterior = anterior;
	}
}

class ListaDuplamenteEncadeada {
	private CelulaDE primeiro;
	private CelulaDE ultimo;
	private int tamanho;
	
	public ListaDuplamenteEncadeada() {
		CelulaDE sentinela = new CelulaDE();
		primeiro = sentinela;
		ultimo = sentinela;
		tamanho = 0;
	}
	
	public boolean listaVazia() {
		if(primeiro == ultimo) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void inserirInicio(Acomodacao novo) {
		CelulaDE anterior , novaCelula , proximaCelula;
		novaCelula = new CelulaDE(novo);
		anterior = primeiro;
		proximaCelula = anterior.getProximo();
		anterior.setProximo(novaCelula);
		novaCelula.setProximo(proximaCelula);
		novaCelula.setAnterior(anterior);
		
		tamanho++;
		
		if(proximaCelula == null) {
			ultimo = novaCelula;
		}
	}
	
	public void inserir(Acomodacao novo , int posicao) throws Exception{
		
		CelulaDE anterior , novaCelula , proximaCelula;
		
		if(posicao >= 0 && posicao <= tamanho) {
			anterior = primeiro;
			for(int i = 0; i < posicao; i++) {
				anterior = anterior.getProximo();
			}
			
			novaCelula = new CelulaDE(novo);
			proximaCelula = anterior.getProximo();
			anterior.setProximo(novaCelula);
			novaCelula.setProximo(proximaCelula);
			novaCelula.setAnterior(anterior);
			
			if(posicao == tamanho) {
				ultimo = novaCelula;
			}
			
			tamanho++;
		}
		
		else {
			throw new Exception("N??o foi poss??vel inserir o item na lista: posi????o de inser????o inv??lida!");
		}
	}
	
	public void inserirFim(Acomodacao novo) {
		CelulaDE novaCelula = new CelulaDE(novo);
		CelulaDE anterior = primeiro;
		
		ultimo.setProximo(novaCelula);
		novaCelula.setAnterior(ultimo);
		ultimo = novaCelula;
		
		tamanho++;
	}
	
	public Acomodacao removerInicio() throws Exception{
		
		CelulaDE anterior , removida , proximaCelula;
		
		if(!listaVazia()) {
			anterior = primeiro;
			removida = anterior.getProximo();
			proximaCelula = removida.getProximo();
			anterior.setProximo(proximaCelula);
			proximaCelula.setAnterior(anterior);
			
			tamanho--;
			return removida.getItem();
		}
		
		else {
			throw new Exception("N??o foi poss??vel remover o item pois a lista est?? vazia!");
		}
	}
	
	public Acomodacao remover(int posicao) throws Exception{
		
		CelulaDE anterior , removida , proximaCelula;
		
		if(!listaVazia()) {
			if(posicao >= 0 && posicao <= tamanho) {
				anterior = primeiro;
				
				for(int i = 0; i < posicao; i++) {
					anterior = anterior.getProximo();
				}
				
				removida = anterior.getProximo();
				proximaCelula = removida.getProximo();
				proximaCelula.setAnterior(anterior);
				anterior.setProximo(proximaCelula);
				
				if(removida == ultimo) {
					ultimo = anterior;
				}
				
				tamanho--;
				return removida.getItem();
			}
			else {
				throw new Exception("N??o foi poss??vel remover o item: posi????o inv??lida!");
			}
		}
		
		else {
			throw new Exception("N??o foi poss??vel remover o item pois a lista est?? vazia!");
		}
	}
	
	public Acomodacao removerFim() throws Exception{
		
		CelulaDE removida , proximaCelula;
		proximaCelula = null;
		
		if(!listaVazia()) {
						
			removida = ultimo;
			proximaCelula = ultimo.getAnterior();

			ultimo = proximaCelula;
			
			tamanho--;
			return removida.getItem();
		}
		
		else {
			throw new Exception("N??o foi poss??vel remover o item pois a lista est?? vazia!");
		}
	}
	
	public void mostrar() throws Exception{
		
		CelulaDE aux;
		
		if (! listaVazia()) {
			aux = primeiro.getProximo();
			int posicao = 0;
			while (posicao < tamanho) {
				System.out.print("[" + posicao + "]");
				aux.getItem().imprimir();
				aux = aux.getProximo();
				posicao++;
			}
		} else
			throw new Exception("N??o foi poss??vel imprimir o conte??do da lista: a lista est?? vazia!");
	}
}