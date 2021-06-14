package entities;

public class Compte {
	
	private int numCompte;
	private int solde	;
	private int montantDecouverte;
	private int montantDebit;
	private String etatCompte;
	private int cin ;
	
	
	public Compte() {
		super();
	}


	public Compte( int numC ,int solde, int montantDecouverte, int montantDebit, String etatCompte, int cin) {
		super();
		
		this.numCompte = numC;
		this.solde = solde;
		this.montantDecouverte = montantDecouverte;
		this.montantDebit = montantDebit;
		this.etatCompte = etatCompte;
		this.cin = cin;
	}

	
	
	
	public int getNumCompte() {
		return numCompte;
	}


	public void setNumCompte(int numCompte) {
		this.numCompte = numCompte;
	}


	public int getSolde() {
		return solde;
	}


	public void setSolde(int solde) {
		this.solde = solde;
	}


	public int getMontantDecouverte() {
		return montantDecouverte;
	}


	public void setMontantDecouverte(int montantDecouverte) {
		this.montantDecouverte = montantDecouverte;
	}


	public int getMontantDebit() {
		return montantDebit;
	}


	public void setMontantDebit(int montantDebit) {
		this.montantDebit = montantDebit;
	}


	public String getEtatCompte() {
		return etatCompte;
	}


	public void setEtatCompte(String etatCompte) {
		this.etatCompte = etatCompte;
	}


	public int getCin() {
		return cin;
	}


	public void setCin(int cin) {
		this.cin = cin;
	}


	@Override
	public String toString() {
		return "Compte [numCompte=" + numCompte + ", solde=" + solde + ", montantDecouverte=" + montantDecouverte
				+ ", montantDebit=" + montantDebit + ", etatCompte=" + etatCompte + ", cin=" + cin + "]";
	}
	
	
	
	

}
