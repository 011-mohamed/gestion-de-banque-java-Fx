package entities;

public class Client {
	
	private int cin ;
	private String nomClient;
	private String prenomClient;
	private String adresse;
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Client(int cin, String nomClient, String prenomClient, String adresse) {
		super();
		this.cin = cin;
		this.nomClient = nomClient;
		this.prenomClient = prenomClient;
		this.adresse = adresse;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getPrenomClient() {
		return prenomClient;
	}

	public void setPrenomClient(String prenomClient) {
		this.prenomClient = prenomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Override
	public String toString() {
		return "Client [cin=" + cin + ", nomClient=" + nomClient + ", prenomClient=" + prenomClient + ", adresse="
				+ adresse + "]";
	}
	
	
	
}
